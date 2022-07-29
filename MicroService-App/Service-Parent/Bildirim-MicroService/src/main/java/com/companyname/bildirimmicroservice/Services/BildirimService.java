package com.companyname.bildirimmicroservice.Services;


import com.companyname.bildirimmicroservice.entities.Bildirim;
import com.companyname.bildirimmicroservice.repositories.BildirimRepository;
import messaging.TalimatBildirim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@EnableBinding(Sink.class)
@Service
public class BildirimService {

    @Autowired
    private BildirimRepository bildirimRepository;

    @StreamListener(Sink.INPUT)
    public void onBildirim(TalimatBildirim talimatBildirim){
        System.out.println("________________________________________________");
        System.out.println("talimat alındı kullanıcılara gönderiliyor");
        System.out.println("talimat+hesap -> " + talimatBildirim.toString());
        // sonra mapper ekleyebilirsin
        Bildirim b = new Bildirim();
        b.setBildirimNo(talimatBildirim.getTalimatTanimi());
        b.setAdSoyad("MTUSTA RAABBIITTT TEST");
        createBildirim(b);
        /*mail atabilirsin sms gonderebilirsin ff*/
    }

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
