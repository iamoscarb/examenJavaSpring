package ids.airport.airport.controller;

import ids.airport.airport.exception.Mensaje;
import ids.airport.airport.model.Language;
import ids.airport.airport.service.Language.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/listLanguage")
    public ResponseEntity<?> getAllLanguage(){
        List<Language> lista = languageService.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(new Mensaje("No languages to show"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(languageService.findAll());
    }

    //petición tipo GET
    @GetMapping("/findLanguage/{id}")
    public ResponseEntity<Language> getLanguageyById(@PathVariable long id) {
        return ResponseEntity.ok().body(languageService.findById(id));
    }

    //petición PUT
    @PutMapping("/updateLanguage/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable long id, @RequestBody Language language){
        language.setId(id);
        return ResponseEntity.ok().body(this.languageService.updateLanguage(language));
    }

    @DeleteMapping("/deleteLanguage/{id}")
    public HttpStatus deleteLanguage(@PathVariable long id){
        this.languageService.deleteLanguage(id);
        return HttpStatus.OK;
    }
}
