package com.android.systemui.recents;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.MutableBoolean;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.recents.model.RecentsTaskLoadPlan;
import com.android.systemui.recents.model.RecentsTaskLoader;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.phone.StatusBar;

/* JADX INFO: loaded from: classes.dex */
public class RecentsImpl {
    private static final int MIN_TOGGLE_DELAY_MS = 350;
    public static final String RECENTS_ACTIVITY = "com.android.systemui.recents.RecentsActivity";
    public static final String RECENTS_PACKAGE = "com.android.systemui";
    private static final String TAG = "RecentsImpl";
    protected static RecentsTaskLoadPlan sInstanceLoadPlan;
    protected Context mContext;
    protected Handler mHandler = new Handler();
    protected long mLastToggleTime;

    public void hideRecents(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) {
    }

    public void showRecents(boolean triggeredFromAltTab, boolean draggingInRecents, boolean animate, int growTarget) {
    }

    public RecentsImpl(Context context) {
        this.mContext = context;
    }

    private StatusBar getStatusBar() {
        return (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
    }

    public static RecentsTaskLoadPlan consumeInstanceLoadPlan() {
        RecentsTaskLoadPlan recentsTaskLoadPlan = sInstanceLoadPlan;
        sInstanceLoadPlan = null;
        return recentsTaskLoadPlan;
    }

    private void startRecentsActivity(ActivityManager.RunningTaskInfo runningTask, boolean isHomeStackVisible, boolean animate, int growTarget) {
        Intent intent = new Intent();
        intent.setClassName("com.android.systemui", RECENTS_ACTIVITY);
        intent.setFlags(276840448);
        this.mContext.startActivity(intent);
    }

    public void toggleRecents(int growTarget) {
        if (ActivityManagerWrapper.getInstance().isScreenPinningActive()) {
            return;
        }
        try {
            MutableBoolean mutableBoolean = new MutableBoolean(true);
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.mLastToggleTime;
            if (!LegacyRecentsImpl.getSystemServices().isRecentsActivityVisible(mutableBoolean)) {
                if (jElapsedRealtime < 350) {
                    return;
                }
                startRecentsActivityAndDismissKeyguardIfNeeded(ActivityManagerWrapper.getInstance().getRunningTask(), mutableBoolean.value, true, growTarget);
                ActivityManagerWrapper.getInstance().closeSystemWindows(StatusBar.SYSTEM_DIALOG_REASON_RECENT_APPS);
                this.mLastToggleTime = SystemClock.elapsedRealtime();
                return;
            }
            if (!LegacyRecentsImpl.getConfiguration().getLaunchState().launchedWithAltTab) {
                boolean z = LegacyRecentsImpl.getConfiguration().isGridEnabled;
            } else {
                if (jElapsedRealtime < 350) {
                    return;
                }
                this.mLastToggleTime = SystemClock.elapsedRealtime();
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Failed to launch RecentsActivity", e);
        }
    }

    public void preloadRecents() {
        if (ActivityManagerWrapper.getInstance().isScreenPinningActive()) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.recents.-$$Lambda$RecentsImpl$JYIWyb7qbGbCUYK2xqw49ultvGg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$preloadRecents$0$RecentsImpl();
            }
        });
    }

    public /* synthetic */ void lambda$preloadRecents$0$RecentsImpl() {
        ActivityManager.RunningTaskInfo runningTask;
        if (LegacyRecentsImpl.getSystemServices().isRecentsActivityVisible(null) || (runningTask = ActivityManagerWrapper.getInstance().getRunningTask()) == null) {
            return;
        }
        RecentsTaskLoader taskLoader = LegacyRecentsImpl.getTaskLoader();
        RecentsTaskLoadPlan recentsTaskLoadPlan = new RecentsTaskLoadPlan(this.mContext);
        sInstanceLoadPlan = recentsTaskLoadPlan;
        taskLoader.preloadTasks(recentsTaskLoadPlan, runningTask.id);
    }

    protected void startRecentsActivityAndDismissKeyguardIfNeeded(final ActivityManager.RunningTaskInfo runningTask, final boolean isHomeStackVisible, final boolean animate, final int growTarget) {
        startRecentsActivity(runningTask, isHomeStackVisible, animate, growTarget);
    }
}
