package com.amazon.avod.media.downloadservice;

import com.amazon.avod.media.framework.event.EventListenerSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadListenerSet extends EventListenerSet<DownloadListener> implements DownloadListener {
    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
        Set<DownloadListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<DownloadListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onSuccess(downloadRequest, downloadStatistics);
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics) {
        Set<DownloadListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<DownloadListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onFailure(downloadRequest, exc, downloadStatistics);
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onCancel(DownloadRequest downloadRequest) {
        Set<DownloadListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<DownloadListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onCancel(downloadRequest);
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
        Set<DownloadListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<DownloadListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadProgress(downloadRequest, downloadStatistics, j);
            }
        }
    }
}
