package com.companyname.talimatmicroservice.Services;

import com.companyname.feignclient.HesapServiceClient;
import com.companyname.hesapmicroservice.ResponseDTO.HesapResponseDto;
import com.companyname.talimatmicroservice.entities.Talimat;
import com.companyname.talimatmicroservice.repositories.TalimatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TalimatService {

    @Autowired
    private TalimatRepository talimatRepository;
    @Autowired
    private HesapServiceClient hesapServiceClient;

    public List<Talimat> getAllTalimat() {
        return talimatRepository.findAll();
    }

    public Talimat createTalimat(Talimat h) {
        // talimat olusturulmadan önce hesap var mı kontrol edelim
        ResponseEntity<HesapResponseDto> res = hesapServiceClient.getOneHesap(h.getHesapId());
        if (res.getBody().getError().equals("")){
            return talimatRepository.save(h);
        }

        return null;
    }

    public Talimat getOneTalimat(Long talimatId) {
        return talimatRepository.findById(talimatId).orElse(null);
    }

    public Talimat birTalimatiGuncelle(Long talimatId, Talimat newTalimat) {
        Optional<Talimat> h = talimatRepository.findById(talimatId);
        if (h.isPresent()) {
            try {
                Talimat h1 = h.get();
                h1.setTalimatNo(newTalimat.getTalimatNo());
                h1.setAdSoyad(newTalimat.getAdSoyad());
                talimatRepository.save(h1);
                return h1;
            }
            catch (Exception E){
                return null;
            }
        }
        throw new EmptyResultDataAccessException("talimat bulunamadi",1);
    }

    public String birTalimatiSil(@PathVariable Long talimatId) {
        try {
            talimatRepository.deleteById(talimatId);
            return "Succes";
        }catch (EmptyResultDataAccessException E){
            throw new EmptyResultDataAccessException("talimat bulunamadi",1);
        }
        catch (Exception e) {
            return "DB de hata var: "+ e.toString();
        }
    }
}
