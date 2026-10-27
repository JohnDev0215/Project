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
public class GetFollowedPlaylistsInLibraryRequestSerializer extends JsonSerializer<GetFollowedPlaylistsInLibraryRequest> {
    public static final GetFollowedPlaylistsInLibraryRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetFollowedPlaylistsInLibraryRequestSerializer getFollowedPlaylistsInLibraryRequestSerializer = new GetFollowedPlaylistsInLibraryRequestSerializer();
        INSTANCE = getFollowedPlaylistsInLibraryRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetFollowedPlaylistsInLibraryRequest.class, getFollowedPlaylistsInLibraryRequestSerializer);
    }

    private GetFollowedPlaylistsInLibraryRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetFollowedPlaylistsInLibraryRequest getFollowedPlaylistsInLibraryRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getFollowedPlaylistsInLibraryRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getFollowedPlaylistsInLibraryRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetFollowedPlaylistsInLibraryRequest getFollowedPlaylistsInLibraryRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sortBy");
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getSortBy(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("optIntoSharedPlaylists");
        SimpleSerializers.serializePrimitiveBoolean(getFollowedPlaylistsInLibraryRequest.isOptIntoSharedPlaylists(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageSize");
        SimpleSerializers.serializeInteger(getFollowedPlaylistsInLibraryRequest.getPageSize(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(getFollowedPlaylistsInLibraryRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getFollowedPlaylistsInLibraryRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entryOffset");
        SimpleSerializers.serializeInteger(getFollowedPlaylistsInLibraryRequest.getEntryOffset(), jsonGenerator, serializerProvider);
    }
}
