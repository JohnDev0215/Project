package com.android.car.developeroptions.applications;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.app.GrantedUriPermission;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.util.MutableInt;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.AppStorageSizesController;
import com.android.car.developeroptions.deviceinfo.StorageWizardMoveConfirm;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.widget.ActionButtonsPreference;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class AppStorageSettings extends AppInfoWithHeader implements View.OnClickListener, ApplicationsState.Callbacks, DialogInterface.OnClickListener, LoaderManager.LoaderCallbacks<StorageStatsSource.AppStorageStats> {
    private static final String TAG = AppStorageSettings.class.getSimpleName();
    ActionButtonsPreference mButtonsPref;
    private boolean mCacheCleared;
    private VolumeInfo[] mCandidates;
    private Button mChangeStorageButton;
    private ClearCacheObserver mClearCacheObserver;
    private ClearUserDataObserver mClearDataObserver;
    private LayoutPreference mClearUri;
    private Button mClearUriButton;
    private boolean mDataCleared;
    private AlertDialog.Builder mDialogBuilder;
    private ApplicationInfo mInfo;
    AppStorageSizesController mSizeController;
    private Preference mStorageUsed;
    private PreferenceCategory mUri;
    private boolean mCanClearData = true;
    private final Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.applications.AppStorageSettings.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (AppStorageSettings.this.getView() == null) {
                return;
            }
            int i = message.what;
            if (i == 1) {
                AppStorageSettings.this.mDataCleared = true;
                AppStorageSettings.this.mCacheCleared = true;
                AppStorageSettings.this.processClearMsg(message);
            } else {
                if (i != 3) {
                    return;
                }
                AppStorageSettings.this.mCacheCleared = true;
                AppStorageSettings.this.updateSize();
            }
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 19;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<StorageStatsSource.AppStorageStats> loader) {
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase, com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mCacheCleared = bundle.getBoolean("cache_cleared", false);
            boolean z = bundle.getBoolean("data_cleared", false);
            this.mDataCleared = z;
            this.mCacheCleared = this.mCacheCleared || z;
        }
        addPreferencesFromResource(R.xml.app_storage_settings);
        setupViews();
        initMoveDialog();
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateSize();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("cache_cleared", this.mCacheCleared);
        bundle.putBoolean("data_cleared", this.mDataCleared);
    }

    private void setupViews() {
        AppStorageSizesController.Builder builder = new AppStorageSizesController.Builder();
        builder.setTotalSizePreference(findPreference("total_size"));
        builder.setAppSizePreference(findPreference("app_size"));
        builder.setDataSizePreference(findPreference("data_size"));
        builder.setCacheSizePreference(findPreference("cache_size"));
        builder.setComputingString(R.string.computing_size);
        builder.setErrorString(R.string.invalid_size_value);
        this.mSizeController = builder.build();
        this.mButtonsPref = (ActionButtonsPreference) findPreference("header_view");
        this.mStorageUsed = findPreference("storage_used");
        Button button = (Button) ((LayoutPreference) findPreference("change_storage_button")).findViewById(R.id.button);
        this.mChangeStorageButton = button;
        button.setText(R.string.change);
        this.mChangeStorageButton.setOnClickListener(this);
        ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
        actionButtonsPreference.setButton2Text(R.string.clear_cache_btn_text);
        actionButtonsPreference.setButton2Icon(R.drawable.ic_settings_delete);
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("uri_category");
        this.mUri = preferenceCategory;
        LayoutPreference layoutPreference = (LayoutPreference) preferenceCategory.findPreference("clear_uri_button");
        this.mClearUri = layoutPreference;
        Button button2 = (Button) layoutPreference.findViewById(R.id.button);
        this.mClearUriButton = button2;
        button2.setText(R.string.clear_uri_btn_text);
        this.mClearUriButton.setOnClickListener(this);
    }

    void handleClearCacheClick() {
        if (this.mAppsControlDisallowedAdmin != null && !this.mAppsControlDisallowedBySystem) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), this.mAppsControlDisallowedAdmin);
            return;
        }
        if (this.mClearCacheObserver == null) {
            this.mClearCacheObserver = new ClearCacheObserver();
        }
        this.mMetricsFeatureProvider.action(getContext(), 877, new Pair[0]);
        this.mPm.deleteApplicationCacheFiles(this.mPackageName, this.mClearCacheObserver);
    }

    void handleClearDataClick() {
        if (this.mAppsControlDisallowedAdmin != null && !this.mAppsControlDisallowedBySystem) {
            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), this.mAppsControlDisallowedAdmin);
            return;
        }
        if (this.mAppEntry.info.manageSpaceActivityName != null) {
            if (Utils.isMonkeyRunning()) {
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            ApplicationInfo applicationInfo = this.mAppEntry.info;
            intent.setClassName(applicationInfo.packageName, applicationInfo.manageSpaceActivityName);
            startActivityForResult(intent, 2);
            return;
        }
        showDialogInner(1, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mChangeStorageButton && this.mDialogBuilder != null && !isMoveInProgress()) {
            this.mDialogBuilder.show();
            return;
        }
        if (view == this.mClearUriButton) {
            if (this.mAppsControlDisallowedAdmin != null && !this.mAppsControlDisallowedBySystem) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), this.mAppsControlDisallowedAdmin);
            } else {
                clearUriPermissions();
            }
        }
    }

    private boolean isMoveInProgress() {
        try {
            AppGlobals.getPackageManager().checkPackageStartable(this.mPackageName, UserHandle.myUserId());
            return false;
        } catch (RemoteException | SecurityException unused) {
            return true;
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        FragmentActivity activity = getActivity();
        VolumeInfo volumeInfo = this.mCandidates[i];
        if (!Objects.equals(volumeInfo, activity.getPackageManager().getPackageCurrentVolume(this.mAppEntry.info))) {
            Intent intent = new Intent(activity, (Class<?>) StorageWizardMoveConfirm.class);
            intent.putExtra("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
            intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mAppEntry.info.packageName);
            startActivity(intent);
        }
        dialogInterface.dismiss();
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase
    protected boolean refreshUi() {
        retrieveAppEntry();
        if (this.mAppEntry == null) {
            return false;
        }
        updateUiWithSize(this.mSizeController.getLastResult());
        refreshGrantedUriPermissions();
        VolumeInfo packageCurrentVolume = getActivity().getPackageManager().getPackageCurrentVolume(this.mAppEntry.info);
        this.mStorageUsed.setSummary(((StorageManager) getContext().getSystemService(StorageManager.class)).getBestVolumeDescription(packageCurrentVolume));
        refreshButtons();
        return true;
    }

    private void refreshButtons() {
        initMoveDialog();
        initDataButtons();
    }

    private void initDataButtons() {
        boolean z = this.mAppEntry.info.manageSpaceActivityName != null;
        boolean z2 = ((this.mAppEntry.info.flags & 65) == 1) || this.mDpm.packageHasActiveAdmins(this.mPackageName);
        Intent intent = new Intent("android.intent.action.VIEW");
        if (z) {
            ApplicationInfo applicationInfo = this.mAppEntry.info;
            intent.setClassName(applicationInfo.packageName, applicationInfo.manageSpaceActivityName);
        }
        boolean z3 = getPackageManager().resolveActivity(intent, 0) != null;
        if ((!z && z2) || !z3) {
            ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
            actionButtonsPreference.setButton1Text(R.string.clear_user_data_text);
            actionButtonsPreference.setButton1Icon(R.drawable.ic_settings_delete);
            actionButtonsPreference.setButton1Enabled(false);
            this.mCanClearData = false;
        } else {
            if (z) {
                this.mButtonsPref.setButton1Text(R.string.manage_space_text);
            } else {
                ActionButtonsPreference actionButtonsPreference2 = this.mButtonsPref;
                actionButtonsPreference2.setButton1Text(R.string.clear_user_data_text);
                actionButtonsPreference2.setButton1Icon(R.drawable.ic_settings_delete);
            }
            ActionButtonsPreference actionButtonsPreference3 = this.mButtonsPref;
            actionButtonsPreference3.setButton1Text(R.string.clear_user_data_text);
            actionButtonsPreference3.setButton1Icon(R.drawable.ic_settings_delete);
            actionButtonsPreference3.setButton1OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$AppStorageSettings$6c0TjJ4smGs6ml2KhWqoYlfCBtk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initDataButtons$0$AppStorageSettings(view);
                }
            });
        }
        if (this.mAppsControlDisallowedBySystem) {
            this.mButtonsPref.setButton1Enabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initDataButtons$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initDataButtons$0$AppStorageSettings(View view) {
        handleClearDataClick();
    }

    private void initMoveDialog() {
        FragmentActivity activity = getActivity();
        StorageManager storageManager = (StorageManager) activity.getSystemService(StorageManager.class);
        List packageCandidateVolumes = activity.getPackageManager().getPackageCandidateVolumes(this.mAppEntry.info);
        if (packageCandidateVolumes.size() > 1) {
            Collections.sort(packageCandidateVolumes, VolumeInfo.getDescriptionComparator());
            CharSequence[] charSequenceArr = new CharSequence[packageCandidateVolumes.size()];
            int i = -1;
            for (int i2 = 0; i2 < packageCandidateVolumes.size(); i2++) {
                String bestVolumeDescription = storageManager.getBestVolumeDescription((VolumeInfo) packageCandidateVolumes.get(i2));
                if (Objects.equals(bestVolumeDescription, this.mStorageUsed.getSummary())) {
                    i = i2;
                }
                charSequenceArr[i2] = bestVolumeDescription;
            }
            this.mCandidates = (VolumeInfo[]) packageCandidateVolumes.toArray(new VolumeInfo[packageCandidateVolumes.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.change_storage);
            builder.setSingleChoiceItems(charSequenceArr, i, this);
            builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
            this.mDialogBuilder = builder;
            return;
        }
        removePreference("storage_used");
        removePreference("change_storage_button");
        removePreference("storage_space");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateClearUserData() {
        String str = TAG;
        this.mMetricsFeatureProvider.action(getContext(), 876, new Pair[0]);
        this.mButtonsPref.setButton1Enabled(false);
        String str2 = this.mAppEntry.info.packageName;
        Log.i(str, "Clearing user data for package : " + str2);
        if (this.mClearDataObserver == null) {
            this.mClearDataObserver = new ClearUserDataObserver();
        }
        if (!((ActivityManager) getActivity().getSystemService("activity")).clearApplicationUserData(str2, this.mClearDataObserver)) {
            Log.i(str, "Couldn't clear application user data for package:" + str2);
            showDialogInner(2, 0);
            return;
        }
        this.mButtonsPref.setButton1Text(R.string.recompute_size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processClearMsg(Message message) {
        int i = message.arg1;
        String str = this.mAppEntry.info.packageName;
        ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
        actionButtonsPreference.setButton1Text(R.string.clear_user_data_text);
        actionButtonsPreference.setButton1Icon(R.drawable.ic_settings_delete);
        if (i == 1) {
            Log.i(TAG, "Cleared user data for package : " + str);
            updateSize();
            return;
        }
        this.mButtonsPref.setButton1Enabled(true);
    }

    private void refreshGrantedUriPermissions() {
        removeUriPermissionsFromUi();
        List list = ((ActivityManager) getActivity().getSystemService("activity")).getGrantedUriPermissions(this.mAppEntry.info.packageName).getList();
        if (list.isEmpty()) {
            this.mClearUriButton.setVisibility(8);
            return;
        }
        PackageManager packageManager = getActivity().getPackageManager();
        TreeMap treeMap = new TreeMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CharSequence charSequenceLoadLabel = packageManager.resolveContentProvider(((GrantedUriPermission) it.next()).uri.getAuthority(), 0).applicationInfo.loadLabel(packageManager);
            MutableInt mutableInt = (MutableInt) treeMap.get(charSequenceLoadLabel);
            if (mutableInt == null) {
                treeMap.put(charSequenceLoadLabel, new MutableInt(1));
            } else {
                mutableInt.value++;
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            int i = ((MutableInt) entry.getValue()).value;
            Preference preference = new Preference(getPrefContext());
            preference.setTitle((CharSequence) entry.getKey());
            preference.setSummary(getPrefContext().getResources().getQuantityString(R.plurals.uri_permissions_text, i, Integer.valueOf(i)));
            preference.setSelectable(false);
            preference.setLayoutResource(R.layout.horizontal_preference);
            preference.setOrder(0);
            Log.v(TAG, "Adding preference '" + preference + "' at order 0");
            this.mUri.addPreference(preference);
        }
        if (this.mAppsControlDisallowedBySystem) {
            this.mClearUriButton.setEnabled(false);
        }
        this.mClearUri.setOrder(0);
        this.mClearUriButton.setVisibility(0);
    }

    private void clearUriPermissions() {
        FragmentActivity activity = getActivity();
        ((ActivityManager) activity.getSystemService("activity")).clearGrantedUriPermissions(this.mAppEntry.info.packageName);
        refreshGrantedUriPermissions();
    }

    private void removeUriPermissionsFromUi() {
        for (int preferenceCount = this.mUri.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
            Preference preference = this.mUri.getPreference(preferenceCount);
            if (preference != this.mClearUri) {
                this.mUri.removePreference(preference);
            }
        }
    }

    @Override // com.android.car.developeroptions.applications.AppInfoBase
    protected AlertDialog createDialog(int i, int i2) {
        if (i == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getActivity().getText(R.string.clear_data_dlg_title));
            builder.setMessage(getActivity().getText(R.string.clear_data_dlg_text));
            builder.setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.AppStorageSettings.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    AppStorageSettings.this.initiateClearUserData();
                }
            });
            builder.setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
        if (i != 2) {
            return null;
        }
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle(getActivity().getText(R.string.clear_user_data_text));
        builder2.setMessage(getActivity().getText(R.string.clear_failed_dlg_text));
        builder2.setNeutralButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.AppStorageSettings.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                AppStorageSettings.this.mButtonsPref.setButton1Enabled(false);
                AppStorageSettings.this.setIntentAndFinish(false);
            }
        });
        return builder2.create();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<StorageStatsSource.AppStorageStats> onCreateLoader(int i, Bundle bundle) {
        Context context = getContext();
        return new FetchPackageStorageAsyncLoader(context, new StorageStatsSource(context), this.mInfo, UserHandle.of(this.mUserId));
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<StorageStatsSource.AppStorageStats> loader, StorageStatsSource.AppStorageStats appStorageStats) {
        this.mSizeController.setResult(appStorageStats);
        updateUiWithSize(appStorageStats);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSize() {
        try {
            this.mInfo = getPackageManager().getApplicationInfo(this.mPackageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find package", e);
        }
        if (this.mInfo == null) {
            return;
        }
        getLoaderManager().restartLoader(1, Bundle.EMPTY, this);
    }

    void updateUiWithSize(StorageStatsSource.AppStorageStats appStorageStats) {
        if (this.mCacheCleared) {
            this.mSizeController.setCacheCleared(true);
        }
        if (this.mDataCleared) {
            this.mSizeController.setDataCleared(true);
        }
        this.mSizeController.updateUi(getContext());
        if (appStorageStats == null) {
            ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
            actionButtonsPreference.setButton1Enabled(false);
            actionButtonsPreference.setButton2Enabled(false);
        } else {
            long cacheBytes = appStorageStats.getCacheBytes();
            if (appStorageStats.getDataBytes() - cacheBytes <= 0 || !this.mCanClearData || this.mDataCleared) {
                this.mButtonsPref.setButton1Enabled(false);
            } else {
                ActionButtonsPreference actionButtonsPreference2 = this.mButtonsPref;
                actionButtonsPreference2.setButton1Enabled(true);
                actionButtonsPreference2.setButton1OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$AppStorageSettings$mmJRA6sNUX8Y0hl08sriYUobP9U
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateUiWithSize$1$AppStorageSettings(view);
                    }
                });
            }
            if (cacheBytes <= 0 || this.mCacheCleared) {
                this.mButtonsPref.setButton2Enabled(false);
            } else {
                ActionButtonsPreference actionButtonsPreference3 = this.mButtonsPref;
                actionButtonsPreference3.setButton2Enabled(true);
                actionButtonsPreference3.setButton2OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$AppStorageSettings$7VBn5jw2WdZGAplyNURy6nWrYF8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateUiWithSize$2$AppStorageSettings(view);
                    }
                });
            }
        }
        if (this.mAppsControlDisallowedBySystem) {
            ActionButtonsPreference actionButtonsPreference4 = this.mButtonsPref;
            actionButtonsPreference4.setButton1Enabled(false);
            actionButtonsPreference4.setButton2Enabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateUiWithSize$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateUiWithSize$1$AppStorageSettings(View view) {
        handleClearDataClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateUiWithSize$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateUiWithSize$2$AppStorageSettings(View view) {
        handleClearCacheClick();
    }

    class ClearCacheObserver extends IPackageDataObserver.Stub {
        ClearCacheObserver() {
        }

        public void onRemoveCompleted(String str, boolean z) {
            Message messageObtainMessage = AppStorageSettings.this.mHandler.obtainMessage(3);
            messageObtainMessage.arg1 = z ? 1 : 2;
            AppStorageSettings.this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    class ClearUserDataObserver extends IPackageDataObserver.Stub {
        ClearUserDataObserver() {
        }

        public void onRemoveCompleted(String str, boolean z) {
            Message messageObtainMessage = AppStorageSettings.this.mHandler.obtainMessage(1);
            messageObtainMessage.arg1 = z ? 1 : 2;
            AppStorageSettings.this.mHandler.sendMessage(messageObtainMessage);
        }
    }
}
