package com.companyname.bildirimmicroservice.Services;


import com.companyname.bildirimmicroservice.entities.Bildirim;
import com.companyname.bildirimmicroservice.repositories.BildirimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Service
public class BildirimService {

    @Autowired
    private BildirimRepository bildirimRepository;

    public List<Bildirim> getAllBildirim() {
        return bildirimRepository.findAll();
    }

    public Bildirim createBildirim(Bildirim b) {
        return bildirimRepository.save(b);
    }

    public Bildirim getOneBildirim(Long BildirimId) {
        return bildirimRepository.findById(BildirimId).orElse(null);
    }

    public Bildirim birBildirimiGuncelle(Long BildirimId, Bildirim newBildirim) {
        Optional<Bildirim> b = bildirimRepository.findById(BildirimId);
        if (b.isPresent()) {
            try {
                Bildirim b1 = b.get();
                b1.setBildirimNo(newBildirim.getBildirimNo());
                b1.setAdSoyad(newBildirim.getAdSoyad());
                bildirimRepository.save(b1);
                return b1;
            }
            catch (Exception E){
                return null;
            }
        }
        throw new EmptyResultDataAccessException("Bildirim bulunamadi",1);
    }

    public String birBildirimiSil(@PathVariable Long BildirimId) {
        try {
            bildirimRepository.deleteById(BildirimId);
            return "Succes";
        }catch (EmptyResultDataAccessException E){
            throw new EmptyResultDataAccessException("Bildirim bulunamadi",1);
        }
        catch (Exception e) {
            return "DB de hata var: "+ e.toString();
        }
    }
}
