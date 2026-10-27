package com.amazon.music.find.data.database;

import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusTable;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TRACKS", "LOCAL_TRACK_URIS", "PLAYLISTS", "PRIME_PLAYLISTS", "PLAYLISTS_TRACKS", "PRIME_PLAYLISTS_TRACKS", "PRIME_PLAYLIST_TO_TRACK", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum CirrusTable {
    TRACKS("Track"),
    LOCAL_TRACK_URIS("LocalTrackUri"),
    PLAYLISTS("Playlist"),
    PRIME_PLAYLISTS("PrimePlaylists"),
    PLAYLISTS_TRACKS("PlaylistTrack"),
    PRIME_PLAYLISTS_TRACKS("PrimePlaylistTracks"),
    PRIME_PLAYLIST_TO_TRACK("PrimePlaylistToTrack");

    private final String value;

    CirrusTable(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
