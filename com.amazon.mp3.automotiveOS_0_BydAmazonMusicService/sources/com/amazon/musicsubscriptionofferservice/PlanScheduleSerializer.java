package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.destination.parser.ParserUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlanScheduleSerializer extends JsonSerializer<PlanSchedule> {
    public static final PlanScheduleSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlanScheduleSerializer planScheduleSerializer = new PlanScheduleSerializer();
        INSTANCE = planScheduleSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PlanScheduleSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(PlanSchedule.class, planScheduleSerializer);
    }

    private PlanScheduleSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull PlanSchedule planSchedule, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (planSchedule == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(planSchedule, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(PlanSchedule planSchedule, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(ParserUtil.SCHEDULE_ID_QUERY_PARAM_NAME);
        SimpleSerializers.serializeString(planSchedule.getScheduleId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("addonRequired");
        SimpleSerializers.serializeBoolean(planSchedule.isAddonRequired(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("subscriptionType");
        SimpleSerializers.serializeString(planSchedule.getSubscriptionType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("musicSubscriptionOfferMetadata");
        MusicSubscriptionOfferMetadataSerializer.INSTANCE.serialize(planSchedule.getMusicSubscriptionOfferMetadata(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promoType");
        SimpleSerializers.serializeString(planSchedule.getPromoType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("addonScheduleIds");
        AddonScheduleIdsSerializer.INSTANCE.serialize(planSchedule.getAddonScheduleIds(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("planOffers");
        PlanOffersSerializer.INSTANCE.serialize(planSchedule.getPlanOffers(), jsonGenerator, serializerProvider);
    }
}
