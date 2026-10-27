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
public class DeviceIdentitySerializer extends JsonSerializer<DeviceIdentity> {
    public static final DeviceIdentitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceIdentitySerializer deviceIdentitySerializer = new DeviceIdentitySerializer();
        INSTANCE = deviceIdentitySerializer;
        SimpleModule simpleModule = new SimpleModule("SOACoreInterface.v1_0.DeviceIdentitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DeviceIdentity.class, deviceIdentitySerializer);
    }

    private DeviceIdentitySerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DeviceIdentity deviceIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (deviceIdentity == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(deviceIdentity, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DeviceIdentity deviceIdentity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("language");
        SimpleSerializers.serializeString(deviceIdentity.getLanguage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(deviceIdentity.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("timeZone");
        SimpleSerializers.serializeString(deviceIdentity.getTimeZone(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("typeId");
        SimpleSerializers.serializeString(deviceIdentity.getTypeId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("manufacturer");
        SimpleSerializers.serializeString(deviceIdentity.getManufacturer(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("width");
        SimpleSerializers.serializeString(deviceIdentity.getWidth(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("model");
        SimpleSerializers.serializeString(deviceIdentity.getModel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("osVersion");
        SimpleSerializers.serializeString(deviceIdentity.getOsVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("height");
        SimpleSerializers.serializeString(deviceIdentity.getHeight(), jsonGenerator, serializerProvider);
    }
}
