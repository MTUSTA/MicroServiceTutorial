package com.companyname.hesapmicroservice.controllers;

import com.companyname.hesapmicroservice.RequestDTO.HesapRequestDto;
import com.companyname.hesapmicroservice.ResponseDTO.HesapResponseDto;
import com.companyname.hesapmicroservice.Services.HesapService;
import com.companyname.hesapmicroservice.entities.Hesap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hesap")
public class HesapController {
    @Autowired
    private HesapService hesapService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<HesapResponseDto>> TumHesaplariAl() {
        // bazi örneklerde pageable kullanilmis -> daha cok esneklik sagliyormus
        List<HesapResponseDto> hesapResponseDtoList = hesapService.getAllHesap().stream().map(hesap -> modelMapper.map(hesap, HesapResponseDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(hesapResponseDtoList);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<HesapResponseDto> HesapOlustur(@RequestBody HesapRequestDto h) {
        Hesap h1 = modelMapper.map(h, Hesap.class);
        Hesap h2 = hesapService.createHesap(h1);
        HesapResponseDto hesapResponseDto = modelMapper.map(h2, HesapResponseDto.class);
        return new ResponseEntity<HesapResponseDto>(hesapResponseDto, HttpStatus.CREATED);//ResponseEntity.ok(hesapResponseDto);
    }

    @GetMapping("/{hesapId}")
    public ResponseEntity<HesapResponseDto> getOneHesap(@PathVariable Long hesapId) {
        Hesap h = hesapService.getOneHesap(hesapId);
        HesapResponseDto hesapResponseDto = null;
        if (h != null) {
            hesapResponseDto = modelMapper.map(h, HesapResponseDto.class);
            return ResponseEntity.ok(hesapResponseDto);
        }
        hesapResponseDto = new HesapResponseDto();
        hesapResponseDto.setError("HESAP BULUNAMADI");
        return ResponseEntity.ok(hesapResponseDto);
    }

    @PutMapping("/{hesapId}")
    @Transactional
    public ResponseEntity<HesapResponseDto> updateOneHesap(@PathVariable Long hesapId, @RequestBody HesapRequestDto newHesap) {
        Hesap h = modelMapper.map(newHesap, Hesap.class);
        Hesap h1 = hesapService.birHesabiGuncelle(hesapId, h);
        HesapResponseDto hesapResponseDto = modelMapper.map(h1, HesapResponseDto.class);
        return ResponseEntity.ok(hesapResponseDto);
    }

    @DeleteMapping("/{hesapId}")
    @Transactional
    public ResponseEntity<String> deleteOneHesap(@PathVariable Long hesapId) {
        return new ResponseEntity<String>(hesapService.birHesabiSil(hesapId), HttpStatus.OK);
    }


}
