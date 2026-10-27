package Podcast.Touch.PTCTemplateInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CompactPillItemsSerializer extends JsonSerializer<List<CompactPillItemElement>> {
    public static final JsonSerializer<List<CompactPillItemElement>> INSTANCE = new CompactPillItemsSerializer();

    private CompactPillItemsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<CompactPillItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<CompactPillItemElement> it = list.iterator();
        while (it.hasNext()) {
            CompactPillItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
