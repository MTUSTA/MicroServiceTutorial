package com.companyname.hesapmicroservice.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="hesap")
@Data
public class Hesap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="hesap_no")
    private String hesapNo;
    @Column(nullable = false, name="ad_soyad")
    private String AdSoyad;
}
