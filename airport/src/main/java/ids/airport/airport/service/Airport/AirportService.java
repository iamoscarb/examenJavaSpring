package ids.airport.airport.service.Airport;

import ids.airport.airport.model.Airport;

import java.util.List;

public interface AirportService {
    Airport findById(long airportId);

    List<Airport> findAll();

    void deleteAirport(long airportId);

    Airport updateAirport(Airport airport);
}
