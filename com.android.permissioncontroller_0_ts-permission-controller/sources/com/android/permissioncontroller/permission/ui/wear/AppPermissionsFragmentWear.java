package com.android.permissioncontroller.permission.ui.wear;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.wear.ble.view.WearableDialogHelper;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.RestrictedLockUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class AppPermissionsFragmentWear extends PreferenceFragmentCompat {
    private static final String KEY_NO_PERMISSIONS = "no_permissions";
    private static final String LOG_TAG = "AppPermFragWear";
    private AppPermissions mAppPermissions;
    private boolean mHasConfirmedRevoke;
    private PackageManager mPackageManager;
    private ArraySet<AppPermissionGroup> mToggledGroups;

    public static AppPermissionsFragmentWear newInstance(String packageName) {
        return (AppPermissionsFragmentWear) setPackageName(new AppPermissionsFragmentWear(), packageName);
    }

    private static <T extends Fragment> T setPackageName(T fragment, String packageName) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        fragment.setArguments(arguments);
        return fragment;
    }

    private static class PermissionSwitchPreference extends SwitchPreference {
        private final Activity mActivity;

        public PermissionSwitchPreference(Activity activity) {
            super(activity);
            this.mActivity = activity;
        }

        @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
        protected void performClick(View view) {
            super.performClick(view);
            if (!isEnabled()) {
                RestrictedLockUtils.EnforcedAdmin deviceOrProfileOwner = RestrictedLockUtils.getProfileOrDeviceOwner(this.mActivity, UserHandle.of(UserHandle.myUserId()));
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mActivity, deviceOrProfileOwner);
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        PackageInfo packageInfo;
        super.onCreate(savedInstanceState);
        String packageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        Activity activity = getActivity();
        PackageManager packageManager = activity.getPackageManager();
        this.mPackageManager = packageManager;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package:" + activity.getCallingPackage(), e);
            packageInfo = null;
        }
        if (packageInfo == null) {
            Toast.makeText(activity, R.string.app_not_found_dlg_title, 1).show();
            activity.finish();
        } else {
            this.mAppPermissions = new AppPermissions(activity, packageInfo, true, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$AppPermissionsFragmentWear$-krmNrBXrssE1ZDJff75RaEANkc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreate$0$AppPermissionsFragmentWear();
                }
            });
            addPreferencesFromResource(R.xml.watch_permissions);
            initializePermissionGroupList();
        }
    }

    public /* synthetic */ void lambda$onCreate$0$AppPermissionsFragmentWear() {
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAppPermissions.refresh();
        for (AppPermissionGroup group : this.mAppPermissions.getPermissionGroups()) {
            if (Utils.areGroupPermissionsIndividuallyControlled(getContext(), group.getName())) {
                for (PermissionInfo perm : getPermissionInfosFromGroup(group)) {
                    setPreferenceCheckedIfPresent(perm.name, group.areRuntimePermissionsGranted(new String[]{perm.name}));
                }
            } else {
                setPreferenceCheckedIfPresent(group.getName(), group.areRuntimePermissionsGranted());
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        logAndClearToggledGroups();
    }

    private void initializePermissionGroupList() {
        List<AppPermissionGroup> groups = this.mAppPermissions.getPermissionGroups();
        List<SwitchPreference> nonSystemPreferences = new ArrayList<>();
        if (!groups.isEmpty()) {
            getPreferenceScreen().removePreference(findPreference(KEY_NO_PERMISSIONS));
        }
        for (AppPermissionGroup group : groups) {
            if (Utils.shouldShowPermission(getContext(), group)) {
                boolean isPlatform = group.getDeclaringPackage().equals(Utils.OS_PKG);
                if (Utils.areGroupPermissionsIndividuallyControlled(getContext(), group.getName())) {
                    for (PermissionInfo perm : getPermissionInfosFromGroup(group)) {
                        SwitchPreference pref = createSwitchPreferenceForPermission(group, perm);
                        showOrAddToNonSystemPreferences(pref, nonSystemPreferences, isPlatform);
                    }
                } else {
                    SwitchPreference pref2 = createSwitchPreferenceForGroup(group);
                    showOrAddToNonSystemPreferences(pref2, nonSystemPreferences, isPlatform);
                }
            }
        }
        for (SwitchPreference nonSystemPreference : nonSystemPreferences) {
            getPreferenceScreen().addPreference(nonSystemPreference);
        }
    }

    private void showOrAddToNonSystemPreferences(SwitchPreference pref, List<SwitchPreference> nonSystemPreferences, boolean isPlatform) {
        if (isPlatform) {
            getPreferenceScreen().addPreference(pref);
        } else {
            nonSystemPreferences.add(pref);
        }
    }

    private SwitchPreference createSwitchPreferenceForPermission(final AppPermissionGroup group, final PermissionInfo perm) {
        final SwitchPreference pref = new PermissionSwitchPreference(getActivity());
        pref.setKey(perm.name);
        pref.setTitle(perm.loadLabel(this.mPackageManager));
        pref.setChecked(group.areRuntimePermissionsGranted(new String[]{perm.name}));
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$AppPermissionsFragmentWear$yz_CbnBSBNBbdFzim6guw3upNyI
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$createSwitchPreferenceForPermission$2$AppPermissionsFragmentWear(group, perm, pref, preference, obj);
            }
        });
        return pref;
    }

    public /* synthetic */ boolean lambda$createSwitchPreferenceForPermission$2$AppPermissionsFragmentWear(final AppPermissionGroup group, final PermissionInfo perm, final SwitchPreference pref, Preference p, Object newVal) {
        int i;
        if (((Boolean) newVal).booleanValue()) {
            group.grantRuntimePermissions(true, false, new String[]{perm.name});
            if (Utils.areGroupPermissionsIndividuallyControlled(getContext(), group.getName()) && group.doesSupportRuntimePermissions()) {
                String[] revokedPermissionsToFix = null;
                int permissionCount = group.getPermissions().size();
                for (int i2 = 0; i2 < permissionCount; i2++) {
                    Permission current = group.getPermissions().get(i2);
                    if (!current.isGranted() && !current.isUserFixed()) {
                        revokedPermissionsToFix = ArrayUtils.appendString(revokedPermissionsToFix, current.getName());
                    }
                }
                if (revokedPermissionsToFix != null) {
                    group.revokeRuntimePermissions(true, revokedPermissionsToFix);
                }
            }
        } else {
            final Permission appPerm = getPermissionFromGroup(group, perm.name);
            if (appPerm == null) {
                return false;
            }
            boolean grantedByDefault = appPerm.isGrantedByDefault();
            if (grantedByDefault || (!group.doesSupportRuntimePermissions() && !this.mHasConfirmedRevoke)) {
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$AppPermissionsFragmentWear$rY15cBr3eXAwjUL6YJJ-D_Cf3HA
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i3) {
                        this.f$0.lambda$createSwitchPreferenceForPermission$1$AppPermissionsFragmentWear(group, perm, pref, appPerm, dialogInterface, i3);
                    }
                };
                if (grantedByDefault) {
                    i = R.string.system_warning;
                } else {
                    i = R.string.old_sdk_deny_warning;
                }
                showRevocationWarningDialog(onClickListener, i);
                return false;
            }
            revokePermissionInGroup(group, perm.name);
        }
        return true;
    }

    public /* synthetic */ void lambda$createSwitchPreferenceForPermission$1$AppPermissionsFragmentWear(AppPermissionGroup group, PermissionInfo perm, SwitchPreference pref, Permission appPerm, DialogInterface dialog, int which) {
        revokePermissionInGroup(group, perm.name);
        pref.setChecked(false);
        if (!appPerm.isGrantedByDefault()) {
            this.mHasConfirmedRevoke = true;
        }
    }

    private void showRevocationWarningDialog(DialogInterface.OnClickListener confirmListener, int warningMessageId) {
        new WearableDialogHelper.DialogBuilder(getContext()).setNegativeIcon(R.drawable.confirm_button).setPositiveIcon(R.drawable.cancel_button).setNegativeButton(R.string.grant_dialog_button_deny_anyway, confirmListener).setPositiveButton(R.string.cancel, (DialogInterface.OnClickListener) null).setMessage(warningMessageId).show();
    }

    private static Permission getPermissionFromGroup(AppPermissionGroup group, String permName) {
        int permissionCount = group.getPermissions().size();
        for (int i = 0; i < permissionCount; i++) {
            Permission currentPerm = group.getPermissions().get(i);
            if (currentPerm.getName().equals(permName)) {
                return currentPerm;
            }
        }
        if ("user".equals(Build.TYPE)) {
            Log.e(LOG_TAG, String.format("The impossible happens, permission %s is not in group %s.", permName, group.getName()));
            return null;
        }
        throw new IllegalArgumentException(String.format("Permission %s is not in group %s", permName, group.getName()));
    }

    private void revokePermissionInGroup(AppPermissionGroup group, String permName) {
        group.revokeRuntimePermissions(true, new String[]{permName});
        if (Utils.areGroupPermissionsIndividuallyControlled(getContext(), group.getName()) && group.doesSupportRuntimePermissions() && !group.areRuntimePermissionsGranted()) {
            group.revokeRuntimePermissions(false);
        }
    }

    private SwitchPreference createSwitchPreferenceForGroup(final AppPermissionGroup group) {
        final SwitchPreference pref = new PermissionSwitchPreference(getActivity());
        pref.setKey(group.getName());
        pref.setTitle(group.getLabel());
        pref.setChecked(group.areRuntimePermissionsGranted());
        if (group.isSystemFixed() || group.isPolicyFixed()) {
            pref.setEnabled(false);
        } else {
            pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$AppPermissionsFragmentWear$35hGo6r-UucN3F5auZjc3xK7bcs
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return this.f$0.lambda$createSwitchPreferenceForGroup$4$AppPermissionsFragmentWear(group, pref, preference, obj);
                }
            });
        }
        return pref;
    }

    public /* synthetic */ boolean lambda$createSwitchPreferenceForGroup$4$AppPermissionsFragmentWear(final AppPermissionGroup group, final SwitchPreference pref, Preference p, Object newVal) {
        int i;
        if (LocationUtils.isLocationGroupAndProvider(getContext(), group.getName(), group.getApp().packageName)) {
            LocationUtils.showLocationDialog(getContext(), this.mAppPermissions.getAppLabel());
            return false;
        }
        if (((Boolean) newVal).booleanValue()) {
            setPermission(group, pref, true);
        } else {
            boolean grantedByDefault = group.hasGrantedByDefaultPermission();
            if (grantedByDefault || (!group.doesSupportRuntimePermissions() && !this.mHasConfirmedRevoke)) {
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$AppPermissionsFragmentWear$HEyrAkG9S-tCFjRRR5wzlz9QMw8
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        this.f$0.lambda$createSwitchPreferenceForGroup$3$AppPermissionsFragmentWear(group, pref, dialogInterface, i2);
                    }
                };
                if (grantedByDefault) {
                    i = R.string.system_warning;
                } else {
                    i = R.string.old_sdk_deny_warning;
                }
                showRevocationWarningDialog(onClickListener, i);
                return false;
            }
            setPermission(group, pref, false);
        }
        return true;
    }

    public /* synthetic */ void lambda$createSwitchPreferenceForGroup$3$AppPermissionsFragmentWear(AppPermissionGroup group, SwitchPreference pref, DialogInterface dialog, int which) {
        setPermission(group, pref, false);
        if (!group.hasGrantedByDefaultPermission()) {
            this.mHasConfirmedRevoke = true;
        }
    }

    private void setPermission(AppPermissionGroup group, SwitchPreference pref, boolean grant) {
        if (grant) {
            group.grantRuntimePermissions(true, false);
        } else {
            group.revokeRuntimePermissions(false);
        }
        addToggledGroup(group);
        pref.setChecked(grant);
    }

    private void addToggledGroup(AppPermissionGroup group) {
        if (this.mToggledGroups == null) {
            this.mToggledGroups = new ArraySet<>();
        }
        this.mToggledGroups.add(group);
    }

    private void logAndClearToggledGroups() {
        ArraySet<AppPermissionGroup> arraySet = this.mToggledGroups;
        if (arraySet != null) {
            SafetyNetLogger.logPermissionsToggled(arraySet);
            this.mToggledGroups = null;
        }
    }

    private List<PermissionInfo> getPermissionInfosFromGroup(AppPermissionGroup group) {
        ArrayList<PermissionInfo> permInfos = new ArrayList<>(group.getPermissions().size());
        for (Permission perm : group.getPermissions()) {
            try {
                permInfos.add(this.mPackageManager.getPermissionInfo(perm.getName(), 0));
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(LOG_TAG, "No permission:" + perm.getName());
            }
        }
        return permInfos;
    }

    private void setPreferenceCheckedIfPresent(String preferenceKey, boolean checked) {
        Preference pref = findPreference(preferenceKey);
        if (pref instanceof SwitchPreference) {
            ((SwitchPreference) pref).setChecked(checked);
        }
    }
}
