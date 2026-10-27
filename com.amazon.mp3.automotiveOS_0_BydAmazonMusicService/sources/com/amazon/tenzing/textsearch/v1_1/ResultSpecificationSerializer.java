package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ResultSpecificationSerializer extends JsonSerializer<ResultSpecification> {
    public static final ResultSpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ResultSpecificationSerializer resultSpecificationSerializer = new ResultSpecificationSerializer();
        INSTANCE = resultSpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.ResultSpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(ResultSpecification.class, resultSpecificationSerializer);
    }

    private ResultSpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull ResultSpecification resultSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (resultSpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(resultSpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(ResultSpecification resultSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("maxResults");
        SimpleSerializers.serializeInteger(resultSpecification.getMaxResults(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("pageToken");
        SimpleSerializers.serializeString(resultSpecification.getPageToken(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("topHitSpec");
        TopHitSpecificationSerializer.INSTANCE.serialize(resultSpecification.getTopHitSpec(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("contentRestrictions");
        ContentRestrictionsSerializer.INSTANCE.serialize(resultSpecification.getContentRestrictions(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(ContextMappingConstants.LABEL);
        SimpleSerializers.serializeString(resultSpecification.getLabel(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("documentSpecs");
        DocumentSpecificationsSerializer.INSTANCE.serialize(resultSpecification.getDocumentSpecs(), jsonGenerator, serializerProvider);
    }
}
