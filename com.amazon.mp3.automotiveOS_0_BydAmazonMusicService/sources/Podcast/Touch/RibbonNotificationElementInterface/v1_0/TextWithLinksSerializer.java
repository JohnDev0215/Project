package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class TextWithLinksSerializer extends JsonSerializer<List<TextWithLinkElement>> {
    public static final JsonSerializer<List<TextWithLinkElement>> INSTANCE = new TextWithLinksSerializer();

    private TextWithLinksSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<TextWithLinkElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<TextWithLinkElement> it = list.iterator();
        while (it.hasNext()) {
            TextWithLinkElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
