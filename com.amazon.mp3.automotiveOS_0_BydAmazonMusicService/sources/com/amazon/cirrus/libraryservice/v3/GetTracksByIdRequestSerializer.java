package com.amazon.cirrus.libraryservice.v3;

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

/* JADX INFO: loaded from: classes2.dex */
public class GetTracksByIdRequestSerializer extends JsonSerializer<GetTracksByIdRequest> {
    public static final GetTracksByIdRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTracksByIdRequestSerializer getTracksByIdRequestSerializer = new GetTracksByIdRequestSerializer();
        INSTANCE = getTracksByIdRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetTracksByIdRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetTracksByIdRequest.class, getTracksByIdRequestSerializer);
    }

    private GetTracksByIdRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetTracksByIdRequest getTracksByIdRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getTracksByIdRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getTracksByIdRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetTracksByIdRequest getTracksByIdRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getTracksByIdRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackIdList");
        TrackIdListSerializer.INSTANCE.serialize(getTracksByIdRequest.getTrackIdList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("batchExceptions");
        SimpleSerializers.serializePrimitiveBoolean(getTracksByIdRequest.isBatchExceptions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getTracksByIdRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getTracksByIdRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getTracksByIdRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeList");
        SelectableAttributeListSerializer.INSTANCE.serialize(getTracksByIdRequest.getAttributeList(), jsonGenerator, serializerProvider);
    }
}
