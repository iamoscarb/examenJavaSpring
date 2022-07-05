package ids.airport.airport.controller;

import ids.airport.airport.exception.Mensaje;
import ids.airport.airport.model.Country;
import ids.airport.airport.service.Country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    //petición tipo GET
    @GetMapping("/listCountry")
    public ResponseEntity<?> getAllCountry(){
        List<Country> lista = countryService.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No contry to show"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(countryService.findAll());
    }

    //petición tipo GET
    @GetMapping("/findEmployee/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable long id) {
        return ResponseEntity.ok().body(countryService.findById(id));
    }

    //petición PUT
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable long id, @RequestBody Country country){
        country.setId(id);
        return ResponseEntity.ok().body(this.countryService.updateCountry(country));
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public HttpStatus deleteCountry(@PathVariable long id){
        this.countryService.deleteCountry(id);
        return HttpStatus.OK;
    }
}
