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
public class SingleThreadedQueueSerializer extends JsonSerializer<SingleThreadedQueue> {
    public static final SingleThreadedQueueSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SingleThreadedQueueSerializer singleThreadedQueueSerializer = new SingleThreadedQueueSerializer();
        INSTANCE = singleThreadedQueueSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.SingleThreadedQueueSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SingleThreadedQueue.class, singleThreadedQueueSerializer);
    }

    private SingleThreadedQueueSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SingleThreadedQueue singleThreadedQueue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (singleThreadedQueue == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(singleThreadedQueue, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SingleThreadedQueue singleThreadedQueue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(singleThreadedQueue.getId(), jsonGenerator, serializerProvider);
    }
}
