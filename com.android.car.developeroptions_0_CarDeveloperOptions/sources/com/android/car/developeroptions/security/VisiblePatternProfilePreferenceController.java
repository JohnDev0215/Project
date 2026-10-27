package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class VisiblePatternProfilePreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume {
    private static final String KEY_VISIBLE_PATTERN_PROFILE = "visiblepattern_profile";
    private static final String TAG = "VisPtnProfPrefCtrl";
    private final LockPatternUtils mLockPatternUtils;
    private Preference mPreference;
    private final int mProfileChallengeUserId;
    private final UserManager mUm;
    private final int mUserId;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public VisiblePatternProfilePreferenceController(Context context) {
        this(context, null);
    }

    public VisiblePatternProfilePreferenceController(Context context, Lifecycle lifecycle) {
        super(context, KEY_VISIBLE_PATTERN_PROFILE);
        this.mUserId = UserHandle.myUserId();
        this.mUm = (UserManager) context.getSystemService("user");
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
        this.mProfileChallengeUserId = Utils.getManagedProfileId(this.mUm, this.mUserId);
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.android.car.developeroptions.security.-$$Lambda$VisiblePatternProfilePreferenceController$-YHXKJhynL1NbMSB_0oK4lJ88_U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$getAvailabilityStatus$0$VisiblePatternProfilePreferenceController();
            }
        });
        try {
            futureTask.run();
            return ((Integer) futureTask.get()).intValue();
        } catch (InterruptedException | ExecutionException unused) {
            Log.w(TAG, "Error getting lock pattern state.");
            return 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getAvailabilityStatus$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$getAvailabilityStatus$0$VisiblePatternProfilePreferenceController() throws Exception {
        boolean zIsSecure = this.mLockPatternUtils.isSecure(this.mProfileChallengeUserId);
        boolean z = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(this.mProfileChallengeUserId) == 65536;
        if (zIsSecure && z) {
            return 0;
        }
        return 4;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mLockPatternUtils.isVisiblePatternEnabled(this.mProfileChallengeUserId);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        if (Utils.startQuietModeDialogIfNecessary(this.mContext, this.mUm, this.mProfileChallengeUserId)) {
            return false;
        }
        this.mLockPatternUtils.setVisiblePatternEnabled(z, this.mProfileChallengeUserId);
        return true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mPreference.setVisible(isAvailable());
    }
}
