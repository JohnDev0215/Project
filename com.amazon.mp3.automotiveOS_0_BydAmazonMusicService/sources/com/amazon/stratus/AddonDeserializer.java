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
public class AddonDeserializer extends JsonDeserializer<Addon> {
    public static final AddonDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddonDeserializer addonDeserializer = new AddonDeserializer();
        INSTANCE = addonDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AddonDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Addon.class, addonDeserializer);
    }

    private AddonDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Addon deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#Addon".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Addon deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Addon addon = new Addon();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("autoRenew".equals(currentName)) {
                addon.setAutoRenew(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("plans".equals(currentName)) {
                addon.setPlans(PlanListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isInSyncWithBaseSubscription".equals(currentName)) {
                addon.setIsInSyncWithBaseSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("startDate".equals(currentName)) {
                addon.setStartDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("nextBillAmount".equals(currentName)) {
                addon.setNextBillAmount(SimpleDeserializers.deserializeBigDecimal(jsonParser, deserializationContext));
            } else if ("baseCurrencyCode".equals(currentName)) {
                addon.setBaseCurrencyCode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("cancelReason".equals(currentName)) {
                addon.setCancelReason(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("planCategory".equals(currentName)) {
                addon.setPlanCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("empDetails".equals(currentName)) {
                addon.setEmpDetails(EmpDetailsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("addonId".equals(currentName)) {
                addon.setAddonId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hasOpenProblems".equals(currentName)) {
                addon.setHasOpenProblems(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("nextBillDate".equals(currentName)) {
                addon.setNextBillDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("endDate".equals(currentName)) {
                addon.setEndDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("graceDaysBeforeCancellation".equals(currentName)) {
                addon.setGraceDaysBeforeCancellation(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("tentativeEndDate".equals(currentName)) {
                addon.setTentativeEndDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("contractPeriodStartDate".equals(currentName)) {
                addon.setContractPeriodStartDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if (NotificationCompat.CATEGORY_STATUS.equals(currentName)) {
                addon.setStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMFARequired".equals(currentName)) {
                addon.setIsMFARequired(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return addon;
    }
}
