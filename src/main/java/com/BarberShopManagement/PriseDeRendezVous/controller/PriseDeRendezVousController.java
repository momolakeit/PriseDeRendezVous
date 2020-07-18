package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.StylesDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.services.PriseDeRendezVousServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
    @RequestMapping(path = "/createStyle")
    @ResponseBody
    public ResponseEntity<?> createStyle(Styles style,  EmployeeDTO employeeDTO,@RequestParam("styleIMG") MultipartFile styleImg){

        style.setImageUrl(priseDeRendezVousServices.uploadFile(styleImg));
        return ResponseEntity.ok(priseDeRendezVousServices.createStyle(style,employeeDTO));

    }
}
