package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class GetSongRecommendationsRequestSerializer extends JsonSerializer<GetSongRecommendationsRequest> {
    public static final GetSongRecommendationsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetSongRecommendationsRequestSerializer getSongRecommendationsRequestSerializer = new GetSongRecommendationsRequestSerializer();
        INSTANCE = getSongRecommendationsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetSongRecommendationsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetSongRecommendationsRequest.class, getSongRecommendationsRequestSerializer);
    }

    private GetSongRecommendationsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetSongRecommendationsRequest getSongRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getSongRecommendationsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getSongRecommendationsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetSongRecommendationsRequest getSongRecommendationsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.CONTENT_ENCODING);
        SimpleSerializers.serializePrimitiveBoolean(getSongRecommendationsRequest.isContentEncoding(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(getSongRecommendationsRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("numTracksRequested");
        SimpleSerializers.serializePrimitiveInt(getSongRecommendationsRequest.getNumTracksRequested(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistId");
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getPlaylistId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("parentalControlSettings");
        ParentalControlSettingsSerializer.INSTANCE.serialize(getSongRecommendationsRequest.getParentalControlSettings(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentSubscriptionMode");
        SimpleSerializers.serializeString(getSongRecommendationsRequest.getContentSubscriptionMode(), jsonGenerator, serializerProvider);
    }
}
