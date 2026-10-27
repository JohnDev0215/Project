package com.amazon.music.downloads;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMMDownloadableItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\r\u0010\t\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\"\u0010\u000f\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\"\u0010\u0011\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\t\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/downloads/DMMDownloadableItem;", "Lcom/amazon/music/downloads/DownloadableItem;", "Lcom/amazon/music/downloads/DownloadItemInfo;", "downloadItemInfo", "Lcom/amazon/music/downloads/DownloadItemInfo;", "getDownloadItemInfo", "()Lcom/amazon/music/downloads/DownloadItemInfo;", "", "isPaused", "Z", "()Z", "setPaused", "(Z)V", "isCanceled", "setCanceled", "isQueued", "setQueued", "isProcessing", "setProcessing", "<init>", "(Lcom/amazon/music/downloads/DownloadItemInfo;)V", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public final class DMMDownloadableItem implements DownloadableItem {
    private final DownloadItemInfo downloadItemInfo;
    private boolean isCanceled;
    private boolean isPaused;
    private boolean isProcessing;
    private boolean isQueued;

    public DMMDownloadableItem(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        this.downloadItemInfo = downloadItemInfo;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    public DownloadItemInfo getDownloadItemInfo() {
        return this.downloadItemInfo;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    public void setPaused(boolean z) {
        this.isPaused = z;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    public void setCanceled(boolean z) {
        this.isCanceled = z;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    /* JADX INFO: renamed from: isQueued, reason: from getter */
    public boolean getIsQueued() {
        return this.isQueued;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    public void setQueued(boolean z) {
        this.isQueued = z;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    /* JADX INFO: renamed from: isProcessing, reason: from getter */
    public boolean getIsProcessing() {
        return this.isProcessing;
    }

    @Override // com.amazon.music.downloads.DownloadableItem
    public void setProcessing(boolean z) {
        this.isProcessing = z;
    }
}
