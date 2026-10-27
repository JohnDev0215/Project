package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlanOfferDeserializer extends JsonDeserializer<PlanOffer> {
    public static final PlanOfferDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlanOfferDeserializer planOfferDeserializer = new PlanOfferDeserializer();
        INSTANCE = planOfferDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PlanOfferDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlanOffer.class, planOfferDeserializer);
    }

    private PlanOfferDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlanOffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.musicsubscriptionofferservice#PlanOffer".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlanOffer deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlanOffer planOffer = new PlanOffer();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("promotion".equals(currentName)) {
                planOffer.setPromotion(PromotionDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("index".equals(currentName)) {
                planOffer.setIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("contractType".equals(currentName)) {
                planOffer.setContractType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("principalOwnershipType".equals(currentName)) {
                planOffer.setPrincipalOwnershipType(PrincipalOwnershipTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("baseCommitmentPeriod".equals(currentName)) {
                planOffer.setBaseCommitmentPeriod(PeriodDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("renewalPolicy".equals(currentName)) {
                planOffer.setRenewalPolicy(RenewalPolicyDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("commitmentPeriod".equals(currentName)) {
                planOffer.setCommitmentPeriod(PeriodDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("billingSchedule".equals(currentName)) {
                planOffer.setBillingSchedule(BillingScheduleDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("salesPromotions".equals(currentName)) {
                planOffer.setSalesPromotions(SalesPromotionsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("displayableInfo".equals(currentName)) {
                planOffer.setDisplayableInfo(DisplayableInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("planId".equals(currentName)) {
                planOffer.setPlanId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("serviceLevel".equals(currentName)) {
                planOffer.setServiceLevel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shortPlanId".equals(currentName)) {
                planOffer.setShortPlanId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("externalPaymentIdentifier".equals(currentName)) {
                planOffer.setExternalPaymentIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("acquisitionGateways".equals(currentName)) {
                planOffer.setAcquisitionGateways(AcquisitionGatewaysDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return planOffer;
    }
}
