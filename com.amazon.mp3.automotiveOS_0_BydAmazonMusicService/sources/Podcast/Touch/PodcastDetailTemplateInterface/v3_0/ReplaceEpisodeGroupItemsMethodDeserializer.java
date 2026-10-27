package Podcast.Touch.PodcastDetailTemplateInterface.v3_0;

import Podcast.Touch.EpisodeGroupItemsInterface.v1_0.EpisodeGroupItemElementsDeserializer;
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
public class ReplaceEpisodeGroupItemsMethodDeserializer extends JsonDeserializer<ReplaceEpisodeGroupItemsMethod> {
    public static final ReplaceEpisodeGroupItemsMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ReplaceEpisodeGroupItemsMethodDeserializer replaceEpisodeGroupItemsMethodDeserializer = new ReplaceEpisodeGroupItemsMethodDeserializer();
        INSTANCE = replaceEpisodeGroupItemsMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v3_0.ReplaceEpisodeGroupItemsMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ReplaceEpisodeGroupItemsMethod.class, replaceEpisodeGroupItemsMethodDeserializer);
    }

    private ReplaceEpisodeGroupItemsMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ReplaceEpisodeGroupItemsMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#ReplaceEpisodeGroupItemsMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ReplaceEpisodeGroupItemsMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ReplaceEpisodeGroupItemsMethod replaceEpisodeGroupItemsMethod = new ReplaceEpisodeGroupItemsMethod();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.ITEMS.equals(currentName)) {
                replaceEpisodeGroupItemsMethod.setItems(EpisodeGroupItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                replaceEpisodeGroupItemsMethod.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onEndOfList".equals(currentName)) {
                replaceEpisodeGroupItemsMethod.setOnEndOfList(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("queue".equals(currentName)) {
                replaceEpisodeGroupItemsMethod.setQueue(QueueDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("forced".equals(currentName)) {
                replaceEpisodeGroupItemsMethod.setForced(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return replaceEpisodeGroupItemsMethod;
    }
}
