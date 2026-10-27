package com.android.car.settings.system;

import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class AboutSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.about_settings_fragment, "android.settings.DEVICE_INFO_SETTINGS");

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.about_settings_fragment;
    }
}
