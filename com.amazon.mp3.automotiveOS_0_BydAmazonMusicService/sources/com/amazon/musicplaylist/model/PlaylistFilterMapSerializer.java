package com.amazon.musicplaylist.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistFilterMapSerializer extends JsonSerializer<Map<String, List<String>>> {
    public static final JsonSerializer<Map<String, List<String>>> INSTANCE = new PlaylistFilterMapSerializer();

    private PlaylistFilterMapSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, List<String>> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            GenericStringListSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
