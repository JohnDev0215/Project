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
public class GetStationRecommendationsRequestSerializer extends JsonSerializer<GetStationRecommendationsRequest> {
    public static final GetStationRecommendationsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetStationRecommendationsRequestSerializer getStationRecommendationsRequestSerializer = new GetStationRecommendationsRequestSerializer();
        INSTANCE = getStationRecommendationsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.GetStationRecommendationsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetStationRecommendationsRequest.class, getStationRecommendationsRequestSerializer);
    }

    private GetStationRecommendationsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetStationRecommendationsRequest getStationRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getStationRecommendationsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getStationRecommendationsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetStationRecommendationsRequest getStationRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("widgetIdTokenMap");
        WidgetIdTokenMapSerializer.INSTANCE.serialize(getStationRecommendationsRequest.getWidgetIdTokenMap(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("minResultsPerWidget");
        SimpleSerializers.serializeInteger(getStationRecommendationsRequest.getMinResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResultsPerWidget");
        SimpleSerializers.serializeInteger(getStationRecommendationsRequest.getMaxResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getStationRecommendationsRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getStationRecommendationsRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getStationRecommendationsRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
