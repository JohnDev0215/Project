package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.find.utils.PageUriUtils;
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
public class GetAlphabetsForScrollingRequestSerializer extends JsonSerializer<GetAlphabetsForScrollingRequest> {
    public static final GetAlphabetsForScrollingRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetAlphabetsForScrollingRequestSerializer getAlphabetsForScrollingRequestSerializer = new GetAlphabetsForScrollingRequestSerializer();
        INSTANCE = getAlphabetsForScrollingRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.cirrus.libraryservice.v3.GetAlphabetsForScrollingRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetAlphabetsForScrollingRequest.class, getAlphabetsForScrollingRequestSerializer);
    }

    private GetAlphabetsForScrollingRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetAlphabetsForScrollingRequest getAlphabetsForScrollingRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getAlphabetsForScrollingRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getAlphabetsForScrollingRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetAlphabetsForScrollingRequest getAlphabetsForScrollingRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(getAlphabetsForScrollingRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
        SimpleSerializers.serializeString(getAlphabetsForScrollingRequest.getEntityType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getAlphabetsForScrollingRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(getAlphabetsForScrollingRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
