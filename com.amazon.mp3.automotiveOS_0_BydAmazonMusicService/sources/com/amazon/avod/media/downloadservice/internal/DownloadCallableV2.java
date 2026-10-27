package com.amazon.avod.media.downloadservice.internal;

import android.net.Uri;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.exceptions.NetworkIOException;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.util.DLog;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
class DownloadCallableV2 extends DownloadCallable {
    private final OkHttpRequester mOkHttpRequester;

    DownloadCallableV2(@Nonnull OkHttpRequester okHttpRequester, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DownloadCompletedListener downloadCompletedListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull Ticker ticker, @Nonnull SurgingBufferPool surgingBufferPool, boolean z, boolean z2, @Nonnull TimeSpan timeSpan, boolean z3, boolean z4, @Nonnull String str, boolean z5, @Nonnull TimeSpan timeSpan2, boolean z6) {
        super(networkConnectionManager, downloadCompletedListener, downloadRequest, downloadStatisticsBuilder, ticker, surgingBufferPool, z, z2, timeSpan, z3, z4, str, z5, timeSpan2, z6);
        this.mOkHttpRequester = (OkHttpRequester) Preconditions.checkNotNull(okHttpRequester, "okHttpRequester");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Void call() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 782
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.downloadservice.internal.DownloadCallableV2.call():java.lang.Void");
    }

    private void fail(@Nonnull Exception exc) throws MediaException, NetworkIOException {
        if (!this.mIsCancelled && this.mShouldPingServerAfterDownloadFailure) {
            String url = this.mDownloadRequest.getUrl();
            String str = this.mPingUrl;
            try {
                Response response = this.mOkHttpRequester.getResponse(str, Collections.emptyMap(), false, false, this.mHttpResponseCacheMaxAge);
                try {
                    DLog.warnf("DownloadCallableV2 Download/processing failed for url %s, executing HttpGet request for %s responseCode: %d", url, str, Integer.valueOf(response.code()));
                    response.close();
                } finally {
                }
            } catch (IOException e) {
                DLog.warnf("DownloadCallableV2 Failed to execute HttpGet request for %s (added to diagnose download timeouts), ErrorMessage: %s", str, e.getMessage());
            }
        }
        if (exc instanceof MediaException) {
            throw ((MediaException) exc);
        }
        String message = exc.getMessage();
        if (message == null) {
            message = exc.getClass().getSimpleName();
        }
        throw new NetworkIOException(message, exc);
    }

    private void checkAndNotifyRedirect(@Nonnull Response response) {
        Response responsePriorResponse = response.priorResponse();
        if (responsePriorResponse != null) {
            if (responsePriorResponse.code() == 302 || responsePriorResponse.code() == 301) {
                this.mDownloadRequest.getDownloadCallback().onDownloadRedirected(Uri.parse(responsePriorResponse.header(HttpHeader.LOCATION)));
            }
        }
    }

    static class OkHttpRequester {
        private boolean mIsEnabledDTIDInQueryParameter;
        private final MediaSystemSharedDependencies mMediaSystemSharedDependencies = MediaSystemSharedDependencies.getInstance();
        private final OkHttpClient mOkHttpClient;
        private boolean mRequestGzipCompression;
        private final String mUserAgent;

        OkHttpRequester(@Nonnull OkHttpClient okHttpClient, @Nonnull String str, boolean z, boolean z2) {
            this.mOkHttpClient = (OkHttpClient) Preconditions.checkNotNull(okHttpClient, "okHttpClient");
            this.mUserAgent = (String) Preconditions.checkNotNull(str, "userAgent");
            this.mIsEnabledDTIDInQueryParameter = z;
            this.mRequestGzipCompression = z2;
        }

        @Nonnull
        Response getResponse(@Nonnull String str, @Nonnull Map<String, String> map, boolean z, boolean z2, @Nonnull TimeSpan timeSpan) throws IOException {
            if (this.mIsEnabledDTIDInQueryParameter) {
                this.mMediaSystemSharedDependencies.waitForInitializationUninterruptibly();
                str = Uri.parse(str).buildUpon().appendQueryParameter("amznDtid", this.mMediaSystemSharedDependencies.getDeviceIdentity().getDeviceTypeId()).build().toString();
            }
            URL url = new URL(str);
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            builder.header(HttpHeader.USER_AGENT, this.mUserAgent);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
            if (z) {
                builder.header("Accept-Encoding", "identity");
            } else if (this.mRequestGzipCompression) {
                builder.header("Accept-Encoding", "gzip");
            }
            if (z2) {
                builder.cacheControl(new CacheControl.Builder().maxAge(timeSpan.getTotalMinutes(), TimeUnit.MINUTES).build());
            } else {
                builder.cacheControl(CacheControl.FORCE_NETWORK);
            }
            return FirebasePerfOkHttpClient.execute(this.mOkHttpClient.newCall(builder.build()));
        }
    }
}
