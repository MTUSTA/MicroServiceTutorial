package com.companyname.bildirimmicroservice.controllers;



import com.companyname.bildirimmicroservice.RequestDTO.BildirimRequestDto;
import com.companyname.bildirimmicroservice.ResponseDTO.BildirimResponseDto;
import com.companyname.bildirimmicroservice.Services.BildirimService;
import com.companyname.bildirimmicroservice.entities.Bildirim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bildirim")
public class BildirimController {
    @Autowired
    private BildirimService bildirimService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<BildirimResponseDto>> TumBildirimlariAl() {
        // bazi örneklerde pageable kullanilmis -> daha cok esneklik sagliyormus
        List<BildirimResponseDto> BildirimResponseDtoList = bildirimService.getAllBildirim().stream().map(Bildirim -> modelMapper.map(Bildirim, BildirimResponseDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(BildirimResponseDtoList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<BildirimResponseDto> BildirimOlustur(@RequestBody BildirimRequestDto h) {
        Bildirim h1 = modelMapper.map(h, Bildirim.class);
        Bildirim h2 = bildirimService.createBildirim(h1);
        BildirimResponseDto BildirimResponseDto = modelMapper.map(h2, BildirimResponseDto.class);
        return new ResponseEntity<BildirimResponseDto>(BildirimResponseDto, HttpStatus.CREATED);//ResponseEntity.ok(BildirimResponseDto);
    }

    @GetMapping("/{bildirimId}")
    public ResponseEntity<BildirimResponseDto> getOneBildirim(@PathVariable Long BildirimId) {
        Bildirim h = bildirimService.getOneBildirim(BildirimId);
        BildirimResponseDto BildirimResponseDto = null;
        if (h != null) {
            BildirimResponseDto = modelMapper.map(h, BildirimResponseDto.class);
            return ResponseEntity.ok(BildirimResponseDto);
        }
        BildirimResponseDto = new BildirimResponseDto();
        BildirimResponseDto.setError("BILDIRIM BULUNAMADI");
        return ResponseEntity.ok(BildirimResponseDto);
    }

    @PutMapping("/{bildirimId}")
    @Transactional
    public ResponseEntity<BildirimResponseDto> updateOneBildirim(@PathVariable Long BildirimId, @RequestBody BildirimRequestDto newBildirim) {
        Bildirim h = modelMapper.map(newBildirim, Bildirim.class);
        Bildirim h1 = bildirimService.birBildirimiGuncelle(BildirimId, h);
        BildirimResponseDto BildirimResponseDto = modelMapper.map(h1, BildirimResponseDto.class);
        return ResponseEntity.ok(BildirimResponseDto);
    }

    @DeleteMapping("/{bildirimId}")
    @Transactional
    public ResponseEntity<String> deleteOneBildirim(@PathVariable Long BildirimId) {
        return new ResponseEntity<String>(bildirimService.birTalimatiSil(BildirimId), HttpStatus.OK);
    }


}
