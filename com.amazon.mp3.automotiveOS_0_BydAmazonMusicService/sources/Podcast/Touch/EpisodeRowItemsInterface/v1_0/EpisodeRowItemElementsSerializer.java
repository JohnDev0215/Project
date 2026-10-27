package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeRowItemElementsSerializer extends JsonSerializer<List<EpisodeRowItemElement>> {
    public static final JsonSerializer<List<EpisodeRowItemElement>> INSTANCE = new EpisodeRowItemElementsSerializer();

    private EpisodeRowItemElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<EpisodeRowItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<EpisodeRowItemElement> it = list.iterator();
        while (it.hasNext()) {
            EpisodeRowItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
