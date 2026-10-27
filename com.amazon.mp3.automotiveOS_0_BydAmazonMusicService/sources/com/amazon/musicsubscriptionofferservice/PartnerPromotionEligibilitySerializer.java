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
public class PartnerPromotionEligibilitySerializer extends JsonSerializer<PartnerPromotionEligibility> {
    public static final PartnerPromotionEligibilitySerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PartnerPromotionEligibilitySerializer partnerPromotionEligibilitySerializer = new PartnerPromotionEligibilitySerializer();
        INSTANCE = partnerPromotionEligibilitySerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PartnerPromotionEligibilitySerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PartnerPromotionEligibility.class, partnerPromotionEligibilitySerializer);
    }

    private PartnerPromotionEligibilitySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PartnerPromotionEligibility partnerPromotionEligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (partnerPromotionEligibility == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(partnerPromotionEligibility, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PartnerPromotionEligibility partnerPromotionEligibility, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("promoPartner");
        SimpleSerializers.serializeString(partnerPromotionEligibility.getPromoPartner(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promoGroup");
        SimpleSerializers.serializeString(partnerPromotionEligibility.getPromoGroup(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promoTransitionType");
        SimpleSerializers.serializeString(partnerPromotionEligibility.getPromoTransitionType(), jsonGenerator, serializerProvider);
    }
}
