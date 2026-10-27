package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class PodcastEpisodeDeserializer extends JsonDeserializer<PodcastEpisode> {
    public static final PodcastEpisodeDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastEpisodeDeserializer podcastEpisodeDeserializer = new PodcastEpisodeDeserializer();
        INSTANCE = podcastEpisodeDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.PodcastEpisodeDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PodcastEpisode.class, podcastEpisodeDeserializer);
    }

    private PodcastEpisodeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PodcastEpisode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#PodcastEpisode".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PodcastEpisode deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PodcastEpisode podcastEpisode = new PodcastEpisode();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("audioUri".equals(currentName)) {
                podcastEpisode.setAudioUri(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryTitle".equals(currentName)) {
                podcastEpisode.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                podcastEpisode.setPublishDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowTitle".equals(currentName)) {
                podcastEpisode.setPodcastShowTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowImage".equals(currentName)) {
                podcastEpisode.setPodcastShowImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playbackMode".equals(currentName)) {
                podcastEpisode.setPlaybackMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                podcastEpisode.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                podcastEpisode.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("catalogId".equals(currentName)) {
                podcastEpisode.setCatalogId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                podcastEpisode.setDurationSeconds(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("availabilityDate".equals(currentName)) {
                podcastEpisode.setAvailabilityDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_SUBTITLE.equals(currentName)) {
                podcastEpisode.setSubtitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("availableUpsells".equals(currentName)) {
                podcastEpisode.setAvailableUpsells(PodcastAvailableUpsellsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastShowCatalogId".equals(currentName)) {
                podcastEpisode.setPodcastShowCatalogId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTraits".equals(currentName)) {
                podcastEpisode.setContentTraits(PodcastContentTraitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastEpisodeVariantId".equals(currentName)) {
                podcastEpisode.setPodcastEpisodeVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                podcastEpisode.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("podcastShowVariantId".equals(currentName)) {
                podcastEpisode.setPodcastShowVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                podcastEpisode.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                podcastEpisode.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                podcastEpisode.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                podcastEpisode.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return podcastEpisode;
    }
}
