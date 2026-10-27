package SOAAppSyncInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ConditionElementSerializer extends JsonSerializer<ConditionElement> {
    public static final ConditionElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ConditionElementSerializer conditionElementSerializer = new ConditionElementSerializer();
        INSTANCE = conditionElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.ConditionElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ConditionElement.class, conditionElementSerializer);
    }

    private ConditionElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ConditionElement conditionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (conditionElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        boolean z = conditionElement instanceof TimeConditionElement;
        jsonGenerator.writeStartObject();
        if (!z) {
            serializeFields(conditionElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("SOAAppSyncInterface.v1_0#TimeConditionElement");
            TimeConditionElementSerializer.INSTANCE.serializeFields((TimeConditionElement) conditionElement, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void serializeFields(ConditionElement conditionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
