package com.android.internal.telephony.util;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class HandlerExecutor implements Executor {
    private final Handler mHandler;

    public HandlerExecutor(Handler handler) {
        if (handler == null) {
            throw null;
        }
        this.mHandler = handler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.mHandler.post(runnable)) {
            return;
        }
        throw new RejectedExecutionException(this.mHandler + " is shutting down");
    }
}
