package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public class ManualExecutor implements Executor {
    private final List<Runnable> runnables = new ArrayList();

    @Override // java.util.concurrent.Executor
    public void execute(@NonNull Runnable runnable) {
        this.runnables.add(runnable);
    }

    public int getNumPending() {
        return this.runnables.size();
    }
}
