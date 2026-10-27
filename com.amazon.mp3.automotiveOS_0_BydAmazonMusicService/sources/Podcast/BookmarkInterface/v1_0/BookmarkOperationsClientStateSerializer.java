package Podcast.BookmarkInterface.v1_0;

import SOAAppSyncInterface.v1_0.OperationsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkOperationsClientStateSerializer extends JsonSerializer<BookmarkOperationsClientState> {
    public static final BookmarkOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarkOperationsClientStateSerializer bookmarkOperationsClientStateSerializer = new BookmarkOperationsClientStateSerializer();
        INSTANCE = bookmarkOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.BookmarkOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarkOperationsClientState.class, bookmarkOperationsClientStateSerializer);
    }

    private BookmarkOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarkOperationsClientState bookmarkOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarkOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarkOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarkOperationsClientState bookmarkOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(bookmarkOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
