package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class RequestConfigSerializer extends JsonSerializer<RequestConfig> {
    public static final RequestConfigSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestConfigSerializer requestConfigSerializer = new RequestConfigSerializer();
        INSTANCE = requestConfigSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RequestConfigSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RequestConfig.class, requestConfigSerializer);
    }

    private RequestConfigSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RequestConfig requestConfig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (requestConfig == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(requestConfig, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RequestConfig requestConfig, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("includeBuyability");
        SimpleSerializers.serializeBoolean(requestConfig.isIncludeBuyability(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("alternateDevices");
        AlternateDevicesSerializer.INSTANCE.serialize(requestConfig.getAlternateDevices(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("includeOfferMetadata");
        SimpleSerializers.serializeBoolean(requestConfig.isIncludeOfferMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("includeIneligibleOffers");
        SimpleSerializers.serializeBoolean(requestConfig.isIncludeIneligibleOffers(), jsonGenerator, serializerProvider);
    }
}
