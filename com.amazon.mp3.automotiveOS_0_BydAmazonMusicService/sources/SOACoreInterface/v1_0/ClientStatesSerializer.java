package SOACoreInterface.v1_0;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class ClientStatesSerializer extends JsonSerializer<Map<String, ClientState>> {
    public static final JsonSerializer<Map<String, ClientState>> INSTANCE = new ClientStatesSerializer();

    private ClientStatesSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@CheckForNull Map<String, ClientState> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (map == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        for (Map.Entry<String, ClientState> entry : map.entrySet()) {
            jsonGenerator.writeFieldName(entry.getKey());
            ClientStateSerializer.INSTANCE.serialize(entry.getValue(), jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }
}
