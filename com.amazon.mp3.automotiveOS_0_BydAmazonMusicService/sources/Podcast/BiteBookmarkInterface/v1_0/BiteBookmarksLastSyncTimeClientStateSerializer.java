package Podcast.BiteBookmarkInterface.v1_0;

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
public class BiteBookmarksLastSyncTimeClientStateSerializer extends JsonSerializer<BiteBookmarksLastSyncTimeClientState> {
    public static final BiteBookmarksLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BiteBookmarksLastSyncTimeClientStateSerializer biteBookmarksLastSyncTimeClientStateSerializer = new BiteBookmarksLastSyncTimeClientStateSerializer();
        INSTANCE = biteBookmarksLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.BiteBookmarkInterface.v1_0.BiteBookmarksLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BiteBookmarksLastSyncTimeClientState.class, biteBookmarksLastSyncTimeClientStateSerializer);
    }

    private BiteBookmarksLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BiteBookmarksLastSyncTimeClientState biteBookmarksLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (biteBookmarksLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(biteBookmarksLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BiteBookmarksLastSyncTimeClientState biteBookmarksLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(biteBookmarksLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
