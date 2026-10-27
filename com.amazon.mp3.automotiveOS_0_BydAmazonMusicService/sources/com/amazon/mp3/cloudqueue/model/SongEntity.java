package com.amazon.mp3.cloudqueue.model;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.Rating;
import com.amazon.mp3.cloudqueue.SynchronizedPlayableEntity;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.musicplayqueueservice.client.common.ParentalControls;
import com.amazon.musicplayqueueservice.client.v2.track.BooleanActionStrategy;
import com.amazon.musicplayqueueservice.client.v2.track.Capability;
import com.amazon.musicplayqueueservice.client.v2.track.SongMetadata;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SongEntity extends SynchronizedPlayableEntity {
    private SongMetadata songMetadata;

    @GuardedBy("this")
    private Rating trackRating;

    public Boolean isPrime() {
        return Boolean.valueOf(UpsellType.PRIME.equals(this.songMetadata.getServiceTier()));
    }

    public Boolean isMusicSubscription() {
        return Boolean.valueOf("SUBSCRIPTION".equals(this.songMetadata.getServiceTier()));
    }

    public Boolean isNightWing() {
        return Boolean.valueOf("NIGHTWING".equals(this.songMetadata.getServiceTier()));
    }

    public String getTitle() {
        return this.songMetadata.getName();
    }

    public String getAlbumAsin() {
        return this.songMetadata.getAlbum().getAsin();
    }

    public String getAlbumArtImageUrl() {
        return this.songMetadata.getAlbumArt().get("FULL");
    }

    public String getArtistName() {
        return this.songMetadata.getArtistName();
    }

    public String getTrackArtistAsin() {
        if (this.songMetadata.getArtists() == null || this.songMetadata.getArtists().size() <= 0) {
            return null;
        }
        return this.songMetadata.getArtists().get(0).getAsin();
    }

    public String getAsin() {
        return this.songMetadata.getIdentifier();
    }

    public String getTrackId() {
        return this.songMetadata.getEntityReference();
    }

    public Double getTrackDuration() {
        return Double.valueOf(this.songMetadata.getDurationInSeconds());
    }

    public String getAlbumTitle() {
        return this.songMetadata.getAlbum().getName();
    }

    @Nullable
    public synchronized Rating getTrackRating() {
        return this.trackRating;
    }

    public Map<String, String> getMetricsContext() {
        return this.songMetadata.getMetricsContext();
    }

    public boolean isExplicit() {
        ParentalControls parentalControls = this.songMetadata.getParentalControls();
        if (parentalControls == null) {
            return false;
        }
        return parentalControls.isHasExplicitLanguage();
    }

    public synchronized Rating changeTrackRating(final Rating rating) {
        if (rating.equals(this.trackRating)) {
            this.trackRating = Rating.NEUTRAL;
        } else {
            this.trackRating = rating;
        }
        return this.trackRating;
    }

    public SongEntity(final SongMetadata base) {
        this.songMetadata = base;
        this.trackRating = base.getRating() == null ? Rating.NEUTRAL : Rating.valueOf(this.songMetadata.getRating());
        if (AmazonApplication.getCapabilities().isFreeTierScrubbingOnDemandPlaylistsEnabled() && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(base.getMetricsContext().get("selectionSourceId"))) {
            for (Capability capability : base.getCapabilities()) {
                if (capability.getAction().equals("SCRUB_BACKWARD") || capability.getAction().equals("SCRUB_FORWARD")) {
                    capability.setActionStrategy(BooleanActionStrategy.builder().withActionAvailable(true).build());
                }
            }
        }
        processActionMap(base.getCapabilities());
    }
}
