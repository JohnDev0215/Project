package defpackage;

import android.content.Intent;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.prefs.PrefService;
import org.chromium.ui.permissions.AndroidPermissionDelegate;

/* JADX INFO: compiled from: VoiceRecognitionUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class to3 {
    public static Boolean a;
    public static final yj1 b = new yj1("VoiceSearchAudioCapturePolicy", false);

    public static boolean a(boolean z) {
        Object obj = ThreadUtils.a;
        if (a == null || !z) {
            a = Boolean.valueOf(wt1.a(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0));
        }
        return a.booleanValue();
    }

    public static boolean b(AndroidPermissionDelegate androidPermissionDelegate) {
        if (androidPermissionDelegate == null) {
            return false;
        }
        if ((androidPermissionDelegate.hasPermission("android.permission.RECORD_AUDIO") || androidPermissionDelegate.canRequestPermission("android.permission.RECORD_AUDIO")) && c(false)) {
            return a(true);
        }
        return false;
    }

    public static boolean c(boolean z) {
        if (!b.a()) {
            return true;
        }
        PrefService prefServiceK = !ProfileManager.b ? null : oj2.k(Profile.d());
        if (z && prefServiceK == null) {
            return false;
        }
        return prefServiceK == null || prefServiceK.a("hardware.audio_capture_enabled");
    }
}
