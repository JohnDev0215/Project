package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class MediaEventsElementDeserializer extends JsonDeserializer<MediaEventsElement> {
    public static final MediaEventsElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaEventsElementDeserializer mediaEventsElementDeserializer = new MediaEventsElementDeserializer();
        INSTANCE = mediaEventsElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.MediaEventsElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MediaEventsElement.class, mediaEventsElementDeserializer);
    }

    private MediaEventsElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MediaEventsElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MediaEventsElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MediaEventsElement mediaEventsElement = new MediaEventsElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("onFinished".equals(currentName)) {
                mediaEventsElement.setOnFinished(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onSleepTimerSet".equals(currentName)) {
                mediaEventsElement.setOnSleepTimerSet(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onFastForward".equals(currentName)) {
                mediaEventsElement.setOnFastForward(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onStopped".equals(currentName)) {
                mediaEventsElement.setOnStopped(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onError".equals(currentName)) {
                mediaEventsElement.setOnError(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onCompletedThresholdElapsed".equals(currentName)) {
                mediaEventsElement.setOnCompletedThresholdElapsed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPlaybackSpeedModified".equals(currentName)) {
                mediaEventsElement.setOnPlaybackSpeedModified(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onSleepTimerElapsed".equals(currentName)) {
                mediaEventsElement.setOnSleepTimerElapsed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onWriteFrequencyReached".equals(currentName)) {
                mediaEventsElement.setOnWriteFrequencyReached(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPostScrubbed".equals(currentName)) {
                mediaEventsElement.setOnPostScrubbed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPaused".equals(currentName)) {
                mediaEventsElement.setOnPaused(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onWriteAndReportFrequencyReached".equals(currentName)) {
                mediaEventsElement.setOnWriteAndReportFrequencyReached(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onRewind".equals(currentName)) {
                mediaEventsElement.setOnRewind(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onResumed".equals(currentName)) {
                mediaEventsElement.setOnResumed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onRebuffered".equals(currentName)) {
                mediaEventsElement.setOnRebuffered(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onPreScrubbed".equals(currentName)) {
                mediaEventsElement.setOnPreScrubbed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onScrubbed".equals(currentName)) {
                mediaEventsElement.setOnScrubbed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onStarted".equals(currentName)) {
                mediaEventsElement.setOnStarted(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return mediaEventsElement;
    }
}
