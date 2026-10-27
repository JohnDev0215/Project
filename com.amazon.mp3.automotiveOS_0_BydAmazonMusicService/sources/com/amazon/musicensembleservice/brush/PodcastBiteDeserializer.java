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
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class PodcastBiteDeserializer extends JsonDeserializer<PodcastBite> {
    public static final PodcastBiteDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PodcastBiteDeserializer podcastBiteDeserializer = new PodcastBiteDeserializer();
        INSTANCE = podcastBiteDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.PodcastBiteDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PodcastBite.class, podcastBiteDeserializer);
    }

    private PodcastBiteDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PodcastBite deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#PodcastBite".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PodcastBite deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PodcastBite podcastBite = new PodcastBite();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primaryTitle".equals(currentName)) {
                podcastBite.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("audioUri".equals(currentName)) {
                podcastBite.setAudioUri(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("catalogId".equals(currentName)) {
                podcastBite.setCatalogId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                podcastBite.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                podcastBite.setDurationSeconds(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                podcastBite.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("podcastShowCatalogId".equals(currentName)) {
                podcastBite.setPodcastShowCatalogId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastEpisodeCatalogId".equals(currentName)) {
                podcastBite.setPodcastEpisodeCatalogId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                podcastBite.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                podcastBite.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                podcastBite.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                podcastBite.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastEpisodeTitle".equals(currentName)) {
                podcastBite.setPodcastEpisodeTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("podcastShowTitle".equals(currentName)) {
                podcastBite.setPodcastShowTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                podcastBite.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return podcastBite;
    }
}
