package com.amazon.video.sdk.download;

import com.amazon.avod.threading.WeakReferenceSet;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackDownloadCentralChangeListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0001J\u0016\u0010\n\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\bH\u0016R<\u0010\u0012\u001a*\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00100\u000fj\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0010`\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/video/sdk/download/PlaybackDownloadCentralChangeListener;", "Lcom/amazon/avod/userdownload/PlaybackDownloadChangeListener;", "", "titleId", "downloadChangeListener", "", "registerListener", "Lcom/google/common/collect/ImmutableSet;", "Lcom/amazon/avod/userdownload/PlaybackDownload;", "downloads", "onDownloadsAvailabilityChanged", "download", "onDownloadAvailabilityChanged", "onDownloadStateChanged", "onDownloadProgressChanged", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "changeListenerMap", "Ljava/util/HashMap;", "<init>", "()V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class PlaybackDownloadCentralChangeListener implements PlaybackDownloadChangeListener {
    private final HashMap<String, Set<PlaybackDownloadChangeListener>> changeListenerMap = new HashMap<>();

    public final void registerListener(String titleId, PlaybackDownloadChangeListener downloadChangeListener) {
        Intrinsics.checkNotNullParameter(titleId, "titleId");
        Intrinsics.checkNotNullParameter(downloadChangeListener, "downloadChangeListener");
        if (this.changeListenerMap.containsKey(titleId)) {
            Set<PlaybackDownloadChangeListener> set = this.changeListenerMap.get(titleId);
            if (set == null) {
                return;
            }
            set.add(downloadChangeListener);
            return;
        }
        HashMap<String, Set<PlaybackDownloadChangeListener>> map = this.changeListenerMap;
        Set<PlaybackDownloadChangeListener> setBuild = WeakReferenceSet.build();
        Intrinsics.checkNotNullExpressionValue(setBuild, "build()");
        map.put(titleId, setBuild);
        Set<PlaybackDownloadChangeListener> set2 = this.changeListenerMap.get(titleId);
        if (set2 == null) {
            return;
        }
        set2.add(downloadChangeListener);
    }

    @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
    public void onDownloadsAvailabilityChanged(ImmutableSet<PlaybackDownload> downloads) {
        Intrinsics.checkNotNullParameter(downloads, "downloads");
        UnmodifiableIterator<PlaybackDownload> it = downloads.iterator();
        while (it.hasNext()) {
            PlaybackDownload download = it.next();
            Intrinsics.checkNotNullExpressionValue(download, "download");
            onDownloadAvailabilityChanged(download);
        }
    }

    @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
    public void onDownloadAvailabilityChanged(PlaybackDownload download) {
        Intrinsics.checkNotNullParameter(download, "download");
        Set<PlaybackDownloadChangeListener> set = this.changeListenerMap.get(download.getAsin());
        if (set == null) {
            return;
        }
        Iterator it = new HashSet(set).iterator();
        while (it.hasNext()) {
            ((PlaybackDownloadChangeListener) it.next()).onDownloadAvailabilityChanged(download);
        }
    }

    @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
    public void onDownloadStateChanged(PlaybackDownload download) {
        Intrinsics.checkNotNullParameter(download, "download");
        Set<PlaybackDownloadChangeListener> set = this.changeListenerMap.get(download.getAsin());
        if (set == null) {
            return;
        }
        Iterator it = new HashSet(set).iterator();
        while (it.hasNext()) {
            ((PlaybackDownloadChangeListener) it.next()).onDownloadStateChanged(download);
        }
    }

    @Override // com.amazon.avod.userdownload.PlaybackDownloadChangeListener
    public void onDownloadProgressChanged(PlaybackDownload download) {
        Intrinsics.checkNotNullParameter(download, "download");
        Set<PlaybackDownloadChangeListener> set = this.changeListenerMap.get(download.getAsin());
        if (set == null) {
            return;
        }
        Iterator it = new HashSet(set).iterator();
        while (it.hasNext()) {
            ((PlaybackDownloadChangeListener) it.next()).onDownloadProgressChanged(download);
        }
    }
}
