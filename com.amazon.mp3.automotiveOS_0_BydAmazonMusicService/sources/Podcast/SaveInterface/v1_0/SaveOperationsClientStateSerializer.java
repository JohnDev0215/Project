package Podcast.SaveInterface.v1_0;

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
public class SaveOperationsClientStateSerializer extends JsonSerializer<SaveOperationsClientState> {
    public static final SaveOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveOperationsClientStateSerializer saveOperationsClientStateSerializer = new SaveOperationsClientStateSerializer();
        INSTANCE = saveOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SaveOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SaveOperationsClientState.class, saveOperationsClientStateSerializer);
    }

    private SaveOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SaveOperationsClientState saveOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (saveOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(saveOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SaveOperationsClientState saveOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(saveOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
