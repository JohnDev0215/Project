package com.amazon.music.skyfire.core.template;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public class Handlers {
    private static final Handler foreground = new Handler(Looper.getMainLooper());

    public static Handler getForeground() {
        return foreground;
    }
}
