package com.android.car.settings.datetime;

import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class DatetimeSettingsFragment extends SettingsFragment {
    public static final long MIN_DATE = 1194220800000L;
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.datetime_settings_fragment, "android.settings.DATE_SETTINGS");

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.datetime_settings_fragment;
    }
}
