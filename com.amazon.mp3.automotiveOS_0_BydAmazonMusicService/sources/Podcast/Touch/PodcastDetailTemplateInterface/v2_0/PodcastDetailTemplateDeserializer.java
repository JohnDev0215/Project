package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

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
public class PodcastDetailTemplateDeserializer extends JsonDeserializer<PodcastDetailTemplate> {
    public static final PodcastDetailTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastDetailTemplateDeserializer podcastDetailTemplateDeserializer = new PodcastDetailTemplateDeserializer();
        INSTANCE = podcastDetailTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PodcastDetailTemplate.class, podcastDetailTemplateDeserializer);
    }

    private PodcastDetailTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PodcastDetailTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#PodcastDetailTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PodcastDetailTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PodcastDetailTemplate podcastDetailTemplate = new PodcastDetailTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                podcastDetailTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                podcastDetailTemplate.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                podcastDetailTemplate.setItems(EpisodeRowItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                podcastDetailTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                podcastDetailTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                podcastDetailTemplate.setHeader(HeaderElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("backgroundImage".equals(currentName)) {
                podcastDetailTemplate.setBackgroundImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                podcastDetailTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                podcastDetailTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("share".equals(currentName)) {
                podcastDetailTemplate.setShare(ShareElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("badge".equals(currentName)) {
                podcastDetailTemplate.setBadge(BadgeElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                podcastDetailTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("featuredPinnedElement".equals(currentName)) {
                podcastDetailTemplate.setFeaturedPinnedElement(FeaturedPinnedElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("refinementBarElement".equals(currentName)) {
                podcastDetailTemplate.setRefinementBarElement(RefinementBarElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("button".equals(currentName)) {
                podcastDetailTemplate.setButton(ButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                podcastDetailTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                podcastDetailTemplate.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("follow".equals(currentName)) {
                podcastDetailTemplate.setFollow(FollowElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("about".equals(currentName)) {
                podcastDetailTemplate.setAbout(AboutElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                podcastDetailTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return podcastDetailTemplate;
    }
}
