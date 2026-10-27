package com.amazon.musicidentityservice.shared.model;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class IdentityFlagsMapSerializer extends JsonSerializer<Map<String, Boolean>> {
    public static final JsonSerializer<Map<String, Boolean>> INSTANCE = new IdentityFlagsMapSerializer();

    private IdentityFlagsMapSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, Boolean> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SimpleSerializers.serializeBoolean(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
