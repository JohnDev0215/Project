package androidx.core.os;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class BuildCompat {

    public @interface PrereleaseSdkCheck {
    }

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

    protected static boolean isAtLeastPreReleaseCodename(String codename, String buildCodename) {
        return !"REL".equals(buildCodename) && buildCodename.compareTo(codename) >= 0;
    }

    @Deprecated
    public static boolean isAtLeastQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    @Deprecated
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public static boolean isAtLeastS() {
        return Build.VERSION.SDK_INT >= 31 || isAtLeastPreReleaseCodename("S", Build.VERSION.CODENAME);
    }

    public static boolean isAtLeastT() {
        return isAtLeastPreReleaseCodename("T", Build.VERSION.CODENAME);
    }
}
