package Podcast.FollowPromptInterface.v1_0;

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
public class WriteHidePromptPreferencesMethodDeserializer extends JsonDeserializer<WriteHidePromptPreferencesMethod> {
    public static final WriteHidePromptPreferencesMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteHidePromptPreferencesMethodDeserializer writeHidePromptPreferencesMethodDeserializer = new WriteHidePromptPreferencesMethodDeserializer();
        INSTANCE = writeHidePromptPreferencesMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.FollowPromptInterface.v1_0.WriteHidePromptPreferencesMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(WriteHidePromptPreferencesMethod.class, writeHidePromptPreferencesMethodDeserializer);
    }

    private WriteHidePromptPreferencesMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public WriteHidePromptPreferencesMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.FollowPromptInterface.v1_0#WriteHidePromptPreferencesMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public WriteHidePromptPreferencesMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        WriteHidePromptPreferencesMethod writeHidePromptPreferencesMethod = new WriteHidePromptPreferencesMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastId".equals(currentName)) {
                writeHidePromptPreferencesMethod.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("preference".equals(currentName)) {
                writeHidePromptPreferencesMethod.setPreference(HidePromptPreferenceElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                writeHidePromptPreferencesMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                writeHidePromptPreferencesMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return writeHidePromptPreferencesMethod;
    }
}
