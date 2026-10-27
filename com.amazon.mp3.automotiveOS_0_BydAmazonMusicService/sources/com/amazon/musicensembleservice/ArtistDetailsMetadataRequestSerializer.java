package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.storeservice.model.FeatureListSerializer;
import com.amazon.music.storeservice.model.StringListSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataRequestSerializer extends JsonSerializer<ArtistDetailsMetadataRequest> {
    public static final ArtistDetailsMetadataRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDetailsMetadataRequestSerializer artistDetailsMetadataRequestSerializer = new ArtistDetailsMetadataRequestSerializer();
        INSTANCE = artistDetailsMetadataRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.ArtistDetailsMetadataRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ArtistDetailsMetadataRequest.class, artistDetailsMetadataRequestSerializer);
    }

    private ArtistDetailsMetadataRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ArtistDetailsMetadataRequest artistDetailsMetadataRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (artistDetailsMetadataRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(artistDetailsMetadataRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ArtistDetailsMetadataRequest artistDetailsMetadataRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languagesOfPerformance");
        StringListSerializer.INSTANCE.serialize(artistDetailsMetadataRequest.getLanguagesOfPerformance(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("types");
        ArtistDetailsMetadataTypeListSerializer.INSTANCE.serialize(artistDetailsMetadataRequest.getTypes(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asin");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getAsin(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(artistDetailsMetadataRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metadataLang");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getMetadataLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(artistDetailsMetadataRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeatureListSerializer.INSTANCE.serialize(artistDetailsMetadataRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(artistDetailsMetadataRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
