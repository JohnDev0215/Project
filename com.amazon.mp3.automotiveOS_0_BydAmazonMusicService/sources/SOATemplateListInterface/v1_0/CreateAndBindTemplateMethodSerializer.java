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
public class CreateAndBindTemplateMethodSerializer extends JsonSerializer<CreateAndBindTemplateMethod> {
    public static final CreateAndBindTemplateMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CreateAndBindTemplateMethodSerializer createAndBindTemplateMethodSerializer = new CreateAndBindTemplateMethodSerializer();
        INSTANCE = createAndBindTemplateMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.CreateAndBindTemplateMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CreateAndBindTemplateMethod.class, createAndBindTemplateMethodSerializer);
    }

    private CreateAndBindTemplateMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CreateAndBindTemplateMethod createAndBindTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (createAndBindTemplateMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(createAndBindTemplateMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CreateAndBindTemplateMethod createAndBindTemplateMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("screenMode");
        SimpleSerializers.serializeString(createAndBindTemplateMethod.getScreenMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("template");
        TemplateSerializer.INSTANCE.serialize(createAndBindTemplateMethod.getTemplate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(createAndBindTemplateMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(createAndBindTemplateMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
