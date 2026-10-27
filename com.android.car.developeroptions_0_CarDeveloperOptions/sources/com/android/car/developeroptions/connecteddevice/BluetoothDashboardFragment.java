package com.android.car.developeroptions.connecteddevice;

import android.content.Context;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceRenamePreferenceController;
import com.android.car.developeroptions.bluetooth.BluetoothSwitchPreferenceController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.SwitchBarController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.FooterPreference;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDashboardFragment extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.connecteddevice.BluetoothDashboardFragment.1
    };
    private BluetoothSwitchPreferenceController mController;
    private FooterPreference mFooterPreference;
    private SwitchBar mSwitchBar;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_bluetooth_screen;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "BluetoothDashboardFrag";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1390;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_screen;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFooterPreference = (FooterPreference) findPreference("bluetooth_screen_footer");
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((BluetoothDeviceRenamePreferenceController) use(BluetoothDeviceRenamePreferenceController.class)).setFragment(this);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBar = switchBar;
        this.mController = new BluetoothSwitchPreferenceController(settingsActivity, new SwitchBarController(switchBar), this.mFooterPreference);
        Lifecycle settingsLifecycle = getSettingsLifecycle();
        if (settingsLifecycle != null) {
            settingsLifecycle.addObserver(this.mController);
        }
    }
}
