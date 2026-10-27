package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class DownloadEntry {
    final String id;
    final DownloadPriority priority;
    final DownloadReason reason;

    public DownloadEntry(String str, DownloadPriority downloadPriority, DownloadReason downloadReason) {
        this.id = str;
        this.priority = downloadPriority;
        this.reason = downloadReason;
    }

    public String getId() {
        return this.id;
    }

    public DownloadPriority getPriority() {
        return this.priority;
    }

    public DownloadReason getReason() {
        return this.reason;
    }

    public String toString() {
        return "DownloadEntry{id=" + this.id + ",priority=" + this.priority + ",reason=" + this.reason + "}";
    }
}
