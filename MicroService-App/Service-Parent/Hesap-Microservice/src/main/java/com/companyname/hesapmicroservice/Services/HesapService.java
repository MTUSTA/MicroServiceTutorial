package com.companyname.hesapmicroservice.Services;

import com.companyname.hesapmicroservice.RequestDTO.HesapRequestDto;
import com.companyname.hesapmicroservice.entities.Hesap;
import com.companyname.hesapmicroservice.repositories.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
            try {
                Hesap h1 = h.get();
                h1.setHesapNo(newHesap.getHesapNo());
                h1.setAdSoyad(newHesap.getAdSoyad());
                hesapRepository.save(h1);
                return h1;
            }
            catch (Exception E){
                return null;
            }
        }
        throw new EmptyResultDataAccessException("hesap bulunamadi",1);
    }

    public String birHesabiSil(@PathVariable Long hesapId) {
        try {
            hesapRepository.deleteById(hesapId);
            return "Succes";
        }catch (EmptyResultDataAccessException E){
            throw new EmptyResultDataAccessException("hesap bulunamadi",1);
        }
        catch (Exception e) {
            return "DB de hata var: "+ e.toString();
        }
    }
}
