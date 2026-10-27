package Podcast.DownloadInterface.v1_0;

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
public class DownloadWriteElementDeserializer extends JsonDeserializer<DownloadWriteElement> {
    public static final DownloadWriteElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DownloadWriteElementDeserializer downloadWriteElementDeserializer = new DownloadWriteElementDeserializer();
        INSTANCE = downloadWriteElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.DownloadInterface.v1_0.DownloadWriteElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(DownloadWriteElement.class, downloadWriteElementDeserializer);
    }

    private DownloadWriteElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public DownloadWriteElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.DownloadInterface.v1_0#DownloadWriteElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public DownloadWriteElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        DownloadWriteElement downloadWriteElement = new DownloadWriteElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastTitle".equals(currentName)) {
                downloadWriteElement.setPodcastTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("audioUri".equals(currentName)) {
                downloadWriteElement.setAudioUri(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableUpsells".equals(currentName)) {
                downloadWriteElement.setAvailableUpsells(AvailableUpsellsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                downloadWriteElement.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                downloadWriteElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                downloadWriteElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                downloadWriteElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastImage".equals(currentName)) {
                downloadWriteElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                downloadWriteElement.setAuthors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTraits".equals(currentName)) {
                downloadWriteElement.setContentTraits(ContentTraitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                downloadWriteElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                downloadWriteElement.setSeasonNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("state".equals(currentName)) {
                downloadWriteElement.setState(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                downloadWriteElement.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availabilityDate".equals(currentName)) {
                downloadWriteElement.setAvailabilityDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastId".equals(currentName)) {
                downloadWriteElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationMilliseconds".equals(currentName)) {
                downloadWriteElement.setDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                downloadWriteElement.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                downloadWriteElement.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("explicit".equals(currentName)) {
                downloadWriteElement.setExplicit(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return downloadWriteElement;
    }
}
