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
        private ClientePersonaService clientePersonaService;
        @Autowired
        private RabbitTemplate rabbitTemplate;

        @PostMapping("/clientes")
        public ResponseEntity<Cliente> crearCliente(@RequestBody @Valid Cliente cliente) {
            Cliente creado = clientePersonaService.crearCliente(cliente);
            rabbitTemplate.convertAndSend("cliente-queue", "Cliente creado: " + cliente.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        }

        @PostMapping("/personas")
        public ResponseEntity<Persona> crearPersona(@RequestBody @Valid Persona persona) {
            Persona creada = clientePersonaService.crearPersona(persona);
            rabbitTemplate.convertAndSend("persona-queue", "Persona creada: " + persona.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        }
    }
}