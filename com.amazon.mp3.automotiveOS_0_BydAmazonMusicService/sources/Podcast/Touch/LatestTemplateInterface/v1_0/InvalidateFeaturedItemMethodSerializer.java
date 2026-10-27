package Podcast.Touch.LatestTemplateInterface.v1_0;

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
public class InvalidateFeaturedItemMethodSerializer extends JsonSerializer<InvalidateFeaturedItemMethod> {
    public static final InvalidateFeaturedItemMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidateFeaturedItemMethodSerializer invalidateFeaturedItemMethodSerializer = new InvalidateFeaturedItemMethodSerializer();
        INSTANCE = invalidateFeaturedItemMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvalidateFeaturedItemMethod.class, invalidateFeaturedItemMethodSerializer);
    }

    private InvalidateFeaturedItemMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvalidateFeaturedItemMethod invalidateFeaturedItemMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invalidateFeaturedItemMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invalidateFeaturedItemMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvalidateFeaturedItemMethod invalidateFeaturedItemMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(invalidateFeaturedItemMethod.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invalidateFeaturedItemMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invalidateFeaturedItemMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
