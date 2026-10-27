package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveSubscriptionOffersResponseSerializer extends JsonSerializer<RetrieveSubscriptionOffersResponse> {
    public static final RetrieveSubscriptionOffersResponseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveSubscriptionOffersResponseSerializer retrieveSubscriptionOffersResponseSerializer = new RetrieveSubscriptionOffersResponseSerializer();
        INSTANCE = retrieveSubscriptionOffersResponseSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersResponseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveSubscriptionOffersResponse.class, retrieveSubscriptionOffersResponseSerializer);
    }

    private RetrieveSubscriptionOffersResponseSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveSubscriptionOffersResponse retrieveSubscriptionOffersResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveSubscriptionOffersResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveSubscriptionOffersResponse, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveSubscriptionOffersResponse retrieveSubscriptionOffersResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("planSchedules");
        PlanSchedulesSerializer.INSTANCE.serialize(retrieveSubscriptionOffersResponse.getPlanSchedules(), jsonGenerator, serializerProvider);
    }
}
