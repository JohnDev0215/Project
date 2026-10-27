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
public class WriteLocalMethodSerializer extends JsonSerializer<WriteLocalMethod> {
    public static final WriteLocalMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteLocalMethodSerializer writeLocalMethodSerializer = new WriteLocalMethodSerializer();
        INSTANCE = writeLocalMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.WriteLocalMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(WriteLocalMethod.class, writeLocalMethodSerializer);
    }

    private WriteLocalMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull WriteLocalMethod writeLocalMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (writeLocalMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(writeLocalMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(WriteLocalMethod writeLocalMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("condition");
        ConditionElementSerializer.INSTANCE.serialize(writeLocalMethod.getCondition(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(writeLocalMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("element");
        WriteElementsSerializer.INSTANCE.serialize(writeLocalMethod.getElement(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(writeLocalMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
