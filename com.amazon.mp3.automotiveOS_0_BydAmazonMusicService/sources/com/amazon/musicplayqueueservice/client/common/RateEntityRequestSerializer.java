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
public class RateEntityRequestSerializer extends JsonSerializer<RateEntityRequest> {
    public static final RateEntityRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RateEntityRequestSerializer rateEntityRequestSerializer = new RateEntityRequestSerializer();
        INSTANCE = rateEntityRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.RateEntityRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RateEntityRequest.class, rateEntityRequestSerializer);
    }

    private RateEntityRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RateEntityRequest rateEntityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (rateEntityRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(rateEntityRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RateEntityRequest rateEntityRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("rating");
        SimpleSerializers.serializeString(rateEntityRequest.getRating(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(rateEntityRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(rateEntityRequest.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("identifier");
        SimpleSerializers.serializeString(rateEntityRequest.getIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("durationPlayedInMilliseconds");
        SimpleSerializers.serializePrimitiveInt(rateEntityRequest.getDurationPlayedInMilliseconds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("utcTimeStamp");
        SimpleSerializers.serializePrimitiveLong(rateEntityRequest.getUtcTimeStamp(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(rateEntityRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
    }
}
