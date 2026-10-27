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
public class RetrieveEligibleSubscriptionOffersResponseSerializer extends JsonSerializer<RetrieveEligibleSubscriptionOffersResponse> {
    public static final RetrieveEligibleSubscriptionOffersResponseSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveEligibleSubscriptionOffersResponseSerializer retrieveEligibleSubscriptionOffersResponseSerializer = new RetrieveEligibleSubscriptionOffersResponseSerializer();
        INSTANCE = retrieveEligibleSubscriptionOffersResponseSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponseSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveEligibleSubscriptionOffersResponse.class, retrieveEligibleSubscriptionOffersResponseSerializer);
    }

    private RetrieveEligibleSubscriptionOffersResponseSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveEligibleSubscriptionOffersResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveEligibleSubscriptionOffersResponse, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveEligibleSubscriptionOffersResponse retrieveEligibleSubscriptionOffersResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("results");
        ResultsSerializer.INSTANCE.serialize(retrieveEligibleSubscriptionOffersResponse.getResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("addons");
        AddonsSerializer.INSTANCE.serialize(retrieveEligibleSubscriptionOffersResponse.getAddons(), jsonGenerator, serializerProvider);
    }
}
