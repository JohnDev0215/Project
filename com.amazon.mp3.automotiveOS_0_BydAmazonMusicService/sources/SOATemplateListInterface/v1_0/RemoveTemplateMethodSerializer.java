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
public class RemoveTemplateMethodSerializer extends JsonSerializer<RemoveTemplateMethod> {
    public static final RemoveTemplateMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RemoveTemplateMethodSerializer removeTemplateMethodSerializer = new RemoveTemplateMethodSerializer();
        INSTANCE = removeTemplateMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.RemoveTemplateMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RemoveTemplateMethod.class, removeTemplateMethodSerializer);
    }

    private RemoveTemplateMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RemoveTemplateMethod removeTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (removeTemplateMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(removeTemplateMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RemoveTemplateMethod removeTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(removeTemplateMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(removeTemplateMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
