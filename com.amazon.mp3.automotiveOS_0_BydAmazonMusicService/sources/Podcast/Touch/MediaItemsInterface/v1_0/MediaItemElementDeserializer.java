package Podcast.Touch.MediaItemsInterface.v1_0;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class MediaItemElementDeserializer extends JsonDeserializer<MediaItemElement> {
    public static final MediaItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MediaItemElementDeserializer mediaItemElementDeserializer = new MediaItemElementDeserializer();
        INSTANCE = mediaItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MediaItemElement.class, mediaItemElementDeserializer);
    }

    private MediaItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MediaItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.MediaItemsInterface.v1_0#MediaItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MediaItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MediaItemElement mediaItemElement = new MediaItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.IMAGE.equals(currentName)) {
                mediaItemElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                mediaItemElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("mediaId".equals(currentName)) {
                mediaItemElement.setMediaId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("bookmark".equals(currentName)) {
                mediaItemElement.setBookmark(BookmarkElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("durationMilliseconds".equals(currentName)) {
                mediaItemElement.setDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return mediaItemElement;
    }
}
