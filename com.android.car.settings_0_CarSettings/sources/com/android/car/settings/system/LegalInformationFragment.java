package com.android.car.settings.system;

import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class LegalInformationFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.legal_information_fragment, CarSettingActivities.LegalInformationActivity.class);

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.legal_information_fragment;
    }
}
