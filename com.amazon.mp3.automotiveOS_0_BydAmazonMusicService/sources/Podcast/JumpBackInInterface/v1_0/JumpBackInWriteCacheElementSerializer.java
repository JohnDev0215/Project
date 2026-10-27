package Podcast.JumpBackInInterface.v1_0;

import SOACoreInterface.v1_0.MethodsSerializer;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInWriteCacheElementSerializer extends JsonSerializer<JumpBackInWriteCacheElement> {
    public static final JumpBackInWriteCacheElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInWriteCacheElementSerializer jumpBackInWriteCacheElementSerializer = new JumpBackInWriteCacheElementSerializer();
        INSTANCE = jumpBackInWriteCacheElementSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(JumpBackInWriteCacheElement.class, jumpBackInWriteCacheElementSerializer);
    }

    private JumpBackInWriteCacheElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull JumpBackInWriteCacheElement jumpBackInWriteCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (jumpBackInWriteCacheElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(jumpBackInWriteCacheElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(JumpBackInWriteCacheElement jumpBackInWriteCacheElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ContextMappingConstants.ELEMENTS);
        JumpBackInCacheItemElementsSerializer.INSTANCE.serialize(jumpBackInWriteCacheElement.getElements(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("onInvalidated");
        MethodsSerializer.INSTANCE.serialize(jumpBackInWriteCacheElement.getOnInvalidated(), jsonGenerator, serializerProvider);
    }
}
