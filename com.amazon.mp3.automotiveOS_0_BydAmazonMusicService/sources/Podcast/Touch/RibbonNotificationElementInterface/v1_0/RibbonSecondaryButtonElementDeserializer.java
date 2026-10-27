package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class RibbonSecondaryButtonElementDeserializer extends JsonDeserializer<RibbonSecondaryButtonElement> {
    public static final RibbonSecondaryButtonElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RibbonSecondaryButtonElementDeserializer ribbonSecondaryButtonElementDeserializer = new RibbonSecondaryButtonElementDeserializer();
        INSTANCE = ribbonSecondaryButtonElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RibbonSecondaryButtonElement.class, ribbonSecondaryButtonElementDeserializer);
    }

    private RibbonSecondaryButtonElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RibbonSecondaryButtonElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.RibbonNotificationElementInterface.v1_0#RibbonSecondaryButtonElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RibbonSecondaryButtonElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RibbonSecondaryButtonElement ribbonSecondaryButtonElement = new RibbonSecondaryButtonElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("onItemSelected".equals(currentName)) {
                ribbonSecondaryButtonElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("icon".equals(currentName)) {
                ribbonSecondaryButtonElement.setIcon(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                ribbonSecondaryButtonElement.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return ribbonSecondaryButtonElement;
    }
}
