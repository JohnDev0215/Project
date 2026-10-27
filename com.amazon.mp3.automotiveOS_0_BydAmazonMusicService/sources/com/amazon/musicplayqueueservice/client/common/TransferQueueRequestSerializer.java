package com.amazon.musicplayqueueservice.client.common;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.client.common.casting.StartAtSerializer;
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
public class TransferQueueRequestSerializer extends JsonSerializer<TransferQueueRequest> {
    public static final TransferQueueRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TransferQueueRequestSerializer transferQueueRequestSerializer = new TransferQueueRequestSerializer();
        INSTANCE = transferQueueRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.TransferQueueRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TransferQueueRequest.class, transferQueueRequestSerializer);
    }

    private TransferQueueRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TransferQueueRequest transferQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (transferQueueRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(transferQueueRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TransferQueueRequest transferQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(transferQueueRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(transferQueueRequest.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("queueId");
        SimpleSerializers.serializeString(transferQueueRequest.getQueueId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackPreference");
        SimpleSerializers.serializeString(transferQueueRequest.getPlaybackPreference(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetId");
        SimpleSerializers.serializeString(transferQueueRequest.getTargetId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(transferQueueRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("startAt");
        StartAtSerializer.INSTANCE.serialize(transferQueueRequest.getStartAt(), jsonGenerator, serializerProvider);
    }
}
