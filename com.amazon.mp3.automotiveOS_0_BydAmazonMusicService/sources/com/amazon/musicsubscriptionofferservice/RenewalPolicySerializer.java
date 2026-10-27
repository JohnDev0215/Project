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
public class RenewalPolicySerializer extends JsonSerializer<RenewalPolicy> {
    public static final RenewalPolicySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RenewalPolicySerializer renewalPolicySerializer = new RenewalPolicySerializer();
        INSTANCE = renewalPolicySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RenewalPolicySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RenewalPolicy.class, renewalPolicySerializer);
    }

    private RenewalPolicySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RenewalPolicy renewalPolicy, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (renewalPolicy == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(renewalPolicy, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RenewalPolicy renewalPolicy, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("autoRenew");
        SimpleSerializers.serializeBoolean(renewalPolicy.isAutoRenew(), jsonGenerator, serializerProvider);
    }
}
