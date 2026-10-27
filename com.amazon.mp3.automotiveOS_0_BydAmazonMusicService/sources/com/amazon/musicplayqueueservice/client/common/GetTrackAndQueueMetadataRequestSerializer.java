package com.amazon.musicplayqueueservice.client.common;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.model.CustomerInformationSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class GetTrackAndQueueMetadataRequestSerializer extends JsonSerializer<GetTrackAndQueueMetadataRequest> {
    public static final GetTrackAndQueueMetadataRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTrackAndQueueMetadataRequestSerializer getTrackAndQueueMetadataRequestSerializer = new GetTrackAndQueueMetadataRequestSerializer();
        INSTANCE = getTrackAndQueueMetadataRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetTrackAndQueueMetadataRequest.class, getTrackAndQueueMetadataRequestSerializer);
    }

    private GetTrackAndQueueMetadataRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetTrackAndQueueMetadataRequest getTrackAndQueueMetadataRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getTrackAndQueueMetadataRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getTrackAndQueueMetadataRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetTrackAndQueueMetadataRequest getTrackAndQueueMetadataRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getTrackAndQueueMetadataRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(getTrackAndQueueMetadataRequest.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(getTrackAndQueueMetadataRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("trackId");
        SimpleSerializers.serializeString(getTrackAndQueueMetadataRequest.getTrackId(), jsonGenerator, serializerProvider);
    }
}
