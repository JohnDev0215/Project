package SOACoreInterface.v1_0;

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
public class IdentityDeserializer extends JsonDeserializer<Identity> {
    public static final IdentityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        IdentityDeserializer identityDeserializer = new IdentityDeserializer();
        INSTANCE = identityDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.IdentityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Identity.class, identityDeserializer);
    }

    private IdentityDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Identity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("SOACoreInterface.v1_0#Identity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Identity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Identity identity = new Identity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("device".equals(currentName)) {
                identity.setDevice(DeviceIdentityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("account".equals(currentName)) {
                identity.setAccount(AccountIdentityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("request".equals(currentName)) {
                identity.setRequest(RequestIdentityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("application".equals(currentName)) {
                identity.setApplication(ApplicationIdentityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("user".equals(currentName)) {
                identity.setUser(UserIdentityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return identity;
    }
}
