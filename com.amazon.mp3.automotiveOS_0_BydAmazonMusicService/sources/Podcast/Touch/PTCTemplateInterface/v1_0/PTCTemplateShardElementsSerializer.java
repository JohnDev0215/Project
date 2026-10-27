package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOATemplateListInterface.v1_0.Shard;
import SOATemplateListInterface.v1_0.ShardSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class PTCTemplateShardElementsSerializer extends JsonSerializer<List<Shard>> {
    public static final JsonSerializer<List<Shard>> INSTANCE = new PTCTemplateShardElementsSerializer();

    private PTCTemplateShardElementsSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull List<Shard> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (list == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartArray();
        Iterator<Shard> it = list.iterator();
        while (it.hasNext()) {
            ShardSerializer.INSTANCE.serialize(it.next(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
