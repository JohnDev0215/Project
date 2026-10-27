package com.android.car.settings.applications;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.applications.ApplicationListItemManager;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationsSettingsPreferenceController extends PreferenceController<PreferenceGroup> implements ApplicationListItemManager.AppListItemListener {
    public ApplicationsSettingsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
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

    private Preference createPreference(String title, String summary, Drawable icon, final String packageName) {
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setTitle(title);
        preference.setSummary(summary);
        preference.setIcon(icon);
        preference.setKey(packageName);
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationsSettingsPreferenceController$sw0p0ZmI5QRNAeDcm3AxiuVKKmo
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createPreference$0$ApplicationsSettingsPreferenceController(packageName, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createPreference$0$ApplicationsSettingsPreferenceController(String packageName, Preference p) {
        Intent intent = new Intent(getContext(), (Class<?>) CarSettingActivities.ApplicationsDetailsActivity.class);
        intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
        getContext().startActivity(intent);
        return true;
    }
}
