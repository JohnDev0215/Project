package androidx.core.os;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class BuildCompat {
    @Deprecated
    public static boolean isAtLeastN() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastNMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastO() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastOMR1() {
        return true;
    }

    @Deprecated
    public static boolean isAtLeastP() {
        return true;
    }

    private BuildCompat() {
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean isAtLeastR() {
        return Build.VERSION.CODENAME.length() == 1 && Build.VERSION.CODENAME.charAt(0) >= 'R' && Build.VERSION.CODENAME.charAt(0) <= 'Z';
    }
}
