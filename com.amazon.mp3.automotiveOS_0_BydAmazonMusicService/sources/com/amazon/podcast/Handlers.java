package com.amazon.podcast;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.facebook.react.modules.appstate.AppStateModule;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes5.dex */
public enum Handlers {
    INSTANCE;

    private final Handler background;
    private final ExecutorService executorService;
    private final Handler foreground = new Handler(Looper.getMainLooper());

    Handlers() {
        HandlerThread handlerThread = new HandlerThread(AppStateModule.APP_STATE_BACKGROUND);
        handlerThread.start();
        this.background = new Handler(handlerThread.getLooper());
        this.executorService = Executors.newCachedThreadPool();
    }

    public Handler getForeground() {
        return this.foreground;
    }

    @Deprecated
    public Handler getBackground() {
        return this.background;
    }

    public void runInBackground(final Runnable runnable) {
        this.executorService.submit(runnable);
    }
}
