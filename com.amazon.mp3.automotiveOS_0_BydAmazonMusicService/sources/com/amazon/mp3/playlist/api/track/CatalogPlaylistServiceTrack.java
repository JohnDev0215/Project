package com.amazon.mp3.playlist.api.track;

/* JADX INFO: loaded from: classes3.dex */
public class CatalogPlaylistServiceTrack extends PlaylistServiceTrack {
    @Override // com.amazon.mp3.playlist.api.track.PlaylistServiceTrack
    protected String getIdName() {
        return "asin";
    }

    @Override // com.amazon.mp3.playlist.api.track.PlaylistServiceTrack
    protected String getTypeName() {
        return "com.amazon.musicplaylist.model#CatalogTrack";
    }

    public CatalogPlaylistServiceTrack(String asin) {
        super(asin);
    }
}
