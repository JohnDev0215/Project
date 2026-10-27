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
public class GetBrowseRecommendationsBySourceAsinsRequestSerializer extends JsonSerializer<GetBrowseRecommendationsBySourceAsinsRequest> {
    public static final GetBrowseRecommendationsBySourceAsinsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetBrowseRecommendationsBySourceAsinsRequestSerializer getBrowseRecommendationsBySourceAsinsRequestSerializer = new GetBrowseRecommendationsBySourceAsinsRequestSerializer();
        INSTANCE = getBrowseRecommendationsBySourceAsinsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetBrowseRecommendationsBySourceAsinsRequest.class, getBrowseRecommendationsBySourceAsinsRequestSerializer);
    }

    private GetBrowseRecommendationsBySourceAsinsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getBrowseRecommendationsBySourceAsinsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getBrowseRecommendationsBySourceAsinsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("maxResultsPerWidget");
        SimpleSerializers.serializeInteger(getBrowseRecommendationsBySourceAsinsRequest.getMaxResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getBrowseRecommendationsBySourceAsinsRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("minResultsPerWidget");
        SimpleSerializers.serializeInteger(getBrowseRecommendationsBySourceAsinsRequest.getMinResultsPerWidget(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("widgetIdTokenMap");
        WidgetIdTokenMapSerializer.INSTANCE.serialize(getBrowseRecommendationsBySourceAsinsRequest.getWidgetIdTokenMap(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("asins");
        AsinListSerializer.INSTANCE.serialize(getBrowseRecommendationsBySourceAsinsRequest.getAsins(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getBrowseRecommendationsBySourceAsinsRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getBrowseRecommendationsBySourceAsinsRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
