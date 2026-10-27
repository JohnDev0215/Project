package com.android.bluetooth.avrcp;

import android.content.Context;
import android.media.session.MediaSession;

/* JADX INFO: loaded from: classes.dex */
public final class MediaControllerFactory {
    private static MediaController sInjectedController;

    static MediaController wrap(android.media.session.MediaController delegate) {
        MediaController mediaController = sInjectedController;
        if (mediaController != null) {
            return mediaController;
        }
        if (delegate != null) {
            return new MediaController(delegate);
        }
        return null;
    }

    static MediaController make(Context context, MediaSession.Token token) {
        MediaController mediaController = sInjectedController;
        return mediaController != null ? mediaController : new MediaController(context, token);
    }

    static void inject(MediaController controller) {
        sInjectedController = controller;
    }
}
