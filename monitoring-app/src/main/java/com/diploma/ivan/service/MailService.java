package com.diploma.ivan.service;

import com.diploma.ivan.model.DeliveryObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.Duration;

import static java.util.Objects.isNull;

public class MailService {

    private static final String EMAIL_BODY = "Pod with name %s crosses threshold: actual value %s, threshold value %s \nScale deployment here: %s";
    private static final String URL = "%s/mitigate/v1/deployments/%s/%s";
    private static final int FIVE_MINUTES = 5 * 60 * 1000;
    private static JavaMailSender javaMailSender;
    private static LoadingCache<String, Long> cache;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(5))
                .build(new CacheLoader<String, Long>() {
            @Override
            public Long load(String key) {
                return (long) key.length();
            }
        });
    }

    public static void executeMail(DeliveryObject deliveryObject, String destination) {
        if (checkIfKeyIsNotPresent(deliveryObject.getPodName())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destination);
            message.setSubject("Threshold is crossed");
            String url = String.format(URL,deliveryObject.getBaseUrl(), deliveryObject.getNamespace(), deliveryObject.getDeployment());
            message.setText(String.format(EMAIL_BODY, deliveryObject.getPodName(), deliveryObject.getActualValue(), deliveryObject.getThresholdValue(), url));

            javaMailSender.send(message);
        }
    }

    private static boolean checkIfKeyIsNotPresent(String key) {
        try {
            Long time = cache.get(key);

            if (isNull(time) || System.currentTimeMillis() - time < FIVE_MINUTES) {
                return false;
            }
        } catch (Exception exception) {
            //ignore
        }

        cache.put(key, System.currentTimeMillis());
        return true;
    }

}
