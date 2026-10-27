package com.amazon.music.curate.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Playlist.kt */
/* JADX INFO: loaded from: classes4.dex */
@Entity
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0017\u0018\u00002\u00020\u0001BÃ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0018J\b\u0010+\u001a\u00020\u0003H\u0016R\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001dR\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b \u0010\u001dR\u001a\u0010\t\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u000f\u0010\u001dR\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u0012\u0010\u001dR\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u0013\u0010\u001dR\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u0014\u0010\u001dR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\r\u0010\u001dR\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u0011\u0010\u001dR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u0010\u0010\u001dR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001a¨\u0006,"}, d2 = {"Lcom/amazon/music/curate/model/Playlist;", "", "id", "", "title", "artworkUrl", "bannerArtworkUrl", "numSongs", "", "durationSeconds", "version", "expiryTime", "", "isPersonalized", "", "isFollowed", "isShareable", "isPrime", "isMusicUnlimited", "isNightwing", "isNightwingOnDemand", "canRename", "canEditContent", "canRemove", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/String;JLjava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getArtworkUrl", "()Ljava/lang/String;", "getBannerArtworkUrl", "getCanEditContent", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCanRemove", "getCanRename", "getDurationSeconds", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getExpiryTime", "()J", "getId", "getNumSongs", "()I", "getTitle", "getVersion", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class Playlist {

    @ColumnInfo(name = "artwork_url")
    private final String artworkUrl;

    @ColumnInfo(name = "banner_artwork_url")
    private final String bannerArtworkUrl;

    @ColumnInfo(name = "can_edit_content")
    private final Boolean canEditContent;

    @ColumnInfo(name = "can_remove")
    private final Boolean canRemove;

    @ColumnInfo(name = "can_rename")
    private final Boolean canRename;

    @ColumnInfo(name = "duration_seconds")
    private final Integer durationSeconds;

    @ColumnInfo(name = "expiry_time")
    private final long expiryTime;

    @PrimaryKey
    private final String id;

    @ColumnInfo(name = "is_followed")
    private final Boolean isFollowed;

    @ColumnInfo(name = "is_music_unlimited")
    private final Boolean isMusicUnlimited;

    @ColumnInfo(name = "is_nightwing")
    private final Boolean isNightwing;

    @ColumnInfo(name = "is_nightwing_on_demand")
    private final Boolean isNightwingOnDemand;

    @ColumnInfo(name = "is_personalized")
    private final Boolean isPersonalized;

    @ColumnInfo(name = "is_prime")
    private final Boolean isPrime;

    @ColumnInfo(name = "is_shareable")
    private final Boolean isShareable;

    @ColumnInfo(name = "num_songs")
    private final int numSongs;
    private final String title;
    private final String version;

    public Playlist(String id, String title, String artworkUrl, String bannerArtworkUrl, int i, Integer num, String version, long j, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artworkUrl, "artworkUrl");
        Intrinsics.checkNotNullParameter(bannerArtworkUrl, "bannerArtworkUrl");
        Intrinsics.checkNotNullParameter(version, "version");
        this.id = id;
        this.title = title;
        this.artworkUrl = artworkUrl;
        this.bannerArtworkUrl = bannerArtworkUrl;
        this.numSongs = i;
        this.durationSeconds = num;
        this.version = version;
        this.expiryTime = j;
        this.isPersonalized = bool;
        this.isFollowed = bool2;
        this.isShareable = bool3;
        this.isPrime = bool4;
        this.isMusicUnlimited = bool5;
        this.isNightwing = bool6;
        this.isNightwingOnDemand = bool7;
        this.canRename = bool8;
        this.canEditContent = bool9;
        this.canRemove = bool10;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtworkUrl() {
        return this.artworkUrl;
    }

    public final String getBannerArtworkUrl() {
        return this.bannerArtworkUrl;
    }

    public final int getNumSongs() {
        return this.numSongs;
    }

    public /* synthetic */ Playlist(String str, String str2, String str3, String str4, int i, Integer num, String str5, long j, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i2 & 16) != 0 ? 0 : i, (i2 & 32) != 0 ? 0 : num, str5, j, (i2 & 256) != 0 ? Boolean.FALSE : bool, (i2 & 512) != 0 ? Boolean.FALSE : bool2, (i2 & 1024) != 0 ? Boolean.FALSE : bool3, (i2 & 2048) != 0 ? Boolean.FALSE : bool4, (i2 & 4096) != 0 ? Boolean.FALSE : bool5, (i2 & 8192) != 0 ? Boolean.FALSE : bool6, (i2 & 16384) != 0 ? Boolean.FALSE : bool7, (32768 & i2) != 0 ? Boolean.FALSE : bool8, (65536 & i2) != 0 ? Boolean.FALSE : bool9, (i2 & 131072) != 0 ? Boolean.FALSE : bool10);
    }

    public final Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public final String getVersion() {
        return this.version;
    }

    public final long getExpiryTime() {
        return this.expiryTime;
    }

    /* JADX INFO: renamed from: isPersonalized, reason: from getter */
    public final Boolean getIsPersonalized() {
        return this.isPersonalized;
    }

    /* JADX INFO: renamed from: isFollowed, reason: from getter */
    public final Boolean getIsFollowed() {
        return this.isFollowed;
    }

    /* JADX INFO: renamed from: isShareable, reason: from getter */
    public final Boolean getIsShareable() {
        return this.isShareable;
    }

    /* JADX INFO: renamed from: isPrime, reason: from getter */
    public final Boolean getIsPrime() {
        return this.isPrime;
    }

    /* JADX INFO: renamed from: isMusicUnlimited, reason: from getter */
    public final Boolean getIsMusicUnlimited() {
        return this.isMusicUnlimited;
    }

    /* JADX INFO: renamed from: isNightwing, reason: from getter */
    public final Boolean getIsNightwing() {
        return this.isNightwing;
    }

    /* JADX INFO: renamed from: isNightwingOnDemand, reason: from getter */
    public final Boolean getIsNightwingOnDemand() {
        return this.isNightwingOnDemand;
    }

    public final Boolean getCanRename() {
        return this.canRename;
    }

    public final Boolean getCanEditContent() {
        return this.canEditContent;
    }

    public final Boolean getCanRemove() {
        return this.canRemove;
    }

    public String toString() {
        return "Playlist(id='" + this.id + "', title='" + this.title + "', artworkUrl='" + this.artworkUrl + "', bannerArtworkUrl='" + this.bannerArtworkUrl + "', numSongs=" + this.numSongs + ", durationSeconds=" + this.durationSeconds + ", version='" + this.version + "', isPersonalized=" + this.isPersonalized + ", isFollowed=" + this.isFollowed + ", isShareable=" + this.isShareable + ", isPrime=" + this.isPrime + ", isMusicUnlimited=" + this.isMusicUnlimited + ", isNightwing=" + this.isNightwing + ", isNightwingOnDemand=" + this.isNightwingOnDemand + ", canRename=" + this.canRename + ", canEditContent=" + this.canEditContent + ", canRemove=" + this.canRemove + ')';
    }
}
