package Podcast.Touch.DetailTemplateInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DetailTemplateShardElementsSerializer extends JsonSerializer<List<DetailTemplateShardElement>> {
    public static final JsonSerializer<List<DetailTemplateShardElement>> INSTANCE = new DetailTemplateShardElementsSerializer();

    private DetailTemplateShardElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<DetailTemplateShardElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<DetailTemplateShardElement> it = list.iterator();
        while (it.hasNext()) {
            DetailTemplateShardElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
