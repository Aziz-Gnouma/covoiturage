package com.example.covoiturage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
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



    @Autowired
    private EmailSenderService senderService;


    @PostMapping("/SendEmail")
    public ResponseEntity<String> triggerMail(
            @RequestParam String email,
            @RequestParam Long idCovoiturage,
            @RequestParam String nameClient) {
        try {
            // Fetch additional information based on idCovoiturage
            Optional<Covoiturage> optionalCovoiturage = CovoiturageRepository.findById(idCovoiturage);

            // Check if the covoiturageInfo is present
            if (optionalCovoiturage.isPresent()) {
                Covoiturage covoiturageInfo = optionalCovoiturage.get();

                String candidateEmail = email;
                senderService.sendSimpleEmail(candidateEmail,
                        "Confirmation d'acceptation de votre réservation",
                        "Bonjour " + nameClient + ",\n\n" +
                                "J'espère que cette lettre vous trouve bien.\n" +
                                "Nous sommes ravis de vous informer que votre réservation pour le prochain trajet en covoiturage a été acceptée.\n"+
                                " Votre demande a été confirmée et nous avons hâte de partager cette balade avec vous. Votre participation est très appréciée, et nous pensons que votre présence contribuera positivement à l'expérience du voyage.\n" +
                                "\n\n" +
                                "Voici quelques détails importants pour votre référence :\n" +
                                "Départ : " + covoiturageInfo.getDepart() + "\n" +
                                "Destination : " + covoiturageInfo.getDestination() + "\n" +
                                "Date : " + covoiturageInfo.getDate() + "\n" +
                                "*Veuillez vous assurer d'arriver à l'heure au lieu de départ et, en cas de changement dans vos projets, veuillez nous en informer à l'avance. Nous nous efforçons d'offrir une expérience de covoiturage confortable et agréable à tous les participants.\n" +
                                "\n" +
                                 "Merci d'avoir choisi de faire du covoiturage avec nous. Si vous avez d'autres questions ou avez besoin d'informations supplémentaires, n'hésitez pas à contacter notre équipe d'assistance client."+
                                "\n\n" +
                                "Bon voyage,\n" +
                                "DevGenuis Teams");

                // Assuming "dash" is the view name to be displayed after the acceptance process
                return ResponseEntity.ok("Email sent successfully");
            } else {
                // Handle the case where Covoiturage with the given idCovoiturage is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Covoiturage not found for id: " + idCovoiturage);
            }
        } catch (Exception e) {
            // Log the exception or return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }



}