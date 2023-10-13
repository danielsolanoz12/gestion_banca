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

public class ApiClientesP extends BasicApi implements IApi {

    @RestController
    @RequestMapping("/api")
    public class ClientePersonaController {
        @Autowired
        private ClienteService clienteService;
        @Autowired
        private PersonaService personaService;
        @Autowired
        private RabbitTemplate rabbitTemplate;

        @PostMapping("/cuentas/crear")
        public ResponseEntity<Cliente> crearCliente(@RequestBody @Valid Cliente cliente) {
            Cliente creado = clienteService.crearCliente(cliente);
            rabbitTemplate.convertAndSend("cuenta-queue", "Cuenta creada: " + cliente.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        }

        @PutMapping("/cuentas/put")
        public ResponseEntity<Cliente> crearCliente(@RequestBody @Valid Cliente cliente) {
            Cliente creado = clienteService.crearCliente(cliente);
            rabbitTemplate.convertAndSend("cuenta-queue", "Cuenta actualizada exitosamente: " + cliente.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        }

        @DeleteMapping("/cuentas/delete")
        public ResponseEntity<Cliente> crearCliente(@RequestBody @Valid Cliente cliente) {
            Cliente creado = clienteService.crearCliente(cliente);
            rabbitTemplate.convertAndSend("cuenta-queue", "Cuenta eliminada exitosamente: " + cliente.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        }
    }
}