package com.bugsnag.android.okhttp;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX INFO: compiled from: BugsnagOkHttpPlugin.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/bugsnag/android/okhttp/RequestResult;", "", "message", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "SUCCESS", "FAILURE", MediaError.ERROR_TYPE_ERROR, "bugsnag-plugin-android-okhttp_release"}, k = 1, mv = {1, 4, 2})
public enum RequestResult {
    SUCCESS("OkHttp call succeeded"),
    FAILURE("OkHttp call failed"),
    ERROR("OkHttp call error");

    private final String message;

    RequestResult(String str) {
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }
}
