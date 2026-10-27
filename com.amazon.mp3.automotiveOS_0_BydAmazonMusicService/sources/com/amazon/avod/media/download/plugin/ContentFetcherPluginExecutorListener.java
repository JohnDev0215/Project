package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentFetcherPluginExecutorListener {
    void onDownloadComplete(@Nonnull ContentFetcherPlugin contentFetcherPlugin, @Nonnull TimeSpan timeSpan);

    void onDownloadStart(@Nonnull ContentFetcherPlugin contentFetcherPlugin);
}
