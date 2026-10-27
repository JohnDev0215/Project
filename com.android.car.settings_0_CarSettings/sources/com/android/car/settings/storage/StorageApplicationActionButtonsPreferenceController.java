package com.android.car.settings.storage;

import android.app.ActivityManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.view.View;
import androidx.loader.app.LoaderManager;
import com.android.car.settings.R;
import com.android.car.settings.common.ActionButtonInfo;
import com.android.car.settings.common.ActionButtonsPreference;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.storage.AppsStorageStatsManager;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.StorageStatsSource;

/* JADX INFO: loaded from: classes5.dex */
public class StorageApplicationActionButtonsPreferenceController extends PreferenceController<ActionButtonsPreference> implements AppsStorageStatsManager.Callback {
    static final String CONFIRM_CANNOT_CLEAR_STORAGE_DIALOG_TAG = "com.android.car.settings.storage.ConfirmCannotClearStorageDialog";
    static final String CONFIRM_CLEAR_STORAGE_DIALOG_TAG = "com.android.car.settings.storage.ConfirmClearStorageDialog";
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";
    private static final Logger LOG = new Logger((Class<?>) StorageApplicationActionButtonsPreferenceController.class);
    private static final int MSG_CLEAR_CACHE = 2;
    private static final int MSG_CLEAR_USER_DATA = 1;
    private static final int OP_FAILED = 2;
    private static final int OP_SUCCESSFUL = 1;
    public static final int REQUEST_MANAGE_SPACE = 2;
    private ApplicationsState.AppEntry mAppEntry;
    private RestrictedLockUtils.EnforcedAdmin mAppsControlDisallowedAdmin;
    private boolean mAppsControlDisallowedBySystem;
    private AppsStorageStatsManager mAppsStorageStatsManager;
    private boolean mCacheCleared;
    private ActionButtonInfo mClearCacheButton;
    private ClearCacheObserver mClearCacheObserver;
    private ClearUserDataObserver mClearDataObserver;
    private ActionButtonInfo mClearStorageButton;
    private final ConfirmationDialogFragment.ConfirmListener mConfirmCannotClearStorageDialog;
    private final ConfirmationDialogFragment.ConfirmListener mConfirmClearStorageDialog;
    private boolean mDataCleared;
    private final Handler mHandler;
    private ApplicationInfo mInfo;
    private LoaderManager mLoaderManager;
    private PackageManager mPackageManager;
    private String mPackageName;
    private int mUserId;

    public /* synthetic */ void lambda$new$0$StorageApplicationActionButtonsPreferenceController(Bundle arguments) {
        initiateClearUserData();
    }

    public /* synthetic */ void lambda$new$1$StorageApplicationActionButtonsPreferenceController(Bundle arguments) {
        this.mClearStorageButton.setEnabled(false);
    }

    public StorageApplicationActionButtonsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmClearStorageDialog = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.storage.-$$Lambda$StorageApplicationActionButtonsPreferenceController$KvP-oTJztZQiNYLCJf5dZLo81vU
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$StorageApplicationActionButtonsPreferenceController(bundle);
            }
        };
        this.mConfirmCannotClearStorageDialog = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.storage.-$$Lambda$StorageApplicationActionButtonsPreferenceController$xmcy_6mXKpqcZ7VhANgnuHVaW48
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$1$StorageApplicationActionButtonsPreferenceController(bundle);
            }
        };
        this.mHandler = new Handler() { // from class: com.android.car.settings.storage.StorageApplicationActionButtonsPreferenceController.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                int i = msg.what;
                if (i == 1) {
                    StorageApplicationActionButtonsPreferenceController.this.mDataCleared = true;
                    StorageApplicationActionButtonsPreferenceController.this.mCacheCleared = true;
                    StorageApplicationActionButtonsPreferenceController.this.processClearMsg(msg);
                } else if (i == 2) {
                    StorageApplicationActionButtonsPreferenceController.this.mCacheCleared = true;
                    StorageApplicationActionButtonsPreferenceController.this.refreshUi();
                }
            }
        };
        this.mUserId = UserHandle.myUserId();
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ActionButtonsPreference> getPreferenceType() {
        return ActionButtonsPreference.class;
    }

    public StorageApplicationActionButtonsPreferenceController setAppEntry(ApplicationsState.AppEntry appEntry) {
        this.mAppEntry = appEntry;
        return this;
    }

    public StorageApplicationActionButtonsPreferenceController setPackageName(String packageName) {
        this.mPackageName = packageName;
        return this;
    }

    public StorageApplicationActionButtonsPreferenceController setAppsStorageStatsManager(AppsStorageStatsManager appsStorageStatsManager) {
        this.mAppsStorageStatsManager = appsStorageStatsManager;
        return this;
    }

    public StorageApplicationActionButtonsPreferenceController setLoaderManager(LoaderManager loaderManager) {
        this.mLoaderManager = loaderManager;
        return this;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAppEntry == null || this.mPackageName == null || this.mAppsStorageStatsManager == null || this.mLoaderManager == null) {
            throw new IllegalStateException("AppEntry, PackageName, AppStorageStatsManager, and LoaderManager should be set before calling this function");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mAppsStorageStatsManager.registerListener(this);
        this.mClearStorageButton = getPreference().getButton(ActionButtonsPreference.ActionButtons.BUTTON1);
        this.mClearCacheButton = getPreference().getButton(ActionButtonsPreference.ActionButtons.BUTTON2);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(CONFIRM_CLEAR_STORAGE_DIALOG_TAG), this.mConfirmClearStorageDialog, null, null);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(CONFIRM_CANNOT_CLEAR_STORAGE_DIALOG_TAG), this.mConfirmCannotClearStorageDialog, null, null);
        this.mClearStorageButton.setText(R.string.storage_clear_user_data_text).setIcon(R.drawable.ic_delete).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.storage.-$$Lambda$StorageApplicationActionButtonsPreferenceController$NhH4qDwknYa6gQtoWlElmkYORmM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateInternal$2$StorageApplicationActionButtonsPreferenceController(view);
            }
        }).setEnabled(false);
        this.mClearCacheButton.setText(R.string.storage_clear_cache_btn_text).setIcon(R.drawable.ic_delete).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.storage.-$$Lambda$StorageApplicationActionButtonsPreferenceController$EDyd6b5myfN1jnnEhZUD_n3VE_g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateInternal$3$StorageApplicationActionButtonsPreferenceController(view);
            }
        }).setEnabled(false);
    }

    public /* synthetic */ void lambda$onCreateInternal$2$StorageApplicationActionButtonsPreferenceController(View i) {
        handleClearDataClick();
    }

    public /* synthetic */ void lambda$onCreateInternal$3$StorageApplicationActionButtonsPreferenceController(View i) {
        handleClearCacheClick();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mAppsControlDisallowedAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_control_apps", this.mUserId);
        this.mAppsControlDisallowedBySystem = RestrictedLockUtilsInternal.hasBaseUserRestriction(getContext(), "no_control_apps", this.mUserId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ActionButtonsPreference preference) {
        try {
            this.mInfo = this.mPackageManager.getApplicationInfo(this.mPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e("Could not find package", e);
        }
        ApplicationInfo applicationInfo = this.mInfo;
        if (applicationInfo == null) {
            return;
        }
        this.mAppsStorageStatsManager.startLoading(this.mLoaderManager, applicationInfo, this.mUserId, this.mCacheCleared, this.mDataCleared);
    }

    @Override // com.android.car.settings.storage.AppsStorageStatsManager.Callback
    public void onDataLoaded(StorageStatsSource.AppStorageStats data, boolean cacheCleared, boolean dataCleared) {
        boolean z = false;
        if (data == null || this.mAppsControlDisallowedBySystem) {
            this.mClearStorageButton.setEnabled(false);
            this.mClearCacheButton.setEnabled(false);
            return;
        }
        long cacheSize = data.getCacheBytes();
        long dataSize = data.getDataBytes() - cacheSize;
        this.mClearStorageButton.setEnabled(dataSize > 0 && !this.mDataCleared);
        ActionButtonInfo actionButtonInfo = this.mClearCacheButton;
        if (cacheSize > 0 && !this.mCacheCleared) {
            z = true;
        }
        actionButtonInfo.setEnabled(z);
    }

    void setPackageManager(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    void setAppsControlDisallowedAdmin(RestrictedLockUtils.EnforcedAdmin admin) {
        this.mAppsControlDisallowedAdmin = admin;
    }

    void setAppsControlDisallowedBySystem(boolean disallowed) {
        this.mAppsControlDisallowedBySystem = disallowed;
    }

    private void handleClearCacheClick() {
        if (this.mAppsControlDisallowedAdmin != null && !this.mAppsControlDisallowedBySystem) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), this.mAppsControlDisallowedAdmin);
            return;
        }
        if (this.mClearCacheObserver == null) {
            this.mClearCacheObserver = new ClearCacheObserver();
        }
        this.mPackageManager.deleteApplicationCacheFiles(this.mPackageName, this.mClearCacheObserver);
    }

    private void handleClearDataClick() {
        if (this.mAppsControlDisallowedAdmin != null && !this.mAppsControlDisallowedBySystem) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), this.mAppsControlDisallowedAdmin);
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        boolean isManageSpaceActivityAvailable = false;
        if (this.mAppEntry.info.manageSpaceActivityName != null) {
            intent.setClassName(this.mAppEntry.info.packageName, this.mAppEntry.info.manageSpaceActivityName);
            isManageSpaceActivityAvailable = this.mPackageManager.resolveActivity(intent, 0) != null;
        }
        if (isManageSpaceActivityAvailable) {
            getFragmentController().startActivityForResult(intent, 2, null);
        } else {
            showClearDataDialog();
        }
    }

    private void initiateClearUserData() {
        this.mClearStorageButton.setEnabled(false);
        String packageName = this.mAppEntry.info.packageName;
        LOG.i("Clearing user data for package : " + packageName);
        if (this.mClearDataObserver == null) {
            this.mClearDataObserver = new ClearUserDataObserver();
        }
        ActivityManager am = (ActivityManager) getContext().getSystemService("activity");
        boolean res = am.clearApplicationUserData(packageName, this.mClearDataObserver);
        if (!res) {
            LOG.i("Couldn't clear application user data for package:" + packageName);
            showCannotClearDataDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processClearMsg(Message msg) {
        int result = msg.arg1;
        String packageName = this.mAppEntry.info.packageName;
        if (result != 1) {
            this.mClearStorageButton.setEnabled(true);
            return;
        }
        LOG.i("Cleared user data for package : " + packageName);
        refreshUi();
    }

    private void showClearDataDialog() {
        ConfirmationDialogFragment confirmClearStorageDialog = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.storage_clear_user_data_text).setMessage(getContext().getString(R.string.storage_clear_data_dlg_text)).setPositiveButton(R.string.okay, this.mConfirmClearStorageDialog).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
        getFragmentController().showDialog(confirmClearStorageDialog, CONFIRM_CLEAR_STORAGE_DIALOG_TAG);
    }

    private void showCannotClearDataDialog() {
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.storage_clear_data_dlg_title).setMessage(getContext().getString(R.string.storage_clear_failed_dlg_text)).setPositiveButton(R.string.okay, this.mConfirmCannotClearStorageDialog).build();
        getFragmentController().showDialog(dialogFragment, CONFIRM_CANNOT_CLEAR_STORAGE_DIALOG_TAG);
    }

    class ClearCacheObserver extends IPackageDataObserver.Stub {
        ClearCacheObserver() {
        }

        public void onRemoveCompleted(String packageName, boolean succeeded) {
            Message msg = StorageApplicationActionButtonsPreferenceController.this.mHandler.obtainMessage(2);
            msg.arg1 = succeeded ? 1 : 2;
            StorageApplicationActionButtonsPreferenceController.this.mHandler.sendMessage(msg);
        }
    }

    class ClearUserDataObserver extends IPackageDataObserver.Stub {
        ClearUserDataObserver() {
        }

        public void onRemoveCompleted(String packageName, boolean succeeded) {
            Message msg = StorageApplicationActionButtonsPreferenceController.this.mHandler.obtainMessage(1);
            msg.arg1 = succeeded ? 1 : 2;
            StorageApplicationActionButtonsPreferenceController.this.mHandler.sendMessage(msg);
        }
    }
}
