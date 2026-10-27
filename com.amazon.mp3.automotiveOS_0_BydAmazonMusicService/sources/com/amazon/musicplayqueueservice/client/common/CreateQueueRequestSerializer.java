package com.amazon.musicplayqueueservice.client.common;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.client.common.casting.StartAtSerializer;
import com.amazon.musicplayqueueservice.client.common.casting.TrackListSerializer;
import com.amazon.musicplayqueueservice.model.CustomerInformationSerializer;
import com.amazon.musicplayqueueservice.model.MapOfStringToStringMapSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueRequestSerializer extends JsonSerializer<CreateQueueRequest> {
    public static final CreateQueueRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CreateQueueRequestSerializer createQueueRequestSerializer = new CreateQueueRequestSerializer();
        INSTANCE = createQueueRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.CreateQueueRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CreateQueueRequest.class, createQueueRequestSerializer);
    }

    private CreateQueueRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CreateQueueRequest createQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (createQueueRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(createQueueRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CreateQueueRequest createQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("identifiers");
        TrackListSerializer.INSTANCE.serialize(createQueueRequest.getIdentifiers(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("timezoneOffset");
        SimpleSerializers.serializeString(createQueueRequest.getTimezoneOffset(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(createQueueRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isShuffled");
        SimpleSerializers.serializePrimitiveBoolean(createQueueRequest.isIsShuffled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsContexts");
        MapOfStringToStringMapSerializer.INSTANCE.serialize(createQueueRequest.getMetricsContexts(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(createQueueRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifier");
        SimpleSerializers.serializeString(createQueueRequest.getIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("supportedFeatures");
        FeatureListSerializer.INSTANCE.serialize(createQueueRequest.getSupportedFeatures(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetId");
        SimpleSerializers.serializeString(createQueueRequest.getTargetId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("playbackPreference");
        SimpleSerializers.serializeString(createQueueRequest.getPlaybackPreference(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(createQueueRequest.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("startAt");
        StartAtSerializer.INSTANCE.serialize(createQueueRequest.getStartAt(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifierType");
        SimpleSerializers.serializeString(createQueueRequest.getIdentifierType(), jsonGenerator, serializerProvider);
    }
}
