package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class TopHitSpecificationSerializer extends JsonSerializer<TopHitSpecification> {
    public static final TopHitSpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TopHitSpecificationSerializer topHitSpecificationSerializer = new TopHitSpecificationSerializer();
        INSTANCE = topHitSpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.TopHitSpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(TopHitSpecification.class, topHitSpecificationSerializer);
    }

    private TopHitSpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull TopHitSpecification topHitSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (topHitSpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(topHitSpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(TopHitSpecification topHitSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("shouldUpsellMusicSubscription");
        SimpleSerializers.serializeBoolean(topHitSpecification.isShouldUpsellMusicSubscription(), jsonGenerator, serializerProvider);
    }
}
