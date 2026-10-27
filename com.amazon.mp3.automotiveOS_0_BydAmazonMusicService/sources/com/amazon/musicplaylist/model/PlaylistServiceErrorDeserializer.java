package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistServiceErrorDeserializer extends JsonDeserializer<PlaylistServiceError> {
    public static final PlaylistServiceErrorDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistServiceErrorDeserializer playlistServiceErrorDeserializer = new PlaylistServiceErrorDeserializer();
        INSTANCE = playlistServiceErrorDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.PlaylistServiceErrorDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaylistServiceError.class, playlistServiceErrorDeserializer);
    }

    private PlaylistServiceErrorDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaylistServiceError deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#DuplicateTracksError".equals(text)) {
                return DuplicateTracksErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ExpiredPageTokenError".equals(text)) {
                return ExpiredPageTokenErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#CustomerCloudPlayerMarketplaceMismatchedError".equals(text)) {
                return CustomerCloudPlayerMarketplaceMismatchedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ProhibitedCuratedByError".equals(text)) {
                return ProhibitedCuratedByErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#TrackFulfillmentRestrictionsViolatedError".equals(text)) {
                return TrackFulfillmentRestrictionsViolatedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#BadRequestError".equals(text)) {
                return BadRequestErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PrimeTrackRestrictionsViolatedError".equals(text)) {
                return PrimeTrackRestrictionsViolatedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#CustomerNotFoundError".equals(text)) {
                return CustomerNotFoundErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#DescriptionModerationFailedError".equals(text)) {
                return DescriptionModerationFailedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#TitleModerationFailedError".equals(text)) {
                return TitleModerationFailedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ModerationFailedError".equals(text)) {
                return ModerationFailedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#InvalidPageTokenError".equals(text)) {
                return InvalidPageTokenErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#LatestTermsOfUseUnacceptedError".equals(text)) {
                return LatestTermsOfUseUnacceptedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistFilteredError".equals(text)) {
                return PlaylistFilteredErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#InvalidParameterError".equals(text)) {
                return InvalidParameterErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#InvalidCheckpointError".equals(text)) {
                return InvalidCheckpointErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistSuppressedError".equals(text)) {
                return PlaylistSuppressedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#NonRetriableDependencyError".equals(text)) {
                return NonRetriableDependencyErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistNotFoundError".equals(text)) {
                return PlaylistNotFoundErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#TrackIdNotFoundError".equals(text)) {
                return TrackIdNotFoundErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PermissionsError".equals(text)) {
                return PermissionsErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#BatchSizeExceededError".equals(text)) {
                return BatchSizeExceededErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistVersionError".equals(text)) {
                return PlaylistVersionErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#DependencyError".equals(text)) {
                return DependencyErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ProfileHiddenError".equals(text)) {
                return ProfileHiddenErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistFullError".equals(text)) {
                return PlaylistFullErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#TrackIdUnavailableError".equals(text)) {
                return TrackIdUnavailableErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ContentValidationError".equals(text)) {
                return ContentValidationErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#AccountNotFoundError".equals(text)) {
                return AccountNotFoundErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#TooManyPlaylistsError".equals(text)) {
                return TooManyPlaylistsErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#AccountNotVerifiedError".equals(text)) {
                return AccountNotVerifiedErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#ServiceError".equals(text)) {
                return ServiceErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#CannotSharePlaylistError".equals(text)) {
                return CannotSharePlaylistErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#EntryIdNotFoundError".equals(text)) {
                return EntryIdNotFoundErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#InvalidBenefitsError".equals(text)) {
                return InvalidBenefitsErrorDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplaylist.model#PlaylistServiceError".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaylistServiceError deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaylistServiceError playlistServiceError = new PlaylistServiceError();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("message".equals(currentName)) {
                playlistServiceError.setMessage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlistServiceError;
    }
}
