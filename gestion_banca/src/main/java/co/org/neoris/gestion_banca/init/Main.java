package co.org.neoris.gestion_banca.init;

import co.org.neoris.gestion_banca.apis.*;
import spark.*;

public class Main {

    public static void main(String[] args) {
        
        Spark.staticFiles.location("/public");
        //puerto de conexion
        Spark.port(99);       
        //Registro de apis
        ApiCategoria.singleton();
        ApiLibro.singleton();
        ApiAutor.singleton();
    }
}
