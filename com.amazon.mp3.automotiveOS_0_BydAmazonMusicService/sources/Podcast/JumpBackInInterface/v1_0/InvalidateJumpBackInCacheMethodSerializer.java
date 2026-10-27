package Podcast.JumpBackInInterface.v1_0;

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
public class InvalidateJumpBackInCacheMethodSerializer extends JsonSerializer<InvalidateJumpBackInCacheMethod> {
    public static final InvalidateJumpBackInCacheMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidateJumpBackInCacheMethodSerializer invalidateJumpBackInCacheMethodSerializer = new InvalidateJumpBackInCacheMethodSerializer();
        INSTANCE = invalidateJumpBackInCacheMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvalidateJumpBackInCacheMethod.class, invalidateJumpBackInCacheMethodSerializer);
    }

    private InvalidateJumpBackInCacheMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvalidateJumpBackInCacheMethod invalidateJumpBackInCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invalidateJumpBackInCacheMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invalidateJumpBackInCacheMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvalidateJumpBackInCacheMethod invalidateJumpBackInCacheMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invalidateJumpBackInCacheMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invalidateJumpBackInCacheMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
