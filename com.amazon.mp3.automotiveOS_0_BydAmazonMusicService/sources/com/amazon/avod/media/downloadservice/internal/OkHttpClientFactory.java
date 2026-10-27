package com.amazon.avod.media.downloadservice.internal;

import android.content.Context;
import com.amazon.avod.qahooks.PlaybackQASettings;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public class OkHttpClientFactory {
    private static final OkHttpClientFactory INSTANCE = new OkHttpClientFactory();
    private OkHttpClient mClient;
    private OkHttpClient mNoRedirectClient;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private final DownloadServiceConfig mDownloadServiceConfig = DownloadServiceConfig.INSTANCE;

    @Nonnull
    public static OkHttpClientFactory getInstance() {
        return INSTANCE;
    }

    private OkHttpClientFactory() {
    }

    public void initialize(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mInitializationLatch.start(60L, TimeUnit.SECONDS);
        if (this.mDownloadServiceConfig.isOkHttpForDownloadServiceEnabled()) {
            OkHttpClient.Builder builderNewBuilder = new OkHttpClient().newBuilder();
            long httpConnectionTimeoutMilliseconds = this.mDownloadServiceConfig.getHttpConnectionTimeoutMilliseconds();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            builderNewBuilder.connectTimeout(httpConnectionTimeoutMilliseconds, timeUnit);
            builderNewBuilder.readTimeout(this.mDownloadServiceConfig.getHttpSocketTimeoutMilliseconds(), timeUnit);
            if (this.mDownloadServiceConfig.isHttpResponseCacheEnabled()) {
                builderNewBuilder.cache(new Cache(new File(context.getCacheDir(), "DownloadServiceHttpResponseCache"), DataUnit.MEGABYTES.toBytes(this.mDownloadServiceConfig.getHttpResponseCacheSizeMB())));
            }
            if (PlaybackQASettings.getInstance().isPlaybackQAHooksEnabled()) {
                builderNewBuilder.addInterceptor(new QAHttpRequestThrottlingInterceptor());
            }
            OkHttpClient okHttpClientBuild = builderNewBuilder.build();
            this.mClient = okHttpClientBuild;
            this.mNoRedirectClient = okHttpClientBuild.newBuilder().followRedirects(false).build();
        }
        this.mInitializationLatch.complete();
    }

    @Nullable
    public OkHttpClient getOkHttpClient(boolean z) {
        return z ? this.mClient : this.mNoRedirectClient;
    }
}
