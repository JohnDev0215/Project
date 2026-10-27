package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersRequestDeserializer;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersRequestSerializer;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponseDeserializer;
import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponseSerializer;
import com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersRequestDeserializer;
import com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersRequestSerializer;
import com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersResponseDeserializer;
import com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersResponseSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
class MSOSMapper {
    private static final ObjectMapper MAPPER;

    MSOSMapper() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        RetrieveEligibleSubscriptionOffersResponseDeserializer.register(objectMapper);
        RetrieveEligibleSubscriptionOffersResponseSerializer.register(objectMapper);
        RetrieveEligibleSubscriptionOffersRequestDeserializer.register(objectMapper);
        RetrieveEligibleSubscriptionOffersRequestSerializer.register(objectMapper);
        RetrieveSubscriptionOffersRequestDeserializer.register(objectMapper);
        RetrieveSubscriptionOffersRequestSerializer.register(objectMapper);
        RetrieveSubscriptionOffersResponseDeserializer.register(objectMapper);
        RetrieveSubscriptionOffersResponseSerializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
