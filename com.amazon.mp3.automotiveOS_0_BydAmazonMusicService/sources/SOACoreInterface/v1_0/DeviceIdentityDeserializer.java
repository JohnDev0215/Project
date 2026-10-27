package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DeviceIdentityDeserializer extends JsonDeserializer<DeviceIdentity> {
    public static final DeviceIdentityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceIdentityDeserializer deviceIdentityDeserializer = new DeviceIdentityDeserializer();
        INSTANCE = deviceIdentityDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.DeviceIdentityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(DeviceIdentity.class, deviceIdentityDeserializer);
    }

    private DeviceIdentityDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public DeviceIdentity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("SOACoreInterface.v1_0#DeviceIdentity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public DeviceIdentity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DeviceIdentity deviceIdentity = new DeviceIdentity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("language".equals(currentName)) {
                deviceIdentity.setLanguage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                deviceIdentity.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("timeZone".equals(currentName)) {
                deviceIdentity.setTimeZone(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("typeId".equals(currentName)) {
                deviceIdentity.setTypeId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("manufacturer".equals(currentName)) {
                deviceIdentity.setManufacturer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("width".equals(currentName)) {
                deviceIdentity.setWidth(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("model".equals(currentName)) {
                deviceIdentity.setModel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("osVersion".equals(currentName)) {
                deviceIdentity.setOsVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("height".equals(currentName)) {
                deviceIdentity.setHeight(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return deviceIdentity;
    }
}
