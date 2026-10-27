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
public class OwnedPlaylistMetadataDeserializer extends JsonDeserializer<OwnedPlaylistMetadata> {
    public static final OwnedPlaylistMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        OwnedPlaylistMetadataDeserializer ownedPlaylistMetadataDeserializer = new OwnedPlaylistMetadataDeserializer();
        INSTANCE = ownedPlaylistMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.OwnedPlaylistMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(OwnedPlaylistMetadata.class, ownedPlaylistMetadataDeserializer);
    }

    private OwnedPlaylistMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public OwnedPlaylistMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#OwnedPlaylistMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public OwnedPlaylistMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        OwnedPlaylistMetadata ownedPlaylistMetadata = new OwnedPlaylistMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("sharedPlaylistId".equals(currentName)) {
                ownedPlaylistMetadata.setSharedPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                ownedPlaylistMetadata.setBannerArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canEditContents".equals(currentName)) {
                ownedPlaylistMetadata.setCanEditContents(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                ownedPlaylistMetadata.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRenamePlaylist".equals(currentName)) {
                ownedPlaylistMetadata.setCanRenamePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                ownedPlaylistMetadata.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRemovePlaylist".equals(currentName)) {
                ownedPlaylistMetadata.setCanRemovePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("totalTrackCount".equals(currentName)) {
                ownedPlaylistMetadata.setTotalTrackCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                ownedPlaylistMetadata.setDurationSeconds(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("internalTags".equals(currentName)) {
                ownedPlaylistMetadata.setInternalTags(InternalTagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("generatedForCustomer".equals(currentName)) {
                ownedPlaylistMetadata.setGeneratedForCustomer(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                ownedPlaylistMetadata.setFourSquareArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                ownedPlaylistMetadata.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                ownedPlaylistMetadata.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("socialSharingErrors".equals(currentName)) {
                ownedPlaylistMetadata.setSocialSharingErrors(GenericStringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                ownedPlaylistMetadata.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canReorderPlaylist".equals(currentName)) {
                ownedPlaylistMetadata.setCanReorderPlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("createdDate".equals(currentName)) {
                ownedPlaylistMetadata.setCreatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                ownedPlaylistMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isShareableType".equals(currentName)) {
                ownedPlaylistMetadata.setIsShareableType(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("lastModifiedDate".equals(currentName)) {
                ownedPlaylistMetadata.setLastModifiedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("goLiveDate".equals(currentName)) {
                ownedPlaylistMetadata.setGoLiveDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                ownedPlaylistMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return ownedPlaylistMetadata;
    }
}
