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
public class ArtistDeserializer extends JsonDeserializer<Artist> {
    public static final ArtistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistDeserializer artistDeserializer = new ArtistDeserializer();
        INSTANCE = artistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.ArtistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Artist.class, artistDeserializer);
    }

    private ArtistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Artist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#Artist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Artist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Artist artist = new Artist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primaryTitle".equals(currentName)) {
                artist.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("percentileRank".equals(currentName)) {
                artist.setPercentileRank(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                artist.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFollowing".equals(currentName)) {
                artist.setIsFollowing(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                artist.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("heroImage".equals(currentName)) {
                artist.setHeroImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                artist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("roles".equals(currentName)) {
                artist.setRoles(RolesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("relationMetadata".equals(currentName)) {
                artist.setRelationMetadata(ArtistRelationMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("expirationTime".equals(currentName)) {
                artist.setExpirationTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("artistStationKey".equals(currentName)) {
                artist.setArtistStationKey(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                artist.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                artist.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contributorAsin".equals(currentName)) {
                artist.setContributorAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                artist.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                artist.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                artist.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("followerCountText".equals(currentName)) {
                artist.setFollowerCountText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("relatedEntities".equals(currentName)) {
                artist.setRelatedEntities(RelatedEntitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                artist.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tags".equals(currentName)) {
                artist.setTags(TagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return artist;
    }
}
