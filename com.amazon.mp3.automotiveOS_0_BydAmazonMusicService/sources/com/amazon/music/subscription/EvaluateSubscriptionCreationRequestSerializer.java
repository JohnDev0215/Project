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
public class EvaluateSubscriptionCreationRequestSerializer extends JsonSerializer<EvaluateSubscriptionCreationRequest> {
    public static final EvaluateSubscriptionCreationRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EvaluateSubscriptionCreationRequestSerializer evaluateSubscriptionCreationRequestSerializer = new EvaluateSubscriptionCreationRequestSerializer();
        INSTANCE = evaluateSubscriptionCreationRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.EvaluateSubscriptionCreationRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(EvaluateSubscriptionCreationRequest.class, evaluateSubscriptionCreationRequestSerializer);
    }

    private EvaluateSubscriptionCreationRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (evaluateSubscriptionCreationRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(evaluateSubscriptionCreationRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("rolloverPlanType");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getRolloverPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("displayPrice");
        PriceWithCurrencySerializer.INSTANCE.serialize(evaluateSubscriptionCreationRequest.getDisplayPrice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME);
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getScheduleId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planType");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getPlanType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipv6Address");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getIpv6Address(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_SESSION_ID);
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getSessionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("ipAddress");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getIpAddress(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("autoRenew");
        SimpleSerializers.serializeBoolean(evaluateSubscriptionCreationRequest.isAutoRenew(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("associateTagId");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getAssociateTagId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentInfo");
        PaymentInfoSerializer.INSTANCE.serialize(evaluateSubscriptionCreationRequest.getPaymentInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("empSku");
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getEmpSku(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(evaluateSubscriptionCreationRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
    }
}
