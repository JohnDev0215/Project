package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class RequestIdentitySerializer extends JsonSerializer<RequestIdentity> {
    public static final RequestIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestIdentitySerializer requestIdentitySerializer = new RequestIdentitySerializer();
        INSTANCE = requestIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.RequestIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RequestIdentity.class, requestIdentitySerializer);
    }

    private RequestIdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RequestIdentity requestIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (requestIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(requestIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RequestIdentity requestIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("domain");
        SimpleSerializers.serializeString(requestIdentity.getDomain(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(requestIdentity.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(requestIdentity.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(requestIdentity.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(TetheredMessageKey.timestamp);
        SimpleSerializers.serializePrimitiveLong(requestIdentity.getTimestamp(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("csrf");
        CsrfSerializer.INSTANCE.serialize(requestIdentity.getCsrf(), jsonGenerator, serializerProvider);
    }
}
