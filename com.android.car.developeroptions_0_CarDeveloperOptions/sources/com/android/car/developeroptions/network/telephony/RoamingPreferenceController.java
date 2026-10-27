package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class RoamingPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String DIALOG_TAG = "MobileDataDialog";
    private CarrierConfigManager mCarrierConfigManager;
    private DataContentObserver mDataContentObserver;
    FragmentManager mFragmentManager;
    boolean mNeedDialog;
    private RestrictedSwitchPreference mSwitchPreference;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 1;
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

    public RoamingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mDataContentObserver.unRegister(this.mContext);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSwitchPreference = (RestrictedSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        if (!this.mNeedDialog) {
            return true;
        }
        showDialog();
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        boolean zIsDialogNeeded = isDialogNeeded();
        this.mNeedDialog = zIsDialogNeeded;
        if (zIsDialogNeeded) {
            return false;
        }
        this.mTelephonyManager.setDataRoamingEnabled(z);
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) preference;
        restrictedSwitchPreference.setEnabled(this.mSubId != -1);
        restrictedSwitchPreference.setChecked(isChecked());
    }

    boolean isDialogNeeded() {
        boolean zIsDataRoamingEnabled = this.mTelephonyManager.isDataRoamingEnabled();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubId);
        if (zIsDataRoamingEnabled) {
            return false;
        }
        return configForSubId == null || !configForSubId.getBoolean("disable_charge_indication_bool");
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mTelephonyManager.isDataRoamingEnabled();
    }

    public void init(FragmentManager fragmentManager, int i) {
        this.mFragmentManager = fragmentManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    private void showDialog() {
        RoamingDialogFragment.newInstance(this.mSubId).show(this.mFragmentManager, DIALOG_TAG);
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            RoamingPreferenceController roamingPreferenceController = RoamingPreferenceController.this;
            roamingPreferenceController.updateState(roamingPreferenceController.mSwitchPreference);
        }

        public void register(Context context, int i) {
            Uri uriFor = Settings.Global.getUriFor("data_roaming");
            if (TelephonyManager.getDefault().getSimCount() != 1) {
                uriFor = Settings.Global.getUriFor("data_roaming" + i);
            }
            context.getContentResolver().registerContentObserver(uriFor, false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
