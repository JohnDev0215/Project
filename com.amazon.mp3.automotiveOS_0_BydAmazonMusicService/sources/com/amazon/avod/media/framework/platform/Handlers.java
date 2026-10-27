package com.amazon.avod.media.framework.platform;

import android.os.Handler;
import android.os.Looper;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class Handlers {

    private static class UIHandlerHolder {
        private static Handler INSTANCE = new Handler(Looper.getMainLooper());

        private UIHandlerHolder() {
        }
    }

    @Nonnull
    public static Handler getUIHandler() {
        return UIHandlerHolder.INSTANCE;
    }
}
