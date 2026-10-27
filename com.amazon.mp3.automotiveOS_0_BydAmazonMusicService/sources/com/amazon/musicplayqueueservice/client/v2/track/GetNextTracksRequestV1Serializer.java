package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.musicplayqueueservice.client.common.AllowedParentalControlsSerializer;
import com.amazon.musicplayqueueservice.client.v2.queue.AdContextSerializer;
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
public class GetNextTracksRequestV1Serializer extends JsonSerializer<GetNextTracksRequestV1> {
    public static final GetNextTracksRequestV1Serializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetNextTracksRequestV1Serializer getNextTracksRequestV1Serializer = new GetNextTracksRequestV1Serializer();
        INSTANCE = getNextTracksRequestV1Serializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksRequestV1Serializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(GetNextTracksRequestV1.class, getNextTracksRequestV1Serializer);
    }

    private GetNextTracksRequestV1Serializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull GetNextTracksRequestV1 getNextTracksRequestV1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (getNextTracksRequestV1 == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(getNextTracksRequestV1, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(GetNextTracksRequestV1 getNextTracksRequestV1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(getNextTracksRequestV1.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("numberOfTracks");
        SimpleSerializers.serializePrimitiveInt(getNextTracksRequestV1.getNumberOfTracks(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("adContext");
        AdContextSerializer.INSTANCE.serialize(getNextTracksRequestV1.getAdContext(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        AllowedParentalControlsSerializer.INSTANCE.serialize(getNextTracksRequestV1.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("isVoiceInitiatedRequest");
        SimpleSerializers.serializePrimitiveBoolean(getNextTracksRequestV1.isIsVoiceInitiatedRequest(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerInfo");
        CustomerInformationSerializer.INSTANCE.serialize(getNextTracksRequestV1.getCustomerInfo(), jsonGenerator, serializerProvider);
    }
}
