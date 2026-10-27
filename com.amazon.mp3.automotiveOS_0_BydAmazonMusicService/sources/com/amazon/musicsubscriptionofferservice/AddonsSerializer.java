package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AddonsSerializer extends JsonSerializer<List<Addon>> {
    public static final JsonSerializer<List<Addon>> INSTANCE = new AddonsSerializer();

    private AddonsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<Addon> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<Addon> it = list.iterator();
        while (it.hasNext()) {
            AddonSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
