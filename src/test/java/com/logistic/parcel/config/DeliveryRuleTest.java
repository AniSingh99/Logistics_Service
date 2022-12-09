package com.logistic.parcel.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.parcel.constant.RulePriority;
import org.junit.jupiter.api.Test;

class DeliveryRuleTest {

    @Test
    void testConstructor() {
        DeliveryRule actualDeliveryRule = new DeliveryRule(10.0d, 10.0d, 10.0d, RulePriority.REJECT_WEIGHT);

        assertEquals(10.0d, actualDeliveryRule.getCostValue());
        assertEquals(RulePriority.REJECT_WEIGHT, actualDeliveryRule.getRuleName());
        assertEquals(10.0d, actualDeliveryRule.getLowest());
        assertEquals(10.0d, actualDeliveryRule.getHighest());
    }

    @Test
    void testConstructor2() {
        DeliveryRule actualDeliveryRule = new DeliveryRule();
        actualDeliveryRule.setRuleName(RulePriority.REJECT_WEIGHT);
        actualDeliveryRule.setCostValue(10.0d);
        actualDeliveryRule.setLowest(10.0d);
        actualDeliveryRule.setHighest(10.0d);
        assertEquals(10.0d, actualDeliveryRule.getCostValue());
        assertEquals(RulePriority.REJECT_WEIGHT, actualDeliveryRule.getRuleName());
        assertEquals(10.0d, actualDeliveryRule.getLowest());
        assertEquals(10.0d, actualDeliveryRule.getHighest());
        assertNotNull(actualDeliveryRule.toString());
    }
}

