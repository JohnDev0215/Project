package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeGroupItemElementsSerializer extends JsonSerializer<List<EpisodeGroupItemElement>> {
    public static final JsonSerializer<List<EpisodeGroupItemElement>> INSTANCE = new EpisodeGroupItemElementsSerializer();

    private EpisodeGroupItemElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<EpisodeGroupItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<EpisodeGroupItemElement> it = list.iterator();
        while (it.hasNext()) {
            EpisodeGroupItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
