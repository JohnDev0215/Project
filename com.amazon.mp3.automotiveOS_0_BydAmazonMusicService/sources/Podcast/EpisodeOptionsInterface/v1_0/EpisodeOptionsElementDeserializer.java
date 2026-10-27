package Podcast.EpisodeOptionsInterface.v1_0;

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
public class EpisodeOptionsElementDeserializer extends JsonDeserializer<EpisodeOptionsElement> {
    public static final EpisodeOptionsElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeOptionsElementDeserializer episodeOptionsElementDeserializer = new EpisodeOptionsElementDeserializer();
        INSTANCE = episodeOptionsElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeOptionsElement.class, episodeOptionsElementDeserializer);
    }

    private EpisodeOptionsElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeOptionsElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.EpisodeOptionsInterface.v1_0#EpisodeOptionsElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeOptionsElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeOptionsElement episodeOptionsElement = new EpisodeOptionsElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("share".equals(currentName)) {
                episodeOptionsElement.setShare(ShareElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playEpisode".equals(currentName)) {
                episodeOptionsElement.setPlayEpisode(PlayEpisodeElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("follow".equals(currentName)) {
                episodeOptionsElement.setFollow(FollowElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                episodeOptionsElement.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("goToPodcast".equals(currentName)) {
                episodeOptionsElement.setGoToPodcast(GoToPodcastElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                episodeOptionsElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("download".equals(currentName)) {
                episodeOptionsElement.setDownload(DownloadElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                episodeOptionsElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("carPreset".equals(currentName)) {
                episodeOptionsElement.setCarPreset(CarPresetElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEpisodeOptionsShow".equals(currentName)) {
                episodeOptionsElement.setOnEpisodeOptionsShow(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("completed".equals(currentName)) {
                episodeOptionsElement.setCompleted(CompletedElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEpisodeOptionsDismiss".equals(currentName)) {
                episodeOptionsElement.setOnEpisodeOptionsDismiss(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("save".equals(currentName)) {
                episodeOptionsElement.setSave(SaveElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("report".equals(currentName)) {
                episodeOptionsElement.setReport(ReportElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeOptionsElement;
    }
}
