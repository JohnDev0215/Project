package Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailNavigatorItemElementsSerializer extends JsonSerializer<List<ThumbnailNavigatorItemElement>> {
    public static final JsonSerializer<List<ThumbnailNavigatorItemElement>> INSTANCE = new ThumbnailNavigatorItemElementsSerializer();

    private ThumbnailNavigatorItemElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<ThumbnailNavigatorItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<ThumbnailNavigatorItemElement> it = list.iterator();
        while (it.hasNext()) {
            ThumbnailNavigatorItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
