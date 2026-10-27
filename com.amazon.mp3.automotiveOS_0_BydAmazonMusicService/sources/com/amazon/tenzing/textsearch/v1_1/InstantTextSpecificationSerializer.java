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
public class InstantTextSpecificationSerializer extends JsonSerializer<InstantTextSpecification> {
    public static final InstantTextSpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InstantTextSpecificationSerializer instantTextSpecificationSerializer = new InstantTextSpecificationSerializer();
        INSTANCE = instantTextSpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.InstantTextSpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InstantTextSpecification.class, instantTextSpecificationSerializer);
    }

    private InstantTextSpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InstantTextSpecification instantTextSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (instantTextSpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(instantTextSpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InstantTextSpecification instantTextSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("fields");
        FieldNamesSerializer.INSTANCE.serialize(instantTextSpecification.getFields(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("enabled");
        SimpleSerializers.serializeBoolean(instantTextSpecification.isEnabled(), jsonGenerator, serializerProvider);
    }
}
