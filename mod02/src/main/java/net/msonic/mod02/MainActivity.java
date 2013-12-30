package net.msonic.mod02;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static String TAG =MainActivity.class.getCanonicalName();

    IRemoteService mRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        Toast.makeText(this,"Demoo2",Toast.LENGTH_SHORT).show();
    }



    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface((IBinder)service);

            Log.d(TAG, "onServiceConnected() connected");
            if(mRemoteService==null){
                Log.d(TAG, "onServiceConnected() null");
            }else{
                Log.d(TAG, "onServiceConnected() not null");
            }

            /*
            try {
                String message=mRemoteService.sayHello("Mina");
                Log.v("message", message);
            } catch (RemoteException e) {
                Log.e("RemoteException", e.toString());
            }*/
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d(TAG, "onServiceDisconnected() disconnected");
            mRemoteService = null;


        }
    };

    public void btnCall(View v){
        try {
            String msg = mRemoteService.sayHello("sss");
            Log.d(TAG,msg);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void btnIniciarServicio(View v){
        Intent intent = new Intent(this, DownloadService.class);
        boolean ok=bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.v("ok", String.valueOf(ok));
        /*
       if(ok){

        }*/



    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
