package com.amazon.avod.media.downloadservice.internal;

import android.net.Uri;
import android.net.http.HttpResponseCache;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadCallback;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
class DownloadCallableV1 extends DownloadCallable {
    private final ConnectionOpener mConnectionOpener;
    private final HttpResponseCache mHttpResponseCache;

    DownloadCallableV1(@Nonnull ConnectionOpener connectionOpener, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DownloadCompletedListener downloadCompletedListener, @Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics.DownloadStatisticsBuilder downloadStatisticsBuilder, @Nonnull Ticker ticker, @Nonnull SurgingBufferPool surgingBufferPool, boolean z, boolean z2, @Nonnull TimeSpan timeSpan, boolean z3, boolean z4, @Nonnull String str, boolean z5, @Nonnull TimeSpan timeSpan2, boolean z6) {
        super(networkConnectionManager, downloadCompletedListener, downloadRequest, downloadStatisticsBuilder, ticker, surgingBufferPool, z, z2, timeSpan, z3, z4, str, z5, timeSpan2, z6);
        this.mConnectionOpener = (ConnectionOpener) Preconditions.checkNotNull(connectionOpener, "connectionOpener");
        this.mHttpResponseCache = HttpResponseCache.getInstalled();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Void call() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 790
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.downloadservice.internal.DownloadCallableV1.call():java.lang.Void");
    }

