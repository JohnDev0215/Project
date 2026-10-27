package SOACoreInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class QueueSerializer extends JsonSerializer<Queue> {
    public static final QueueSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        QueueSerializer queueSerializer = new QueueSerializer();
        INSTANCE = queueSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.QueueSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Queue.class, queueSerializer);
    }

    private QueueSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Queue queue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (queue == null) {
            jsonGenerator.writeNull();
            return;
        }
        if (queue instanceof MultiThreadedQueue) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOACoreInterface.v1_0#MultiThreadedQueue");
            MultiThreadedQueueSerializer.INSTANCE.serializeFields((MultiThreadedQueue) queue, jsonGenerator, serializerProvider);
        } else {
            boolean z = queue instanceof SingleThreadedQueue;
            jsonGenerator.writeStartObject();
            if (!z) {
                serializeFields(queue, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndObject();
                return;
            } else {
                jsonGenerator.writeFieldName("__type");
                jsonGenerator.writeString("SOACoreInterface.v1_0#SingleThreadedQueue");
                SingleThreadedQueueSerializer.INSTANCE.serializeFields((SingleThreadedQueue) queue, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Queue queue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
