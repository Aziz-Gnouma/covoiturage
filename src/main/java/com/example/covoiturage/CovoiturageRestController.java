package com.example.covoiturage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RefreshScope
@RestController
@RequestMapping("/driver")
public class CovoiturageRestController {



    @Autowired
    private CovoiturageRepository CovoiturageRepository;

    @GetMapping("/count")
    public Long getAllcom() {
        Long x = CovoiturageRepository.count();
        return x;
    }
    @GetMapping("/covoiturages")
    public List<Covoiturage> getAllcovs() {
        return CovoiturageRepository.findAll();

    }
    @PostMapping("/covoiturages")
    public Covoiturage create(@RequestBody Covoiturage Covoiturage) {
        return CovoiturageRepository.save(Covoiturage);
    }



    @DeleteMapping("/covoiturages/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduit(@PathVariable Long id){
        Covoiturage Covoiturage = CovoiturageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Covoiturage not exist with id :" + id));

        CovoiturageRepository.delete(Covoiturage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/covs/{depart}")
    public List<Covoiturage> getByName(@PathVariable String depart)
    {
        return CovoiturageRepository.findByDepart(depart);
    }

    @GetMapping("/covDriver/{idriver}")
    public List<Covoiturage> getByDriver(@PathVariable Long idriver) {
        return CovoiturageRepository.findByDriver(idriver);
    }



    @GetMapping("/covoiturages/{id}")
    public Covoiturage getById(@PathVariable Long id) {
        return CovoiturageRepository.findById(id).orElse(null);
    }



    @PutMapping("/covoiturages/{id}")
    public ResponseEntity<Covoiturage> updateCovoiturage(@PathVariable Long id, @RequestBody Covoiturage updatedCovoiturage) {
        Optional<Covoiturage> existingCovoiturageOptional = CovoiturageRepository.findById(id);

        if (!existingCovoiturageOptional.isPresent()) {
            throw new ResourceNotFoundException("Covoiturage not exist with id: " + id);
        }

        Covoiturage existingCovoiturage = existingCovoiturageOptional.get();

        existingCovoiturage.setPlace(updatedCovoiturage.getPlace());
        existingCovoiturage.setPrice(updatedCovoiturage.getPrice());
        existingCovoiturage.setDescription(updatedCovoiturage.getDescription());
        existingCovoiturage.setPhone(updatedCovoiturage.getPhone());
        existingCovoiturage.setBagage(updatedCovoiturage.getBagage());
        existingCovoiturage.setDate(updatedCovoiturage.getDate());
        existingCovoiturage.setDepart(updatedCovoiturage.getDepart());
        existingCovoiturage.setDestination(updatedCovoiturage.getDestination());
        existingCovoiturage.setmarque(updatedCovoiturage.getmarque());


        // Save the updated Covoiturage to the repository
        Covoiturage updatedCovoiturageEntity = CovoiturageRepository.save(existingCovoiturage);

        return ResponseEntity.ok(updatedCovoiturageEntity);
    }

}