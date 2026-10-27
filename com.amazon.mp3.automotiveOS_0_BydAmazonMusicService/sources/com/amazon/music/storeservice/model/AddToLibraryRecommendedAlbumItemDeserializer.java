package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class AddToLibraryRecommendedAlbumItemDeserializer extends JsonDeserializer<AddToLibraryRecommendedAlbumItem> {
    public static final AddToLibraryRecommendedAlbumItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AddToLibraryRecommendedAlbumItemDeserializer addToLibraryRecommendedAlbumItemDeserializer = new AddToLibraryRecommendedAlbumItemDeserializer();
        INSTANCE = addToLibraryRecommendedAlbumItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.AddToLibraryRecommendedAlbumItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AddToLibraryRecommendedAlbumItem.class, addToLibraryRecommendedAlbumItemDeserializer);
    }

    private AddToLibraryRecommendedAlbumItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AddToLibraryRecommendedAlbumItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#AddToLibraryRecommendedAlbumItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AddToLibraryRecommendedAlbumItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AddToLibraryRecommendedAlbumItem addToLibraryRecommendedAlbumItem = new AddToLibraryRecommendedAlbumItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("asin".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setPrice(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currency".equals(currentName)) {
                addToLibraryRecommendedAlbumItem.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return addToLibraryRecommendedAlbumItem;
    }
}
