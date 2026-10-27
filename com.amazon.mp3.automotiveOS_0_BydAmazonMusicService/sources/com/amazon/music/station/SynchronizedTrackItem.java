package com.amazon.music.station;

import UpsellInterface.v1_0.UpsellType;
import androidx.annotation.AnyThread;
import androidx.annotation.GuardedBy;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.musicplayqueueservice.client.common.ParentalControls;
import com.amazon.musicplayqueueservice.client.common.TrackMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
@AnyThread
public class SynchronizedTrackItem {
    private final Marketplace marketplace;
    private final String stationId;
    private final TrackMetadata trackItem;

    @GuardedBy("this")
    private Rating trackRating;

    static List<SynchronizedTrackItem> createAll(List<TrackMetadata> list, Marketplace marketplace, String str) {
        Validate.notNull(list, "trackItems can't be null", new Object[0]);
        Validate.notNull(marketplace, "marketplace can't be null", new Object[0]);
        Validate.notEmpty(str, "stationId can't be null", new Object[0]);
        ArrayList arrayList = new ArrayList();
        Iterator<TrackMetadata> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new SynchronizedTrackItem(it.next(), marketplace, str));
        }
        return arrayList;
    }

    public Boolean isPrime() {
        return Boolean.valueOf(UpsellType.PRIME.equals(this.trackItem.getServiceTier()));
    }

    public Boolean isMusicSubscription() {
        return Boolean.valueOf("SUBSCRIPTION".equals(this.trackItem.getServiceTier()));
    }

    public String getTitle() {
        return this.trackItem.getName();
    }

    public String getAlbumAsin() {
        return this.trackItem.getAlbum() == null ? "" : this.trackItem.getAlbum().getAsin();
    }

    public String getIdentifierType() {
        return this.trackItem.getIdentifierType();
    }

    public String getStreamingURL() {
        return this.trackItem.getStreamingURL();
    }

    public String getAlbumArtImageUrl() {
        return this.trackItem.getArtUrlMap().get("LARGE");
    }

    public String getArtistName() {
        return this.trackItem.getArtistName();
    }

    public String getTrackArtistAsin() {
        if (this.trackItem.getArtists() == null || this.trackItem.getArtists().size() <= 0) {
            return null;
        }
        return this.trackItem.getArtists().get(0).getAsin();
    }

    public String getIdentifier() {
        return this.trackItem.getIdentifier();
    }

    public String getTrackId() {
        return this.trackItem.getTrackId();
    }

    public Double getTrackDuration() {
        return Double.valueOf(this.trackItem.getDurationInSeconds());
    }

    public String getAlbumTitle() {
        return this.trackItem.getAlbum() == null ? "" : this.trackItem.getAlbum().getName();
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public String getStationId() {
        return this.stationId;
    }

    public synchronized Rating getTrackRating() {
        return this.trackRating;
    }

    public Map<String, String> getMetricsContext() {
        return this.trackItem.getMetricsContext();
    }

    public boolean isExplicit() {
        ParentalControls parentalControls = this.trackItem.getParentalControls();
        if (parentalControls == null) {
            return false;
        }
        return parentalControls.isHasExplicitLanguage();
    }

    synchronized Rating changeTrackRating(Rating rating) {
        if (rating.equals(this.trackRating)) {
            this.trackRating = Rating.NEUTRAL;
        } else {
            this.trackRating = rating;
        }
        return this.trackRating;
    }

    private SynchronizedTrackItem(TrackMetadata trackMetadata, Marketplace marketplace, String str) {
        this.trackItem = trackMetadata;
        this.trackRating = trackMetadata.getRating() == null ? Rating.NEUTRAL : Rating.valueOf(trackMetadata.getRating());
        this.marketplace = marketplace;
        this.stationId = str;
    }
}