    private static long getContentLengthLong(@Nonnull HttpURLConnection httpURLConnection) {
        try {
            return Long.parseLong(httpURLConnection.getHeaderField("content-length"));
        } catch (Exception unused) {
            return -1L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void fail(@javax.annotation.Nonnull java.lang.Exception r19) throws java.lang.Throwable {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            boolean r0 = r1.mIsCancelled
            if (r0 != 0) goto L6d
            boolean r0 = r1.mShouldPingServerAfterDownloadFailure
            if (r0 == 0) goto L6d
            com.amazon.avod.media.downloadservice.DownloadRequest r0 = r1.mDownloadRequest
            java.lang.String r0 = r0.getUrl()
            java.lang.String r13 = r1.mPingUrl
            r15 = 1
            r16 = 0
            r12 = 2
            com.amazon.avod.media.downloadservice.internal.DownloadCallableV1$ConnectionOpener r3 = r1.mConnectionOpener     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            java.util.Map r5 = java.util.Collections.emptyMap()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            r6 = 0
            r7 = 0
            com.amazon.avod.media.TimeSpan r8 = r1.mHttpResponseCacheMaxAge     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
            r9 = 1
            r10 = 0
            r11 = 0
            r17 = 0
            r4 = r13
            r14 = r12
            r12 = r17
            java.net.HttpURLConnection r3 = r3.openConnection(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.io.IOException -> L48 java.lang.Throwable -> L4a
            int r4 = r3.getResponseCode()     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            java.lang.String r5 = "Download/processing failed for url %s, executing HttpGet request for %s responseCode: %d"
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            r6[r16] = r0     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            r6[r15] = r13     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            r6[r14] = r0     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            com.amazon.avod.util.DLog.warnf(r5, r6)     // Catch: java.io.IOException -> L46 java.lang.Throwable -> L65
            goto L61
        L46:
            r0 = move-exception
            goto L50
        L48:
            r0 = move-exception
            goto L4f
        L4a:
            r0 = move-exception
            r14 = 0
            goto L67
        L4d:
            r0 = move-exception
            r14 = r12
        L4f:
            r3 = 0
        L50:
            java.lang.String r4 = "Failed to execute HttpGet request for %s (added to diagnose download timeouts), ErrorMessage: %s"
            java.lang.Object[] r5 = new java.lang.Object[r14]     // Catch: java.lang.Throwable -> L65
            r5[r16] = r13     // Catch: java.lang.Throwable -> L65
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L65
            r5[r15] = r0     // Catch: java.lang.Throwable -> L65
            com.amazon.avod.util.DLog.warnf(r4, r5)     // Catch: java.lang.Throwable -> L65
            if (r3 == 0) goto L6d
        L61:
            r3.disconnect()
            goto L6d
        L65:
            r0 = move-exception
            r14 = r3
        L67:
            if (r14 == 0) goto L6c
            r14.disconnect()
        L6c:
            throw r0
        L6d:
            boolean r0 = r2 instanceof com.amazon.avod.media.framework.error.MediaException
            if (r0 != 0) goto L86
            java.lang.String r0 = r19.getMessage()
            if (r0 == 0) goto L78
            goto L80
        L78:
            java.lang.Class r0 = r19.getClass()
            java.lang.String r0 = r0.getSimpleName()
        L80:
            com.amazon.avod.media.downloadservice.exceptions.NetworkIOException r3 = new com.amazon.avod.media.downloadservice.exceptions.NetworkIOException
            r3.<init>(r0, r2)
            throw r3
        L86:
            r0 = r2
            com.amazon.avod.media.framework.error.MediaException r0 = (com.amazon.avod.media.framework.error.MediaException) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.downloadservice.internal.DownloadCallableV1.fail(java.lang.Exception):void");
    }

    static class ConnectionOpener {
        private final int mConnectTimeoutMillis;
        private boolean mIsEnabledDTIDInQueryParameter;
        private final MediaSystemSharedDependencies mMediaSystemSharedDependencies = MediaSystemSharedDependencies.getInstance();
        private final int mReadTimeoutMillis;
        private boolean mRequestGzipCompression;
        private final String mUserAgent;

        ConnectionOpener(@Nonnull String str, int i, int i2, boolean z, boolean z2) {
            this.mUserAgent = (String) Preconditions.checkNotNull(str, "userAgent");
            this.mConnectTimeoutMillis = i;
            this.mReadTimeoutMillis = i2;
            this.mIsEnabledDTIDInQueryParameter = z;
            this.mRequestGzipCompression = z2;
        }

        @Nonnull
        HttpURLConnection openConnection(@Nonnull String str, @Nonnull Map<String, String> map, boolean z, boolean z2, @Nonnull TimeSpan timeSpan, boolean z3, boolean z4, @Nullable DownloadCallback downloadCallback, boolean z5) throws IOException {
            String string;
            if (this.mIsEnabledDTIDInQueryParameter) {
                this.mMediaSystemSharedDependencies.waitForInitializationUninterruptibly();
                string = Uri.parse(str).buildUpon().appendQueryParameter("amznDtid", this.mMediaSystemSharedDependencies.getDeviceIdentity().getDeviceTypeId()).build().toString();
            } else {
                string = str;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(string).openConnection()));
            httpURLConnection.setRequestProperty(HttpHeader.USER_AGENT, this.mUserAgent);
            httpURLConnection.setConnectTimeout(this.mConnectTimeoutMillis);
            httpURLConnection.setReadTimeout(this.mReadTimeoutMillis);
            httpURLConnection.setInstanceFollowRedirects(z3 && !z4);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (z) {
                httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            } else if (this.mRequestGzipCompression) {
                httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            }
            if (z2) {
                httpURLConnection.setUseCaches(true);
                httpURLConnection.addRequestProperty("Cache-Control", String.format("max-age=%s", Integer.valueOf(timeSpan.getTotalSeconds())));
            } else {
                httpURLConnection.setUseCaches(false);
            }
            if (!z4) {
                return httpURLConnection;
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 302 || responseCode == 301) {
                return openConnection(httpURLConnection.getHeaderField(HttpHeader.LOCATION), map, z, z2, timeSpan, z3, true, downloadCallback, true);
            }
            if (!z5 || downloadCallback == null) {
                return httpURLConnection;
            }
            downloadCallback.onDownloadRedirected(Uri.parse(str));
            return httpURLConnection;
        }
    }
}
