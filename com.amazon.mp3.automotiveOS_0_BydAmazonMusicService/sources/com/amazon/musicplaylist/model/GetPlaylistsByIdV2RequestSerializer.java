package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class GetPlaylistsByIdV2RequestSerializer extends JsonSerializer<GetPlaylistsByIdV2Request> {
    public static final GetPlaylistsByIdV2RequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetPlaylistsByIdV2RequestSerializer getPlaylistsByIdV2RequestSerializer = new GetPlaylistsByIdV2RequestSerializer();
        INSTANCE = getPlaylistsByIdV2RequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetPlaylistsByIdV2RequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetPlaylistsByIdV2Request.class, getPlaylistsByIdV2RequestSerializer);
    }

    private GetPlaylistsByIdV2RequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetPlaylistsByIdV2Request getPlaylistsByIdV2Request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getPlaylistsByIdV2Request == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getPlaylistsByIdV2Request, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetPlaylistsByIdV2Request getPlaylistsByIdV2Request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("featureSet");
        FeatureListSerializer.INSTANCE.serialize(getPlaylistsByIdV2Request.getFeatureSet(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.CONTENT_ENCODING);
        SimpleSerializers.serializePrimitiveBoolean(getPlaylistsByIdV2Request.isContentEncoding(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(getPlaylistsByIdV2Request.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistIds");
        PlaylistIdsSerializer.INSTANCE.serialize(getPlaylistsByIdV2Request.getPlaylistIds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        ParentalControlsSerializer.INSTANCE.serialize(getPlaylistsByIdV2Request.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContextToken");
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getMusicRequestIdentityContextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getPlaylistsByIdV2Request.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestedMetadata");
        SelectableAttributesSerializer.INSTANCE.serialize(getPlaylistsByIdV2Request.getRequestedMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getPlaylistsByIdV2Request.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("includeUnreleasedPlaylists");
        SimpleSerializers.serializePrimitiveBoolean(getPlaylistsByIdV2Request.isIncludeUnreleasedPlaylists(), jsonGenerator, serializerProvider);
    }
}
