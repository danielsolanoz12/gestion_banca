package co.org.neoris.gestion_banca.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer{
    //Primero tener una instancia de el mismo estatica
    private static JsonTransformer instance = null;
    private Gson gson = null;
    
    //Segundo tener constructor privado
    private JsonTransformer(){
        //gson = new Gson();
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    
    //Tercero tener un metodo estatico publico
    public static JsonTransformer singleton(){
        if(instance==null){
            instance = new JsonTransformer();
        }
        return instance;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public String render(Object o) throws Exception {
        String json = gson.toJson(o);
        return json;
    }
    
}
