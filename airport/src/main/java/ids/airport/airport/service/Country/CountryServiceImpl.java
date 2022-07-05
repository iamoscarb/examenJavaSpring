package ids.airport.airport.service.Country;

import ids.airport.airport.exception.ResourceNotFoundException;
import ids.airport.airport.model.Airport;
import ids.airport.airport.model.Country;
import ids.airport.airport.model.Language;
import ids.airport.airport.repository.CountryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;

    @Override
    public Country findById(long countryId) {
        Optional<Country> countrytDb = this.countryRepository.findById(countryId);
        if(countrytDb.isPresent()){
            return countrytDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + countrytDb);
        }
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) this.countryRepository.findAll();
    }

    @Override
    public void deleteCountry(long countryId) {
        Optional<Country> countryDb = this.countryRepository.findById(countryId);

        if (countryDb.isPresent()) {
            this.countryRepository.delete(countryDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + countryId);
        }

    }

    @Override
    public Country updateCountry(Country country) {
        Optional<Country> countryDb = this.countryRepository.findById(country.getId());

        if (countryDb.isPresent()) {
            Country countryUpdate = countryDb.get();
            countryUpdate.setId(country.getId());
            countryUpdate.setcCode(country.getcCode());
            countryUpdate.setcName(country.getcName());

            countryRepository.save(countryUpdate);
            return countryUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + country.getId());
        }
    }
}
