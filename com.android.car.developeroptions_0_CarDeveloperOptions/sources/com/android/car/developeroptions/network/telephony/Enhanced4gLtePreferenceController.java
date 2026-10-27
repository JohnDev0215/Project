package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.ims.ImsManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Enhanced4gLtePreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final int VARIANT_TITLE_4G_CALLING;
    private final int VARIANT_TITLE_ADVANCED_CALL;
    private final int VARIANT_TITLE_VOLTE;
    private final List<On4gLteUpdateListener> m4gLteListeners;
    private PersistableBundle mCarrierConfig;
    private CarrierConfigManager mCarrierConfigManager;
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener;
    private Preference mPreference;
    private TelephonyManager mTelephonyManager;
    private final CharSequence[] mVariantSumaries;
    private final CharSequence[] mVariantTitles;

    public interface On4gLteUpdateListener {
        void on4gLteUpdated();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public Enhanced4gLtePreferenceController(Context context, String str) {
        super(context, str);
        this.VARIANT_TITLE_VOLTE = 0;
        this.VARIANT_TITLE_ADVANCED_CALL = 1;
        this.VARIANT_TITLE_4G_CALLING = 2;
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.m4gLteListeners = new ArrayList();
        this.mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
        this.mVariantTitles = context.getResources().getTextArray(R.array.enhanced_4g_lte_mode_title_variant);
        this.mVariantSumaries = context.getResources().getTextArray(R.array.enhanced_4g_lte_mode_sumary_variant);
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        ImsManager imsManager;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        if ((i == -1 || (imsManager = this.mImsManager) == null || configForSubId == null || !imsManager.isVolteEnabledByPlatform() || !this.mImsManager.isVolteProvisionedOnDevice() || !MobileNetworkUtils.isImsServiceStateReady(this.mImsManager) || configForSubId.getBoolean("hide_enhanced_4g_lte_bool")) ? false : true) {
            return is4gLtePrefEnabled() ? 0 : 1;
        }
        return 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mPhoneStateListener.unregister();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        boolean z = this.mCarrierConfig.getBoolean("show_4g_for_lte_data_icon_bool");
        int i = this.mCarrierConfig.getInt("enhanced_4g_lte_title_variant_int");
        boolean z2 = false;
        if (i != 1) {
            i = z ? 2 : 0;
        }
        switchPreference.setTitle(this.mVariantTitles[i]);
        switchPreference.setSummary(this.mVariantSumaries[i]);
        switchPreference.setEnabled(is4gLtePrefEnabled());
        if (this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled()) {
            z2 = true;
        }
        switchPreference.setChecked(z2);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        this.mImsManager.setEnhanced4gLteModeSetting(z);
        Iterator<On4gLteUpdateListener> it = this.m4gLteListeners.iterator();
        while (it.hasNext()) {
            it.next().on4gLteUpdated();
        }
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser();
    }

    public Enhanced4gLtePreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        this.mCarrierConfig = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        int i2 = this.mSubId;
        if (i2 != -1) {
            this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i2));
        }
        return this;
    }

    public Enhanced4gLtePreferenceController addListener(On4gLteUpdateListener on4gLteUpdateListener) {
        this.m4gLteListeners.add(on4gLteUpdateListener);
        return this;
    }

    private boolean is4gLtePrefEnabled() {
        ImsManager imsManager;
        int i = this.mSubId;
        return i != -1 && this.mTelephonyManager.getCallState(i) == 0 && (imsManager = this.mImsManager) != null && imsManager.isNonTtyOrTtyOnVolteEnabled() && this.mCarrierConfig.getBoolean("editable_enhanced_4g_lte_bool");
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            Enhanced4gLtePreferenceController enhanced4gLtePreferenceController = Enhanced4gLtePreferenceController.this;
            enhanced4gLtePreferenceController.updateState(enhanced4gLtePreferenceController.mPreference);
        }

        public void register(int i) {
            ((PhoneStateListener) this).mSubId = Integer.valueOf(i);
            Enhanced4gLtePreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            Enhanced4gLtePreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }
}
