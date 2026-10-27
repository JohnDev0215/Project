package Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0;

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
public class FeaturedBarkerItemElementDeserializer extends JsonDeserializer<FeaturedBarkerItemElement> {
    public static final FeaturedBarkerItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedBarkerItemElementDeserializer featuredBarkerItemElementDeserializer = new FeaturedBarkerItemElementDeserializer();
        INSTANCE = featuredBarkerItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FeaturedBarkerItemElement.class, featuredBarkerItemElementDeserializer);
    }

    private FeaturedBarkerItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FeaturedBarkerItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0#FeaturedBarkerItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FeaturedBarkerItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FeaturedBarkerItemElement featuredBarkerItemElement = new FeaturedBarkerItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("backgroundImageLarge".equals(currentName)) {
                featuredBarkerItemElement.setBackgroundImageLarge(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                featuredBarkerItemElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                featuredBarkerItemElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                featuredBarkerItemElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("backgroundImageSmall".equals(currentName)) {
                featuredBarkerItemElement.setBackgroundImageSmall(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.PRIMARY_TEXT.equals(currentName)) {
                featuredBarkerItemElement.setPrimaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                featuredBarkerItemElement.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return featuredBarkerItemElement;
    }
}
