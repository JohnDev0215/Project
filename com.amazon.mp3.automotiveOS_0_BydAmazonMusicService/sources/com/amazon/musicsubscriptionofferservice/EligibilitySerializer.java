package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class EligibilitySerializer extends JsonSerializer<Eligibility> {
    public static final EligibilitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EligibilitySerializer eligibilitySerializer = new EligibilitySerializer();
        INSTANCE = eligibilitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.EligibilitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Eligibility.class, eligibilitySerializer);
    }

    private EligibilitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Eligibility eligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (eligibility == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(eligibility, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Eligibility eligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("eligible");
        SimpleSerializers.serializeBoolean(eligibility.isEligible(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("reasons");
        EligibilityReasonsSerializer.INSTANCE.serialize(eligibility.getReasons(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("alternateDevices");
        DeviceEligibilitiesSerializer.INSTANCE.serialize(eligibility.getAlternateDevices(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requiresAlternateDevice");
        SimpleSerializers.serializeBoolean(eligibility.isRequiresAlternateDevice(), jsonGenerator, serializerProvider);
    }
}
