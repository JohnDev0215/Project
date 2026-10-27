package com.android.car.developeroptions.fuelgauge;

import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public class InactiveApps extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private static final CharSequence[] SETTABLE_BUCKETS_NAMES = {"ACTIVE", "WORKING_SET", "FREQUENT", "RARE"};
    private static final CharSequence[] SETTABLE_BUCKETS_VALUES = {Integer.toString(10), Integer.toString(20), Integer.toString(30), Integer.toString(40)};
    private UsageStatsManager mUsageStats;

    static String bucketToName(int i) {
        return i != 5 ? i != 10 ? i != 20 ? i != 30 ? i != 40 ? i != 50 ? "" : "NEVER" : "RARE" : "FREQUENT" : "WORKING_SET" : "ACTIVE" : "EXEMPTED";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 238;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUsageStats = (UsageStatsManager) getActivity().getSystemService(UsageStatsManager.class);
        addPreferencesFromResource(R.xml.inactive_apps);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        preferenceScreen.setOrderingAsAdded(false);
        FragmentActivity activity = getActivity();
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            String str = resolveInfo.activityInfo.applicationInfo.packageName;
            ListPreference listPreference = new ListPreference(getPrefContext());
            listPreference.setTitle(resolveInfo.loadLabel(packageManager));
            listPreference.setIcon(resolveInfo.loadIcon(packageManager));
            listPreference.setKey(str);
            listPreference.setEntries(SETTABLE_BUCKETS_NAMES);
            listPreference.setEntryValues(SETTABLE_BUCKETS_VALUES);
            updateSummary(listPreference);
            listPreference.setOnPreferenceChangeListener(this);
            preferenceScreen.addPreference(listPreference);
        }
    }

    private void updateSummary(ListPreference listPreference) {
        Resources resources = getActivity().getResources();
        int appStandbyBucket = this.mUsageStats.getAppStandbyBucket(listPreference.getKey());
        listPreference.setSummary(resources.getString(R.string.standby_bucket_summary, bucketToName(appStandbyBucket)));
        boolean z = appStandbyBucket >= 10 && appStandbyBucket <= 40;
        if (z) {
            listPreference.setValue(Integer.toString(appStandbyBucket));
        }
        listPreference.setEnabled(z);
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        this.mUsageStats.setAppStandbyBucket(preference.getKey(), Integer.parseInt((String) obj));
        updateSummary((ListPreference) preference);
        return false;
    }
}
