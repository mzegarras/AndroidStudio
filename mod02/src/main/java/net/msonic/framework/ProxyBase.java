package net.msonic.framework;

import android.util.Log;

import java.io.IOException;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public abstract class ProxyBase<T extends ResponseBase> {

    public boolean sinDatos=false;
    public int timeOut=1000 * 90;
    protected abstract String getUrl();



    protected abstract String requestText();
    protected abstract T responseText(String json);

    private T response;
    private boolean exito;


    public boolean isExito() {
        return exito;
    }

    public T getResponse() {
        return response;
    }



    public void execute() throws Exception{

        String rp = null;
        try {
            HttpPoster httpPoster = new HttpPoster();
            Log.d("ProxyBase",this.getUrl());
            httpPoster.setUrl(this.getUrl());
            httpPoster.setRequest(this.requestText());
            httpPoster.setTimeOut(this.timeOut);
            httpPoster.invoke();


            if(httpPoster.getStatus()==200){
                rp = httpPoster.getResponse();

                T responseBase = responseText(rp);
                response = responseBase;
                exito=true;
            }else{
                Log.d("ProxyBase", "sin exito");
                response = null;
                exito=false;
            }

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            response = null;
            exito=false;
            Log.e("ProxyBase", "execute", e);
            throw e;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            response = null;
            exito=false;
            Log.e("ProxyBase", "execute", e);
            throw e;
        }


    }



}

