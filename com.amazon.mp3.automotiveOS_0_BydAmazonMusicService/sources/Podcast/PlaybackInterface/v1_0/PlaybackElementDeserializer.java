package Podcast.PlaybackInterface.v1_0;

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
public class PlaybackElementDeserializer extends JsonDeserializer<PlaybackElement> {
    public static final PlaybackElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaybackElementDeserializer playbackElementDeserializer = new PlaybackElementDeserializer();
        INSTANCE = playbackElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.PlaybackElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaybackElement.class, playbackElementDeserializer);
    }

    private PlaybackElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaybackElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.PlaybackInterface.v1_0#MediaEventsElement".equals(text)) {
                return MediaEventsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#EMPMediaMetadataElement".equals(text)) {
                return EMPMediaMetadataElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaControlsElement".equals(text)) {
                return MediaControlsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#SleepTimerElement".equals(text)) {
                return SleepTimerElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaMetadataElement".equals(text)) {
                return MediaMetadataElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlaybackSpeedElement".equals(text)) {
                return PlaybackSpeedElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#MediaActionsElement".equals(text)) {
                return MediaActionsElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#RewindElement".equals(text)) {
                return RewindElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlayPauseElement".equals(text)) {
                return PlayPauseElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#FastForwardElement".equals(text)) {
                return FastForwardElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.PlaybackInterface.v1_0#PlaybackElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaybackElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
