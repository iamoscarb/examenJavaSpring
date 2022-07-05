package ids.airport.airport.controller;

import ids.airport.airport.exception.Mensaje;
import ids.airport.airport.model.Airport;
import ids.airport.airport.service.Airport.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    //petición tipo GET
    @GetMapping("/listAirport")
    public ResponseEntity<?> getAllAirports(){
        List<Airport> lista = airportService.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No airports to show"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(airportService.findAll());
    }

    //petición tipo GET
    @GetMapping("/findAirport/{id}")
    public ResponseEntity<Airport> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok().body(airportService.findById(id));
    }

    //petición PUT
    @PutMapping("/updateAirport/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable long id, @RequestBody Airport airport){
        airport.setId(id);
        return ResponseEntity.ok().body(this.airportService.updateAirport(airport));
    }

    @DeleteMapping("/deleteAirport/{id}")
    public HttpStatus deleteAirport(@PathVariable long id){
        this.airportService.deleteAirport(id);
        return HttpStatus.OK;
    }
}
