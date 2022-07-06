package ids.airport.airport.service.Language;

import ids.airport.airport.exception.ResourceNotFoundException;
import ids.airport.airport.model.Language;
import ids.airport.airport.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    @Override
    public Language findById(long languageId) {
        Optional<Language> languageDb = this.languageRepository.findById(languageId);
        if(languageDb.isPresent()){
            return languageDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + languageDb);
        }
    }

    @Override
    public List<Language> findAll() {
        return (List<Language>) this.languageRepository.findAll();
    }

    @Override
    public void deleteLanguage(long languageId) {
        Optional<Language> countryDb = this.languageRepository.findById(languageId);

        if (countryDb.isPresent()) {
            this.languageRepository.delete(countryDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + languageId);
        }

    }

    @Override
    public Language updateLanguage(Language language) {
        Optional<Language> languageDb = this.languageRepository.findById(language.getId());

        if (languageDb.isPresent()) {
            Language languageUpdate = languageDb.get();
            languageUpdate.setId(language.getId());
            languageUpdate.setLangCode(language.getLangCode());
            languageUpdate.setLangName(language.getLangName());

            languageRepository.save(languageUpdate);
            return languageUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + language.getId());
        }
    }

    @Override
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }
}
