package SOACoreInterface.v1_0;

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
public class RequestSerializer extends JsonSerializer<Request> {
    public static final RequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestSerializer requestSerializer = new RequestSerializer();
        INSTANCE = requestSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.RequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Request.class, requestSerializer);
    }

    private RequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Request request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (request == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(request, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Request request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("clientStates");
        ClientStatesSerializer.INSTANCE.serialize(request.getClientStates(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("preset");
        SimpleSerializers.serializeString(request.getPreset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("extra");
        ExtraSerializer.INSTANCE.serialize(request.getExtra(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identity");
        IdentitySerializer.INSTANCE.serialize(request.getIdentity(), jsonGenerator, serializerProvider);
    }
}
