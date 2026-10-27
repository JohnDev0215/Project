package SOAAppSyncInterface.v1_0;

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
public class WriteMethodSerializer extends JsonSerializer<WriteMethod> {
    public static final WriteMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteMethodSerializer writeMethodSerializer = new WriteMethodSerializer();
        INSTANCE = writeMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.WriteMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteMethod.class, writeMethodSerializer);
    }

    private WriteMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteMethod writeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(writeMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WriteMethod writeMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("condition");
        ConditionElementSerializer.INSTANCE.serialize(writeMethod.getCondition(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(writeMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(writeMethod.isForced(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("element");
        WriteElementsSerializer.INSTANCE.serialize(writeMethod.getElement(), jsonGenerator, serializerProvider);
    }
}
