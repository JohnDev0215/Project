package Podcast.CompletedInterface.v1_0;

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
public class CompletedOperationsClientStateSerializer extends JsonSerializer<CompletedOperationsClientState> {
    public static final CompletedOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CompletedOperationsClientStateSerializer completedOperationsClientStateSerializer = new CompletedOperationsClientStateSerializer();
        INSTANCE = completedOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CompletedInterface.v1_0.CompletedOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CompletedOperationsClientState.class, completedOperationsClientStateSerializer);
    }

    private CompletedOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CompletedOperationsClientState completedOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (completedOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(completedOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CompletedOperationsClientState completedOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(completedOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
