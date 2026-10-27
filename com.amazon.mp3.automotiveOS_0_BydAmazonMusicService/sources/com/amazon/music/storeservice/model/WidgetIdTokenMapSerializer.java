package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetIdTokenMapSerializer extends JsonSerializer<Map<String, Integer>> {
    public static final JsonSerializer<Map<String, Integer>> INSTANCE = new WidgetIdTokenMapSerializer();

    private WidgetIdTokenMapSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, Integer> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SimpleSerializers.serializeInteger(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
