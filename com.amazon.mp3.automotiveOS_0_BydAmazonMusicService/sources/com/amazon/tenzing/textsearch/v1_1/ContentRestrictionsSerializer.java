package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.platform.model.ParentalControlsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ContentRestrictionsSerializer extends JsonSerializer<ContentRestrictions> {
    public static final ContentRestrictionsSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ContentRestrictionsSerializer contentRestrictionsSerializer = new ContentRestrictionsSerializer();
        INSTANCE = contentRestrictionsSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.ContentRestrictionsSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ContentRestrictions.class, contentRestrictionsSerializer);
    }

    private ContentRestrictionsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ContentRestrictions contentRestrictions, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (contentRestrictions == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(contentRestrictions, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ContentRestrictions contentRestrictions, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("eligibility");
        EligibilitySerializer.INSTANCE.serialize(contentRestrictions.getEligibility(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("assetQuality");
        AssetQualitySerializer.INSTANCE.serialize(contentRestrictions.getAssetQuality(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("allowedParentalControls");
        ParentalControlsSerializer.INSTANCE.serialize(contentRestrictions.getAllowedParentalControls(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentTier");
        SimpleSerializers.serializeString(contentRestrictions.getContentTier(), jsonGenerator, serializerProvider);
    }
}
