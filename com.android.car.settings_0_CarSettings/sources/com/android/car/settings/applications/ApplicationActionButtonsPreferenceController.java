package com.android.car.settings.applications;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArraySet;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.ActionButtonInfo;
import com.android.car.settings.common.ActionButtonsPreference;
import com.android.car.settings.common.ActivityResultCallback;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.users.UserHelper;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.ApplicationsState;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationActionButtonsPreferenceController extends PreferenceController<ActionButtonsPreference> implements ActivityResultCallback {
    static final String DISABLE_CONFIRM_DIALOG_TAG = "com.android.car.settings.applications.DisableConfirmDialog";
    static final String FORCE_STOP_CONFIRM_DIALOG_TAG = "com.android.car.settings.applications.ForceStopConfirmDialog";
    private static final Logger LOG = new Logger((Class<?>) ApplicationActionButtonsPreferenceController.class);
    static final int UNINSTALL_REQUEST_CODE = 10;
    private ApplicationsState.AppEntry mAppEntry;
    private final ApplicationsState.Callbacks mApplicationStateCallbacks;
    private ApplicationsState mApplicationsState;
    final BroadcastReceiver mCheckKillProcessesReceiver;
    private final View.OnClickListener mDisableClickListener;
    final ConfirmationDialogFragment.ConfirmListener mDisableConfirmListener;
    private DevicePolicyManager mDpm;
    private final View.OnClickListener mEnableClickListener;
    private final View.OnClickListener mForceStopClickListener;
    final ConfirmationDialogFragment.ConfirmListener mForceStopConfirmListener;
    private PackageInfo mPackageInfo;
    private String mPackageName;
    private PackageManager mPm;
    private ApplicationsState.Session mSession;
    private final View.OnClickListener mUninstallClickListener;
    private UserHelper mUserHelper;
    private UserManager mUserManager;

    public /* synthetic */ void lambda$new$0$ApplicationActionButtonsPreferenceController(View i) {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.force_stop_dialog_title).setMessage(R.string.force_stop_dialog_text).setPositiveButton(android.R.string.ok, this.mForceStopConfirmListener).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
        getFragmentController().showDialog(dialogFragment, FORCE_STOP_CONFIRM_DIALOG_TAG);
    }

    public /* synthetic */ void lambda$new$1$ApplicationActionButtonsPreferenceController(View i) {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setMessage(getContext().getString(R.string.app_disable_dialog_text)).setPositiveButton(R.string.app_disable_dialog_positive, this.mDisableConfirmListener).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
        getFragmentController().showDialog(dialogFragment, DISABLE_CONFIRM_DIALOG_TAG);
    }

    public /* synthetic */ void lambda$new$2$ApplicationActionButtonsPreferenceController(View i) {
        this.mPm.setApplicationEnabledSetting(this.mPackageName, 0, 0);
        updateUninstallButtonInner(true);
    }

    public /* synthetic */ void lambda$new$3$ApplicationActionButtonsPreferenceController(View i) {
        Uri packageUri = Uri.parse("package:" + this.mPackageName);
        Intent uninstallIntent = new Intent("android.intent.action.UNINSTALL_PACKAGE", packageUri);
        uninstallIntent.putExtra("android.intent.extra.RETURN_RESULT", true);
        getFragmentController().startActivityForResult(uninstallIntent, 10, this);
    }

    public ApplicationActionButtonsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mForceStopConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.applications.ApplicationActionButtonsPreferenceController.1
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public void onConfirm(Bundle arguments) {
                ApplicationActionButtonsPreferenceController.LOG.d("Stopping package " + ApplicationActionButtonsPreferenceController.this.mPackageName);
                ((ActivityManager) ApplicationActionButtonsPreferenceController.this.getContext().getSystemService(ActivityManager.class)).forceStopPackage(ApplicationActionButtonsPreferenceController.this.mPackageName);
                int userId = UserHandle.getUserId(ApplicationActionButtonsPreferenceController.this.mAppEntry.info.uid);
                ApplicationActionButtonsPreferenceController.this.mApplicationsState.invalidatePackage(ApplicationActionButtonsPreferenceController.this.mPackageName, userId);
            }
        };
        this.mForceStopClickListener = new View.OnClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationActionButtonsPreferenceController$CnkfowL0EpoQOwJmOSk1P-dSPx8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$ApplicationActionButtonsPreferenceController(view);
            }
        };
        this.mCheckKillProcessesReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.applications.ApplicationActionButtonsPreferenceController.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                boolean enabled = getResultCode() != 0;
                ApplicationActionButtonsPreferenceController.LOG.d("Got broadcast response: Restart status for " + ApplicationActionButtonsPreferenceController.this.mPackageName + " " + enabled);
                ApplicationActionButtonsPreferenceController.this.updateForceStopButtonInner(enabled);
            }
        };
        this.mDisableConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.applications.ApplicationActionButtonsPreferenceController.3
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public void onConfirm(Bundle arguments) {
                ApplicationActionButtonsPreferenceController.this.mPm.setApplicationEnabledSetting(ApplicationActionButtonsPreferenceController.this.mPackageName, 3, 0);
                ApplicationActionButtonsPreferenceController.this.updateUninstallButtonInner(false);
            }
        };
        this.mDisableClickListener = new View.OnClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationActionButtonsPreferenceController$XYYlmVgonz5KUlBKOFe7dR_uB6Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1$ApplicationActionButtonsPreferenceController(view);
            }
        };
        this.mEnableClickListener = new View.OnClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationActionButtonsPreferenceController$hBsGe4J1F7J0kFZ9bGqgSl5_3Vs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$2$ApplicationActionButtonsPreferenceController(view);
            }
        };
        this.mUninstallClickListener = new View.OnClickListener() { // from class: com.android.car.settings.applications.-$$Lambda$ApplicationActionButtonsPreferenceController$foDc4NM7BTXSKEv0_1ljdKFIggQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$3$ApplicationActionButtonsPreferenceController(view);
            }
        };
        this.mApplicationStateCallbacks = new ApplicationsState.Callbacks() { // from class: com.android.car.settings.applications.ApplicationActionButtonsPreferenceController.4
            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onRunningStateChanged(boolean running) {
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onPackageListChanged() {
                ApplicationActionButtonsPreferenceController.this.refreshUi();
            }

            @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
            public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> apps) {
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
            }
        };
        this.mDpm = (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class);
        this.mPm = context.getPackageManager();
        this.mUserManager = UserManager.get(context);
        this.mUserHelper = UserHelper.getInstance(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ActionButtonsPreference> getPreferenceType() {
        return ActionButtonsPreference.class;
    }

    public ApplicationActionButtonsPreferenceController setAppEntry(ApplicationsState.AppEntry appEntry) {
        this.mAppEntry = appEntry;
        return this;
    }

    public ApplicationActionButtonsPreferenceController setAppState(ApplicationsState applicationsState) {
        this.mApplicationsState = applicationsState;
        return this;
    }

    public ApplicationActionButtonsPreferenceController setPackageName(String packageName) {
        this.mPackageName = packageName;
        return this;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAppEntry == null || this.mApplicationsState == null || this.mPackageName == null) {
            throw new IllegalStateException("AppEntry, AppState, and PackageName should be set before calling this function");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(DISABLE_CONFIRM_DIALOG_TAG), this.mDisableConfirmListener, null, null);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(FORCE_STOP_CONFIRM_DIALOG_TAG), this.mForceStopConfirmListener, null, null);
        getPreference().getButton(ActionButtonsPreference.ActionButtons.BUTTON2).setText(R.string.force_stop).setIcon(R.drawable.ic_warning).setOnClickListener(this.mForceStopClickListener).setEnabled(false);
        this.mSession = this.mApplicationsState.newSession(this.mApplicationStateCallbacks);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mSession.onResume();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mSession.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ActionButtonsPreference preference) {
        refreshAppEntry();
        if (this.mAppEntry == null) {
            getFragmentController().goBack();
        } else {
            updateForceStopButton();
            updateUninstallButton();
        }
    }

    private void refreshAppEntry() {
        ApplicationsState.AppEntry entry = this.mApplicationsState.getEntry(this.mPackageName, UserHandle.myUserId());
        this.mAppEntry = entry;
        if (entry != null) {
            try {
                this.mPackageInfo = this.mPm.getPackageInfo(this.mPackageName, 4198976);
                return;
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("Exception when retrieving package:" + this.mPackageName, e);
                this.mPackageInfo = null;
                return;
            }
        }
        this.mPackageInfo = null;
    }

    private void updateForceStopButton() {
        if (this.mDpm.packageHasActiveAdmins(this.mPackageName)) {
            updateForceStopButtonInner(false);
            return;
        }
        if ((this.mAppEntry.info.flags & 2097152) == 0) {
            updateForceStopButtonInner(true);
            return;
        }
        Intent intent = new Intent("android.intent.action.QUERY_PACKAGE_RESTART", Uri.fromParts("package", this.mPackageName, null));
        intent.putExtra("android.intent.extra.PACKAGES", new String[]{this.mPackageName});
        intent.putExtra("android.intent.extra.UID", this.mAppEntry.info.uid);
        intent.putExtra("android.intent.extra.user_handle", UserHandle.getUserId(this.mAppEntry.info.uid));
        LOG.d("Sending broadcast to query restart status for " + this.mPackageName);
        getContext().sendOrderedBroadcastAsUser(intent, UserHandle.CURRENT, null, this.mCheckKillProcessesReceiver, null, 0, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForceStopButtonInner(boolean enabled) {
        getPreference().getButton(ActionButtonsPreference.ActionButtons.BUTTON2).setEnabled(enabled && !this.mUserManager.hasUserRestriction("no_control_apps"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUninstallButtonInner(boolean isAppEnabled) {
        ActionButtonInfo uninstallButton = getPreference().getButton(ActionButtonsPreference.ActionButtons.BUTTON1);
        if (isBundledApp()) {
            if (isAppEnabled) {
                uninstallButton.setText(R.string.disable_text).setIcon(R.drawable.ic_block).setOnClickListener(this.mDisableClickListener);
            } else {
                uninstallButton.setText(R.string.enable_text).setIcon(R.drawable.ic_check_circle).setOnClickListener(this.mEnableClickListener);
            }
        } else {
            uninstallButton.setText(R.string.uninstall_text).setIcon(R.drawable.ic_delete).setOnClickListener(this.mUninstallClickListener);
        }
        uninstallButton.setEnabled(!shouldDisableUninstallButton());
    }

    private void updateUninstallButton() {
        updateUninstallButtonInner(isAppEnabled());
    }

    private boolean shouldDisableUninstallButton() {
        if (shouldDisableUninstallForHomeApp()) {
            LOG.d("Uninstall disabled for home app");
            return true;
        }
        if (isAppEnabled() && ApplicationsUtils.isKeepEnabledPackage(getContext(), this.mPackageName)) {
            LOG.d("Disable button disabled for keep enabled package");
            return true;
        }
        if (Utils.isSystemPackage(getContext().getResources(), this.mPm, this.mPackageInfo)) {
            LOG.d("Uninstall disabled for system package");
            return true;
        }
        if (this.mDpm.packageHasActiveAdmins(this.mPackageName)) {
            LOG.d("Uninstall disabled because package has active admins");
            return true;
        }
        if (ApplicationsUtils.isProfileOrDeviceOwner(this.mPackageName, this.mDpm, this.mUserHelper)) {
            LOG.d("Uninstall disabled because package is profile or device owner");
            return true;
        }
        if (this.mDpm.isUninstallInQueue(this.mPackageName)) {
            LOG.d("Uninstall disabled because intent is already queued");
            return true;
        }
        if (this.mUserManager.hasUserRestriction("no_control_apps")) {
            LOG.d("Uninstall disabled because user has DISALLOW_APPS_CONTROL restriction");
            return true;
        }
        if (this.mUserManager.hasUserRestriction("no_uninstall_apps")) {
            LOG.d("Uninstall disabled because user has DISALLOW_UNINSTALL_APPS restriction");
            return true;
        }
        return false;
    }

    private boolean shouldDisableUninstallForHomeApp() {
        Set<String> homePackages = new ArraySet<>();
        List<ResolveInfo> homeActivities = new ArrayList<>();
        ComponentName currentDefaultHome = this.mPm.getHomeActivities(homeActivities);
        for (int i = 0; i < homeActivities.size(); i++) {
            ResolveInfo ri = homeActivities.get(i);
            String activityPkg = ri.activityInfo.packageName;
            homePackages.add(activityPkg);
            Bundle metadata = ri.activityInfo.metaData;
            if (metadata != null) {
                String metaPkg = metadata.getString("android.app.home.alternate");
                if (signaturesMatch(metaPkg, activityPkg)) {
                    homePackages.add(metaPkg);
                }
            }
        }
        if (!homePackages.contains(this.mPackageName)) {
            return false;
        }
        if (isBundledApp()) {
            return true;
        }
        if (currentDefaultHome == null) {
            return homePackages.size() == 1;
        }
        return this.mPackageName.equals(currentDefaultHome.getPackageName());
    }

    private boolean signaturesMatch(String pkg1, String pkg2) {
        if (pkg1 != null && pkg2 != null) {
            try {
                int match = this.mPm.checkSignatures(pkg1, pkg2);
                if (match >= 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private boolean isBundledApp() {
        return (this.mAppEntry.info.flags & 1) != 0;
    }

    private boolean isAppEnabled() {
        return this.mAppEntry.info.enabled && this.mAppEntry.info.enabledSetting != 4;
    }

    @Override // com.android.car.settings.common.ActivityResultCallback
    public void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (resultCode == -1) {
                getFragmentController().goBack();
                return;
            }
            LOG.e("Uninstall failed with result " + resultCode);
        }
    }
}
