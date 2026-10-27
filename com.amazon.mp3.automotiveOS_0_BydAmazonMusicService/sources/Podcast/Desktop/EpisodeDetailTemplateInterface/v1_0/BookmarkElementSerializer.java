package Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0;

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
public class BookmarkElementSerializer extends JsonSerializer<BookmarkElement> {
    public static final BookmarkElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarkElementSerializer bookmarkElementSerializer = new BookmarkElementSerializer();
        INSTANCE = bookmarkElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.EpisodeDetailTemplateInterface.v1_0.BookmarkElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarkElement.class, bookmarkElementSerializer);
    }

    private BookmarkElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarkElement bookmarkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarkElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarkElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarkElement bookmarkElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onPauseSelected");
        JsonSerializer<List<Method>> jsonSerializer = MethodsSerializer.INSTANCE;
        jsonSerializer.serialize(bookmarkElement.getOnPauseSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onResumeSelected");
        jsonSerializer.serialize(bookmarkElement.getOnResumeSelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onPlaySelected");
        jsonSerializer.serialize(bookmarkElement.getOnPlaySelected(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("totalDurationMilliseconds");
        SimpleSerializers.serializeLong(bookmarkElement.getTotalDurationMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(bookmarkElement.getId(), jsonGenerator, serializerProvider);
    }
}
