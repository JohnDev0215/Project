package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.stratus.PaymentInfoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class PrepareSubscriptionRequestSerializer extends JsonSerializer<PrepareSubscriptionRequest> {
    public static final PrepareSubscriptionRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PrepareSubscriptionRequestSerializer prepareSubscriptionRequestSerializer = new PrepareSubscriptionRequestSerializer();
        INSTANCE = prepareSubscriptionRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.PrepareSubscriptionRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PrepareSubscriptionRequest.class, prepareSubscriptionRequestSerializer);
    }

    private PrepareSubscriptionRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PrepareSubscriptionRequest prepareSubscriptionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (prepareSubscriptionRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(prepareSubscriptionRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PrepareSubscriptionRequest prepareSubscriptionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planType");
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentInfo");
        PaymentInfoSerializer.INSTANCE.serialize(prepareSubscriptionRequest.getPaymentInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("addonScheduleIds");
        AddonScheduleIdsSerializer.INSTANCE.serialize(prepareSubscriptionRequest.getAddonScheduleIds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rolloverPlanType");
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getRolloverPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("subscriptionId");
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getSubscriptionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME);
        SimpleSerializers.serializeString(prepareSubscriptionRequest.getScheduleId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("forceAuthenticationRequired");
        SimpleSerializers.serializeBoolean(prepareSubscriptionRequest.isForceAuthenticationRequired(), jsonGenerator, serializerProvider);
    }
}
