package com.bugsnag.android;

import android.util.Log;
import androidx.core.app.NotificationCompat;

/* JADX INFO: compiled from: DebugLogger.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/bugsnag/android/DebugLogger;", "Lcom/bugsnag/android/Logger;", "", NotificationCompat.CATEGORY_MESSAGE, "", "e", "", "throwable", "w", "i", "d", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class DebugLogger implements Logger {
    public static final DebugLogger INSTANCE = new DebugLogger();

    private DebugLogger() {
    }

    @Override // com.bugsnag.android.Logger
    public void e(String msg) {
        Log.e("Bugsnag", msg);
    }

    @Override // com.bugsnag.android.Logger
    public void e(String msg, Throwable throwable) {
        Log.e("Bugsnag", msg, throwable);
    }

    @Override // com.bugsnag.android.Logger
    public void w(String msg) {
        Log.w("Bugsnag", msg);
    }

    @Override // com.bugsnag.android.Logger
    public void w(String msg, Throwable throwable) {
        Log.w("Bugsnag", msg, throwable);
    }

    @Override // com.bugsnag.android.Logger
    public void i(String msg) {
        Log.i("Bugsnag", msg);
    }

    @Override // com.bugsnag.android.Logger
    public void d(String msg) {
        Log.d("Bugsnag", msg);
    }

    @Override // com.bugsnag.android.Logger
    public void d(String msg, Throwable throwable) {
        Log.d("Bugsnag", msg, throwable);
    }
}
