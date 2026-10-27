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
public class CatalogPodcastShowDeserializer extends JsonDeserializer<CatalogPodcastShow> {
    public static final CatalogPodcastShowDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogPodcastShowDeserializer catalogPodcastShowDeserializer = new CatalogPodcastShowDeserializer();
        INSTANCE = catalogPodcastShowDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogPodcastShowDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogPodcastShow.class, catalogPodcastShowDeserializer);
    }

    private CatalogPodcastShowDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogPodcastShow deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogPodcastShow".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogPodcastShow deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogPodcastShow catalogPodcastShow = new CatalogPodcastShow();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("contentTier".equals(currentName)) {
                catalogPodcastShow.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("provider".equals(currentName)) {
                catalogPodcastShow.setProvider(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                catalogPodcastShow.setRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogPodcastShow.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("variantId".equals(currentName)) {
                catalogPodcastShow.setVariantId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogPodcastShow.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogPodcastShow.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("episodeCount".equals(currentName)) {
                catalogPodcastShow.setEpisodeCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("category".equals(currentName)) {
                catalogPodcastShow.setCategory(PodcastCategoriesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seasonCount".equals(currentName)) {
                catalogPodcastShow.setSeasonCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                catalogPodcastShow.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("id".equals(currentName)) {
                catalogPodcastShow.setId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tierBenefit".equals(currentName)) {
                catalogPodcastShow.setTierBenefit(PodcastTierBenefitsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogPodcastShow;
    }
}
