package com.amazon.musicplaylist.model;

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
public class AppendTracksToPlaylistRequestSerializer extends JsonSerializer<AppendTracksToPlaylistRequest> {
    public static final AppendTracksToPlaylistRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AppendTracksToPlaylistRequestSerializer appendTracksToPlaylistRequestSerializer = new AppendTracksToPlaylistRequestSerializer();
        INSTANCE = appendTracksToPlaylistRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.AppendTracksToPlaylistRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AppendTracksToPlaylistRequest.class, appendTracksToPlaylistRequestSerializer);
    }

    private AppendTracksToPlaylistRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AppendTracksToPlaylistRequest appendTracksToPlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (appendTracksToPlaylistRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(appendTracksToPlaylistRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AppendTracksToPlaylistRequest appendTracksToPlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("rejectDuplicateTracks");
        SimpleSerializers.serializePrimitiveBoolean(appendTracksToPlaylistRequest.isRejectDuplicateTracks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featureSet");
        FeatureListSerializer.INSTANCE.serialize(appendTracksToPlaylistRequest.getFeatureSet(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(appendTracksToPlaylistRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(appendTracksToPlaylistRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistId");
        SimpleSerializers.serializeString(appendTracksToPlaylistRequest.getPlaylistId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("tracks");
        TrackIdListAtLeastOneRequiredSerializer.INSTANCE.serialize(appendTracksToPlaylistRequest.getTracks(), jsonGenerator, serializerProvider);
    }
}
