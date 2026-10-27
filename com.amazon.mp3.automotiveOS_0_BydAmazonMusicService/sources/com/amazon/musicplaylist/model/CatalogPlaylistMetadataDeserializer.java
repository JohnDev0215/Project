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
public class CatalogPlaylistMetadataDeserializer extends JsonDeserializer<CatalogPlaylistMetadata> {
    public static final CatalogPlaylistMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogPlaylistMetadataDeserializer catalogPlaylistMetadataDeserializer = new CatalogPlaylistMetadataDeserializer();
        INSTANCE = catalogPlaylistMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.CatalogPlaylistMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogPlaylistMetadata.class, catalogPlaylistMetadataDeserializer);
    }

    private CatalogPlaylistMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogPlaylistMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#CatalogPlaylistMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogPlaylistMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogPlaylistMetadata catalogPlaylistMetadata = new CatalogPlaylistMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("parentalControls".equals(currentName)) {
                catalogPlaylistMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("sharedPlaylistId".equals(currentName)) {
                catalogPlaylistMetadata.setSharedPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curationDescriptor".equals(currentName)) {
                catalogPlaylistMetadata.setCurationDescriptor(CurationDescriptorListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                catalogPlaylistMetadata.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canRenamePlaylist".equals(currentName)) {
                catalogPlaylistMetadata.setCanRenamePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                catalogPlaylistMetadata.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                catalogPlaylistMetadata.setDurationSeconds(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("generatedForCustomer".equals(currentName)) {
                catalogPlaylistMetadata.setGeneratedForCustomer(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                catalogPlaylistMetadata.setFourSquareArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                catalogPlaylistMetadata.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("internalTags".equals(currentName)) {
                catalogPlaylistMetadata.setInternalTags(InternalTagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogPlaylistMetadata.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("createdDate".equals(currentName)) {
                catalogPlaylistMetadata.setCreatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogPlaylistMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("goLiveDate".equals(currentName)) {
                catalogPlaylistMetadata.setGoLiveDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                catalogPlaylistMetadata.setIsPrime(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                catalogPlaylistMetadata.setBannerArt(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canEditContents".equals(currentName)) {
                catalogPlaylistMetadata.setCanEditContents(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                catalogPlaylistMetadata.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogPlaylistMetadata.setIsMusicSubscription(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("isNightwing".equals(currentName)) {
                catalogPlaylistMetadata.setIsNightwing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("isCustomerFollowing".equals(currentName)) {
                catalogPlaylistMetadata.setIsCustomerFollowing(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("canRemovePlaylist".equals(currentName)) {
                catalogPlaylistMetadata.setCanRemovePlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                catalogPlaylistMetadata.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("totalTrackCount".equals(currentName)) {
                catalogPlaylistMetadata.setTotalTrackCount(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                catalogPlaylistMetadata.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isNightwingOnDemandPlayable".equals(currentName)) {
                catalogPlaylistMetadata.setIsNightwingOnDemandPlayable(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                catalogPlaylistMetadata.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canReorderPlaylist".equals(currentName)) {
                catalogPlaylistMetadata.setCanReorderPlaylist(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("isShareableType".equals(currentName)) {
                catalogPlaylistMetadata.setIsShareableType(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("lastModifiedDate".equals(currentName)) {
                catalogPlaylistMetadata.setLastModifiedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogPlaylistMetadata;
    }
}
