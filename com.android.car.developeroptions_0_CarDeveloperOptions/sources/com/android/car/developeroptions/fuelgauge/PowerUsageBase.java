package com.android.car.developeroptions.fuelgauge;

import android.app.Activity;
import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.fuelgauge.BatteryBroadcastReceiver;
import com.android.internal.os.BatteryStatsHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class PowerUsageBase extends DashboardFragment {
    static final int MENU_STATS_REFRESH = 2;
    private BatteryBroadcastReceiver mBatteryBroadcastReceiver;
    protected BatteryStatsHelper mStatsHelper;

    protected abstract void refreshUi(int i);

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mStatsHelper = new BatteryStatsHelper(activity, true);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStatsHelper.create(bundle);
        setHasOptionsMenu(true);
        BatteryBroadcastReceiver batteryBroadcastReceiver = new BatteryBroadcastReceiver(getContext());
        this.mBatteryBroadcastReceiver = batteryBroadcastReceiver;
        batteryBroadcastReceiver.setBatteryChangedListener(new BatteryBroadcastReceiver.OnBatteryChangedListener() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$PowerUsageBase$Ot5SGnHWBb0_Mt1YnONbkGNcUnQ
            @Override // com.android.car.developeroptions.fuelgauge.BatteryBroadcastReceiver.OnBatteryChangedListener
            public final void onBatteryChanged(int i) {
                this.f$0.lambda$onCreate$0$PowerUsageBase(i);
            }
        });
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mBatteryBroadcastReceiver.register();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mBatteryBroadcastReceiver.unRegister();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: restartBatteryStatsLoader, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0$PowerUsageBase(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("refresh_type", i);
        getLoaderManager().restartLoader(0, bundle, new PowerLoaderCallback());
    }

    protected void updatePreference(BatteryHistoryPreference batteryHistoryPreference) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        batteryHistoryPreference.setStats(this.mStatsHelper);
        BatteryUtils.logRuntime("PowerUsageBase", "updatePreference", jCurrentTimeMillis);
    }

    public class PowerLoaderCallback implements LoaderManager.LoaderCallbacks<BatteryStatsHelper> {
        private int mRefreshType;

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<BatteryStatsHelper> loader) {
        }

        public PowerLoaderCallback() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<BatteryStatsHelper> onCreateLoader(int i, Bundle bundle) {
            this.mRefreshType = bundle.getInt("refresh_type");
            return new BatteryStatsHelperLoader(PowerUsageBase.this.getContext());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<BatteryStatsHelper> loader, BatteryStatsHelper batteryStatsHelper) {
            PowerUsageBase powerUsageBase = PowerUsageBase.this;
            powerUsageBase.mStatsHelper = batteryStatsHelper;
            powerUsageBase.refreshUi(this.mRefreshType);
        }
    }
}
