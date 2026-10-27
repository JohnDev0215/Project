package com.amazon.tenzing.textsearch.v1_1;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class InstantDocumentSpecificationSerializer extends JsonSerializer<InstantDocumentSpecification> {
    public static final InstantDocumentSpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InstantDocumentSpecificationSerializer instantDocumentSpecificationSerializer = new InstantDocumentSpecificationSerializer();
        INSTANCE = instantDocumentSpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.InstantDocumentSpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(InstantDocumentSpecification.class, instantDocumentSpecificationSerializer);
    }

    private InstantDocumentSpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull InstantDocumentSpecification instantDocumentSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (instantDocumentSpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(instantDocumentSpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(InstantDocumentSpecification instantDocumentSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("filters");
        FiltersSerializer.INSTANCE.serialize(instantDocumentSpecification.getFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(instantDocumentSpecification.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("fields");
        FieldNamesSerializer.INSTANCE.serialize(instantDocumentSpecification.getFields(), jsonGenerator, serializerProvider);
    }
}
