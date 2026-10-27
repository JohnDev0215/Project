package com.amazon.mp3.download;

import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloaderListenerDispatcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J$\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J&\u0010\u0012\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0001J\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0001R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/download/DownloaderListenerDispatcher;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "shouldNotifyAll", "", "(Z)V", "listeners", "", "mTAG", "", "onProgressUpdate", "", TetheredMessageKey.requestId, "group", "Lcom/amazon/music/downloads/Group;", "percentageDownloaded", "", "item", "Lcom/amazon/music/downloads/Item;", "onStatusUpdate", "downloadState", "Lcom/amazon/music/downloads/DownloadState;", "registerListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "unregisterListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloaderListenerDispatcher extends DownloadListener {
    private Set<DownloadListener> listeners;
    private final String mTAG;

    public DownloaderListenerDispatcher(boolean z) {
        super(z);
        String simpleName = DownloaderListenerDispatcher.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "DownloaderListenerDispat…er::class.java.simpleName");
        this.mTAG = simpleName;
        Set<DownloadListener> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
        Intrinsics.checkNotNullExpressionValue(setNewSetFromMap, "newSetFromMap(Concurrent…loadListener, Boolean>())");
        this.listeners = setNewSetFromMap;
    }

    public final void unregisterListener(DownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.debug(this.mTAG, Intrinsics.stringPlus("UnRegister listener called with ", listener));
        this.listeners.remove(listener);
    }

    public final void registerListener(DownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.debug(this.mTAG, Intrinsics.stringPlus("Register listener called with ", listener));
        this.listeners.add(listener);
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String requestId, Group group) {
        Log.debug(this.mTAG, "Download status update for requestID " + ((Object) requestId) + ", downloadState " + downloadState + ", group " + group);
        Iterator<DownloadListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStatusUpdate(downloadState, requestId, group);
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String requestId, Item item) {
        Log.debug(this.mTAG, "Download status update for requestID " + ((Object) requestId) + ", downloadState " + downloadState + ", item " + item);
        Iterator<DownloadListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onStatusUpdate(downloadState, requestId, item);
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String requestId, Group group, float percentageDownloaded) {
        Iterator<DownloadListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onProgressUpdate(requestId, group, percentageDownloaded);
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String requestId, Item item, float percentageDownloaded) {
        Iterator<DownloadListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onProgressUpdate(requestId, item, percentageDownloaded);
        }
    }
}
