package com.android.car.developeroptions.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.Bundle;
import androidx.core.text.BidiFormatter;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.apppreference.AppPreference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeAllBypassingAppsPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private ApplicationsState.Session mAppSession;
    private final ApplicationsState.Callbacks mAppSessionCallbacks;
    ApplicationsState mApplicationsState;
    private Fragment mHostFragment;
    private NotificationBackend mNotificationBackend;
    Context mPrefContext;
    PreferenceScreen mPreferenceScreen;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "zen_mode_bypassing_apps_category";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public ZenModeAllBypassingAppsPreferenceController(Context context, Application application, Fragment fragment) {
        this(context, application == null ? null : ApplicationsState.getInstance(application), fragment);
    }

    private ZenModeAllBypassingAppsPreferenceController(Context context, ApplicationsState applicationsState, Fragment fragment) {
        super(context);
        this.mNotificationBackend = new NotificationBackend();
        ApplicationsState.Callbacks callbacks = new ApplicationsState.Callbacks() { // from class: com.android.car.developeroptions.notification.ZenModeAllBypassingAppsPreferenceController.2
            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onAllSizesComputed() {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onRunningStateChanged(boolean z) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageListChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            /* JADX INFO: renamed from: onRebuildComplete */
            public void lambda$rebuild$2(ArrayList<ApplicationsState.AppEntry> arrayList) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList(arrayList);
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageIconChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageSizeChanged(String str) {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onLauncherInfoChanged() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onLoadEntriesCompleted() {
                ZenModeAllBypassingAppsPreferenceController.this.updateNotificationChannelList();
            }
        };
        this.mAppSessionCallbacks = callbacks;
        this.mApplicationsState = applicationsState;
        this.mHostFragment = fragment;
        if (applicationsState == null || fragment == null) {
            return;
        }
        this.mAppSession = applicationsState.newSession(callbacks, fragment.getLifecycle());
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mPreferenceScreen = preferenceScreen;
        this.mPrefContext = preferenceScreen.getContext();
        updateNotificationChannelList();
        super.displayPreference(preferenceScreen);
    }

    public void updateNotificationChannelList() {
        ArrayList<ApplicationsState.AppEntry> arrayListRebuild;
        ApplicationsState.Session session = this.mAppSession;
        if (session == null || (arrayListRebuild = session.rebuild(ApplicationsState.FILTER_ALL_ENABLED, ApplicationsState.ALPHA_COMPARATOR)) == null) {
            return;
        }
        updateNotificationChannelList(arrayListRebuild);
    }

    void updateNotificationChannelList(List<ApplicationsState.AppEntry> list) {
        if (this.mPreferenceScreen == null || list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (final ApplicationsState.AppEntry appEntry : list) {
            String str = appEntry.info.packageName;
            this.mApplicationsState.ensureIcon(appEntry);
            for (final NotificationChannel notificationChannel : this.mNotificationBackend.getNotificationChannelsBypassingDnd(str, appEntry.info.uid).getList()) {
                AppPreference appPreference = new AppPreference(this.mPrefContext);
                appPreference.setKey(str + "|" + notificationChannel.getId());
                appPreference.setTitle(BidiFormatter.getInstance().unicodeWrap(appEntry.label));
                appPreference.setIcon(appEntry.icon);
                appPreference.setSummary(BidiFormatter.getInstance().unicodeWrap(notificationChannel.getName()));
                appPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.ZenModeAllBypassingAppsPreferenceController.1
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public boolean onPreferenceClick(Preference preference) {
                        Bundle bundle = new Bundle();
                        bundle.putString("package", appEntry.info.packageName);
                        bundle.putInt("uid", appEntry.info.uid);
                        bundle.putString("android.provider.extra.CHANNEL_ID", notificationChannel.getId());
                        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(((AbstractPreferenceController) ZenModeAllBypassingAppsPreferenceController.this).mContext);
                        subSettingLauncher.setDestination(ChannelNotificationSettings.class.getName());
                        subSettingLauncher.setArguments(bundle);
                        subSettingLauncher.setTitleRes(R.string.notification_channel_title);
                        subSettingLauncher.setResultListener(ZenModeAllBypassingAppsPreferenceController.this.mHostFragment, 0);
                        subSettingLauncher.setSourceMetricsCategory(1589);
                        subSettingLauncher.launch();
                        return true;
                    }
                });
                arrayList.add(appPreference);
            }
            this.mPreferenceScreen.removeAll();
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mPreferenceScreen.addPreference((Preference) it.next());
                }
            }
        }
    }
}
