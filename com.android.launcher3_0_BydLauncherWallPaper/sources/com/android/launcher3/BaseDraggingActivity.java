package com.android.launcher3;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.os.UserHandle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Toast;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.model.AppLaunchTracker;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.uioverrides.DisplayRotationListener;
import com.android.launcher3.uioverrides.WallpaperColorInfo;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseDraggingActivity extends BaseActivity implements WallpaperColorInfo.OnChangeListener {
    public static final Object AUTO_CANCEL_ACTION_MODE = new Object();
    private static final String TAG = "BaseDraggingActivity";
    private ActionMode mCurrentActionMode;
    protected boolean mIsSafeModeEnabled;
    private OnStartCallback mOnStartCallback;
    private DisplayRotationListener mRotationListener;
    private int mThemeRes = R.style.AppTheme;

    public interface OnStartCallback<T extends BaseDraggingActivity> {
        void onActivityStart(T activity);
    }

    public abstract ActivityOptions getActivityLaunchOptions(View v);

    public abstract <T extends View> T getOverviewPanel();

    public abstract View getRootView();

    protected boolean onErrorStartingShortcut(Intent intent, ItemInfo info) {
        return false;
    }

    protected abstract void reapplyUi();

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mIsSafeModeEnabled = getPackageManager().isSafeMode();
        this.mRotationListener = new DisplayRotationListener(this, new Runnable() { // from class: com.android.launcher3.-$$Lambda$BaseDraggingActivity$vk3nYIta7xg3ZyjxYxvzqgSBXsg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.onDeviceRotationChanged();
            }
        });
        WallpaperColorInfo.getInstance(this).addOnChangeListener(this);
        int activityThemeRes = Themes.getActivityThemeRes(this);
        if (activityThemeRes != this.mThemeRes) {
            this.mThemeRes = activityThemeRes;
            setTheme(activityThemeRes);
        }
    }

    @Override // com.android.launcher3.uioverrides.WallpaperColorInfo.OnChangeListener
    public void onExtractedColorsChanged(WallpaperColorInfo wallpaperColorInfo) {
        updateTheme();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateTheme();
    }

    private void updateTheme() {
        if (this.mThemeRes != Themes.getActivityThemeRes(this)) {
            recreate();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        this.mCurrentActionMode = mode;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        this.mCurrentActionMode = null;
    }

    @Override // com.android.launcher3.views.ActivityContext
    public boolean finishAutoCancelActionMode() {
        ActionMode actionMode = this.mCurrentActionMode;
        if (actionMode == null || AUTO_CANCEL_ACTION_MODE != actionMode.getTag()) {
            return false;
        }
        this.mCurrentActionMode.finish();
        return true;
    }

    public Rect getViewBounds(View v) {
        int[] iArr = new int[2];
        v.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + v.getWidth(), iArr[1] + v.getHeight());
    }

    public final Bundle getActivityLaunchOptionsAsBundle(View v) {
        ActivityOptions activityLaunchOptions = getActivityLaunchOptions(v);
        if (activityLaunchOptions == null) {
            return null;
        }
        return activityLaunchOptions.toBundle();
    }

    /* JADX INFO: renamed from: startActivitySafely */
    public boolean lambda$startActivitySafely$0$Launcher(View v, Intent intent, ItemInfo item, String sourceContainer) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_START_TAG, "startActivitySafely 1");
        }
        if (this.mIsSafeModeEnabled && !Utilities.isSystemApp(this, intent)) {
            Toast.makeText(this, R.string.safemode_shortcut_error, 0).show();
            return false;
        }
        Bundle activityLaunchOptionsAsBundle = v != null ? getActivityLaunchOptionsAsBundle(v) : null;
        UserHandle userHandle = item != null ? item.user : null;
        intent.addFlags(268435456);
        if (v != null) {
            intent.setSourceBounds(getViewBounds(v));
        }
        try {
            if ((item instanceof WorkspaceItemInfo) && (item.itemType == 1 || item.itemType == 6) && !((WorkspaceItemInfo) item).isPromise()) {
                startShortcutIntentSafely(intent, activityLaunchOptionsAsBundle, item, sourceContainer);
            } else if (userHandle == null || userHandle.equals(Process.myUserHandle())) {
                if (TestProtocol.sDebugTracing) {
                    Log.d(TestProtocol.NO_START_TAG, "startActivitySafely 2");
                }
                startActivity(intent, activityLaunchOptionsAsBundle);
                AppLaunchTracker.INSTANCE.lambda$get$0$MainThreadInitializedObject(this).onStartApp(intent.getComponent(), Process.myUserHandle(), sourceContainer);
            } else {
                LauncherAppsCompat.getInstance(this).startActivityForProfile(intent.getComponent(), userHandle, intent.getSourceBounds(), activityLaunchOptionsAsBundle);
                AppLaunchTracker.INSTANCE.lambda$get$0$MainThreadInitializedObject(this).onStartApp(intent.getComponent(), userHandle, sourceContainer);
            }
            getUserEventDispatcher().logAppLaunch(v, intent);
            getStatsLogManager().logAppLaunch(v, intent);
            return true;
        } catch (ActivityNotFoundException | SecurityException e) {
            Toast.makeText(this, R.string.activity_not_found, 0).show();
            Log.e(TAG, "Unable to launch. tag=" + item + " intent=" + intent, e);
            return false;
        }
    }

    private void startShortcutIntentSafely(Intent intent, Bundle optsBundle, ItemInfo info, String sourceContainer) {
        try {
            StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
            try {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
                if (info.itemType == 6) {
                    String deepShortcutId = ((WorkspaceItemInfo) info).getDeepShortcutId();
                    String str = intent.getPackage();
                    DeepShortcutManager.getInstance(this).startShortcut(str, deepShortcutId, intent.getSourceBounds(), optsBundle, info.user);
                    AppLaunchTracker.INSTANCE.lambda$get$0$MainThreadInitializedObject(this).onStartShortcut(str, deepShortcutId, info.user, sourceContainer);
                } else {
                    startActivity(intent, optsBundle);
                }
                StrictMode.setVmPolicy(vmPolicy);
            } catch (Throwable th) {
                StrictMode.setVmPolicy(vmPolicy);
                throw th;
            }
        } catch (SecurityException e) {
            if (!onErrorStartingShortcut(intent, info)) {
                throw e;
            }
        }
    }

    @Override // com.android.launcher3.BaseActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        OnStartCallback onStartCallback = this.mOnStartCallback;
        if (onStartCallback != null) {
            onStartCallback.onActivityStart(this);
            this.mOnStartCallback = null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        WallpaperColorInfo.getInstance(this).removeOnChangeListener(this);
        this.mRotationListener.disable();
    }

    public <T extends BaseDraggingActivity> void setOnStartCallback(OnStartCallback<T> callback) {
        this.mOnStartCallback = callback;
    }

    protected void onDeviceProfileInitiated() {
        if (this.mDeviceProfile.isVerticalBarLayout()) {
            this.mRotationListener.enable();
            this.mDeviceProfile.updateIsSeascape(getWindowManager());
        } else {
            this.mRotationListener.disable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceRotationChanged() {
        if (this.mDeviceProfile.updateIsSeascape(getWindowManager())) {
            reapplyUi();
        }
    }
}
