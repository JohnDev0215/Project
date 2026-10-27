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
public class SalesPromotionInfoSerializer extends JsonSerializer<SalesPromotionInfo> {
    public static final SalesPromotionInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SalesPromotionInfoSerializer salesPromotionInfoSerializer = new SalesPromotionInfoSerializer();
        INSTANCE = salesPromotionInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.SalesPromotionInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(SalesPromotionInfo.class, salesPromotionInfoSerializer);
    }

    private SalesPromotionInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull SalesPromotionInfo salesPromotionInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (salesPromotionInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(salesPromotionInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(SalesPromotionInfo salesPromotionInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("effective");
        DateRangeSerializer.INSTANCE.serialize(salesPromotionInfo.getEffective(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("savingsMessage");
        SimpleSerializers.serializeString(salesPromotionInfo.getSavingsMessage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("residualUses");
        SimpleSerializers.serializeInteger(salesPromotionInfo.getResidualUses(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("subscriptionBillingMessage");
        SimpleSerializers.serializeString(salesPromotionInfo.getSubscriptionBillingMessage(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("expiryDate");
        SimpleSerializers.serializeDate(salesPromotionInfo.getExpiryDate(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promotionTitle");
        SimpleSerializers.serializeString(salesPromotionInfo.getPromotionTitle(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("promotionId");
        SimpleSerializers.serializeString(salesPromotionInfo.getPromotionId(), jsonGenerator, serializerProvider);
    }
}
