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
public class BookmarkMetadataWriteElementSerializer extends JsonSerializer<BookmarkMetadataWriteElement> {
    public static final BookmarkMetadataWriteElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarkMetadataWriteElementSerializer bookmarkMetadataWriteElementSerializer = new BookmarkMetadataWriteElementSerializer();
        INSTANCE = bookmarkMetadataWriteElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.BookmarkMetadataWriteElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarkMetadataWriteElement.class, bookmarkMetadataWriteElementSerializer);
    }

    private BookmarkMetadataWriteElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarkMetadataWriteElement bookmarkMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarkMetadataWriteElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarkMetadataWriteElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarkMetadataWriteElement bookmarkMetadataWriteElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.IMAGE);
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authors");
        StringsSerializer.INSTANCE.serialize(bookmarkMetadataWriteElement.getAuthors(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastId");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getPodcastId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastTitle");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getPodcastTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("title");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(MediaTrack.ROLE_DESCRIPTION);
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getDescription(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("podcastImage");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getPodcastImage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("publishDate");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getPublishDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seasonNumber");
        SimpleSerializers.serializeInteger(bookmarkMetadataWriteElement.getSeasonNumber(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(bookmarkMetadataWriteElement.getId(), jsonGenerator, serializerProvider);
    }
}
