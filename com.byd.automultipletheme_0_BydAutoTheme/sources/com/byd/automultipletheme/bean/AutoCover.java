package com.byd.automultipletheme.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoCover.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/byd/automultipletheme/bean/AutoCover;", "", "ar", "Lcom/byd/automultipletheme/bean/CoverData;", "en", "(Lcom/byd/automultipletheme/bean/CoverData;Lcom/byd/automultipletheme/bean/CoverData;)V", "getAr", "()Lcom/byd/automultipletheme/bean/CoverData;", "getEn", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class AutoCover {
    private final CoverData ar;
    private final CoverData en;

    public static /* synthetic */ AutoCover copy$default(AutoCover autoCover, CoverData coverData, CoverData coverData2, int i, Object obj) {
        if ((i & 1) != 0) {
            coverData = autoCover.ar;
        }
        if ((i & 2) != 0) {
            coverData2 = autoCover.en;
        }
        return autoCover.copy(coverData, coverData2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CoverData getAr() {
        return this.ar;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CoverData getEn() {
        return this.en;
    }

    public final AutoCover copy(CoverData ar, CoverData en) {
        Intrinsics.checkNotNullParameter(ar, "ar");
        Intrinsics.checkNotNullParameter(en, "en");
        return new AutoCover(ar, en);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoCover)) {
            return false;
        }
        AutoCover autoCover = (AutoCover) other;
        return Intrinsics.areEqual(this.ar, autoCover.ar) && Intrinsics.areEqual(this.en, autoCover.en);
    }

    public int hashCode() {
        return (this.ar.hashCode() * 31) + this.en.hashCode();
    }

    public String toString() {
        return "AutoCover(ar=" + this.ar + ", en=" + this.en + ')';
    }

    public AutoCover(CoverData ar, CoverData en) {
        Intrinsics.checkNotNullParameter(ar, "ar");
        Intrinsics.checkNotNullParameter(en, "en");
        this.ar = ar;
        this.en = en;
    }

    public final CoverData getAr() {
        return this.ar;
    }

    public final CoverData getEn() {
        return this.en;
    }
}
