package com.amazon.stratus;

import com.amazon.cirrus.shared.model.TermsOfUseSerializer;
import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AcceptTermsOfUseRequestSerializer extends JsonSerializer<AcceptTermsOfUseRequest> {
    public static final AcceptTermsOfUseRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AcceptTermsOfUseRequestSerializer acceptTermsOfUseRequestSerializer = new AcceptTermsOfUseRequestSerializer();
        INSTANCE = acceptTermsOfUseRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.AcceptTermsOfUseRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AcceptTermsOfUseRequest.class, acceptTermsOfUseRequestSerializer);
    }

    private AcceptTermsOfUseRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AcceptTermsOfUseRequest acceptTermsOfUseRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (acceptTermsOfUseRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(acceptTermsOfUseRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AcceptTermsOfUseRequest acceptTermsOfUseRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(acceptTermsOfUseRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("termsOfUse");
        TermsOfUseSerializer.INSTANCE.serialize(acceptTermsOfUseRequest.getTermsOfUse(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(acceptTermsOfUseRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(acceptTermsOfUseRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
