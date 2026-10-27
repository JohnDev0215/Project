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
public class GetGenresRequestSerializer extends JsonSerializer<GetGenresRequest> {
    public static final GetGenresRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetGenresRequestSerializer getGenresRequestSerializer = new GetGenresRequestSerializer();
        INSTANCE = getGenresRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetGenresRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetGenresRequest.class, getGenresRequestSerializer);
    }

    private GetGenresRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetGenresRequest getGenresRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getGenresRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getGenresRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetGenresRequest getGenresRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("sortOrder");
        SortOrderSerializer.INSTANCE.serialize(getGenresRequest.getSortOrder(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getGenresRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filterList");
        FilterListSerializer.INSTANCE.serialize(getGenresRequest.getFilterList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializePrimitiveInt(getGenresRequest.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("nextToken");
        SimpleSerializers.serializeString(getGenresRequest.getNextToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getGenresRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("attributeList");
        SelectableAttributeListSerializer.INSTANCE.serialize(getGenresRequest.getAttributeList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getGenresRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
