package com.android.car.developeroptions.development.gamedriver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class GameDriverAppPreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener, GameDriverContentObserver.OnGameDriverContentChangedListener, LifecycleObserver, OnStart, OnStop {
    private final Comparator<AppInfo> appInfoComparator;
    private final List<AppInfo> mAppInfos;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final Set<String> mDevOptInApps;
    private final Set<String> mDevOptOutApps;
    private final CharSequence[] mEntryList;
    GameDriverContentObserver mGameDriverContentObserver;
    private final String mPreferenceDefault;
    private final String mPreferenceGameDriver;
    private PreferenceGroup mPreferenceGroup;
    private final String mPreferenceSystem;
    private final String mPreferenceTitle;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public GameDriverAppPreferenceController(Context context, String str) {
        super(context, str);
        this.appInfoComparator = new Comparator<AppInfo>(this) { // from class: com.android.car.developeroptions.development.gamedriver.GameDriverAppPreferenceController.1
            @Override // java.util.Comparator
            public final int compare(AppInfo appInfo, AppInfo appInfo2) {
                return Collator.getInstance().compare(appInfo.label, appInfo2.label);
            }
        };
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mGameDriverContentObserver = new GameDriverContentObserver(new Handler(Looper.getMainLooper()), this);
        Resources resources = context.getResources();
        this.mEntryList = resources.getStringArray(R.array.game_driver_app_preference_values);
        this.mPreferenceTitle = resources.getString(R.string.game_driver_app_preference_title);
        this.mPreferenceDefault = resources.getString(R.string.game_driver_app_preference_default);
        this.mPreferenceGameDriver = resources.getString(R.string.game_driver_app_preference_game_driver);
        this.mPreferenceSystem = resources.getString(R.string.game_driver_app_preference_system);
        this.mAppInfos = getAppInfos(context);
        this.mDevOptInApps = getGlobalSettingsString(this.mContentResolver, "game_driver_opt_in_apps");
        this.mDevOptOutApps = getGlobalSettingsString(this.mContentResolver, "game_driver_opt_out_apps");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (!DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(this.mContext) || Settings.Global.getInt(this.mContentResolver, "game_driver_all_apps", 0) == 2) ? 2 : 0;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        PreferenceGroup preferenceGroup = (PreferenceGroup) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceGroup = preferenceGroup;
        Context context = preferenceGroup.getContext();
        for (AppInfo appInfo : this.mAppInfos) {
            this.mPreferenceGroup.addPreference(createListPreference(context, appInfo.info.packageName, appInfo.label));
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mGameDriverContentObserver.register(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mGameDriverContentObserver.unregister(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setVisible(isAvailable());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        ListPreference listPreference = (ListPreference) preference;
        String string = obj.toString();
        String key = preference.getKey();
        if (string.equals(this.mPreferenceSystem)) {
            this.mDevOptInApps.remove(key);
            this.mDevOptOutApps.add(key);
        } else if (string.equals(this.mPreferenceGameDriver)) {
            this.mDevOptInApps.add(key);
            this.mDevOptOutApps.remove(key);
        } else {
            this.mDevOptInApps.remove(key);
            this.mDevOptOutApps.remove(key);
        }
        listPreference.setValue(string);
        listPreference.setSummary(string);
        Settings.Global.putString(this.mContentResolver, "game_driver_opt_in_apps", String.join(",", this.mDevOptInApps));
        Settings.Global.putString(this.mContentResolver, "game_driver_opt_out_apps", String.join(",", this.mDevOptOutApps));
        return true;
    }

    @Override // com.android.car.developeroptions.development.gamedriver.GameDriverContentObserver.OnGameDriverContentChangedListener
    public void onGameDriverContentChanged() {
        updateState(this.mPreferenceGroup);
    }

    class AppInfo {
        final ApplicationInfo info;
        final String label;

        AppInfo(GameDriverAppPreferenceController gameDriverAppPreferenceController, PackageManager packageManager, ApplicationInfo applicationInfo) {
            this.info = applicationInfo;
            this.label = packageManager.getApplicationLabel(applicationInfo).toString();
        }
    }

    private List<AppInfo> getAppInfos(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        ArrayList arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if ((applicationInfo.flags & 1) == 0) {
                arrayList.add(new AppInfo(this, packageManager, applicationInfo));
            }
        }
        Collections.sort(arrayList, this.appInfoComparator);
        return arrayList;
    }

    private Set<String> getGlobalSettingsString(ContentResolver contentResolver, String str) {
        String string = Settings.Global.getString(contentResolver, str);
        if (string == null) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet(Arrays.asList(string.split(",")));
        hashSet.remove("");
        return hashSet;
    }

    protected ListPreference createListPreference(Context context, String str, String str2) {
        ListPreference listPreference = new ListPreference(context);
        listPreference.setKey(str);
        listPreference.setTitle(str2);
        listPreference.setDialogTitle(this.mPreferenceTitle);
        listPreference.setEntries(this.mEntryList);
        listPreference.setEntryValues(this.mEntryList);
        if (this.mDevOptOutApps.contains(str)) {
            listPreference.setValue(this.mPreferenceSystem);
            listPreference.setSummary(this.mPreferenceSystem);
        } else if (this.mDevOptInApps.contains(str)) {
            listPreference.setValue(this.mPreferenceGameDriver);
            listPreference.setSummary(this.mPreferenceGameDriver);
        } else {
            listPreference.setValue(this.mPreferenceDefault);
            listPreference.setSummary(this.mPreferenceDefault);
        }
        listPreference.setOnPreferenceChangeListener(this);
        return listPreference;
    }
}
