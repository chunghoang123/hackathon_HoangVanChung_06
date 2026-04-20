package org.example.hackathon.controller;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.example.hackathon.model.Flight;
import org.example.hackathon.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public String listFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flight-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight-from";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "flight-from";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("flight") Flight flight,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "flight-from";
        }

        MultipartFile file = flight.getImageFile();
        if (file != null && !file.isEmpty()) {
            try {
                String uploadPath = "C:/path/to/your/project/src/main/webapp/WEB-INF/resources/img/";

                File dir = new File(uploadPath);
                if (!dir.exists()) dir.mkdirs();

                String fileName = file.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

                file.transferTo(serverFile);
                flight.setAirlineLogo(fileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (flight.getId() != null) {
            Flight oldFlight = flightService.getById(flight.getId());
            flight.setAirlineLogo(oldFlight.getAirlineLogo());
        }

        flightService.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        flightService.delete(id);
        return "redirect:/flights";
    }
}