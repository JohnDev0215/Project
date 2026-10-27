package SOACacheInterface.v1_0;

import Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElementDeserializer;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class WriteCacheElementDeserializer extends JsonDeserializer<WriteCacheElement> {
    public static final WriteCacheElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        WriteCacheElementDeserializer writeCacheElementDeserializer = new WriteCacheElementDeserializer();
        INSTANCE = writeCacheElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("SOACacheInterface.v1_0.WriteCacheElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(WriteCacheElement.class, writeCacheElementDeserializer);
    }

    private WriteCacheElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public WriteCacheElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Desktop.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Desktop.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.DetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.JumpBackInInterface.v1_0#JumpBackInWriteCacheElement".equals(text)) {
                return JumpBackInWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v2_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v2_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v3_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.PodcastDetailTemplateInterface.v3_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.LatestTemplateInterface.v1_0#FeaturedItemWriteCacheElement".equals(text)) {
                return Podcast.Touch.LatestTemplateInterface.v1_0.FeaturedItemWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.PTCTemplateInterface.v1_0#PTCWriteCacheElement".equals(text)) {
                return PTCWriteCacheElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("SOACacheInterface.v1_0#WriteCacheElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public WriteCacheElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        WriteCacheElement writeCacheElement = new WriteCacheElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("onInvalidated".equals(currentName)) {
                writeCacheElement.setOnInvalidated(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return writeCacheElement;
    }
}
