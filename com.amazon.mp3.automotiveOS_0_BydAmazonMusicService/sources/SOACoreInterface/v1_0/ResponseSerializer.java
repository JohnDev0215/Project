package SOACoreInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ResponseSerializer extends JsonSerializer<Response> {
    public static final ResponseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResponseSerializer responseSerializer = new ResponseSerializer();
        INSTANCE = responseSerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.ResponseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Response.class, responseSerializer);
    }

    private ResponseSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Response response, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (response == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(response, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Response response, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("methods");
        MethodsSerializer.INSTANCE.serialize(response.getMethods(), jsonGenerator, serializerProvider);
    }
}
