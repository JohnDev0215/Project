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
public class ClearTemplatesMethodSerializer extends JsonSerializer<ClearTemplatesMethod> {
    public static final ClearTemplatesMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ClearTemplatesMethodSerializer clearTemplatesMethodSerializer = new ClearTemplatesMethodSerializer();
        INSTANCE = clearTemplatesMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOATemplateListInterface.v1_0.ClearTemplatesMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ClearTemplatesMethod.class, clearTemplatesMethodSerializer);
    }

    private ClearTemplatesMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ClearTemplatesMethod clearTemplatesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (clearTemplatesMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(clearTemplatesMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ClearTemplatesMethod clearTemplatesMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(clearTemplatesMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(clearTemplatesMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
