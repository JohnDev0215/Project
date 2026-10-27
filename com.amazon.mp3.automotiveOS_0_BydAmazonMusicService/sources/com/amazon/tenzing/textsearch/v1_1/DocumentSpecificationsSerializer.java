package com.amazon.tenzing.textsearch.v1_1;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class DocumentSpecificationsSerializer extends JsonSerializer<List<DocumentSpecification>> {
    public static final JsonSerializer<List<DocumentSpecification>> INSTANCE = new DocumentSpecificationsSerializer();

    private DocumentSpecificationsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<DocumentSpecification> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<DocumentSpecification> it = list.iterator();
        while (it.hasNext()) {
            DocumentSpecificationSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
