package com.amazon.music.share.menu.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/share/menu/data/LyricsShareData;", "", "", "toString", "", "hashCode", "other", "", "equals", "cardPosition", "I", "getCardPosition", "()I", "", "lyrics", "Ljava/util/List;", "getLyrics", "()Ljava/util/List;", "<init>", "(ILjava/util/List;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsShareData {
    private final int cardPosition;
    private final List<String> lyrics;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsShareData)) {
            return false;
        }
        LyricsShareData lyricsShareData = (LyricsShareData) other;
        return this.cardPosition == lyricsShareData.cardPosition && Intrinsics.areEqual(this.lyrics, lyricsShareData.lyrics);
    }

    public int hashCode() {
        return (Integer.hashCode(this.cardPosition) * 31) + this.lyrics.hashCode();
    }

    public String toString() {
        return "LyricsShareData(cardPosition=" + this.cardPosition + ", lyrics=" + this.lyrics + ')';
    }

    public LyricsShareData(int i, List<String> lyrics) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        this.cardPosition = i;
        this.lyrics = lyrics;
    }

    public final int getCardPosition() {
        return this.cardPosition;
    }
}
