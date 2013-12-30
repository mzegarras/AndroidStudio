package net.msonic.framework;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class UsuarioTO {

    @SerializedName("NOM")
    @Expose()
    public String nombre;

    @SerializedName("USU")
    @Expose()
    public String usuario;

    @SerializedName("SAP")
    @Expose()
    public String codigoSAP;

    @SerializedName("CAR")
    @Expose()
    public String cargo;

    @SerializedName("PLA")
    @Expose()
    public String planta;

    @SerializedName("GER")
    @Expose()
    public String gerencia;

    @SerializedName("COR")
    @Expose()
    public String correo;


    @SerializedName("ID")
    @Expose()
    public int id;
}
