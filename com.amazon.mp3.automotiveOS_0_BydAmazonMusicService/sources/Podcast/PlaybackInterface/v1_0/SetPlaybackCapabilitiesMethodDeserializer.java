package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.QueueDeserializer;
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
public class SetPlaybackCapabilitiesMethodDeserializer extends JsonDeserializer<SetPlaybackCapabilitiesMethod> {
    public static final SetPlaybackCapabilitiesMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetPlaybackCapabilitiesMethodDeserializer setPlaybackCapabilitiesMethodDeserializer = new SetPlaybackCapabilitiesMethodDeserializer();
        INSTANCE = setPlaybackCapabilitiesMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetPlaybackCapabilitiesMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SetPlaybackCapabilitiesMethod.class, setPlaybackCapabilitiesMethodDeserializer);
    }

    private SetPlaybackCapabilitiesMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SetPlaybackCapabilitiesMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.PlaybackInterface.v1_0#SetPlaybackCapabilitiesMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SetPlaybackCapabilitiesMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SetPlaybackCapabilitiesMethod setPlaybackCapabilitiesMethod = new SetPlaybackCapabilitiesMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("miniplayer".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setMiniplayer(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("rewind".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setRewind(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("scrubber".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setScrubber(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("playbackSpeed".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setPlaybackSpeed(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("lockScreen".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setLockScreen(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("sleepTimer".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setSleepTimer(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("fastForward".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setFastForward(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("transcript".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setTranscript(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                setPlaybackCapabilitiesMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return setPlaybackCapabilitiesMethod;
    }
}
