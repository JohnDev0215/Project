package com.amazon.nimblymusicservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class HiddenRecentsListSerializer extends JsonSerializer<List<NimblyHideFromMostRecent>> {
    public static final JsonSerializer<List<NimblyHideFromMostRecent>> INSTANCE = new HiddenRecentsListSerializer();

    private HiddenRecentsListSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<NimblyHideFromMostRecent> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<NimblyHideFromMostRecent> it = list.iterator();
        while (it.hasNext()) {
            NimblyHideFromMostRecentSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
