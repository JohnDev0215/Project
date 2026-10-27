package com.amazon.music.curate.data;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetUserPlaylistResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/curate/data/GetUserPlaylistResponse;", "", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "tracks", "", "Lcom/amazon/music/curate/model/Track;", "token", "", "(Lcom/amazon/music/curate/model/Playlist;Ljava/util/List;Ljava/lang/String;)V", "getPlaylist", "()Lcom/amazon/music/curate/model/Playlist;", "getToken", "()Ljava/lang/String;", "getTracks", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class GetUserPlaylistResponse {
    private final Playlist playlist;
    private final String token;
    private final List<Track> tracks;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GetUserPlaylistResponse copy$default(GetUserPlaylistResponse getUserPlaylistResponse, Playlist playlist, List list, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            playlist = getUserPlaylistResponse.playlist;
        }
        if ((i & 2) != 0) {
            list = getUserPlaylistResponse.tracks;
        }
        if ((i & 4) != 0) {
            str = getUserPlaylistResponse.token;
        }
        return getUserPlaylistResponse.copy(playlist, list, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Playlist getPlaylist() {
        return this.playlist;
    }

    public final List<Track> component2() {
        return this.tracks;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    public final GetUserPlaylistResponse copy(Playlist playlist, List<Track> tracks, String token) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        return new GetUserPlaylistResponse(playlist, tracks, token);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetUserPlaylistResponse)) {
            return false;
        }
        GetUserPlaylistResponse getUserPlaylistResponse = (GetUserPlaylistResponse) other;
        return Intrinsics.areEqual(this.playlist, getUserPlaylistResponse.playlist) && Intrinsics.areEqual(this.tracks, getUserPlaylistResponse.tracks) && Intrinsics.areEqual(this.token, getUserPlaylistResponse.token);
    }

    public int hashCode() {
        int iHashCode = ((this.playlist.hashCode() * 31) + this.tracks.hashCode()) * 31;
        String str = this.token;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "GetUserPlaylistResponse(playlist=" + this.playlist + ", tracks=" + this.tracks + ", token=" + ((Object) this.token) + ')';
    }

    public GetUserPlaylistResponse(Playlist playlist, List<Track> tracks, String str) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        this.playlist = playlist;
        this.tracks = tracks;
        this.token = str;
    }

    public final Playlist getPlaylist() {
        return this.playlist;
    }

    public final List<Track> getTracks() {
        return this.tracks;
    }

    public final String getToken() {
        return this.token;
    }
}
