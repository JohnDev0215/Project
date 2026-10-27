package com.android.permissioncontroller.permission.ui.auto;

import android.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.permission.model.legacy.PermissionGroup;
import com.android.permissioncontroller.permission.model.legacy.PermissionGroups;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: classes8.dex */
abstract class AutoManagePermissionsFragment extends AutoSettingsFrameFragment implements PermissionGroups.PermissionsGroupsChangeCallback, Preference.OnPreferenceClickListener {
    private static final String LOG_TAG = "ManagePermissionsFragment";
    static final String OS_PKG = "android";
    private Collator mCollator;
    private PermissionGroups mPermissions;

    protected abstract int getScreenHeaderRes();

    protected abstract void updatePermissionsUi();

    AutoManagePermissionsFragment() {
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoading(true);
        this.mPermissions = new PermissionGroups(getContext(), requireActivity().getLoaderManager(), this, false, true);
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
        setHeaderLabel(getString(getScreenHeaderRes()));
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        PermissionGroup group = this.mPermissions.getGroup(key);
        if (group == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MANAGE_PERMISSION_APPS").putExtra("android.intent.extra.PERMISSION_NAME", key);
        try {
            getActivity().startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(LOG_TAG, "No app to handle " + intent);
            return true;
        }
    }

    protected PermissionGroups getPermissions() {
        return this.mPermissions;
    }

    public void onPermissionGroupsChanged() {
        updatePermissionsUi();
    }

    protected void updatePermissionsUi(boolean addSystemPermissions) {
        Context context = getPreferenceManager().getContext();
        if (context == null || getActivity() == null) {
            return;
        }
        ArrayList<PermissionGroup> groups = new ArrayList<>(this.mPermissions.getGroups());
        groups.sort(new Comparator() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoManagePermissionsFragment$9HhAaFnejL9XFSqifrPCWxW-oJk
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.lambda$updatePermissionsUi$0$AutoManagePermissionsFragment((PermissionGroup) obj, (PermissionGroup) obj2);
            }
        });
        getPreferenceScreen().removeAll();
        getPreferenceScreen().setOrderingAsAdded(true);
        for (int i = 0; i < groups.size(); i++) {
            PermissionGroup group = groups.get(i);
            boolean isSystemPermission = group.getDeclaringPackage().equals("android");
            if (addSystemPermissions == isSystemPermission) {
                Preference preference = findPreference(group.getName());
                if (preference == null) {
                    preference = new Preference(context);
                    preference.setOnPreferenceClickListener(this);
                    preference.setKey(group.getName());
                    preference.setIcon(Utils.applyTint(context, group.getIcon(), R.attr.colorControlNormal));
                    preference.setTitle(group.getLabel());
                    preference.setSummary(" ");
                    preference.setPersistent(false);
                    getPreferenceScreen().addPreference(preference);
                }
                preference.setSummary(getString(com.android.permissioncontroller.R.string.app_permissions_group_summary, Integer.valueOf(group.getGranted()), Integer.valueOf(group.getTotal())));
            }
        }
        if (getPreferenceScreen().getPreferenceCount() != 0) {
            setLoading(false);
        }
    }

    public /* synthetic */ int lambda$updatePermissionsUi$0$AutoManagePermissionsFragment(PermissionGroup x, PermissionGroup y) {
        return this.mCollator.compare(x.getLabel(), y.getLabel());
    }
}
