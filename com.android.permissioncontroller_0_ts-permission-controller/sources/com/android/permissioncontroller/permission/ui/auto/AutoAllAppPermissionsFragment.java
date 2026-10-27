package com.android.permissioncontroller.permission.ui.auto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.car.ui.AlertDialogBuilder;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.ui.auto.AutoAllAppPermissionsFragment;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class AutoAllAppPermissionsFragment extends AutoSettingsFrameFragment {
    private static final String KEY_OTHER = "other_perms";
    private static final String LOG_TAG = "AllAppPermsFrag";
    private List<AppPermissionGroup> mGroups;

    public static AutoAllAppPermissionsFragment newInstance(String packageName, UserHandle userHandle) {
        return newInstance(packageName, null, userHandle);
    }

    public static AutoAllAppPermissionsFragment newInstance(String packageName, String filterGroup, UserHandle userHandle) {
        AutoAllAppPermissionsFragment instance = new AutoAllAppPermissionsFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putString("android.intent.extra.PERMISSION_GROUP_NAME", filterGroup);
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        instance.setArguments(arguments);
        return instance;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME") == null) {
            setHeaderLabel(getContext().getString(R.string.all_permissions));
        } else {
            setHeaderLabel(getContext().getString(R.string.app_permissions));
        }
        updateUi();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getPreferenceScreen().removeAll();
    }

    private void updateUi() {
        PreferenceCategory preferenceCategory = new PreferenceCategory(getContext());
        preferenceCategory.setKey(KEY_OTHER);
        preferenceCategory.setTitle(R.string.other_permissions);
        getPreferenceScreen().addPreference(preferenceCategory);
        ArrayList<Preference> prefs = new ArrayList<>();
        prefs.add(preferenceCategory);
        String pkg = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        String filterGroup = getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        preferenceCategory.removeAll();
        PackageManager pm = getContext().getPackageManager();
        PackageInfo info = AutoPermissionsUtils.getPackageInfo(requireActivity(), pkg, userHandle);
        if (info == null) {
            return;
        }
        ApplicationInfo appInfo = info.applicationInfo;
        Preference header = AutoPermissionsUtils.createHeaderPreference(getContext(), appInfo);
        header.setOrder(0);
        getPreferenceScreen().addPreference(header);
        if (info.requestedPermissions != null) {
            for (int i = 0; i < info.requestedPermissions.length; i++) {
                try {
                    PermissionInfo perm = pm.getPermissionInfo(info.requestedPermissions[i], 0);
                    if ((perm.flags & BasicMeasure.EXACTLY) != 0 && (perm.flags & 2) == 0 && ((!appInfo.isInstantApp() || (perm.protectionLevel & 4096) != 0) && (appInfo.targetSdkVersion >= 23 || (perm.protectionLevel & 8192) == 0))) {
                        if ((perm.protectionLevel & 15) == 1) {
                            PermissionGroupInfo group = getGroup(Utils.getGroupOfPermission(perm), pm);
                            if (group == null) {
                                group = perm;
                            }
                            if (filterGroup == null || ((PackageItemInfo) group).name.equals(filterGroup)) {
                                PreferenceGroup pref = findOrCreate(group, pm, prefs);
                                pref.addPreference(getPreference(info, perm, group, pm));
                            }
                        } else if (filterGroup == null && (perm.protectionLevel & 15) == 0) {
                            preferenceCategory.addPreference(getPreference(info, perm, getGroup(perm.group, pm), pm));
                        }
                        if (filterGroup != null) {
                            getPreferenceScreen().removePreference(preferenceCategory);
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(LOG_TAG, "Can't get permission info for " + info.requestedPermissions[i], e);
                }
            }
        }
        Collections.sort(prefs, new Comparator() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAllAppPermissionsFragment$YzPcCvbJgvmNQbAWoIB-4U3bdLg
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AutoAllAppPermissionsFragment.lambda$updateUi$0((Preference) obj, (Preference) obj2);
            }
        });
        for (int i2 = 0; i2 < prefs.size(); i2++) {
            prefs.get(i2).setOrder(i2 + 1);
        }
    }

    static /* synthetic */ int lambda$updateUi$0(Preference lhs, Preference rhs) {
        String lKey = lhs.getKey();
        String rKey = rhs.getKey();
        if (lKey.equals(KEY_OTHER)) {
            return 1;
        }
        if (rKey.equals(KEY_OTHER)) {
            return -1;
        }
        if (Utils.isModernPermissionGroup(lKey) != Utils.isModernPermissionGroup(rKey)) {
            return Utils.isModernPermissionGroup(lKey) ? -1 : 1;
        }
        return lhs.getTitle().toString().compareTo(rhs.getTitle().toString());
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
            PreferenceGroup pref2 = new PreferenceCategory(getPreferenceManager().getContext());
            pref2.setKey(group.name);
            pref2.setTitle(group.loadLabel(pm));
            prefs.add(pref2);
            getPreferenceScreen().addPreference(pref2);
            return pref2;
        }
        return pref;
    }

    private Preference getPreference(PackageInfo packageInfo, PermissionInfo perm, PackageItemInfo group, PackageManager pm) {
        Preference pref;
        Drawable icon;
        Context context = getPreferenceManager().getContext();
        final boolean mutable = Utils.isPermissionIndividuallyControlled(getContext(), perm.name);
        if (mutable) {
            pref = new MyMultiTargetSwitchPreference(context, perm.name, getPermissionForegroundGroup(packageInfo, perm.name));
        } else {
            pref = new Preference(context);
        }
        if (perm.icon != 0) {
            icon = perm.loadUnbadgedIcon(pm);
        } else if (group != null && group.icon != 0) {
            icon = group.loadUnbadgedIcon(pm);
        } else {
            icon = context.getDrawable(R.drawable.ic_perm_device_info);
        }
        pref.setIcon(Utils.applyTint(context, icon, android.R.attr.colorControlNormal));
        pref.setTitle(perm.loadSafeLabel(pm, 20000.0f, 1));
        pref.setSingleLineTitle(false);
        final CharSequence desc = perm.loadDescription(pm);
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAllAppPermissionsFragment$MrU85Fwp_l5vpXCc00Swb9mUjBI
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$getPreference$1$AutoAllAppPermissionsFragment(desc, mutable, preference);
            }
        });
        return pref;
    }

    public /* synthetic */ boolean lambda$getPreference$1$AutoAllAppPermissionsFragment(CharSequence desc, boolean mutable, Preference preference) {
        new AlertDialogBuilder(getContext()).setMessage(desc).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
        return mutable;
    }

    private AppPermissionGroup getPermissionForegroundGroup(PackageInfo packageInfo, String permission) throws PackageManager.NameNotFoundException {
        AppPermissionGroup appPermissionGroup = null;
        List<AppPermissionGroup> list = this.mGroups;
        if (list != null) {
            int groupCount = list.size();
            int i = 0;
            while (true) {
                if (i >= groupCount) {
                    break;
                }
                AppPermissionGroup currentPermissionGroup = this.mGroups.get(i);
                if (currentPermissionGroup.hasPermission(permission)) {
                    appPermissionGroup = currentPermissionGroup;
                    break;
                }
                if (currentPermissionGroup.getBackgroundPermissions() == null || !currentPermissionGroup.getBackgroundPermissions().hasPermission(permission)) {
                    i++;
                } else {
                    appPermissionGroup = currentPermissionGroup.getBackgroundPermissions();
                    break;
                }
            }
        }
        if (appPermissionGroup == null) {
            appPermissionGroup = AppPermissionGroup.create(getContext(), packageInfo, permission, false);
            if (this.mGroups == null) {
                this.mGroups = new ArrayList();
            }
            this.mGroups.add(appPermissionGroup);
        }
        return appPermissionGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class MyMultiTargetSwitchPreference extends SwitchPreference {
        private View.OnClickListener mSwitchOnClickLister;

        MyMultiTargetSwitchPreference(Context context, final String permission, final AppPermissionGroup appPermissionGroup) {
            super(context);
            setChecked(appPermissionGroup.areRuntimePermissionsGranted(new String[]{permission}));
            setSwitchOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAllAppPermissionsFragment$MyMultiTargetSwitchPreference$jrDHRXdfhJInc6vXnmgkh7OjQ8I
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AutoAllAppPermissionsFragment.MyMultiTargetSwitchPreference.lambda$new$0(appPermissionGroup, permission, view);
                }
            });
        }

        static /* synthetic */ void lambda$new$0(AppPermissionGroup appPermissionGroup, String permission, View v) {
            Switch switchView = (Switch) v;
            if (switchView.isChecked()) {
                appPermissionGroup.grantRuntimePermissions(false, false, new String[]{permission});
                if (appPermissionGroup.doesSupportRuntimePermissions()) {
                    int grantedCount = 0;
                    String[] revokedPermissionsToFix = null;
                    int permissionCount = appPermissionGroup.getPermissions().size();
                    for (int i = 0; i < permissionCount; i++) {
                        Permission current = appPermissionGroup.getPermissions().get(i);
                        if (!current.isGrantedIncludingAppOp()) {
                            if (!current.isUserFixed()) {
                                revokedPermissionsToFix = ArrayUtils.appendString(revokedPermissionsToFix, current.getName());
                            }
                        } else {
                            grantedCount++;
                        }
                    }
                    if (revokedPermissionsToFix != null) {
                        appPermissionGroup.revokeRuntimePermissions(true, revokedPermissionsToFix);
                        return;
                    } else {
                        if (appPermissionGroup.getPermissions().size() == grantedCount) {
                            appPermissionGroup.grantRuntimePermissions(true, false);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            appPermissionGroup.revokeRuntimePermissions(true, new String[]{permission});
            if (appPermissionGroup.doesSupportRuntimePermissions() && !appPermissionGroup.areRuntimePermissionsGranted()) {
                appPermissionGroup.revokeRuntimePermissions(false);
            }
        }

        @Override // androidx.preference.TwoStatePreference
        public void setChecked(boolean checked) {
            if (this.mSwitchOnClickLister == null) {
                super.setChecked(checked);
            }
        }

        void setSwitchOnClickListener(View.OnClickListener listener) {
            this.mSwitchOnClickLister = listener;
        }

        @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder holder) {
            super.onBindViewHolder(holder);
            Switch switchView = (Switch) holder.itemView.findViewById(android.R.id.switch_widget);
            if (switchView != null) {
                switchView.setOnClickListener(this.mSwitchOnClickLister);
            }
        }
    }
}
