package Podcast.Touch.ChromeTemplateInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ChromeTemplateShardElementsSerializer extends JsonSerializer<List<ChromeTemplateShardElement>> {
    public static final JsonSerializer<List<ChromeTemplateShardElement>> INSTANCE = new ChromeTemplateShardElementsSerializer();

    private ChromeTemplateShardElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<ChromeTemplateShardElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<ChromeTemplateShardElement> it = list.iterator();
        while (it.hasNext()) {
            ChromeTemplateShardElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
