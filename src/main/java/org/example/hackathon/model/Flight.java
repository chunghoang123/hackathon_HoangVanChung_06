package org.example.hackathon.model;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class Flight {
    private Long id;

    @NotBlank(message = "Số hiệu chuyến bay không được trống")
    @Size(min = 5, max = 20, message = "Số hiệu chuyến bay từ (5 - 20) ký tự")
    private String flightNumber;

    @NotBlank(message = "Điểm đến không được trống")
    private String destination;

    private LocalDateTime departureTime;

    @Min(value = 100, message = "Giá vé phải lớn hơn 100")
    private double ticketPrice;

    private String airlineLogo;
    private MultipartFile imageFile;

    public Flight() {
    }

    public Flight(Long id, String flightNumber, String destination, LocalDateTime departureTime, double ticketPrice, String airlineLogo) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.ticketPrice = ticketPrice;
        this.airlineLogo = airlineLogo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getAirlineLogo() {
        return airlineLogo;
    }

    public void setAirlineLogo(String airlineLogo) {
        this.airlineLogo = airlineLogo;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}