package com.bugsnag.android.okhttp;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.JvmField;

/* JADX INFO: compiled from: BugsnagOkHttpPlugin.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/bugsnag/android/okhttp/NetworkRequestMetadata;", "", "startTime", "", "(J)V", "requestBodyCount", "responseBodyCount", NotificationCompat.CATEGORY_STATUS, "", "bugsnag-plugin-android-okhttp_release"}, k = 1, mv = {1, 4, 2})
public final class NetworkRequestMetadata {

    @JvmField
    public volatile long requestBodyCount;

    @JvmField
    public volatile long responseBodyCount;

    @JvmField
    public final long startTime;

    @JvmField
    public volatile int status;

    public NetworkRequestMetadata(long j) {
        this.startTime = j;
    }
}
