package com.amazon.alexa.voice.core.schedulers;

import com.amazon.alexa.voice.core.Scheduler;
import com.amazon.alexa.voice.core.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class ImmediateScheduler implements Scheduler {
    @Override // com.amazon.alexa.voice.core.Scheduler
    public void schedule(Runnable runnable) {
        Preconditions.notNull(runnable, "runnable == null");
        runnable.run();
    }
}
