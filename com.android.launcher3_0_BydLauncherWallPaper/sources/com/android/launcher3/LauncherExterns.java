package com.android.launcher3;

import android.content.SharedPreferences;
import com.android.launcher3.Launcher;

/* JADX INFO: loaded from: classes4.dex */
public interface LauncherExterns {
    SharedPreferences getSharedPrefs();

    boolean setLauncherCallbacks(LauncherCallbacks callbacks);

    void setLauncherOverlay(Launcher.LauncherOverlay overlay);
}
