package Podcast.UIMetricsInterface.v1_0;

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
public class UIMetricsOperationsClientStateSerializer extends JsonSerializer<UIMetricsOperationsClientState> {
    public static final UIMetricsOperationsClientStateSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UIMetricsOperationsClientStateSerializer uIMetricsOperationsClientStateSerializer = new UIMetricsOperationsClientStateSerializer();
        INSTANCE = uIMetricsOperationsClientStateSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.UIMetricsInterface.v1_0.UIMetricsOperationsClientStateSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UIMetricsOperationsClientState.class, uIMetricsOperationsClientStateSerializer);
    }

    private UIMetricsOperationsClientStateSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UIMetricsOperationsClientState uIMetricsOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (uIMetricsOperationsClientState == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(uIMetricsOperationsClientState, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UIMetricsOperationsClientState uIMetricsOperationsClientState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        OperationsSerializer.INSTANCE.serialize(uIMetricsOperationsClientState.getOperations(), jsonGenerator, serializerProvider);
    }
}
