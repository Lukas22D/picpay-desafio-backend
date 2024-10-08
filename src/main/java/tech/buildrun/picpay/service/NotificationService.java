package tech.buildrun.picpay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tech.buildrun.picpay.client.NotificationClient;
import tech.buildrun.picpay.model.Transfer;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer) {
        try {
            LOGGER.info("Sending notification...");

            var response = notificationClient.sendNotification(transfer);
            
            if (response.getStatusCode().isError()) {
                LOGGER.error("Error sending notification");
            }

        } catch (Exception e) {
            LOGGER.error("Error sending notification", e);
        }
    }
}
