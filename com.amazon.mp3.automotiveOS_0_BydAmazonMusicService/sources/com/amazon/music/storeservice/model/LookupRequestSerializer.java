package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicensembleservice.AllowedParentalControlsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class LookupRequestSerializer extends JsonSerializer<LookupRequest> {
    public static final LookupRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LookupRequestSerializer lookupRequestSerializer = new LookupRequestSerializer();
        INSTANCE = lookupRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.LookupRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(LookupRequest.class, lookupRequestSerializer);
    }

    private LookupRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull LookupRequest lookupRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (lookupRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(lookupRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(LookupRequest lookupRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(lookupRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(lookupRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(lookupRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(lookupRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(lookupRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(lookupRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asins");
        AsinListSerializer.INSTANCE.serialize(lookupRequest.getAsins(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(lookupRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("currencyOfPreference");
        SimpleSerializers.serializeString(lookupRequest.getCurrencyOfPreference(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeatureListSerializer.INSTANCE.serialize(lookupRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(lookupRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadataLang");
        SimpleSerializers.serializeString(lookupRequest.getMetadataLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(lookupRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(lookupRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filters");
        FilterListSerializer.INSTANCE.serialize(lookupRequest.getFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(lookupRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(lookupRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
