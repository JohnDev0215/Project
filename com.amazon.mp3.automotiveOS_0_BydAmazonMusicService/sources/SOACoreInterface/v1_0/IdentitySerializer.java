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
public class IdentitySerializer extends JsonSerializer<Identity> {
    public static final IdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        IdentitySerializer identitySerializer = new IdentitySerializer();
        INSTANCE = identitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.IdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Identity.class, identitySerializer);
    }

    private IdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Identity identity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (identity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(identity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Identity identity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("device");
        DeviceIdentitySerializer.INSTANCE.serialize(identity.getDevice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("account");
        AccountIdentitySerializer.INSTANCE.serialize(identity.getAccount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("request");
        RequestIdentitySerializer.INSTANCE.serialize(identity.getRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("application");
        ApplicationIdentitySerializer.INSTANCE.serialize(identity.getApplication(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("user");
        UserIdentitySerializer.INSTANCE.serialize(identity.getUser(), jsonGenerator, serializerProvider);
    }
}
