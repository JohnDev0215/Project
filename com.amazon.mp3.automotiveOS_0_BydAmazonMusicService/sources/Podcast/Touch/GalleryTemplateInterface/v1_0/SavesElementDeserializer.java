package Podcast.Touch.GalleryTemplateInterface.v1_0;

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
public class SavesElementDeserializer extends JsonDeserializer<SavesElement> {
    public static final SavesElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SavesElementDeserializer savesElementDeserializer = new SavesElementDeserializer();
        INSTANCE = savesElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.SavesElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SavesElement.class, savesElementDeserializer);
    }

    private SavesElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SavesElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#SavesElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SavesElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SavesElement savesElement = new SavesElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("id".equals(currentName)) {
                savesElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                savesElement.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seeMoreText".equals(currentName)) {
                savesElement.setSeeMoreText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                savesElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onContentFirstViewed".equals(currentName)) {
                savesElement.setOnContentFirstViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return savesElement;
    }
}
