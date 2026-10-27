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
public class CreateTemplateMethodSerializer extends JsonSerializer<CreateTemplateMethod> {
    public static final CreateTemplateMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CreateTemplateMethodSerializer createTemplateMethodSerializer = new CreateTemplateMethodSerializer();
        INSTANCE = createTemplateMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.CreateTemplateMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CreateTemplateMethod.class, createTemplateMethodSerializer);
    }

    private CreateTemplateMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CreateTemplateMethod createTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (createTemplateMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(createTemplateMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CreateTemplateMethod createTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(createTemplateMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("screenMode");
        SimpleSerializers.serializeString(createTemplateMethod.getScreenMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(createTemplateMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("template");
        TemplateSerializer.INSTANCE.serialize(createTemplateMethod.getTemplate(), jsonGenerator, serializerProvider);
    }
}
