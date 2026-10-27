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
public class SharedPlaylistMetadataDeserializer extends JsonDeserializer<SharedPlaylistMetadata> {
    public static final SharedPlaylistMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SharedPlaylistMetadataDeserializer sharedPlaylistMetadataDeserializer = new SharedPlaylistMetadataDeserializer();
        INSTANCE = sharedPlaylistMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.SharedPlaylistMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SharedPlaylistMetadata.class, sharedPlaylistMetadataDeserializer);
    }

    private SharedPlaylistMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SharedPlaylistMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return EntityPlaylistMetadataDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#SharedPlaylistMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SharedPlaylistMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SharedPlaylistMetadata sharedPlaylistMetadata = new SharedPlaylistMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("collaborativeMode".equals(currentName)) {
                sharedPlaylistMetadata.setCollaborativeMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sharedPlaylistId".equals(currentName)) {
                sharedPlaylistMetadata.setSharedPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("numFollowers".equals(currentName)) {
                sharedPlaylistMetadata.setNumFollowers(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("canRenamePlaylist".equals(currentName)) {
                sharedPlaylistMetadata.setCanRenamePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                sharedPlaylistMetadata.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                sharedPlaylistMetadata.setDurationSeconds(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("internalTags".equals(currentName)) {
                sharedPlaylistMetadata.setInternalTags(InternalTagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("generatedForCustomer".equals(currentName)) {
                sharedPlaylistMetadata.setGeneratedForCustomer(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                sharedPlaylistMetadata.setFourSquareArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                sharedPlaylistMetadata.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                sharedPlaylistMetadata.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("createdDate".equals(currentName)) {
                sharedPlaylistMetadata.setCreatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("profileId".equals(currentName)) {
                sharedPlaylistMetadata.setProfileId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                sharedPlaylistMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("goLiveDate".equals(currentName)) {
                sharedPlaylistMetadata.setGoLiveDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                sharedPlaylistMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isCustomerCurator".equals(currentName)) {
                sharedPlaylistMetadata.setIsCustomerCurator(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                sharedPlaylistMetadata.setBannerArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canEditContents".equals(currentName)) {
                sharedPlaylistMetadata.setCanEditContents(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                sharedPlaylistMetadata.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRemovePlaylist".equals(currentName)) {
                sharedPlaylistMetadata.setCanRemovePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("totalTrackCount".equals(currentName)) {
                sharedPlaylistMetadata.setTotalTrackCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                sharedPlaylistMetadata.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isCustomerFollowing".equals(currentName)) {
                sharedPlaylistMetadata.setIsCustomerFollowing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                sharedPlaylistMetadata.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                sharedPlaylistMetadata.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canReorderPlaylist".equals(currentName)) {
                sharedPlaylistMetadata.setCanReorderPlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                sharedPlaylistMetadata.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isShareableType".equals(currentName)) {
                sharedPlaylistMetadata.setIsShareableType(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("lastModifiedDate".equals(currentName)) {
                sharedPlaylistMetadata.setLastModifiedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return sharedPlaylistMetadata;
    }
}
