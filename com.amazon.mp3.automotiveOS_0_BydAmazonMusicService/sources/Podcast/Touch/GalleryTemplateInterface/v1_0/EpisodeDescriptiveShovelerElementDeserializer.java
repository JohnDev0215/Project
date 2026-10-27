package Podcast.Touch.GalleryTemplateInterface.v1_0;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElementsDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
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
public class EpisodeDescriptiveShovelerElementDeserializer extends JsonDeserializer<EpisodeDescriptiveShovelerElement> {
    public static final EpisodeDescriptiveShovelerElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EpisodeDescriptiveShovelerElementDeserializer episodeDescriptiveShovelerElementDeserializer = new EpisodeDescriptiveShovelerElementDeserializer();
        INSTANCE = episodeDescriptiveShovelerElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EpisodeDescriptiveShovelerElement.class, episodeDescriptiveShovelerElementDeserializer);
    }

    private EpisodeDescriptiveShovelerElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EpisodeDescriptiveShovelerElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.GalleryTemplateInterface.v1_0#EpisodeDescriptiveShovelerElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EpisodeDescriptiveShovelerElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EpisodeDescriptiveShovelerElement episodeDescriptiveShovelerElement = new EpisodeDescriptiveShovelerElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("header".equals(currentName)) {
                episodeDescriptiveShovelerElement.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                episodeDescriptiveShovelerElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.DO_SEE_MORE_VALUE.equals(currentName)) {
                episodeDescriptiveShovelerElement.setSeeMore(EpisodeDescriptiveSeeMoreItemElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                episodeDescriptiveShovelerElement.setItems(EpisodeRowItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                episodeDescriptiveShovelerElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onContentFirstViewed".equals(currentName)) {
                episodeDescriptiveShovelerElement.setOnContentFirstViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return episodeDescriptiveShovelerElement;
    }
}
