package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceEligibilitiesSerializer extends JsonSerializer<List<DeviceEligibility>> {
    public static final JsonSerializer<List<DeviceEligibility>> INSTANCE = new DeviceEligibilitiesSerializer();

    private DeviceEligibilitiesSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<DeviceEligibility> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<DeviceEligibility> it = list.iterator();
        while (it.hasNext()) {
            DeviceEligibilitySerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
