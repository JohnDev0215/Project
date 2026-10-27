package com.android.car.settings.sound;

import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;

/* JADX INFO: loaded from: classes5.dex */
public class SoundSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.sound_settings_fragment, "android.settings.SOUND_SETTINGS");

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.sound_settings_fragment;
    }
}
