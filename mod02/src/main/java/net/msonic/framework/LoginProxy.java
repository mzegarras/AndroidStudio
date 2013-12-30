package net.msonic.framework;

import net.msonic.mod02.R;

import roboguice.inject.InjectResource;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class LoginProxy extends ProxyBase<LoginResponse> {


    @InjectResource(R.string.urlServiceSeguridad)protected String urlWS;


    @Override
    protected String getUrl() {
        // TODO Auto-generated method stub
        return urlWS.concat("/Logon");
    }


    public String usuario;
    public String password;

    @Override
    protected String requestText() {
        // TODO Auto-generated method stub
        LoginRequest request = new LoginRequest();
        request.usuario=usuario;
        request.password=password;

        return JSONHelper.serializar(request);
    }

    @Override
    protected LoginResponse responseText(String json) {
        // TODO Auto-generated method stub
        LoginResponse loginResponse = JSONHelper.desSerializar(json, LoginResponse.class);
        return loginResponse;
    }
}
