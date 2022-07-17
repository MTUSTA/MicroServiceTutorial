package com.companyname.talimatmicroservice.controllers;

import com.companyname.talimatmicroservice.RequestDTO.TalimatRequestDto;
import com.companyname.talimatmicroservice.ResponseDTO.TalimatResponseDto;
import com.companyname.talimatmicroservice.Services.TalimatService;
import com.companyname.talimatmicroservice.entities.Talimat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/talimat")
public class TalimatController {
    @Autowired
    private TalimatService talimatService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TalimatResponseDto>> TumTalimatlariAl() {
        // bazi örneklerde pageable kullanilmis -> daha cok esneklik sagliyormus
        List<TalimatResponseDto> talimatResponseDtoList = talimatService.getAllTalimat().stream().map(talimat -> modelMapper.map(talimat, TalimatResponseDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(talimatResponseDtoList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TalimatResponseDto> TalimatOlustur(@RequestBody TalimatRequestDto h) {
        Talimat h1 = modelMapper.map(h, Talimat.class);
        Talimat h2 = talimatService.createTalimat(h1);
        TalimatResponseDto talimatResponseDto = null;
        if (h2 == null) {
            talimatResponseDto = new TalimatResponseDto();
            talimatResponseDto.setError("hesap hata");
            return new ResponseEntity<TalimatResponseDto>(talimatResponseDto, HttpStatus.NOT_FOUND);
        }

        talimatResponseDto = modelMapper.map(h2, TalimatResponseDto.class);
        return new ResponseEntity<TalimatResponseDto>(talimatResponseDto, HttpStatus.CREATED);//ResponseEntity.ok(talimatResponseDto);
    }

    @GetMapping("/{talimatId}")
    public ResponseEntity<TalimatResponseDto> getOneTalimat(@PathVariable Long talimatId) {
        Talimat h = talimatService.getOneTalimat(talimatId);
        TalimatResponseDto talimatResponseDto = null;
        if (h != null) {
            talimatResponseDto = modelMapper.map(h, TalimatResponseDto.class);
            return ResponseEntity.ok(talimatResponseDto);
        }
        talimatResponseDto = new TalimatResponseDto();
        talimatResponseDto.setError("Talimat BULUNAMADI");
        return ResponseEntity.ok(talimatResponseDto);
    }

    @PutMapping("/{talimatId}")
    @Transactional
    public ResponseEntity<TalimatResponseDto> updateOneTalimat(@PathVariable Long talimatId, @RequestBody TalimatRequestDto newTalimat) {
        Talimat h = modelMapper.map(newTalimat, Talimat.class);
        Talimat h1 = talimatService.birTalimatiGuncelle(talimatId, h);
        TalimatResponseDto talimatResponseDto = modelMapper.map(h1, TalimatResponseDto.class);
        return ResponseEntity.ok(talimatResponseDto);
    }

    @DeleteMapping("/{talimatId}")
    @Transactional
    public ResponseEntity<String> deleteOneTalimat(@PathVariable Long talimatId) {
        return new ResponseEntity<String>(talimatService.birTalimatiSil(talimatId), HttpStatus.OK);
    }


}
