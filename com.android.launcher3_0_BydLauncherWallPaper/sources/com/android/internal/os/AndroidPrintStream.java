package com.android.internal.os;

import android.os.DropBoxManager;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
class AndroidPrintStream extends LoggingPrintStream {
    private final int priority;
    private final String tag;

    public AndroidPrintStream(int i, String str) {
        Objects.requireNonNull(str, DropBoxManager.EXTRA_TAG);
        this.priority = i;
        this.tag = str;
    }

    @Override // com.android.internal.os.LoggingPrintStream
    protected void log(String str) {
        Log.println(this.priority, this.tag, str);
    }
}
