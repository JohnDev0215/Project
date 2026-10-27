package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
public class DeviceEligibilityDeserializer extends JsonDeserializer<DeviceEligibility> {
    public static final DeviceEligibilityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceEligibilityDeserializer deviceEligibilityDeserializer = new DeviceEligibilityDeserializer();
        INSTANCE = deviceEligibilityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.DeviceEligibilityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(DeviceEligibility.class, deviceEligibilityDeserializer);
    }

    private DeviceEligibilityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public DeviceEligibility deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#DeviceEligibility".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public DeviceEligibility deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DeviceEligibility deviceEligibility = new DeviceEligibility();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("id".equals(currentName)) {
                deviceEligibility.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("eligible".equals(currentName)) {
                deviceEligibility.setEligible(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                deviceEligibility.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return deviceEligibility;
    }
}
