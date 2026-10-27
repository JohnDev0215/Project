package com.amazon.avod.media.download.plugin.action;

/* JADX INFO: loaded from: classes2.dex */
public class ContentPluginActionResult {
    private final String mMessage;
    private final ResultStatus mStatus;

    public enum ResultStatus {
        Success,
        Failure
    }

    public ContentPluginActionResult(ResultStatus resultStatus, String str) {
        this.mMessage = str;
        this.mStatus = resultStatus;
    }
}
