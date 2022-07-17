package com.companyname.talimatmicroservice.ResponseDTO;

import lombok.Data;

@Data
public class TalimatResponseDto {
    // hata durumlarinda buranin doldurulmasi
    private String Error = "";

    private String id = "";
    private String talimatNo = "";
    private String AdSoyad = "";
    private Long hesapId;

}
