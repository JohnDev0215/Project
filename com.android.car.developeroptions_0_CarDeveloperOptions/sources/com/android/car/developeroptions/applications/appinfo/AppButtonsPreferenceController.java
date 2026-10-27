package com.android.car.developeroptions.applications.appinfo;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.OverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.applications.ApplicationFeatureProvider;
import com.android.car.developeroptions.applications.appinfo.ButtonActionDialogFragment;
import com.android.car.developeroptions.applications.specialaccess.deviceadmin.DeviceAdminAdd;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnDestroy;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.ActionButtonsPreference;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class AppButtonsPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume, OnDestroy, ApplicationsState.Callbacks {
    public static final String APP_CHG = "chg";
    private static final String KEY_ACTION_BUTTONS = "action_buttons";
    private static final boolean LOCAL_LOGV = false;
    private static final String TAG = "AppButtonsPrefCtl";
    private final SettingsActivity mActivity;
    ApplicationsState.AppEntry mAppEntry;
    private Intent mAppLaunchIntent;
    private final ApplicationFeatureProvider mApplicationFeatureProvider;
    private RestrictedLockUtils.EnforcedAdmin mAppsControlDisallowedAdmin;
    private boolean mAppsControlDisallowedBySystem;
    ActionButtonsPreference mButtonsPref;
    private final BroadcastReceiver mCheckKillProcessesReceiver;
    boolean mDisableAfterUninstall;
    private final DevicePolicyManager mDpm;
    private boolean mFinishing;
    private final InstrumentedPreferenceFragment mFragment;
    final HashSet<String> mHomePackages;
    private boolean mListeningToPackageRemove;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final OverlayManager mOverlayManager;
    PackageInfo mPackageInfo;
    String mPackageName;
    private final BroadcastReceiver mPackageRemovedReceiver;
    private final PackageManager mPm;
    private final int mRequestRemoveDeviceAdmin;
    private final int mRequestUninstall;
    private ApplicationsState.Session mSession;
    ApplicationsState mState;
    private boolean mUpdatedSysApp;
    private final int mUserId;
    private final UserManager mUserManager;

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

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_ACTION_BUTTONS;
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

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onAllSizesComputed() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLauncherInfoChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLoadEntriesCompleted() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageIconChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    /* JADX INFO: renamed from: onRebuildComplete */
    public void lambda$rebuild$2$ManageApplications$ApplicationsAdapter(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppButtonsPreferenceController(SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, Lifecycle lifecycle, String str, ApplicationsState applicationsState, int i, int i2) {
        super(settingsActivity, KEY_ACTION_BUTTONS);
        this.mHomePackages = new HashSet<>();
        this.mDisableAfterUninstall = LOCAL_LOGV;
        this.mUpdatedSysApp = LOCAL_LOGV;
        this.mListeningToPackageRemove = LOCAL_LOGV;
        this.mFinishing = LOCAL_LOGV;
        this.mCheckKillProcessesReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.applications.appinfo.AppButtonsPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                boolean z = getResultCode() != 0 ? true : AppButtonsPreferenceController.LOCAL_LOGV;
                Log.d(AppButtonsPreferenceController.TAG, "Got broadcast response: Restart status for " + AppButtonsPreferenceController.this.mAppEntry.info.packageName + " " + z);
                AppButtonsPreferenceController.this.updateForceStopButtonInner(z);
            }
        };
        this.mPackageRemovedReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.applications.appinfo.AppButtonsPreferenceController.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                if (AppButtonsPreferenceController.this.mFinishing || !AppButtonsPreferenceController.this.mAppEntry.info.packageName.equals(schemeSpecificPart)) {
                    return;
                }
                AppButtonsPreferenceController.this.mActivity.finishAndRemoveTask();
            }
        };
        if (!(instrumentedPreferenceFragment instanceof ButtonActionDialogFragment.AppButtonsDialogListener)) {
            throw new IllegalArgumentException("Fragment should implement AppButtonsDialogListener");
        }
        FeatureFactory factory = FeatureFactory.getFactory(settingsActivity);
        this.mMetricsFeatureProvider = factory.getMetricsFeatureProvider();
        this.mApplicationFeatureProvider = factory.getApplicationFeatureProvider(settingsActivity);
        this.mState = applicationsState;
        this.mDpm = (DevicePolicyManager) settingsActivity.getSystemService("device_policy");
        this.mUserManager = (UserManager) settingsActivity.getSystemService("user");
        this.mPm = settingsActivity.getPackageManager();
        this.mOverlayManager = (OverlayManager) settingsActivity.getSystemService(OverlayManager.class);
        this.mPackageName = str;
        this.mActivity = settingsActivity;
        this.mFragment = instrumentedPreferenceFragment;
        this.mUserId = UserHandle.myUserId();
        this.mRequestUninstall = i;
        this.mRequestRemoveDeviceAdmin = i2;
        this.mAppLaunchIntent = this.mPm.getLaunchIntentForPackage(this.mPackageName);
        if (str != null) {
            this.mAppEntry = this.mState.getEntry(str, this.mUserId);
            this.mSession = this.mState.newSession(this, lifecycle);
            lifecycle.addObserver(this);
            return;
        }
        this.mFinishing = true;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        return (appEntry == null || AppUtils.isInstant(appEntry.info)) ? 4 : 0;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            ActionButtonsPreference actionButtonsPreference = (ActionButtonsPreference) preferenceScreen.findPreference(KEY_ACTION_BUTTONS);
            actionButtonsPreference.setButton1Text(R.string.launch_instant_app);
            actionButtonsPreference.setButton1Icon(R.drawable.ic_settings_open);
            actionButtonsPreference.setButton1OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.appinfo.-$$Lambda$AppButtonsPreferenceController$QOVCZccmcL17NfXP_H2-ybBqKag
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$displayPreference$0$AppButtonsPreferenceController(view);
                }
            });
            actionButtonsPreference.setButton2Text(R.string.uninstall_text);
            actionButtonsPreference.setButton2Icon(R.drawable.ic_settings_delete);
            actionButtonsPreference.setButton2OnClickListener(new UninstallAndDisableButtonListener());
            actionButtonsPreference.setButton3Text(R.string.force_stop);
            actionButtonsPreference.setButton3Icon(R.drawable.ic_settings_force_stop);
            actionButtonsPreference.setButton3OnClickListener(new ForceStopButtonListener());
            actionButtonsPreference.setButton3Enabled(LOCAL_LOGV);
            this.mButtonsPref = actionButtonsPreference;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$AppButtonsPreferenceController(View view) {
        launchApplication();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (!isAvailable() || this.mFinishing) {
            return;
        }
        this.mAppsControlDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mActivity, "no_control_apps", this.mUserId);
        this.mAppsControlDisallowedAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this.mActivity, "no_control_apps", this.mUserId);
        if (refreshUi()) {
            return;
        }
        setIntentAndFinish(true);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnDestroy
    public void onDestroy() {
        stopListeningToPackageRemove();
    }

    private class UninstallAndDisableButtonListener implements View.OnClickListener {
        private UninstallAndDisableButtonListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppButtonsPreferenceController appButtonsPreferenceController = AppButtonsPreferenceController.this;
            String str = appButtonsPreferenceController.mAppEntry.info.packageName;
            if (appButtonsPreferenceController.mDpm.packageHasActiveAdmins(AppButtonsPreferenceController.this.mPackageInfo.packageName)) {
                AppButtonsPreferenceController.this.stopListeningToPackageRemove();
                Intent intent = new Intent(AppButtonsPreferenceController.this.mActivity, (Class<?>) DeviceAdminAdd.class);
                intent.putExtra("android.app.extra.DEVICE_ADMIN_PACKAGE_NAME", str);
                AppButtonsPreferenceController.this.mMetricsFeatureProvider.action(AppButtonsPreferenceController.this.mActivity, 873, new Pair[0]);
                AppButtonsPreferenceController.this.mFragment.startActivityForResult(intent, AppButtonsPreferenceController.this.mRequestRemoveDeviceAdmin);
                return;
            }
            RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfUninstallBlocked = RestrictedLockUtilsInternal.checkIfUninstallBlocked(AppButtonsPreferenceController.this.mActivity, str, AppButtonsPreferenceController.this.mUserId);
            boolean z = AppButtonsPreferenceController.this.mAppsControlDisallowedBySystem || RestrictedLockUtilsInternal.hasBaseUserRestriction(AppButtonsPreferenceController.this.mActivity, str, AppButtonsPreferenceController.this.mUserId);
            if (enforcedAdminCheckIfUninstallBlocked != null && !z) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(AppButtonsPreferenceController.this.mActivity, enforcedAdminCheckIfUninstallBlocked);
                return;
            }
            AppButtonsPreferenceController appButtonsPreferenceController2 = AppButtonsPreferenceController.this;
            ApplicationInfo applicationInfo = appButtonsPreferenceController2.mAppEntry.info;
            int i = applicationInfo.flags;
            if ((i & 1) == 0) {
                if ((8388608 & i) == 0) {
                    appButtonsPreferenceController2.uninstallPkg(str, true, AppButtonsPreferenceController.LOCAL_LOGV);
                    return;
                } else {
                    appButtonsPreferenceController2.uninstallPkg(str, AppButtonsPreferenceController.LOCAL_LOGV, AppButtonsPreferenceController.LOCAL_LOGV);
                    return;
                }
            }
            if (applicationInfo.enabled && !appButtonsPreferenceController2.isDisabledUntilUsed()) {
                if (!AppButtonsPreferenceController.this.mUpdatedSysApp || !AppButtonsPreferenceController.this.isSingleUser()) {
                    AppButtonsPreferenceController.this.showDialogInner(0);
                    return;
                } else {
                    AppButtonsPreferenceController.this.showDialogInner(1);
                    return;
                }
            }
            AppButtonsPreferenceController.this.mMetricsFeatureProvider.action(AppButtonsPreferenceController.this.mActivity, AppButtonsPreferenceController.this.mAppEntry.info.enabled ? 874 : 875, new Pair[0]);
            AppButtonsPreferenceController appButtonsPreferenceController3 = AppButtonsPreferenceController.this;
            AsyncTask.execute(new DisableChangerRunnable(appButtonsPreferenceController3, appButtonsPreferenceController3.mPm, AppButtonsPreferenceController.this.mAppEntry.info.packageName, 0));
        }
    }

    private class ForceStopButtonListener implements View.OnClickListener {
        private ForceStopButtonListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AppButtonsPreferenceController.this.mAppsControlDisallowedAdmin == null || AppButtonsPreferenceController.this.mAppsControlDisallowedBySystem) {
                AppButtonsPreferenceController.this.showDialogInner(2);
            } else {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(AppButtonsPreferenceController.this.mActivity, AppButtonsPreferenceController.this.mAppsControlDisallowedAdmin);
            }
        }
    }

    public void handleActivityResult(int i, int i2, Intent intent) {
        if (i == this.mRequestUninstall) {
            if (this.mDisableAfterUninstall) {
                this.mDisableAfterUninstall = LOCAL_LOGV;
                AsyncTask.execute(new DisableChangerRunnable(this, this.mPm, this.mAppEntry.info.packageName, 3));
            }
            refreshAndFinishIfPossible();
            return;
        }
        if (i == this.mRequestRemoveDeviceAdmin) {
            refreshAndFinishIfPossible();
        }
    }

    public void handleDialogClick(int i) {
        if (i == 0) {
            this.mMetricsFeatureProvider.action(this.mActivity, 874, new Pair[0]);
            AsyncTask.execute(new DisableChangerRunnable(this, this.mPm, this.mAppEntry.info.packageName, 3));
        } else if (i == 1) {
            this.mMetricsFeatureProvider.action(this.mActivity, 874, new Pair[0]);
            uninstallPkg(this.mAppEntry.info.packageName, LOCAL_LOGV, true);
        } else {
            if (i != 2) {
                return;
            }
            forceStopPackage(this.mAppEntry.info.packageName);
        }
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageListChanged() {
        if (isAvailable()) {
            refreshUi();
        }
    }

    void retrieveAppEntry() {
        ApplicationsState.AppEntry entry = this.mState.getEntry(this.mPackageName, this.mUserId);
        this.mAppEntry = entry;
        if (entry != null) {
            try {
                this.mPackageInfo = this.mPm.getPackageInfo(entry.info.packageName, 4198976);
                this.mPackageName = this.mAppEntry.info.packageName;
                return;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Exception when retrieving package:" + this.mAppEntry.info.packageName, e);
                this.mPackageInfo = null;
                return;
            }
        }
        this.mPackageInfo = null;
    }

    void updateOpenButton() {
        Intent launchIntentForPackage = this.mPm.getLaunchIntentForPackage(this.mPackageName);
        this.mAppLaunchIntent = launchIntentForPackage;
        this.mButtonsPref.setButton1Visible(launchIntentForPackage != null ? true : LOCAL_LOGV);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void updateUninstallButton() {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.applications.appinfo.AppButtonsPreferenceController.updateUninstallButton():void");
    }

    private void setIntentAndFinish(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(APP_CHG, z);
        this.mActivity.finishPreferencePanel(-1, intent);
        this.mFinishing = true;
    }

    private void refreshAndFinishIfPossible() {
        if (!refreshUi()) {
            setIntentAndFinish(true);
        } else {
            startListeningToPackageRemove();
        }
    }

    void updateForceStopButton() {
        if (this.mDpm.packageHasActiveAdmins(this.mPackageInfo.packageName)) {
            Log.w(TAG, "User can't force stop device admin");
            updateForceStopButtonInner(LOCAL_LOGV);
            return;
        }
        if ((this.mAppEntry.info.flags & 2097152) == 0) {
            Log.w(TAG, "App is not explicitly stopped");
            updateForceStopButtonInner(true);
            return;
        }
        Intent intent = new Intent("android.intent.action.QUERY_PACKAGE_RESTART", Uri.fromParts("package", this.mAppEntry.info.packageName, null));
        intent.putExtra("android.intent.extra.PACKAGES", new String[]{this.mAppEntry.info.packageName});
        intent.putExtra("android.intent.extra.UID", this.mAppEntry.info.uid);
        intent.putExtra("android.intent.extra.user_handle", UserHandle.getUserId(this.mAppEntry.info.uid));
        Log.d(TAG, "Sending broadcast to query restart status for " + this.mAppEntry.info.packageName);
        this.mActivity.sendOrderedBroadcastAsUser(intent, UserHandle.CURRENT, null, this.mCheckKillProcessesReceiver, null, 0, null, null);
    }

    void updateForceStopButtonInner(boolean z) {
        if (this.mAppsControlDisallowedBySystem) {
            this.mButtonsPref.setButton3Enabled(LOCAL_LOGV);
        } else {
            this.mButtonsPref.setButton3Enabled(z);
        }
    }

    void uninstallPkg(String str, boolean z, boolean z2) {
        stopListeningToPackageRemove();
        Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE", Uri.parse("package:" + str));
        intent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", z);
        this.mMetricsFeatureProvider.action(this.mActivity, 872, new Pair[0]);
        this.mFragment.startActivityForResult(intent, this.mRequestUninstall);
        this.mDisableAfterUninstall = z2;
    }

    void forceStopPackage(String str) {
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeatureProvider;
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(this.mActivity), 807, this.mFragment.getMetricsCategory(), str, 0);
        ActivityManager activityManager = (ActivityManager) this.mActivity.getSystemService("activity");
        Log.d(TAG, "Stopping package " + str);
        activityManager.forceStopPackage(str);
        int userId = UserHandle.getUserId(this.mAppEntry.info.uid);
        this.mState.invalidatePackage(str, userId);
        ApplicationsState.AppEntry entry = this.mState.getEntry(str, userId);
        if (entry != null) {
            this.mAppEntry = entry;
        }
        updateForceStopButton();
    }

    boolean handleDisableable() {
        if (this.mHomePackages.contains(this.mAppEntry.info.packageName) || isSystemPackage(this.mActivity.getResources(), this.mPm, this.mPackageInfo)) {
            ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
            actionButtonsPreference.setButton2Text(R.string.disable_text);
            actionButtonsPreference.setButton2Icon(R.drawable.ic_settings_disable);
            return LOCAL_LOGV;
        }
        if (this.mAppEntry.info.enabled && !isDisabledUntilUsed()) {
            ActionButtonsPreference actionButtonsPreference2 = this.mButtonsPref;
            actionButtonsPreference2.setButton2Text(R.string.disable_text);
            actionButtonsPreference2.setButton2Icon(R.drawable.ic_settings_disable);
            return true ^ this.mApplicationFeatureProvider.getKeepEnabledPackages().contains(this.mAppEntry.info.packageName);
        }
        ActionButtonsPreference actionButtonsPreference3 = this.mButtonsPref;
        actionButtonsPreference3.setButton2Text(R.string.enable_text);
        actionButtonsPreference3.setButton2Icon(R.drawable.ic_settings_enable);
        return true;
    }

    boolean isSystemPackage(Resources resources, PackageManager packageManager, PackageInfo packageInfo) {
        return Utils.isSystemPackage(resources, packageManager, packageInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDisabledUntilUsed() {
        if (this.mAppEntry.info.enabledSetting == 4) {
            return true;
        }
        return LOCAL_LOGV;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialogInner(int i) {
        ButtonActionDialogFragment buttonActionDialogFragmentNewInstance = ButtonActionDialogFragment.newInstance(i);
        buttonActionDialogFragmentNewInstance.setTargetFragment(this.mFragment, 0);
        buttonActionDialogFragmentNewInstance.show(this.mActivity.getSupportFragmentManager(), "dialog " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSingleUser() {
        int userCount = this.mUserManager.getUserCount();
        if (userCount == 1) {
            return true;
        }
        if (UserManager.isSplitSystemUser() && userCount == 2) {
            return true;
        }
        return LOCAL_LOGV;
    }

    private boolean signaturesMatch(String str, String str2) {
        if (str == null || str2 == null) {
            return LOCAL_LOGV;
        }
        try {
            if (this.mPm.checkSignatures(str, str2) >= 0) {
                return true;
            }
            return LOCAL_LOGV;
        } catch (Exception unused) {
            return LOCAL_LOGV;
        }
    }

    boolean refreshUi() {
        if (this.mPackageName == null) {
            return LOCAL_LOGV;
        }
        retrieveAppEntry();
        if (this.mAppEntry == null || this.mPackageInfo == null) {
            return LOCAL_LOGV;
        }
        ArrayList arrayList = new ArrayList();
        this.mPm.getHomeActivities(arrayList);
        this.mHomePackages.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = (ResolveInfo) arrayList.get(i);
            String str = resolveInfo.activityInfo.packageName;
            this.mHomePackages.add(str);
            Bundle bundle = resolveInfo.activityInfo.metaData;
            if (bundle != null) {
                String string = bundle.getString("android.app.home.alternate");
                if (signaturesMatch(string, str)) {
                    this.mHomePackages.add(string);
                }
            }
        }
        updateOpenButton();
        updateUninstallButton();
        updateForceStopButton();
        return true;
    }

    private void startListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            return;
        }
        this.mListeningToPackageRemove = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.mActivity.registerReceiver(this.mPackageRemovedReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = LOCAL_LOGV;
            this.mActivity.unregisterReceiver(this.mPackageRemovedReceiver);
        }
    }

    private void launchApplication() {
        Intent intent = this.mAppLaunchIntent;
        if (intent != null) {
            this.mContext.startActivityAsUser(intent, new UserHandle(this.mUserId));
        }
    }

    private class DisableChangerRunnable implements Runnable {
        final String mPackageName;
        final PackageManager mPm;
        final int mState;

        public DisableChangerRunnable(AppButtonsPreferenceController appButtonsPreferenceController, PackageManager packageManager, String str, int i) {
            this.mPm = packageManager;
            this.mPackageName = str;
            this.mState = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mPm.setApplicationEnabledSetting(this.mPackageName, this.mState, 0);
        }
    }
}
