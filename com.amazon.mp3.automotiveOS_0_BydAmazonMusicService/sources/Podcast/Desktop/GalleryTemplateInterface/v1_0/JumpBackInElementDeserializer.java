package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.find.utils.PageUriUtils;
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
public class JumpBackInElementDeserializer extends JsonDeserializer<JumpBackInElement> {
    public static final JumpBackInElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInElementDeserializer jumpBackInElementDeserializer = new JumpBackInElementDeserializer();
        INSTANCE = jumpBackInElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Desktop.GalleryTemplateInterface.v1_0.JumpBackInElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(JumpBackInElement.class, jumpBackInElementDeserializer);
    }

    private JumpBackInElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public JumpBackInElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Desktop.GalleryTemplateInterface.v1_0#JumpBackInElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public JumpBackInElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JumpBackInElement jumpBackInElement = new JumpBackInElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("header".equals(currentName)) {
                jumpBackInElement.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.DO_SEE_MORE_VALUE.equals(currentName)) {
                jumpBackInElement.setSeeMore(JumpBackInSeeMoreElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                jumpBackInElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return jumpBackInElement;
    }
}
