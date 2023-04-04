package com.example.app1tp1.repositories;

import com.example.app1tp1.entité.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

public interface Irepositories extends JpaRepository<Patient, Long> {

}
