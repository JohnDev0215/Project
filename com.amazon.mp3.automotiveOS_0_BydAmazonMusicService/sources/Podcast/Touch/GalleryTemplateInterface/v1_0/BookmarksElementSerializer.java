package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BookmarksElementSerializer extends JsonSerializer<BookmarksElement> {
    public static final BookmarksElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarksElementSerializer bookmarksElementSerializer = new BookmarksElementSerializer();
        INSTANCE = bookmarksElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarksElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarksElement.class, bookmarksElementSerializer);
    }

    private BookmarksElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarksElement bookmarksElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarksElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarksElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarksElement bookmarksElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(bookmarksElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("header");
        SimpleSerializers.serializeString(bookmarksElement.getHeader(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("seeMoreText");
        SimpleSerializers.serializeString(bookmarksElement.getSeeMoreText(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onSeeMoreSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(bookmarksElement.getOnSeeMoreSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onViewed");
        jsonSerializer.serialize(bookmarksElement.getOnViewed(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onContentFirstViewed");
        jsonSerializer.serialize(bookmarksElement.getOnContentFirstViewed(), jsonGenerator, serializerProvider);
    }
}
