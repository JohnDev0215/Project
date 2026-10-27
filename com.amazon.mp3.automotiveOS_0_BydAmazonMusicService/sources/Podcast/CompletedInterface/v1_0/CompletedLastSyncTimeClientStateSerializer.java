package Podcast.CompletedInterface.v1_0;

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
public class CompletedLastSyncTimeClientStateSerializer extends JsonSerializer<CompletedLastSyncTimeClientState> {
    public static final CompletedLastSyncTimeClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompletedLastSyncTimeClientStateSerializer completedLastSyncTimeClientStateSerializer = new CompletedLastSyncTimeClientStateSerializer();
        INSTANCE = completedLastSyncTimeClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompletedLastSyncTimeClientState.class, completedLastSyncTimeClientStateSerializer);
    }

    private CompletedLastSyncTimeClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompletedLastSyncTimeClientState completedLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (completedLastSyncTimeClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(completedLastSyncTimeClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompletedLastSyncTimeClientState completedLastSyncTimeClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("lastSyncTime");
        SimpleSerializers.serializeLong(completedLastSyncTimeClientState.getLastSyncTime(), jsonGenerator, serializerProvider);
    }
}
