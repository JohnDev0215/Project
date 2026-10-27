package com.amazon.music.downloads;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadItemInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/downloads/DownloadItemInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "quality", "getQuality", "Lcom/amazon/music/downloads/PriorityInfo;", "priorityInfo", "Lcom/amazon/music/downloads/PriorityInfo;", "getPriorityInfo", "()Lcom/amazon/music/downloads/PriorityInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/downloads/PriorityInfo;)V", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DownloadItemInfo {
    private final String id;
    private final PriorityInfo priorityInfo;
    private final String quality;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadItemInfo)) {
            return false;
        }
        DownloadItemInfo downloadItemInfo = (DownloadItemInfo) other;
        return Intrinsics.areEqual(this.id, downloadItemInfo.id) && Intrinsics.areEqual(this.quality, downloadItemInfo.quality) && Intrinsics.areEqual(this.priorityInfo, downloadItemInfo.priorityInfo);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.quality.hashCode()) * 31) + this.priorityInfo.hashCode();
    }

    public String toString() {
        return "DownloadItemInfo(id=" + this.id + ", quality=" + this.quality + ", priorityInfo=" + this.priorityInfo + ')';
    }

    public DownloadItemInfo(String id, String quality, PriorityInfo priorityInfo) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(priorityInfo, "priorityInfo");
        this.id = id;
        this.quality = quality;
        this.priorityInfo = priorityInfo;
    }

    public final String getId() {
        return this.id;
    }

    public final PriorityInfo getPriorityInfo() {
        return this.priorityInfo;
    }
}
