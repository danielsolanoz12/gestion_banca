package co.org.neoris.gestion_banca.apis;

import co.org.neoris.gestion_banca.apis.abstract_.BasicApi;
import co.org.neoris.gestion_banca.apis.abstract_.IApi;
import co.org.neoris.gestion_banca.utils.JsonTransformer;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import spark.Request;
import spark.Response;

public class ApiMovimientos extends BasicApi implements IApi {

    private static ApiMovimientos instance = null;
    private Gson gson = null;
    private String path = "/movimientos";
    private MovimientosJpaController controller;

    private ApiMovimientos() {
        init();
        gson = JsonTransformer.singleton().getGson();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU_PRUEBA");
        controller = new AutorJpaController(emf);
    }

    public static ApiMovimientos singleton() {
        if (instance == null) {
            instance = new ApiMovimientos();
        }
        return instance;
    }

    @Override
    public String getPath() {
        return path;
    }

    /**
     * Metodo para crear o insertar un cliente
     * **/
    @Override
    public Object create(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        String body = rq.body();
        if (!body.trim().equals("")) {
            try {
                Clientes entity = gson.fromJson(body, Movimientos.class);
                controller.create(entity);
                rs.status(201);
                r.put("status", 201);
                r.put("message", "Creado con exito!");
                r.put("data", entity);
            } catch (JsonSyntaxException e) {
                rs.status(400);
                r.put("status", 400);
                r.put("message", e.getMessage());
            }
        } else {
            rs.status(400);
            r.put("status", 400);
            r.put("message", "El body no puede llegar vacio!");
        }
        return r;
    }

    /**
     * Metodo para editar o actualizar a un cliente
     * **/
    @Override
    public Object update(Request rq, Response rs) {
        Hashtable<String, Object> retorno = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            String body = rq.body();
            Movimientos newEntity = gson.fromJson(body, Movimientos.class);
            Movimientos oldEntity = controller.findAutor(id);
            if (oldEntity != null) {
                oldEntity.setAutNombre(newEntity.getAutNombre());
                oldEntity.setAutGenero(newEntity.getAutGenero());
                oldEntity.setAutFechaNacimiento(newEntity.getAutFechaNacimiento());
                controller.edit(oldEntity);
                retorno.put("status", 200);
                retorno.put("message", "Registro actualizado con exito!");
                retorno.put("data",oldEntity);
            } else {
                rs.status(404);
                retorno.put("status", 404);
                retorno.put("message", "Registros con id@"+id+" no encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            retorno.put("status", 400);
            retorno.put("message", e.getMessage());
        }
        return retorno;
    }

    /**
     * Metodo para hacer la eliminación de un cliente por su id
     * @param rq
     * @param rs
     * **/
    @Override
    public Object delete(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            controller.destroy(id);
            r.put("status", 200);
            r.put("message", "Eliminado con exito!");
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }

    /**
     * Metodo para obtener todos los clientes registrados
     * **/
    @Override
    public Object getAll(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        List<Movimientos> movimientos = controller.findAutorEntities();
        if (categorias.size() > 0) {
            r.put("status", 200);
            r.put("message", "Registros encontrados");
            r.put("data", movimientos);
        } else {
            rs.status(404);
            r.put("status", 404);
            r.put("message", "Registros no encontrados");
        }
        return r;
    }

    /**
     * Metodo para obtener un cliente especifico por su id
     * @param rq
     * @param rs
     * **/
    @Override
    public Object getById(Request rq, Response rs) {
        Hashtable<String, Object> r = new Hashtable<>();
        try {
            int id = Integer.parseInt(rq.params("id"));
            Clientes entity = controller.findAutor(id);
            if (entity != null) {
                r.put("status", 200);
                r.put("message", "Registro encontrado!");
                r.put("data", entity);
            } else {
                rs.status(404);
                r.put("status", 404);
                r.put("message", "Registro con id@" + id + " no encontrado!");
            }
        } catch (Exception e) {
            rs.status(400);
            r.put("status", 400);
            r.put("message", e.getMessage());
        }
        return r;
    }
}