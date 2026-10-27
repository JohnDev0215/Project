package com.android.car.developeroptions.deviceinfo.storage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.provider.Settings;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.deletionhelper.ActivationWarningFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.MasterSwitchController;
import com.android.car.developeroptions.widget.MasterSwitchPreference;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.Utils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class AutomaticStorageManagementSwitchPreferenceController extends BasePreferenceController implements LifecycleObserver, OnResume, SwitchWidgetController.OnSwitchChangeListener {
    static final String STORAGE_MANAGER_ENABLED_BY_DEFAULT_PROPERTY = "ro.storage_manager.enabled";
    private FragmentManager mFragmentManager;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private MasterSwitchPreference mSwitch;
    private MasterSwitchController mSwitchController;

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

    public AutomaticStorageManagementSwitchPreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public AutomaticStorageManagementSwitchPreferenceController setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        return this;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSwitch = (MasterSwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return !ActivityManager.isLowRamDeviceStatic() ? 0 : 3;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (isAvailable()) {
            this.mSwitch.setChecked(Utils.isStorageManagerEnabled(this.mContext));
            MasterSwitchPreference masterSwitchPreference = this.mSwitch;
            if (masterSwitchPreference != null) {
                MasterSwitchController masterSwitchController = new MasterSwitchController(masterSwitchPreference);
                this.mSwitchController = masterSwitchController;
                masterSwitchController.setListener(this);
                this.mSwitchController.startListening();
            }
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController.OnSwitchChangeListener
    public boolean onSwitchToggled(boolean z) {
        this.mMetricsFeatureProvider.action(this.mContext, 489, z);
        Settings.Secure.putInt(this.mContext.getContentResolver(), "automatic_storage_manager_enabled", z ? 1 : 0);
        boolean z2 = SystemProperties.getBoolean(STORAGE_MANAGER_ENABLED_BY_DEFAULT_PROPERTY, false);
        boolean z3 = Settings.Secure.getInt(this.mContext.getContentResolver(), "automatic_storage_manager_turned_off_by_policy", 0) != 0;
        if (z && (!z2 || z3)) {
            ActivationWarningFragment.newInstance().show(this.mFragmentManager, "ActivationWarningFragment");
        }
        return true;
    }
}
