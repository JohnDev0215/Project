package com.amazon.music.models;

import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AssetQuality.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/models/AssetQuality;", "", "", "toString", "", "hashCode", "other", "", "equals", "pid", "Ljava/lang/String;", "getPid", "()Ljava/lang/String;", "quality", "getQuality", "rank", "I", "getRank", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "DMMCommonModels_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AssetQuality {
    private final String pid;
    private final String quality;
    private final int rank;

    @JvmOverloads
    public AssetQuality() {
        this(null, null, 0, 7, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssetQuality)) {
            return false;
        }
        AssetQuality assetQuality = (AssetQuality) other;
        return Intrinsics.areEqual(this.pid, assetQuality.pid) && Intrinsics.areEqual(this.quality, assetQuality.quality) && this.rank == assetQuality.rank;
    }

    public int hashCode() {
        return (((this.pid.hashCode() * 31) + this.quality.hashCode()) * 31) + Integer.hashCode(this.rank);
    }

    public String toString() {
        return "AssetQuality(pid=" + this.pid + ", quality=" + this.quality + ", rank=" + this.rank + ')';
    }

    @JvmOverloads
    public AssetQuality(String pid, String quality, int i) {
        Intrinsics.checkNotNullParameter(pid, "pid");
        Intrinsics.checkNotNullParameter(quality, "quality");
        this.pid = pid;
        this.quality = quality;
        this.rank = i;
    }

    public /* synthetic */ AssetQuality(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getPid() {
        return this.pid;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final int getRank() {
        return this.rank;
    }
}
