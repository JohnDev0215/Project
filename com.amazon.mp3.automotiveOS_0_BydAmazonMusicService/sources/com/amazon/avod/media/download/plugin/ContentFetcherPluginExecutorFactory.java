package com.amazon.avod.media.download.plugin;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ContentFetcherPluginExecutorFactory {
    public ContentFetcherPluginExecutor newPluginExecutor(@Nonnull ContentFetcherPluginContext contentFetcherPluginContext, Set<? extends ContentFetcherPlugin> set) {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(set.size());
        for (ContentFetcherPlugin contentFetcherPlugin : set) {
            contentFetcherPlugin.init(contentFetcherPluginContext);
            arrayListNewArrayListWithCapacity.add(contentFetcherPlugin);
        }
        return new ContentFetcherPluginExecutor(arrayListNewArrayListWithCapacity);
    }
}
