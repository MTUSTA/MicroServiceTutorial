package com.companyname.gatewayserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class HystrixController {
    // hesap-service erişemiyorsa bu metod cagirilacak
    @GetMapping("/hesap")
    public String hesapFallback(){
        return "Hesap Service is not available.";
    }

    @GetMapping("/talimat")
    public String talimatFallback(){
        return "Talimat Service is not available.";
    }
}