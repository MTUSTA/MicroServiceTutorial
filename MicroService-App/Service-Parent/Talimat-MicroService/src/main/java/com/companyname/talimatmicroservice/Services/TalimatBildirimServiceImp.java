package com.companyname.talimatmicroservice.Services;

import com.companyname.talimatmicroservice.entities.Talimat;

import messaging.TalimatBildirim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class TalimatBildirimServiceImp implements TalimatBildirimService {

    @Autowired
    private Source source;

    @Override
    public void sendToQueue(Talimat talimat) {

        TalimatBildirim talimatBildirim = new TalimatBildirim();

        talimatBildirim.setTalimatId(talimat.getTalimatNo());
        talimatBildirim.setHesapId(talimat.getHesapId().toString());
        talimatBildirim.setTalimatTanimi("DENEME RABBITMQ");
        System.out.println("gonderilmeden once");
        source.output().send(MessageBuilder.withPayload(talimatBildirim).build());
        System.out.println("gonderildi");
    }
}
