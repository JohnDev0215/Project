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
public class RetryOperationMethodSerializer extends JsonSerializer<RetryOperationMethod> {
    public static final RetryOperationMethodSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetryOperationMethodSerializer retryOperationMethodSerializer = new RetryOperationMethodSerializer();
        INSTANCE = retryOperationMethodSerializer;
        SimpleModule simpleModule = new SimpleModule("SOAAppSyncInterface.v1_0.RetryOperationMethodSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetryOperationMethod.class, retryOperationMethodSerializer);
    }

    private RetryOperationMethodSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetryOperationMethod retryOperationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retryOperationMethod == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retryOperationMethod, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetryOperationMethod retryOperationMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("queue");
        QueueSerializer.INSTANCE.serialize(retryOperationMethod.getQueue(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("operations");
        StringsSerializer.INSTANCE.serialize(retryOperationMethod.getOperations(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forced");
        SimpleSerializers.serializeBoolean(retryOperationMethod.isForced(), jsonGenerator, serializerProvider);
    }
}
