package com.companyname.hesapmicroservice.controllers;

import com.companyname.hesapmicroservice.Services.HesapService;
import com.companyname.hesapmicroservice.entities.Hesap;
import com.companyname.hesapmicroservice.repositories.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hesap")
public class HesapController {
    @Autowired
    private HesapService hesapService;

    @GetMapping
    public ResponseEntity<List<Hesap>> TumHesaplariAl() {
        return ResponseEntity.ok(hesapService.getAllHesap());
    }

    @PostMapping
    public ResponseEntity<Hesap> HesapOlustur(@RequestBody Hesap h) {
        return ResponseEntity.ok(hesapService.createHesap(h));
    }

    @GetMapping("/{hesapId}")
    public ResponseEntity<Hesap> getOneHesap(@PathVariable Long hesapId) {
        return ResponseEntity.ok(hesapService.getOneHesap(hesapId));
    }

    @PutMapping("/{hesapId}")
    public ResponseEntity<Hesap> updateOneHesap(@PathVariable Long hesapId, @RequestBody Hesap newHesap) {
        return ResponseEntity.ok(hesapService.birHesabiGuncelle(hesapId, newHesap));
    }

    @DeleteMapping("/{hesapId}")
    public ResponseEntity<String> deleteOneHesap(@PathVariable Long hesapId) {
        return ResponseEntity.ok(hesapService.birHesabiSil(hesapId));
    }
}
