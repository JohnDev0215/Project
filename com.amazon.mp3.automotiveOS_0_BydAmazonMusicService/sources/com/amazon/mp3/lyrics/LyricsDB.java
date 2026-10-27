package com.amazon.mp3.lyrics;

import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public interface LyricsDB {
    LyricsStatus loadLyricsStatusForTrackInfo(LyricsTrackInfo info);

    Set<LyricsTrackInfo> loadTrackInfoForAllTracksWithLyrics();

    List<LyricsTrackInfo> loadTrackInfoForAsins(List<String> asinList);

    List<LyricsTrackInfo> loadTrackInfoForNewTracks(int limit);

    void updateLyricsStatusForAsins(LyricsStatus status, List<String> asinList);
}
