package com.amazon.musicensembleservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataTypeListSerializer extends JsonSerializer<List<ArtistDetailsMetadataType>> {
    public static final JsonSerializer<List<ArtistDetailsMetadataType>> INSTANCE = new ArtistDetailsMetadataTypeListSerializer();

    private ArtistDetailsMetadataTypeListSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<ArtistDetailsMetadataType> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<ArtistDetailsMetadataType> it = list.iterator();
        while (it.hasNext()) {
            ArtistDetailsMetadataTypeSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
