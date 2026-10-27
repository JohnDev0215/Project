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
public class TimeConditionElementSerializer extends JsonSerializer<TimeConditionElement> {
    public static final TimeConditionElementSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TimeConditionElementSerializer timeConditionElementSerializer = new TimeConditionElementSerializer();
        INSTANCE = timeConditionElementSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.TimeConditionElementSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TimeConditionElement.class, timeConditionElementSerializer);
    }

    private TimeConditionElementSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TimeConditionElement timeConditionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (timeConditionElement == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(timeConditionElement, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TimeConditionElement timeConditionElement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("updatedTime");
        SimpleSerializers.serializePrimitiveLong(timeConditionElement.getUpdatedTime(), jsonGenerator, serializerProvider);
    }
}
