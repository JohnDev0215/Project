package SOAV2CompatibilityInterface.v1_0;

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
public class ExtractResponseMethodSerializer extends JsonSerializer<ExtractResponseMethod> {
    public static final ExtractResponseMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ExtractResponseMethodSerializer extractResponseMethodSerializer = new ExtractResponseMethodSerializer();
        INSTANCE = extractResponseMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAV2CompatibilityInterface.v1_0.ExtractResponseMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ExtractResponseMethod.class, extractResponseMethodSerializer);
    }

    private ExtractResponseMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ExtractResponseMethod extractResponseMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (extractResponseMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(extractResponseMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ExtractResponseMethod extractResponseMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(extractResponseMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("content");
        SimpleSerializers.serializeString(extractResponseMethod.getContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(extractResponseMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
