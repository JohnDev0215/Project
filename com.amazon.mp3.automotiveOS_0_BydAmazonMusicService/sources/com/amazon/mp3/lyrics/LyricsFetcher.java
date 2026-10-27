package com.amazon.mp3.lyrics;

import com.amazon.mp3.lyrics.exception.NetworkException;
import com.amazon.mp3.lyrics.item.LyricsStatusChanges;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.lyrics.item.UnparsedLyrics;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
interface LyricsFetcher {
    Map<LyricsTrackInfo, UnparsedLyrics> downloadLyrics(Collection<LyricsTrackInfo> infoList) throws NetworkException;

    LyricsStatusChanges getRefreshList(long startDate, String pageToken) throws NetworkException;

    LyricsStatusChanges getTakedownList(long startDate, String pageToken) throws NetworkException;
}
