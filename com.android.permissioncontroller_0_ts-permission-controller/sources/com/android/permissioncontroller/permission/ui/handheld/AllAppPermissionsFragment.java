package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.PackagePermissionsLiveData;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.ui.handheld.AllAppPermissionsFragment;
import com.android.permissioncontroller.permission.ui.model.AllAppPermissionsViewModel;
import com.android.permissioncontroller.permission.ui.model.AllAppPermissionsViewModelFactory;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes8.dex */
public final class AllAppPermissionsFragment extends SettingsWithLargeHeader {
    private static final String KEY_OTHER = "other_perms";
    private static final String LOG_TAG = "AllAppPermissionsFragment";
    private Collator mCollator;
    private String mFilterGroup;
    private String mPackageName;
    private UserHandle mUser;
    private AllAppPermissionsViewModel mViewModel;

    public static AllAppPermissionsFragment newInstance(String packageName, String filterGroup, UserHandle userHandle) {
        AllAppPermissionsFragment instance = new AllAppPermissionsFragment();
        instance.setArguments(createArgs(packageName, filterGroup, userHandle));
        return instance;
    }

    public static Bundle createArgs(String packageName, String filterGroup, UserHandle userHandle) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putString("android.intent.extra.PERMISSION_GROUP_NAME", filterGroup);
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        return arguments;
    }

    public static Bundle createArgs(String packageName, UserHandle userHandle) {
        return createArgs(packageName, null, userHandle);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPackageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        this.mFilterGroup = getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        this.mUser = userHandle;
        if (this.mPackageName == null || userHandle == null) {
            Log.e(LOG_TAG, "Missing required argument EXTRA_PACKAGE_NAME or EXTRA_USER");
            UtilsKt.pressBack(this);
        }
        AllAppPermissionsViewModelFactory factory = new AllAppPermissionsViewModelFactory(this.mPackageName, this.mUser, this.mFilterGroup);
        AllAppPermissionsViewModel allAppPermissionsViewModel = (AllAppPermissionsViewModel) new ViewModelProvider(this, factory).get(AllAppPermissionsViewModel.class);
        this.mViewModel = allAppPermissionsViewModel;
        allAppPermissionsViewModel.getAllPackagePermissionsLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AllAppPermissionsFragment$UoOYNrV360FNS0JmzypS_unnpTA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.updateUi((Map) obj);
            }
        });
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        if (getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME") == null) {
            getActivity().setTitle(R.string.all_permissions);
        } else {
            getActivity().setTitle(R.string.app_permissions);
        }
        setHasOptionsMenu(true);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUi(Map<String, List<String>> groupMap) {
        if (groupMap == null && this.mViewModel.getAllPackagePermissionsLiveData().getIsInitialized()) {
            Toast.makeText(getActivity(), R.string.app_not_found_dlg_title, 1).show();
            Log.w(LOG_TAG, "invalid package " + this.mPackageName);
            UtilsKt.pressBack(this);
            return;
        }
        if (getPreferenceScreen() == null) {
            addPreferencesFromResource(R.xml.all_permissions);
        }
        PreferenceGroup otherGroup = (PreferenceGroup) findPreference(KEY_OTHER);
        otherGroup.removeAll();
        Preference header = findPreference(" HEADER_PREFERENCE");
        getPreferenceScreen().removeAll();
        getPreferenceScreen().addPreference(otherGroup);
        getPreferenceScreen().addPreference(header);
        Drawable icon = KotlinUtils.INSTANCE.getBadgedPackageIcon(getActivity().getApplication(), this.mPackageName, this.mUser);
        CharSequence label = KotlinUtils.INSTANCE.getPackageLabel(getActivity().getApplication(), this.mPackageName, this.mUser);
        Intent infoIntent = null;
        if (!getActivity().getIntent().getBooleanExtra("hideInfoButton", false)) {
            infoIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", this.mPackageName, null));
        }
        setHeader(icon, label, infoIntent, this.mUser, false);
        if (groupMap != null) {
            for (String groupName : groupMap.keySet()) {
                List<String> permissions = groupMap.get(groupName);
                if (permissions != null && !permissions.isEmpty()) {
                    PreferenceGroup pref = findOrCreatePrefGroup(groupName);
                    for (String permName : permissions) {
                        pref.addPreference(getPreference(permName, groupName));
                    }
                }
            }
        }
        if (otherGroup.getPreferenceCount() == 0) {
            otherGroup.setVisible(false);
        } else {
            otherGroup.setVisible(true);
        }
        KotlinUtils.INSTANCE.sortPreferenceGroup(getPreferenceScreen(), new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AllAppPermissionsFragment$YJGolH7gutG21OHrjtsCjXjCyzc
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(this.f$0.comparePreferences((Preference) obj, (Preference) obj2));
            }
        }, true);
        setLoading(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int comparePreferences(Preference lhs, Preference rhs) {
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
        return this.mCollator.compare(lhs.getTitle().toString(), rhs.getTitle().toString());
    }

    private PreferenceGroup findOrCreatePrefGroup(String groupName) {
        if (groupName.equals(PackagePermissionsLiveData.NON_RUNTIME_NORMAL_PERMS)) {
            return (PreferenceGroup) findPreference(KEY_OTHER);
        }
        PreferenceGroup pref = (PreferenceGroup) findPreference(groupName);
        if (pref == null) {
            PreferenceGroup pref2 = new PreferenceCategory(getPreferenceManager().getContext());
            pref2.setKey(groupName);
            pref2.setTitle(KotlinUtils.INSTANCE.getPermGroupLabel(getContext(), groupName));
            getPreferenceScreen().addPreference(pref2);
            return pref2;
        }
        pref.removeAll();
        return pref;
    }

    private Preference getPreference(String permName, String groupName) {
        Preference pref;
        Context context = getPreferenceManager().getContext();
        final boolean mutable = Utils.isPermissionIndividuallyControlled(getContext(), permName);
        if (mutable) {
            AppPermissionGroup appPermGroup = AppPermissionGroup.create(getActivity().getApplication(), this.mPackageName, groupName, this.mUser, false);
            pref = new MyMultiTargetSwitchPreference(context, permName, appPermGroup);
        } else {
            pref = new Preference(context);
        }
        pref.setIcon(KotlinUtils.INSTANCE.getPermInfoIcon(context, permName));
        pref.setTitle(KotlinUtils.INSTANCE.getPermInfoLabel(context, permName));
        pref.setSingleLineTitle(false);
        final CharSequence desc = KotlinUtils.INSTANCE.getPermInfoDescription(context, permName);
        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AllAppPermissionsFragment$bHNNYYT5wSLpIW_9YZbbqdL0by0
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$getPreference$0$AllAppPermissionsFragment(desc, mutable, preference);
            }
        });
        return pref;
    }

    public /* synthetic */ boolean lambda$getPreference$0$AllAppPermissionsFragment(CharSequence desc, boolean mutable, Preference preference) {
        new AlertDialog.Builder(getContext()).setMessage(desc).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null).show();
        return mutable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class MyMultiTargetSwitchPreference extends MultiTargetSwitchPreference {
        MyMultiTargetSwitchPreference(Context context, final String permission, final AppPermissionGroup appPermissionGroup) {
            super(context);
            setChecked(appPermissionGroup.areRuntimePermissionsGranted(new String[]{permission}));
            setSwitchOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AllAppPermissionsFragment$MyMultiTargetSwitchPreference$81T36DG6tYtM1Ab_PZRRvevHWpk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AllAppPermissionsFragment.MyMultiTargetSwitchPreference.lambda$new$0(appPermissionGroup, permission, view);
                }
            });
        }

        static /* synthetic */ void lambda$new$0(AppPermissionGroup appPermissionGroup, String permission, View v) {
            Switch switchView = (Switch) v;
            if (switchView.isChecked()) {
                appPermissionGroup.grantRuntimePermissions(true, false, new String[]{permission});
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
    }
}
