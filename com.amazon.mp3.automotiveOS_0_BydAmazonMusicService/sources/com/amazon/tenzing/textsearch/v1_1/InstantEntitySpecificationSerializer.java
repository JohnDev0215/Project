package com.amazon.tenzing.textsearch.v1_1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class InstantEntitySpecificationSerializer extends JsonSerializer<InstantEntitySpecification> {
    public static final InstantEntitySpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InstantEntitySpecificationSerializer instantEntitySpecificationSerializer = new InstantEntitySpecificationSerializer();
        INSTANCE = instantEntitySpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.InstantEntitySpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InstantEntitySpecification.class, instantEntitySpecificationSerializer);
    }

    private InstantEntitySpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InstantEntitySpecification instantEntitySpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (instantEntitySpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(instantEntitySpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InstantEntitySpecification instantEntitySpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("documentSpecs");
        InstantDocumentSpecificationsSerializer.INSTANCE.serialize(instantEntitySpecification.getDocumentSpecs(), jsonGenerator, serializerProvider);
    }
}
