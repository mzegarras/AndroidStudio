package net.msonic.mod02;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by manuelzegarra on 28/12/13.
 */
public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(DownloadService.class.getCanonicalName(),"Inicio Servicio");
        /*for (int i=1;i<=100;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(DownloadService.class.getCanonicalName(),String.valueOf(i) + Thread.currentThread().getId());
        }*/


        HttpPoster httpPoster = new HttpPoster();
        httpPoster.setUrl("http://192.168.0.14/test/ClienteService.svc/q/323");
        try {
            httpPoster.invoke();
            Log.d(DownloadService.class.getCanonicalName(),"OK");
            Log.d(DownloadService.class.getCanonicalName(),httpPoster.getResponse());
            //return httpPoster.getResponse();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d(DownloadService.class.getCanonicalName(),"Error");
            //return "ERROR";
        }

    }

}
