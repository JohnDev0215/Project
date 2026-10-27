package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
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
public class EpisodeHorizontalItemElementDeserializer extends JsonDeserializer<EpisodeHorizontalItemElement> {
    public static final EpisodeHorizontalItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeHorizontalItemElementDeserializer episodeHorizontalItemElementDeserializer = new EpisodeHorizontalItemElementDeserializer();
        INSTANCE = episodeHorizontalItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.EpisodeHorizontalItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeHorizontalItemElement.class, episodeHorizontalItemElementDeserializer);
    }

    private EpisodeHorizontalItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeHorizontalItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#EpisodeHorizontalItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeHorizontalItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeHorizontalItemElement episodeHorizontalItemElement = new EpisodeHorizontalItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.IMAGE.equals(currentName)) {
                episodeHorizontalItemElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.PRIMARY_TEXT.equals(currentName)) {
                episodeHorizontalItemElement.setPrimaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("altText".equals(currentName)) {
                episodeHorizontalItemElement.setAltText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onImageSelected".equals(currentName)) {
                episodeHorizontalItemElement.setOnImageSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                episodeHorizontalItemElement.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isDisabled".equals(currentName)) {
                episodeHorizontalItemElement.setIsDisabled(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                episodeHorizontalItemElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                episodeHorizontalItemElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeHorizontalItemElement;
    }
}
