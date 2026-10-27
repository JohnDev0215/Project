package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
public class PodcastFeaturedPlayElementDeserializer extends JsonDeserializer<PodcastFeaturedPlayElement> {
    public static final PodcastFeaturedPlayElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastFeaturedPlayElementDeserializer podcastFeaturedPlayElementDeserializer = new PodcastFeaturedPlayElementDeserializer();
        INSTANCE = podcastFeaturedPlayElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PodcastFeaturedPlayElement.class, podcastFeaturedPlayElementDeserializer);
    }

    private PodcastFeaturedPlayElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PodcastFeaturedPlayElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#PodcastFeaturedPlayElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PodcastFeaturedPlayElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PodcastFeaturedPlayElement podcastFeaturedPlayElement = new PodcastFeaturedPlayElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("id".equals(currentName)) {
                podcastFeaturedPlayElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                podcastFeaturedPlayElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.TERTIARY_TEXT.equals(currentName)) {
                podcastFeaturedPlayElement.setTertiaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                podcastFeaturedPlayElement.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.PRIMARY_TEXT.equals(currentName)) {
                podcastFeaturedPlayElement.setPrimaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                podcastFeaturedPlayElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                podcastFeaturedPlayElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (PageUriUtils.DO_PLAY_VALUE.equals(currentName)) {
                podcastFeaturedPlayElement.setPlay(PodcastPlayElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                podcastFeaturedPlayElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("foregroundImage".equals(currentName)) {
                podcastFeaturedPlayElement.setForegroundImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("backgroundImage".equals(currentName)) {
                podcastFeaturedPlayElement.setBackgroundImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onContentFirstViewed".equals(currentName)) {
                podcastFeaturedPlayElement.setOnContentFirstViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return podcastFeaturedPlayElement;
    }
}
