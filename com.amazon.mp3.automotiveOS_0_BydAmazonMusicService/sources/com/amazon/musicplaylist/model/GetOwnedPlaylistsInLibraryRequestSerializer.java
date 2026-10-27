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
public class GetOwnedPlaylistsInLibraryRequestSerializer extends JsonSerializer<GetOwnedPlaylistsInLibraryRequest> {
    public static final GetOwnedPlaylistsInLibraryRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetOwnedPlaylistsInLibraryRequestSerializer getOwnedPlaylistsInLibraryRequestSerializer = new GetOwnedPlaylistsInLibraryRequestSerializer();
        INSTANCE = getOwnedPlaylistsInLibraryRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetOwnedPlaylistsInLibraryRequest.class, getOwnedPlaylistsInLibraryRequestSerializer);
    }

    private GetOwnedPlaylistsInLibraryRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getOwnedPlaylistsInLibraryRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getOwnedPlaylistsInLibraryRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("sortBy");
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getSortBy(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("entryOffset");
        SimpleSerializers.serializeInteger(getOwnedPlaylistsInLibraryRequest.getEntryOffset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(getOwnedPlaylistsInLibraryRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getOwnedPlaylistsInLibraryRequest.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filters");
        PlaylistFilterMapSerializer.INSTANCE.serialize(getOwnedPlaylistsInLibraryRequest.getFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageSize");
        SimpleSerializers.serializeInteger(getOwnedPlaylistsInLibraryRequest.getPageSize(), jsonGenerator, serializerProvider);
    }
}
