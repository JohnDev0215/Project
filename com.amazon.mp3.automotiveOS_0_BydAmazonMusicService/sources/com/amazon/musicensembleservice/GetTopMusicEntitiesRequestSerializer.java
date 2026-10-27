package com.amazon.musicensembleservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.storeservice.model.FeatureListSerializer;
import com.amazon.music.storeservice.model.PrimeTypeListSerializer;
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
public class GetTopMusicEntitiesRequestSerializer extends JsonSerializer<GetTopMusicEntitiesRequest> {
    public static final GetTopMusicEntitiesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTopMusicEntitiesRequestSerializer getTopMusicEntitiesRequestSerializer = new GetTopMusicEntitiesRequestSerializer();
        INSTANCE = getTopMusicEntitiesRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.GetTopMusicEntitiesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetTopMusicEntitiesRequest.class, getTopMusicEntitiesRequestSerializer);
    }

    private GetTopMusicEntitiesRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetTopMusicEntitiesRequest getTopMusicEntitiesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getTopMusicEntitiesRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getTopMusicEntitiesRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetTopMusicEntitiesRequest getTopMusicEntitiesRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("types");
        PrimeTypeListSerializer.INSTANCE.serialize(getTopMusicEntitiesRequest.getTypes(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("stub");
        SimpleSerializers.serializeBoolean(getTopMusicEntitiesRequest.isStub(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getTopMusicEntitiesRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerIP");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getCustomerIP(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languagesOfPerformance");
        StringListSerializer.INSTANCE.serialize(getTopMusicEntitiesRequest.getLanguagesOfPerformance(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languagesOfPerformanceFilter");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getLanguagesOfPerformanceFilter(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("browseId");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getBrowseId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rankType");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getRankType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeatureListSerializer.INSTANCE.serialize(getTopMusicEntitiesRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextTokenMap");
        NextTokenMapSerializer.INSTANCE.serialize(getTopMusicEntitiesRequest.getNextTokenMap(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("lang");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getLang(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxCount");
        SimpleSerializers.serializeInteger(getTopMusicEntitiesRequest.getMaxCount(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedContent");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getRequestedContent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getTopMusicEntitiesRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
