package com.amazon.music.platform.playback;

import kotlin.Metadata;

/* JADX INFO: compiled from: OfflineStatus.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\r\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\r\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/playback/OfflineStatus;", "", "", "toString", "", "hashCode", "other", "", "equals", "isDownloadable", "Z", "()Z", "isDownloaded", "isPlayableOffline", "<init>", "(ZZZ)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class OfflineStatus {
    private final boolean isDownloadable;
    private final boolean isDownloaded;
    private final boolean isPlayableOffline;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OfflineStatus)) {
            return false;
        }
        OfflineStatus offlineStatus = (OfflineStatus) other;
        return this.isDownloadable == offlineStatus.isDownloadable && this.isDownloaded == offlineStatus.isDownloaded && this.isPlayableOffline == offlineStatus.isPlayableOffline;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public int hashCode() {
        boolean z = this.isDownloadable;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.isDownloaded;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i2 = (i + r2) * 31;
        boolean z3 = this.isPlayableOffline;
        return i2 + (z3 ? 1 : z3);
    }

    public String toString() {
        return "OfflineStatus(isDownloadable=" + this.isDownloadable + ", isDownloaded=" + this.isDownloaded + ", isPlayableOffline=" + this.isPlayableOffline + ')';
    }

    public OfflineStatus(boolean z, boolean z2, boolean z3) {
        this.isDownloadable = z;
        this.isDownloaded = z2;
        this.isPlayableOffline = z3;
    }

    /* JADX INFO: renamed from: isDownloadable, reason: from getter */
    public final boolean getIsDownloadable() {
        return this.isDownloadable;
    }

    /* JADX INFO: renamed from: isDownloaded, reason: from getter */
    public final boolean getIsDownloaded() {
        return this.isDownloaded;
    }

    /* JADX INFO: renamed from: isPlayableOffline, reason: from getter */
    public final boolean getIsPlayableOffline() {
        return this.isPlayableOffline;
    }
}
