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
public class AlternateDeviceSerializer extends JsonSerializer<AlternateDevice> {
    public static final AlternateDeviceSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlternateDeviceSerializer alternateDeviceSerializer = new AlternateDeviceSerializer();
        INSTANCE = alternateDeviceSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.AlternateDeviceSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AlternateDevice.class, alternateDeviceSerializer);
    }

    private AlternateDeviceSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AlternateDevice alternateDevice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (alternateDevice == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(alternateDevice, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AlternateDevice alternateDevice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("id");
        SimpleSerializers.serializeString(alternateDevice.getId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(alternateDevice.getType(), jsonGenerator, serializerProvider);
    }
}
