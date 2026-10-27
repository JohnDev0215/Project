package com.amazon.music.platform.aom.hints;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaHints.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/aom/hints/AlexaHints;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "hints", "Ljava/util/List;", "getHints", "()Ljava/util/List;", "", "expirationTime", "J", "getExpirationTime", "()J", "<init>", "(Ljava/util/List;J)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AlexaHints {
    private final long expirationTime;
    private final List<String> hints;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AlexaHints)) {
            return false;
        }
        AlexaHints alexaHints = (AlexaHints) other;
        return Intrinsics.areEqual(this.hints, alexaHints.hints) && this.expirationTime == alexaHints.expirationTime;
    }

    public int hashCode() {
        return (this.hints.hashCode() * 31) + Long.hashCode(this.expirationTime);
    }

    public String toString() {
        return "AlexaHints(hints=" + this.hints + ", expirationTime=" + this.expirationTime + ')';
    }

    public AlexaHints(List<String> hints, long j) {
        Intrinsics.checkNotNullParameter(hints, "hints");
        this.hints = hints;
        this.expirationTime = j;
    }

    public final long getExpirationTime() {
        return this.expirationTime;
    }

    public final List<String> getHints() {
        return this.hints;
    }
}
