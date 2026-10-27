package com.amazon.mp3.lyrics.item;

import kotlin.Metadata;

/* JADX INFO: compiled from: LyricsSharingConfig.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/lyrics/item/LyricsSharingConfig;", "", "", "toString", "", "hashCode", "other", "", "equals", "isLyricsSharable", "Z", "()Z", "<init>", "(Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsSharingConfig {
    private final boolean isLyricsSharable;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LyricsSharingConfig) && this.isLyricsSharable == ((LyricsSharingConfig) other).isLyricsSharable;
    }

    public int hashCode() {
        boolean z = this.isLyricsSharable;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "LyricsSharingConfig(isLyricsSharable=" + this.isLyricsSharable + ')';
    }

    public LyricsSharingConfig(boolean z) {
        this.isLyricsSharable = z;
    }

    /* JADX INFO: renamed from: isLyricsSharable, reason: from getter */
    public final boolean getIsLyricsSharable() {
        return this.isLyricsSharable;
    }
}
