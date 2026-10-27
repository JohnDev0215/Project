package Podcast.Touch.LatestTemplateInterface.v2_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementDeserializer;
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
public class FeaturedItemWriteCacheElementDeserializer extends JsonDeserializer<FeaturedItemWriteCacheElement> {
    public static final FeaturedItemWriteCacheElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedItemWriteCacheElementDeserializer featuredItemWriteCacheElementDeserializer = new FeaturedItemWriteCacheElementDeserializer();
        INSTANCE = featuredItemWriteCacheElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FeaturedItemWriteCacheElement.class, featuredItemWriteCacheElementDeserializer);
    }

    private FeaturedItemWriteCacheElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FeaturedItemWriteCacheElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedItemWriteCacheElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FeaturedItemWriteCacheElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FeaturedItemWriteCacheElement featuredItemWriteCacheElement = new FeaturedItemWriteCacheElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastId".equals(currentName)) {
                featuredItemWriteCacheElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onInvalidated".equals(currentName)) {
                featuredItemWriteCacheElement.setOnInvalidated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("item".equals(currentName)) {
                featuredItemWriteCacheElement.setItem(EpisodeRowItemElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastImage".equals(currentName)) {
                featuredItemWriteCacheElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return featuredItemWriteCacheElement;
    }
}
