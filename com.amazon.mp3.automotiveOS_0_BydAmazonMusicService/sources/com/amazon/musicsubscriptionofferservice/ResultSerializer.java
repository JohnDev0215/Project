package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ResultSerializer extends JsonSerializer<Result> {
    public static final ResultSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResultSerializer resultSerializer = new ResultSerializer();
        INSTANCE = resultSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.ResultSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Result.class, resultSerializer);
    }

    private ResultSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (result == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(result, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("planSchedule");
        PlanScheduleSerializer.INSTANCE.serialize(result.getPlanSchedule(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("partnerPromotionEligibilities");
        PartnerPromotionEligibilitiesSerializer.INSTANCE.serialize(result.getPartnerPromotionEligibilities(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("transitionType");
        SimpleSerializers.serializeString(result.getTransitionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("eligibility");
        EligibilitySerializer.INSTANCE.serialize(result.getEligibility(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("buyability");
        BuyabilitySerializer.INSTANCE.serialize(result.getBuyability(), jsonGenerator, serializerProvider);
    }
}
