package com.amazon.alexa.voice.core.schedulers;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class HandlerScheduler implements Scheduler {
    private final Handler handler;

    public HandlerScheduler(Looper looper) {
        Preconditions.notNull(looper, "looper == null");
        this.handler = new Handler(looper);
    }

    @Override // com.amazon.alexa.voice.core.Scheduler
    public void schedule(Runnable runnable) {
        this.handler.post(runnable);
    }
}
