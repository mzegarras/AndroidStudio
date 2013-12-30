package net.msonic.mod02;


import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by manuelzegarra on 28/12/13.
 */
public class DownloadService extends IntentService {
    public static String TAG = DownloadService.class.getCanonicalName();


    public DownloadService(){
        super("DownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    // implementation of the aidl interface
    private final IRemoteService.Stub mBinder=new IRemoteService.Stub() {
        @Override
        public String sayHello(String message) throws RemoteException {
            return "Hello "+ message;

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(DownloadService.class.getCanonicalName(),"Inicio Servicio");

        HttpPoster httpPoster = new HttpPoster();
        httpPoster.setUrl("http://192.168.0.14/test/ClienteService.svc/q/323");
        try {
            httpPoster.invoke();
            Log.d(DownloadService.class.getCanonicalName(),"OK");
            Log.d(DownloadService.class.getCanonicalName(), httpPoster.getResponse());
            //return httpPoster.getResponse();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d(DownloadService.class.getCanonicalName(), "Error");
            //return "ERROR";
        }

    }

}
