package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

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
public class RefinementOptionsClientStateDeserializer extends JsonDeserializer<RefinementOptionsClientState> {
    public static final RefinementOptionsClientStateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RefinementOptionsClientStateDeserializer refinementOptionsClientStateDeserializer = new RefinementOptionsClientStateDeserializer();
        INSTANCE = refinementOptionsClientStateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.RefinementOptionsClientStateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RefinementOptionsClientState.class, refinementOptionsClientStateDeserializer);
    }

    private RefinementOptionsClientStateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RefinementOptionsClientState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#RefinementOptionsClientState".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RefinementOptionsClientState deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RefinementOptionsClientState refinementOptionsClientState = new RefinementOptionsClientState();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("sort".equals(currentName)) {
                refinementOptionsClientState.setSort(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                refinementOptionsClientState.setSeasonNumber(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("unplayed".equals(currentName)) {
                refinementOptionsClientState.setUnplayed(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("download".equals(currentName)) {
                refinementOptionsClientState.setDownload(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return refinementOptionsClientState;
    }
}
