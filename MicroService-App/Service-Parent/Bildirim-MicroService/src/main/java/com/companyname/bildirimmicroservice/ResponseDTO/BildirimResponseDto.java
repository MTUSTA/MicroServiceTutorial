package com.companyname.bildirimmicroservice.ResponseDTO;

import lombok.Data;

@Data
public class BildirimResponseDto {
    // hata durumlarinda buranin doldurulmasi
    private String Error = "";

    private String id = "";
    private String bildirimNo = "";
    private String AdSoyad = "";

}
