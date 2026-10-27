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
public class GetTracksRequestSerializer extends JsonSerializer<GetTracksRequest> {
    public static final GetTracksRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTracksRequestSerializer getTracksRequestSerializer = new GetTracksRequestSerializer();
        INSTANCE = getTracksRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetTracksRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetTracksRequest.class, getTracksRequestSerializer);
    }

    private GetTracksRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetTracksRequest getTracksRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getTracksRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getTracksRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetTracksRequest getTracksRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sortOrder");
        SortOrderSerializer.INSTANCE.serialize(getTracksRequest.getSortOrder(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getTracksRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterList");
        FilterListSerializer.INSTANCE.serialize(getTracksRequest.getFilterList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializePrimitiveInt(getTracksRequest.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(getTracksRequest.getNextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getTracksRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeList");
        SelectableAttributeListSerializer.INSTANCE.serialize(getTracksRequest.getAttributeList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getTracksRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getTracksRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
    }
}
