package com.example.demoapigooglemaps.controller;
import com.example.demoapigooglemaps.model.Coordenate;
import com.example.demoapigooglemaps.model.Address;
import com.example.demoapigooglemaps.services.OpenStreetMapAPI;
import com.example.demoapigooglemaps.utils.AddressProperties;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Controller
@ConfigurationProperties(prefix = "direccion")
@PropertySource("classpath:address.properties")
public class MapaController {

    private static String PATH = "mapa";

    private static final Logger LOGGER = getLogger(MapaController.class);

    @Autowired
    private AddressProperties addressProperties;

    @RequestMapping(value = "/showMap")
    public String showMap(Model model) {
        try {
            //Se obtienen las coordenadas de origen y destino
            Address sourceAddress = new Address(addressProperties.getOriginStreet(), addressProperties.getOriginHeight(), addressProperties.getOriginLocality());
            Address destinationAddress = new Address(addressProperties.getDestinationStreet(), addressProperties.getDestinationHeight(), addressProperties.getDestinationLocality());

            OpenStreetMapAPI.getCoordenates(sourceAddress);
            OpenStreetMapAPI.getCoordenates(destinationAddress);

            List<Coordenate> coordenates = OpenStreetMapAPI.getRoutesCoordinates(sourceAddress.getCoordenate().toString(), destinationAddress.getCoordenate().toString());

            model.addAttribute("coordenates", coordenates);
            model.addAttribute("originPosition", sourceAddress.getCoordenate());

        } catch(IOException | URISyntaxException ex) {
            LOGGER.error("Ha ocurrido un error: " + ex.getMessage());
        }

        return PATH;
    }
}