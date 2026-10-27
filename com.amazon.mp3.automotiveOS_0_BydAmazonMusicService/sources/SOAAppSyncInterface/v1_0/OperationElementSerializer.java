package SOAAppSyncInterface.v1_0;

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
public class OperationElementSerializer extends JsonSerializer<OperationElement> {
    public static final OperationElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        OperationElementSerializer operationElementSerializer = new OperationElementSerializer();
        INSTANCE = operationElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.OperationElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(OperationElement.class, operationElementSerializer);
    }

    private OperationElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull OperationElement operationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (operationElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(operationElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(OperationElement operationElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("condition");
        ConditionElementSerializer.INSTANCE.serialize(operationElement.getCondition(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(operationElement.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("element");
        WriteElementSerializer.INSTANCE.serialize(operationElement.getElement(), jsonGenerator, serializerProvider);
    }
}
