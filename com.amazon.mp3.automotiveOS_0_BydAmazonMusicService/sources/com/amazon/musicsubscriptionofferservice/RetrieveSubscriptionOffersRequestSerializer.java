package com.amazon.musicsubscriptionofferservice;

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

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveSubscriptionOffersRequestSerializer extends JsonSerializer<RetrieveSubscriptionOffersRequest> {
    public static final RetrieveSubscriptionOffersRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveSubscriptionOffersRequestSerializer retrieveSubscriptionOffersRequestSerializer = new RetrieveSubscriptionOffersRequestSerializer();
        INSTANCE = retrieveSubscriptionOffersRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RetrieveSubscriptionOffersRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(RetrieveSubscriptionOffersRequest.class, retrieveSubscriptionOffersRequestSerializer);
    }

    private RetrieveSubscriptionOffersRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull RetrieveSubscriptionOffersRequest retrieveSubscriptionOffersRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (retrieveSubscriptionOffersRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(retrieveSubscriptionOffersRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(RetrieveSubscriptionOffersRequest retrieveSubscriptionOffersRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(retrieveSubscriptionOffersRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("scheduleIds");
        StringListSerializer.INSTANCE.serialize(retrieveSubscriptionOffersRequest.getScheduleIds(), jsonGenerator, serializerProvider);
    }
}
