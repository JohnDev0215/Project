package com.amazon.stratus;

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
public class PlanDeserializer extends JsonDeserializer<Plan> {
    public static final PlanDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlanDeserializer planDeserializer = new PlanDeserializer();
        INSTANCE = planDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.PlanDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Plan.class, planDeserializer);
    }

    private PlanDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Plan deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#Plan".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Plan deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Plan plan = new Plan();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isPromo".equals(currentName)) {
                plan.setIsPromo(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                plan.setPrice(SimpleDeserializers.deserializeBigDecimal(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                plan.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("defaultRolloverPlan".equals(currentName)) {
                plan.setDefaultRolloverPlan(INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("planId".equals(currentName)) {
                plan.setPlanId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contractPeriod".equals(currentName)) {
                plan.setContractPeriod(PeriodDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("currencyCode".equals(currentName)) {
                plan.setCurrencyCode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return plan;
    }
}
