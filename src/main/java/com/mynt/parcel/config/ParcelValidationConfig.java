package com.mynt.parcel.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config.parcel-validation")
@Getter
@Setter
@NoArgsConstructor
public class ParcelValidationConfig {

    private DeliveryRule reject;
    private DeliveryRule heavy;
    private DeliveryRule small;
    private DeliveryRule medium;
    private DeliveryRule large;
}
