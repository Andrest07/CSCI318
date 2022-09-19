package com.remotegroup.procurement;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    
    @KafkaListener(
        topics = "remotegroup",
        groupId = "groupId")
    void listener(String data) {
        System.out.println("Back order received:");
        System.out.println(data);
    }
}
