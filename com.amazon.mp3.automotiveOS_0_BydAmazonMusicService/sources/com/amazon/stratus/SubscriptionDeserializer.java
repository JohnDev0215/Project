package com.amazon.stratus;

import androidx.core.app.NotificationCompat;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SubscriptionDeserializer extends JsonDeserializer<Subscription> {
    public static final SubscriptionDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SubscriptionDeserializer subscriptionDeserializer = new SubscriptionDeserializer();
        INSTANCE = subscriptionDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.SubscriptionDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Subscription.class, subscriptionDeserializer);
    }

    private SubscriptionDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Subscription deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.stratus#Subscription".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Subscription deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Subscription subscription = new Subscription();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("hasOpenProblems".equals(currentName)) {
                subscription.setHasOpenProblems(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("nextBillDate".equals(currentName)) {
                subscription.setNextBillDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("endDate".equals(currentName)) {
                subscription.setEndDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("cancelReason".equals(currentName)) {
                subscription.setCancelReason(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subscriptionId".equals(currentName)) {
                subscription.setSubscriptionId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("startDate".equals(currentName)) {
                subscription.setStartDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("autoRenew".equals(currentName)) {
                subscription.setAutoRenew(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isMFARequired".equals(currentName)) {
                subscription.setIsMFARequired(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("paymentInfo".equals(currentName)) {
                subscription.setPaymentInfo(PaymentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("planCategory".equals(currentName)) {
                subscription.setPlanCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("nextBillAmount".equals(currentName)) {
                subscription.setNextBillAmount(SimpleDeserializers.deserializeBigDecimal(jsonParser, deserializationContext));
            } else if ("baseCurrencyCode".equals(currentName)) {
                subscription.setBaseCurrencyCode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("empDetails".equals(currentName)) {
                subscription.setEmpDetails(EmpDetailsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("addons".equals(currentName)) {
                subscription.setAddons(AddonsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (NotificationCompat.CATEGORY_STATUS.equals(currentName)) {
                subscription.setStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rolloverPlan".equals(currentName)) {
                subscription.setRolloverPlan(PlanDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("graceDaysBeforeCancellation".equals(currentName)) {
                subscription.setGraceDaysBeforeCancellation(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("contractPeriodStartDate".equals(currentName)) {
                subscription.setContractPeriodStartDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("paymentInstrumentId".equals(currentName)) {
                subscription.setPaymentInstrumentId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("plan".equals(currentName)) {
                subscription.setPlan(PlanDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return subscription;
    }
}
