package com.android.launcher3.uioverrides;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.CancellationSignal;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.util.TouchController;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes4.dex */
public class UiFactory {
    public static void clearSwipeSharedState(boolean finishAnimation) {
    }

    public static boolean dumpActivity(Activity activity, PrintWriter writer) {
        return false;
    }

    public static Runnable enableLiveUIChanges(Launcher l) {
        return null;
    }

    public static void onCreate(Launcher launcher) {
    }

    public static void onEnterAnimationComplete(Context context) {
    }

    public static void onLauncherStateOrFocusChanged(Launcher launcher) {
    }

    public static void onLauncherStateOrResumeChanged(Launcher launcher) {
    }

    public static void onStart(Launcher launcher) {
    }

    public static void onTrimMemory(Launcher launcher, int level) {
    }

    public static void resetOverview(Launcher launcher) {
    }

    public static void resetPendingActivityResults(Launcher launcher, int requestCode) {
    }

    public static void setBackButtonAlpha(Launcher launcher, float alpha, boolean animate) {
    }

    public static boolean startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle options) {
        return false;
    }

    public static boolean startIntentSenderForResult(Activity activity, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) {
        return false;
    }

    public static void useFadeOutAnimationForLauncherStart(Launcher launcher, CancellationSignal cancellationSignal) {
    }

    public static TouchController[] createTouchControllers(Launcher launcher) {
        return new TouchController[]{launcher.getDragController(), new AllAppsSwipeController(launcher)};
    }

    public static LauncherStateManager.StateHandler[] getStateHandler(Launcher launcher) {
        return new LauncherStateManager.StateHandler[]{launcher.getAllAppsController(), launcher.getWorkspace()};
    }

    public static LauncherState.ScaleAndTranslation getOverviewScaleAndTranslationForNormalState(Launcher l) {
        return new LauncherState.ScaleAndTranslation(1.1f, 0.0f, 0.0f);
    }

    public static RotationMode getRotationMode(DeviceProfile dp) {
        return RotationMode.NORMAL;
    }
}
