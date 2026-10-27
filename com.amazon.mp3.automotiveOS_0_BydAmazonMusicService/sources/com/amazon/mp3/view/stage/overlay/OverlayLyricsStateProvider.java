package com.amazon.mp3.view.stage.overlay;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: OverlayLyricsStateProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "", "", "reset", "", "toString", "", "hashCode", "other", "", "equals", "frugalLyricsCompleted", "Z", "getFrugalLyricsCompleted", "()Z", "setFrugalLyricsCompleted", "(Z)V", "shouldShowTapToViewLyrics", "getShouldShowTapToViewLyrics", "setShouldShowTapToViewLyrics", "<init>", "(ZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class OverlayLyricsStateProvider {
    private boolean frugalLyricsCompleted;
    private boolean shouldShowTapToViewLyrics;

    /* JADX WARN: Illegal instructions before constructor call */
    public OverlayLyricsStateProvider() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OverlayLyricsStateProvider)) {
            return false;
        }
        OverlayLyricsStateProvider overlayLyricsStateProvider = (OverlayLyricsStateProvider) other;
        return this.frugalLyricsCompleted == overlayLyricsStateProvider.frugalLyricsCompleted && this.shouldShowTapToViewLyrics == overlayLyricsStateProvider.shouldShowTapToViewLyrics;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public int hashCode() {
        boolean z = this.frugalLyricsCompleted;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.shouldShowTapToViewLyrics;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "OverlayLyricsStateProvider(frugalLyricsCompleted=" + this.frugalLyricsCompleted + ", shouldShowTapToViewLyrics=" + this.shouldShowTapToViewLyrics + ')';
    }

    public OverlayLyricsStateProvider(boolean z, boolean z2) {
        this.frugalLyricsCompleted = z;
        this.shouldShowTapToViewLyrics = z2;
    }

    public /* synthetic */ OverlayLyricsStateProvider(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2);
    }

    public final boolean getFrugalLyricsCompleted() {
        return this.frugalLyricsCompleted;
    }

    public final boolean getShouldShowTapToViewLyrics() {
        return this.shouldShowTapToViewLyrics;
    }

    public final void setFrugalLyricsCompleted(boolean z) {
        this.frugalLyricsCompleted = z;
    }

    public final void setShouldShowTapToViewLyrics(boolean z) {
        this.shouldShowTapToViewLyrics = z;
    }

    public final void reset() {
        this.frugalLyricsCompleted = false;
        this.shouldShowTapToViewLyrics = true;
    }
}
