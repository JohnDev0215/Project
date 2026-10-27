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
public class GetSimilarityRecommendationsRequestSerializer extends JsonSerializer<GetSimilarityRecommendationsRequest> {
    public static final GetSimilarityRecommendationsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetSimilarityRecommendationsRequestSerializer getSimilarityRecommendationsRequestSerializer = new GetSimilarityRecommendationsRequestSerializer();
        INSTANCE = getSimilarityRecommendationsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetSimilarityRecommendationsRequest.class, getSimilarityRecommendationsRequestSerializer);
    }

    private GetSimilarityRecommendationsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getSimilarityRecommendationsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getSimilarityRecommendationsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("widgetIdTokenMap");
        WidgetIdTokenMapSerializer.INSTANCE.serialize(getSimilarityRecommendationsRequest.getWidgetIdTokenMap(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("minResultsPerWidget");
        SimpleSerializers.serializeInteger(getSimilarityRecommendationsRequest.getMinResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResultsPerWidget");
        SimpleSerializers.serializeInteger(getSimilarityRecommendationsRequest.getMaxResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getSimilarityRecommendationsRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asin");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getAsin(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getSimilarityRecommendationsRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getSimilarityRecommendationsRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
