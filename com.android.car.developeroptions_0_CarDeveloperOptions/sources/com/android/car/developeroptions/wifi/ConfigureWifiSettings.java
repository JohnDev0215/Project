package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.wifi.p2p.WifiP2pPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ConfigureWifiSettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.wifi.ConfigureWifiSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.wifi_configure_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private UseOpenWifiPreferenceController mUseOpenWifiPreferenceController;
    private WifiWakeupPreferenceController mWifiWakeupPreferenceController;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ConfigureWifiSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 338;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_configure_settings;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment
    public int getInitialExpandedChildCount() {
        return this.mUseOpenWifiPreferenceController.isAvailable() ? 3 : 2;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mWifiWakeupPreferenceController = new WifiWakeupPreferenceController(context, this, getSettingsLifecycle());
        this.mUseOpenWifiPreferenceController = new UseOpenWifiPreferenceController(context, this, getSettingsLifecycle());
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mWifiWakeupPreferenceController);
        arrayList.add(new NotifyOpenNetworksPreferenceController(context, getSettingsLifecycle()));
        arrayList.add(this.mUseOpenWifiPreferenceController);
        arrayList.add(new WifiInfoPreferenceController(context, getSettingsLifecycle(), wifiManager));
        arrayList.add(new WifiP2pPreferenceController(context, getSettingsLifecycle(), wifiManager));
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        UseOpenWifiPreferenceController useOpenWifiPreferenceController;
        WifiWakeupPreferenceController wifiWakeupPreferenceController;
        if (i == 600 && (wifiWakeupPreferenceController = this.mWifiWakeupPreferenceController) != null) {
            wifiWakeupPreferenceController.onActivityResult(i, i2);
        } else if (i == 400 && (useOpenWifiPreferenceController = this.mUseOpenWifiPreferenceController) != null) {
            useOpenWifiPreferenceController.onActivityResult(i, i2);
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }
}
