package com.amazon.music.nautilus;

import com.amazon.music.subscription.CreateSubscriptionRequestSerializer;
import com.amazon.music.subscription.CreateSubscriptionResponseDeserializer;
import com.amazon.music.subscription.EvaluateSubscriptionCreationRequestSerializer;
import com.amazon.music.subscription.EvaluateSubscriptionCreationResponseDeserializer;
import com.amazon.music.subscription.PrepareSubscriptionRequestSerializer;
import com.amazon.music.subscription.PrepareSubscriptionResponseDeserializer;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequestSerializer;
import com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersResponseDeserializer;
import com.amazon.music.subscription.RetrieveSubscriptionsRequestSerializer;
import com.amazon.music.subscription.RetrieveSubscriptionsResponseDeserializer;
import com.amazon.music.subscription.SubmitSubscriptionCreationRequestSerializer;
import com.amazon.music.subscription.SubmitSubscriptionCreationResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        PrepareSubscriptionRequestSerializer.register(objectMapper);
        PrepareSubscriptionResponseDeserializer.register(objectMapper);
        CreateSubscriptionRequestSerializer.register(objectMapper);
        CreateSubscriptionResponseDeserializer.register(objectMapper);
        RetrieveEligibleSubscriptionOffersRequestSerializer.register(objectMapper);
        RetrieveEligibleSubscriptionOffersResponseDeserializer.register(objectMapper);
        RetrieveSubscriptionsRequestSerializer.register(objectMapper);
        RetrieveSubscriptionsResponseDeserializer.register(objectMapper);
        EvaluateSubscriptionCreationRequestSerializer.register(objectMapper);
        EvaluateSubscriptionCreationResponseDeserializer.register(objectMapper);
        SubmitSubscriptionCreationRequestSerializer.register(objectMapper);
        SubmitSubscriptionCreationResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
