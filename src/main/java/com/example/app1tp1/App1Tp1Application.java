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

            //************* Ajouter des Patients  ****************************
            irepositories.save(new Patient(null, "Othmane", new Date(), false, 120));
            irepositories.save(new Patient(null, "Youness", new Date(), true, 220));
            irepositories.save(new Patient(null, "Ismail", new Date(), false, 320));
            irepositories.save(new Patient(null, "Simo", new Date(), true, 420));


            //************* Afficher tous Patients ****************************
            List<Patient> patients = irepositories.findAll();
            patients.forEach(p  -> {
                System.out.println(p.getId());
                System.out.println(p.getNom());
                System.out.println(p.getDateNaissance());
                System.out.println(p.isMalade());
                System.out.println(p.getScore());
            });

            //************* FInd Patient By Id ****************************
            Patient patient = irepositories.findById(1L).orElse(null);

            //************ Update Patient Name ***************************
            patient.setNom("Darhoni");
            irepositories.save(patient);

            //*********** Chercher Patient ********************************
            Patient patient2 = irepositories.findByNom("Youness");
            System.out.println(patient2.getScore());

            //*********** Delete Patient ********************************
            irepositories.deleteById(1L);
    }
}
