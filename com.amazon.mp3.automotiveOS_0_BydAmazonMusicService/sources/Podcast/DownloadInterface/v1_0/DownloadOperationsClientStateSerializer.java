package Podcast.DownloadInterface.v1_0;

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
public class DownloadOperationsClientStateSerializer extends JsonSerializer<DownloadOperationsClientState> {
    public static final DownloadOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DownloadOperationsClientStateSerializer downloadOperationsClientStateSerializer = new DownloadOperationsClientStateSerializer();
        INSTANCE = downloadOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DownloadInterface.v1_0.DownloadOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DownloadOperationsClientState.class, downloadOperationsClientStateSerializer);
    }

    private DownloadOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DownloadOperationsClientState downloadOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (downloadOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(downloadOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DownloadOperationsClientState downloadOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(downloadOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
