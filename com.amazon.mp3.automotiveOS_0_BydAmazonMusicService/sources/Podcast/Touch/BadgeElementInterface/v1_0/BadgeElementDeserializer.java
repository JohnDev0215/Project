package Podcast.Touch.BadgeElementInterface.v1_0;

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
public class BadgeElementDeserializer extends JsonDeserializer<BadgeElement> {
    public static final BadgeElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BadgeElementDeserializer badgeElementDeserializer = new BadgeElementDeserializer();
        INSTANCE = badgeElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.BadgeElementInterface.v1_0.BadgeElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(BadgeElement.class, badgeElementDeserializer);
    }

    private BadgeElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public BadgeElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.BadgeElementInterface.v1_0#BorderedBadgeElement".equals(text)) {
                return BorderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#UnborderedBadgeElement".equals(text)) {
                return UnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#AccentOutlineBadgeElement".equals(text)) {
                return AccentOutlineBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#LightUnborderedBadgeElement".equals(text)) {
                return LightUnborderedBadgeElementDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("Podcast.Touch.BadgeElementInterface.v1_0#BadgeElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public BadgeElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
