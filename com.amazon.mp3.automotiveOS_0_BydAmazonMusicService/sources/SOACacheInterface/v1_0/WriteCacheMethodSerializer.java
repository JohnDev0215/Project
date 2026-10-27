package SOACacheInterface.v1_0;

import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethodSerializer;
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
public class WriteCacheMethodSerializer extends JsonSerializer<WriteCacheMethod> {
    public static final WriteCacheMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteCacheMethodSerializer writeCacheMethodSerializer = new WriteCacheMethodSerializer();
        INSTANCE = writeCacheMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACacheInterface.v1_0.WriteCacheMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteCacheMethod.class, writeCacheMethodSerializer);
    }

    private WriteCacheMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteCacheMethod writeCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeCacheMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        boolean z = writeCacheMethod instanceof WritePTCCacheMethod;
        jsonGenerator.writeStartObject();
        if (!z) {
            serializeFields(writeCacheMethod, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("Podcast.Touch.PTCTemplateInterface.v1_0#WritePTCCacheMethod");
            WritePTCCacheMethodSerializer.INSTANCE.serializeFields((WritePTCCacheMethod) writeCacheMethod, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void serializeFields(WriteCacheMethod writeCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("element");
        WriteCacheElementSerializer.INSTANCE.serialize(writeCacheMethod.getElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(writeCacheMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(writeCacheMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
