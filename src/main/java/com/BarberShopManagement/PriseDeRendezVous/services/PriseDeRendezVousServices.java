package com.BarberShopManagement.PriseDeRendezVous.services;

import com.BarberShopManagement.PriseDeRendezVous.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.repositories.RendezVousRepository;
import com.BarberShopManagement.PriseDeRendezVous.repositories.StylesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PriseDeRendezVousServices {

    private RendezVousRepository rendezVousRepository;

    @Value("${config.styles.images.path}")
    private String fileBasePath;

    private StylesRepository stylesRepository;
    @Autowired
    public PriseDeRendezVousServices(RendezVousRepository rendezVousRepository, StylesRepository stylesRepositoryRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.stylesRepository = stylesRepositoryRepository;
    }



    public RendezVous createRendezVous(Long styleId, Date date, EmployeeDTO employeeDTO, ClientDTO clientDTO){
         Styles styles= stylesRepository.findById(styleId).get();
         RendezVous rendezVous=new RendezVous();
         rendezVous.setBarberEmail(employeeDTO.getEmail());
         rendezVous.setClientEmail(clientDTO.getEmail());
         rendezVous.setDateRendezVous(date);
         if(validateRendezVousPourEmploye(employeeDTO,rendezVous)){
             return rendezVousRepository.save(rendezVous);

         }

         return null;
    }

    private boolean validateRendezVousPourEmploye(EmployeeDTO employeeDTO,RendezVous rendezVous){
        List<RendezVous> allRendezVous= rendezVousRepository.findByBarberEmail(employeeDTO.getEmail());
        allRendezVous.add(rendezVous);
        LinkedList<RendezVous> list= allRendezVous.stream().
                sorted(Comparator.comparing(RendezVous::getDateRendezVous)).
                collect(Collectors.toCollection(LinkedList::new));



        int objectIndex=list.indexOf(rendezVous);
        ListIterator<RendezVous> rendezVousListIterator =list.listIterator(objectIndex);
        boolean returnVal =true;
        if(rendezVousListIterator.hasPrevious()){
           returnVal= timeToMinutes(rendezVousListIterator.previous().getDateRendezVous().getTime())+15<=
                    timeToMinutes(rendezVous.getDateRendezVous().getTime());
        }
        rendezVousListIterator =list.listIterator(objectIndex+1);
        if(rendezVousListIterator.hasNext()){
           return returnVal && timeToMinutes(rendezVous.getDateRendezVous().getTime())+15<=
                    timeToMinutes(rendezVousListIterator.next().getDateRendezVous().getTime());

        }

    return returnVal;
    }

    public Styles createStyle (Styles styles,EmployeeDTO employeeDTO){
        styles.setBarberEmail(employeeDTO.getEmail());
        return stylesRepository.save(styles);
    }

    private Long timeToMinutes(Long time){
        return time /1000/60;
    }
    public String uploadFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(UUID.randomUUID().toString());
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
