package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.QueueDeserializer;
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
public class InvalidatePTCCacheMethodDeserializer extends JsonDeserializer<InvalidatePTCCacheMethod> {
    public static final InvalidatePTCCacheMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidatePTCCacheMethodDeserializer invalidatePTCCacheMethodDeserializer = new InvalidatePTCCacheMethodDeserializer();
        INSTANCE = invalidatePTCCacheMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(InvalidatePTCCacheMethod.class, invalidatePTCCacheMethodDeserializer);
    }

    private InvalidatePTCCacheMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public InvalidatePTCCacheMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#InvalidatePTCCacheMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public InvalidatePTCCacheMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        InvalidatePTCCacheMethod invalidatePTCCacheMethod = new InvalidatePTCCacheMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("queue".equals(currentName)) {
                invalidatePTCCacheMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                invalidatePTCCacheMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return invalidatePTCCacheMethod;
    }
}
