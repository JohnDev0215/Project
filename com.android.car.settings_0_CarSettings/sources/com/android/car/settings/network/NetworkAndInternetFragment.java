package com.android.car.settings.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkAndInternetFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.network_and_internet_fragment, "android.settings.panel.action.INTERNET_CONNECTIVITY") { // from class: com.android.car.settings.network.NetworkAndInternetFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider, com.android.settingslib.search.Indexable.SearchIndexProvider
        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            if (!NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class))) {
                nonIndexableKeys.add(context.getString(R.string.pk_mobile_network_settings_entry));
                nonIndexableKeys.add(context.getString(R.string.pk_data_usage_settings_entry));
            }
            return nonIndexableKeys;
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.network_and_internet_fragment;
    }
}
