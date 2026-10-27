package SOATemplateListInterface.v1_0;

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
public class InvalidateTemplatesMethodSerializer extends JsonSerializer<InvalidateTemplatesMethod> {
    public static final InvalidateTemplatesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidateTemplatesMethodSerializer invalidateTemplatesMethodSerializer = new InvalidateTemplatesMethodSerializer();
        INSTANCE = invalidateTemplatesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.InvalidateTemplatesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InvalidateTemplatesMethod.class, invalidateTemplatesMethodSerializer);
    }

    private InvalidateTemplatesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InvalidateTemplatesMethod invalidateTemplatesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (invalidateTemplatesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(invalidateTemplatesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InvalidateTemplatesMethod invalidateTemplatesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(invalidateTemplatesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(invalidateTemplatesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
