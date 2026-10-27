package com.android.launcher3;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.logging.StatsLogManager;
import com.android.launcher3.logging.StatsLogUtils;
import com.android.launcher3.logging.UserEventDispatcher;
import com.android.launcher3.uioverrides.UiFactory;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.SystemUiController;
import com.android.launcher3.util.ViewCache;
import com.android.launcher3.views.ActivityContext;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseActivity extends Activity implements UserEventDispatcher.UserEventDelegate, StatsLogUtils.LogStateProvider, ActivityContext {
    private static final int ACTIVITY_STATE_RESUMED = 2;
    private static final int ACTIVITY_STATE_STARTED = 1;
    private static final int ACTIVITY_STATE_USER_ACTIVE = 4;
    public static final int INVISIBLE_ALL = 15;
    public static final int INVISIBLE_BY_APP_TRANSITIONS = 2;
    public static final int INVISIBLE_BY_PENDING_FLAGS = 4;
    public static final int INVISIBLE_BY_STATE_HANDLER = 1;
    private static final int INVISIBLE_FLAGS = 7;
    public static final int PENDING_INVISIBLE_BY_WALLPAPER_ANIMATION = 8;
    public static final int STATE_HANDLER_INVISIBILITY_FLAGS = 9;
    private int mActivityFlags;
    protected DeviceProfile mDeviceProfile;
    private int mForceInvisible;
    protected StatsLogManager mStatsLogManager;
    protected SystemUiController mSystemUiController;
    protected UserEventDispatcher mUserEventDispatcher;
    private final ArrayList<DeviceProfile.OnDeviceProfileChangeListener> mDPChangeListeners = new ArrayList<>();
    private final ArrayList<MultiWindowModeChangedListener> mMultiWindowModeChangedListeners = new ArrayList<>();
    private final ViewCache mViewCache = new ViewCache();

    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InvisibilityFlags {
    }

    public interface MultiWindowModeChangedListener {
        void onMultiWindowModeChanged(boolean isInMultiWindowMode);
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogStateProvider
    public int getCurrentState() {
        return 0;
    }

    @Override // com.android.launcher3.logging.UserEventDispatcher.UserEventDelegate
    public void modifyUserEvent(LauncherLogProto.LauncherEvent event) {
    }

    public ViewCache getViewCache() {
        return this.mViewCache;
    }

    @Override // com.android.launcher3.views.ActivityContext
    public DeviceProfile getDeviceProfile() {
        return this.mDeviceProfile;
    }

    public final StatsLogManager getStatsLogManager() {
        if (this.mStatsLogManager == null) {
            this.mStatsLogManager = StatsLogManager.newInstance(this, this);
        }
        return this.mStatsLogManager;
    }

    public final UserEventDispatcher getUserEventDispatcher() {
        if (this.mUserEventDispatcher == null) {
            this.mUserEventDispatcher = UserEventDispatcher.newInstance(this, this);
        }
        return this.mUserEventDispatcher;
    }

    public SystemUiController getSystemUiController() {
        if (this.mSystemUiController == null) {
            this.mSystemUiController = new SystemUiController(getWindow());
        }
        return this.mSystemUiController;
    }

    @Override // android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // android.app.Activity
    protected void onStart() {
        this.mActivityFlags |= 1;
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        this.mActivityFlags |= 6;
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        this.mActivityFlags &= -5;
        super.onUserLeaveHint();
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        for (int size = this.mMultiWindowModeChangedListeners.size() - 1; size >= 0; size--) {
            this.mMultiWindowModeChangedListeners.get(size).onMultiWindowModeChanged(isInMultiWindowMode);
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        this.mActivityFlags &= -6;
        this.mForceInvisible = 0;
        super.onStop();
        getSystemUiController().updateUiState(4, 0);
    }

    @Override // android.app.Activity
    protected void onPause() {
        this.mActivityFlags &= -3;
        super.onPause();
        getSystemUiController().updateUiState(4, 0);
    }

    public boolean isStarted() {
        return (this.mActivityFlags & 1) != 0;
    }

    public boolean hasBeenResumed() {
        return (this.mActivityFlags & 2) != 0;
    }

    public boolean isUserActive() {
        return (this.mActivityFlags & 4) != 0;
    }

    public void addOnDeviceProfileChangeListener(DeviceProfile.OnDeviceProfileChangeListener listener) {
        this.mDPChangeListeners.add(listener);
    }

    public void removeOnDeviceProfileChangeListener(DeviceProfile.OnDeviceProfileChangeListener listener) {
        this.mDPChangeListeners.remove(listener);
    }

    protected void dispatchDeviceProfileChanged() {
        for (int size = this.mDPChangeListeners.size() - 1; size >= 0; size--) {
            this.mDPChangeListeners.get(size).onDeviceProfileChanged(this.mDeviceProfile);
        }
    }

    public void addMultiWindowModeChangedListener(MultiWindowModeChangedListener listener) {
        this.mMultiWindowModeChangedListeners.add(listener);
    }

    public void removeMultiWindowModeChangedListener(MultiWindowModeChangedListener listener) {
        this.mMultiWindowModeChangedListeners.remove(listener);
    }

    public void addForceInvisibleFlag(int flag) {
        this.mForceInvisible = flag | this.mForceInvisible;
    }

    public void clearForceInvisibleFlag(int flag) {
        this.mForceInvisible = (~flag) & this.mForceInvisible;
    }

    public boolean isForceInvisible() {
        return hasSomeInvisibleFlag(7);
    }

    public boolean hasSomeInvisibleFlag(int mask) {
        return (this.mForceInvisible & mask) != 0;
    }

    @Override // android.app.Activity
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        if (UiFactory.dumpActivity(this, writer)) {
            return;
        }
        super.dump(prefix, fd, writer, args);
    }

    protected void dumpMisc(PrintWriter writer) {
        writer.println(" deviceProfile isTransposed=" + getDeviceProfile().isVerticalBarLayout());
        writer.println(" orientation=" + getResources().getConfiguration().orientation);
        writer.println(" mSystemUiController: " + this.mSystemUiController);
        writer.println(" mActivityFlags: " + this.mActivityFlags);
        writer.println(" mForceInvisible: " + this.mForceInvisible);
    }

    public static <T extends BaseActivity> T fromContext(Context context) {
        if (context instanceof BaseActivity) {
            return (T) context;
        }
        if (context instanceof ContextThemeWrapper) {
            return (T) fromContext(((ContextWrapper) context).getBaseContext());
        }
        throw new IllegalArgumentException("Cannot find BaseActivity in parent tree");
    }
}
