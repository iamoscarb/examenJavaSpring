package ids.airport.airport.service.Country;

import ids.airport.airport.model.Country;

import java.util.List;

public interface CountryService {
    Country findById(long airportId);

    List<Country> findAll();

    void deleteCountry(long countryId);

    Country updateCountry(Country country);
}
