package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GenericQueueEntity {
    private final TrackAlbum album;
    private final List<TrackArtist> artists;
    private final List<ContentEncodingEnum> availableContentEncodings;
    private final List<FixedSizeImage> backgroundArtistImages;
    private final QueueEntityCapability capabilities;
    private final ClientMetricsInfo clientMetricsInfo;
    private final String concatenatedArtistName;
    private final CuePoints cuePoints;
    private final Double durationInMs;
    private final String entityReferenceId;
    private final Boolean hasLyrics;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final String identifierTypeEnumValue;
    private final ImageMetadata imageMetadata;
    private final Boolean isInstantImport;
    private final Boolean isPurchased;
    private final Boolean isUploaded;
    private final List<String> libraryObjectIds;
    private final List<MetricsContextTuple> metricsContext;
    private final MetricsSpecEnum metricsSpec;
    private final ParentalControls parentalControls;
    private final String playableAsin;
    private final PlaybackAssetSource playbackAssetSource;
    private final QueueEntitySubtypeEnum queueEntitySubtype;
    private final String queueEntitySubtypeEnumValue;
    private final RatingEnum rating;
    private final String subTitle;
    private final String title;

    public interface ArtistsStep {
    }

    public interface AvailableContentEncodingsStep {
    }

    public interface BackgroundArtistImagesStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements EntityReferenceIdStep, IdentifierStep, IdentifierTypeStep, TitleStep, ImageMetadataStep, ArtistsStep, CapabilitiesStep, MetricsContextStep, AvailableContentEncodingsStep, LibraryObjectIdsStep, BackgroundArtistImagesStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface EntityReferenceIdStep {
    }

    public interface IdentifierStep {
    }

    public interface IdentifierTypeStep {
    }

    public interface ImageMetadataStep {
    }

    public interface LibraryObjectIdsStep {
    }

    public interface MetricsContextStep {
    }

    public interface TitleStep {
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
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

    public RatingEnum getRating() {
        return this.rating;
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

    public QueueEntityCapability getCapabilities() {
        return this.capabilities;
    }

    public PlaybackAssetSource getPlaybackAssetSource() {
        return this.playbackAssetSource;
    }

    public List<MetricsContextTuple> getMetricsContext() {
        return this.metricsContext;
    }

    public ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }

    public MetricsSpecEnum getMetricsSpec() {
        return this.metricsSpec;
    }

    public List<ContentEncodingEnum> getAvailableContentEncodings() {
        return this.availableContentEncodings;
    }

    public String getPlayableAsin() {
        return this.playableAsin;
    }

    public List<String> getLibraryObjectIds() {
        return this.libraryObjectIds;
    }

    public Boolean getIsPurchased() {
        return this.isPurchased;
    }

    public Boolean getIsInstantImport() {
        return this.isInstantImport;
    }

    public Boolean getIsUploaded() {
        return this.isUploaded;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public List<FixedSizeImage> getBackgroundArtistImages() {
        return this.backgroundArtistImages;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GenericQueueEntity.class != obj.getClass()) {
            return false;
        }
        GenericQueueEntity genericQueueEntity = (GenericQueueEntity) obj;
        return ObjectsCompat.equals(getEntityReferenceId(), genericQueueEntity.getEntityReferenceId()) && ObjectsCompat.equals(getIdentifier(), genericQueueEntity.getIdentifier()) && ObjectsCompat.equals(getIdentifierType(), genericQueueEntity.getIdentifierType()) && ObjectsCompat.equals(getIdentifierTypeEnumValue(), genericQueueEntity.getIdentifierTypeEnumValue()) && ObjectsCompat.equals(getQueueEntitySubtype(), genericQueueEntity.getQueueEntitySubtype()) && ObjectsCompat.equals(getQueueEntitySubtypeEnumValue(), genericQueueEntity.getQueueEntitySubtypeEnumValue()) && ObjectsCompat.equals(getDurationInMs(), genericQueueEntity.getDurationInMs()) && ObjectsCompat.equals(getTitle(), genericQueueEntity.getTitle()) && ObjectsCompat.equals(getSubTitle(), genericQueueEntity.getSubTitle()) && ObjectsCompat.equals(getImageMetadata(), genericQueueEntity.getImageMetadata()) && ObjectsCompat.equals(getRating(), genericQueueEntity.getRating()) && ObjectsCompat.equals(getAlbum(), genericQueueEntity.getAlbum()) && ObjectsCompat.equals(getConcatenatedArtistName(), genericQueueEntity.getConcatenatedArtistName()) && ObjectsCompat.equals(getArtists(), genericQueueEntity.getArtists()) && ObjectsCompat.equals(getHasLyrics(), genericQueueEntity.getHasLyrics()) && ObjectsCompat.equals(getCuePoints(), genericQueueEntity.getCuePoints()) && ObjectsCompat.equals(getCapabilities(), genericQueueEntity.getCapabilities()) && ObjectsCompat.equals(getPlaybackAssetSource(), genericQueueEntity.getPlaybackAssetSource()) && ObjectsCompat.equals(getMetricsContext(), genericQueueEntity.getMetricsContext()) && ObjectsCompat.equals(getClientMetricsInfo(), genericQueueEntity.getClientMetricsInfo()) && ObjectsCompat.equals(getMetricsSpec(), genericQueueEntity.getMetricsSpec()) && ObjectsCompat.equals(getAvailableContentEncodings(), genericQueueEntity.getAvailableContentEncodings()) && ObjectsCompat.equals(getPlayableAsin(), genericQueueEntity.getPlayableAsin()) && ObjectsCompat.equals(getLibraryObjectIds(), genericQueueEntity.getLibraryObjectIds()) && ObjectsCompat.equals(getIsPurchased(), genericQueueEntity.getIsPurchased()) && ObjectsCompat.equals(getIsInstantImport(), genericQueueEntity.getIsInstantImport()) && ObjectsCompat.equals(getIsUploaded(), genericQueueEntity.getIsUploaded()) && ObjectsCompat.equals(getParentalControls(), genericQueueEntity.getParentalControls()) && ObjectsCompat.equals(getBackgroundArtistImages(), genericQueueEntity.getBackgroundArtistImages());
    }

    public int hashCode() {
        return (getEntityReferenceId() + getIdentifier() + getIdentifierType() + getIdentifierTypeEnumValue() + getQueueEntitySubtype() + getQueueEntitySubtypeEnumValue() + getDurationInMs() + getTitle() + getSubTitle() + getImageMetadata() + getRating() + getAlbum() + getConcatenatedArtistName() + getArtists() + getHasLyrics() + getCuePoints() + getCapabilities() + getPlaybackAssetSource() + getMetricsContext() + getClientMetricsInfo() + getMetricsSpec() + getAvailableContentEncodings() + getPlayableAsin() + getLibraryObjectIds() + getIsPurchased() + getIsInstantImport() + getIsUploaded() + getParentalControls() + getBackgroundArtistImages()).hashCode();
    }
}
