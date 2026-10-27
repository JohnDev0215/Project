package Podcast.BookmarkInterface.v1_0;

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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkWriteElementDeserializer extends JsonDeserializer<BookmarkWriteElement> {
    public static final BookmarkWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarkWriteElementDeserializer bookmarkWriteElementDeserializer = new BookmarkWriteElementDeserializer();
        INSTANCE = bookmarkWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.BookmarkWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(BookmarkWriteElement.class, bookmarkWriteElementDeserializer);
    }

    private BookmarkWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public BookmarkWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.BookmarkInterface.v1_0#BookmarkWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public BookmarkWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        BookmarkWriteElement bookmarkWriteElement = new BookmarkWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastId".equals(currentName)) {
                bookmarkWriteElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("totalDurationMilliseconds".equals(currentName)) {
                bookmarkWriteElement.setTotalDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("progressMilliSeconds".equals(currentName)) {
                bookmarkWriteElement.setProgressMilliSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                bookmarkWriteElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                bookmarkWriteElement.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                bookmarkWriteElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                bookmarkWriteElement.setAuthors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastImage".equals(currentName)) {
                bookmarkWriteElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastTitle".equals(currentName)) {
                bookmarkWriteElement.setPodcastTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                bookmarkWriteElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                bookmarkWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                bookmarkWriteElement.setSeasonNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return bookmarkWriteElement;
    }
}
