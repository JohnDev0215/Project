package Podcast.EpisodeOptionsInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class FollowStatusMapSerializer extends JsonSerializer<Map<String, String>> {
    public static final JsonSerializer<Map<String, String>> INSTANCE = new FollowStatusMapSerializer();

    private FollowStatusMapSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, String> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            SimpleSerializers.serializeString(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
