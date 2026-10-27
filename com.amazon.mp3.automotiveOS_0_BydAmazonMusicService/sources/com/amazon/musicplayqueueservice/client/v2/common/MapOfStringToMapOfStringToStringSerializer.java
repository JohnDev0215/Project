package com.amazon.musicplayqueueservice.client.v2.common;

import com.amazon.musicplayqueueservice.model.StringToStringMapSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class MapOfStringToMapOfStringToStringSerializer extends JsonSerializer<Map<String, Map<String, String>>> {
    public static final JsonSerializer<Map<String, Map<String, String>>> INSTANCE = new MapOfStringToMapOfStringToStringSerializer();

    private MapOfStringToMapOfStringToStringSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, Map<String, String>> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            StringToStringMapSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
