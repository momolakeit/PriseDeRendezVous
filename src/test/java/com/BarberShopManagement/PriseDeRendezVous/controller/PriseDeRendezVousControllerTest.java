package com.BarberShopManagement.PriseDeRendezVous.controller;

import com.BarberShopManagement.PriseDeRendezVous.PriseDeRendezVousApplication;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = PriseDeRendezVousApplication.class)
@ActiveProfiles("test")
class PriseDeRendezVousControllerTest {
    @LocalServerPort
    private int port;



    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    ServletContext context;

    @Autowired
    private PriseDeRendezVousController priseDeRendezVousController;



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
    @Test
    public void createStylesTestRetourneBonStyle() throws Exception {
        MockMvc mvc  =MockMvcBuilders.standaloneSetup(priseDeRendezVousController).build();
        JSONObject employeDto= new JSONObject();
        employeDto.put("prenom","momo");
        employeDto.put("email","barber@email.com");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "img.png",
                "image/jpg", "Spring Framework".getBytes());
        MockMultipartFile employee = new MockMultipartFile("employeeDto", "",
                "application/json", employeDto.toString().getBytes());
        MvcResult result=mvc.perform(multipart("/createStyle").file(multipartFile).file(employee))
                .andExpect(status().isOk())
                .andReturn();
        Path currentRelativePath = Paths.get("");
        String absolutePath = currentRelativePath.toAbsolutePath().toString();
        JSONObject returnValue = new JSONObject(result.getResponse().getContentAsString());
        File file =new File(absolutePath+returnValue.get("imageUrl").toString());

        assertTrue("barber@email.com".contentEquals(returnValue.get("barberEmail").toString()));
        assertTrue(file.exists());
    }

}