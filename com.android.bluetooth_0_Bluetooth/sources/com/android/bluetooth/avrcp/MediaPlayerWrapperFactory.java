package com.android.bluetooth.avrcp;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerWrapperFactory {
    private static MediaPlayerWrapper sInjectedWrapper;

    static MediaPlayerWrapper wrap(MediaController controller, Looper looper) {
        MediaPlayerWrapper mediaPlayerWrapper = sInjectedWrapper;
        if (mediaPlayerWrapper != null) {
            return mediaPlayerWrapper;
        }
        if (controller == null || looper == null) {
            return null;
        }
        if (controller.getPackageName().equals("com.google.android.music")) {
            MediaPlayerWrapper newWrapper = new GPMWrapper(controller, looper);
            return newWrapper;
        }
        MediaPlayerWrapper newWrapper2 = new MediaPlayerWrapper(controller, looper);
        return newWrapper2;
    }

    static void inject(MediaPlayerWrapper wrapper) {
        sInjectedWrapper = wrapper;
    }
}
