package Podcast.Touch.WidgetsInterface.v1_0;

import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElementsDeserializer;
import SOACoreInterface.v1_0.MethodsDeserializer;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes.dex */
public class DescriptiveShowcaseElementDeserializer extends JsonDeserializer<DescriptiveShowcaseElement> {
    public static final DescriptiveShowcaseElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DescriptiveShowcaseElementDeserializer descriptiveShowcaseElementDeserializer = new DescriptiveShowcaseElementDeserializer();
        INSTANCE = descriptiveShowcaseElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(DescriptiveShowcaseElement.class, descriptiveShowcaseElementDeserializer);
    }

    private DescriptiveShowcaseElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public DescriptiveShowcaseElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.WidgetsInterface.v1_0#DescriptiveShowcaseElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public DescriptiveShowcaseElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DescriptiveShowcaseElement descriptiveShowcaseElement = new DescriptiveShowcaseElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                descriptiveShowcaseElement.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ITEMS.equals(currentName)) {
                descriptiveShowcaseElement.setItems(HorizontalItemElementsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                descriptiveShowcaseElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onViewed".equals(currentName)) {
                descriptiveShowcaseElement.setOnViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                descriptiveShowcaseElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onContentFirstViewed".equals(currentName)) {
                descriptiveShowcaseElement.setOnContentFirstViewed(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return descriptiveShowcaseElement;
    }
}
