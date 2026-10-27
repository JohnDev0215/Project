package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsDeserializer;
import com.amazon.music.find.utils.DeeplinksUtils;
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
public class EpisodeGroupItemElementDeserializer extends JsonDeserializer<EpisodeGroupItemElement> {
    public static final EpisodeGroupItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeGroupItemElementDeserializer episodeGroupItemElementDeserializer = new EpisodeGroupItemElementDeserializer();
        INSTANCE = episodeGroupItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeGroupItemElement.class, episodeGroupItemElementDeserializer);
    }

    private EpisodeGroupItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeGroupItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.EpisodeGroupItemsInterface.v1_0#EpisodeGroupItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeGroupItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeGroupItemElement episodeGroupItemElement = new EpisodeGroupItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("header".equals(currentName)) {
                episodeGroupItemElement.setHeader(HeaderRowItemElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (DeeplinksUtils.PODCAST_EPISODES_PATH_SEGMENT.equals(currentName)) {
                episodeGroupItemElement.setEpisodes(EpisodeRowItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeGroupItemElement;
    }
}
