package com.amazon.mp3.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Debug;
import android.os.Process;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.ExternalProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.error.CirrusErrorLogger;

/* JADX INFO: loaded from: classes4.dex */
public final class ExitCheck {
    public static synchronized void enterActivity(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("com.amazon.mp3_ExitCheck", 0);
        boolean z = sharedPreferences.getBoolean("IsActivityRunning", false);
        int i = sharedPreferences.getInt("ActivityPid", Integer.MIN_VALUE);
        final String string = sharedPreferences.getString("ActivityName", "");
        int iMyPid = Process.myPid();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putBoolean("IsActivityRunning", true);
        editorEdit.putInt("ActivityPid", iMyPid);
        editorEdit.putString("ActivityName", activity.getLocalClassName());
        editorEdit.apply();
        if (z && i != iMyPid && !Debug.isDebuggerConnected() && !Debug.waitingForDebugger()) {
            NowPlayingManager.getInstance().clear();
            new Thread() { // from class: com.amazon.mp3.util.ExitCheck.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    ThreadUtil.randomSleep(10000L, 12000L);
                    CirrusErrorLogger.getInstance().log(CirrusError.UNEXPECTED_SHUTDOWN, "Unexpected shutdown in " + string);
                }
            }.start();
            if (AmazonApplication.getCapabilities().isExternalProviderEnabled()) {
                ExternalProvider.fireBulkUpdate(activity);
            }
        }
    }

    public static synchronized void exitActivity(Context context) {
        clearActivityCheck(context, false);
    }

    public static synchronized void clearActivityCheck(Context context, boolean synchronousWrite) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.amazon.mp3_ExitCheck", 0).edit();
        editorEdit.remove("IsActivityRunning");
        editorEdit.remove("ActivityPid");
        editorEdit.remove("ActivityName");
        if (synchronousWrite) {
            editorEdit.commit();
        } else {
            editorEdit.apply();
        }
    }
}
