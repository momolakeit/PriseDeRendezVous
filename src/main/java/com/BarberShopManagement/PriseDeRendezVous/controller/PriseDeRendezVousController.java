package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.services.PriseDeRendezVousServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
public class PriseDeRendezVousController {
    @Autowired
    PriseDeRendezVousServices priseDeRendezVousServices;

    @RequestMapping(path = "/prendreRendezVous",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> prendreRendezVous(Styles style,  EmployeeDTO employeeDTO, ClientDTO clientDTO){
     return ResponseEntity.ok(priseDeRendezVousServices.createRendezVous(   style.getId(),
                                                                            new Date(System.currentTimeMillis()),
                                                                            employeeDTO,clientDTO));

    }
    @RequestMapping(path = "/prendreRendezVous")
    @ResponseBody
    public ResponseEntity<?> createStyle(Styles style,  EmployeeDTO employeeDTO,@RequestParam("styleIMG") MultipartFile styleImg){

        style.setImageUrl(priseDeRendezVousServices.uploadFile(styleImg));
        return ResponseEntity.ok(priseDeRendezVousServices.createStyle(style,employeeDTO));

    }
}
