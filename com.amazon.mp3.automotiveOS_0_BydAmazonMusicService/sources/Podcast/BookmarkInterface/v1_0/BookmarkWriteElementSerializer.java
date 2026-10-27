package Podcast.BookmarkInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkWriteElementSerializer extends JsonSerializer<BookmarkWriteElement> {
    public static final BookmarkWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarkWriteElementSerializer bookmarkWriteElementSerializer = new BookmarkWriteElementSerializer();
        INSTANCE = bookmarkWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.BookmarkWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarkWriteElement.class, bookmarkWriteElementSerializer);
    }

    private BookmarkWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarkWriteElement bookmarkWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarkWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarkWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarkWriteElement bookmarkWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(bookmarkWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(bookmarkWriteElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("progressMilliSeconds");
        SimpleSerializers.serializeLong(bookmarkWriteElement.getProgressMilliSeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(bookmarkWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(bookmarkWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(bookmarkWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(bookmarkWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(bookmarkWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(bookmarkWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(bookmarkWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(bookmarkWriteElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(bookmarkWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
    }
}
