package SOACacheInterface.v1_0;

import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethodDeserializer;
import Podcast.Touch.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethodDeserializer;
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
public class InvalidateCacheMethodDeserializer extends JsonDeserializer<InvalidateCacheMethod> {
    public static final InvalidateCacheMethodDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        InvalidateCacheMethodDeserializer invalidateCacheMethodDeserializer = new InvalidateCacheMethodDeserializer();
        INSTANCE = invalidateCacheMethodDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACacheInterface.v1_0.InvalidateCacheMethodDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(InvalidateCacheMethod.class, invalidateCacheMethodDeserializer);
    }

    private InvalidateCacheMethodDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public InvalidateCacheMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#InvalidatePTCCacheMethod".equals(text)) {
                return InvalidatePTCCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#InvalidateJumpBackInCacheMethod".equals(text)) {
                return InvalidateJumpBackInCacheMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#InvalidateFeaturedItemMethod".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.InvalidateFeaturedItemMethodDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACacheInterface.v1_0#InvalidateCacheMethod".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public InvalidateCacheMethod deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
