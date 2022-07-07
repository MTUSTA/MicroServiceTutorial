package com.companyname.bildirimmicroservice.repositories;

import com.companyname.bildirimmicroservice.entities.Bildirim;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BildirimRepository extends JpaRepository<Bildirim, Long> {

}
