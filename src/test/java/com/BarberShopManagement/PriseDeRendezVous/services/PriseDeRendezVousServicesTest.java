package com.BarberShopManagement.PriseDeRendezVous.services;

import com.BarberShopManagement.PriseDeRendezVous.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.StylesDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.repositories.RendezVousRepository;
import com.BarberShopManagement.PriseDeRendezVous.repositories.StylesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriseDeRendezVousServicesTest {
    @Mock
    private RendezVousRepository rendezVousRepository;
    @Mock
    private StylesRepository stylesRepository;
    private PriseDeRendezVousServices priseDeRendezVousServices;
    @Test
    public void createRendezVousRendezVousMauvaiseDateReturnNull() throws InterruptedException {
        priseDeRendezVousServices=new PriseDeRendezVousServices(rendezVousRepository,
                                                                stylesRepository);
        List<RendezVous> rendezVousList=new ArrayList<>();

        RendezVous rendezVousAvant =new RendezVous();
        rendezVousAvant.setDateRendezVous(new Date(System.currentTimeMillis()));
        rendezVousList.add(rendezVousAvant);
        Thread.sleep(2000L);
        RendezVous rendezVousApres =new RendezVous();
        rendezVousApres.setDateRendezVous(new Date(System.currentTimeMillis()+10000));

        rendezVousList.add(rendezVousApres);
        when(rendezVousRepository.findByBarberEmail(anyString())).thenReturn(rendezVousList);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("employee@gmail.com");
        ClientDTO clientDTO =new ClientDTO();
        clientDTO.setEmail("client@gmail.com");
        Styles styles=new Styles();
        when(stylesRepository.findById(anyLong())).thenReturn(java.util.Optional.of(styles));
        assertNull(priseDeRendezVousServices.createRendezVous(
                  54L,
                        new Date(System.currentTimeMillis()),
                        employeeDTO,clientDTO));


    }
    @Test
    public void createRendezVousRendezVousPremierRendexVousRetourneRendezVous() throws InterruptedException {
        priseDeRendezVousServices=new PriseDeRendezVousServices(rendezVousRepository,
                stylesRepository);
        List<RendezVous> rendezVousList=new ArrayList<>();


        when(rendezVousRepository.findByBarberEmail(anyString())).thenReturn(rendezVousList);
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(new RendezVous());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("employee@gmail.com");
        ClientDTO clientDTO =new ClientDTO();
        clientDTO.setEmail("client@gmail.com");
        Styles styles=new Styles();
        when(stylesRepository.findById(anyLong())).thenReturn(java.util.Optional.of(styles));
        RendezVousDto rendezVousRetour= priseDeRendezVousServices.createRendezVous(
                54L,
                new Date(System.currentTimeMillis()),
                employeeDTO,clientDTO);
        assertNotNull(rendezVousRetour);

    }

    @Test
    public void createRendezVousRendezVousRendezVousToutFitRetourneTrue() throws InterruptedException {
        priseDeRendezVousServices=new PriseDeRendezVousServices(rendezVousRepository,
                stylesRepository);
        List<RendezVous> rendezVousList=new ArrayList<>();

        RendezVous rendezVousAvant =new RendezVous();
        rendezVousAvant.setDateRendezVous(new Date(2020,07,04,10,0,0));
        rendezVousList.add(rendezVousAvant);
        Thread.sleep(2000L);
        RendezVous rendezVousApres =new RendezVous();
        rendezVousApres.setDateRendezVous(new Date(2020,07,04,10,30,0));



        when(rendezVousRepository.findByBarberEmail(anyString())).thenReturn(rendezVousList);
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(new RendezVous());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("employee@gmail.com");
        ClientDTO clientDTO =new ClientDTO();
        clientDTO.setEmail("client@gmail.com");
        Styles styles=new Styles();
        when(stylesRepository.findById(anyLong())).thenReturn(java.util.Optional.of(styles));
        RendezVousDto rendezVousRetour= priseDeRendezVousServices.createRendezVous(
                54L,
                new Date(2020,07,04,10,15,0),
                employeeDTO,clientDTO);
        assertNotNull(rendezVousRetour);

    }

    /*@Test integration tests
    public void uploadStyleTestUploadFileShouldBeOnServer() throws InterruptedException {
        priseDeRendezVousServices=new PriseDeRendezVousServices(rendezVousRepository,
                stylesRepository);
        MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        priseDeRendezVousServices.uploadFile(file);

    }*/
    @Test
    public void createStyleTest() throws InterruptedException {
        priseDeRendezVousServices=new PriseDeRendezVousServices(rendezVousRepository,
                stylesRepository);
        Styles styles = new Styles();
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setEmail("employee@gmail.com");
        when(stylesRepository.save(any())).thenReturn(styles);
        StylesDto reponse =priseDeRendezVousServices.createStyle(styles,employeeDTO);
        assertTrue(reponse.getBarberEmail().contentEquals(employeeDTO.getEmail()));

    }


}