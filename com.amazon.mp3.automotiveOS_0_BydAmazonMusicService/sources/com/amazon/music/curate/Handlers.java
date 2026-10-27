package com.amazon.music.curate;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.facebook.react.modules.appstate.AppStateModule;

/* JADX INFO: loaded from: classes4.dex */
public enum Handlers {
    INSTANCE;

    private final Handler background;
    private final Handler foreground = new Handler(Looper.getMainLooper());

    Handlers() {
        HandlerThread handlerThread = new HandlerThread(AppStateModule.APP_STATE_BACKGROUND);
        handlerThread.start();
        this.background = new Handler(handlerThread.getLooper());
    }

    public Handler getForeground() {
        return this.foreground;
    }

    public Handler getBackground() {
        return this.background;
    }
}
