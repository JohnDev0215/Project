package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACacheInterface.v1_0.WriteCacheElementSerializer;
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
public class WritePTCCacheMethodSerializer extends JsonSerializer<WritePTCCacheMethod> {
    public static final WritePTCCacheMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WritePTCCacheMethodSerializer writePTCCacheMethodSerializer = new WritePTCCacheMethodSerializer();
        INSTANCE = writePTCCacheMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WritePTCCacheMethod.class, writePTCCacheMethodSerializer);
    }

    private WritePTCCacheMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WritePTCCacheMethod writePTCCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writePTCCacheMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(writePTCCacheMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WritePTCCacheMethod writePTCCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("element");
        WriteCacheElementSerializer.INSTANCE.serialize(writePTCCacheMethod.getElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(writePTCCacheMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(writePTCCacheMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
