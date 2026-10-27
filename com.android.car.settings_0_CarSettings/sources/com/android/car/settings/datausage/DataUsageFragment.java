package com.android.car.settings.datausage;

import android.content.Context;
import android.net.ConnectivityManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.network.NetworkUtils;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class DataUsageFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.data_usage_fragment, "android.settings.MOBILE_DATA_USAGE") { // from class: com.android.car.settings.datausage.DataUsageFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider
        protected boolean isPageSearchEnabled(Context context) {
            return NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class));
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.data_usage_fragment;
    }
}
