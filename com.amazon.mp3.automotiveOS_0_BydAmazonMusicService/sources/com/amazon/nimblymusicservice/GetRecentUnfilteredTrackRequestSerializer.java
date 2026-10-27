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
public class GetRecentUnfilteredTrackRequestSerializer extends JsonSerializer<GetRecentUnfilteredTrackRequest> {
    public static final GetRecentUnfilteredTrackRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetRecentUnfilteredTrackRequestSerializer getRecentUnfilteredTrackRequestSerializer = new GetRecentUnfilteredTrackRequestSerializer();
        INSTANCE = getRecentUnfilteredTrackRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetRecentUnfilteredTrackRequest.class, getRecentUnfilteredTrackRequestSerializer);
    }

    private GetRecentUnfilteredTrackRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getRecentUnfilteredTrackRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getRecentUnfilteredTrackRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getRecentUnfilteredTrackRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("features");
        FeaturesListSerializer.INSTANCE.serialize(getRecentUnfilteredTrackRequest.getFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("startTime");
        SimpleSerializers.serializeDate(getRecentUnfilteredTrackRequest.getStartTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterMoreThanThirtySeconds");
        SimpleSerializers.serializePrimitiveBoolean(getRecentUnfilteredTrackRequest.isFilterMoreThanThirtySeconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterHiddenTracks");
        SimpleSerializers.serializePrimitiveBoolean(getRecentUnfilteredTrackRequest.isFilterHiddenTracks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("flatten");
        SimpleSerializers.serializePrimitiveBoolean(getRecentUnfilteredTrackRequest.isFlatten(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("endTime");
        SimpleSerializers.serializeDate(getRecentUnfilteredTrackRequest.getEndTime(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getRecentUnfilteredTrackRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializePrimitiveInt(getRecentUnfilteredTrackRequest.getMaxResults(), jsonGenerator, serializerProvider);
    }
}
