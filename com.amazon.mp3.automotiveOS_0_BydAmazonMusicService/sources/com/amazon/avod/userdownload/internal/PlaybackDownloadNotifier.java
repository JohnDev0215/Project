package com.amazon.avod.userdownload.internal;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadChangeListener;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadNotifier extends SetListenerProxy<PlaybackDownloadChangeListener> {
    private final ExecutorService mDownloadListenerExecutor;
    private final InitializationLatch mInitializationLatch;

    public PlaybackDownloadNotifier() {
        this(ExecutorBuilder.newBuilderFor(PlaybackDownloadNotifier.class, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    PlaybackDownloadNotifier(@Nonnull ExecutorService executorService) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mDownloadListenerExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "downloadListenerExecutor");
    }

    public void notifyStateUpdate(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        this.mDownloadListenerExecutor.execute(new NotifyStateUpdate(playbackDownload));
    }

    public void notifyProgressUpdate(@Nonnull PlaybackDownload playbackDownload) {
        Preconditions.checkNotNull(playbackDownload, "download");
        this.mDownloadListenerExecutor.execute(new NotifyProgressUpdate(playbackDownload));
    }

    public void notifyDownloadAvailabilityChanged(@Nonnull ImmutableSet<PlaybackDownload> immutableSet) {
        Preconditions.checkNotNull(immutableSet, "downloads");
        this.mDownloadListenerExecutor.execute(new NotifyDownloadAvailabilityChanged(immutableSet));
    }

    private class NotifyProgressUpdate implements Runnable {
        private final PlaybackDownload mDownload;

        NotifyProgressUpdate(@Nonnull PlaybackDownload playbackDownload) {
            this.mDownload = playbackDownload;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = PlaybackDownloadNotifier.this.getListeners().iterator();
            while (it.hasNext()) {
                ((PlaybackDownloadChangeListener) it.next()).onDownloadProgressChanged(this.mDownload);
            }
        }
    }

    private class NotifyStateUpdate implements Runnable {
        private final PlaybackDownload mDownload;

        NotifyStateUpdate(@Nonnull PlaybackDownload playbackDownload) {
            this.mDownload = playbackDownload;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = PlaybackDownloadNotifier.this.getListeners().iterator();
            while (it.hasNext()) {
                ((PlaybackDownloadChangeListener) it.next()).onDownloadStateChanged(this.mDownload);
            }
        }
    }

    private class NotifyDownloadAvailabilityChanged implements Runnable {
        private final ImmutableSet<PlaybackDownload> mDownloads;

        NotifyDownloadAvailabilityChanged(@Nonnull ImmutableSet<PlaybackDownload> immutableSet) {
            this.mDownloads = immutableSet;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (PlaybackDownloadChangeListener playbackDownloadChangeListener : PlaybackDownloadNotifier.this.getListeners()) {
                ImmutableSet.Builder builder = ImmutableSet.builder();
                UnmodifiableIterator<PlaybackDownload> it = this.mDownloads.iterator();
                while (it.hasNext()) {
                    PlaybackDownload next = it.next();
                    playbackDownloadChangeListener.onDownloadAvailabilityChanged(next);
                    builder.add(next);
                }
                ImmutableSet<PlaybackDownload> immutableSetBuild = builder.build();
                if (!immutableSetBuild.isEmpty()) {
                    playbackDownloadChangeListener.onDownloadsAvailabilityChanged(immutableSetBuild);
                }
            }
        }
    }
}
