package com.amazon.musicplaylist.model;

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

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistMetadataDeserializer extends JsonDeserializer<PlaylistMetadata> {
    public static final PlaylistMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistMetadataDeserializer playlistMetadataDeserializer = new PlaylistMetadataDeserializer();
        INSTANCE = playlistMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.PlaylistMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaylistMetadata.class, playlistMetadataDeserializer);
    }

    private PlaylistMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaylistMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#PlaylistMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaylistMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaylistMetadata playlistMetadata = new PlaylistMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("profileId".equals(currentName)) {
                playlistMetadata.setProfileId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("collaborativeMode".equals(currentName)) {
                playlistMetadata.setCollaborativeMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                playlistMetadata.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                playlistMetadata.setBannerArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canRemovePlaylist".equals(currentName)) {
                playlistMetadata.setCanRemovePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                playlistMetadata.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                playlistMetadata.setIsMusicSubscription(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("canReorderPlaylist".equals(currentName)) {
                playlistMetadata.setCanReorderPlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                playlistMetadata.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("bannerImage".equals(currentName)) {
                playlistMetadata.setBannerImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isCustomerFollowing".equals(currentName)) {
                playlistMetadata.setIsCustomerFollowing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                playlistMetadata.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                playlistMetadata.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                playlistMetadata.setIsPrime(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                playlistMetadata.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isShareableType".equals(currentName)) {
                playlistMetadata.setIsShareableType(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("isDeleted".equals(currentName)) {
                playlistMetadata.setIsDeleted(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("lastModifiedDate".equals(currentName)) {
                playlistMetadata.setLastModifiedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                playlistMetadata.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRenamePlaylist".equals(currentName)) {
                playlistMetadata.setCanRenamePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                playlistMetadata.setDurationSeconds(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("sharedPlaylistId".equals(currentName)) {
                playlistMetadata.setSharedPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                playlistMetadata.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("totalTrackCount".equals(currentName)) {
                playlistMetadata.setTotalTrackCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("canEditContents".equals(currentName)) {
                playlistMetadata.setCanEditContents(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                playlistMetadata.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isNightwingOnDemandPlayable".equals(currentName)) {
                playlistMetadata.setIsNightwingOnDemandPlayable(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("socialSharingErrors".equals(currentName)) {
                playlistMetadata.setSocialSharingErrors(GenericStringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNightwing".equals(currentName)) {
                playlistMetadata.setIsNightwing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("fourSquareImage".equals(currentName)) {
                playlistMetadata.setFourSquareImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("numFollowers".equals(currentName)) {
                playlistMetadata.setNumFollowers(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                playlistMetadata.setFourSquareArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                playlistMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("autoPlaylistType".equals(currentName)) {
                playlistMetadata.setAutoPlaylistType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("createdDate".equals(currentName)) {
                playlistMetadata.setCreatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlistMetadata;
    }
}
