package com.companyname.bildirimmicroservice.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="bildirim")
@Data
public class Bildirim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name="bildirim_no")
    private String bildirimNo;
    @Column(nullable = false, name="ad_soyad")
    private String AdSoyad;
}
