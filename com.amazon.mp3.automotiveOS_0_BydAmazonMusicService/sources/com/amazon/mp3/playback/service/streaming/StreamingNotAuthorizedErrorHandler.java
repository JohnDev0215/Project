package com.amazon.mp3.playback.service.streaming;

import android.app.NotificationManager;
import android.content.Context;
import com.amazon.mp3.util.UniqueCodeUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class StreamingNotAuthorizedErrorHandler {
    private static final int NOTIFICATION_ID = UniqueCodeUtil.nextUniqueCode();
    private static AtomicBoolean sTriggered = new AtomicBoolean(false);

    public static void hideNotification(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(NOTIFICATION_ID);
    }

    public static boolean isTriggered() {
        return sTriggered.get();
    }

    public static void reset(Context context) {
        sTriggered.set(false);
        hideNotification(context);
    }
}
