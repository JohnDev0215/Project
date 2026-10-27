package com.android.systemui;

import android.content.res.Configuration;
import android.util.Log;
import com.android.systemui.util.SystemUIException;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class SystemBars extends SystemUI {
    private static final boolean DEBUG = false;
    private static final String TAG = "SystemBars";
    private static final int WAIT_FOR_BARS_TO_DIE = 500;
    private SystemUI mStatusBar;

    @Override // com.android.systemui.SystemUI
    public void start() {
        createStatusBarFromConfig();
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
        SystemUI systemUI = this.mStatusBar;
        if (systemUI != null) {
            systemUI.onConfigurationChanged(newConfig);
        }
    }

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        SystemUI systemUI = this.mStatusBar;
        if (systemUI != null) {
            systemUI.dump(fd, pw, args);
        }
    }

    private void createStatusBarFromConfig() {
        String string = this.mContext.getString(R.string.config_statusBarComponent);
        if (string == null || string.length() == 0) {
            throw andLog("No status bar component configured", null);
        }
        try {
            try {
                SystemUI systemUI = (SystemUI) this.mContext.getClassLoader().loadClass(string).newInstance();
                this.mStatusBar = systemUI;
                systemUI.mContext = this.mContext;
                this.mStatusBar.mComponents = this.mComponents;
                this.mStatusBar.start();
            } catch (Throwable th) {
                throw andLog("Error creating status bar component: " + string, th);
            }
        } catch (Throwable th2) {
            throw andLog("Error loading status bar component: " + string, th2);
        }
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
        this.mStatusBar.onBootCompleted();
    }

    private SystemUIException andLog(String msg, Throwable t) {
        Log.w(TAG, msg, t);
        throw new SystemUIException(msg, t);
    }
}
