package com.android.systemui.recents;

import android.content.ContentResolver;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.provider.Settings;
import com.android.systemui.R;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.util.SystemUIException;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class Recents extends SystemUI implements CommandQueue.Callbacks {
    private RecentsImplementation mImpl;

    @Override // com.android.systemui.SystemUI
    public void start() {
        ((CommandQueue) getComponent(CommandQueue.class)).addCallback(this);
        putComponent(Recents.class, this);
        RecentsImplementation recentsImplementationCreateRecentsImplementationFromConfig = createRecentsImplementationFromConfig();
        this.mImpl = recentsImplementationCreateRecentsImplementationFromConfig;
        recentsImplementationCreateRecentsImplementationFromConfig.onStart(this.mContext, this);
    }

    @Override // com.android.systemui.SystemUI
    public void onBootCompleted() {
        this.mImpl.onBootCompleted();
    }

    @Override // com.android.systemui.SystemUI
    public void onConfigurationChanged(Configuration newConfig) {
        this.mImpl.onConfigurationChanged(newConfig);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void appTransitionFinished(int displayId) {
        if (this.mContext.getDisplayId() == displayId) {
            this.mImpl.onAppTransitionFinished();
        }
    }

    public void growRecents() {
        this.mImpl.growRecents();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void showRecentApps(boolean triggeredFromAltTab) {
        if (isUserSetup()) {
            this.mImpl.showRecentApps(triggeredFromAltTab);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) {
        if (isUserSetup()) {
            this.mImpl.hideRecentApps(triggeredFromAltTab, triggeredFromHomeKey);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void toggleRecentApps() {
        if (isUserSetup()) {
            this.mImpl.toggleRecentApps();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void preloadRecentApps() {
        if (isUserSetup()) {
            this.mImpl.preloadRecentApps();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public void cancelPreloadRecentApps() {
        if (isUserSetup()) {
            this.mImpl.cancelPreloadRecentApps();
        }
    }

    public boolean splitPrimaryTask(int stackCreateMode, Rect initialBounds, int metricsDockAction) {
        if (isUserSetup()) {
            return this.mImpl.splitPrimaryTask(stackCreateMode, initialBounds, metricsDockAction);
        }
        return false;
    }

    private boolean isUserSetup() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        return (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, "user_setup_complete", 0) == 0) ? false : true;
    }

    private RecentsImplementation createRecentsImplementationFromConfig() {
        String string = this.mContext.getString(R.string.config_recentsComponent);
        if (string == null || string.length() == 0) {
            throw new SystemUIException("No recents component configured", null);
        }
        try {
            try {
                return (RecentsImplementation) this.mContext.getClassLoader().loadClass(string).newInstance();
            } catch (Throwable th) {
                throw new SystemUIException("Error creating recents component: " + string, th);
            }
        } catch (Throwable th2) {
            throw new SystemUIException("Error loading recents component: " + string, th2);
        }
    }

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        this.mImpl.dump(pw);
    }
}
