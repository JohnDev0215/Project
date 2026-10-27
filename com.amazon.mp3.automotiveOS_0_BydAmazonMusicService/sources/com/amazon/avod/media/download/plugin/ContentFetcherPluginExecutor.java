package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.download.internal.PlaybackDownloadQueueConfig;
import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginAction;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentFetcherPluginExecutor {
    private Future<?> mCompletionFuture;
    private final List<ContentFetcherPlugin> mPlugins;
    private final List<ContentFetcherPluginExecutorListener> mPluginListeners = Lists.newLinkedList();
    private final AtomicReference<StartPluginsRunnable> mCurrentStartPluginsRunnable = new AtomicReference<>();
    private final AtomicReference<ExecutorService> mExecutorService = new AtomicReference<>();
    private final PlaybackDownloadQueueConfig mPlaybackDownloadQueueConfig = new PlaybackDownloadQueueConfig();
    private final Runnable mPluginClear = new ClearPluginsRunnable();

    public ContentFetcherPluginExecutor(List<ContentFetcherPlugin> list) {
        this.mPlugins = list;
    }

    protected class StartPluginsRunnable implements Runnable {
        private volatile boolean mIsCanceled = false;

        protected StartPluginsRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mIsCanceled) {
                return;
            }
            startPlugins(ContentFetcherPluginExecutor.this.mPlugins, ContentFetcherPluginExecutor.this.mPluginListeners);
        }

        public void cancel() {
            this.mIsCanceled = true;
        }

        private void startPlugins(@Nonnull List<ContentFetcherPlugin> list, @Nonnull List<ContentFetcherPluginExecutorListener> list2) {
            for (ContentFetcherPlugin contentFetcherPlugin : list) {
                if (this.mIsCanceled) {
                    return;
                }
                Optional<TimeSpan> optionalProcessPlugin = processPlugin(contentFetcherPlugin);
                if (optionalProcessPlugin.isPresent()) {
                    notifyListeners(list2, contentFetcherPlugin, optionalProcessPlugin.get());
                }
            }
        }

        private Optional<TimeSpan> processPlugin(@Nonnull ContentFetcherPlugin contentFetcherPlugin) {
            Optional<TimeSpan> optionalAbsent;
            synchronized (contentFetcherPlugin.getLock()) {
                if (contentFetcherPlugin.isComplete()) {
                    return Optional.of(TimeSpan.ZERO);
                }
                Stopwatch stopwatchCreateStarted = Stopwatch.createStarted(Tickers.androidTicker());
                if (performActionChain(contentFetcherPlugin.getFirstAction(), contentFetcherPlugin)) {
                    optionalAbsent = Optional.of(new TimeSpan(stopwatchCreateStarted.stop()));
                } else {
                    optionalAbsent = Optional.absent();
                }
                return optionalAbsent;
            }
        }

        private boolean performActionChain(@Nullable ContentFetcherPluginAction contentFetcherPluginAction, @Nonnull ContentFetcherPlugin contentFetcherPlugin) {
            while (contentFetcherPluginAction != null && !this.mIsCanceled) {
                contentFetcherPluginAction = ContentFetcherPluginExecutor.performAction(contentFetcherPluginAction);
            }
            if (this.mIsCanceled && contentFetcherPluginAction != null) {
                ContentFetcherPluginExecutor.cleanupPlugin(contentFetcherPlugin);
            }
            return contentFetcherPluginAction == null;
        }

        private void notifyListeners(@Nonnull List<ContentFetcherPluginExecutorListener> list, @Nonnull ContentFetcherPlugin contentFetcherPlugin, @Nonnull TimeSpan timeSpan) {
            synchronized (list) {
                Iterator<ContentFetcherPluginExecutorListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onDownloadComplete(contentFetcherPlugin, timeSpan);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static ContentFetcherPluginAction performAction(@Nonnull ContentFetcherPluginAction contentFetcherPluginAction) {
        try {
            contentFetcherPluginAction.call();
        } catch (Throwable th) {
            DLog.exceptionf(th, "[ContentFetcherPluginExecutor] Could not run action: %s", contentFetcherPluginAction);
        }
        return contentFetcherPluginAction.next();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void cleanupPlugin(@Nonnull ContentFetcherPlugin contentFetcherPlugin) {
        Optional<ContentFetcherPluginAction> cleanupAction = contentFetcherPlugin.getCleanupAction();
        try {
            if (cleanupAction.isPresent()) {
                cleanupAction.get().call();
            }
        } catch (Throwable th) {
            DLog.exceptionf(th, "[ContentFetcherPluginExecutor] Could not run CleanupAction action: %s", cleanupAction);
        }
    }

    protected class ClearPluginsRunnable implements Runnable {
        protected ClearPluginsRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            for (ContentFetcherPlugin contentFetcherPlugin : ContentFetcherPluginExecutor.this.mPlugins) {
                synchronized (contentFetcherPlugin.getLock()) {
                    try {
                        contentFetcherPlugin.clear();
                    } finally {
                    }
                }
            }
        }
    }

    public void start() {
        for (ContentFetcherPlugin contentFetcherPlugin : this.mPlugins) {
            synchronized (this.mPluginListeners) {
                Iterator<ContentFetcherPluginExecutorListener> it = this.mPluginListeners.iterator();
                while (it.hasNext()) {
                    it.next().onDownloadStart(contentFetcherPlugin);
                }
            }
        }
        StartPluginsRunnable startPluginsRunnable = new StartPluginsRunnable();
        StartPluginsRunnable andSet = this.mCurrentStartPluginsRunnable.getAndSet(startPluginsRunnable);
        if (andSet != null) {
            andSet.cancel();
        }
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, "PluginStart").withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.INFO).build();
        ExecutorService andSet2 = this.mExecutorService.getAndSet(threadPoolExecutorBuild);
        if (andSet2 != null) {
            andSet2.shutdown();
        }
        try {
            this.mCompletionFuture = threadPoolExecutorBuild.submit(startPluginsRunnable);
        } finally {
            threadPoolExecutorBuild.shutdown();
        }
    }

    public void waitForPluginsToFinish() {
        Future<?> future = this.mCompletionFuture;
        if (future == null) {
            return;
        }
        try {
            future.get(this.mPlaybackDownloadQueueConfig.getPluginDownloadTimeoutMillis(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException unused2) {
        } catch (TimeoutException unused3) {
            DLog.warnf("[ContentFetcherPluginExecutor] Timed out waiting for plugins to finish downloading");
            Profiler.reportCounterMetric(new SimpleCounterMetric("AmazonVideo:Download:PluginTimeout"));
        }
    }

    public void cancel() {
        StartPluginsRunnable andSet = this.mCurrentStartPluginsRunnable.getAndSet(null);
        if (andSet != null) {
            andSet.cancel();
        }
    }

    public void addPluginListener(@Nullable ContentFetcherPluginExecutorListener contentFetcherPluginExecutorListener) {
        if (contentFetcherPluginExecutorListener == null) {
            return;
        }
        synchronized (this.mPluginListeners) {
            if (!this.mPluginListeners.contains(contentFetcherPluginExecutorListener)) {
                this.mPluginListeners.add(contentFetcherPluginExecutorListener);
            }
        }
    }
}
