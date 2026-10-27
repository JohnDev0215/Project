package Podcast.Touch.PTCTemplateInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PTCPodcastsTemplateDeserializer extends JsonDeserializer<PTCPodcastsTemplate> {
    public static final PTCPodcastsTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PTCPodcastsTemplateDeserializer pTCPodcastsTemplateDeserializer = new PTCPodcastsTemplateDeserializer();
        INSTANCE = pTCPodcastsTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PTCPodcastsTemplate.class, pTCPodcastsTemplateDeserializer);
    }

    private PTCPodcastsTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PTCPodcastsTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCPodcastsTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PTCPodcastsTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PTCPodcastsTemplate pTCPodcastsTemplate = new PTCPodcastsTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                pTCPodcastsTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                pTCPodcastsTemplate.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                pTCPodcastsTemplate.setItems(GridElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                pTCPodcastsTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                pTCPodcastsTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                pTCPodcastsTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                pTCPodcastsTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trailingButton".equals(currentName)) {
                pTCPodcastsTemplate.setTrailingButton(TrailingButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                pTCPodcastsTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                pTCPodcastsTemplate.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                pTCPodcastsTemplate.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                pTCPodcastsTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("leadingButton".equals(currentName)) {
                pTCPodcastsTemplate.setLeadingButton(LeadingButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                pTCPodcastsTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return pTCPodcastsTemplate;
    }
}
