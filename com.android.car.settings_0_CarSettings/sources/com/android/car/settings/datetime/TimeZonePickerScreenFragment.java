package com.android.car.settings.datetime;

import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes5.dex */
public class TimeZonePickerScreenFragment extends SettingsFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.timezone_picker_screen_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        scrollToPreference(TimeZone.getDefault().getID());
    }
}
