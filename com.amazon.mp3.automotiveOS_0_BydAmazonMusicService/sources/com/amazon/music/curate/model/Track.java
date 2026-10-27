package com.amazon.music.curate.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.music.curate.data.database.BadgeConverter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Track.kt */
/* JADX INFO: loaded from: classes4.dex */
@TypeConverters({BadgeConverter.class})
@Entity
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0010HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u000eHÆ\u0003J\u0089\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00100\u001a\u00020\u000e2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0010HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0016\u0010\f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010 R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015¨\u00064"}, d2 = {"Lcom/amazon/music/curate/model/Track;", "", "asin", "", "title", "artistName", "albumImageUrl", "badges", "", "Lcom/amazon/music/curate/model/Badge;", "artistAsin", "albumAsin", "albumName", "isInLibrary", "", "duration", "", IntlConfiguration.MARKETPLACE, "entryId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;)V", "getAlbumAsin", "()Ljava/lang/String;", "getAlbumImageUrl", "getAlbumName", "getArtistAsin", "getArtistName", "getAsin", "getBadges", "()Ljava/util/List;", "getDuration", "()I", "getEntryId", "()Z", "getMarketplace", "getTitle", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class Track {

    @ColumnInfo(name = "album_asin")
    private final String albumAsin;

    @ColumnInfo(name = "artwork_url")
    private final String albumImageUrl;

    @ColumnInfo(name = "album_name")
    private final String albumName;

    @ColumnInfo(name = "artist_asin")
    private final String artistAsin;

    @ColumnInfo(name = "artist_name")
    private final String artistName;

    @PrimaryKey
    private final String asin;
    private final List<Badge> badges;
    private final int duration;

    @ColumnInfo(name = "entry_id")
    private final String entryId;

    @ColumnInfo(name = "is_in_library")
    private final boolean isInLibrary;
    private final String marketplace;
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAsin() {
        return this.asin;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getMarketplace() {
        return this.marketplace;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getEntryId() {
        return this.entryId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getArtistName() {
        return this.artistName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAlbumImageUrl() {
        return this.albumImageUrl;
    }

    public final List<Badge> component5() {
        return this.badges;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getArtistAsin() {
        return this.artistAsin;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getAlbumName() {
        return this.albumName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsInLibrary() {
        return this.isInLibrary;
    }

    public final Track copy(String asin, String title, String artistName, String albumImageUrl, List<? extends Badge> badges, String artistAsin, String albumAsin, String albumName, boolean isInLibrary, int duration, String marketplace, String entryId) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artistName, "artistName");
        Intrinsics.checkNotNullParameter(albumImageUrl, "albumImageUrl");
        Intrinsics.checkNotNullParameter(badges, "badges");
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(marketplace, "marketplace");
        return new Track(asin, title, artistName, albumImageUrl, badges, artistAsin, albumAsin, albumName, isInLibrary, duration, marketplace, entryId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Track)) {
            return false;
        }
        Track track = (Track) other;
        return Intrinsics.areEqual(this.asin, track.asin) && Intrinsics.areEqual(this.title, track.title) && Intrinsics.areEqual(this.artistName, track.artistName) && Intrinsics.areEqual(this.albumImageUrl, track.albumImageUrl) && Intrinsics.areEqual(this.badges, track.badges) && Intrinsics.areEqual(this.artistAsin, track.artistAsin) && Intrinsics.areEqual(this.albumAsin, track.albumAsin) && Intrinsics.areEqual(this.albumName, track.albumName) && this.isInLibrary == track.isInLibrary && this.duration == track.duration && Intrinsics.areEqual(this.marketplace, track.marketplace) && Intrinsics.areEqual(this.entryId, track.entryId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    public int hashCode() {
        int iHashCode = ((((((((((((((this.asin.hashCode() * 31) + this.title.hashCode()) * 31) + this.artistName.hashCode()) * 31) + this.albumImageUrl.hashCode()) * 31) + this.badges.hashCode()) * 31) + this.artistAsin.hashCode()) * 31) + this.albumAsin.hashCode()) * 31) + this.albumName.hashCode()) * 31;
        boolean z = this.isInLibrary;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int iHashCode2 = (((((iHashCode + r1) * 31) + Integer.hashCode(this.duration)) * 31) + this.marketplace.hashCode()) * 31;
        String str = this.entryId;
        return iHashCode2 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Track(asin=" + this.asin + ", title=" + this.title + ", artistName=" + this.artistName + ", albumImageUrl=" + this.albumImageUrl + ", badges=" + this.badges + ", artistAsin=" + this.artistAsin + ", albumAsin=" + this.albumAsin + ", albumName=" + this.albumName + ", isInLibrary=" + this.isInLibrary + ", duration=" + this.duration + ", marketplace=" + this.marketplace + ", entryId=" + ((Object) this.entryId) + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Track(String asin, String title, String artistName, String albumImageUrl, List<? extends Badge> badges, String artistAsin, String albumAsin, String albumName, boolean z, int i, String marketplace, String str) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artistName, "artistName");
        Intrinsics.checkNotNullParameter(albumImageUrl, "albumImageUrl");
        Intrinsics.checkNotNullParameter(badges, "badges");
        Intrinsics.checkNotNullParameter(artistAsin, "artistAsin");
        Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
        Intrinsics.checkNotNullParameter(albumName, "albumName");
        Intrinsics.checkNotNullParameter(marketplace, "marketplace");
        this.asin = asin;
        this.title = title;
        this.artistName = artistName;
        this.albumImageUrl = albumImageUrl;
        this.badges = badges;
        this.artistAsin = artistAsin;
        this.albumAsin = albumAsin;
        this.albumName = albumName;
        this.isInLibrary = z;
        this.duration = i;
        this.marketplace = marketplace;
        this.entryId = str;
    }

    public /* synthetic */ Track(String str, String str2, String str3, String str4, List list, String str5, String str6, String str7, boolean z, int i, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, list, str5, str6, str7, z, i, str8, (i2 & 2048) != 0 ? null : str9);
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtistName() {
        return this.artistName;
    }

    public final String getAlbumImageUrl() {
        return this.albumImageUrl;
    }

    public final List<Badge> getBadges() {
        return this.badges;
    }

    public final String getArtistAsin() {
        return this.artistAsin;
    }

    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    public final String getAlbumName() {
        return this.albumName;
    }

    public final boolean isInLibrary() {
        return this.isInLibrary;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final String getMarketplace() {
        return this.marketplace;
    }

    public final String getEntryId() {
        return this.entryId;
    }
}
