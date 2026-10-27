package com.android.car.developeroptions.biometrics;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.preference.Preference;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class BiometricStatusPreferenceController extends BasePreferenceController {
    protected final LockPatternUtils mLockPatternUtils;
    protected final int mProfileChallengeUserId;
    protected final UserManager mUm;
    private final int mUserId;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    protected abstract String getEnrollClassName();

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    protected abstract String getSettingsClassName();

    protected abstract String getSummaryTextEnrolled();

    protected abstract String getSummaryTextNoneEnrolled();

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    protected abstract boolean hasEnrolledBiometrics();

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    protected abstract boolean isDeviceSupported();

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    protected boolean isUserSupported() {
        return true;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BiometricStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserId = UserHandle.myUserId();
        this.mUm = (UserManager) context.getSystemService("user");
        this.mLockPatternUtils = FeatureFactory.getFactory(context).getSecurityFeatureProvider().getLockPatternUtils(context);
        this.mProfileChallengeUserId = Utils.getManagedProfileId(this.mUm, this.mUserId);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (isDeviceSupported()) {
            return isUserSupported() ? 0 : 4;
        }
        return 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        final String enrollClassName;
        if (!isAvailable()) {
            if (preference != null) {
                preference.setVisible(false);
                return;
            }
            return;
        }
        preference.setVisible(true);
        final int userId = getUserId();
        if (hasEnrolledBiometrics()) {
            preference.setSummary(getSummaryTextEnrolled());
            enrollClassName = getSettingsClassName();
        } else {
            preference.setSummary(getSummaryTextNoneEnrolled());
            enrollClassName = getEnrollClassName();
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.biometrics.-$$Lambda$BiometricStatusPreferenceController$V6il_JicvSInyiNgNFemO8GncyE
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return BiometricStatusPreferenceController.lambda$updateState$0(userId, enrollClassName, preference2);
            }
        });
    }

    static /* synthetic */ boolean lambda$updateState$0(int i, String str, Preference preference) {
        Context context = preference.getContext();
        if (Utils.startQuietModeDialogIfNecessary(context, UserManager.get(context), i)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClassName("com.android.car.developeroptions", str);
        intent.putExtra("android.intent.extra.USER_ID", i);
        context.startActivity(intent);
        return true;
    }

    protected int getUserId() {
        return this.mUserId;
    }
}
