package com.android.car.settings.system;

import android.content.Context;
import android.os.UserManager;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.development.DevelopmentSettingsUtil;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SystemSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.system_settings_fragment, CarSettingActivities.SystemSettingsActivity.class) { // from class: com.android.car.settings.system.SystemSettingsFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider, com.android.settingslib.search.Indexable.SearchIndexProvider
        public List<String> getNonIndexableKeys(Context context) {
            List<String> nonIndexableKeys = super.getNonIndexableKeys(context);
            if (!DevelopmentSettingsUtil.isDevelopmentSettingsEnabled(context, UserManager.get(context))) {
                nonIndexableKeys.add(context.getString(R.string.pk_developer_options_entry));
            }
            return nonIndexableKeys;
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.system_settings_fragment;
    }
}
