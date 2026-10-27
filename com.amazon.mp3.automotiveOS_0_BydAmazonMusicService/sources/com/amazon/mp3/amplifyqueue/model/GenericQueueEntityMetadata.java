package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class GenericQueueEntityMetadata {
    private final TrackAlbum album;
    private final List<TrackArtist> artists;
    private final AudioSource audioSource;
    private final QueueEntityCapability capabilities;
    private final ClientMetricsInfo clientMetricsInfo;
    private final Float durationInMs;
    private final String entityReferenceId;
    private final QueueEntityTypeEnum entityType;
    private final Boolean hasLyrics;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final ImageMetadata imageMetadata;
    private final List<MetricsContextTuple> metricsContext;
    private final MetricsSpecEnum metricsSpec;
    private final PlaybackInstruction playbackInstruction;
    private final RatingEnum rating;
    private final String subTitle;
    private final String title;

    public interface ArtistsStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements EntityReferenceIdStep, IdentifierStep, IdentifierTypeStep, EntityTypeStep, DurationInMsStep, TitleStep, ImageMetadataStep, ArtistsStep, HasLyricsStep, CapabilitiesStep, MetricsContextStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface DurationInMsStep {
    }

    public interface EntityReferenceIdStep {
    }

    public interface EntityTypeStep {
    }

    public interface HasLyricsStep {
    }

    public interface IdentifierStep {
    }

    public interface IdentifierTypeStep {
    }

    public interface ImageMetadataStep {
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

    public QueueEntityTypeEnum getEntityType() {
        return this.entityType;
    }

    public Float getDurationInMs() {
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

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public Boolean getHasLyrics() {
        return this.hasLyrics;
    }

    public QueueEntityCapability getCapabilities() {
        return this.capabilities;
    }

    public AudioSource getAudioSource() {
        return this.audioSource;
    }

    public PlaybackInstruction getPlaybackInstruction() {
        return this.playbackInstruction;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GenericQueueEntityMetadata.class != obj.getClass()) {
            return false;
        }
        GenericQueueEntityMetadata genericQueueEntityMetadata = (GenericQueueEntityMetadata) obj;
        return ObjectsCompat.equals(getEntityReferenceId(), genericQueueEntityMetadata.getEntityReferenceId()) && ObjectsCompat.equals(getIdentifier(), genericQueueEntityMetadata.getIdentifier()) && ObjectsCompat.equals(getIdentifierType(), genericQueueEntityMetadata.getIdentifierType()) && ObjectsCompat.equals(getEntityType(), genericQueueEntityMetadata.getEntityType()) && ObjectsCompat.equals(getDurationInMs(), genericQueueEntityMetadata.getDurationInMs()) && ObjectsCompat.equals(getTitle(), genericQueueEntityMetadata.getTitle()) && ObjectsCompat.equals(getSubTitle(), genericQueueEntityMetadata.getSubTitle()) && ObjectsCompat.equals(getImageMetadata(), genericQueueEntityMetadata.getImageMetadata()) && ObjectsCompat.equals(getRating(), genericQueueEntityMetadata.getRating()) && ObjectsCompat.equals(getAlbum(), genericQueueEntityMetadata.getAlbum()) && ObjectsCompat.equals(getArtists(), genericQueueEntityMetadata.getArtists()) && ObjectsCompat.equals(getHasLyrics(), genericQueueEntityMetadata.getHasLyrics()) && ObjectsCompat.equals(getCapabilities(), genericQueueEntityMetadata.getCapabilities()) && ObjectsCompat.equals(getAudioSource(), genericQueueEntityMetadata.getAudioSource()) && ObjectsCompat.equals(getPlaybackInstruction(), genericQueueEntityMetadata.getPlaybackInstruction()) && ObjectsCompat.equals(getMetricsContext(), genericQueueEntityMetadata.getMetricsContext()) && ObjectsCompat.equals(getClientMetricsInfo(), genericQueueEntityMetadata.getClientMetricsInfo()) && ObjectsCompat.equals(getMetricsSpec(), genericQueueEntityMetadata.getMetricsSpec());
    }

    public int hashCode() {
        return (getEntityReferenceId() + getIdentifier() + getIdentifierType() + getEntityType() + getDurationInMs() + getTitle() + getSubTitle() + getImageMetadata() + getRating() + getAlbum() + getArtists() + getHasLyrics() + getCapabilities() + getAudioSource() + getPlaybackInstruction() + getMetricsContext() + getClientMetricsInfo() + getMetricsSpec()).hashCode();
    }
}
