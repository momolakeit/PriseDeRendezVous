package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.StylesDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.services.PriseDeRendezVousServices;
import com.sun.istack.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class PriseDeRendezVousController {
    @Autowired
    PriseDeRendezVousServices priseDeRendezVousServices;

    @RequestMapping(path = "/prendreRendezVous",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> prendreRendezVous(@RequestBody Map<String, String> json){
        JSONObject style= new JSONObject(json.get("style"));
        JSONObject employeeDto= new JSONObject(json.get("employeeDTO"));
        JSONObject clientDto= new JSONObject(json.get("clientDTO"));
        return ResponseEntity.ok(priseDeRendezVousServices.createRendezVous( Long.valueOf(style.getString("id")),
                                                                            new Date(System.currentTimeMillis()),
                                                                            employeeDto.getString("email"),
                                                                            clientDto.getString("email")));

    }
    @RequestMapping(path = "/createStyle",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createStyle( @RequestParam("file") MultipartFile styleImg,
                                          @RequestPart("employeeDto") EmployeeDTO employeeDTO ) throws IOException {
        Styles styles = new Styles();
        styles.setImageUrl(priseDeRendezVousServices.uploadFile(styleImg));
        return ResponseEntity.ok(priseDeRendezVousServices.createStyle(styles,employeeDTO));


    }



}
