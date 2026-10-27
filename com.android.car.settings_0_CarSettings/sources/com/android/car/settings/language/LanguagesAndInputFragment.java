package com.android.car.settings.language;

import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class LanguagesAndInputFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.languages_and_input_fragment, CarSettingActivities.LanguagesAndInputActivity.class);

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.languages_and_input_fragment;
    }
}
