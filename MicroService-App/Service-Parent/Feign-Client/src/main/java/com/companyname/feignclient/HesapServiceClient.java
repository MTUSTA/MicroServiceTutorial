package com.companyname.feignclient;



import com.companyname.hesapmicroservice.ResponseDTO.HesapResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("Hesap-Service")// Hesap-Service gidecek iletisim kuracak
public interface HesapServiceClient {

    @RequestMapping("/hesap/{feignId}")
    ResponseEntity<HesapResponseDto> getOneHesap(@PathVariable Long feignId);
}
