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
public class SociallySharePlaylistRequestSerializer extends JsonSerializer<SociallySharePlaylistRequest> {
    public static final SociallySharePlaylistRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SociallySharePlaylistRequestSerializer sociallySharePlaylistRequestSerializer = new SociallySharePlaylistRequestSerializer();
        INSTANCE = sociallySharePlaylistRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.SociallySharePlaylistRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SociallySharePlaylistRequest.class, sociallySharePlaylistRequestSerializer);
    }

    private SociallySharePlaylistRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SociallySharePlaylistRequest sociallySharePlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (sociallySharePlaylistRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(sociallySharePlaylistRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SociallySharePlaylistRequest sociallySharePlaylistRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("rejectForUnshareableTracks");
        SimpleSerializers.serializePrimitiveBoolean(sociallySharePlaylistRequest.isRejectForUnshareableTracks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("collaborativeMode");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getCollaborativeMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(sociallySharePlaylistRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistId");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getPlaylistId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playlistVersion");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getPlaylistVersion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("visibility");
        SimpleSerializers.serializeString(sociallySharePlaylistRequest.getVisibility(), jsonGenerator, serializerProvider);
    }
}
