package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SalesPromotionsSerializer extends JsonSerializer<List<SalesPromotionInfo>> {
    public static final JsonSerializer<List<SalesPromotionInfo>> INSTANCE = new SalesPromotionsSerializer();

    private SalesPromotionsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<SalesPromotionInfo> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<SalesPromotionInfo> it = list.iterator();
        while (it.hasNext()) {
            SalesPromotionInfoSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
