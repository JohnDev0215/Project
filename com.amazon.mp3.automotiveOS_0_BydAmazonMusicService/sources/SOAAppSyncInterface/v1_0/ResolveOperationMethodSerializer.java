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
public class ResolveOperationMethodSerializer extends JsonSerializer<ResolveOperationMethod> {
    public static final ResolveOperationMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResolveOperationMethodSerializer resolveOperationMethodSerializer = new ResolveOperationMethodSerializer();
        INSTANCE = resolveOperationMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.ResolveOperationMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ResolveOperationMethod.class, resolveOperationMethodSerializer);
    }

    private ResolveOperationMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ResolveOperationMethod resolveOperationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (resolveOperationMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(resolveOperationMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ResolveOperationMethod resolveOperationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("operations");
        StringsSerializer.INSTANCE.serialize(resolveOperationMethod.getOperations(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(resolveOperationMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(resolveOperationMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
