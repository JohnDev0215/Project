package com.amazon.cirrus.libraryservice.v3;

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

/* JADX INFO: loaded from: classes2.dex */
public class GetArtistsRequestSerializer extends JsonSerializer<GetArtistsRequest> {
    public static final GetArtistsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetArtistsRequestSerializer getArtistsRequestSerializer = new GetArtistsRequestSerializer();
        INSTANCE = getArtistsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetArtistsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetArtistsRequest.class, getArtistsRequestSerializer);
    }

    private GetArtistsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetArtistsRequest getArtistsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getArtistsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getArtistsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetArtistsRequest getArtistsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sortOrder");
        SortOrderSerializer.INSTANCE.serialize(getArtistsRequest.getSortOrder(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getArtistsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterList");
        FilterListSerializer.INSTANCE.serialize(getArtistsRequest.getFilterList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializePrimitiveInt(getArtistsRequest.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(getArtistsRequest.getNextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getArtistsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeList");
        SelectableAttributeListSerializer.INSTANCE.serialize(getArtistsRequest.getAttributeList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getArtistsRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
