package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentActivityRequestSerializer extends JsonSerializer<GetRecentActivityRequest> {
    public static final GetRecentActivityRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetRecentActivityRequestSerializer getRecentActivityRequestSerializer = new GetRecentActivityRequestSerializer();
        INSTANCE = getRecentActivityRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.GetRecentActivityRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetRecentActivityRequest.class, getRecentActivityRequestSerializer);
    }

    private GetRecentActivityRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetRecentActivityRequest getRecentActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getRecentActivityRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getRecentActivityRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetRecentActivityRequest getRecentActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("maxResultSize");
        SimpleSerializers.serializeInteger(getRecentActivityRequest.getMaxResultSize(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getRecentActivityRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getRecentActivityRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("startTime");
        SimpleSerializers.serializeDate(getRecentActivityRequest.getStartTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("activityTypeFilters");
        ActivityTypeListSerializer.INSTANCE.serialize(getRecentActivityRequest.getActivityTypeFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeaturesListSerializer.INSTANCE.serialize(getRecentActivityRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languageLocale");
        SimpleSerializers.serializeString(getRecentActivityRequest.getLanguageLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("endTime");
        SimpleSerializers.serializeDate(getRecentActivityRequest.getEndTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getRecentActivityRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getRecentActivityRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getRecentActivityRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getRecentActivityRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getRecentActivityRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getRecentActivityRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entityTypeFilters");
        EntityTypeListSerializer.INSTANCE.serialize(getRecentActivityRequest.getEntityTypeFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getRecentActivityRequest.getPageToken(), jsonGenerator, serializerProvider);
    }
}
