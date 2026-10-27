package com.android.launcher3.states;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.config.FeatureFlags;

/* JADX INFO: loaded from: classes4.dex */
public class RotationHelper implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String ALLOW_ROTATION_PREFERENCE_KEY = "pref_allowRotation";
    public static final int REQUEST_LOCK = 2;
    public static final int REQUEST_NONE = 0;
    public static final int REQUEST_ROTATE = 1;
    private boolean mAutoRotateEnabled;
    private boolean mDestroyed;
    private boolean mIgnoreAutoRotateSettings;
    private boolean mInitialized;
    private final Launcher mLauncher;
    private final SharedPreferences mPrefs;
    private boolean mRotationHasDifferentUI;
    private int mStateHandlerRequest = 0;
    private int mCurrentTransitionRequest = 0;
    private int mCurrentStateRequest = 0;
    private int mLastActivityFlags = -1;

    public static boolean getAllowRotationDefaultValue() {
        Resources system = Resources.getSystem();
        return (system.getConfiguration().smallestScreenWidthDp * system.getDisplayMetrics().densityDpi) / DisplayMetrics.DENSITY_DEVICE_STABLE >= 600;
    }

    public RotationHelper(Launcher launcher) {
        this.mLauncher = launcher;
        boolean z = launcher.getResources().getBoolean(R.bool.allow_rotation);
        this.mIgnoreAutoRotateSettings = z;
        if (!z) {
            SharedPreferences prefs = Utilities.getPrefs(launcher);
            this.mPrefs = prefs;
            prefs.registerOnSharedPreferenceChangeListener(this);
            this.mAutoRotateEnabled = prefs.getBoolean(ALLOW_ROTATION_PREFERENCE_KEY, getAllowRotationDefaultValue());
            return;
        }
        this.mPrefs = null;
    }

    public void setRotationHadDifferentUI(boolean rotationHasDifferentUI) {
        this.mRotationHasDifferentUI = rotationHasDifferentUI;
    }

    public boolean homeScreenCanRotate() {
        return this.mRotationHasDifferentUI || this.mIgnoreAutoRotateSettings || this.mAutoRotateEnabled || this.mStateHandlerRequest != 0 || this.mLauncher.getDeviceProfile().isMultiWindowMode;
    }

    public void updateRotationAnimation() {
        if (FeatureFlags.FAKE_LANDSCAPE_UI.get()) {
            WindowManager.LayoutParams attributes = this.mLauncher.getWindow().getAttributes();
            int i = attributes.rotationAnimation;
            attributes.rotationAnimation = homeScreenCanRotate() ? 0 : 3;
            if (i != attributes.rotationAnimation) {
                this.mLauncher.getWindow().setAttributes(attributes);
            }
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        boolean z = this.mAutoRotateEnabled;
        boolean z2 = this.mPrefs.getBoolean(ALLOW_ROTATION_PREFERENCE_KEY, getAllowRotationDefaultValue());
        this.mAutoRotateEnabled = z2;
        if (z2 != z) {
            notifyChange();
            updateRotationAnimation();
            this.mLauncher.reapplyUi();
        }
    }

    public void setStateHandlerRequest(int request) {
        if (this.mStateHandlerRequest != request) {
            this.mStateHandlerRequest = request;
            updateRotationAnimation();
            notifyChange();
        }
    }

    public void setCurrentTransitionRequest(int request) {
        if (this.mCurrentTransitionRequest != request) {
            this.mCurrentTransitionRequest = request;
            notifyChange();
        }
    }

    public void setCurrentStateRequest(int request) {
        if (this.mCurrentStateRequest != request) {
            this.mCurrentStateRequest = request;
            notifyChange();
        }
    }

    public void forceAllowRotationForTesting(boolean allowRotation) {
        this.mIgnoreAutoRotateSettings = allowRotation || this.mLauncher.getResources().getBoolean(R.bool.allow_rotation);
        notifyChange();
    }

    public void initialize() {
        if (this.mInitialized) {
            return;
        }
        this.mInitialized = true;
        notifyChange();
        updateRotationAnimation();
    }

    public void destroy() {
        if (this.mDestroyed) {
            return;
        }
        this.mDestroyed = true;
        SharedPreferences sharedPreferences = this.mPrefs;
        if (sharedPreferences != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void notifyChange() {
        /*
            r4 = this;
            boolean r0 = r4.mInitialized
            if (r0 == 0) goto L3a
            boolean r0 = r4.mDestroyed
            if (r0 == 0) goto L9
            goto L3a
        L9:
            int r0 = r4.mStateHandlerRequest
            r1 = 14
            r2 = -1
            r3 = 2
            if (r0 == 0) goto L14
            if (r0 != r3) goto L2e
            goto L2f
        L14:
            int r0 = r4.mCurrentTransitionRequest
            if (r0 == 0) goto L1b
            if (r0 != r3) goto L2e
            goto L2f
        L1b:
            int r0 = r4.mCurrentStateRequest
            if (r0 != r3) goto L20
            goto L2f
        L20:
            boolean r1 = r4.mIgnoreAutoRotateSettings
            if (r1 != 0) goto L2e
            r1 = 1
            if (r0 == r1) goto L2e
            boolean r0 = r4.mAutoRotateEnabled
            if (r0 == 0) goto L2c
            goto L2e
        L2c:
            r1 = 5
            goto L2f
        L2e:
            r1 = r2
        L2f:
            int r0 = r4.mLastActivityFlags
            if (r1 == r0) goto L3a
            r4.mLastActivityFlags = r1
            com.android.launcher3.Launcher r4 = r4.mLauncher
            com.android.launcher3.util.UiThreadHelper.setOrientationAsync(r4, r1)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.states.RotationHelper.notifyChange():void");
    }

    public String toString() {
        return String.format("[mStateHandlerRequest=%d, mCurrentStateRequest=%d, mLastActivityFlags=%d, mIgnoreAutoRotateSettings=%b, mAutoRotateEnabled=%b]", Integer.valueOf(this.mStateHandlerRequest), Integer.valueOf(this.mCurrentStateRequest), Integer.valueOf(this.mLastActivityFlags), Boolean.valueOf(this.mIgnoreAutoRotateSettings), Boolean.valueOf(this.mAutoRotateEnabled));
    }
}
