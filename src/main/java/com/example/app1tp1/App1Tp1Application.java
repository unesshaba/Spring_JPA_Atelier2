package com.example.app1tp1;

import com.example.app1tp1.entité.Patient;
import com.example.app1tp1.repositories.Irepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class App1Tp1Application implements CommandLineRunner {

    @Autowired
    private Irepositories irepositories;
    public static void main(String[] args) {
        SpringApplication.run(App1Tp1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        irepositories.save(new Patient(null, "Othmane", new Date(), false, 30));
        irepositories.save(new Patient(null, "Darhoni", new Date(), false, 50));

        for(int i=0; i<20; i++){
            irepositories.save(new Patient(null, "TestPatient", new Date(), false, 120));
        }

        Page<Patient> patients =  irepositories.findAll(PageRequest.of(1,5));
        System.out.println("Nombre de page : " + patients.getTotalPages());

        List<Patient> content = patients.getContent();
        content.forEach(p -> {
            System.out.println("==============================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());
        });

        System.out.println("*************Get By ID**************");
        Patient patient = irepositories.findById(1L).orElse(null);
        if(patient!=null){
            System.out.println(patient.getNom());
        }

        patient.setScore(10);
        irepositories.save(patient);

    }
}
