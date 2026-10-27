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
public class GetRecentTrackActivityRequestSerializer extends JsonSerializer<GetRecentTrackActivityRequest> {
    public static final GetRecentTrackActivityRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetRecentTrackActivityRequestSerializer getRecentTrackActivityRequestSerializer = new GetRecentTrackActivityRequestSerializer();
        INSTANCE = getRecentTrackActivityRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.GetRecentTrackActivityRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetRecentTrackActivityRequest.class, getRecentTrackActivityRequestSerializer);
    }

    private GetRecentTrackActivityRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetRecentTrackActivityRequest getRecentTrackActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getRecentTrackActivityRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getRecentTrackActivityRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetRecentTrackActivityRequest getRecentTrackActivityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getRecentTrackActivityRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("languageLocale");
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getLanguageLocale(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeaturesListSerializer.INSTANCE.serialize(getRecentTrackActivityRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getRecentTrackActivityRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("activityTypeFilters");
        ActivityTypeListSerializer.INSTANCE.serialize(getRecentTrackActivityRequest.getActivityTypeFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getRecentTrackActivityRequest.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
    }
}
