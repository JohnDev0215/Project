package Podcast.Touch.VisualRowTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElementDeserializer;
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
public class VisualRowItemElementDeserializer extends JsonDeserializer<VisualRowItemElement> {
    public static final VisualRowItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VisualRowItemElementDeserializer visualRowItemElementDeserializer = new VisualRowItemElementDeserializer();
        INSTANCE = visualRowItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(VisualRowItemElement.class, visualRowItemElementDeserializer);
    }

    private VisualRowItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public VisualRowItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.Touch.VisualRowTemplateInterface.v1_0#VisualRowItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public VisualRowItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        VisualRowItemElement visualRowItemElement = new VisualRowItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.PRIMARY_TEXT.equals(currentName)) {
                visualRowItemElement.setPrimaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                visualRowItemElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isDisabled".equals(currentName)) {
                visualRowItemElement.setIsDisabled(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                visualRowItemElement.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("download".equals(currentName)) {
                visualRowItemElement.setDownload(DownloadElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                visualRowItemElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onImageSelected".equals(currentName)) {
                visualRowItemElement.setOnImageSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("bookmark".equals(currentName)) {
                visualRowItemElement.setBookmark(BookmarkElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.SECONDARY_TEXT.equals(currentName)) {
                visualRowItemElement.setSecondaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.TERTIARY_TEXT.equals(currentName)) {
                visualRowItemElement.setTertiaryText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("onItemSelected".equals(currentName)) {
                visualRowItemElement.setOnItemSelected(MethodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeOptions".equals(currentName)) {
                visualRowItemElement.setEpisodeOptions(EpisodeOptionsElementDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return visualRowItemElement;
    }
}
