package Podcast.JumpBackInInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInCacheItemElementsSerializer extends JsonSerializer<List<JumpBackInCacheItemElement>> {
    public static final JsonSerializer<List<JumpBackInCacheItemElement>> INSTANCE = new JumpBackInCacheItemElementsSerializer();

    private JumpBackInCacheItemElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<JumpBackInCacheItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<JumpBackInCacheItemElement> it = list.iterator();
        while (it.hasNext()) {
            JumpBackInCacheItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
