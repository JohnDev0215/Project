package com.amazon.musicplaylist.model;

/* JADX INFO: loaded from: classes5.dex */
public class CannotSharePlaylistException extends PlaylistServiceException {
    private int numberOfShareableTracks;
    private int numberOfTracksInPlaylist;

    public int getNumberOfShareableTracks() {
        return this.numberOfShareableTracks;
    }

    public int getNumberOfTracksInPlaylist() {
        return this.numberOfTracksInPlaylist;
    }

    public void setNumberOfShareableTracks(int i) {
        this.numberOfShareableTracks = i;
    }

    public void setNumberOfTracksInPlaylist(int i) {
        this.numberOfTracksInPlaylist = i;
    }
}
