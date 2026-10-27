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
public class PromotionSerializer extends JsonSerializer<Promotion> {
    public static final PromotionSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PromotionSerializer promotionSerializer = new PromotionSerializer();
        INSTANCE = promotionSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.PromotionSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(Promotion.class, promotionSerializer);
    }

    private PromotionSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull Promotion promotion, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (promotion == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(promotion, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(Promotion promotion, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("basePrice");
        MonetaryAmountSerializer.INSTANCE.serialize(promotion.getBasePrice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("baseCommitmentPeriod");
        PeriodSerializer.INSTANCE.serialize(promotion.getBaseCommitmentPeriod(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promotionId");
        SimpleSerializers.serializeString(promotion.getPromotionId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("effective");
        DateRangeSerializer.INSTANCE.serialize(promotion.getEffective(), jsonGenerator, serializerProvider);
    }
}
