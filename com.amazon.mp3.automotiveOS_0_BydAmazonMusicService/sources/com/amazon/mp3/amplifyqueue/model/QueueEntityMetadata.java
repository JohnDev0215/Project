package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"customerId", "queueId", "id"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "QueueEntityMetadata")
public final class QueueEntityMetadata implements Model {

    @ModelField(targetType = "TrackAlbum")
    private final TrackAlbum album;

    @ModelField(isRequired = true, targetType = "TrackArtist")
    private final List<TrackArtist> artists;

    @ModelField(isRequired = true, targetType = "ContentEncodingEnum")
    private final List<ContentEncodingEnum> availableContentEncodings;

    @ModelField(targetType = "String")
    private final String concatenatedArtistName;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(targetType = "CuePoints")
    private final CuePoints cuePoints;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(targetType = "Float")
    private final Double durationInMs;

    @ModelField(targetType = "Boolean")
    private final Boolean hasLyrics;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "ID")
    private final String identifier;

    @ModelField(isRequired = true, targetType = "QueueEntityIdTypeEnum")
    private final QueueEntityIdTypeEnum identifierType;

    @ModelField(targetType = "String")
    private final String identifierTypeEnumValue;

    @ModelField(targetType = "ImageMetadata")
    private final ImageMetadata imageMetadata;

    @ModelField(targetType = "ParentalControls")
    private final ParentalControls parentalControls;

    @ModelField(targetType = "String")
    private final String playableAsin;

    @ModelField(targetType = "PlaybackAssetSource")
    private final PlaybackAssetSource playbackAssetSource;

    @ModelField(targetType = "QueueEntitySubtypeEnum")
    private final QueueEntitySubtypeEnum queueEntitySubtype;

    @ModelField(targetType = "String")
    private final String queueEntitySubtypeEnumValue;

    @ModelField(isRequired = true, targetType = "ID")
    private final String queueId;

    @ModelField(targetType = "RatingEnum")
    private final RatingEnum rating;

    @ModelField(targetType = "String")
    private final String subTitle;

    @ModelField(targetType = "String")
    private final String title;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("QueueEntityMetadata", "id");
    public static final QueryField CUSTOMER_ID = QueryField.field("QueueEntityMetadata", "customerId");
    public static final QueryField QUEUE_ID = QueryField.field("QueueEntityMetadata", "queueId");
    public static final QueryField IDENTIFIER = QueryField.field("QueueEntityMetadata", "identifier");
    public static final QueryField IDENTIFIER_TYPE = QueryField.field("QueueEntityMetadata", "identifierType");
    public static final QueryField IDENTIFIER_TYPE_ENUM_VALUE = QueryField.field("QueueEntityMetadata", "identifierTypeEnumValue");
    public static final QueryField QUEUE_ENTITY_SUBTYPE = QueryField.field("QueueEntityMetadata", "queueEntitySubtype");
    public static final QueryField QUEUE_ENTITY_SUBTYPE_ENUM_VALUE = QueryField.field("QueueEntityMetadata", "queueEntitySubtypeEnumValue");
    public static final QueryField DURATION_IN_MS = QueryField.field("QueueEntityMetadata", "durationInMs");
    public static final QueryField TITLE = QueryField.field("QueueEntityMetadata", "title");
    public static final QueryField SUB_TITLE = QueryField.field("QueueEntityMetadata", "subTitle");
    public static final QueryField IMAGE_METADATA = QueryField.field("QueueEntityMetadata", "imageMetadata");
    public static final QueryField PARENTAL_CONTROLS = QueryField.field("QueueEntityMetadata", "parentalControls");
    public static final QueryField ALBUM = QueryField.field("QueueEntityMetadata", "album");
    public static final QueryField CONCATENATED_ARTIST_NAME = QueryField.field("QueueEntityMetadata", "concatenatedArtistName");
    public static final QueryField ARTISTS = QueryField.field("QueueEntityMetadata", "artists");
    public static final QueryField HAS_LYRICS = QueryField.field("QueueEntityMetadata", "hasLyrics");
    public static final QueryField CUE_POINTS = QueryField.field("QueueEntityMetadata", "cuePoints");
    public static final QueryField PLAYBACK_ASSET_SOURCE = QueryField.field("QueueEntityMetadata", "playbackAssetSource");
    public static final QueryField RATING = QueryField.field("QueueEntityMetadata", "rating");
    public static final QueryField AVAILABLE_CONTENT_ENCODINGS = QueryField.field("QueueEntityMetadata", "availableContentEncodings");
    public static final QueryField PLAYABLE_ASIN = QueryField.field("QueueEntityMetadata", "playableAsin");
    public static final QueryField CREATED_AT = QueryField.field("QueueEntityMetadata", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("QueueEntityMetadata", "updatedAt");

    public interface ArtistsStep {
    }

    public interface AvailableContentEncodingsStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements CustomerIdStep, QueueIdStep, IdentifierStep, IdentifierTypeStep, ArtistsStep, AvailableContentEncodingsStep, CreatedAtStep, UpdatedAtStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
    }

    public interface CustomerIdStep {
    }

    public interface IdentifierStep {
    }

    public interface IdentifierTypeStep {
    }

    public interface QueueIdStep {
    }

    public interface UpdatedAtStep {
    }

    public String getId() {
        return this.id;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public QueueEntityIdTypeEnum getIdentifierType() {
        return this.identifierType;
    }

    public String getIdentifierTypeEnumValue() {
        return this.identifierTypeEnumValue;
    }

    public QueueEntitySubtypeEnum getQueueEntitySubtype() {
        return this.queueEntitySubtype;
    }

    public String getQueueEntitySubtypeEnumValue() {
        return this.queueEntitySubtypeEnumValue;
    }

    public Double getDurationInMs() {
        return this.durationInMs;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public ImageMetadata getImageMetadata() {
        return this.imageMetadata;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public TrackAlbum getAlbum() {
        return this.album;
    }

    public String getConcatenatedArtistName() {
        return this.concatenatedArtistName;
    }

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public Boolean getHasLyrics() {
        return this.hasLyrics;
    }

    public CuePoints getCuePoints() {
        return this.cuePoints;
    }

    public PlaybackAssetSource getPlaybackAssetSource() {
        return this.playbackAssetSource;
    }

    public RatingEnum getRating() {
        return this.rating;
    }

    public List<ContentEncodingEnum> getAvailableContentEncodings() {
        return this.availableContentEncodings;
    }

    public String getPlayableAsin() {
        return this.playableAsin;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueEntityMetadata.class != obj.getClass()) {
            return false;
        }
        QueueEntityMetadata queueEntityMetadata = (QueueEntityMetadata) obj;
        return ObjectsCompat.equals(getId(), queueEntityMetadata.getId()) && ObjectsCompat.equals(getCustomerId(), queueEntityMetadata.getCustomerId()) && ObjectsCompat.equals(getQueueId(), queueEntityMetadata.getQueueId()) && ObjectsCompat.equals(getIdentifier(), queueEntityMetadata.getIdentifier()) && ObjectsCompat.equals(getIdentifierType(), queueEntityMetadata.getIdentifierType()) && ObjectsCompat.equals(getIdentifierTypeEnumValue(), queueEntityMetadata.getIdentifierTypeEnumValue()) && ObjectsCompat.equals(getQueueEntitySubtype(), queueEntityMetadata.getQueueEntitySubtype()) && ObjectsCompat.equals(getQueueEntitySubtypeEnumValue(), queueEntityMetadata.getQueueEntitySubtypeEnumValue()) && ObjectsCompat.equals(getDurationInMs(), queueEntityMetadata.getDurationInMs()) && ObjectsCompat.equals(getTitle(), queueEntityMetadata.getTitle()) && ObjectsCompat.equals(getSubTitle(), queueEntityMetadata.getSubTitle()) && ObjectsCompat.equals(getImageMetadata(), queueEntityMetadata.getImageMetadata()) && ObjectsCompat.equals(getParentalControls(), queueEntityMetadata.getParentalControls()) && ObjectsCompat.equals(getAlbum(), queueEntityMetadata.getAlbum()) && ObjectsCompat.equals(getConcatenatedArtistName(), queueEntityMetadata.getConcatenatedArtistName()) && ObjectsCompat.equals(getArtists(), queueEntityMetadata.getArtists()) && ObjectsCompat.equals(getHasLyrics(), queueEntityMetadata.getHasLyrics()) && ObjectsCompat.equals(getCuePoints(), queueEntityMetadata.getCuePoints()) && ObjectsCompat.equals(getPlaybackAssetSource(), queueEntityMetadata.getPlaybackAssetSource()) && ObjectsCompat.equals(getRating(), queueEntityMetadata.getRating()) && ObjectsCompat.equals(getAvailableContentEncodings(), queueEntityMetadata.getAvailableContentEncodings()) && ObjectsCompat.equals(getPlayableAsin(), queueEntityMetadata.getPlayableAsin()) && ObjectsCompat.equals(getCreatedAt(), queueEntityMetadata.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queueEntityMetadata.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getCustomerId() + getQueueId() + getIdentifier() + getIdentifierType() + getIdentifierTypeEnumValue() + getQueueEntitySubtype() + getQueueEntitySubtypeEnumValue() + getDurationInMs() + getTitle() + getSubTitle() + getImageMetadata() + getParentalControls() + getAlbum() + getConcatenatedArtistName() + getArtists() + getHasLyrics() + getCuePoints() + getPlaybackAssetSource() + getRating() + getAvailableContentEncodings() + getPlayableAsin() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueEntityMetadata {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("queueId=" + String.valueOf(getQueueId()) + ", ");
        sb.append("identifier=" + String.valueOf(getIdentifier()) + ", ");
        sb.append("identifierType=" + String.valueOf(getIdentifierType()) + ", ");
        sb.append("identifierTypeEnumValue=" + String.valueOf(getIdentifierTypeEnumValue()) + ", ");
        sb.append("queueEntitySubtype=" + String.valueOf(getQueueEntitySubtype()) + ", ");
        sb.append("queueEntitySubtypeEnumValue=" + String.valueOf(getQueueEntitySubtypeEnumValue()) + ", ");
        sb.append("durationInMs=" + String.valueOf(getDurationInMs()) + ", ");
        sb.append("title=" + String.valueOf(getTitle()) + ", ");
        sb.append("subTitle=" + String.valueOf(getSubTitle()) + ", ");
        sb.append("imageMetadata=" + String.valueOf(getImageMetadata()) + ", ");
        sb.append("parentalControls=" + String.valueOf(getParentalControls()) + ", ");
        sb.append("album=" + String.valueOf(getAlbum()) + ", ");
        sb.append("concatenatedArtistName=" + String.valueOf(getConcatenatedArtistName()) + ", ");
        sb.append("artists=" + String.valueOf(getArtists()) + ", ");
        sb.append("hasLyrics=" + String.valueOf(getHasLyrics()) + ", ");
        sb.append("cuePoints=" + String.valueOf(getCuePoints()) + ", ");
        sb.append("playbackAssetSource=" + String.valueOf(getPlaybackAssetSource()) + ", ");
        sb.append("rating=" + String.valueOf(getRating()) + ", ");
        sb.append("availableContentEncodings=" + String.valueOf(getAvailableContentEncodings()) + ", ");
        sb.append("playableAsin=" + String.valueOf(getPlayableAsin()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
