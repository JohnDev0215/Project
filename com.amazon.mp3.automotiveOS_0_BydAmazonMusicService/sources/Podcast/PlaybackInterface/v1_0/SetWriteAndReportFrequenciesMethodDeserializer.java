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
public class SetWriteAndReportFrequenciesMethodDeserializer extends JsonDeserializer<SetWriteAndReportFrequenciesMethod> {
    public static final SetWriteAndReportFrequenciesMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetWriteAndReportFrequenciesMethodDeserializer setWriteAndReportFrequenciesMethodDeserializer = new SetWriteAndReportFrequenciesMethodDeserializer();
        INSTANCE = setWriteAndReportFrequenciesMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.PlaybackInterface.v1_0.SetWriteAndReportFrequenciesMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SetWriteAndReportFrequenciesMethod.class, setWriteAndReportFrequenciesMethodDeserializer);
    }

    private SetWriteAndReportFrequenciesMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SetWriteAndReportFrequenciesMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.PlaybackInterface.v1_0#SetWriteAndReportFrequenciesMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SetWriteAndReportFrequenciesMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SetWriteAndReportFrequenciesMethod setWriteAndReportFrequenciesMethod = new SetWriteAndReportFrequenciesMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("writeFrequencySeconds".equals(currentName)) {
                setWriteAndReportFrequenciesMethod.setWriteFrequencySeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                setWriteAndReportFrequenciesMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("writeAndReportFrequencySeconds".equals(currentName)) {
                setWriteAndReportFrequenciesMethod.setWriteAndReportFrequencySeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                setWriteAndReportFrequenciesMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return setWriteAndReportFrequenciesMethod;
    }
}
