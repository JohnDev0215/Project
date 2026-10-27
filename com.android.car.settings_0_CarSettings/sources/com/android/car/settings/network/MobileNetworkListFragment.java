package com.android.car.settings.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class MobileNetworkListFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.mobile_network_fragment, CarSettingActivities.MobileNetworkListActivity.class) { // from class: com.android.car.settings.network.MobileNetworkListFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider
        protected boolean isPageSearchEnabled(Context context) {
            return NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class));
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.mobile_network_list_fragment;
    }
}
