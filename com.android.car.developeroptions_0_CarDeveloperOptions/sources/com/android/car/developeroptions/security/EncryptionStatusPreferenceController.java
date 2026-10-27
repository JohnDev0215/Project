package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public class EncryptionStatusPreferenceController extends BasePreferenceController {
    static final String PREF_KEY_ENCRYPTION_DETAIL_PAGE = "encryption_and_credentials_encryption_status";
    static final String PREF_KEY_ENCRYPTION_SECURITY_PAGE = "encryption_and_credential";
    private final UserManager mUserManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public EncryptionStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserManager = (UserManager) context.getSystemService("user");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE) || this.mContext.getResources().getBoolean(R.bool.config_show_encryption_and_credentials_encryption_status)) {
            return this.mUserManager.isAdminUser() ? 0 : 4;
        }
        return 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (LockPatternUtils.isDeviceEncryptionEnabled()) {
            if (TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE)) {
                preference.setFragment(null);
            }
            preference.setSummary(R.string.crypt_keeper_encrypted_summary);
        } else {
            if (TextUtils.equals(getPreferenceKey(), PREF_KEY_ENCRYPTION_DETAIL_PAGE)) {
                preference.setFragment(CryptKeeperSettings.class.getName());
            }
            preference.setSummary(R.string.decryption_settings_summary);
        }
    }
}
