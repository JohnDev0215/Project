package Podcast.PlaybackInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class SetMediaMethodDeserializer extends JsonDeserializer<SetMediaMethod> {
    public static final SetMediaMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetMediaMethodDeserializer setMediaMethodDeserializer = new SetMediaMethodDeserializer();
        INSTANCE = setMediaMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetMediaMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SetMediaMethod.class, setMediaMethodDeserializer);
    }

    private SetMediaMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SetMediaMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.PlaybackInterface.v1_0#SetMediaMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SetMediaMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SetMediaMethod setMediaMethod = new SetMediaMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("actions".equals(currentName)) {
                setMediaMethod.setActions(MediaActionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("ignorable".equals(currentName)) {
                setMediaMethod.setIgnorable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("metadata".equals(currentName)) {
                setMediaMethod.setMetadata(MediaMetadataElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("events".equals(currentName)) {
                setMediaMethod.setEvents(MediaEventsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("controls".equals(currentName)) {
                setMediaMethod.setControls(MediaControlsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onRestore".equals(currentName)) {
                setMediaMethod.setOnRestore(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                setMediaMethod.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                setMediaMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                setMediaMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("onRestoreAndStart".equals(currentName)) {
                setMediaMethod.setOnRestoreAndStart(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("mediaChangeReason".equals(currentName)) {
                setMediaMethod.setMediaChangeReason(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return setMediaMethod;
    }
}
