package com.android.car.settings.applications.managedomainurls;

import android.app.Application;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import androidx.lifecycle.Lifecycle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class DomainAppPreferenceController extends PreferenceController<PreferenceGroup> {
    final ApplicationsState.Callbacks mApplicationStateCallbacks;
    private final ApplicationsState mApplicationsState;
    private final PackageManager mPm;
    private ApplicationsState.Session mSession;

    public DomainAppPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mApplicationStateCallbacks = new ApplicationsState.Callbacks() { // from class: com.android.car.settings.applications.managedomainurls.DomainAppPreferenceController.1
            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onRunningStateChanged(boolean running) {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageListChanged() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> apps) {
                DomainAppPreferenceController.this.rebuildAppList(apps);
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageIconChanged() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageSizeChanged(String packageName) {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onAllSizesComputed() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onLauncherInfoChanged() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onLoadEntriesCompleted() {
                DomainAppPreferenceController.this.mSession.rebuild(ApplicationsState.FILTER_WITH_DOMAIN_URLS, ApplicationsState.ALPHA_COMPARATOR);
            }
        };
        this.mApplicationsState = ApplicationsState.getInstance((Application) context.getApplicationContext());
        this.mPm = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mSession == null) {
            throw new IllegalStateException("session should be non null by this point");
        }
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.mSession = this.mApplicationsState.newSession(this.mApplicationStateCallbacks, lifecycle);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mSession.onResume();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mSession.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rebuildAppList(ArrayList<ApplicationsState.AppEntry> apps) {
        PreferenceGroup preferenceGroup = getPreference();
        preferenceGroup.removeAll();
        for (int i = 0; i < apps.size(); i++) {
            ApplicationsState.AppEntry entry = apps.get(i);
            preferenceGroup.addPreference(createPreference(entry));
        }
    }

    private Preference createPreference(final ApplicationsState.AppEntry entry) {
        String key = entry.info.packageName + "|" + entry.info.uid;
        IconDrawableFactory iconDrawableFactory = IconDrawableFactory.newInstance(getContext());
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setKey(key);
        preference.setTitle(entry.label);
        preference.setSummary(DomainUrlsUtils.getDomainsSummary(getContext(), entry.info.packageName, UserHandle.myUserId(), DomainUrlsUtils.getHandledDomains(this.mPm, entry.info.packageName)));
        preference.setIcon(iconDrawableFactory.getBadgedIcon(entry.info));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.applications.managedomainurls.-$$Lambda$DomainAppPreferenceController$t6Ry8C69bjqbWERS45XA0EWPTo8
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createPreference$0$DomainAppPreferenceController(entry, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createPreference$0$DomainAppPreferenceController(ApplicationsState.AppEntry entry, Preference pref) {
        getFragmentController().launchFragment(ApplicationLaunchSettingsFragment.newInstance(entry.info.packageName));
        return true;
    }
}
