package Podcast.Touch.PTCTemplateInterface.v1_0;

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
public class InvalidatePTCCacheMethodSerializer extends JsonSerializer<InvalidatePTCCacheMethod> {
    public static final InvalidatePTCCacheMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidatePTCCacheMethodSerializer invalidatePTCCacheMethodSerializer = new InvalidatePTCCacheMethodSerializer();
        INSTANCE = invalidatePTCCacheMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvalidatePTCCacheMethod.class, invalidatePTCCacheMethodSerializer);
    }

    private InvalidatePTCCacheMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvalidatePTCCacheMethod invalidatePTCCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invalidatePTCCacheMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invalidatePTCCacheMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvalidatePTCCacheMethod invalidatePTCCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invalidatePTCCacheMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invalidatePTCCacheMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
