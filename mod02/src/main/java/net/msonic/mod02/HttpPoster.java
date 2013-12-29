package net.msonic.mod02;

/**
 * Created by manuelzegarra on 28/12/13.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class HttpPoster {

    public static int TIMEOUT = 0;

    private static String TAG = HttpPoster.class.getCanonicalName();
    private int intentos = 3;
    private String url;
    private String request;
    private String response;
    private String contentType = "application/xml";
    private int timeOut = 1000 * 90;
    private int status;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getResponse() {
        return response;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String HEADER_BEGIN_TAG = "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">";
    private String HEADER_END_TAG = "</string>";

    private String executeHttpPostWithRetry() throws Exception {



        URL url;
        HttpURLConnection connection = null;


        StringBuffer sf = new StringBuffer();
        sf.append(HEADER_BEGIN_TAG);
        sf.append(this.request);
        sf.append(HEADER_END_TAG);

        try {

            StringEntity stringRequest = new StringEntity(sf.toString(),HTTP.UTF_8);

            url = new URL(this.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", this.contentType);
            connection.setRequestProperty("Pragma", "no-cache");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Expires", "-1");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);


            //Send request
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream ());
            wr.writeBytes (stringRequest.toString());
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            return response.toString();


        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }




		/*
		this.status = -1;
		Log.d(TAG, String.format("STATUS:%s", this.status));



		HttpParams httpParams = new BasicHttpParams();

		if (TIMEOUT == 0) {
			HttpConnectionParams.setConnectionTimeout(httpParams, this.timeOut);
			HttpConnectionParams.setSoTimeout(httpParams, this.timeOut);
		} else {
			HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
		}
		Log.d(TAG, String.format("TIMEOUT:%s", this.timeOut));
		BufferedReader in = null;

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		httpClient
				.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
						0, false));


		try {

			HttpPost httpPost = new HttpPost(this.url);
			httpPost.addHeader("Content-Type", this.contentType);
			httpPost.addHeader("Pragma", "no-cache");
			httpPost.addHeader("Cache-Control", "no-cache");
			httpPost.addHeader("Expires", "-1");

			StringBuffer sf = new StringBuffer();
			sf.append(HEADER_BEGIN_TAG);
			sf.append(this.request);
			sf.append(HEADER_END_TAG);

			Log.d(TAG, String.format("RQ:%s", this.request));
			Log.d(TAG, String.format("URL:%s", this.url));

			StringEntity stringRequest = new StringEntity(sf.toString(),
					HTTP.UTF_8);
			httpPost.setEntity(stringRequest);

			if (httpPost.getEntity() != null) {
				Log.d(TAG, String.format("UPLOADBYTES:%s byte", httpPost
						.getEntity().getContentLength()));
			} else {
				Log.d(TAG, String.format("UPLOADBYTES:%s byte", 0));
			}

			HttpResponse response = httpClient.execute(httpPost);

			this.status = response.getStatusLine().getStatusCode();
			Log.d(TAG, String.format("STATUS:%s", this.status));

			if (response.getEntity() != null) {
				Log.d(TAG, String.format("DONWLOADBYTES:%s byte", response
						.getEntity().getContentLength()));
			}

			if (HttpStatus.SC_OK == this.status) {

				char[] buffer = new char[8 * 1024];
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()), 8 * 1024);
				Writer writer = new StringWriter();

				int n;
				while ((n = in.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}

				in.close();

				sf = new StringBuffer(writer.toString());
				sf.replace(0, HEADER_BEGIN_TAG.length(), "");
				sf.replace(sf.length() - HEADER_END_TAG.length(), sf.length(),
						"");

				Log.d(TAG, String.format("RESPONSE:%s", sf.toString()));

				return sf.toString();

			} else {
				String msg = String.format("Error invocando Web Service - %s",
						this.status);
				Log.d(TAG, msg);
				throw new Exception(msg);
			}
		} catch (Exception ex) {
			this.status = -1;
			throw ex;
		} finally {

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					Log.e(TAG, String.valueOf(this.status));
				}
			}

			if (httpClient != null) {
				Log.d(TAG, "close http connection");
				httpClient.getConnectionManager().shutdown();
				httpClient.getConnectionManager().closeExpiredConnections();
			}
		}*/

    }

    public void invoke() throws Exception {

        int intento = 0;
        while (intento < intentos) {
            intento += 1;

            Log.d("HttpPoster:", String.format("Intento %s", intento));

            try {
                this.response = executeHttpPostWithRetry();
                break;
            } catch (Exception e) {

                Log.e("invoke", String.format("%s", intento), e);
                this.response = null;
                if (intento < intentos) {

                } else {
                    throw e;
                }
            }

        }
    }
}
