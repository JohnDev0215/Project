package Podcast.PlaybackMetricsInterface.v1_0;

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
public class PlaybackMetricWriteElementDeserializer extends JsonDeserializer<PlaybackMetricWriteElement> {
    public static final PlaybackMetricWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackMetricWriteElementDeserializer playbackMetricWriteElementDeserializer = new PlaybackMetricWriteElementDeserializer();
        INSTANCE = playbackMetricWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaybackMetricWriteElement.class, playbackMetricWriteElementDeserializer);
    }

    private PlaybackMetricWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaybackMetricWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.PlaybackMetricsInterface.v1_0#PlaybackMetricWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaybackMetricWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaybackMetricWriteElement playbackMetricWriteElement = new PlaybackMetricWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("mediaId".equals(currentName)) {
                playbackMetricWriteElement.setMediaId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackInstanceId".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                playbackMetricWriteElement.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackSignalType".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackSignalType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                playbackMetricWriteElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("initialPlaybackStartDelayMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setInitialPlaybackStartDelayMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("isMediaDownloaded".equals(currentName)) {
                playbackMetricWriteElement.setIsMediaDownloaded(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("mediaSubTitle".equals(currentName)) {
                playbackMetricWriteElement.setMediaSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("mediaTitle".equals(currentName)) {
                playbackMetricWriteElement.setMediaTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("mediaCollectionType".equals(currentName)) {
                playbackMetricWriteElement.setMediaCollectionType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackRequestedAtTimestampMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackRequestedAtTimestampMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("rebufferCount".equals(currentName)) {
                playbackMetricWriteElement.setRebufferCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("rebufferDurationMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setRebufferDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("currentPlaybackSpeed".equals(currentName)) {
                playbackMetricWriteElement.setCurrentPlaybackSpeed(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("audioUri".equals(currentName)) {
                playbackMetricWriteElement.setAudioUri(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currentProgressMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setCurrentProgressMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("playbackStartOffsetMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackStartOffsetMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("playbackStartedAtTimestampMilliseconds".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackStartedAtTimestampMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("mediaCollectionId".equals(currentName)) {
                playbackMetricWriteElement.setMediaCollectionId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("errorException".equals(currentName)) {
                playbackMetricWriteElement.setErrorException(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageType".equals(currentName)) {
                playbackMetricWriteElement.setPageType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                playbackMetricWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("metricsPreset".equals(currentName)) {
                playbackMetricWriteElement.setMetricsPreset(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackEngineType".equals(currentName)) {
                playbackMetricWriteElement.setPlaybackEngineType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playbackMetricWriteElement;
    }
}
