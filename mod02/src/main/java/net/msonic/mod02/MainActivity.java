package net.msonic.mod02;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {


    IRemoteService mRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Toast.makeText(this,"Demoo2",Toast.LENGTH_SHORT).show();
    }



    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);
            try {
                String message=mRemoteService.sayHello("Mina");
                Log.v("message", message);
            } catch (RemoteException e) {
                Log.e("RemoteException", e.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {




        }
    };


    public void btnIniciarServicio(View v){
        Intent intent = new Intent(this, DownloadService.class);
        //startService(intent);


        boolean ok=bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.v("ok", String.valueOf(ok));

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            return rootView;
        }
    }

}
