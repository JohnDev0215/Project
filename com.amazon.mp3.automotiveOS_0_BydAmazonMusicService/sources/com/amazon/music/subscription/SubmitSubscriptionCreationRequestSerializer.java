package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
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
public class SubmitSubscriptionCreationRequestSerializer extends JsonSerializer<SubmitSubscriptionCreationRequest> {
    public static final SubmitSubscriptionCreationRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SubmitSubscriptionCreationRequestSerializer submitSubscriptionCreationRequestSerializer = new SubmitSubscriptionCreationRequestSerializer();
        INSTANCE = submitSubscriptionCreationRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.SubmitSubscriptionCreationRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SubmitSubscriptionCreationRequest.class, submitSubscriptionCreationRequestSerializer);
    }

    private SubmitSubscriptionCreationRequestSerializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (submitSubscriptionCreationRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(submitSubscriptionCreationRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SubmitSubscriptionCreationRequest submitSubscriptionCreationRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_TYPE);
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getDeviceType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("customerId");
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getCustomerId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_MUSIC_TERRITORY);
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getMusicTerritory(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(Splash.PARAMS_DEVICE_ID);
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getDeviceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("orderInitToken");
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getOrderInitToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("empBillingDetails");
        SimpleSerializers.serializeString(submitSubscriptionCreationRequest.getEmpBillingDetails(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("paymentInfo");
        PaymentInfoSerializer.INSTANCE.serialize(submitSubscriptionCreationRequest.getPaymentInfo(), jsonGenerator, serializerProvider);
    }
}
