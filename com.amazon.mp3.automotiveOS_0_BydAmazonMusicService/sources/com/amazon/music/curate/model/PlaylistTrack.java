package com.amazon.music.curate.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.amazon.music.destination.parser.ParserUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistTrack.kt */
/* JADX INFO: loaded from: classes4.dex */
@Entity(foreignKeys = {@ForeignKey(childColumns = {"track_asin"}, entity = Track.class, parentColumns = {"asin"})}, primaryKeys = {"playlist_id", "position_in_playlist"})
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/curate/model/PlaylistTrack;", "", "playlistId", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "positionInPlaylist", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getPlaylistId", "()Ljava/lang/String;", "getPositionInPlaylist", "()I", "getTrackAsin", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class PlaylistTrack {

    @ColumnInfo(name = "playlist_id")
    private final String playlistId;

    @ColumnInfo(name = "position_in_playlist")
    private final int positionInPlaylist;

    @ColumnInfo(index = true, name = "track_asin")
    private final String trackAsin;

    public static /* synthetic */ PlaylistTrack copy$default(PlaylistTrack playlistTrack, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = playlistTrack.playlistId;
        }
        if ((i2 & 2) != 0) {
            str2 = playlistTrack.trackAsin;
        }
        if ((i2 & 4) != 0) {
            i = playlistTrack.positionInPlaylist;
        }
        return playlistTrack.copy(str, str2, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlaylistId() {
        return this.playlistId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTrackAsin() {
        return this.trackAsin;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPositionInPlaylist() {
        return this.positionInPlaylist;
    }

    public final PlaylistTrack copy(String playlistId, String trackAsin, int positionInPlaylist) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        return new PlaylistTrack(playlistId, trackAsin, positionInPlaylist);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaylistTrack)) {
            return false;
        }
        PlaylistTrack playlistTrack = (PlaylistTrack) other;
        return Intrinsics.areEqual(this.playlistId, playlistTrack.playlistId) && Intrinsics.areEqual(this.trackAsin, playlistTrack.trackAsin) && this.positionInPlaylist == playlistTrack.positionInPlaylist;
    }

    public int hashCode() {
        return (((this.playlistId.hashCode() * 31) + this.trackAsin.hashCode()) * 31) + Integer.hashCode(this.positionInPlaylist);
    }

    public String toString() {
        return "PlaylistTrack(playlistId=" + this.playlistId + ", trackAsin=" + this.trackAsin + ", positionInPlaylist=" + this.positionInPlaylist + ')';
    }

    public PlaylistTrack(String playlistId, String trackAsin, int i) {
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        this.playlistId = playlistId;
        this.trackAsin = trackAsin;
        this.positionInPlaylist = i;
    }

    public final String getPlaylistId() {
        return this.playlistId;
    }

    public final String getTrackAsin() {
        return this.trackAsin;
    }

    public final int getPositionInPlaylist() {
        return this.positionInPlaylist;
    }
}
