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
public class UserIdentitySerializer extends JsonSerializer<UserIdentity> {
    public static final UserIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        UserIdentitySerializer userIdentitySerializer = new UserIdentitySerializer();
        INSTANCE = userIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.UserIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(UserIdentity.class, userIdentitySerializer);
    }

    private UserIdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull UserIdentity userIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (userIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(userIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(UserIdentity userIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(userIdentity.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authentication");
        SimpleSerializers.serializeString(userIdentity.getAuthentication(), jsonGenerator, serializerProvider);
    }
}
