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
public class ApplicationIdentitySerializer extends JsonSerializer<ApplicationIdentity> {
    public static final ApplicationIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ApplicationIdentitySerializer applicationIdentitySerializer = new ApplicationIdentitySerializer();
        INSTANCE = applicationIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.ApplicationIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ApplicationIdentity.class, applicationIdentitySerializer);
    }

    private ApplicationIdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ApplicationIdentity applicationIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (applicationIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(applicationIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ApplicationIdentity applicationIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(applicationIdentity.getVersion(), jsonGenerator, serializerProvider);
    }
}
