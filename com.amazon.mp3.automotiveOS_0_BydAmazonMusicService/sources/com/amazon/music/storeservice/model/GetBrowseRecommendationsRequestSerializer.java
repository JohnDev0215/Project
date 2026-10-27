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
public class GetBrowseRecommendationsRequestSerializer extends JsonSerializer<GetBrowseRecommendationsRequest> {
    public static final GetBrowseRecommendationsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetBrowseRecommendationsRequestSerializer getBrowseRecommendationsRequestSerializer = new GetBrowseRecommendationsRequestSerializer();
        INSTANCE = getBrowseRecommendationsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.GetBrowseRecommendationsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetBrowseRecommendationsRequest.class, getBrowseRecommendationsRequestSerializer);
    }

    private GetBrowseRecommendationsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetBrowseRecommendationsRequest getBrowseRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getBrowseRecommendationsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getBrowseRecommendationsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetBrowseRecommendationsRequest getBrowseRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getBrowseRecommendationsRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("minResultsPerWidget");
        SimpleSerializers.serializeInteger(getBrowseRecommendationsRequest.getMinResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResultsPerWidget");
        SimpleSerializers.serializeInteger(getBrowseRecommendationsRequest.getMaxResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getBrowseRecommendationsRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("widgetIdTokenMap");
        WidgetIdTokenMapSerializer.INSTANCE.serialize(getBrowseRecommendationsRequest.getWidgetIdTokenMap(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getBrowseRecommendationsRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
