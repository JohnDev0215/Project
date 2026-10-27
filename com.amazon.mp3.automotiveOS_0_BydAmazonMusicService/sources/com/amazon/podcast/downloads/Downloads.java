package com.amazon.podcast.downloads;

import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.Intent;
import com.amazon.music.downloads.worker.DownloadEventListener;
import com.amazon.music.downloads.worker.ErrorReason;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.bootstrap.DownloadSettingsProvider;
import com.amazon.soa.core.Engine;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class Downloads implements DownloadEventListener {
    private final Context context;
    private DownloadEventListener downloadEventListener;
    private final Engine engine;
    private final Object listenersLock = new Object();
    private final Map<String, Set<EventsListener>> listenersLookup = new HashMap();
    private final Map<String, DownloadProgress> progressMap = new HashMap();
    private final Set<String> requestedDownloads = new HashSet();

    public interface EventsListener {
        void onDownloadRequestCompleted();

        void onDownloadRequested();

        void onProgress(final long bytesDownloaded, final long fileSizeInBytes);
    }

    public Downloads(final Engine engine, final Context context) {
        this.engine = engine;
        this.context = context;
    }

    public void checkSettingsAndDispatchOnDownload(final String ownerId, final List<Method> methods) {
        final DownloadSettingsProvider downloadSettingsProvider = Podcast.getDownloadSettingsProvider();
        if (downloadSettingsProvider.shouldShowDownloadSettings()) {
            downloadSettingsProvider.showDownloadSettings(new PodcastDownloader() { // from class: com.amazon.podcast.downloads.Downloads.1
                @Override // com.amazon.podcast.downloads.PodcastDownloader
                public void startDownload() {
                    Downloads.this.checkAndRequestStoragePermission(ownerId, methods, downloadSettingsProvider);
                }
            });
        } else {
            checkAndRequestStoragePermission(ownerId, methods, downloadSettingsProvider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndRequestStoragePermission(final String ownerId, final List<Method> methods, final DownloadSettingsProvider downloadProvider) {
        PodcastDownloader podcastDownloader = new PodcastDownloader() { // from class: com.amazon.podcast.downloads.Downloads.2
            @Override // com.amazon.podcast.downloads.PodcastDownloader
            public void startDownload() {
                Downloads.this.engine.handleMethods(ownerId, methods);
            }
        };
        if (downloadProvider.shouldRequestStoragePermission()) {
            downloadProvider.requestStoragePermission(podcastDownloader);
        } else {
            this.engine.handleMethods(ownerId, methods);
        }
    }

    public DownloadProgress getProgress(final String episodeId) {
        return this.progressMap.get(episodeId);
    }

    public boolean isDownloadRequested(final String episodeId) {
        return this.requestedDownloads.contains(episodeId);
    }

    public void addEventsListener(final String id, final EventsListener eventsListener) {
        synchronized (this.listenersLock) {
            Set<EventsListener> hashSet = this.listenersLookup.get(id);
            if (hashSet == null) {
                hashSet = new HashSet<>();
                this.listenersLookup.put(id, hashSet);
            }
            hashSet.add(eventsListener);
        }
    }

    public void removeEventsListener(final String id, final EventsListener eventsListener) {
        synchronized (this.listenersLock) {
            Set<EventsListener> set = this.listenersLookup.get(id);
            this.listenersLookup.remove(id);
            if (set == null) {
                return;
            }
            set.remove(eventsListener);
        }
    }

    public void cancel(final String id) {
        Intent intent = new Intent(this.context, (Class<?>) DownloadService.class);
        intent.putExtra("action", "cancel");
        intent.putExtra("id", id);
        this.context.startService(intent);
    }

    void setDownloadEventListener(final DownloadEventListener listener) {
        this.downloadEventListener = listener;
    }

    void downloadRequested(final String id) {
        this.requestedDownloads.add(id);
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.downloads.Downloads.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Downloads.this.listenersLookup) {
                    Set set = (Set) Downloads.this.listenersLookup.get(id);
                    if (set == null) {
                        return;
                    }
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        ((EventsListener) it.next()).onDownloadRequested();
                    }
                }
            }
        });
    }

    void downloadRequestFailed(final String id) {
        this.requestedDownloads.remove(id);
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.downloads.Downloads.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Downloads.this.listenersLookup) {
                    Set set = (Set) Downloads.this.listenersLookup.get(id);
                    if (set == null) {
                        return;
                    }
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        ((EventsListener) it.next()).onDownloadRequestCompleted();
                    }
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onAdded(final String id) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onAdded(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onProgress(final String id, final long bytesDownloaded, final long fileSizeInBytes) {
        this.progressMap.put(id, new DownloadProgress(bytesDownloaded, fileSizeInBytes));
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener != null) {
            downloadEventListener.onProgress(id, bytesDownloaded, fileSizeInBytes);
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.downloads.Downloads.5
            @Override // java.lang.Runnable
            public void run() {
                synchronized (Downloads.this.listenersLookup) {
                    Set set = (Set) Downloads.this.listenersLookup.get(id);
                    if (set == null) {
                        return;
                    }
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        ((EventsListener) it.next()).onProgress(bytesDownloaded, fileSizeInBytes);
                    }
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onPaused(final String id) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onPaused(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onCanceled(final String id) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onCanceled(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onResumed(final String id) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onResumed(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onError(id, errorReason);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason, final int errorCode) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onError(id, errorReason, errorCode);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onDownloaded(final String id) {
        DownloadEventListener downloadEventListener = this.downloadEventListener;
        if (downloadEventListener == null) {
            return;
        }
        downloadEventListener.onDownloaded(id);
    }
}
