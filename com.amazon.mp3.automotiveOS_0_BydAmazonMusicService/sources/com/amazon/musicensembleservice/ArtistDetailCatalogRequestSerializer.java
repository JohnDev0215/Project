package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class ArtistDetailCatalogRequestSerializer extends JsonSerializer<ArtistDetailCatalogRequest> {
    public static final ArtistDetailCatalogRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDetailCatalogRequestSerializer artistDetailCatalogRequestSerializer = new ArtistDetailCatalogRequestSerializer();
        INSTANCE = artistDetailCatalogRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.ArtistDetailCatalogRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ArtistDetailCatalogRequest.class, artistDetailCatalogRequestSerializer);
    }

    private ArtistDetailCatalogRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ArtistDetailCatalogRequest artistDetailCatalogRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (artistDetailCatalogRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(artistDetailCatalogRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ArtistDetailCatalogRequest artistDetailCatalogRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("languagesOfPerformance");
        StringListSerializer.INSTANCE.serialize(artistDetailCatalogRequest.getLanguagesOfPerformance(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("options");
        ArtistDetailCatalogOptionListSerializer.INSTANCE.serialize(artistDetailCatalogRequest.getOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(artistDetailCatalogRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asin");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getAsin(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(artistDetailCatalogRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(artistDetailCatalogRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
