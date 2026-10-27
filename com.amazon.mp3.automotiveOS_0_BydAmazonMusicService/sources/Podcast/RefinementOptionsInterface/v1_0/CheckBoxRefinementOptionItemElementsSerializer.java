package Podcast.RefinementOptionsInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxRefinementOptionItemElementsSerializer extends JsonSerializer<List<CheckBoxRefinementOptionItemElement>> {
    public static final JsonSerializer<List<CheckBoxRefinementOptionItemElement>> INSTANCE = new CheckBoxRefinementOptionItemElementsSerializer();

    private CheckBoxRefinementOptionItemElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<CheckBoxRefinementOptionItemElement> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<CheckBoxRefinementOptionItemElement> it = list.iterator();
        while (it.hasNext()) {
            CheckBoxRefinementOptionItemElementSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
