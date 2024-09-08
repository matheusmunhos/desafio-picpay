package br.com.munhosdev.picpay.service;

import br.com.munhosdev.picpay.client.NotificationClient;
import br.com.munhosdev.picpay.entity.Transfer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationClient notificationClient;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(Transfer transfer){
        try {
            logger.info("Sending notification");
            var resp = notificationClient.sendNotification(transfer);
            if(resp.getStatusCode().isError()){
                logger.error("Error sending notification, status code is not ok " );
            }
        } catch (Exception e) {
            logger.error("Error sending notification", e);
        }
    }
}
