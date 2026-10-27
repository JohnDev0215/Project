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
public class EntityPlaylistMetadataDeserializer extends JsonDeserializer<EntityPlaylistMetadata> {
    public static final EntityPlaylistMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EntityPlaylistMetadataDeserializer entityPlaylistMetadataDeserializer = new EntityPlaylistMetadataDeserializer();
        INSTANCE = entityPlaylistMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.EntityPlaylistMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EntityPlaylistMetadata.class, entityPlaylistMetadataDeserializer);
    }

    private EntityPlaylistMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EntityPlaylistMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#EntityPlaylistMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EntityPlaylistMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EntityPlaylistMetadata entityPlaylistMetadata = new EntityPlaylistMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("collaborativeMode".equals(currentName)) {
                entityPlaylistMetadata.setCollaborativeMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sharedPlaylistId".equals(currentName)) {
                entityPlaylistMetadata.setSharedPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("numFollowers".equals(currentName)) {
                entityPlaylistMetadata.setNumFollowers(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("canRenamePlaylist".equals(currentName)) {
                entityPlaylistMetadata.setCanRenamePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                entityPlaylistMetadata.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                entityPlaylistMetadata.setDurationSeconds(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("internalTags".equals(currentName)) {
                entityPlaylistMetadata.setInternalTags(InternalTagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("generatedForCustomer".equals(currentName)) {
                entityPlaylistMetadata.setGeneratedForCustomer(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                entityPlaylistMetadata.setFourSquareArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                entityPlaylistMetadata.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                entityPlaylistMetadata.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("createdDate".equals(currentName)) {
                entityPlaylistMetadata.setCreatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("profileId".equals(currentName)) {
                entityPlaylistMetadata.setProfileId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                entityPlaylistMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("goLiveDate".equals(currentName)) {
                entityPlaylistMetadata.setGoLiveDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                entityPlaylistMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isCustomerCurator".equals(currentName)) {
                entityPlaylistMetadata.setIsCustomerCurator(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                entityPlaylistMetadata.setBannerArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canEditContents".equals(currentName)) {
                entityPlaylistMetadata.setCanEditContents(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                entityPlaylistMetadata.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRemovePlaylist".equals(currentName)) {
                entityPlaylistMetadata.setCanRemovePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("totalTrackCount".equals(currentName)) {
                entityPlaylistMetadata.setTotalTrackCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                entityPlaylistMetadata.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isCustomerFollowing".equals(currentName)) {
                entityPlaylistMetadata.setIsCustomerFollowing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                entityPlaylistMetadata.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                entityPlaylistMetadata.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canReorderPlaylist".equals(currentName)) {
                entityPlaylistMetadata.setCanReorderPlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                entityPlaylistMetadata.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isShareableType".equals(currentName)) {
                entityPlaylistMetadata.setIsShareableType(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("lastModifiedDate".equals(currentName)) {
                entityPlaylistMetadata.setLastModifiedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return entityPlaylistMetadata;
    }
}
