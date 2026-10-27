package com.amazon.stratus;

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
public class RetrievePrivacyCompliancePreferencesRequestSerializer extends JsonSerializer<RetrievePrivacyCompliancePreferencesRequest> {
    public static final RetrievePrivacyCompliancePreferencesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrievePrivacyCompliancePreferencesRequestSerializer retrievePrivacyCompliancePreferencesRequestSerializer = new RetrievePrivacyCompliancePreferencesRequestSerializer();
        INSTANCE = retrievePrivacyCompliancePreferencesRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrievePrivacyCompliancePreferencesRequest.class, retrievePrivacyCompliancePreferencesRequestSerializer);
    }

    private RetrievePrivacyCompliancePreferencesRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrievePrivacyCompliancePreferencesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrievePrivacyCompliancePreferencesRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("keys");
        PreferenceKeyListSerializer.INSTANCE.serialize(retrievePrivacyCompliancePreferencesRequest.getKeys(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_LOCALE);
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("namespace");
        SimpleSerializers.serializeString(retrievePrivacyCompliancePreferencesRequest.getNamespace(), jsonGenerator, serializerProvider);
    }
}
