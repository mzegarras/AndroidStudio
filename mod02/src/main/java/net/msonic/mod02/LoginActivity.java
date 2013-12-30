package net.msonic.mod02;

//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.google.inject.Inject;

import net.msonic.framework.LoginProxy;
import net.msonic.framework.LoginService;

import roboguice.activity.RoboActivity;

public class LoginActivity extends RoboActivity {

    public static String TAG = LoginActivity.class.getCanonicalName();

    @Inject LoginProxy loginProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*if(android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        setContentView(R.layout.login_activity);

    }


    public void btnIngresar(View view){

        loginProxy.usuario="prios";
        loginProxy.password="pr";

        Intent intent = new Intent(this, LoginService.class);
        startService(intent);
        /*
        Thread t = new Thread() {
            public void run() {
                Log.d(TAG,"Invoke");

                loginProxy.usuario="prios";
                loginProxy.password="pr";



                try {
                    loginProxy.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        t.start();*/

    }



}
