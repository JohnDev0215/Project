package com.amazon.avod.content.smoothstream.manifest.acquisition;

import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.manifest.acquisition.events.NewContentDownloadedEvent;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.BasicDownloadRequest;
import com.amazon.avod.media.downloadservice.BlockingDownloadAdapter;
import com.amazon.avod.media.downloadservice.DownloadCallback;
import com.amazon.avod.media.downloadservice.DownloadRequestPriority;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.retry.BackoffPolicySupportedRetriableCall;
import com.amazon.avod.media.framework.retry.ExceptionBasedRetryPolicy;
import com.amazon.avod.media.framework.retry.RetriableCall;
import com.amazon.avod.media.framework.retry.RetryListener;
import com.amazon.avod.media.framework.retry.RetryPolicy;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.BackoffPolicy;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class RetryingObjectAcquirer<T> {
    private final ContentManagementEventBus mContentEventBus;
    private ContentUrl mCurrentContentUrl;
    private final BlockingDownloadAdapter mDownloadAdapter;
    private final RetryPolicy<T> mRetryPolicy;
    private final Stopwatch mStopwatch;

    public interface RetryingObjectAcquirerCallbacks<T> extends SaveCallback {
        void deleteLocalObject();

        Map<String, String> getObjectHeaders();

        String getObjectUrl(ContentUrl contentUrl);

        boolean localObjectExists();

        T readLocalObject(@Nullable Map<String, List<String>> map) throws ContentException;

        ContentException translateException(Exception exc, @Nullable DownloadStatistics downloadStatistics);
    }

    public RetryingObjectAcquirer(@Nonnull DownloadService downloadService, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        this(new BlockingDownloadAdapter(downloadService), contentManagementEventBus, new ExceptionBasedRetryPolicy(ImmutableList.of(ContentException.class)), Stopwatch.createUnstarted(Tickers.androidTicker()));
    }

    RetryingObjectAcquirer(@Nonnull BlockingDownloadAdapter blockingDownloadAdapter, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull RetryPolicy<T> retryPolicy, @Nonnull Stopwatch stopwatch) {
        this.mCurrentContentUrl = null;
        this.mDownloadAdapter = (BlockingDownloadAdapter) Preconditions.checkNotNull(blockingDownloadAdapter, "downloadAdapter");
        this.mContentEventBus = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentEventBus");
        this.mRetryPolicy = (RetryPolicy) Preconditions.checkNotNull(retryPolicy, "retryPolicy");
        this.mStopwatch = (Stopwatch) Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
    }

    public T acquireObject(final ContentUrlSelector contentUrlSelector, final PlayableContent playableContent, final ContentSessionType contentSessionType, int i, TimeSpan timeSpan, @Nullable BackoffPolicy backoffPolicy, final RetryingObjectAcquirerCallbacks<T> retryingObjectAcquirerCallbacks, final DownloadRequestPriority downloadRequestPriority, final boolean z, final boolean z2, final boolean z3, final boolean z4, final TimeSpan timeSpan2, final boolean z5, final String str, @Nonnull final DownloadType downloadType, @Nonnull final ContentType contentType, final boolean z6, final boolean z7, final boolean z8) throws Throwable {
        synchronized (this) {
            try {
                try {
                    Preconditions.checkNotNull(downloadType, "downloadType");
                    this.mCurrentContentUrl = contentUrlSelector.getCurrentContentUrl();
                    this.mStopwatch.start();
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    try {
                        try {
                            return new BackoffPolicySupportedRetriableCall(new Callable<T>() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.1
                                @Override // java.util.concurrent.Callable
                                public T call() throws ContentException {
                                    boolean z9;
                                    if (retryingObjectAcquirerCallbacks.localObjectExists()) {
                                        try {
                                            if (!z5) {
                                                DLog.logf("Object for %s already downloaded; recycling!", playableContent);
                                                return (T) retryingObjectAcquirerCallbacks.readLocalObject(null);
                                            }
                                            z9 = false;
                                        } catch (ContentException e) {
                                            DLog.errorf("Reading cached object for %s failed - will attempt re-download", playableContent);
                                            RetryingObjectAcquirer.this.mContentEventBus.postEvent(new RetriableContentEventError(playableContent, contentSessionType, e, 1));
                                            retryingObjectAcquirerCallbacks.deleteLocalObject();
                                            z9 = true;
                                        }
                                    } else {
                                        z9 = false;
                                    }
                                    RetryingObjectAcquirer retryingObjectAcquirer = RetryingObjectAcquirer.this;
                                    return (T) retryingObjectAcquirer.downloadObject(playableContent, contentSessionType, retryingObjectAcquirer.getDecoratedContentUrl(z3, z4), contentUrlSelector, retryingObjectAcquirerCallbacks, downloadRequestPriority, z, z2, timeSpan2, contentType, (z5 || z9 || !z6) ? false : true, z7, z8);
                                }
                            }, this.mRetryPolicy, i, new RetryListener<T>() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.2
                                @Override // com.amazon.avod.media.framework.retry.RetryListener
                                public RetriableCall.RetryCode onRetry(T t, Exception exc, int i2) {
                                    ContentException contentExceptionTranslateException = retryingObjectAcquirerCallbacks.translateException(exc, null);
                                    RetriableCall.RetryCode retryCodeNotifyDownloadFailure = contentUrlSelector.notifyDownloadFailure(contentExceptionTranslateException, RetryingObjectAcquirer.this.mCurrentContentUrl, i2, 0L, true, str, contentExceptionTranslateException.getDownloadStatistics(), downloadType);
                                    RetryingObjectAcquirer.this.mContentEventBus.postEvent(new RetriableContentEventError(playableContent, contentSessionType, contentExceptionTranslateException, RetryingObjectAcquirer.this.mCurrentContentUrl, null, i2, null, RetryingObjectAcquirer.this.mStopwatch.elapsed(TimeUnit.NANOSECONDS)));
                                    RetryingObjectAcquirer.this.mCurrentContentUrl = contentUrlSelector.getCurrentContentUrl();
                                    return retryCodeNotifyDownloadFailure;
                                }
                            }, (int) timeSpan.getTotalMilliseconds(), backoffPolicy).call();
                        } catch (Exception e) {
                            throw retryingObjectAcquirerCallbacks.translateException(e, null);
                        }
                    } finally {
                        this.mStopwatch.reset();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T downloadObject(PlayableContent playableContent, ContentSessionType contentSessionType, ContentUrl contentUrl, final ContentUrlSelector contentUrlSelector, final RetryingObjectAcquirerCallbacks<T> retryingObjectAcquirerCallbacks, DownloadRequestPriority downloadRequestPriority, boolean z, boolean z2, TimeSpan timeSpan, ContentType contentType, boolean z3, boolean z4, boolean z5) throws ContentException {
        BlockingDownloadAdapter.BlockingDownloadResult blockingDownloadResultSubmitRequestAndBlock = this.mDownloadAdapter.submitRequestAndBlock(new BasicDownloadRequest(retryingObjectAcquirerCallbacks.getObjectUrl(contentUrl), retryingObjectAcquirerCallbacks.getObjectHeaders(), new DownloadCallback() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.RetryingObjectAcquirer.3
            @Override // com.amazon.avod.media.downloadservice.SaveCallback
            public void save(InputStream inputStream, int i) throws MediaException {
                retryingObjectAcquirerCallbacks.save(inputStream, i);
            }

            @Override // com.amazon.avod.media.downloadservice.DownloadCallback
            public void onDownloadRedirected(@Nonnull Uri uri) {
                if (contentUrlSelector.notifyDownloadRedirect(RetryingObjectAcquirer.this.mCurrentContentUrl, uri)) {
                    RetryingObjectAcquirer.this.mCurrentContentUrl = contentUrlSelector.getCurrentContentUrl();
                }
            }
        }, this.mDownloadAdapter, timeSpan.getTotalNanoSeconds(), downloadRequestPriority, z, z2, z3, z4, z5), downloadRequestPriority.getTier() == PriorityTier.PRIMARY);
        if (blockingDownloadResultSubmitRequestAndBlock.isFailure()) {
            throw retryingObjectAcquirerCallbacks.translateException(blockingDownloadResultSubmitRequestAndBlock.getException(), blockingDownloadResultSubmitRequestAndBlock.getStatistics());
        }
        contentUrlSelector.notifyDownloadSuccess(blockingDownloadResultSubmitRequestAndBlock.getStatistics());
        this.mContentEventBus.postEvent(new NewContentDownloadedEvent(playableContent, contentSessionType, contentUrl, blockingDownloadResultSubmitRequestAndBlock.getStatistics(), contentType));
        DownloadStatistics statistics = blockingDownloadResultSubmitRequestAndBlock.getStatistics();
        return retryingObjectAcquirerCallbacks.readLocalObject((statistics == null || statistics.getUnformattedHeaders() == null) ? null : processInvalidHeaders(statistics.getUnformattedHeaders()));
    }

    private Map<String, List<String>> processInvalidHeaders(Map<String, List<String>> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && !key.isEmpty() && entry.getValue() != null) {
                map2.put(key, entry.getValue());
            }
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentUrl getDecoratedContentUrl(boolean z, boolean z2) {
        ContentUrl contentUrlDecorateCurrentContentUrl = this.mCurrentContentUrl;
        if (z2) {
            contentUrlDecorateCurrentContentUrl = decorateCurrentContentUrl(contentUrlDecorateCurrentContentUrl, "manifestType", "segmentList");
        }
        return (z && ContentUrl.isDashUrl(contentUrlDecorateCurrentContentUrl)) ? decorateCurrentContentUrl(contentUrlDecorateCurrentContentUrl, "encoding", "hex") : contentUrlDecorateCurrentContentUrl;
    }

    private ContentUrl decorateCurrentContentUrl(ContentUrl contentUrl, String str, String str2) {
        return new ContentUrl(Uri.parse(contentUrl.getUrl()).buildUpon().appendQueryParameter(str, str2).build().toString(), contentUrl);
    }
}
