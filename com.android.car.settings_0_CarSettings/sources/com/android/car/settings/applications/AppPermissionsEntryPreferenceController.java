package com.android.car.settings.applications;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.icu.text.ListFormatter;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class AppPermissionsEntryPreferenceController extends PreferenceController<Preference> {
    private static final int NUM_PERMISSION_TO_USE = 3;
    private PackageManager mPackageManager;
    private static final Logger LOG = new Logger((Class<?>) AppPermissionsEntryPreferenceController.class);
    private static final String[] PERMISSION_GROUPS = {"android.permission-group.LOCATION", "android.permission-group.MICROPHONE", "android.permission-group.CAMERA", "android.permission-group.SMS", "android.permission-group.CONTACTS", "android.permission-group.PHONE"};

    public AppPermissionsEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        String summary;
        Set<String> permissions = getAllPermissionsInGroups();
        Set<String> grantedPermissionGroups = getGrantedPermissionGroups(permissions);
        int count = 0;
        List<String> summaries = new ArrayList<>();
        for (String group : PERMISSION_GROUPS) {
            if (grantedPermissionGroups.contains(group)) {
                summaries.add(getPermissionGroupLabel(group).toString().toLowerCase());
                count++;
                if (count >= 3) {
                    break;
                }
            }
        }
        if (count > 0) {
            summary = getContext().getString(R.string.app_permissions_summary, ListFormatter.getInstance().format(summaries));
        } else {
            summary = null;
        }
        preference.setSummary(summary);
    }

    private Set<String> getAllPermissionsInGroups() {
        Set<String> result = new ArraySet<>();
        for (String group : PERMISSION_GROUPS) {
            try {
                List<PermissionInfo> permissions = this.mPackageManager.queryPermissionsByGroup(group, 0);
                for (PermissionInfo permissionInfo : permissions) {
                    result.add(permissionInfo.name);
                }
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("Error getting permissions in group " + group, e);
            }
        }
        return result;
    }

    private Set<String> getGrantedPermissionGroups(Set<String> permissions) {
        Set<String> grantedPermissionGroups = new ArraySet<>();
        List<PackageInfo> installedPackages = this.mPackageManager.getInstalledPackages(4096);
        for (PackageInfo installedPackage : installedPackages) {
            if (installedPackage.permissions != null) {
                for (PermissionInfo permissionInfo : installedPackage.permissions) {
                    if (permissions.contains(permissionInfo.name)) {
                        grantedPermissionGroups.add(permissionInfo.group);
                    }
                }
            }
        }
        return grantedPermissionGroups;
    }

    private CharSequence getPermissionGroupLabel(String group) {
        try {
            PermissionGroupInfo groupInfo = this.mPackageManager.getPermissionGroupInfo(group, 0);
            return groupInfo.loadLabel(this.mPackageManager);
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e("Error getting permissions label.", e);
            return group;
        }
    }
}
