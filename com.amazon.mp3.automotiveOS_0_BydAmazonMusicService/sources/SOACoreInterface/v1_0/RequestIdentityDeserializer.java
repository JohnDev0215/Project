package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
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
public class RequestIdentityDeserializer extends JsonDeserializer<RequestIdentity> {
    public static final RequestIdentityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestIdentityDeserializer requestIdentityDeserializer = new RequestIdentityDeserializer();
        INSTANCE = requestIdentityDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.RequestIdentityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RequestIdentity.class, requestIdentityDeserializer);
    }

    private RequestIdentityDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RequestIdentity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("SOACoreInterface.v1_0#RequestIdentity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RequestIdentity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RequestIdentity requestIdentity = new RequestIdentity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("domain".equals(currentName)) {
                requestIdentity.setDomain(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("ipAddress".equals(currentName)) {
                requestIdentity.setIpAddress(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                requestIdentity.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_SESSION_ID.equals(currentName)) {
                requestIdentity.setSessionId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                requestIdentity.setTimestamp(SimpleDeserializers.deserializePrimitiveLong(jsonParser, deserializationContext));
            } else if ("csrf".equals(currentName)) {
                requestIdentity.setCsrf(CsrfDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return requestIdentity;
    }
}
