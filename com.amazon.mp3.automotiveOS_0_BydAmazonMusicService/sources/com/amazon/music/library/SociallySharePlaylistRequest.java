package com.amazon.music.library;

/* JADX INFO: loaded from: classes4.dex */
public interface SociallySharePlaylistRequest {

    public interface Builder {
        SociallySharePlaylistRequest build();

        Builder rejectForUnshareableTracks();

        Builder withOwnedPlaylist(String str, String str2);
    }
}
