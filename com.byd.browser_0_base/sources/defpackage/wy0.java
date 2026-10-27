package defpackage;

import android.app.KeyguardManager;

/* JADX INFO: compiled from: IncognitoReauthSettingUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class wy0 {
    public static boolean a() {
        return ((KeyguardManager) xz.a.getSystemService("keyguard")).isDeviceSecure();
    }
}
