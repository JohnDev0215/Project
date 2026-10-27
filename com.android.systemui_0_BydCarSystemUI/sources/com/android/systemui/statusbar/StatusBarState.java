package com.android.systemui.statusbar;

/* JADX INFO: loaded from: classes.dex */
public class StatusBarState {
    public static final int FULLSCREEN_USER_SWITCHER = 1;
    public static final int SHADE = 0;

    public static String toShortString(int x) {
        return x != 0 ? x != 1 ? "bad_value_" + x : "FS_USRSW" : "SHD";
    }
}
