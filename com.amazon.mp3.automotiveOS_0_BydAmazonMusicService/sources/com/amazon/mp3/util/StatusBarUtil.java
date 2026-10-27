package com.amazon.mp3.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Window;
import androidx.core.content.ContextCompat;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes4.dex */
public class StatusBarUtil {
    private static int sDefaultColor = -1;

    @TargetApi(21)
    public static void setStatusBarColor(Activity activity, int colorId) {
        Window window = activity.getWindow();
        if (sDefaultColor == -1) {
            sDefaultColor = window.getStatusBarColor();
        }
        window.clearFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(ContextCompat.getColor(activity, colorId));
    }

    @TargetApi(21)
    public static void resetStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        window.clearFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(sDefaultColor);
    }
}
