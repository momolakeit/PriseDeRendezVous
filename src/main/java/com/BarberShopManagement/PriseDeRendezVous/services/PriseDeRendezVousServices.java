package com.BarberShopManagement.PriseDeRendezVous.services;

import com.BarberShopManagement.PriseDeRendezVous.models.dto.ClientDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.EmployeeDTO;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.RendezVousDto;
import com.BarberShopManagement.PriseDeRendezVous.models.dto.StylesDto;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.RendezVous;
import com.BarberShopManagement.PriseDeRendezVous.models.entities.Styles;
import com.BarberShopManagement.PriseDeRendezVous.models.mapping.MapperInterface;
import com.BarberShopManagement.PriseDeRendezVous.models.mapping.RendezvVousToRendezVousdDTOMapper;
import com.BarberShopManagement.PriseDeRendezVous.models.mapping.StylesToStylesDTOMapper;
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



    public RendezVousDto createRendezVous(Long styleId, Date date, String employeeDTOEmail, String clientDTOemail){
        List<Styles> stylesList =new ArrayList<>();
         stylesRepository.findAll().forEach(stylesList::add);
         Styles styles= stylesRepository.findById(styleId).get();
         RendezVousDto rendezVousDto =new RendezVousDto();
         RendezVous rendezVous=new RendezVous();
         rendezVous.setBarberEmail(employeeDTOEmail);
         rendezVous.setClientEmail(clientDTOemail);
         rendezVous.setDateRendezVous(date);
         rendezVous.setStyles(addItem(new ArrayList<>(),styles));
         if(validateRendezVousPourEmploye(employeeDTOEmail,rendezVous)){
             rendezVous =rendezVousRepository.save(rendezVous);
             rendezVousDto= RendezvVousToRendezVousdDTOMapper.instance.convert(rendezVous);
             rendezVousDto.setStyles(convertItem(new ArrayList<>(),
                                         StylesToStylesDTOMapper.instance,
                                          rendezVous.getStyles()));
             return rendezVousDto;

         }

         return null;
    }

    private boolean validateRendezVousPourEmploye(String barberEmail,RendezVous rendezVous){

        List<RendezVous> allRendezVous= rendezVousRepository.findByBarberEmail(barberEmail);
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

    public StylesDto createStyle (Styles styles,EmployeeDTO employeeDTO){
        styles.setBarberEmail(employeeDTO.getEmail());
        styles=stylesRepository.save(styles);
        StylesDto stylesDto =new StylesDto();
        stylesDto = StylesToStylesDTOMapper.instance.convert(styles);
        stylesDto.setRendezVous(convertItem(new ArrayList<>(),
                                            StylesToStylesDTOMapper.instance,
                                            styles.getRendezVous()));
        return stylesDto;
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
    private <T,Q> List<T> convertItem(List<T> arrayList, MapperInterface converter, List<Q> objToConvert){
        if(Objects.nonNull(objToConvert)){
            for(Q x:objToConvert){
                arrayList.add((T)converter.convert(x));
            }
        }
        return arrayList;
    }
    private <T> List<T> addItem(List<T> list,T item){
        list.add(item);
        return list;
    }
}
