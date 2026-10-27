package com.amazon.music.share.menu.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareCard.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/share/menu/data/LyricsShareCard;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "lyrics", "Ljava/util/List;", "getLyrics", "()Ljava/util/List;", "Lcom/amazon/music/share/menu/data/LyricsShareCardBackground;", "lyricsShareCardBackground", "Lcom/amazon/music/share/menu/data/LyricsShareCardBackground;", "getLyricsShareCardBackground", "()Lcom/amazon/music/share/menu/data/LyricsShareCardBackground;", "fontColor", "Ljava/lang/Integer;", "getFontColor", "()Ljava/lang/Integer;", "<init>", "(Ljava/util/List;Lcom/amazon/music/share/menu/data/LyricsShareCardBackground;Ljava/lang/Integer;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsShareCard {
    private final Integer fontColor;
    private final List<String> lyrics;
    private final LyricsShareCardBackground lyricsShareCardBackground;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsShareCard)) {
            return false;
        }
        LyricsShareCard lyricsShareCard = (LyricsShareCard) other;
        return Intrinsics.areEqual(this.lyrics, lyricsShareCard.lyrics) && Intrinsics.areEqual(this.lyricsShareCardBackground, lyricsShareCard.lyricsShareCardBackground) && Intrinsics.areEqual(this.fontColor, lyricsShareCard.fontColor);
    }

    public int hashCode() {
        int iHashCode = ((this.lyrics.hashCode() * 31) + this.lyricsShareCardBackground.hashCode()) * 31;
        Integer num = this.fontColor;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "LyricsShareCard(lyrics=" + this.lyrics + ", lyricsShareCardBackground=" + this.lyricsShareCardBackground + ", fontColor=" + this.fontColor + ')';
    }

    public LyricsShareCard(List<String> lyrics, LyricsShareCardBackground lyricsShareCardBackground, Integer num) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        Intrinsics.checkNotNullParameter(lyricsShareCardBackground, "lyricsShareCardBackground");
        this.lyrics = lyrics;
        this.lyricsShareCardBackground = lyricsShareCardBackground;
        this.fontColor = num;
    }

    public final Integer getFontColor() {
        return this.fontColor;
    }

    public final List<String> getLyrics() {
        return this.lyrics;
    }

    public final LyricsShareCardBackground getLyricsShareCardBackground() {
        return this.lyricsShareCardBackground;
    }
}
