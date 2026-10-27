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
public class EligibilityDeserializer extends JsonDeserializer<Eligibility> {
    public static final EligibilityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EligibilityDeserializer eligibilityDeserializer = new EligibilityDeserializer();
        INSTANCE = eligibilityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.EligibilityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Eligibility.class, eligibilityDeserializer);
    }

    private EligibilityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Eligibility deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#Eligibility".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Eligibility deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Eligibility eligibility = new Eligibility();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("eligible".equals(currentName)) {
                eligibility.setEligible(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("reasons".equals(currentName)) {
                eligibility.setReasons(EligibilityReasonsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("alternateDevices".equals(currentName)) {
                eligibility.setAlternateDevices(DeviceEligibilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requiresAlternateDevice".equals(currentName)) {
                eligibility.setRequiresAlternateDevice(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return eligibility;
    }
}
