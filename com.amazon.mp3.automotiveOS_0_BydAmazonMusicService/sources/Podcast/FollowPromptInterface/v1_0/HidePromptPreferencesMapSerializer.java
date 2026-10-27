package Podcast.FollowPromptInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class HidePromptPreferencesMapSerializer extends JsonSerializer<Map<String, HidePromptPreferenceElement>> {
    public static final JsonSerializer<Map<String, HidePromptPreferenceElement>> INSTANCE = new HidePromptPreferencesMapSerializer();

    private HidePromptPreferencesMapSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, HidePromptPreferenceElement> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, HidePromptPreferenceElement> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            HidePromptPreferenceElementSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
