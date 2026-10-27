package com.amazon.musicensembleservice;

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

/* JADX INFO: loaded from: classes2.dex */
public class SeeMoreRequestSerializer extends JsonSerializer<SeeMoreRequest> {
    public static final SeeMoreRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SeeMoreRequestSerializer seeMoreRequestSerializer = new SeeMoreRequestSerializer();
        INSTANCE = seeMoreRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.SeeMoreRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SeeMoreRequest.class, seeMoreRequestSerializer);
    }

    private SeeMoreRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SeeMoreRequest seeMoreRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (seeMoreRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(seeMoreRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SeeMoreRequest seeMoreRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(seeMoreRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(seeMoreRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("next");
        SimpleSerializers.serializeString(seeMoreRequest.getNext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(seeMoreRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(seeMoreRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(seeMoreRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(seeMoreRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(seeMoreRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("count");
        SimpleSerializers.serializeInteger(seeMoreRequest.getCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(seeMoreRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(seeMoreRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(seeMoreRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(seeMoreRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(seeMoreRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
