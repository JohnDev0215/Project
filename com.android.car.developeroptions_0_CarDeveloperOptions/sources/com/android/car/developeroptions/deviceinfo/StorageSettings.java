package com.android.car.developeroptions.deviceinfo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.os.storage.VolumeRecord;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.deviceinfo.PrivateStorageInfo;
import com.android.settingslib.deviceinfo.StorageManagerVolumeProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StorageSettings extends SettingsPreferenceFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.deviceinfo.StorageSettings.2
    };
    private static long sTotalInternalStorage;
    private PreferenceCategory mExternalCategory;
    private PreferenceCategory mInternalCategory;
    private StorageSummaryPreference mInternalSummary;
    private StorageManager mStorageManager;
    private boolean mHasLaunchedPrivateVolumeSettings = false;
    private final StorageEventListener mStorageListener = new StorageEventListener() { // from class: com.android.car.developeroptions.deviceinfo.StorageSettings.1
        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i, int i2) {
            if (StorageSettings.isInteresting(volumeInfo)) {
                StorageSettings.this.refresh();
            }
        }

        public void onDiskDestroyed(DiskInfo diskInfo) {
            StorageSettings.this.refresh();
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_storage;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 42;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StorageManager storageManager = (StorageManager) getActivity().getSystemService(StorageManager.class);
        this.mStorageManager = storageManager;
        if (sTotalInternalStorage <= 0) {
            sTotalInternalStorage = storageManager.getPrimaryStorageSize();
        }
        addPreferencesFromResource(R.xml.device_info_storage);
        this.mInternalCategory = (PreferenceCategory) findPreference("storage_internal");
        this.mExternalCategory = (PreferenceCategory) findPreference("storage_external");
        this.mInternalSummary = new StorageSummaryPreference(getPrefContext());
        setHasOptionsMenu(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInteresting(VolumeInfo volumeInfo) {
        int type = volumeInfo.getType();
        return type == 0 || type == 1 || type == 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void refresh() {
        Context prefContext = getPrefContext();
        getPreferenceScreen().removeAll();
        this.mInternalCategory.removeAll();
        this.mExternalCategory.removeAll();
        this.mInternalCategory.addPreference(this.mInternalSummary);
        PrivateStorageInfo privateStorageInfo = PrivateStorageInfo.getPrivateStorageInfo(new StorageManagerVolumeProvider(this.mStorageManager));
        long j = privateStorageInfo.totalBytes;
        long j2 = privateStorageInfo.totalBytes - privateStorageInfo.freeBytes;
        List<VolumeInfo> volumes = this.mStorageManager.getVolumes();
        Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo volumeInfo : volumes) {
            if (volumeInfo.getType() == 1) {
                if (volumeInfo.getState() == 6) {
                    this.mInternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, 0L));
                } else {
                    this.mInternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, PrivateStorageInfo.getTotalSize(volumeInfo, sTotalInternalStorage)));
                }
            } else if (volumeInfo.getType() == 0 || volumeInfo.getType() == 5) {
                this.mExternalCategory.addPreference(new StorageVolumePreference(prefContext, volumeInfo, 0L));
            }
        }
        for (VolumeRecord volumeRecord : this.mStorageManager.getVolumeRecords()) {
            if (volumeRecord.getType() == 1 && this.mStorageManager.findVolumeByUuid(volumeRecord.getFsUuid()) == null) {
                Preference preference = new Preference(prefContext);
                preference.setKey(volumeRecord.getFsUuid());
                preference.setTitle(volumeRecord.getNickname());
                preference.setSummary(android.R.string.connected_display_unavailable_notification_content);
                preference.setIcon(R.drawable.ic_sim_sd);
                this.mInternalCategory.addPreference(preference);
            }
        }
        for (DiskInfo diskInfo : this.mStorageManager.getDisks()) {
            if (diskInfo.volumeCount == 0 && diskInfo.size > 0) {
                Preference preference2 = new Preference(prefContext);
                preference2.setKey(diskInfo.getId());
                preference2.setTitle(diskInfo.getDescription());
                preference2.setSummary(android.R.string.contentServiceTooManyDeletesNotificationDesc);
                preference2.setIcon(R.drawable.ic_sim_sd);
                this.mExternalCategory.addPreference(preference2);
            }
        }
        Formatter.BytesResult bytes = Formatter.formatBytes(getResources(), j2, 0);
        this.mInternalSummary.setTitle(TextUtils.expandTemplate(getText(R.string.storage_size_large), bytes.value, bytes.units));
        this.mInternalSummary.setSummary(getString(R.string.storage_volume_used_total, Formatter.formatFileSize(prefContext, j)));
        if (this.mInternalCategory.getPreferenceCount() > 0) {
            getPreferenceScreen().addPreference(this.mInternalCategory);
        }
        if (this.mExternalCategory.getPreferenceCount() > 0) {
            getPreferenceScreen().addPreference(this.mExternalCategory);
        }
        if (this.mInternalCategory.getPreferenceCount() == 2 && this.mExternalCategory.getPreferenceCount() == 0 && !this.mHasLaunchedPrivateVolumeSettings) {
            this.mHasLaunchedPrivateVolumeSettings = true;
            Bundle bundle = new Bundle();
            bundle.putString("android.os.storage.extra.VOLUME_ID", "private");
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
            subSettingLauncher.setDestination(StorageDashboardFragment.class.getName());
            subSettingLauncher.setArguments(bundle);
            subSettingLauncher.setTitleRes(R.string.storage_settings);
            subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher.launch();
            finish();
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mStorageManager.registerListener(this.mStorageListener);
        refresh();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mStorageManager.unregisterListener(this.mStorageListener);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if (preference instanceof StorageVolumePreference) {
            VolumeInfo volumeInfoFindVolumeById = this.mStorageManager.findVolumeById(key);
            if (volumeInfoFindVolumeById == null) {
                return false;
            }
            if (volumeInfoFindVolumeById.getState() == 0) {
                VolumeUnmountedFragment.show(this, volumeInfoFindVolumeById.getId());
                return true;
            }
            if (volumeInfoFindVolumeById.getState() == 6) {
                DiskInitFragment.show(this, R.string.storage_dialog_unmountable, volumeInfoFindVolumeById.getDiskId());
                return true;
            }
            if (volumeInfoFindVolumeById.getType() == 1) {
                Bundle bundle = new Bundle();
                bundle.putString("android.os.storage.extra.VOLUME_ID", volumeInfoFindVolumeById.getId());
                if ("private".equals(volumeInfoFindVolumeById.getId())) {
                    SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
                    subSettingLauncher.setDestination(StorageDashboardFragment.class.getCanonicalName());
                    subSettingLauncher.setTitleRes(R.string.storage_settings);
                    subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
                    subSettingLauncher.setArguments(bundle);
                    subSettingLauncher.launch();
                } else {
                    PrivateVolumeSettings.setVolumeSize(bundle, PrivateStorageInfo.getTotalSize(volumeInfoFindVolumeById, sTotalInternalStorage));
                    SubSettingLauncher subSettingLauncher2 = new SubSettingLauncher(getContext());
                    subSettingLauncher2.setDestination(PrivateVolumeSettings.class.getCanonicalName());
                    subSettingLauncher2.setTitleRes(-1);
                    subSettingLauncher2.setSourceMetricsCategory(getMetricsCategory());
                    subSettingLauncher2.setArguments(bundle);
                    subSettingLauncher2.launch();
                }
                return true;
            }
            if (volumeInfoFindVolumeById.getType() == 0) {
                return handlePublicVolumeClick(getContext(), volumeInfoFindVolumeById);
            }
            if (volumeInfoFindVolumeById.getType() == 5) {
                return handleStubVolumeClick(getContext(), volumeInfoFindVolumeById);
            }
            return false;
        }
        if (key.startsWith("disk:")) {
            DiskInitFragment.show(this, R.string.storage_dialog_unsupported, key);
            return true;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("android.os.storage.extra.FS_UUID", key);
        SubSettingLauncher subSettingLauncher3 = new SubSettingLauncher(getContext());
        subSettingLauncher3.setDestination(PrivateVolumeForget.class.getCanonicalName());
        subSettingLauncher3.setTitleRes(R.string.storage_menu_forget);
        subSettingLauncher3.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher3.setArguments(bundle2);
        subSettingLauncher3.launch();
        return true;
    }

    static boolean handleStubVolumeClick(Context context, VolumeInfo volumeInfo) {
        Intent intentBuildBrowseIntent = volumeInfo.buildBrowseIntent();
        if (!volumeInfo.isMountedReadable() || intentBuildBrowseIntent == null) {
            return false;
        }
        context.startActivity(intentBuildBrowseIntent);
        return true;
    }

    static boolean handlePublicVolumeClick(Context context, VolumeInfo volumeInfo) {
        Intent intentBuildBrowseIntent = volumeInfo.buildBrowseIntent();
        if (volumeInfo.isMountedReadable() && intentBuildBrowseIntent != null) {
            context.startActivity(intentBuildBrowseIntent);
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("android.os.storage.extra.VOLUME_ID", volumeInfo.getId());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(context);
        subSettingLauncher.setDestination(PublicVolumeSettings.class.getCanonicalName());
        subSettingLauncher.setTitleRes(-1);
        subSettingLauncher.setSourceMetricsCategory(42);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
        return true;
    }

    public static class MountTask extends AsyncTask<Void, Void, Exception> {
        private final Context mContext;
        private final String mDescription;
        private final StorageManager mStorageManager;
        private final String mVolumeId;

        public MountTask(Context context, VolumeInfo volumeInfo) {
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext;
            this.mStorageManager = (StorageManager) applicationContext.getSystemService(StorageManager.class);
            this.mVolumeId = volumeInfo.getId();
            this.mDescription = this.mStorageManager.getBestVolumeDescription(volumeInfo);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(Void... voidArr) {
            try {
                this.mStorageManager.mount(this.mVolumeId);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Exception exc) {
            if (exc == null) {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(R.string.storage_mount_success, this.mDescription), 0).show();
                return;
            }
            Log.e("StorageSettings", "Failed to mount " + this.mVolumeId, exc);
            Context context2 = this.mContext;
            Toast.makeText(context2, context2.getString(R.string.storage_mount_failure, this.mDescription), 0).show();
        }
    }

    public static class UnmountTask extends AsyncTask<Void, Void, Exception> {
        private final Context mContext;
        private final String mDescription;
        private final StorageManager mStorageManager;
        private final String mVolumeId;

        public UnmountTask(Context context, VolumeInfo volumeInfo) {
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext;
            this.mStorageManager = (StorageManager) applicationContext.getSystemService(StorageManager.class);
            this.mVolumeId = volumeInfo.getId();
            this.mDescription = this.mStorageManager.getBestVolumeDescription(volumeInfo);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(Void... voidArr) {
            try {
                this.mStorageManager.unmount(this.mVolumeId);
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Exception exc) {
            if (exc == null) {
                Context context = this.mContext;
                Toast.makeText(context, context.getString(R.string.storage_unmount_success, this.mDescription), 0).show();
                return;
            }
            Log.e("StorageSettings", "Failed to unmount " + this.mVolumeId, exc);
            Context context2 = this.mContext;
            Toast.makeText(context2, context2.getString(R.string.storage_unmount_failure, this.mDescription), 0).show();
        }
    }

    public static class VolumeUnmountedFragment extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 562;
        }

        public static void show(Fragment fragment, String str) {
            Bundle bundle = new Bundle();
            bundle.putString("android.os.storage.extra.VOLUME_ID", str);
            VolumeUnmountedFragment volumeUnmountedFragment = new VolumeUnmountedFragment();
            volumeUnmountedFragment.setArguments(bundle);
            volumeUnmountedFragment.setTargetFragment(fragment, 0);
            volumeUnmountedFragment.show(fragment.getFragmentManager(), "volume_unmounted");
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            final FragmentActivity activity = getActivity();
            final VolumeInfo volumeInfoFindVolumeById = ((StorageManager) activity.getSystemService(StorageManager.class)).findVolumeById(getArguments().getString("android.os.storage.extra.VOLUME_ID"));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(TextUtils.expandTemplate(getText(R.string.storage_dialog_unmounted), volumeInfoFindVolumeById.getDisk().getDescription()));
            builder.setPositiveButton(R.string.storage_menu_mount, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.deviceinfo.StorageSettings.VolumeUnmountedFragment.1
                private boolean wasAdminSupportIntentShown(String str) {
                    RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(VolumeUnmountedFragment.this.getActivity(), str, UserHandle.myUserId());
                    boolean zHasBaseUserRestriction = RestrictedLockUtilsInternal.hasBaseUserRestriction(VolumeUnmountedFragment.this.getActivity(), str, UserHandle.myUserId());
                    if (enforcedAdminCheckIfRestrictionEnforced == null || zHasBaseUserRestriction) {
                        return false;
                    }
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(VolumeUnmountedFragment.this.getActivity(), enforcedAdminCheckIfRestrictionEnforced);
                    return true;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (wasAdminSupportIntentShown("no_physical_media")) {
                        return;
                    }
                    DiskInfo diskInfo = volumeInfoFindVolumeById.disk;
                    if (diskInfo != null && diskInfo.isUsb() && wasAdminSupportIntentShown("no_usb_file_transfer")) {
                        return;
                    }
                    new MountTask(activity, volumeInfoFindVolumeById).execute(new Void[0]);
                }
            });
            builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }

    public static class DiskInitFragment extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 561;
        }

        public static void show(Fragment fragment, int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putInt("android.intent.extra.TEXT", i);
            bundle.putString("android.os.storage.extra.DISK_ID", str);
            DiskInitFragment diskInitFragment = new DiskInitFragment();
            diskInitFragment.setArguments(bundle);
            diskInitFragment.setTargetFragment(fragment, 0);
            diskInitFragment.show(fragment.getFragmentManager(), "disk_init");
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            final FragmentActivity activity = getActivity();
            StorageManager storageManager = (StorageManager) activity.getSystemService(StorageManager.class);
            int i = getArguments().getInt("android.intent.extra.TEXT");
            final String string = getArguments().getString("android.os.storage.extra.DISK_ID");
            DiskInfo diskInfoFindDiskById = storageManager.findDiskById(string);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(TextUtils.expandTemplate(getText(i), diskInfoFindDiskById.getDescription()));
            builder.setPositiveButton(R.string.storage_menu_set_up, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.deviceinfo.StorageSettings.DiskInitFragment.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    Intent intent = new Intent(activity, (Class<?>) StorageWizardInit.class);
                    intent.putExtra("android.os.storage.extra.DISK_ID", string);
                    DiskInitFragment.this.startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
    }
}
