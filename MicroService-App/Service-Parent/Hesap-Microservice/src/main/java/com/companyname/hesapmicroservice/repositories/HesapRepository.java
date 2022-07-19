package com.companyname.hesapmicroservice.repositories;

import com.companyname.hesapmicroservice.entities.Hesap;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
// When using spring data jpa you don't need to specify repository explicitly.
// The spring data jpa scans for all interfaces extending repository and generate implementation automatically.
public interface HesapRepository extends JpaRepository<Hesap, Long> {

}
