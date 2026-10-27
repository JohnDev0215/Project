package com.android.permissioncontroller.permission.ui.auto;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.legacy.PermissionGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class AutoManageStandardPermissionsFragment extends AutoManagePermissionsFragment {
    private static final String EXTRA_PREFS_KEY = "extra_prefs_key";

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment, androidx.preference.PreferenceFragmentCompat
    public /* bridge */ /* synthetic */ void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
    }

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment, com.android.permissioncontroller.permission.model.legacy.PermissionGroups.PermissionsGroupsChangeCallback
    public /* bridge */ /* synthetic */ void onPermissionGroupsChanged() {
        super.onPermissionGroupsChanged();
    }

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment, androidx.preference.Preference.OnPreferenceClickListener
    public /* bridge */ /* synthetic */ boolean onPreferenceClick(Preference preference) {
        return super.onPreferenceClick(preference);
    }

    public static AutoManageStandardPermissionsFragment newInstance() {
        return new AutoManageStandardPermissionsFragment();
    }

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment
    protected int getScreenHeaderRes() {
        return R.string.app_permission_manager;
    }

    @Override // com.android.permissioncontroller.permission.ui.auto.AutoManagePermissionsFragment
    protected void updatePermissionsUi() {
        updatePermissionsUi(true);
        List<PermissionGroup> groups = getPermissions().getGroups();
        int numExtraPermissions = 0;
        for (PermissionGroup group : groups) {
            if (!group.getDeclaringPackage().equals(Utils.OS_PKG)) {
                numExtraPermissions++;
            }
        }
        Preference additionalPermissionsPreference = getPreferenceScreen().findPreference(EXTRA_PREFS_KEY);
        if (numExtraPermissions == 0) {
            if (additionalPermissionsPreference != null) {
                getPreferenceScreen().removePreference(additionalPermissionsPreference);
                return;
            }
            return;
        }
        if (additionalPermissionsPreference == null) {
            additionalPermissionsPreference = new Preference(getPreferenceManager().getContext());
            additionalPermissionsPreference.setKey(EXTRA_PREFS_KEY);
            additionalPermissionsPreference.setIcon(Utils.applyTint(getActivity(), R.drawable.ic_more_items, android.R.attr.colorControlNormal));
            additionalPermissionsPreference.setTitle(R.string.additional_permissions);
            additionalPermissionsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoManageStandardPermissionsFragment$rQ3BoUOUKQrdjCuMnf1ERY30gVs
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$updatePermissionsUi$0$AutoManageStandardPermissionsFragment(preference);
                }
            });
            getPreferenceScreen().addPreference(additionalPermissionsPreference);
        }
        additionalPermissionsPreference.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, numExtraPermissions, Integer.valueOf(numExtraPermissions)));
    }

    public /* synthetic */ boolean lambda$updatePermissionsUi$0$AutoManageStandardPermissionsFragment(Preference preference) {
        AutoManageCustomPermissionsFragment frag = new AutoManageCustomPermissionsFragment();
        frag.setTargetFragment(this, 0);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, frag);
        ft.addToBackStack(null);
        ft.commit();
        return true;
    }
}
