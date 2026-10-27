package com.amazon.tenzing.textsearch.v1_1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class InstantDocumentSpecificationsSerializer extends JsonSerializer<List<InstantDocumentSpecification>> {
    public static final JsonSerializer<List<InstantDocumentSpecification>> INSTANCE = new InstantDocumentSpecificationsSerializer();

    private InstantDocumentSpecificationsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<InstantDocumentSpecification> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<InstantDocumentSpecification> it = list.iterator();
        while (it.hasNext()) {
            InstantDocumentSpecificationSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
