package Podcast.SaveInterface.v1_0;

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
public class SavesLastSyncTimeClientStateSerializer extends JsonSerializer<SavesLastSyncTimeClientState> {
    public static final SavesLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SavesLastSyncTimeClientStateSerializer savesLastSyncTimeClientStateSerializer = new SavesLastSyncTimeClientStateSerializer();
        INSTANCE = savesLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SavesLastSyncTimeClientState.class, savesLastSyncTimeClientStateSerializer);
    }

    private SavesLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SavesLastSyncTimeClientState savesLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (savesLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(savesLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SavesLastSyncTimeClientState savesLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(savesLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
