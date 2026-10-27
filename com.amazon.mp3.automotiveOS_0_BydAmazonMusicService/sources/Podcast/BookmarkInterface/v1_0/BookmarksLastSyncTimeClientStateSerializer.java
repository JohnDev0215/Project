package Podcast.BookmarkInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BookmarksLastSyncTimeClientStateSerializer extends JsonSerializer<BookmarksLastSyncTimeClientState> {
    public static final BookmarksLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BookmarksLastSyncTimeClientStateSerializer bookmarksLastSyncTimeClientStateSerializer = new BookmarksLastSyncTimeClientStateSerializer();
        INSTANCE = bookmarksLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BookmarksLastSyncTimeClientState.class, bookmarksLastSyncTimeClientStateSerializer);
    }

    private BookmarksLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BookmarksLastSyncTimeClientState bookmarksLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bookmarksLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bookmarksLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BookmarksLastSyncTimeClientState bookmarksLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(bookmarksLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
