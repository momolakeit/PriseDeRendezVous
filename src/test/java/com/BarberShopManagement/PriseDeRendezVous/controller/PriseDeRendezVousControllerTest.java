package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.PriseDeRendezVousApplication;
import static org.junit.jupiter.api.Assertions.*;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = PriseDeRendezVousApplication.class)
@ActiveProfiles("test")
class PriseDeRendezVousControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createPrendreRendezVousDoitRetournerRendezVousAvecVal() throws JSONException, JsonProcessingException {
        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request;
        JSONObject jsonObject = new JSONObject();
        JSONObject employee = new JSONObject();
        employee.put("prenom","yio");
        employee.put("email","email@gmail.com");
        employee.put("password","yio");
        employee.put("role","employee");
        employee.put("jwt","erewr");

        jsonObject.put("employeeDTO",employee.toString());
        JSONObject client = new JSONObject();
        client.put("prenom","rrr");
        client.put("email","email2@gmail.com");
        client.put("password","yio2222");
        client.put("role","client");
        client.put("jwt","dsadasd");

        jsonObject.put("clientDTO",client.toString());

        JSONObject style = new JSONObject();

        style.put("id","2");
        style.put("imageUrl","url");
        style.put("barberEmail","barber@gmail.com");
        style.put("rendezVous","null");
        jsonObject.put("style",style.toString());

        request= new HttpEntity<String>(jsonObject.toString(),httpHeaders);

        ResponseEntity<String> responseEntity = this.testRestTemplate.exchange("http://localhost:" +
                        port +
                        "/prendreRendezVous", HttpMethod.POST,
                request,
                new ParameterizedTypeReference<String>() {
                });
        RendezVousDto rendezVousDto = new ObjectMapper().readValue(responseEntity.getBody(), RendezVousDto.class);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(rendezVousDto.getBarberEmail(),"email@gmail.com");
        assertEquals(rendezVousDto.getClientEmail(),"email2@gmail.com");
    }

}