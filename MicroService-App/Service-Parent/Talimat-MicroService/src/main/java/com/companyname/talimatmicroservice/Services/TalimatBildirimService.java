package com.companyname.talimatmicroservice.Services;

import com.companyname.talimatmicroservice.entities.Talimat;

public interface TalimatBildirimService {

    void sendToQueue(Talimat talimat);
}
