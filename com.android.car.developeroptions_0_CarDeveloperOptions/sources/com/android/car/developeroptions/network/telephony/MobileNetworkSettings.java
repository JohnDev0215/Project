package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.RestrictedDashboardFragment;
import com.android.car.developeroptions.datausage.BillingCyclePreferenceController;
import com.android.car.developeroptions.datausage.DataUsageSummaryPreferenceController;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.car.developeroptions.network.telephony.cdma.CdmaSubscriptionPreferenceController;
import com.android.car.developeroptions.network.telephony.cdma.CdmaSystemSelectPreferenceController;
import com.android.car.developeroptions.network.telephony.gsm.AutoSelectPreferenceController;
import com.android.car.developeroptions.network.telephony.gsm.OpenNetworkSelectPagePreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkSettings extends RestrictedDashboardFragment {
    static final String KEY_CLICKED_PREF = "key_clicked_pref";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.network.telephony.MobileNetworkSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.mobile_network_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private CdmaSubscriptionPreferenceController mCdmaSubscriptionPreferenceController;
    private CdmaSystemSelectPreferenceController mCdmaSystemSelectPreferenceController;
    private String mClickedPrefKey;
    private int mSubId;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "NetworkSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1571;
    }

    public MobileNetworkSettings() {
        super("no_config_mobile_networks");
        this.mSubId = -1;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (super.onPreferenceTreeClick(preference)) {
            return true;
        }
        String key = preference.getKey();
        if (!TextUtils.equals(key, "cdma_system_select_key") && !TextUtils.equals(key, "cdma_subscription_key")) {
            return false;
        }
        if (this.mTelephonyManager.getEmergencyCallbackMode()) {
            startActivityForResult(new Intent("android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS", (Uri) null), 17);
            this.mClickedPrefKey = key;
        }
        return true;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mSubId = getArguments().getInt("android.provider.extra.SUB_ID", MobileNetworkUtils.getSearchableSubscriptionId(context));
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") && this.mSubId != -1) {
            return Arrays.asList(new DataUsageSummaryPreferenceController(getActivity(), getSettingsLifecycle(), this, this.mSubId));
        }
        return Arrays.asList(new AbstractPreferenceController[0]);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2")) {
            ((CallsDefaultSubscriptionController) use(CallsDefaultSubscriptionController.class)).init(getLifecycle());
            ((SmsDefaultSubscriptionController) use(SmsDefaultSubscriptionController.class)).init(getLifecycle());
            ((MobileNetworkSwitchController) use(MobileNetworkSwitchController.class)).init(getLifecycle(), this.mSubId);
            ((CarrierSettingsVersionPreferenceController) use(CarrierSettingsVersionPreferenceController.class)).init(this.mSubId);
            ((BillingCyclePreferenceController) use(BillingCyclePreferenceController.class)).init(this.mSubId);
        }
        ((MobileDataPreferenceController) use(MobileDataPreferenceController.class)).init(getFragmentManager(), this.mSubId);
        ((RoamingPreferenceController) use(RoamingPreferenceController.class)).init(getFragmentManager(), this.mSubId);
        ((ApnPreferenceController) use(ApnPreferenceController.class)).init(this.mSubId);
        ((CarrierPreferenceController) use(CarrierPreferenceController.class)).init(this.mSubId);
        ((DataUsagePreferenceController) use(DataUsagePreferenceController.class)).init(this.mSubId);
        ((PreferredNetworkModePreferenceController) use(PreferredNetworkModePreferenceController.class)).init(this.mSubId);
        ((EnabledNetworkModePreferenceController) use(EnabledNetworkModePreferenceController.class)).init(this.mSubId);
        ((DataServiceSetupPreferenceController) use(DataServiceSetupPreferenceController.class)).init(this.mSubId);
        if (!FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2")) {
            ((EuiccPreferenceController) use(EuiccPreferenceController.class)).init(this.mSubId);
        }
        ((WifiCallingPreferenceController) use(WifiCallingPreferenceController.class)).init(this.mSubId);
        ((PreferenceCategoryController) use(PreferenceCategoryController.class)).setChildren(Arrays.asList(((AutoSelectPreferenceController) use(AutoSelectPreferenceController.class)).init(this.mSubId).addListener(((OpenNetworkSelectPagePreferenceController) use(OpenNetworkSelectPagePreferenceController.class)).init(this.mSubId))));
        CdmaSystemSelectPreferenceController cdmaSystemSelectPreferenceController = (CdmaSystemSelectPreferenceController) use(CdmaSystemSelectPreferenceController.class);
        this.mCdmaSystemSelectPreferenceController = cdmaSystemSelectPreferenceController;
        cdmaSystemSelectPreferenceController.init(getPreferenceManager(), this.mSubId);
        CdmaSubscriptionPreferenceController cdmaSubscriptionPreferenceController = (CdmaSubscriptionPreferenceController) use(CdmaSubscriptionPreferenceController.class);
        this.mCdmaSubscriptionPreferenceController = cdmaSubscriptionPreferenceController;
        cdmaSubscriptionPreferenceController.init(getPreferenceManager(), this.mSubId);
        ((Enhanced4gLtePreferenceController) use(Enhanced4gLtePreferenceController.class)).init(this.mSubId).addListener(((VideoCallingPreferenceController) use(VideoCallingPreferenceController.class)).init(this.mSubId));
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Log.i("NetworkSettings", "onCreate:+");
        super.onCreate(bundle);
        Context context = getContext();
        this.mTelephonyManager = TelephonyManager.from(context).createForSubscriptionId(this.mSubId);
        onRestoreInstance(bundle);
    }

    void onRestoreInstance(Bundle bundle) {
        if (bundle != null) {
            this.mClickedPrefKey = bundle.getString(KEY_CLICKED_PREF);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") ? R.xml.mobile_network_settings_v2 : R.xml.mobile_network_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(KEY_CLICKED_PREF, this.mClickedPrefKey);
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Preference preferenceFindPreference;
        if (i != 17 || i2 == 0 || (preferenceFindPreference = getPreferenceScreen().findPreference(this.mClickedPrefKey)) == null) {
            return;
        }
        preferenceFindPreference.performClick();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") && this.mSubId != -1) {
            MenuItem menuItemAdd = menu.add(0, R.id.edit_sim_name, 0, R.string.mobile_network_sim_name);
            menuItemAdd.setIcon(android.R.drawable.ic_media_route_connecting_dark_01_mtrl);
            menuItemAdd.setShowAsAction(2);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") && this.mSubId != -1 && menuItem.getItemId() == R.id.edit_sim_name) {
            RenameMobileNetworkDialogFragment.newInstance(this.mSubId).show(getFragmentManager(), "RenameMobileNetwork");
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
