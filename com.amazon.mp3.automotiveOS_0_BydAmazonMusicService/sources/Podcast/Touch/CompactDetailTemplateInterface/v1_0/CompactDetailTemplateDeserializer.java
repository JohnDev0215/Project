package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementDeserializer;
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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CompactDetailTemplateDeserializer extends JsonDeserializer<CompactDetailTemplate> {
    public static final CompactDetailTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompactDetailTemplateDeserializer compactDetailTemplateDeserializer = new CompactDetailTemplateDeserializer();
        INSTANCE = compactDetailTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CompactDetailTemplate.class, compactDetailTemplateDeserializer);
    }

    private CompactDetailTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CompactDetailTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.CompactDetailTemplateInterface.v1_0#CompactDetailTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CompactDetailTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CompactDetailTemplate compactDetailTemplate = new CompactDetailTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                compactDetailTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                compactDetailTemplate.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                compactDetailTemplate.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("share".equals(currentName)) {
                compactDetailTemplate.setShare(ShareElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("button".equals(currentName)) {
                compactDetailTemplate.setButton(ButtonElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("bookmark".equals(currentName)) {
                compactDetailTemplate.setBookmark(BookmarkElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                compactDetailTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                compactDetailTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("badge".equals(currentName)) {
                compactDetailTemplate.setBadge(BadgeElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                compactDetailTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                compactDetailTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("download".equals(currentName)) {
                compactDetailTemplate.setDownload(DownloadElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                compactDetailTemplate.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                compactDetailTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                compactDetailTemplate.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("save".equals(currentName)) {
                compactDetailTemplate.setSave(SaveElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                compactDetailTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                compactDetailTemplate.setHeader(HeaderElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("backgroundImage".equals(currentName)) {
                compactDetailTemplate.setBackgroundImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                compactDetailTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return compactDetailTemplate;
    }
}
