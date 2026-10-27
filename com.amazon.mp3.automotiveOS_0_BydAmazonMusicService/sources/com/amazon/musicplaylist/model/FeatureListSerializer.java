package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class FeatureListSerializer extends JsonSerializer<List<String>> {
    public static final JsonSerializer<List<String>> INSTANCE = new FeatureListSerializer();

    private FeatureListSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            SimpleSerializers.serializeString(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
