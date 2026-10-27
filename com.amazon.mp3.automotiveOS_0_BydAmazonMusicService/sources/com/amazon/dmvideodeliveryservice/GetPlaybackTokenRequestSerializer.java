package com.amazon.dmvideodeliveryservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackTokenRequestSerializer extends JsonSerializer<GetPlaybackTokenRequest> {
    public static final GetPlaybackTokenRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetPlaybackTokenRequestSerializer getPlaybackTokenRequestSerializer = new GetPlaybackTokenRequestSerializer();
        INSTANCE = getPlaybackTokenRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.dmvideodeliveryservice.GetPlaybackTokenRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetPlaybackTokenRequest.class, getPlaybackTokenRequestSerializer);
    }

    private GetPlaybackTokenRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetPlaybackTokenRequest getPlaybackTokenRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getPlaybackTokenRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getPlaybackTokenRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetPlaybackTokenRequest getPlaybackTokenRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInfoSerializer.INSTANCE.serialize(getPlaybackTokenRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(getPlaybackTokenRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deviceToken");
        DeviceTokenSerializer.INSTANCE.serialize(getPlaybackTokenRequest.getDeviceToken(), jsonGenerator, serializerProvider);
    }
}
