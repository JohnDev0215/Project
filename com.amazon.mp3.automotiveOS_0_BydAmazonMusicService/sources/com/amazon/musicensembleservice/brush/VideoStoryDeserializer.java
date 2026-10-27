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
public class VideoStoryDeserializer extends JsonDeserializer<VideoStory> {
    public static final VideoStoryDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VideoStoryDeserializer videoStoryDeserializer = new VideoStoryDeserializer();
        INSTANCE = videoStoryDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.VideoStoryDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(VideoStory.class, videoStoryDeserializer);
    }

    private VideoStoryDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public VideoStory deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#VideoStory".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public VideoStory deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        VideoStory videoStory = new VideoStory();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("genreName".equals(currentName)) {
                videoStory.setGenreName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryTitle".equals(currentName)) {
                videoStory.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("localOriginalReleaseDate".equals(currentName)) {
                videoStory.setLocalOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("isFeatured".equals(currentName)) {
                videoStory.setIsFeatured(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                videoStory.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetQualities".equals(currentName)) {
                videoStory.setAssetQualities(AssetQualitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                videoStory.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                videoStory.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                videoStory.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                videoStory.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("expirationTime".equals(currentName)) {
                videoStory.setExpirationTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                videoStory.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTiers".equals(currentName)) {
                videoStory.setContentTiers(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                videoStory.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                videoStory.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                videoStory.setDuration(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                videoStory.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                videoStory.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tags".equals(currentName)) {
                videoStory.setTags(TagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return videoStory;
    }
}
