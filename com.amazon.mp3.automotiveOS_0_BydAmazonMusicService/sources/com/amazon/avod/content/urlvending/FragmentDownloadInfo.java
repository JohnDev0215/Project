package com.amazon.avod.content.urlvending;

import kotlin.Metadata;

/* JADX INFO: compiled from: FragmentDownloadInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\f\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/amazon/avod/content/urlvending/FragmentDownloadInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "isVideo", "Z", "()Z", "", "bandwidthBitsPerSecond", "J", "getBandwidthBitsPerSecond", "()J", "timeToTheFirstByteInNanos", "getTimeToTheFirstByteInNanos", "bitrate", "I", "getBitrate", "()I", "presentationTimeInNanos", "getPresentationTimeInNanos", "<init>", "(ZJJIJ)V", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FragmentDownloadInfo {
    private final long bandwidthBitsPerSecond;
    private final int bitrate;
    private final boolean isVideo;
    private final long presentationTimeInNanos;
    private final long timeToTheFirstByteInNanos;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FragmentDownloadInfo)) {
            return false;
        }
        FragmentDownloadInfo fragmentDownloadInfo = (FragmentDownloadInfo) other;
        return this.isVideo == fragmentDownloadInfo.isVideo && this.bandwidthBitsPerSecond == fragmentDownloadInfo.bandwidthBitsPerSecond && this.timeToTheFirstByteInNanos == fragmentDownloadInfo.timeToTheFirstByteInNanos && this.bitrate == fragmentDownloadInfo.bitrate && this.presentationTimeInNanos == fragmentDownloadInfo.presentationTimeInNanos;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    public int hashCode() {
        boolean z = this.isVideo;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((((((r0 * 31) + Long.hashCode(this.bandwidthBitsPerSecond)) * 31) + Long.hashCode(this.timeToTheFirstByteInNanos)) * 31) + Integer.hashCode(this.bitrate)) * 31) + Long.hashCode(this.presentationTimeInNanos);
    }

    public String toString() {
        return "FragmentDownloadInfo(isVideo=" + this.isVideo + ", bandwidthBitsPerSecond=" + this.bandwidthBitsPerSecond + ", timeToTheFirstByteInNanos=" + this.timeToTheFirstByteInNanos + ", bitrate=" + this.bitrate + ", presentationTimeInNanos=" + this.presentationTimeInNanos + ')';
    }

    public FragmentDownloadInfo(boolean z, long j, long j2, int i, long j3) {
        this.isVideo = z;
        this.bandwidthBitsPerSecond = j;
        this.timeToTheFirstByteInNanos = j2;
        this.bitrate = i;
        this.presentationTimeInNanos = j3;
    }

    /* JADX INFO: renamed from: isVideo, reason: from getter */
    public final boolean getIsVideo() {
        return this.isVideo;
    }

    public final long getBandwidthBitsPerSecond() {
        return this.bandwidthBitsPerSecond;
    }

    public final long getTimeToTheFirstByteInNanos() {
        return this.timeToTheFirstByteInNanos;
    }

    public final int getBitrate() {
        return this.bitrate;
    }

    public final long getPresentationTimeInNanos() {
        return this.presentationTimeInNanos;
    }
}
