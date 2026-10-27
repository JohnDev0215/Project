package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.stratus.PaymentInfoSerializer;
import com.amazon.stratus.PeriodSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class CreateSubscriptionRequestSerializer extends JsonSerializer<CreateSubscriptionRequest> {
    public static final CreateSubscriptionRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CreateSubscriptionRequestSerializer createSubscriptionRequestSerializer = new CreateSubscriptionRequestSerializer();
        INSTANCE = createSubscriptionRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.CreateSubscriptionRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(CreateSubscriptionRequest.class, createSubscriptionRequestSerializer);
    }

    private CreateSubscriptionRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull CreateSubscriptionRequest createSubscriptionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (createSubscriptionRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(createSubscriptionRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(CreateSubscriptionRequest createSubscriptionRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(createSubscriptionRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("vetRequired");
        SimpleSerializers.serializeBoolean(createSubscriptionRequest.isVetRequired(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("rolloverPlanType");
        SimpleSerializers.serializeString(createSubscriptionRequest.getRolloverPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("addonScheduleIds");
        AddonScheduleIdsSerializer.INSTANCE.serialize(createSubscriptionRequest.getAddonScheduleIds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(createSubscriptionRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planType");
        SimpleSerializers.serializeString(createSubscriptionRequest.getPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipv6Address");
        SimpleSerializers.serializeString(createSubscriptionRequest.getIpv6Address(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(createSubscriptionRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("periodOverride");
        PeriodSerializer.INSTANCE.serialize(createSubscriptionRequest.getPeriodOverride(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("marketplaceId");
        SimpleSerializers.serializeString(createSubscriptionRequest.getMarketplaceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("mfaPrerequisiteInfo");
        MFAPrerequisiteInfoSerializer.INSTANCE.serialize(createSubscriptionRequest.getMfaPrerequisiteInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("autoRenew");
        SimpleSerializers.serializeBoolean(createSubscriptionRequest.isAutoRenew(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentInfo");
        PaymentInfoSerializer.INSTANCE.serialize(createSubscriptionRequest.getPaymentInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME);
        SimpleSerializers.serializeString(createSubscriptionRequest.getScheduleId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("enableOptimisticSignUp");
        SimpleSerializers.serializeBoolean(createSubscriptionRequest.isEnableOptimisticSignUp(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("requestConfig");
        RequestConfigSerializer.INSTANCE.serialize(createSubscriptionRequest.getRequestConfig(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(createSubscriptionRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(createSubscriptionRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(createSubscriptionRequest.getDeviceId(), jsonGenerator, serializerProvider);
    }
}
