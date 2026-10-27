package com.android.permissioncontroller.permission.ui.television;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.legacy.PermissionApps;
import com.android.permissioncontroller.permission.model.legacy.PermissionGroup;
import com.android.permissioncontroller.permission.model.legacy.PermissionGroups;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class ManagePermissionsFragment extends SettingsWithHeader implements PermissionGroups.PermissionsGroupsChangeCallback, Preference.OnPreferenceClickListener {
    private static final String EXTRA_PREFS_KEY = "extra_prefs_key";
    private static final String LOG_TAG = "ManagePermissionsFragment";
    private static final String OS_PKG = "android";
    private PreferenceScreen mExtraScreen;
    private PermissionGroups mPermissions;

    public static ManagePermissionsFragment newInstance() {
        return new ManagePermissionsFragment();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setLoading(true, false);
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        this.mPermissions = new PermissionGroups(getContext(), getActivity().getLoaderManager(), this, false, true);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
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

    @Override // com.android.permissioncontroller.permission.model.legacy.PermissionGroups.PermissionsGroupsChangeCallback
    public void onPermissionGroupsChanged() {
        updatePermissionsUi();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindPermissionUi(this, getView());
    }

    private static void bindPermissionUi(SettingsWithHeader fragment, View rootView) {
        if (fragment == null || rootView == null) {
            return;
        }
        fragment.setHeader(null, null, null, fragment.getString(R.string.manage_permissions_decor_title));
    }

    private void updatePermissionsUi() {
        PreferenceScreen preferenceScreen;
        Context context = getPreferenceManager().getContext();
        if (context == null) {
            return;
        }
        List<PermissionGroup> groups = this.mPermissions.getGroups();
        PreferenceScreen screen = getPreferenceScreen();
        new PermissionApps.PmCache(context.getPackageManager());
        for (PermissionGroup group : groups) {
            boolean isSystemPermission = group.getDeclaringPackage().equals("android");
            Preference preference = findPreference(group.getName());
            if (preference == null && (preferenceScreen = this.mExtraScreen) != null) {
                preference = preferenceScreen.findPreference(group.getName());
            }
            if (preference == null) {
                preference = new Preference(context);
                preference.setOnPreferenceClickListener(this);
                preference.setKey(group.getName());
                preference.setIcon(Utils.applyTint(context, group.getIcon(), android.R.attr.colorControlNormal));
                preference.setTitle(group.getLabel());
                preference.setSummary(" ");
                preference.setPersistent(false);
                if (isSystemPermission) {
                    screen.addPreference(preference);
                } else {
                    if (this.mExtraScreen == null) {
                        this.mExtraScreen = getPreferenceManager().createPreferenceScreen(context);
                    }
                    this.mExtraScreen.addPreference(preference);
                }
            }
            preference.setSummary(getString(R.string.app_permissions_group_summary, Integer.valueOf(group.getGranted()), Integer.valueOf(group.getTotal())));
        }
        PreferenceScreen preferenceScreen2 = this.mExtraScreen;
        if (preferenceScreen2 != null && preferenceScreen2.getPreferenceCount() > 0 && screen.findPreference(EXTRA_PREFS_KEY) == null) {
            Preference extraScreenPreference = new Preference(context);
            extraScreenPreference.setKey(EXTRA_PREFS_KEY);
            extraScreenPreference.setIcon(Utils.applyTint(context, R.drawable.ic_more_items, android.R.attr.colorControlNormal));
            extraScreenPreference.setTitle(R.string.additional_permissions);
            extraScreenPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.ManagePermissionsFragment.1
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference2) {
                    AdditionalPermissionsFragment frag = new AdditionalPermissionsFragment();
                    frag.setTargetFragment(ManagePermissionsFragment.this, 0);
                    FragmentTransaction ft = ManagePermissionsFragment.this.getFragmentManager().beginTransaction();
                    ft.replace(android.R.id.content, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    return true;
                }
            });
            int count = this.mExtraScreen.getPreferenceCount();
            extraScreenPreference.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, count, Integer.valueOf(count)));
            screen.addPreference(extraScreenPreference);
        }
        if (screen.getPreferenceCount() != 0) {
            setLoading(false, true);
        }
    }

    public static class AdditionalPermissionsFragment extends SettingsWithHeader {
        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle icicle) {
            setLoading(true, false);
            super.onCreate(icicle);
            getActivity().setTitle(R.string.additional_permissions);
            setHasOptionsMenu(true);
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            getActivity().setTitle(R.string.app_permissions);
            super.onDestroy();
        }

        @Override // androidx.fragment.app.Fragment
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == 16908332) {
                getFragmentManager().popBackStack();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            bindPermissionUi(this, getView());
        }

        private static void bindPermissionUi(SettingsWithHeader fragment, View rootView) {
            if (fragment == null || rootView == null) {
                return;
            }
            fragment.setHeader(null, null, null, fragment.getString(R.string.additional_permissions_decor_title));
        }

        @Override // com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferenceScreen(((ManagePermissionsFragment) getTargetFragment()).mExtraScreen);
            setLoading(false, true);
        }
    }
}
