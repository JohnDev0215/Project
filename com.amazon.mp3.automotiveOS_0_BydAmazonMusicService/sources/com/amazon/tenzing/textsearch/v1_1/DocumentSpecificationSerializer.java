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
public class DocumentSpecificationSerializer extends JsonSerializer<DocumentSpecification> {
    public static final DocumentSpecificationSerializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DocumentSpecificationSerializer documentSpecificationSerializer = new DocumentSpecificationSerializer();
        INSTANCE = documentSpecificationSerializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.tenzing.textsearch.v1_1.DocumentSpecificationSerializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addSerializer(DocumentSpecification.class, documentSpecificationSerializer);
    }

    private DocumentSpecificationSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(@CheckForNull DocumentSpecification documentSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (documentSpecification == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        serializeFields(documentSpecification, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public void serializeFields(DocumentSpecification documentSpecification, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        SimpleSerializers.serializeString(documentSpecification.getType(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("filters");
        FiltersSerializer.INSTANCE.serialize(documentSpecification.getFilters(), jsonGenerator, serializerProvider);
        jsonGenerator.writeFieldName("fields");
        FieldNamesSerializer.INSTANCE.serialize(documentSpecification.getFields(), jsonGenerator, serializerProvider);
    }
}
