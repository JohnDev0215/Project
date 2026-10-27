package Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElementDeserializer;
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
public class EpisodeDetailTemplateDeserializer extends JsonDeserializer<EpisodeDetailTemplate> {
    public static final EpisodeDetailTemplateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDetailTemplateDeserializer episodeDetailTemplateDeserializer = new EpisodeDetailTemplateDeserializer();
        INSTANCE = episodeDetailTemplateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.EpisodeDetailTemplateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeDetailTemplate.class, episodeDetailTemplateDeserializer);
    }

    private EpisodeDetailTemplateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeDetailTemplate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0#EpisodeDetailTemplate".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeDetailTemplate deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeDetailTemplate episodeDetailTemplate = new EpisodeDetailTemplate();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("latencyMetricElement".equals(currentName)) {
                episodeDetailTemplate.setLatencyMetricElement(LatencyMetricElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                episodeDetailTemplate.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onCreated".equals(currentName)) {
                episodeDetailTemplate.setOnCreated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("bookmark".equals(currentName)) {
                episodeDetailTemplate.setBookmark(BookmarkElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                episodeDetailTemplate.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("download".equals(currentName)) {
                episodeDetailTemplate.setDownload(DownloadElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                episodeDetailTemplate.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onBound".equals(currentName)) {
                episodeDetailTemplate.setOnBound(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("save".equals(currentName)) {
                episodeDetailTemplate.setSave(SaveElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                episodeDetailTemplate.setHeader(HeaderElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metadata".equals(currentName)) {
                episodeDetailTemplate.setMetadata(TemplateMetadataElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("backgroundImage".equals(currentName)) {
                episodeDetailTemplate.setBackgroundImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("cacheSeconds".equals(currentName)) {
                episodeDetailTemplate.setCacheSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("share".equals(currentName)) {
                episodeDetailTemplate.setShare(ShareElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                episodeDetailTemplate.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("launchMode".equals(currentName)) {
                episodeDetailTemplate.setLaunchMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onDismissed".equals(currentName)) {
                episodeDetailTemplate.setOnDismissed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeDetailTemplate;
    }
}
