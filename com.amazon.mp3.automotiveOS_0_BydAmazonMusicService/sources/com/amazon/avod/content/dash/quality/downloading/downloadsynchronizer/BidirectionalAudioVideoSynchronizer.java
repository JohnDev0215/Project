package com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer;

import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BidirectionalAudioVideoSynchronizer.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0002R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/avod/content/dash/quality/downloading/downloadsynchronizer/BidirectionalAudioVideoSynchronizer;", "Lcom/amazon/avod/content/dash/quality/downloading/downloadsynchronizer/DownloadSynchronizer;", "maxAudioVideoDeltaSeconds", "", "(J)V", "latestAudioPresentationTimeInNanos", "latestVideoPresentationTimeInNanos", "lock", "", "maxAudioVideoDeltaNanos", "onFragmentDownloaded", "", "uri", "Lcom/amazon/avod/content/smoothstream/SmoothStreamingURI;", "onSeeked", "toTimeInNanos", "shouldDownload", "", "shouldDownloadInner", "minPresentationTimeInNanos", "givenPresentationTimeInNanos", "Companion", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BidirectionalAudioVideoSynchronizer implements DownloadSynchronizer {
    private final long maxAudioVideoDeltaNanos;
    private long latestVideoPresentationTimeInNanos = -1;
    private long latestAudioPresentationTimeInNanos = -1;
    private final Object lock = new Object();

    public BidirectionalAudioVideoSynchronizer(long j) {
        this.maxAudioVideoDeltaNanos = TimeSpan.fromSeconds(j).getTotalNanoSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public void onSeeked(long toTimeInNanos) {
        synchronized (this.lock) {
            this.latestAudioPresentationTimeInNanos = -1L;
            this.latestVideoPresentationTimeInNanos = -1L;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public void onFragmentDownloaded(SmoothStreamingURI uri) {
        synchronized (this.lock) {
            if (uri != null) {
                long presentationTimeInNanos = uri.getPresentationTimeInNanos();
                boolean z = true;
                if (uri.isVideo() & (!uri.isInitFragment()) & (presentationTimeInNanos > this.latestVideoPresentationTimeInNanos)) {
                    this.latestVideoPresentationTimeInNanos = presentationTimeInNanos;
                } else {
                    boolean zIsAudio = uri.isAudio();
                    if (presentationTimeInNanos <= this.latestAudioPresentationTimeInNanos) {
                        z = false;
                    }
                    if (zIsAudio & z) {
                        this.latestAudioPresentationTimeInNanos = presentationTimeInNanos;
                    }
                }
                DLog.devf(Intrinsics.stringPlus("onFragmentDownloaded AV delta ", Double.valueOf((this.latestVideoPresentationTimeInNanos - this.latestAudioPresentationTimeInNanos) / 1.0E9d)));
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer
    public boolean shouldDownload(SmoothStreamingURI uri) {
        synchronized (this.lock) {
            if (uri != null) {
                if (uri.isInitFragment() || ((!uri.isVideo()) & (!uri.isAudio()))) {
                    return true;
                }
                long presentationTimeInNanos = uri.getPresentationTimeInNanos();
                if (uri.isVideo()) {
                    return shouldDownloadInner(this.latestAudioPresentationTimeInNanos, presentationTimeInNanos);
                }
                if (!uri.isAudio()) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    return shouldDownloadInner(this.latestVideoPresentationTimeInNanos, presentationTimeInNanos);
                }
            }
            return true;
        }
    }

    private final boolean shouldDownloadInner(long minPresentationTimeInNanos, long givenPresentationTimeInNanos) {
        boolean z = true;
        if (minPresentationTimeInNanos != -1 && givenPresentationTimeInNanos - minPresentationTimeInNanos >= this.maxAudioVideoDeltaNanos) {
            z = false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("AV delta ");
        sb.append((minPresentationTimeInNanos != -1 ? givenPresentationTimeInNanos - minPresentationTimeInNanos : 0L) / 1.0E9d);
        sb.append(" shouldDownload ");
        sb.append(z);
        DLog.devf(sb.toString());
        return z;
    }
}
