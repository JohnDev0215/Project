package com.amazon.mp3.voice.hints.network;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AHSHints.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/voice/hints/network/AHSHints;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "rawHints", "Ljava/util/List;", "getRawHints", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AHSHints {
    private final List<String> rawHints;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AHSHints) && Intrinsics.areEqual(this.rawHints, ((AHSHints) other).rawHints);
    }

    public int hashCode() {
        return this.rawHints.hashCode();
    }

    public String toString() {
        return "AHSHints(rawHints=" + this.rawHints + ')';
    }

    public AHSHints(List<String> rawHints) {
        Intrinsics.checkNotNullParameter(rawHints, "rawHints");
        this.rawHints = rawHints;
    }

    public final List<String> getRawHints() {
        return this.rawHints;
    }
}
