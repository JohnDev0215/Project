package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
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

/* JADX INFO: loaded from: classes4.dex */
public class CatalogPodcastEpisodeDeserializer extends JsonDeserializer<CatalogPodcastEpisode> {
    public static final CatalogPodcastEpisodeDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogPodcastEpisodeDeserializer catalogPodcastEpisodeDeserializer = new CatalogPodcastEpisodeDeserializer();
        INSTANCE = catalogPodcastEpisodeDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogPodcastEpisodeDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogPodcastEpisode.class, catalogPodcastEpisodeDeserializer);
    }

    private CatalogPodcastEpisodeDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogPodcastEpisode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogPodcastEpisode".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogPodcastEpisode deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogPodcastEpisode catalogPodcastEpisode = new CatalogPodcastEpisode();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("duration".equals(currentName)) {
                catalogPodcastEpisode.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                catalogPodcastEpisode.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("episodeNum".equals(currentName)) {
                catalogPodcastEpisode.setEpisodeNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogPodcastEpisode.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("availabilityDisplayDate".equals(currentName)) {
                catalogPodcastEpisode.setAvailabilityDisplayDate(TierAvailabilityDisplayDateDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogPodcastEpisode.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("episodeType".equals(currentName)) {
                catalogPodcastEpisode.setEpisodeType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                catalogPodcastEpisode.setRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("seasonNum".equals(currentName)) {
                catalogPodcastEpisode.setSeasonNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogPodcastEpisode.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogPodcastEpisode.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("publishDate".equals(currentName)) {
                catalogPodcastEpisode.setPublishDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("show".equals(currentName)) {
                catalogPodcastEpisode.setShow(EpisodeShowDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tierBenefit".equals(currentName)) {
                catalogPodcastEpisode.setTierBenefit(EpisodeTierBenefitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("variantId".equals(currentName)) {
                catalogPodcastEpisode.setVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                catalogPodcastEpisode.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogPodcastEpisode;
    }
}
