package com.companyname.talimatmicroservice.Services;

import com.companyname.talimatmicroservice.entities.Talimat;
import com.companyname.talimatmicroservice.repositories.TalimatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TalimatService {

    @Autowired
    private TalimatRepository talimatRepository;

    public List<Talimat> getAllTalimat() {
        return talimatRepository.findAll();
    }

    public Talimat createTalimat(Talimat h) {
        return talimatRepository.save(h);
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
