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
public class BindTemplateMethodSerializer extends JsonSerializer<BindTemplateMethod> {
    public static final BindTemplateMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BindTemplateMethodSerializer bindTemplateMethodSerializer = new BindTemplateMethodSerializer();
        INSTANCE = bindTemplateMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.BindTemplateMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(BindTemplateMethod.class, bindTemplateMethodSerializer);
    }

    private BindTemplateMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull BindTemplateMethod bindTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (bindTemplateMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(bindTemplateMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(BindTemplateMethod bindTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(bindTemplateMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("template");
        TemplateSerializer.INSTANCE.serialize(bindTemplateMethod.getTemplate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(bindTemplateMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
