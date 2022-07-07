package com.companyname.hesapmicroservice.ResponseDTO;

import lombok.Data;

@Data
public class HesapResponseDto {
    // hata durumlarinda buranin doldurulmasi
    private String Error = "";

    private String id = "";
    private String hesapNo = "";
    private String AdSoyad = "";

}
