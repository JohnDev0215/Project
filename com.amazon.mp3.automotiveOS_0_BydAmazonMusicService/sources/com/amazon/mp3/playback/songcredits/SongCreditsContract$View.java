package com.amazon.mp3.playback.songcredits;

import com.amazon.music.browse.TrackLookup;

/* JADX INFO: loaded from: classes3.dex */
public interface SongCreditsContract$View {
    TrackLookup.Request getTrackLookupRequest(String asin);

    void setSongWriterText(String s);
}
