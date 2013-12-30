package net.msonic.framework;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by manuelzegarra on 30/12/13.
 */
public class JSONHelper {

    public static <T> String serializar(T obj){
        //String text = gson.fromJson(jsonDemo,T);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(obj);
    }

    public static <T> T desSerializar(String obj,Type clazz){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(obj,clazz);
    }

}
