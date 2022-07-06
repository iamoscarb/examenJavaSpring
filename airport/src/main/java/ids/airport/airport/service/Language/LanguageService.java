package ids.airport.airport.service.Language;

import ids.airport.airport.model.Language;

import java.util.List;

public interface LanguageService {

    Language findById(long languageId);

    List<Language> findAll();

    void deleteLanguage(long languageId);

    Language updateLanguage(Language language);

    Language createLanguage(Language language);
}
