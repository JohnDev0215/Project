package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceEligibilitySerializer extends JsonSerializer<DeviceEligibility> {
    public static final DeviceEligibilitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DeviceEligibilitySerializer deviceEligibilitySerializer = new DeviceEligibilitySerializer();
        INSTANCE = deviceEligibilitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.DeviceEligibilitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DeviceEligibility.class, deviceEligibilitySerializer);
    }

    private DeviceEligibilitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DeviceEligibility deviceEligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (deviceEligibility == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(deviceEligibility, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DeviceEligibility deviceEligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(deviceEligibility.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eligible");
        SimpleSerializers.serializeBoolean(deviceEligibility.isEligible(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(deviceEligibility.getType(), jsonGenerator, serializerProvider);
    }
}
