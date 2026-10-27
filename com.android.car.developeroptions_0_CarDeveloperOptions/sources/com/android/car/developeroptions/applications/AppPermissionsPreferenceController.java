package com.android.car.developeroptions.applications;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.icu.text.ListFormatter;
import android.util.ArraySet;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AppPermissionsPreferenceController extends BasePreferenceController {
    private static final int NUM_PERMISSION_TO_USE = 3;
    private static final String[] PERMISSION_GROUPS = {"android.permission-group.LOCATION", "android.permission-group.MICROPHONE", "android.permission-group.CAMERA", "android.permission-group.SMS", "android.permission-group.CONTACTS", "android.permission-group.PHONE"};
    private static final String TAG = "AppPermissionPrefCtrl";
    private final PackageManager mPackageManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
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

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppPermissionsPreferenceController(Context context, String str) {
        super(context, str);
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        Set<String> grantedPermissionGroups = getGrantedPermissionGroups(getAllPermissionsInGroups());
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (String str : PERMISSION_GROUPS) {
            if (grantedPermissionGroups.contains(str)) {
                arrayList.add(getPermissionGroupLabel(str).toString().toLowerCase());
                i++;
                if (i >= 3) {
                    break;
                }
            }
        }
        if (i > 0) {
            return this.mContext.getString(R.string.app_permissions_summary, ListFormatter.getInstance().format(arrayList));
        }
        return null;
    }

    private Set<String> getGrantedPermissionGroups(Set<String> set) {
        ArraySet arraySet = new ArraySet();
        Iterator<PackageInfo> it = this.mPackageManager.getInstalledPackages(4096).iterator();
        while (it.hasNext()) {
            PermissionInfo[] permissionInfoArr = it.next().permissions;
            if (permissionInfoArr != null) {
                for (PermissionInfo permissionInfo : permissionInfoArr) {
                    if (set.contains(permissionInfo.name) && !arraySet.contains(permissionInfo.group)) {
                        arraySet.add(permissionInfo.group);
                    }
                }
            }
        }
        return arraySet;
    }

    private CharSequence getPermissionGroupLabel(String str) {
        try {
            return this.mPackageManager.getPermissionGroupInfo(str, 0).loadLabel(this.mPackageManager);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Error getting permissions label.", e);
            return str;
        }
    }

    private Set<String> getAllPermissionsInGroups() {
        ArraySet arraySet = new ArraySet();
        for (String str : PERMISSION_GROUPS) {
            try {
                Iterator<PermissionInfo> it = this.mPackageManager.queryPermissionsByGroup(str, 0).iterator();
                while (it.hasNext()) {
                    arraySet.add(it.next().name);
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Error getting permissions in group " + str, e);
            }
        }
        return arraySet;
    }
}
