package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.music.destination.parser.UpsellDeeplinkParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class FeaturesRequestSerializer extends JsonSerializer<FeaturesRequest> {
    public static final FeaturesRequestSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturesRequestSerializer featuresRequestSerializer = new FeaturesRequestSerializer();
        INSTANCE = featuresRequestSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.FeaturesRequestSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(FeaturesRequest.class, featuresRequestSerializer);
    }

    private FeaturesRequestSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull FeaturesRequest featuresRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (featuresRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(featuresRequest, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(FeaturesRequest featuresRequest, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("spellCorrection");
        SpellCorrectionRequestSerializer.INSTANCE.serialize(featuresRequest.getSpellCorrection(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("spiritual");
        SpiritualRequestSerializer.INSTANCE.serialize(featuresRequest.getSpiritual(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(UpsellDeeplinkParser.UPSELL_SEGMENT_NAME);
        UpsellRequestSerializer.INSTANCE.serialize(featuresRequest.getUpsell(), jsonGenerator, serializerProvider);
    }
}
