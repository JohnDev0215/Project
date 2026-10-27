package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeHorizontalItemsSerializer extends JsonSerializer<List<EpisodeHorizontalItemElement>> {
    public static final JsonSerializer<List<EpisodeHorizontalItemElement>> INSTANCE = new EpisodeHorizontalItemsSerializer();

    private EpisodeHorizontalItemsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<EpisodeHorizontalItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<EpisodeHorizontalItemElement> it = list.iterator();
        while (it.hasNext()) {
            EpisodeHorizontalItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
