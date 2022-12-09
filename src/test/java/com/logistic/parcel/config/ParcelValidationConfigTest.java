package com.logistic.parcel.config;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.logistic.parcel.constant.RulePriority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ParcelValidationConfig.class)
class ParcelValidationConfigTest {

    @Test
    void testConstructor() {
        ParcelValidationConfig actualParcelValidationConfig = new ParcelValidationConfig();
        DeliveryRule deliveryRule = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        actualParcelValidationConfig.setHeavy(deliveryRule);
        DeliveryRule deliveryRule1 = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        actualParcelValidationConfig.setLarge(deliveryRule1);
        DeliveryRule deliveryRule2 = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        actualParcelValidationConfig.setMedium(deliveryRule2);
        DeliveryRule deliveryRule3 = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        actualParcelValidationConfig.setReject(deliveryRule3);
        DeliveryRule deliveryRule4 = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        actualParcelValidationConfig.setSmall(deliveryRule4);
        assertSame(deliveryRule, actualParcelValidationConfig.getHeavy());
        assertSame(deliveryRule1, actualParcelValidationConfig.getLarge());
        assertSame(deliveryRule2, actualParcelValidationConfig.getMedium());
        assertSame(deliveryRule3, actualParcelValidationConfig.getReject());
        assertSame(deliveryRule4, actualParcelValidationConfig.getSmall());
    }
}

