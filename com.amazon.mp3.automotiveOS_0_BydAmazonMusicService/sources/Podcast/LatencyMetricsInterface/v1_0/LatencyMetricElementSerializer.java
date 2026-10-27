package Podcast.LatencyMetricsInterface.v1_0;

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
public class LatencyMetricElementSerializer extends JsonSerializer<LatencyMetricElement> {
    public static final LatencyMetricElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LatencyMetricElementSerializer latencyMetricElementSerializer = new LatencyMetricElementSerializer();
        INSTANCE = latencyMetricElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LatencyMetricElement.class, latencyMetricElementSerializer);
    }

    private LatencyMetricElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LatencyMetricElement latencyMetricElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (latencyMetricElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(latencyMetricElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LatencyMetricElement latencyMetricElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("pageType");
        SimpleSerializers.serializeString(latencyMetricElement.getPageType(), jsonGenerator, serializerProvider);
    }
}
