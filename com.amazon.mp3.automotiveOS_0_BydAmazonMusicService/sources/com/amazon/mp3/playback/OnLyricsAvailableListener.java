package com.amazon.mp3.playback;

import com.amazon.mp3.lyrics.item.Lyrics;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnLyricsAvailableListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/playback/OnLyricsAvailableListener;", "", "onLyricsAvailable", "", "lyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OnLyricsAvailableListener {
    void onLyricsAvailable(Lyrics lyrics);
}
