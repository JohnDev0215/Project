package com.amazon.musicplayqueueservice.client.v2.queue;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AbstractCreateQueueRequestSerializer extends JsonSerializer<AbstractCreateQueueRequest> {
    public static final AbstractCreateQueueRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AbstractCreateQueueRequestSerializer abstractCreateQueueRequestSerializer = new AbstractCreateQueueRequestSerializer();
        INSTANCE = abstractCreateQueueRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AbstractCreateQueueRequest.class, abstractCreateQueueRequestSerializer);
    }

    private AbstractCreateQueueRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AbstractCreateQueueRequest abstractCreateQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (abstractCreateQueueRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        boolean z = abstractCreateQueueRequest instanceof CreateQueueRequestV1;
        jsonGenerator.writeStartObject();
        if (!z) {
            serializeFields(abstractCreateQueueRequest, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeFieldName("__type");
            jsonGenerator.writeString("com.amazon.musicplayqueueservice.client.v2.queue#CreateQueueRequestV1");
            CreateQueueRequestV1Serializer.INSTANCE.serializeFields((CreateQueueRequestV1) abstractCreateQueueRequest, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndObject();
        }
    }

    public void serializeFields(AbstractCreateQueueRequest abstractCreateQueueRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        throw new UnsupportedOperationException("Serialization for abstract type not supported");
    }
}
