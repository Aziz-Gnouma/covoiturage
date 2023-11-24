package com.example.covoiturage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "RES_SERVICE", url = "localhost:3002")
public interface repoUser {


}


