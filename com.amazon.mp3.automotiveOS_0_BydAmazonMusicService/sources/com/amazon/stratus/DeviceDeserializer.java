package com.amazon.stratus;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class DeviceDeserializer extends JsonDeserializer<Device> {
    public static final DeviceDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceDeserializer deviceDeserializer = new DeviceDeserializer();
        INSTANCE = deviceDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.DeviceDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Device.class, deviceDeserializer);
    }

    private DeviceDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Device deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#Device".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Device deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Device device = new Device();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("customerId".equals(currentName)) {
                device.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_ID.equals(currentName)) {
                device.setDeviceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableState".equals(currentName)) {
                device.setAvailableState(DeviceAvailableStateDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_TYPE.equals(currentName)) {
                device.setDeviceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currentState".equals(currentName)) {
                device.setCurrentState(DeviceCurrentStateDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return device;
    }
}
