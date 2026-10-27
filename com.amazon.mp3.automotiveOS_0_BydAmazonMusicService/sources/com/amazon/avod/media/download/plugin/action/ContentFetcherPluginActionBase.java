package com.amazon.avod.media.download.plugin.action;

import com.amazon.avod.media.download.plugin.ContentFetcherPluginContext;
import com.amazon.avod.media.download.plugin.action.ContentPluginActionResult;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public abstract class ContentFetcherPluginActionBase implements ContentFetcherPluginAction {
    private ContentFetcherPluginAction mNextAction;
    private ContentFetcherPluginContext mPluginContext;

    public static ContentPluginActionResult createSuccessfulResult(String str) {
        return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Success, str);
    }

    public static ContentPluginActionResult createFailedResult(String str) {
        return new ContentPluginActionResult(ContentPluginActionResult.ResultStatus.Failure, str);
    }

    public void init(ContentFetcherPluginContext contentFetcherPluginContext) {
        this.mPluginContext = contentFetcherPluginContext;
    }

    public ContentFetcherPluginContext getPluginContext() {
        return this.mPluginContext;
    }

    protected final void setNextAction(ContentFetcherPluginAction contentFetcherPluginAction) {
        this.mNextAction = contentFetcherPluginAction;
    }

    @Override // com.amazon.avod.media.download.plugin.action.ContentFetcherPluginAction
    public ContentFetcherPluginAction next() {
        return this.mNextAction;
    }
}
