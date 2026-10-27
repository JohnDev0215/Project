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
public class GetHomeRequestSerializer extends JsonSerializer<GetHomeRequest> {
    public static final GetHomeRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetHomeRequestSerializer getHomeRequestSerializer = new GetHomeRequestSerializer();
        INSTANCE = getHomeRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.GetHomeRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetHomeRequest.class, getHomeRequestSerializer);
    }

    private GetHomeRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetHomeRequest getHomeRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getHomeRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getHomeRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetHomeRequest getHomeRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControlsString");
        SimpleSerializers.serializeString(getHomeRequest.getAllowedParentalControlsString(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getHomeRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isDebug");
        SimpleSerializers.serializeBoolean(getHomeRequest.isIsDebug(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("campaignsXml");
        SimpleSerializers.serializeString(getHomeRequest.getCampaignsXml(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languagesOfPerformance");
        StringListSerializer.INSTANCE.serialize(getHomeRequest.getLanguagesOfPerformance(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getHomeRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getHomeRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getHomeRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("recsTestingConfigToken");
        SimpleSerializers.serializeString(getHomeRequest.getRecsTestingConfigToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getHomeRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("options");
        HomeOptionListSerializer.INSTANCE.serialize(getHomeRequest.getOptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getHomeRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getHomeRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getHomeRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getHomeRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getHomeRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getHomeRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getHomeRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
