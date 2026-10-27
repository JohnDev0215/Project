package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class ResultsSerializer extends JsonSerializer<List<Result>> {
    public static final JsonSerializer<List<Result>> INSTANCE = new ResultsSerializer();

    private ResultsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<Result> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<Result> it = list.iterator();
        while (it.hasNext()) {
            ResultSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
