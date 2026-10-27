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
public class SaveMetadataWriteElementDeserializer extends JsonDeserializer<SaveMetadataWriteElement> {
    public static final SaveMetadataWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SaveMetadataWriteElementDeserializer saveMetadataWriteElementDeserializer = new SaveMetadataWriteElementDeserializer();
        INSTANCE = saveMetadataWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.SaveInterface.v1_0.SaveMetadataWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SaveMetadataWriteElement.class, saveMetadataWriteElementDeserializer);
    }

    private SaveMetadataWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SaveMetadataWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.SaveInterface.v1_0#SaveMetadataWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SaveMetadataWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SaveMetadataWriteElement saveMetadataWriteElement = new SaveMetadataWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastId".equals(currentName)) {
                saveMetadataWriteElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                saveMetadataWriteElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                saveMetadataWriteElement.setAuthors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                saveMetadataWriteElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableUpsells".equals(currentName)) {
                saveMetadataWriteElement.setAvailableUpsells(AvailableUpsellsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                saveMetadataWriteElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                saveMetadataWriteElement.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                saveMetadataWriteElement.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                saveMetadataWriteElement.setSeasonNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                saveMetadataWriteElement.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availabilityDate".equals(currentName)) {
                saveMetadataWriteElement.setAvailabilityDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastTitle".equals(currentName)) {
                saveMetadataWriteElement.setPodcastTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastImage".equals(currentName)) {
                saveMetadataWriteElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTraits".equals(currentName)) {
                saveMetadataWriteElement.setContentTraits(ContentTraitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                saveMetadataWriteElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                saveMetadataWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return saveMetadataWriteElement;
    }
}
