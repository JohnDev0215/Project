package Podcast.JumpBackInInterface.v1_0;

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
public class JumpBackInCacheItemElementDeserializer extends JsonDeserializer<JumpBackInCacheItemElement> {
    public static final JumpBackInCacheItemElementDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        JumpBackInCacheItemElementDeserializer jumpBackInCacheItemElementDeserializer = new JumpBackInCacheItemElementDeserializer();
        INSTANCE = jumpBackInCacheItemElementDeserializer;
        SimpleModule simpleModule = new SimpleModule("Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElementDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(JumpBackInCacheItemElement.class, jumpBackInCacheItemElementDeserializer);
    }

    private JumpBackInCacheItemElementDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public JumpBackInCacheItemElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("Podcast.JumpBackInInterface.v1_0#JumpBackInCacheItemElement".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public JumpBackInCacheItemElement deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JumpBackInCacheItemElement jumpBackInCacheItemElement = new JumpBackInCacheItemElement();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("podcastImage".equals(currentName)) {
                jumpBackInCacheItemElement.setPodcastImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableUpsells".equals(currentName)) {
                jumpBackInCacheItemElement.setAvailableUpsells(AvailableUpsellsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastId".equals(currentName)) {
                jumpBackInCacheItemElement.setPodcastId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("totalDurationMilliseconds".equals(currentName)) {
                jumpBackInCacheItemElement.setTotalDurationMilliseconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                jumpBackInCacheItemElement.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availabilityDate".equals(currentName)) {
                jumpBackInCacheItemElement.setAvailabilityDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                jumpBackInCacheItemElement.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("showDeeplink".equals(currentName)) {
                jumpBackInCacheItemElement.setShowDeeplink(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                jumpBackInCacheItemElement.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("seasonNumber".equals(currentName)) {
                jumpBackInCacheItemElement.setSeasonNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                jumpBackInCacheItemElement.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                jumpBackInCacheItemElement.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                jumpBackInCacheItemElement.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastTitle".equals(currentName)) {
                jumpBackInCacheItemElement.setPodcastTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTraits".equals(currentName)) {
                jumpBackInCacheItemElement.setContentTraits(ContentTraitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeId".equals(currentName)) {
                jumpBackInCacheItemElement.setEpisodeId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playDeeplink".equals(currentName)) {
                jumpBackInCacheItemElement.setPlayDeeplink(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                jumpBackInCacheItemElement.setAuthors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                jumpBackInCacheItemElement.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return jumpBackInCacheItemElement;
    }
}
