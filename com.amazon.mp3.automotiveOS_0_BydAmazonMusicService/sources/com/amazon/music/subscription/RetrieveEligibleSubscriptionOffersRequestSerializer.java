package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveEligibleSubscriptionOffersRequestSerializer extends JsonSerializer<RetrieveEligibleSubscriptionOffersRequest> {
    public static final RetrieveEligibleSubscriptionOffersRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveEligibleSubscriptionOffersRequestSerializer retrieveEligibleSubscriptionOffersRequestSerializer = new RetrieveEligibleSubscriptionOffersRequestSerializer();
        INSTANCE = retrieveEligibleSubscriptionOffersRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveEligibleSubscriptionOffersRequest.class, retrieveEligibleSubscriptionOffersRequestSerializer);
    }

    private RetrieveEligibleSubscriptionOffersRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveEligibleSubscriptionOffersRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveEligibleSubscriptionOffersRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("includeIneligibleOffers");
        SimpleSerializers.serializeBoolean(retrieveEligibleSubscriptionOffersRequest.isIncludeIneligibleOffers(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("responseFilters");
        EligibilityResponseFiltersSerializer.INSTANCE.serialize(retrieveEligibleSubscriptionOffersRequest.getResponseFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("includeBuyability");
        SimpleSerializers.serializeBoolean(retrieveEligibleSubscriptionOffersRequest.isIncludeBuyability(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(retrieveEligibleSubscriptionOffersRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
