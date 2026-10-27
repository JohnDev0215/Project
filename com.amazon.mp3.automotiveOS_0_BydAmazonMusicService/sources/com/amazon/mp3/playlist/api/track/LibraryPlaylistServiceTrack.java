package com.amazon.mp3.playlist.api.track;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryPlaylistServiceTrack extends PlaylistServiceTrack {
    @Override // com.amazon.mp3.playlist.api.track.PlaylistServiceTrack
    protected String getIdName() {
        return "objectId";
    }

    @Override // com.amazon.mp3.playlist.api.track.PlaylistServiceTrack
    protected String getTypeName() {
        return "com.amazon.musicplaylist.model#LibraryTrack";
    }

    public LibraryPlaylistServiceTrack(String luid) {
        super(luid);
    }
}
