package Podcast.Touch.EpisodeRowTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsDeserializer;
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
public class EpisodeRowTemplateDeserializer extends JsonDeserializer<EpisodeRowTemplate> {
    public static final EpisodeRowTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeRowTemplateDeserializer episodeRowTemplateDeserializer = new EpisodeRowTemplateDeserializer();
        INSTANCE = episodeRowTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeRowTemplateInterface.v1_0.EpisodeRowTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeRowTemplate.class, episodeRowTemplateDeserializer);
    }

    private EpisodeRowTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeRowTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.EpisodeRowTemplateInterface.v1_0#EpisodeRowTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeRowTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeRowTemplate episodeRowTemplate = new EpisodeRowTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                episodeRowTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                episodeRowTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                episodeRowTemplate.setItems(EpisodeRowItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                episodeRowTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                episodeRowTemplate.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                episodeRowTemplate.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                episodeRowTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                episodeRowTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                episodeRowTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                episodeRowTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                episodeRowTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeRowTemplate;
    }
}
