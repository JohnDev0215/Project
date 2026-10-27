package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
import SOACoreInterface.v1_0.QueueDeserializer;
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
public class ReplaceEpisodeRowItemsMethodDeserializer extends JsonDeserializer<ReplaceEpisodeRowItemsMethod> {
    public static final ReplaceEpisodeRowItemsMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReplaceEpisodeRowItemsMethodDeserializer replaceEpisodeRowItemsMethodDeserializer = new ReplaceEpisodeRowItemsMethodDeserializer();
        INSTANCE = replaceEpisodeRowItemsMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.ReplaceEpisodeRowItemsMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ReplaceEpisodeRowItemsMethod.class, replaceEpisodeRowItemsMethodDeserializer);
    }

    private ReplaceEpisodeRowItemsMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ReplaceEpisodeRowItemsMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#ReplaceEpisodeRowItemsMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ReplaceEpisodeRowItemsMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ReplaceEpisodeRowItemsMethod replaceEpisodeRowItemsMethod = new ReplaceEpisodeRowItemsMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.ITEMS.equals(currentName)) {
                replaceEpisodeRowItemsMethod.setItems(EpisodeRowItemsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                replaceEpisodeRowItemsMethod.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                replaceEpisodeRowItemsMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                replaceEpisodeRowItemsMethod.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                replaceEpisodeRowItemsMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return replaceEpisodeRowItemsMethod;
    }
}
