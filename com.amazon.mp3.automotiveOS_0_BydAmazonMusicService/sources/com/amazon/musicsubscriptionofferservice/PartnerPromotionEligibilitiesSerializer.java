package com.amazon.musicsubscriptionofferservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PartnerPromotionEligibilitiesSerializer extends JsonSerializer<List<PartnerPromotionEligibility>> {
    public static final JsonSerializer<List<PartnerPromotionEligibility>> INSTANCE = new PartnerPromotionEligibilitiesSerializer();

    private PartnerPromotionEligibilitiesSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<PartnerPromotionEligibility> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<PartnerPromotionEligibility> it = list.iterator();
        while (it.hasNext()) {
            PartnerPromotionEligibilitySerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
