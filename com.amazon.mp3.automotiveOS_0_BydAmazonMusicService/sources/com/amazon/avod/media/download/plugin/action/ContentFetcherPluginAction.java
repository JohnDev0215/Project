package com.amazon.avod.media.download.plugin.action;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentFetcherPluginAction extends Callable<ContentPluginActionResult> {
    ContentFetcherPluginAction next();
}
