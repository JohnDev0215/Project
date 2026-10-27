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
public class StartMethodSerializer extends JsonSerializer<StartMethod> {
    public static final StartMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StartMethodSerializer startMethodSerializer = new StartMethodSerializer();
        INSTANCE = startMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.StartMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(StartMethod.class, startMethodSerializer);
    }

    private StartMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull StartMethod startMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (startMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(startMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(StartMethod startMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(startMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(startMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
