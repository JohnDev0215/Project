package android.app;

import android.util.EventLog;

/* JADX INFO: loaded from: classes.dex */
public class EventLogTags {
    public static final int WM_ADD_TO_STOPPING = 30066;
    public static final int WM_ON_ACTIVITY_RESULT_CALLED = 30062;
    public static final int WM_ON_CREATE_CALLED = 30057;
    public static final int WM_ON_DESTROY_CALLED = 30060;
    public static final int WM_ON_PAUSED_CALLED = 30021;
    public static final int WM_ON_RESTART_CALLED = 30058;
    public static final int WM_ON_RESUME_CALLED = 30022;
    public static final int WM_ON_START_CALLED = 30059;
    public static final int WM_ON_STOP_CALLED = 30049;
    public static final int WM_ON_TOP_RESUMED_GAINED_CALLED = 30064;
    public static final int WM_ON_TOP_RESUMED_LOST_CALLED = 30065;
    public static final int WM_STOP_ACTIVITY = 30048;

    private EventLogTags() {
    }

    public static void writeWmOnPausedCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_PAUSED_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnResumeCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_RESUME_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmStopActivity(int i, int i2, String str) {
        EventLog.writeEvent(WM_STOP_ACTIVITY, Integer.valueOf(i), Integer.valueOf(i2), str);
    }

    public static void writeWmOnStopCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_STOP_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnCreateCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_CREATE_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnRestartCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_RESTART_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnStartCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_START_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnDestroyCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_DESTROY_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnActivityResultCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_ACTIVITY_RESULT_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnTopResumedGainedCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_TOP_RESUMED_GAINED_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmOnTopResumedLostCalled(int i, String str, String str2) {
        EventLog.writeEvent(WM_ON_TOP_RESUMED_LOST_CALLED, Integer.valueOf(i), str, str2);
    }

    public static void writeWmAddToStopping(int i, int i2, String str, String str2) {
        EventLog.writeEvent(WM_ADD_TO_STOPPING, Integer.valueOf(i), Integer.valueOf(i2), str, str2);
    }
}
