package Podcast.UIMetricsInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import SOACoreInterface.v1_0.QueueSerializer;
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
public class SetOnReportOperationsMethodSerializer extends JsonSerializer<SetOnReportOperationsMethod> {
    public static final SetOnReportOperationsMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SetOnReportOperationsMethodSerializer setOnReportOperationsMethodSerializer = new SetOnReportOperationsMethodSerializer();
        INSTANCE = setOnReportOperationsMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.UIMetricsInterface.v1_0.SetOnReportOperationsMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SetOnReportOperationsMethod.class, setOnReportOperationsMethodSerializer);
    }

    private SetOnReportOperationsMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SetOnReportOperationsMethod setOnReportOperationsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (setOnReportOperationsMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(setOnReportOperationsMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SetOnReportOperationsMethod setOnReportOperationsMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("onReportOperations");
        MethodsSerializer.INSTANCE.serialize(setOnReportOperationsMethod.getOnReportOperations(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(setOnReportOperationsMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(setOnReportOperationsMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
