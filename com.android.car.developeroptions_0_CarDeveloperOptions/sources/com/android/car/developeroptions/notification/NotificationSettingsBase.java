package com.android.car.developeroptions.notification;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LifecycleObserver;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class NotificationSettingsBase extends DashboardFragment {
    private static final boolean DEBUG = Log.isLoggable("NotifiSettingsBase", 3);
    protected NotificationBackend.AppRow mAppRow;
    protected Bundle mArgs;
    protected NotificationChannel mChannel;
    protected NotificationChannelGroup mChannelGroup;
    protected Context mContext;
    protected Intent mIntent;
    protected boolean mListeningToPackageRemove;
    protected String mPkg;
    protected PackageInfo mPkgInfo;
    protected PackageManager mPm;
    protected RestrictedLockUtils.EnforcedAdmin mSuspendedAppsAdmin;
    protected int mUid;
    protected int mUserId;
    protected NotificationBackend mBackend = new NotificationBackend();
    protected boolean mShowLegacyChannelConfig = false;
    protected List<NotificationPreferenceController> mControllers = new ArrayList();
    protected List<Preference> mDynamicPreferences = new ArrayList();
    protected ImportanceListener mImportanceListener = new ImportanceListener();
    protected final BroadcastReceiver mPackageRemovedReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.notification.NotificationSettingsBase.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            PackageInfo packageInfo = NotificationSettingsBase.this.mPkgInfo;
            if (packageInfo == null || TextUtils.equals(packageInfo.packageName, schemeSpecificPart)) {
                if (NotificationSettingsBase.DEBUG) {
                    Log.d("NotifiSettingsBase", "Package (" + schemeSpecificPart + ") removed. RemovingNotificationSettingsBase.");
                }
                NotificationSettingsBase.this.onPackageRemoved();
            }
        }
    };
    protected Comparator<NotificationChannel> mChannelComparator = new Comparator() { // from class: com.android.car.developeroptions.notification.-$$Lambda$NotificationSettingsBase$eDQw7QGd3dRssRn0lR1L7ubyUiA
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return NotificationSettingsBase.lambda$new$0((NotificationChannel) obj, (NotificationChannel) obj2);
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        String stringExtra;
        int intExtra;
        super.onAttach(context);
        this.mContext = getActivity();
        this.mIntent = getActivity().getIntent();
        this.mArgs = getArguments();
        this.mPm = getPackageManager();
        NotificationManager.from(this.mContext);
        Bundle bundle = this.mArgs;
        if (bundle != null && bundle.containsKey("package")) {
            stringExtra = this.mArgs.getString("package");
        } else {
            stringExtra = this.mIntent.getStringExtra("android.provider.extra.APP_PACKAGE");
        }
        this.mPkg = stringExtra;
        Bundle bundle2 = this.mArgs;
        if (bundle2 != null && bundle2.containsKey("uid")) {
            intExtra = this.mArgs.getInt("uid");
        } else {
            intExtra = this.mIntent.getIntExtra("app_uid", -1);
        }
        this.mUid = intExtra;
        if (intExtra < 0) {
            try {
                this.mUid = this.mPm.getPackageUid(this.mPkg, 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        PackageInfo packageInfoFindPackageInfo = findPackageInfo(this.mPkg, this.mUid);
        this.mPkgInfo = packageInfoFindPackageInfo;
        if (packageInfoFindPackageInfo != null) {
            int userId = UserHandle.getUserId(this.mUid);
            this.mUserId = userId;
            this.mSuspendedAppsAdmin = RestrictedLockUtilsInternal.checkIfApplicationIsSuspended(this.mContext, this.mPkg, userId);
            loadChannel();
            loadAppRow();
            loadChannelGroup();
            collectConfigActivities();
            getSettingsLifecycle().addObserver((LifecycleObserver) use(HeaderPreferenceController.class));
            Iterator<NotificationPreferenceController> it = this.mControllers.iterator();
            while (it.hasNext()) {
                it.next().onResume(this.mAppRow, this.mChannel, this.mChannelGroup, this.mSuspendedAppsAdmin);
            }
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mIntent == null && this.mArgs == null) {
            Log.w("NotifiSettingsBase", "No intent");
            toastAndFinish();
        } else if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null) {
            Log.w("NotifiSettingsBase", "Missing package or uid or packageinfo");
            toastAndFinish();
        } else {
            startListeningToPackageRemove();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        stopListeningToPackageRemove();
        super.onDestroy();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null || this.mAppRow == null) {
            Log.w("NotifiSettingsBase", "Missing package or uid or packageinfo");
            finish();
            return;
        }
        loadAppRow();
        if (this.mAppRow == null) {
            Log.w("NotifiSettingsBase", "Can't load package");
            finish();
        } else {
            loadChannel();
            loadChannelGroup();
            collectConfigActivities();
        }
    }

    private void loadChannel() {
        Intent intent = getActivity().getIntent();
        String stringExtra = intent != null ? intent.getStringExtra("android.provider.extra.CHANNEL_ID") : null;
        if (stringExtra == null && intent != null) {
            Bundle bundleExtra = intent.getBundleExtra(":settings:show_fragment_args");
            stringExtra = bundleExtra != null ? bundleExtra.getString("android.provider.extra.CHANNEL_ID") : null;
        }
        this.mChannel = this.mBackend.getChannel(this.mPkg, this.mUid, stringExtra);
    }

    private void loadAppRow() {
        this.mAppRow = this.mBackend.loadAppRow(this.mContext, this.mPm, this.mPkgInfo);
    }

    private void loadChannelGroup() {
        NotificationChannelGroup group;
        NotificationChannel notificationChannel;
        NotificationBackend notificationBackend = this.mBackend;
        NotificationBackend.AppRow appRow = this.mAppRow;
        boolean z = notificationBackend.onlyHasDefaultChannel(appRow.pkg, appRow.uid) || ((notificationChannel = this.mChannel) != null && "miscellaneous".equals(notificationChannel.getId()));
        this.mShowLegacyChannelConfig = z;
        if (z) {
            NotificationBackend notificationBackend2 = this.mBackend;
            NotificationBackend.AppRow appRow2 = this.mAppRow;
            this.mChannel = notificationBackend2.getChannel(appRow2.pkg, appRow2.uid, "miscellaneous");
        }
        NotificationChannel notificationChannel2 = this.mChannel;
        if (notificationChannel2 == null || TextUtils.isEmpty(notificationChannel2.getGroup()) || (group = this.mBackend.getGroup(this.mPkg, this.mUid, this.mChannel.getGroup())) == null) {
            return;
        }
        this.mChannelGroup = group;
    }

    protected void toastAndFinish() {
        Toast.makeText(this.mContext, R.string.app_not_found_dlg_text, 0).show();
        getActivity().finish();
    }

    protected void collectConfigActivities() {
        Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.NOTIFICATION_PREFERENCES").setPackage(this.mAppRow.pkg);
        List<ResolveInfo> listQueryIntentActivities = this.mPm.queryIntentActivities(intent, 0);
        if (DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ");
            sb.append(listQueryIntentActivities.size());
            sb.append(" preference activities");
            sb.append(listQueryIntentActivities.size() == 0 ? " ;_;" : "");
            Log.d("NotifiSettingsBase", sb.toString());
        }
        Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            NotificationBackend.AppRow appRow = this.mAppRow;
            if (appRow.settingsIntent != null) {
                if (DEBUG) {
                    Log.d("NotifiSettingsBase", "Ignoring duplicate notification preference activity (" + activityInfo.name + ") for package " + activityInfo.packageName);
                }
            } else {
                appRow.settingsIntent = intent.setPackage(null).setClassName(activityInfo.packageName, activityInfo.name);
                NotificationChannel notificationChannel = this.mChannel;
                if (notificationChannel != null) {
                    this.mAppRow.settingsIntent.putExtra("android.intent.extra.CHANNEL_ID", notificationChannel.getId());
                }
                NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
                if (notificationChannelGroup != null) {
                    this.mAppRow.settingsIntent.putExtra("android.intent.extra.CHANNEL_GROUP_ID", notificationChannelGroup.getId());
                }
            }
        }
    }

    private PackageInfo findPackageInfo(String str, int i) {
        String[] packagesForUid;
        if (str != null && i >= 0 && (packagesForUid = this.mPm.getPackagesForUid(i)) != null && str != null) {
            for (String str2 : packagesForUid) {
                if (str.equals(str2)) {
                    try {
                        return this.mPm.getPackageInfo(str, 64);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w("NotifiSettingsBase", "Failed to load package " + str, e);
                    }
                }
            }
        }
        return null;
    }

    protected Preference populateSingleChannelPrefs(PreferenceGroup preferenceGroup, final NotificationChannel notificationChannel, boolean z) {
        ChannelSummaryPreference channelSummaryPreference = new ChannelSummaryPreference(getPrefContext());
        channelSummaryPreference.setCheckBoxEnabled(this.mSuspendedAppsAdmin == null && isChannelBlockable(notificationChannel) && isChannelConfigurable(notificationChannel) && !z);
        channelSummaryPreference.setKey(notificationChannel.getId());
        channelSummaryPreference.setTitle(notificationChannel.getName());
        channelSummaryPreference.setSummary(NotificationBackend.getSentSummary(this.mContext, this.mAppRow.sentByChannel.get(notificationChannel.getId()), false));
        channelSummaryPreference.setChecked(notificationChannel.getImportance() != 0);
        Bundle bundle = new Bundle();
        bundle.putInt("uid", this.mUid);
        bundle.putString("package", this.mPkg);
        bundle.putString("android.provider.extra.CHANNEL_ID", notificationChannel.getId());
        bundle.putBoolean("fromSettings", true);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
        subSettingLauncher.setDestination(ChannelNotificationSettings.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.notification_channel_title);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        channelSummaryPreference.setIntent(subSettingLauncher.toIntent());
        channelSummaryPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.notification.NotificationSettingsBase.1
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                notificationChannel.setImportance(((Boolean) obj).booleanValue() ? 2 : 0);
                notificationChannel.lockFields(4);
                NotificationSettingsBase notificationSettingsBase = NotificationSettingsBase.this;
                notificationSettingsBase.mBackend.updateChannel(notificationSettingsBase.mPkg, notificationSettingsBase.mUid, notificationChannel);
                return true;
            }
        });
        if (preferenceGroup.findPreference(channelSummaryPreference.getKey()) == null) {
            preferenceGroup.addPreference(channelSummaryPreference);
        }
        return channelSummaryPreference;
    }

    protected boolean isChannelConfigurable(NotificationChannel notificationChannel) {
        if (notificationChannel == null || this.mAppRow == null) {
            return false;
        }
        return !notificationChannel.getId().equals(this.mAppRow.lockedChannelId);
    }

    protected boolean isChannelBlockable(NotificationChannel notificationChannel) {
        NotificationBackend.AppRow appRow;
        if (notificationChannel == null || (appRow = this.mAppRow) == null) {
            return false;
        }
        if (appRow.systemApp) {
            return notificationChannel.isBlockable() || notificationChannel.getImportance() == 0;
        }
        return true;
    }

    protected boolean isChannelGroupBlockable(NotificationChannelGroup notificationChannelGroup) {
        NotificationBackend.AppRow appRow;
        if (notificationChannelGroup == null || (appRow = this.mAppRow) == null) {
            return false;
        }
        if (appRow.systemApp) {
            return notificationChannelGroup.isBlocked();
        }
        return true;
    }

    protected void setVisible(PreferenceGroup preferenceGroup, Preference preference, boolean z) {
        if ((preferenceGroup.findPreference(preference.getKey()) != null) == z) {
            return;
        }
        if (z) {
            preferenceGroup.addPreference(preference);
        } else {
            preferenceGroup.removePreference(preference);
        }
    }

    protected void startListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            return;
        }
        this.mListeningToPackageRemove = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        getContext().registerReceiver(this.mPackageRemovedReceiver, intentFilter);
    }

    protected void stopListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = false;
            getContext().unregisterReceiver(this.mPackageRemovedReceiver);
        }
    }

    protected void onPackageRemoved() {
        getActivity().finishAndRemoveTask();
    }

    static /* synthetic */ int lambda$new$0(NotificationChannel notificationChannel, NotificationChannel notificationChannel2) {
        if (notificationChannel.isDeleted() != notificationChannel2.isDeleted()) {
            return Boolean.compare(notificationChannel.isDeleted(), notificationChannel2.isDeleted());
        }
        if (notificationChannel.getId().equals("miscellaneous")) {
            return 1;
        }
        if (notificationChannel2.getId().equals("miscellaneous")) {
            return -1;
        }
        return notificationChannel.getId().compareTo(notificationChannel2.getId());
    }

    protected class ImportanceListener {
        protected ImportanceListener() {
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected void onImportanceChanged() {
            /*
                r7 = this;
                com.android.car.developeroptions.notification.NotificationSettingsBase r0 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                androidx.preference.PreferenceScreen r0 = r0.getPreferenceScreen()
                com.android.car.developeroptions.notification.NotificationSettingsBase r1 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                java.util.List<com.android.car.developeroptions.notification.NotificationPreferenceController> r1 = r1.mControllers
                java.util.Iterator r1 = r1.iterator()
            Le:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L1e
                java.lang.Object r2 = r1.next()
                com.android.car.developeroptions.notification.NotificationPreferenceController r2 = (com.android.car.developeroptions.notification.NotificationPreferenceController) r2
                r2.displayPreference(r0)
                goto Le
            L1e:
                com.android.car.developeroptions.notification.NotificationSettingsBase r0 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                com.android.car.developeroptions.notification.NotificationSettingsBase.access$100(r0)
                com.android.car.developeroptions.notification.NotificationSettingsBase r0 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                com.android.car.developeroptions.notification.NotificationBackend$AppRow r1 = r0.mAppRow
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L44
                boolean r1 = r1.banned
                if (r1 == 0) goto L30
                goto L44
            L30:
                android.app.NotificationChannel r1 = r0.mChannel
                if (r1 == 0) goto L3b
                int r0 = r1.getImportance()
                if (r0 != 0) goto L45
                goto L44
            L3b:
                android.app.NotificationChannelGroup r0 = r0.mChannelGroup
                if (r0 == 0) goto L45
                boolean r2 = r0.isBlocked()
                goto L45
            L44:
                r2 = r3
            L45:
                com.android.car.developeroptions.notification.NotificationSettingsBase r0 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                java.util.List<androidx.preference.Preference> r0 = r0.mDynamicPreferences
                java.util.Iterator r0 = r0.iterator()
            L4d:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L65
                java.lang.Object r1 = r0.next()
                androidx.preference.Preference r1 = (androidx.preference.Preference) r1
                com.android.car.developeroptions.notification.NotificationSettingsBase r4 = com.android.car.developeroptions.notification.NotificationSettingsBase.this
                androidx.preference.PreferenceScreen r5 = r4.getPreferenceScreen()
                r6 = r2 ^ 1
                r4.setVisible(r5, r1, r6)
                goto L4d
            L65:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.notification.NotificationSettingsBase.ImportanceListener.onImportanceChanged():void");
        }
    }
}
