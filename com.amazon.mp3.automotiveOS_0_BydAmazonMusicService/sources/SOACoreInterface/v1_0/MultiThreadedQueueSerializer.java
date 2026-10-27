package SOACoreInterface.v1_0;

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
public class MultiThreadedQueueSerializer extends JsonSerializer<MultiThreadedQueue> {
    public static final MultiThreadedQueueSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MultiThreadedQueueSerializer multiThreadedQueueSerializer = new MultiThreadedQueueSerializer();
        INSTANCE = multiThreadedQueueSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.MultiThreadedQueueSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MultiThreadedQueue.class, multiThreadedQueueSerializer);
    }

    private MultiThreadedQueueSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MultiThreadedQueue multiThreadedQueue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (multiThreadedQueue == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(multiThreadedQueue, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MultiThreadedQueue multiThreadedQueue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(multiThreadedQueue.getId(), jsonGenerator, serializerProvider);
    }
}
