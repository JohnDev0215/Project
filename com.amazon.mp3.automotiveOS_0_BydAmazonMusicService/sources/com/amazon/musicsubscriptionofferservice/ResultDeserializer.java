package com.amazon.musicsubscriptionofferservice;

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
public class ResultDeserializer extends JsonDeserializer<Result> {
    public static final ResultDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResultDeserializer resultDeserializer = new ResultDeserializer();
        INSTANCE = resultDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.ResultDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Result.class, resultDeserializer);
    }

    private ResultDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Result deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#Result".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Result deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Result result = new Result();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("planSchedule".equals(currentName)) {
                result.setPlanSchedule(PlanScheduleDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("partnerPromotionEligibilities".equals(currentName)) {
                result.setPartnerPromotionEligibilities(PartnerPromotionEligibilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("transitionType".equals(currentName)) {
                result.setTransitionType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("eligibility".equals(currentName)) {
                result.setEligibility(EligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("buyability".equals(currentName)) {
                result.setBuyability(BuyabilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return result;
    }
}
