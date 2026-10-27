package com.android.car.developeroptions.applications.appinfo;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.applications.appinfo.ButtonActionDialogFragment;
import com.android.car.developeroptions.applications.specialaccess.pictureinpicture.PictureInPictureDetailPreferenceController;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppInfoDashboardFragment extends DashboardFragment implements ApplicationsState.Callbacks, ButtonActionDialogFragment.AppButtonsDialogListener {
    static final int REQUEST_UNINSTALL = 0;
    static final int UNINSTALL_ALL_USERS_MENU = 1;
    static final int UNINSTALL_UPDATES = 2;
    private AppButtonsPreferenceController mAppButtonsPreferenceController;
    private ApplicationsState.AppEntry mAppEntry;
    private RestrictedLockUtils.EnforcedAdmin mAppsControlDisallowedAdmin;
    private boolean mAppsControlDisallowedBySystem;
    private DevicePolicyManager mDpm;
    boolean mFinishing;
    private boolean mInitialized;
    private InstantAppButtonsPreferenceController mInstantAppButtonPreferenceController;
    private boolean mListeningToPackageRemove;
    private PackageInfo mPackageInfo;
    private String mPackageName;
    private PackageManager mPm;
    private boolean mShowUninstalled;
    private ApplicationsState mState;
    private int mUserId;
    private UserManager mUserManager;
    private boolean mUpdatedSysApp = false;
    private List<Callback> mCallbacks = new ArrayList();
    final BroadcastReceiver mPackageRemovedReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (AppInfoDashboardFragment.this.mFinishing) {
                return;
            }
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (AppInfoDashboardFragment.this.mAppEntry == null || AppInfoDashboardFragment.this.mAppEntry.info == null || TextUtils.equals(AppInfoDashboardFragment.this.mAppEntry.info.packageName, schemeSpecificPart)) {
                AppInfoDashboardFragment.this.onPackageRemoved();
            } else if (AppInfoDashboardFragment.this.mAppEntry.info.isResourceOverlay() && TextUtils.equals(AppInfoDashboardFragment.this.mPackageInfo.overlayTarget, schemeSpecificPart)) {
                AppInfoDashboardFragment.this.refreshUi();
            }
        }
    };

    public interface Callback {
        void refreshUi();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AppInfoDashboard";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 20;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_info_settings;
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
    /* JADX INFO: renamed from: onRebuildComplete */
    public void lambda$rebuild$2(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        String packageName = getPackageName();
        ((TimeSpentInAppPreferenceController) use(TimeSpentInAppPreferenceController.class)).setPackageName(packageName);
        ((AppDataUsagePreferenceController) use(AppDataUsagePreferenceController.class)).setParentFragment(this);
        AppInstallerInfoPreferenceController appInstallerInfoPreferenceController = (AppInstallerInfoPreferenceController) use(AppInstallerInfoPreferenceController.class);
        appInstallerInfoPreferenceController.setPackageName(packageName);
        appInstallerInfoPreferenceController.setParentFragment(this);
        ((AppInstallerPreferenceCategoryController) use(AppInstallerPreferenceCategoryController.class)).setChildren(Arrays.asList(appInstallerInfoPreferenceController));
        ((AppNotificationPreferenceController) use(AppNotificationPreferenceController.class)).setParentFragment(this);
        ((AppOpenByDefaultPreferenceController) use(AppOpenByDefaultPreferenceController.class)).setParentFragment(this);
        ((AppPermissionPreferenceController) use(AppPermissionPreferenceController.class)).setParentFragment(this);
        ((AppPermissionPreferenceController) use(AppPermissionPreferenceController.class)).setPackageName(packageName);
        ((AppSettingPreferenceController) use(AppSettingPreferenceController.class)).setPackageName(packageName).setParentFragment(this);
        ((AppStoragePreferenceController) use(AppStoragePreferenceController.class)).setParentFragment(this);
        ((AppVersionPreferenceController) use(AppVersionPreferenceController.class)).setParentFragment(this);
        ((InstantAppDomainsPreferenceController) use(InstantAppDomainsPreferenceController.class)).setParentFragment(this);
        WriteSystemSettingsPreferenceController writeSystemSettingsPreferenceController = (WriteSystemSettingsPreferenceController) use(WriteSystemSettingsPreferenceController.class);
        writeSystemSettingsPreferenceController.setParentFragment(this);
        DrawOverlayDetailPreferenceController drawOverlayDetailPreferenceController = (DrawOverlayDetailPreferenceController) use(DrawOverlayDetailPreferenceController.class);
        drawOverlayDetailPreferenceController.setParentFragment(this);
        PictureInPictureDetailPreferenceController pictureInPictureDetailPreferenceController = (PictureInPictureDetailPreferenceController) use(PictureInPictureDetailPreferenceController.class);
        pictureInPictureDetailPreferenceController.setPackageName(packageName);
        pictureInPictureDetailPreferenceController.setParentFragment(this);
        ExternalSourceDetailPreferenceController externalSourceDetailPreferenceController = (ExternalSourceDetailPreferenceController) use(ExternalSourceDetailPreferenceController.class);
        externalSourceDetailPreferenceController.setPackageName(packageName);
        externalSourceDetailPreferenceController.setParentFragment(this);
        ((AdvancedAppInfoPreferenceCategoryController) use(AdvancedAppInfoPreferenceCategoryController.class)).setChildren(Arrays.asList(writeSystemSettingsPreferenceController, drawOverlayDetailPreferenceController, pictureInPictureDetailPreferenceController, externalSourceDetailPreferenceController));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFinishing = false;
        FragmentActivity activity = getActivity();
        this.mDpm = (DevicePolicyManager) activity.getSystemService("device_policy");
        this.mUserManager = (UserManager) activity.getSystemService("user");
        this.mPm = activity.getPackageManager();
        if (ensurePackageInfoAvailable(activity) && ensureDisplayableModule(activity)) {
            startListeningToPackageRemove();
            setHasOptionsMenu(true);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        if (ensurePackageInfoAvailable(getActivity())) {
            super.onCreatePreferences(bundle, str);
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
        FragmentActivity activity = getActivity();
        this.mAppsControlDisallowedAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(activity, "no_control_apps", this.mUserId);
        this.mAppsControlDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(activity, "no_control_apps", this.mUserId);
        if (refreshUi()) {
            return;
        }
        setIntentAndFinish(true, true);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        retrieveAppEntry();
        if (this.mPackageInfo == null) {
            return null;
        }
        String packageName = getPackageName();
        ArrayList arrayList = new ArrayList();
        Lifecycle settingsLifecycle = getSettingsLifecycle();
        arrayList.add(new AppHeaderViewPreferenceController(context, this, packageName, settingsLifecycle));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mCallbacks.add((Callback) ((AbstractPreferenceController) it.next()));
        }
        InstantAppButtonsPreferenceController instantAppButtonsPreferenceController = new InstantAppButtonsPreferenceController(context, this, packageName, settingsLifecycle);
        this.mInstantAppButtonPreferenceController = instantAppButtonsPreferenceController;
        arrayList.add(instantAppButtonsPreferenceController);
        AppButtonsPreferenceController appButtonsPreferenceController = new AppButtonsPreferenceController((SettingsActivity) getActivity(), this, settingsLifecycle, packageName, this.mState, 0, 5);
        this.mAppButtonsPreferenceController = appButtonsPreferenceController;
        arrayList.add(appButtonsPreferenceController);
        arrayList.add(new AppBatteryPreferenceController(context, this, packageName, settingsLifecycle));
        arrayList.add(new AppMemoryPreferenceController(context, this, settingsLifecycle));
        arrayList.add(new DefaultHomeShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultBrowserShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultPhoneShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultEmergencyShortcutPreferenceController(context, packageName));
        arrayList.add(new DefaultSmsShortcutPreferenceController(context, packageName));
        return arrayList;
    }

    void addToCallbackList(Callback callback) {
        if (callback != null) {
            this.mCallbacks.add(callback);
        }
    }

    ApplicationsState.AppEntry getAppEntry() {
        return this.mAppEntry;
    }

    public PackageInfo getPackageInfo() {
        return this.mPackageInfo;
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
        if (!TextUtils.equals(str, this.mPackageName)) {
            Log.d("AppInfoDashboard", "Package change irrelevant, skipping");
        } else {
            refreshUi();
        }
    }

    boolean ensurePackageInfoAvailable(Activity activity) {
        if (this.mPackageInfo != null) {
            return true;
        }
        this.mFinishing = true;
        Log.w("AppInfoDashboard", "Package info not available. Is this package already uninstalled?");
        activity.finishAndRemoveTask();
        return false;
    }

    boolean ensureDisplayableModule(Activity activity) {
        if (!AppUtils.isHiddenSystemModule(activity.getApplicationContext(), this.mPackageName)) {
            return true;
        }
        this.mFinishing = true;
        Log.w("AppInfoDashboard", "Package is hidden module, exiting: " + this.mPackageName);
        activity.finishAndRemoveTask();
        return false;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menu.add(0, 2, 0, R.string.app_factory_reset).setShowAsAction(0);
        menu.add(0, 1, 1, R.string.uninstall_all_users_text).setShowAsAction(0);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        if (this.mFinishing) {
            return;
        }
        super.onPrepareOptionsMenu(menu);
        menu.findItem(1).setVisible(shouldShowUninstallForAll(this.mAppEntry));
        this.mUpdatedSysApp = (this.mAppEntry.info.flags & 128) != 0;
        MenuItem menuItemFindItem = menu.findItem(2);
        menuItemFindItem.setVisible(this.mUserManager.isAdminUser() && this.mUpdatedSysApp && !this.mAppsControlDisallowedBySystem && !getContext().getResources().getBoolean(R.bool.config_disable_uninstall_update));
        if (menuItemFindItem.isVisible()) {
            RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getActivity(), menuItemFindItem, this.mAppsControlDisallowedAdmin);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            uninstallPkg(this.mAppEntry.info.packageName, true, false);
            return true;
        }
        if (itemId == 2) {
            uninstallPkg(this.mAppEntry.info.packageName, false, false);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0) {
            getActivity().invalidateOptionsMenu();
        }
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleActivityResult(i, i2, intent);
        }
    }

    @Override // com.android.car.developeroptions.applications.appinfo.ButtonActionDialogFragment.AppButtonsDialogListener
    public void handleDialogClick(int i) {
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleDialogClick(i);
        }
    }

    boolean shouldShowUninstallForAll(ApplicationsState.AppEntry appEntry) {
        PackageInfo packageInfo;
        return !this.mUpdatedSysApp && appEntry != null && (appEntry.info.flags & 1) == 0 && (packageInfo = this.mPackageInfo) != null && !this.mDpm.packageHasActiveAdmins(packageInfo.packageName) && UserHandle.myUserId() == 0 && this.mUserManager.getUsers().size() >= 2 && (getNumberOfUserWithPackageInstalled(this.mPackageName) >= 2 || (appEntry.info.flags & 8388608) == 0) && !AppUtils.isInstant(appEntry.info);
    }

    boolean refreshUi() {
        retrieveAppEntry();
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry == null || this.mPackageInfo == null) {
            return false;
        }
        this.mState.ensureIcon(appEntry);
        Iterator<Callback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().refreshUi();
        }
        if (this.mAppButtonsPreferenceController.isAvailable()) {
            this.mAppButtonsPreferenceController.refreshUi();
        }
        if (!this.mInitialized) {
            this.mInitialized = true;
            this.mShowUninstalled = (this.mAppEntry.info.flags & 8388608) == 0;
        } else {
            try {
                ApplicationInfo applicationInfo = getActivity().getPackageManager().getApplicationInfo(this.mAppEntry.info.packageName, 4194816);
                if (!this.mShowUninstalled) {
                    return (applicationInfo.flags & 8388608) != 0;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return false;
            }
        }
        return true;
    }

    private void uninstallPkg(String str, boolean z, boolean z2) {
        stopListeningToPackageRemove();
        Intent intent = new Intent("android.intent.action.UNINSTALL_PACKAGE", Uri.parse("package:" + str));
        intent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", z);
        this.mMetricsFeatureProvider.action(getContext(), 872, new Pair[0]);
        startActivityForResult(intent, 0);
    }

    public static void startAppInfoFragment(Class<?> cls, int i, Bundle bundle, SettingsPreferenceFragment settingsPreferenceFragment, ApplicationsState.AppEntry appEntry) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("package", appEntry.info.packageName);
        bundle.putInt("uid", appEntry.info.uid);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(settingsPreferenceFragment.getContext());
        subSettingLauncher.setDestination(cls.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(i);
        subSettingLauncher.setResultListener(settingsPreferenceFragment, 1);
        subSettingLauncher.setSourceMetricsCategory(settingsPreferenceFragment.getMetricsCategory());
        subSettingLauncher.launch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPackageRemoved() {
        getActivity().finishActivity(1);
        getActivity().finishAndRemoveTask();
    }

    int getNumberOfUserWithPackageInstalled(String str) {
        int i = 0;
        for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
            try {
                if ((this.mPm.getApplicationInfoAsUser(str, 128, userInfo.id).flags & 8388608) != 0) {
                    i++;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("AppInfoDashboard", "Package: " + str + " not found for user: " + userInfo.id);
            }
        }
        return i;
    }

    private String getPackageName() {
        String str = this.mPackageName;
        if (str != null) {
            return str;
        }
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("package") : null;
        this.mPackageName = string;
        if (string == null) {
            Intent intent = arguments == null ? getActivity().getIntent() : (Intent) arguments.getParcelable("intent");
            if (intent != null) {
                this.mPackageName = intent.getData().getSchemeSpecificPart();
            }
        }
        return this.mPackageName;
    }

    void retrieveAppEntry() {
        FragmentActivity activity = getActivity();
        if (activity == null || this.mFinishing) {
            return;
        }
        if (this.mState == null) {
            ApplicationsState applicationsState = ApplicationsState.getInstance(activity.getApplication());
            this.mState = applicationsState;
            applicationsState.newSession(this, getSettingsLifecycle());
        }
        this.mUserId = UserHandle.myUserId();
        ApplicationsState.AppEntry entry = this.mState.getEntry(getPackageName(), UserHandle.myUserId());
        this.mAppEntry = entry;
        if (entry != null) {
            try {
                this.mPackageInfo = activity.getPackageManager().getPackageInfo(this.mAppEntry.info.packageName, 4198976);
                return;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("AppInfoDashboard", "Exception when retrieving package:" + this.mAppEntry.info.packageName, e);
                return;
            }
        }
        Log.w("AppInfoDashboard", "Missing AppEntry; maybe reinstalling?");
        this.mPackageInfo = null;
    }

    private void setIntentAndFinish(boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(AppButtonsPreferenceController.APP_CHG, z2);
        ((SettingsActivity) getActivity()).finishPreferencePanel(-1, intent);
        this.mFinishing = true;
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageListChanged() {
        if (refreshUi()) {
            return;
        }
        setIntentAndFinish(true, true);
    }

    void startListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            return;
        }
        this.mListeningToPackageRemove = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        getContext().registerReceiver(this.mPackageRemovedReceiver, intentFilter);
    }

    private void stopListeningToPackageRemove() {
        if (this.mListeningToPackageRemove) {
            this.mListeningToPackageRemove = false;
            getContext().unregisterReceiver(this.mPackageRemovedReceiver);
        }
    }
}
