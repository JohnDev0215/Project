package com.amazon.mp3.view.lyrics.sharing.data;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareCardBackground.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsShareCardBackground;", "", "", "toString", "", "hashCode", "other", "", "equals", "backgroundDrawable", "Ljava/lang/Integer;", "getBackgroundDrawable", "()Ljava/lang/Integer;", "setBackgroundDrawable", "(Ljava/lang/Integer;)V", "Landroid/graphics/Bitmap;", "backgroundBitmap", "Landroid/graphics/Bitmap;", "getBackgroundBitmap", "()Landroid/graphics/Bitmap;", "setBackgroundBitmap", "(Landroid/graphics/Bitmap;)V", "isBlurAlbumArtBackground", "Z", "()Z", "setBlurAlbumArtBackground", "(Z)V", "<init>", "(Ljava/lang/Integer;Landroid/graphics/Bitmap;Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsShareCardBackground {
    private Bitmap backgroundBitmap;
    private Integer backgroundDrawable;
    private boolean isBlurAlbumArtBackground;

    public LyricsShareCardBackground() {
        this(null, null, false, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsShareCardBackground)) {
            return false;
        }
        LyricsShareCardBackground lyricsShareCardBackground = (LyricsShareCardBackground) other;
        return Intrinsics.areEqual(this.backgroundDrawable, lyricsShareCardBackground.backgroundDrawable) && Intrinsics.areEqual(this.backgroundBitmap, lyricsShareCardBackground.backgroundBitmap) && this.isBlurAlbumArtBackground == lyricsShareCardBackground.isBlurAlbumArtBackground;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        Integer num = this.backgroundDrawable;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Bitmap bitmap = this.backgroundBitmap;
        int iHashCode2 = (iHashCode + (bitmap != null ? bitmap.hashCode() : 0)) * 31;
        boolean z = this.isBlurAlbumArtBackground;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode2 + r1;
    }

    public String toString() {
        return "LyricsShareCardBackground(backgroundDrawable=" + this.backgroundDrawable + ", backgroundBitmap=" + this.backgroundBitmap + ", isBlurAlbumArtBackground=" + this.isBlurAlbumArtBackground + ')';
    }

    public LyricsShareCardBackground(Integer num, Bitmap bitmap, boolean z) {
        this.backgroundDrawable = num;
        this.backgroundBitmap = bitmap;
        this.isBlurAlbumArtBackground = z;
    }

    public /* synthetic */ LyricsShareCardBackground(Integer num, Bitmap bitmap, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : bitmap, (i & 4) != 0 ? false : z);
    }

    public final Integer getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    public final Bitmap getBackgroundBitmap() {
        return this.backgroundBitmap;
    }

    /* JADX INFO: renamed from: isBlurAlbumArtBackground, reason: from getter */
    public final boolean getIsBlurAlbumArtBackground() {
        return this.isBlurAlbumArtBackground;
    }
}
