package com.amazon.music.share.menu.data;

import android.graphics.Bitmap;
import com.amazon.ui.foundations.styles.GradientStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsShareCardBackground.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/share/menu/data/LyricsShareCardBackground;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "gradientStyle", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "getGradientStyle", "()Lcom/amazon/ui/foundations/styles/GradientStyle;", "setGradientStyle", "(Lcom/amazon/ui/foundations/styles/GradientStyle;)V", "Landroid/graphics/Bitmap;", "backgroundBitmap", "Landroid/graphics/Bitmap;", "getBackgroundBitmap", "()Landroid/graphics/Bitmap;", "setBackgroundBitmap", "(Landroid/graphics/Bitmap;)V", "isBlurAlbumArtBackground", "Z", "()Z", "setBlurAlbumArtBackground", "(Z)V", "<init>", "(Lcom/amazon/ui/foundations/styles/GradientStyle;Landroid/graphics/Bitmap;Z)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsShareCardBackground {
    private Bitmap backgroundBitmap;
    private GradientStyle gradientStyle;
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
        return Intrinsics.areEqual(this.gradientStyle, lyricsShareCardBackground.gradientStyle) && Intrinsics.areEqual(this.backgroundBitmap, lyricsShareCardBackground.backgroundBitmap) && this.isBlurAlbumArtBackground == lyricsShareCardBackground.isBlurAlbumArtBackground;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    public int hashCode() {
        GradientStyle gradientStyle = this.gradientStyle;
        int iHashCode = (gradientStyle == null ? 0 : gradientStyle.hashCode()) * 31;
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
        return "LyricsShareCardBackground(gradientStyle=" + this.gradientStyle + ", backgroundBitmap=" + this.backgroundBitmap + ", isBlurAlbumArtBackground=" + this.isBlurAlbumArtBackground + ')';
    }

    public LyricsShareCardBackground(GradientStyle gradientStyle, Bitmap bitmap, boolean z) {
        this.gradientStyle = gradientStyle;
        this.backgroundBitmap = bitmap;
        this.isBlurAlbumArtBackground = z;
    }

    public /* synthetic */ LyricsShareCardBackground(GradientStyle gradientStyle, Bitmap bitmap, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : gradientStyle, (i & 2) != 0 ? null : bitmap, (i & 4) != 0 ? false : z);
    }

    public final GradientStyle getGradientStyle() {
        return this.gradientStyle;
    }

    public final Bitmap getBackgroundBitmap() {
        return this.backgroundBitmap;
    }

    /* JADX INFO: renamed from: isBlurAlbumArtBackground, reason: from getter */
    public final boolean getIsBlurAlbumArtBackground() {
        return this.isBlurAlbumArtBackground;
    }
}
