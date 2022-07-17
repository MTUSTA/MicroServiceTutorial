package com.companyname.talimatmicroservice.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="talimat")
@Data
public class Talimat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="talimat_no")
    private String talimatNo;
    @Column(nullable = false, name="ad_soyad")
    private String AdSoyad;
    @Column(nullable = false, name ="hesap_id")
    private Long hesapId;
}
