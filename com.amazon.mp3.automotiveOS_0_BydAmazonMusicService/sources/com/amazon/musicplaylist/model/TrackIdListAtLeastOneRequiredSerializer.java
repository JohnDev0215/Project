package com.amazon.musicplaylist.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class TrackIdListAtLeastOneRequiredSerializer extends JsonSerializer<List<TrackId>> {
    public static final JsonSerializer<List<TrackId>> INSTANCE = new TrackIdListAtLeastOneRequiredSerializer();

    private TrackIdListAtLeastOneRequiredSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<TrackId> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<TrackId> it = list.iterator();
        while (it.hasNext()) {
            TrackIdSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
