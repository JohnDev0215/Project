package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppNotificationSettings extends NotificationSettingsBase {
    private static String KEY_ADVANCED_CATEGORY;
    private static String KEY_APP_LINK;
    private static String KEY_BADGE;
    private static String KEY_BUBBLE;
    private static String KEY_GENERAL_CATEGORY;
    private static String[] LEGACY_NON_ADVANCED_KEYS;
    private Comparator<NotificationChannelGroup> mChannelGroupComparator = new Comparator<NotificationChannelGroup>(this) { // from class: com.android.car.developeroptions.notification.AppNotificationSettings.2
        @Override // java.util.Comparator
        public int compare(NotificationChannelGroup notificationChannelGroup, NotificationChannelGroup notificationChannelGroup2) {
            if (notificationChannelGroup.getId() == null && notificationChannelGroup2.getId() != null) {
                return 1;
            }
            if (notificationChannelGroup2.getId() != null || notificationChannelGroup.getId() == null) {
                return notificationChannelGroup.getId().compareTo(notificationChannelGroup2.getId());
            }
            return -1;
        }
    };
    private List<NotificationChannelGroup> mChannelGroupList;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AppNotificationSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 72;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_notification_settings;
    }

    static {
        Log.isLoggable("AppNotificationSettings", 3);
        KEY_GENERAL_CATEGORY = "categories";
        KEY_ADVANCED_CATEGORY = "app_advanced";
        KEY_BADGE = "badge";
        KEY_APP_LINK = "app_link";
        KEY_BUBBLE = "bubble_link_pref";
        LEGACY_NON_ADVANCED_KEYS = new String[]{"badge", "app_link", "bubble_link_pref"};
    }

    @Override // com.android.car.developeroptions.notification.NotificationSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (!this.mShowLegacyChannelConfig || preferenceScreen == null) {
            return;
        }
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference(KEY_ADVANCED_CATEGORY);
        removePreference(KEY_ADVANCED_CATEGORY);
        if (preferenceGroup != null) {
            for (String str : LEGACY_NON_ADVANCED_KEYS) {
                Preference preferenceFindPreference = preferenceGroup.findPreference(str);
                preferenceGroup.removePreference(preferenceFindPreference);
                if (preferenceFindPreference != null) {
                    preferenceScreen.addPreference(preferenceFindPreference);
                }
            }
        }
    }

    @Override // com.android.car.developeroptions.notification.NotificationSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null) {
            Log.w("AppNotificationSettings", "Missing package or uid or packageinfo");
            finish();
            return;
        }
        if (!this.mShowLegacyChannelConfig) {
            new AsyncTask<Void, Void, Void>() { // from class: com.android.car.developeroptions.notification.AppNotificationSettings.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    AppNotificationSettings appNotificationSettings = AppNotificationSettings.this;
                    appNotificationSettings.mChannelGroupList = appNotificationSettings.mBackend.getGroups(appNotificationSettings.mPkg, appNotificationSettings.mUid).getList();
                    Collections.sort(AppNotificationSettings.this.mChannelGroupList, AppNotificationSettings.this.mChannelGroupComparator);
                    return null;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Void r1) {
                    if (AppNotificationSettings.this.getHost() == null) {
                        return;
                    }
                    AppNotificationSettings.this.populateList();
                }
            }.execute(new Void[0]);
        }
        for (NotificationPreferenceController notificationPreferenceController : this.mControllers) {
            notificationPreferenceController.onResume(this.mAppRow, this.mChannel, this.mChannelGroup, this.mSuspendedAppsAdmin);
            notificationPreferenceController.displayPreference(getPreferenceScreen());
        }
        updatePreferenceStates();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mControllers = arrayList;
        arrayList.add(new HeaderPreferenceController(context, this));
        this.mControllers.add(new BlockPreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new BadgePreferenceController(context, this.mBackend));
        this.mControllers.add(new AllowSoundPreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new ImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new MinImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new HighImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new SoundPreferenceController(context, this, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new LightsPreferenceController(context, this.mBackend));
        this.mControllers.add(new VibrationPreferenceController(context, this.mBackend));
        this.mControllers.add(new VisibilityPreferenceController(context, new LockPatternUtils(context), this.mBackend));
        this.mControllers.add(new DndPreferenceController(context, this.mBackend));
        this.mControllers.add(new AppLinkPreferenceController(context));
        this.mControllers.add(new DescriptionPreferenceController(context));
        this.mControllers.add(new NotificationsOffPreferenceController(context));
        this.mControllers.add(new DeletedChannelsPreferenceController(context, this.mBackend));
        return new ArrayList(this.mControllers);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateList() {
        if (!this.mDynamicPreferences.isEmpty()) {
            Iterator<Preference> it = this.mDynamicPreferences.iterator();
            while (it.hasNext()) {
                getPreferenceScreen().removePreference(it.next());
            }
            this.mDynamicPreferences.clear();
        }
        if (this.mChannelGroupList.isEmpty()) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(getPrefContext());
            preferenceCategory.setTitle(R.string.notification_channels);
            preferenceCategory.setKey(KEY_GENERAL_CATEGORY);
            getPreferenceScreen().addPreference(preferenceCategory);
            this.mDynamicPreferences.add(preferenceCategory);
            Preference preference = new Preference(getPrefContext());
            preference.setTitle(R.string.no_channels);
            preference.setEnabled(false);
            preferenceCategory.addPreference(preference);
            return;
        }
        populateGroupList();
        this.mImportanceListener.onImportanceChanged();
    }

    private void populateGroupList() {
        for (NotificationChannelGroup notificationChannelGroup : this.mChannelGroupList) {
            PreferenceCategory preferenceCategory = new PreferenceCategory(getPrefContext());
            preferenceCategory.setOrderingAsAdded(true);
            getPreferenceScreen().addPreference(preferenceCategory);
            this.mDynamicPreferences.add(preferenceCategory);
            if (notificationChannelGroup.getId() == null) {
                if (this.mChannelGroupList.size() > 1) {
                    preferenceCategory.setTitle(R.string.notification_channels_other);
                }
                preferenceCategory.setKey(KEY_GENERAL_CATEGORY);
            } else {
                preferenceCategory.setTitle(notificationChannelGroup.getName());
                preferenceCategory.setKey(notificationChannelGroup.getId());
                populateGroupToggle(preferenceCategory, notificationChannelGroup);
            }
            if (!notificationChannelGroup.isBlocked()) {
                List<NotificationChannel> channels = notificationChannelGroup.getChannels();
                Collections.sort(channels, this.mChannelComparator);
                int size = channels.size();
                for (int i = 0; i < size; i++) {
                    populateSingleChannelPrefs(preferenceCategory, channels.get(i), notificationChannelGroup.isBlocked());
                }
            }
        }
    }

    protected void populateGroupToggle(PreferenceGroup preferenceGroup, final NotificationChannelGroup notificationChannelGroup) {
        RestrictedSwitchPreference restrictedSwitchPreference = new RestrictedSwitchPreference(getPrefContext());
        restrictedSwitchPreference.setTitle(R.string.notification_switch_label);
        restrictedSwitchPreference.setEnabled(this.mSuspendedAppsAdmin == null && isChannelGroupBlockable(notificationChannelGroup));
        restrictedSwitchPreference.setChecked(!notificationChannelGroup.isBlocked());
        restrictedSwitchPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$AppNotificationSettings$D9g9Cr2bi3XMavUvzz8n-mrsDkE
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$populateGroupToggle$0$AppNotificationSettings(notificationChannelGroup, preference);
            }
        });
        preferenceGroup.addPreference(restrictedSwitchPreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$populateGroupToggle$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$populateGroupToggle$0$AppNotificationSettings(NotificationChannelGroup notificationChannelGroup, Preference preference) {
        notificationChannelGroup.setBlocked(!((SwitchPreference) preference).isChecked());
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        notificationBackend.updateChannelGroup(appRow.pkg, appRow.uid, notificationChannelGroup);
        onGroupBlockStateChanged(notificationChannelGroup);
        return true;
    }

    protected void onGroupBlockStateChanged(NotificationChannelGroup notificationChannelGroup) {
        PreferenceGroup preferenceGroup;
        if (notificationChannelGroup == null || (preferenceGroup = (PreferenceGroup) getPreferenceScreen().findPreference(notificationChannelGroup.getId())) == null) {
            return;
        }
        int i = 0;
        if (notificationChannelGroup.isBlocked()) {
            ArrayList arrayList = new ArrayList();
            int preferenceCount = preferenceGroup.getPreferenceCount();
            while (i < preferenceCount) {
                Preference preference = preferenceGroup.getPreference(i);
                if (preference instanceof ChannelSummaryPreference) {
                    arrayList.add(preference);
                }
                i++;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                preferenceGroup.removePreference((Preference) it.next());
            }
            return;
        }
        List<NotificationChannel> channels = notificationChannelGroup.getChannels();
        Collections.sort(channels, this.mChannelComparator);
        int size = channels.size();
        while (i < size) {
            populateSingleChannelPrefs(preferenceGroup, channels.get(i), notificationChannelGroup.isBlocked());
            i++;
        }
    }
}
