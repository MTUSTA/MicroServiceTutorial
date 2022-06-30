package com.companyname.hesapmicroservice.controllers;

import com.companyname.hesapmicroservice.Services.HesapService;
import com.companyname.hesapmicroservice.entities.Hesap;
import com.companyname.hesapmicroservice.repositories.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hesap")
public class HesapController {
    @Autowired
    private HesapService hesapService;

    @GetMapping
    public List<Hesap> TumHesaplariAl() {
        return hesapService.getAllHesap();
    }

    @PostMapping
    public Hesap HesapOlustur(@RequestBody Hesap h) {
        return hesapService.createHesap(h);
    }

    @GetMapping("/{hesapId}")
    public Hesap getOneHesap(@PathVariable Long hesapId) {
        return hesapService.getOneHesap(hesapId);
    }

    @PutMapping("/{hesapId}")
    public Hesap updateOneHesap(@PathVariable Long hesapId, @RequestBody Hesap newHesap) {
        return hesapService.birHesabiGuncelle(hesapId, newHesap);
    }

    @DeleteMapping("/{hesapId}")
    public String deleteOneHesap(@PathVariable Long hesapId) {
        return hesapService.birHesabiSil(hesapId);
    }
}
