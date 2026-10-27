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
public class GetPersonalizedPlaylistsRequestSerializer extends JsonSerializer<GetPersonalizedPlaylistsRequest> {
    public static final GetPersonalizedPlaylistsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetPersonalizedPlaylistsRequestSerializer getPersonalizedPlaylistsRequestSerializer = new GetPersonalizedPlaylistsRequestSerializer();
        INSTANCE = getPersonalizedPlaylistsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetPersonalizedPlaylistsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetPersonalizedPlaylistsRequest.class, getPersonalizedPlaylistsRequestSerializer);
    }

    private GetPersonalizedPlaylistsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getPersonalizedPlaylistsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getPersonalizedPlaylistsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetPersonalizedPlaylistsRequest getPersonalizedPlaylistsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("playlistTypes");
        PersonalizedPlaylistTypesSerializer.INSTANCE.serialize(getPersonalizedPlaylistsRequest.getPlaylistTypes(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getPersonalizedPlaylistsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getPersonalizedPlaylistsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("dependentServiceName");
        SimpleSerializers.serializeString(getPersonalizedPlaylistsRequest.getDependentServiceName(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("apiRevision");
        SimpleSerializers.serializePrimitiveInt(getPersonalizedPlaylistsRequest.getApiRevision(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getPersonalizedPlaylistsRequest.getDeviceType(), jsonGenerator, serializerProvider);
    }
}
