package Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0;

import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElementDeserializer;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementsDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class ExperienceVisualShovelerElementDeserializer extends JsonDeserializer<ExperienceVisualShovelerElement> {
    public static final ExperienceVisualShovelerElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ExperienceVisualShovelerElementDeserializer experienceVisualShovelerElementDeserializer = new ExperienceVisualShovelerElementDeserializer();
        INSTANCE = experienceVisualShovelerElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ExperienceVisualShovelerElement.class, experienceVisualShovelerElementDeserializer);
    }

    private ExperienceVisualShovelerElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ExperienceVisualShovelerElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0#ExperienceVisualShovelerElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ExperienceVisualShovelerElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ExperienceVisualShovelerElement experienceVisualShovelerElement = new ExperienceVisualShovelerElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.ITEMS.equals(currentName)) {
                experienceVisualShovelerElement.setItems(MediaItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryButton".equals(currentName)) {
                experienceVisualShovelerElement.setPrimaryButton(PrimaryButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                experienceVisualShovelerElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                experienceVisualShovelerElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                experienceVisualShovelerElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                experienceVisualShovelerElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onContentFirstViewed".equals(currentName)) {
                experienceVisualShovelerElement.setOnContentFirstViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                experienceVisualShovelerElement.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return experienceVisualShovelerElement;
    }
}
