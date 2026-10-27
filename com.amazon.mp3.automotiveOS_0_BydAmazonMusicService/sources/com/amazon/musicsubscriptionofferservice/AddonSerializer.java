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
public class AddonSerializer extends JsonSerializer<Addon> {
    public static final AddonSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddonSerializer addonSerializer = new AddonSerializer();
        INSTANCE = addonSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.AddonSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Addon.class, addonSerializer);
    }

    private AddonSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Addon addon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (addon == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(addon, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Addon addon, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("eligibility");
        EligibilitySerializer.INSTANCE.serialize(addon.getEligibility(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("buyability");
        BuyabilitySerializer.INSTANCE.serialize(addon.getBuyability(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("partnerPromotionEligibilities");
        PartnerPromotionEligibilitiesSerializer.INSTANCE.serialize(addon.getPartnerPromotionEligibilities(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("transitionType");
        SimpleSerializers.serializeString(addon.getTransitionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planSchedule");
        PlanScheduleSerializer.INSTANCE.serialize(addon.getPlanSchedule(), jsonGenerator, serializerProvider);
    }
}
