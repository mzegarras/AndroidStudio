package net.msonic.framework;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class LoginResponse extends ResponseBase {

    @Expose()
    @SerializedName("USR")
    public UsuarioTO usuario;


}
