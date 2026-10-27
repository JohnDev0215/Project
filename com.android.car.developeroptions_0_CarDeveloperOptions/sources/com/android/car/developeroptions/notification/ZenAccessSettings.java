package com.android.car.developeroptions.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.ArraySet;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppInfoBase;
import com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessController;
import com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessDetails;
import com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessSettingObserverMixin;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.EmptyTextSettings;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.apppreference.AppPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ZenAccessSettings extends EmptyTextSettings implements ZenAccessSettingObserverMixin.Listener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.ZenAccessSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.zen_access_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private Context mContext;
    private NotificationManager mNoMan;
    private PackageManager mPkgMan;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 180;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_access_settings;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPkgMan = activity.getPackageManager();
        this.mNoMan = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
        getSettingsLifecycle().addObserver(new ZenAccessSettingObserverMixin(getContext(), this));
    }

    @Override // com.android.car.developeroptions.widget.EmptyTextSettings, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setEmptyText(R.string.zen_access_empty_text);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        reloadList();
    }

    @Override // com.android.car.developeroptions.applications.specialaccess.zenaccess.ZenAccessSettingObserverMixin.Listener
    public void onZenAccessPolicyChanged() {
        reloadList();
    }

    private void reloadList() {
        List<ApplicationInfo> installedApplications;
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        ArrayList<ApplicationInfo> arrayList = new ArrayList();
        Set<String> packagesRequestingNotificationPolicyAccess = ZenAccessController.getPackagesRequestingNotificationPolicyAccess();
        if (!packagesRequestingNotificationPolicyAccess.isEmpty() && (installedApplications = this.mPkgMan.getInstalledApplications(0)) != null) {
            for (ApplicationInfo applicationInfo : installedApplications) {
                if (packagesRequestingNotificationPolicyAccess.contains(applicationInfo.packageName)) {
                    arrayList.add(applicationInfo);
                }
            }
        }
        ArraySet arraySet = new ArraySet();
        arraySet.addAll(this.mNoMan.getEnabledNotificationListenerPackages());
        packagesRequestingNotificationPolicyAccess.addAll(arraySet);
        Collections.sort(arrayList, new PackageItemInfo.DisplayNameComparator(this.mPkgMan));
        for (final ApplicationInfo applicationInfo2 : arrayList) {
            final String str = applicationInfo2.packageName;
            CharSequence charSequenceLoadLabel = applicationInfo2.loadLabel(this.mPkgMan);
            AppPreference appPreference = new AppPreference(getPrefContext());
            appPreference.setKey(str);
            appPreference.setIcon(applicationInfo2.loadIcon(this.mPkgMan));
            appPreference.setTitle(charSequenceLoadLabel);
            if (arraySet.contains(str)) {
                appPreference.setEnabled(false);
                appPreference.setSummary(getString(R.string.zen_access_disabled_package_warning));
            } else {
                appPreference.setSummary(getPreferenceSummary(str));
            }
            appPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ZenAccessSettings$rctO8bGaUorof25vHty7U44ejYk
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$reloadList$0$ZenAccessSettings(str, applicationInfo2, preference);
                }
            });
            preferenceScreen.addPreference(appPreference);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$reloadList$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$reloadList$0$ZenAccessSettings(String str, ApplicationInfo applicationInfo, Preference preference) {
        AppInfoBase.startAppInfoFragment(ZenAccessDetails.class, R.string.manage_zen_access_title, str, applicationInfo.uid, this, -1, getMetricsCategory());
        return true;
    }

    private int getPreferenceSummary(String str) {
        return ZenAccessController.hasAccess(getContext(), str) ? R.string.app_permission_summary_allowed : R.string.app_permission_summary_not_allowed;
    }
}
