package com.android.systemui.recents;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import com.android.systemui.SysUiServiceProvider;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
interface RecentsImplementation {
    default void cancelPreloadRecentApps() {
    }

    default void dump(PrintWriter pw) {
    }

    default void growRecents() {
    }

    default void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) {
    }

    default void onAppTransitionFinished() {
    }

    default void onBootCompleted() {
    }

    default void onConfigurationChanged(Configuration newConfig) {
    }

    default void onStart(Context context, SysUiServiceProvider sysUiServiceProvider) {
    }

    default void preloadRecentApps() {
    }

    default void showRecentApps(boolean triggeredFromAltTab) {
    }

    default boolean splitPrimaryTask(int stackCreateMode, Rect initialBounds, int metricsDockAction) {
        return false;
    }

    default void toggleRecentApps() {
    }
}
