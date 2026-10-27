package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControlsSerializer;
import com.amazon.musicplayqueueservice.client.v2.common.MapOfStringToMapOfStringToStringSerializer;
import com.amazon.musicplayqueueservice.model.CustomerInformationSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueRequestV1Serializer extends JsonSerializer<CreateQueueRequestV1> {
    public static final CreateQueueRequestV1Serializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CreateQueueRequestV1Serializer createQueueRequestV1Serializer = new CreateQueueRequestV1Serializer();
        INSTANCE = createQueueRequestV1Serializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.queue.CreateQueueRequestV1Serializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CreateQueueRequestV1.class, createQueueRequestV1Serializer);
    }

    private CreateQueueRequestV1Serializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CreateQueueRequestV1 createQueueRequestV1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (createQueueRequestV1 == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(createQueueRequestV1, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CreateQueueRequestV1 createQueueRequestV1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("playableEntityList");
        PlayableEntityListSerializer.INSTANCE.serialize(createQueueRequestV1.getPlayableEntityList(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(createQueueRequestV1.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isShuffled");
        SimpleSerializers.serializeBoolean(createQueueRequestV1.isIsShuffled(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(createQueueRequestV1.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("startAt");
        StartAtSerializer.INSTANCE.serialize(createQueueRequestV1.getStartAt(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("adContext");
        AdContextSerializer.INSTANCE.serialize(createQueueRequestV1.getAdContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.PLAY_MODE);
        SimpleSerializers.serializeString(createQueueRequestV1.getPlayMode(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("targetId");
        SimpleSerializers.serializeString(createQueueRequestV1.getTargetId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("metricsContexts");
        MapOfStringToMapOfStringToStringSerializer.INSTANCE.serialize(createQueueRequestV1.getMetricsContexts(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(createQueueRequestV1.getCustomerInfo(), jsonGenerator, serializerProvider);
    }
}
