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
public class GetLibraryCountsRequestSerializer extends JsonSerializer<GetLibraryCountsRequest> {
    public static final GetLibraryCountsRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetLibraryCountsRequestSerializer getLibraryCountsRequestSerializer = new GetLibraryCountsRequestSerializer();
        INSTANCE = getLibraryCountsRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetLibraryCountsRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetLibraryCountsRequest.class, getLibraryCountsRequestSerializer);
    }

    private GetLibraryCountsRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetLibraryCountsRequest getLibraryCountsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getLibraryCountsRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getLibraryCountsRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetLibraryCountsRequest getLibraryCountsRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("counts");
        CountTypeListSerializer.INSTANCE.serialize(getLibraryCountsRequest.getCounts(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getLibraryCountsRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getLibraryCountsRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getLibraryCountsRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
