package com.bugsnag.android.okhttp;

import kotlin.Metadata;

/* JADX INFO: compiled from: BugsnagOkHttpPlugin.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"getRequestResult", "Lcom/bugsnag/android/okhttp/RequestResult;", "requestInfo", "Lcom/bugsnag/android/okhttp/NetworkRequestMetadata;", "bugsnag-plugin-android-okhttp_release"}, k = 2, mv = {1, 4, 2})
public final class BugsnagOkHttpPluginKt {
    public static final RequestResult getRequestResult(NetworkRequestMetadata networkRequestMetadata) {
        int i = networkRequestMetadata.status;
        if (100 <= i && 399 >= i) {
            return RequestResult.SUCCESS;
        }
        if (400 <= i && 599 >= i) {
            return RequestResult.FAILURE;
        }
        return RequestResult.ERROR;
    }
}
