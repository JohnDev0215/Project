package Podcast.JumpBackInInterface.v1_0;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInCacheItemElementsDeserializer extends JsonDeserializer<List<JumpBackInCacheItemElement>> {
    public static final JsonDeserializer<List<JumpBackInCacheItemElement>> INSTANCE = new JumpBackInCacheItemElementsDeserializer();

    private JumpBackInCacheItemElementsDeserializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public List<JumpBackInCacheItemElement> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_ARRAY) {
            throw new JsonParseException(jsonParser, "Expected start of array, got " + currentToken, jsonParser.getTokenLocation());
        }
        ArrayList arrayList = new ArrayList();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            if (jsonParser.isClosed()) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            arrayList.add(JumpBackInCacheItemElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
        }
        return arrayList;
    }
}
