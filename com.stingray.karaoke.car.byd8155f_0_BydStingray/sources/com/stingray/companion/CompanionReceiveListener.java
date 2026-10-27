package com.stingray.companion;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\fJc\u0010\u0019\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011H&¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H&¢\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H&¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0004H&¢\u0006\u0004\b\"\u0010\bJ'\u0010#\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0013H&¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/stingray/companion/CompanionReceiveListener;", "", "", "info", "Lm7/q;", "onCommandReceived", "(Ljava/lang/String;)V", "onConnectionChanged", "()V", "", "time", "onSeek", "(J)V", "", "songIds", "playlistId", "artistId", "", "index", "", "playNow", "resetQueue", "replaceOldestSongs", "scoreSong", "length", "onAddSongs", "([Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZZZZLjava/lang/Integer;)V", "songId", "onRemoveSong", "(Ljava/lang/String;I)V", "fromIndex", "toIndex", "onMoveSong", "(Ljava/lang/String;II)V", "onClearQueue", "onSelectSong", "(Ljava/lang/String;IZ)V", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface CompanionReceiveListener {
    void onAddSongs(String[] songIds, String playlistId, String artistId, int index, boolean playNow, boolean resetQueue, boolean replaceOldestSongs, boolean scoreSong, Integer length);

    void onClearQueue();

    void onCommandReceived(String info);

    void onConnectionChanged();

    void onMoveSong(String songId, int fromIndex, int toIndex);

    void onRemoveSong(String songId, int index);

    void onSeek(long time);

    void onSelectSong(String songId, int index, boolean scoreSong);
}
