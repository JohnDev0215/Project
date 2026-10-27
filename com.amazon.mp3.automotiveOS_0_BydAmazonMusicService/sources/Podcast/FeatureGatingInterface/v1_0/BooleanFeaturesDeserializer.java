package Podcast.FeatureGatingInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class BooleanFeaturesDeserializer extends JsonDeserializer<Map<String, Boolean>> {
    public static final JsonDeserializer<Map<String, Boolean>> INSTANCE = new BooleanFeaturesDeserializer();

    private BooleanFeaturesDeserializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Map<String, Boolean> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of map, got " + currentToken, jsonParser.getTokenLocation());
        }
        HashMap map = new HashMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + currentToken, jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            map.put(currentName, SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
        }
        return map;
    }
}
