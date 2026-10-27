package com.android.car.settings.storage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.applications.ApplicationListItemManager;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class StorageApplicationListPreferenceController extends PreferenceController<PreferenceGroup> implements ApplicationListItemManager.AppListItemListener {
    public StorageApplicationListPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.applications.ApplicationListItemManager.AppListItemListener
    public void onDataLoaded(ArrayList<ApplicationsState.AppEntry> apps) {
        getPreference().removeAll();
        for (ApplicationsState.AppEntry appEntry : apps) {
            getPreference().addPreference(createPreference(appEntry.label, appEntry.sizeStr, appEntry.icon, appEntry.info.packageName));
        }
    }

    protected Preference createPreference(String title, String summary, Drawable icon, final String key) {
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setTitle(title);
        preference.setSummary(summary);
        preference.setIcon(icon);
        preference.setKey(key);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.storage.-$$Lambda$StorageApplicationListPreferenceController$ixKXMuaLnRuoi9rKV9KELkCwy_E
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createPreference$0$StorageApplicationListPreferenceController(key, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createPreference$0$StorageApplicationListPreferenceController(String key, Preference p) {
        getFragmentController().launchFragment(AppStorageSettingsDetailsFragment.getInstance(key));
        return true;
    }
}
