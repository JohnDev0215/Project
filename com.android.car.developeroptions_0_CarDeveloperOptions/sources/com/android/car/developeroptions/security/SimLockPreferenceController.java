package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SimLockPreferenceController extends BasePreferenceController {
    private static final String KEY_SIM_LOCK = "sim_lock_settings";
    private final CarrierConfigManager mCarrierConfigManager;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;
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

    public SimLockPreferenceController(Context context) {
        super(context, KEY_SIM_LOCK);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mCarrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService("telephony_subscription_service");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (this.mUserManager.isAdminUser() && isSimIccReady() && !this.mCarrierConfigManager.getConfig().getBoolean("hide_sim_lock_settings_bool")) ? 0 : 4;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference == null) {
            return;
        }
        preferenceFindPreference.setEnabled(isSimReady());
    }

    private boolean isSimReady() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        Iterator it = activeSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            int simState = this.mTelephonyManager.getSimState(((SubscriptionInfo) it.next()).getSimSlotIndex());
            if (simState != 1 && simState != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isSimIccReady() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        Iterator it = activeSubscriptionInfoList.iterator();
        while (it.hasNext()) {
            if (this.mTelephonyManager.hasIccCard(((SubscriptionInfo) it.next()).getSimSlotIndex())) {
                return true;
            }
        }
        return false;
    }
}
