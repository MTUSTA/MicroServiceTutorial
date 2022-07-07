package com.companyname.hesapmicroservice.repositories;

import com.companyname.hesapmicroservice.entities.Hesap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HesapRepository extends JpaRepository<Hesap, Long> {

}
