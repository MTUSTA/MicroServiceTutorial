package com.companyname.hesapmicroservice.Services;

import com.companyname.hesapmicroservice.entities.Hesap;
import com.companyname.hesapmicroservice.repositories.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class HesapService {

    @Autowired
    private HesapRepository hesapRepository;

    public List<Hesap> getAllHesap() {
        return hesapRepository.findAll();
    }

    public Hesap createHesap(Hesap h) {
        return hesapRepository.save(h);
    }

    public Hesap getOneHesap(Long hesapId) {
        return hesapRepository.findById(hesapId).orElse(null);
    }

    public Hesap birHesabiGuncelle(Long hesapId, Hesap newHesap) {
        Optional<Hesap> h = hesapRepository.findById(hesapId);
        if (h.isPresent()) {
            Hesap h1 = h.get();
            h1.setHesapNo(newHesap.getHesapNo());
            h1.setAdSoyad(newHesap.getAdSoyad());
            hesapRepository.save(h1);
            return h1;
        }
        return null;
    }

    public String birHesabiSil(@PathVariable Long hesapId) {
        try {
            hesapRepository.deleteById(hesapId);
            return "Succes";
        } catch (Exception e) {

        }
        return "FAIL";
    }
}
