package ids.airport.airport.service.Airport;

import ids.airport.airport.exception.ResourceNotFoundException;
import ids.airport.airport.model.Airport;
import ids.airport.airport.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AirportServiceImpl implements AirportService{

    @Autowired
    private AirportRepository airportRepository;


    @Override
    public Airport findById(long airportId) {
        Optional<Airport> airportDb = this.airportRepository.findById(airportId);
        if(airportDb.isPresent()){
            return airportDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + airportDb);
        }
    }

    @Override
    public List<Airport> findAll() {
        return (List<Airport>) this.airportRepository.findAll();
    }

    @Override
    public void deleteAirport(long airportId) {
        Optional<Airport> airportDb = this.airportRepository.findById(airportId);

        if (airportDb.isPresent()) {
            this.airportRepository.delete(airportDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + airportId);
        }
    }

    @Override
    public Airport updateAirport(Airport airport) {
        Optional<Airport> airportDb = this.airportRepository.findById(airport.getId());

        if (airportDb.isPresent()) {
            Airport airportUpdate = airportDb.get();
            airportUpdate.setId(airport.getId());
            airportUpdate.setArName(airport.getArName());

            airportRepository.save(airportUpdate);
            return airportUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + airport.getId());
        }
    }

    @Override
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }
}
