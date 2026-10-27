package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.model.StringToStringMapSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AdContextSerializer extends JsonSerializer<AdContext> {
    public static final AdContextSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AdContextSerializer adContextSerializer = new AdContextSerializer();
        INSTANCE = adContextSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.queue.AdContextSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(AdContext.class, adContextSerializer);
    }

    private AdContextSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull AdContext adContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (adContext == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(adContext, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(AdContext adContext, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("userAgent");
        SimpleSerializers.serializeString(adContext.getUserAgent(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("additionalMetadata");
        StringToStringMapSerializer.INSTANCE.serialize(adContext.getAdditionalMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("deviceFamily");
        SimpleSerializers.serializeString(adContext.getDeviceFamily(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(adContext.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("advertiserId");
        SimpleSerializers.serializeString(adContext.getAdvertiserId(), jsonGenerator, serializerProvider);
    }
}
