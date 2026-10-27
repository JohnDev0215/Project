package com.neusoft.na.common.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpaceInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/common/bean/SpaceInfo;", "", "()V", "Available", "", "getAvailable", "()J", "setAvailable", "(J)V", "Capacity", "getCapacity", "setCapacity", "Free", "getFree", "setFree", "PartitionName", "", "getPartitionName", "()Ljava/lang/String;", "setPartitionName", "(Ljava/lang/String;)V", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SpaceInfo {
    private long Available;
    private long Capacity;
    private long Free;
    private String PartitionName = "";

    public final String getPartitionName() {
        return this.PartitionName;
    }

    public final void setPartitionName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.PartitionName = str;
    }

    public final long getCapacity() {
        return this.Capacity;
    }

    public final void setCapacity(long j) {
        this.Capacity = j;
    }

    public final long getFree() {
        return this.Free;
    }

    public final void setFree(long j) {
        this.Free = j;
    }

    public final long getAvailable() {
        return this.Available;
    }

    public final void setAvailable(long j) {
        this.Available = j;
    }
}
