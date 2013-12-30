package net.msonic.framework;

import android.app.IntentService;
import android.content.Intent;

import com.google.inject.Inject;

import roboguice.service.RoboIntentService;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class LoginService extends RoboIntentService {

    @Inject LoginProxy loginProxy;

    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            loginProxy.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
