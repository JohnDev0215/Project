package SOACoreInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleSerializers;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ExtraSerializer extends JsonSerializer<Map<String, String>> {
    public static final JsonSerializer<Map<String, String>> INSTANCE = new ExtraSerializer();

    private ExtraSerializer() {
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
