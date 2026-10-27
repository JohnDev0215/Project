package com.amazon.music.downloads;

import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadableItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u001c\u0010\u0003\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0004\"\u0004\b\b\u0010\u0006R\u001c\u0010\t\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u0006R\u001c\u0010\u000b\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\u0004\"\u0004\b\f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/downloads/DownloadableItem;", "", "", "isPaused", "()Z", "setPaused", "(Z)V", "isCanceled", "setCanceled", "isQueued", "setQueued", "isProcessing", "setProcessing", "Lcom/amazon/music/downloads/DownloadItemInfo;", "getDownloadItemInfo", "()Lcom/amazon/music/downloads/DownloadItemInfo;", "downloadItemInfo", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public interface DownloadableItem {
    DownloadItemInfo getDownloadItemInfo();

    boolean isProcessing();

    boolean isQueued();

    void setCanceled(boolean z);

    void setPaused(boolean z);

    void setProcessing(boolean z);

    void setQueued(boolean z);
}
