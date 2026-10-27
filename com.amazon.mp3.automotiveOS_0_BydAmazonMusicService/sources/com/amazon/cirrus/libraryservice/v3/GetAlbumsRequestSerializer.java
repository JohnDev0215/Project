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
public class GetAlbumsRequestSerializer extends JsonSerializer<GetAlbumsRequest> {
    public static final GetAlbumsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetAlbumsRequestSerializer getAlbumsRequestSerializer = new GetAlbumsRequestSerializer();
        INSTANCE = getAlbumsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetAlbumsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetAlbumsRequest.class, getAlbumsRequestSerializer);
    }

    private GetAlbumsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetAlbumsRequest getAlbumsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getAlbumsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getAlbumsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetAlbumsRequest getAlbumsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sortOrder");
        SortOrderSerializer.INSTANCE.serialize(getAlbumsRequest.getSortOrder(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getAlbumsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterList");
        FilterListSerializer.INSTANCE.serialize(getAlbumsRequest.getFilterList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializePrimitiveInt(getAlbumsRequest.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(getAlbumsRequest.getNextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getAlbumsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeList");
        SelectableAttributeListSerializer.INSTANCE.serialize(getAlbumsRequest.getAttributeList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getAlbumsRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicRequestIdentityContext");
        MusicRequestIdentityContextSerializer.INSTANCE.serialize(getAlbumsRequest.getMusicRequestIdentityContext(), jsonGenerator, serializerProvider);
    }
}
