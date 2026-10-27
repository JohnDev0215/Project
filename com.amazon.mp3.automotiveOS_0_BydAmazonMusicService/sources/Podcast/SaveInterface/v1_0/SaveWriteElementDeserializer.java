package Podcast.SaveInterface.v1_0;

import Podcast.UpsellInterface.v1_0.AvailableUpsellsDeserializer;
import Podcast.UpsellInterface.v1_0.ContentTraitsDeserializer;
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
public class SaveWriteElementDeserializer extends JsonDeserializer<SaveWriteElement> {
    public static final SaveWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveWriteElementDeserializer saveWriteElementDeserializer = new SaveWriteElementDeserializer();
        INSTANCE = saveWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SaveWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SaveWriteElement.class, saveWriteElementDeserializer);
    }

    private SaveWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SaveWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.SaveInterface.v1_0#SaveWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SaveWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SaveWriteElement saveWriteElement = new SaveWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastShowVariantId".equals(currentName)) {
                saveWriteElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("saved".equals(currentName)) {
                saveWriteElement.setSaved(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                saveWriteElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTraits".equals(currentName)) {
                saveWriteElement.setContentTraits(ContentTraitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                saveWriteElement.setSeasonNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                saveWriteElement.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastId".equals(currentName)) {
                saveWriteElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                saveWriteElement.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("totalDurationMilliseconds".equals(currentName)) {
                saveWriteElement.setTotalDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("availabilityDate".equals(currentName)) {
                saveWriteElement.setAvailabilityDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableUpsells".equals(currentName)) {
                saveWriteElement.setAvailableUpsells(AvailableUpsellsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastImage".equals(currentName)) {
                saveWriteElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                saveWriteElement.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                saveWriteElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                saveWriteElement.setAuthors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                saveWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastTitle".equals(currentName)) {
                saveWriteElement.setPodcastTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                saveWriteElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return saveWriteElement;
    }
}
