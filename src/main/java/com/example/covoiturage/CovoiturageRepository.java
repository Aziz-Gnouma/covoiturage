package com.example.covoiturage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {

    List<Covoiturage> findByDepartAndDestinationAndDate(String depart, String destination, String date);
    List<Covoiturage> findByDriver(Long driver);
}