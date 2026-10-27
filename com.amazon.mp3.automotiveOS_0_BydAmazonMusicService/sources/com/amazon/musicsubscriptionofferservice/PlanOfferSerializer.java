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
public class PlanOfferSerializer extends JsonSerializer<PlanOffer> {
    public static final PlanOfferSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlanOfferSerializer planOfferSerializer = new PlanOfferSerializer();
        INSTANCE = planOfferSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PlanOfferSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlanOffer.class, planOfferSerializer);
    }

    private PlanOfferSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlanOffer planOffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (planOffer == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(planOffer, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlanOffer planOffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("promotion");
        PromotionSerializer.INSTANCE.serialize(planOffer.getPromotion(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("index");
        SimpleSerializers.serializeInteger(planOffer.getIndex(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contractType");
        SimpleSerializers.serializeString(planOffer.getContractType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("principalOwnershipType");
        PrincipalOwnershipTypesSerializer.INSTANCE.serialize(planOffer.getPrincipalOwnershipType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("baseCommitmentPeriod");
        PeriodSerializer periodSerializer = PeriodSerializer.INSTANCE;
        periodSerializer.serialize(planOffer.getBaseCommitmentPeriod(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("renewalPolicy");
        RenewalPolicySerializer.INSTANCE.serialize(planOffer.getRenewalPolicy(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("commitmentPeriod");
        periodSerializer.serialize(planOffer.getCommitmentPeriod(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("billingSchedule");
        BillingScheduleSerializer.INSTANCE.serialize(planOffer.getBillingSchedule(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("salesPromotions");
        SalesPromotionsSerializer.INSTANCE.serialize(planOffer.getSalesPromotions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("displayableInfo");
        DisplayableInfoSerializer.INSTANCE.serialize(planOffer.getDisplayableInfo(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planId");
        SimpleSerializers.serializeString(planOffer.getPlanId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("serviceLevel");
        SimpleSerializers.serializeString(planOffer.getServiceLevel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("shortPlanId");
        SimpleSerializers.serializeString(planOffer.getShortPlanId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("externalPaymentIdentifier");
        SimpleSerializers.serializeString(planOffer.getExternalPaymentIdentifier(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("acquisitionGateways");
        AcquisitionGatewaysSerializer.INSTANCE.serialize(planOffer.getAcquisitionGateways(), jsonGenerator, serializerProvider);
    }
}
