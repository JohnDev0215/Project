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
public class GetNextTracksRequestSerializer extends JsonSerializer<GetNextTracksRequest> {
    public static final GetNextTracksRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetNextTracksRequestSerializer getNextTracksRequestSerializer = new GetNextTracksRequestSerializer();
        INSTANCE = getNextTracksRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.GetNextTracksRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetNextTracksRequest.class, getNextTracksRequestSerializer);
    }

    private GetNextTracksRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetNextTracksRequest getNextTracksRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getNextTracksRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getNextTracksRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetNextTracksRequest getNextTracksRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getNextTracksRequest.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(getNextTracksRequest.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getNextTracksRequest.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("numberOfTracks");
        SimpleSerializers.serializePrimitiveInt(getNextTracksRequest.getNumberOfTracks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(getNextTracksRequest.getCustomerInfo(), jsonGenerator, serializerProvider);
    }
}
