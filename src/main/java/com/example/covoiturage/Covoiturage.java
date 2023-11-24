package com.example.covoiturage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Covoiturage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long driver;
    private String depart;
    private String destination;
    private double price;
    private String date;
    private String phone;
    private Integer place;
    private String description;
    private String bagage;
    private String marque;

    public Covoiturage(String depart, String destination, String phone, double price, Integer place, String bagage, String description,  String marque,String date) {
        this.driver = getdriver();
        this.depart = depart;
        this.destination = destination;
        this.phone = phone;
        this.price = price;
        this.place = place;
        this.bagage = bagage;
        this.description = description;
        this.date = date;
        this.marque = marque ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getdriver() {
        return driver;
    }

    public void setdriver(Long driver) {
        this.driver = driver;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public String getBagage() {
        return bagage;
    }

    public void setBagage(String bagage) {
        this.bagage = bagage;
    }

    public String getmarque() {
        return marque;
    }

    public void setmarque(String marque) {
        this.marque = marque;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
