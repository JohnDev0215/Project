package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElementDeserializer;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElementDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
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
public class RefinementBarElementDeserializer extends JsonDeserializer<RefinementBarElement> {
    public static final RefinementBarElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RefinementBarElementDeserializer refinementBarElementDeserializer = new RefinementBarElementDeserializer();
        INSTANCE = refinementBarElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RefinementBarElement.class, refinementBarElementDeserializer);
    }

    private RefinementBarElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RefinementBarElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.PodcastDetailTemplateInterface.v1_0#RefinementBarElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RefinementBarElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RefinementBarElement refinementBarElement = new RefinementBarElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("onChangeWithDownloadFilter".equals(currentName)) {
                refinementBarElement.setOnChangeWithDownloadFilter(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seasonOptions".equals(currentName)) {
                refinementBarElement.setSeasonOptions(CheckBoxRefinementOptionElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("downloadFilter".equals(currentName)) {
                refinementBarElement.setDownloadFilter(ToggleRefinementOptionElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("timeout".equals(currentName)) {
                refinementBarElement.setTimeout(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("podcastId".equals(currentName)) {
                refinementBarElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sortingOptions".equals(currentName)) {
                refinementBarElement.setSortingOptions(CheckBoxRefinementOptionElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("onChange".equals(currentName)) {
                refinementBarElement.setOnChange(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("clientStateName".equals(currentName)) {
                refinementBarElement.setClientStateName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("unplayedFilter".equals(currentName)) {
                refinementBarElement.setUnplayedFilter(ToggleRefinementOptionElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return refinementBarElement;
    }
}
