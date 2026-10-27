package Podcast.CategoryFollowInterface.v1_0;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class CategoryFollowsLastSyncTimeClientStateDeserializer extends JsonDeserializer<CategoryFollowsLastSyncTimeClientState> {
    public static final CategoryFollowsLastSyncTimeClientStateDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CategoryFollowsLastSyncTimeClientStateDeserializer categoryFollowsLastSyncTimeClientStateDeserializer = new CategoryFollowsLastSyncTimeClientStateDeserializer();
        INSTANCE = categoryFollowsLastSyncTimeClientStateDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.CategoryFollowInterface.v1_0.CategoryFollowsLastSyncTimeClientStateDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CategoryFollowsLastSyncTimeClientState.class, categoryFollowsLastSyncTimeClientStateDeserializer);
    }

    private CategoryFollowsLastSyncTimeClientStateDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CategoryFollowsLastSyncTimeClientState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("Podcast.CategoryFollowInterface.v1_0#CategoryFollowsLastSyncTimeClientState".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CategoryFollowsLastSyncTimeClientState deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CategoryFollowsLastSyncTimeClientState categoryFollowsLastSyncTimeClientState = new CategoryFollowsLastSyncTimeClientState();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("lastSyncTime".equals(currentName)) {
                categoryFollowsLastSyncTimeClientState.setLastSyncTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return categoryFollowsLastSyncTimeClientState;
    }
}
