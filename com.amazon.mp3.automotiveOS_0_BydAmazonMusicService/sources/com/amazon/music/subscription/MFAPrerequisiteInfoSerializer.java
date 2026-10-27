package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.stratus.PeriodSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class MFAPrerequisiteInfoSerializer extends JsonSerializer<MFAPrerequisiteInfo> {
    public static final MFAPrerequisiteInfoSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MFAPrerequisiteInfoSerializer mFAPrerequisiteInfoSerializer = new MFAPrerequisiteInfoSerializer();
        INSTANCE = mFAPrerequisiteInfoSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.MFAPrerequisiteInfoSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(MFAPrerequisiteInfo.class, mFAPrerequisiteInfoSerializer);
    }

    private MFAPrerequisiteInfoSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull MFAPrerequisiteInfo mFAPrerequisiteInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (mFAPrerequisiteInfo == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(mFAPrerequisiteInfo, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(MFAPrerequisiteInfo mFAPrerequisiteInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("mfaType");
        SimpleSerializers.serializeString(mFAPrerequisiteInfo.getMfaType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authenticationRecurringPeriod");
        PeriodSerializer.INSTANCE.serialize(mFAPrerequisiteInfo.getAuthenticationRecurringPeriod(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authenticationSourceId");
        SimpleSerializers.serializeString(mFAPrerequisiteInfo.getAuthenticationSourceId(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("redirectRequired");
        SimpleSerializers.serializeBoolean(mFAPrerequisiteInfo.isRedirectRequired(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("authenticationPrice");
        PriceWithCurrencySerializer.INSTANCE.serialize(mFAPrerequisiteInfo.getAuthenticationPrice(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("additionalPlanPriceInfo");
        AdditionalPlanPriceInfoSerializer.INSTANCE.serialize(mFAPrerequisiteInfo.getAdditionalPlanPriceInfo(), jsonGenerator, serializerProvider);
    }
}
