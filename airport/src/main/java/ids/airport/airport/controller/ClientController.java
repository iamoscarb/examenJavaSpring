package ids.airport.airport.controller;

import ids.airport.airport.exception.Mensaje;
import ids.airport.airport.model.Client;
import ids.airport.airport.service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiv1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/listClients")
    public ResponseEntity<?> getAllClients(){
        List<Client> lista = clientService.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No clients to show"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(clientService.findAll());
    }

    //petición tipo GET
    @GetMapping("/findClient/{id}")
    public ResponseEntity<Client> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    //petición PUT
    @PutMapping("/updateClient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client client){
        client.setId(id);
        return ResponseEntity.ok().body(this.clientService.updateClient(client));
    }

    @DeleteMapping("/deleteClient/{id}")
    public HttpStatus deleteClient(@PathVariable long id){
        this.clientService.deleteClient(id);
        return HttpStatus.OK;
    }

    @PostMapping("/add")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return ResponseEntity.ok().body(this.clientService.createClient(client));
    }
}
