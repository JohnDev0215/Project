package com.amazon.avod.media.download.plugin;

import com.amazon.avod.media.download.plugin.action.ContentFetcherPluginAction;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentFetcherPlugin {
    void clear();

    @Nonnull
    Optional<ContentFetcherPluginAction> getCleanupAction();

    ContentFetcherPluginAction getFirstAction();

    Object getLock();

    void init(ContentFetcherPluginContext contentFetcherPluginContext);

    boolean isComplete();
}
