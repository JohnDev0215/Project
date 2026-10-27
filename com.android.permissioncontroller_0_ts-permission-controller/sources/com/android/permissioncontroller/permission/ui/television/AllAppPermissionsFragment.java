package com.android.permissioncontroller.permission.ui.television;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: loaded from: classes8.dex */
public final class AllAppPermissionsFragment extends SettingsWithHeader {
    private static final String KEY_OTHER = "other_perms";
    private static final String LOG_TAG = "AllAppPermissionsFragment";
    private AppPermissions mAppPermissions;
    private PackageInfo mPackageInfo;

    public static AllAppPermissionsFragment newInstance(String packageName) {
        AllAppPermissionsFragment instance = new AllAppPermissionsFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        instance.setArguments(arguments);
        return instance;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setTitle(R.string.all_permissions);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String pkg = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        try {
            this.mPackageInfo = getActivity().getPackageManager().getPackageInfo(pkg, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            getActivity().finish();
        }
        this.mAppPermissions = new AppPermissions(getActivity(), this.mPackageInfo, false, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.television.AllAppPermissionsFragment.1
            @Override // java.lang.Runnable
            public void run() {
                AllAppPermissionsFragment.this.getActivity().finish();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            getFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private PreferenceGroup getOtherGroup() {
        PreferenceGroup otherGroup = (PreferenceGroup) findPreference(KEY_OTHER);
        if (otherGroup == null) {
            PreferenceGroup otherGroup2 = new PreferenceCategory(getPreferenceManager().getContext());
            otherGroup2.setKey(KEY_OTHER);
            otherGroup2.setTitle(getString(R.string.other_permissions));
            getPreferenceScreen().addPreference(otherGroup2);
            return otherGroup2;
        }
        return otherGroup;
    }

    private void updateUi() {
        getPreferenceScreen().removeAll();
        ArrayList<Preference> prefs = new ArrayList<>();
        PackageManager pm = getActivity().getPackageManager();
        ApplicationInfo appInfo = this.mPackageInfo.applicationInfo;
        Drawable icon = appInfo.loadIcon(pm);
        CharSequence label = appInfo.loadLabel(pm);
        Intent infoIntent = null;
        if (!getActivity().getIntent().getBooleanExtra("hideInfoButton", false)) {
            infoIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mPackageInfo.packageName, null));
        }
        setHeader(icon, label, infoIntent, null);
        if (this.mPackageInfo.requestedPermissions != null) {
            for (int i = 0; i < this.mPackageInfo.requestedPermissions.length; i++) {
                try {
                    PermissionInfo perm = pm.getPermissionInfo(this.mPackageInfo.requestedPermissions[i], 0);
                    if ((perm.flags & BasicMeasure.EXACTLY) != 0 && (perm.flags & 2) == 0 && ((!appInfo.isInstantApp() || (perm.protectionLevel & 4096) != 0) && (appInfo.targetSdkVersion >= 23 || (perm.protectionLevel & 8192) == 0))) {
                        PermissionGroupInfo group = getGroup(Utils.getGroupOfPermission(perm), pm);
                        if ((perm.protectionLevel & 15) == 1) {
                            PreferenceGroup pref = findOrCreate(group != null ? group : perm, pm, prefs);
                            pref.addPreference(getPreference(perm, group));
                        } else if ((perm.protectionLevel & 15) == 0) {
                            PreferenceGroup otherGroup = getOtherGroup();
                            if (prefs.indexOf(otherGroup) < 0) {
                                prefs.add(otherGroup);
                            }
                            getOtherGroup().addPreference(getPreference(perm, group));
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(LOG_TAG, "Can't get permission info for " + this.mPackageInfo.requestedPermissions[i], e);
                }
            }
        }
        Collections.sort(prefs, new Comparator<Preference>() { // from class: com.android.permissioncontroller.permission.ui.television.AllAppPermissionsFragment.2
            @Override // java.util.Comparator
            public int compare(Preference lhs, Preference rhs) {
                String lKey = lhs.getKey();
                String rKey = rhs.getKey();
                if (lKey.equals(AllAppPermissionsFragment.KEY_OTHER)) {
                    return 1;
                }
                if (rKey.equals(AllAppPermissionsFragment.KEY_OTHER)) {
                    return -1;
                }
                if (Utils.isModernPermissionGroup(lKey) != Utils.isModernPermissionGroup(rKey)) {
                    return Utils.isModernPermissionGroup(lKey) ? -1 : 1;
                }
                return lhs.getTitle().toString().compareTo(rhs.getTitle().toString());
            }
        });
        for (int i2 = 0; i2 < prefs.size(); i2++) {
            prefs.get(i2).setOrder(i2);
        }
    }

    private PermissionGroupInfo getGroup(String group, PackageManager pm) {
        try {
            return pm.getPermissionGroupInfo(group, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private PreferenceGroup findOrCreate(PackageItemInfo group, PackageManager pm, ArrayList<Preference> prefs) {
        PreferenceGroup pref = (PreferenceGroup) findPreference(group.name);
        if (pref == null) {
            PreferenceGroup pref2 = new PreferenceCategory(getActivity());
            pref2.setKey(group.name);
            pref2.setLayoutResource(R.layout.preference_category_material);
            pref2.setTitle(group.loadLabel(pm));
            prefs.add(pref2);
            getPreferenceScreen().addPreference(pref2);
            return pref2;
        }
        return pref;
    }

    private Preference getPreference(PermissionInfo perm, PermissionGroupInfo group) {
        if (isMutableGranularPermission(perm.name)) {
            return getMutablePreference(perm, group);
        }
        return getImmutablePreference(perm, group);
    }

    private Preference getMutablePreference(PermissionInfo perm, PermissionGroupInfo group) {
        final AppPermissionGroup permGroup = this.mAppPermissions.getPermissionGroup(group.name);
        final String[] filterPermissions = {perm.name};
        SwitchPreference pref = new SwitchPreference(getPreferenceManager().getContext());
        pref.setLayoutResource(R.layout.preference_permissions);
        pref.setChecked(permGroup.areRuntimePermissionsGranted(filterPermissions));
        pref.setIcon(getTintedPermissionIcon(getActivity(), perm, group));
        pref.setTitle(perm.loadLabel(getActivity().getPackageManager()));
        pref.setPersistent(false);
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.television.AllAppPermissionsFragment.3
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object value) {
                if (value == Boolean.TRUE) {
                    permGroup.grantRuntimePermissions(true, false, filterPermissions);
                } else {
                    permGroup.revokeRuntimePermissions(false, filterPermissions);
                }
                return true;
            }
        });
        return pref;
    }

    private Preference getImmutablePreference(final PermissionInfo perm, PermissionGroupInfo group) {
        final PackageManager pm = getActivity().getPackageManager();
        Preference pref = new Preference(getActivity());
        pref.setLayoutResource(R.layout.preference_permissions);
        pref.setIcon(getTintedPermissionIcon(getActivity(), perm, group));
        pref.setTitle(perm.loadLabel(pm));
        pref.setPersistent(false);
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.AllAppPermissionsFragment.4
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                new AlertDialog.Builder(AllAppPermissionsFragment.this.getActivity()).setMessage(perm.loadDescription(pm)).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
                return true;
            }
        });
        return pref;
    }

    private static Drawable getTintedPermissionIcon(Context context, PermissionInfo perm, PermissionGroupInfo group) {
        Drawable icon;
        if (perm.icon != 0) {
            icon = perm.loadIcon(context.getPackageManager());
        } else if (group != null && group.icon != 0) {
            icon = group.loadIcon(context.getPackageManager());
        } else {
            icon = context.getDrawable(R.drawable.ic_perm_device_info);
        }
        return Utils.applyTint(context, icon, android.R.attr.colorControlNormal);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private boolean isMutableGranularPermission(String name) {
        if (!getContext().getPackageManager().arePermissionsIndividuallyControlled()) {
            return false;
        }
        byte b = -1;
        switch (name.hashCode()) {
            case -2062386608:
                if (name.equals("android.permission.READ_SMS")) {
                    b = 2;
                }
                break;
            case -1921431796:
                if (name.equals("android.permission.READ_CALL_LOG")) {
                    b = 3;
                }
                break;
            case 112197485:
                if (name.equals("android.permission.CALL_PHONE")) {
                    b = 4;
                }
                break;
            case 214526995:
                if (name.equals("android.permission.WRITE_CONTACTS")) {
                    b = 1;
                }
                break;
            case 1977429404:
                if (name.equals("android.permission.READ_CONTACTS")) {
                    b = 0;
                }
                break;
        }
        return b == 0 || b == 1 || b == 2 || b == 3 || b == 4;
    }
}
