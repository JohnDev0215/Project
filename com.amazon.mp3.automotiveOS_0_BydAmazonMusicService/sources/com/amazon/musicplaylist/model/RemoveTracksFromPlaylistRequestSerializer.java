package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
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
public class RemoveTracksFromPlaylistRequestSerializer extends JsonSerializer<RemoveTracksFromPlaylistRequest> {
    public static final RemoveTracksFromPlaylistRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RemoveTracksFromPlaylistRequestSerializer removeTracksFromPlaylistRequestSerializer = new RemoveTracksFromPlaylistRequestSerializer();
        INSTANCE = removeTracksFromPlaylistRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.RemoveTracksFromPlaylistRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RemoveTracksFromPlaylistRequest.class, removeTracksFromPlaylistRequestSerializer);
    }

    private RemoveTracksFromPlaylistRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (removeTracksFromPlaylistRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(removeTracksFromPlaylistRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("version");
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entryIds");
        PlaylistEntryIdsSerializer.INSTANCE.serialize(removeTracksFromPlaylistRequest.getEntryIds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(removeTracksFromPlaylistRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistId");
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getPlaylistId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(removeTracksFromPlaylistRequest.getDeviceType(), jsonGenerator, serializerProvider);
    }
}
