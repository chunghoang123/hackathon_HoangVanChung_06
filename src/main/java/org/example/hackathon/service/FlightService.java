package org.example.hackathon.service;

import org.example.hackathon.model.Flight;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private List<Flight> flights = new ArrayList<>();
    private long autoId = 1L;

    public FlightService() {
        flights.add(new Flight(autoId++, "AA101", "New York", LocalDateTime.of(2023, 10, 1, 10, 0), 100.0, "demo.png"));
        flights.add(new Flight(autoId++, "AA102", "Los Angeles", LocalDateTime.of(2023, 10, 1, 11, 0), 150.0, "anh2.png"));
    }

    public List<Flight> getAllFlights() { return flights; }

    public Flight getById(Long id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Flight flight) {
        if (flight.getId() == null) {
            flight.setId(autoId++);
            flights.add(flight);
        } else {
            int index = -1;
            for(int i=0; i<flights.size(); i++) {
                if(flights.get(i).getId().equals(flight.getId())) {
                    index = i;
                    break;
                }
            }
            if(index != -1) flights.set(index, flight);
        }
    }

    public void delete(Long id) {
        flights.removeIf(f -> f.getId().equals(id));
    }
}