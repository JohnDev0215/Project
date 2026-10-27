package com.android.permissioncontroller.permission.ui.auto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: loaded from: classes8.dex */
public class AutoAppPermissionsFragment extends AutoSettingsFrameFragment {
    private static final String KEY_ALLOWED_PERMISSIONS_GROUP = "allowed_permissions_group";
    private static final String KEY_DENIED_PERMISSIONS_GROUP = "denied_permissions_group";
    private AppPermissions mAppPermissions;
    private Collator mCollator;
    private PreferenceScreen mExtraScreen;

    public static AutoAppPermissionsFragment newInstance(String packageName, UserHandle userHandle) {
        return (AutoAppPermissionsFragment) setPackageNameAndUserHandle(new AutoAppPermissionsFragment(), packageName, userHandle);
    }

    private static <T extends Fragment> T setPackageNameAndUserHandle(T fragment, String packageName, UserHandle userHandle) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoading(true);
        String packageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        UserHandle userHandle = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        Activity activity = requireActivity();
        PackageInfo packageInfo = AutoPermissionsUtils.getPackageInfo(activity, packageName, userHandle);
        if (packageInfo == null) {
            Toast.makeText(getContext(), R.string.app_not_found_dlg_title, 1).show();
            activity.finish();
        } else {
            setHeaderLabel(getContext().getString(R.string.app_permissions));
            setAction(getContext().getString(R.string.all_permissions), new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionsFragment$pHF1mhsaavx4itZWKSdbRLNe6X0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreate$0$AutoAppPermissionsFragment(view);
                }
            });
            this.mAppPermissions = new AppPermissions(activity, packageInfo, true, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionsFragment$5_ffpF3rTSzKcEyAzGFumYDf9zo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreate$1$AutoAppPermissionsFragment();
                }
            });
            this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
        }
    }

    public /* synthetic */ void lambda$onCreate$0$AutoAppPermissionsFragment(View v) {
        showAllPermissions();
    }

    public /* synthetic */ void lambda$onCreate$1$AutoAppPermissionsFragment() {
        getActivity().finish();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mAppPermissions.refresh();
        bindUi(this.mAppPermissions.getPackageInfo());
        updatePreferences();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getPreferenceScreen().removeAll();
    }

    private void showAllPermissions() {
        Fragment frag = AutoAllAppPermissionsFragment.newInstance(getArguments().getString("android.intent.extra.PACKAGE_NAME"), (UserHandle) getArguments().getParcelable("android.intent.extra.USER"));
        getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack("AllPerms").commit();
    }

    protected void bindUi(PackageInfo packageInfo) {
        getPreferenceScreen().addPreference(AutoPermissionsUtils.createHeaderPreference(getContext(), packageInfo.applicationInfo));
        PreferenceGroup allowed = new PreferenceCategory(getContext());
        allowed.setKey(KEY_ALLOWED_PERMISSIONS_GROUP);
        allowed.setTitle(R.string.allowed_header);
        getPreferenceScreen().addPreference(allowed);
        PreferenceGroup denied = new PreferenceCategory(getContext());
        denied.setKey(KEY_DENIED_PERMISSIONS_GROUP);
        denied.setTitle(R.string.denied_header);
        getPreferenceScreen().addPreference(denied);
    }

    private void updatePreferences() {
        Context context = getPreferenceManager().getContext();
        if (context == null) {
            return;
        }
        PreferenceCategory allowed = (PreferenceCategory) findPreference(KEY_ALLOWED_PERMISSIONS_GROUP);
        PreferenceCategory denied = (PreferenceCategory) findPreference(KEY_DENIED_PERMISSIONS_GROUP);
        allowed.removeAll();
        denied.removeAll();
        PreferenceScreen preferenceScreen = this.mExtraScreen;
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
            this.mExtraScreen.addPreference(AutoPermissionsUtils.createHeaderPreference(getContext(), this.mAppPermissions.getPackageInfo().applicationInfo));
        }
        Preference extraPerms = new Preference(context);
        extraPerms.setIcon(R.drawable.ic_toc);
        extraPerms.setTitle(R.string.additional_permissions);
        boolean extraPermsAreAllowed = false;
        ArrayList<AppPermissionGroup> groups = new ArrayList<>(this.mAppPermissions.getPermissionGroups());
        groups.sort(new Comparator() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionsFragment$CbjxU7i3eCV4SG0it_Oq5QqfSpo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.lambda$updatePreferences$2$AutoAppPermissionsFragment((AppPermissionGroup) obj, (AppPermissionGroup) obj2);
            }
        });
        allowed.setOrderingAsAdded(true);
        denied.setOrderingAsAdded(true);
        for (int i = 0; i < groups.size(); i++) {
            AppPermissionGroup group = groups.get(i);
            if (Utils.shouldShowPermission(getContext(), group)) {
                boolean isPlatform = group.getDeclaringPackage().equals(Utils.OS_PKG);
                Preference preference = createPermissionPreference(getContext(), group);
                if (isPlatform) {
                    PreferenceCategory category = group.areRuntimePermissionsGranted() ? allowed : denied;
                    category.addPreference(preference);
                } else {
                    if (this.mExtraScreen == null) {
                        PreferenceScreen preferenceScreenCreatePreferenceScreen = getPreferenceManager().createPreferenceScreen(context);
                        this.mExtraScreen = preferenceScreenCreatePreferenceScreen;
                        preferenceScreenCreatePreferenceScreen.addPreference(AutoPermissionsUtils.createHeaderPreference(getContext(), this.mAppPermissions.getPackageInfo().applicationInfo));
                    }
                    this.mExtraScreen.addPreference(preference);
                    if (group.areRuntimePermissionsGranted()) {
                        extraPermsAreAllowed = true;
                    }
                }
            }
        }
        if (this.mExtraScreen != null) {
            extraPerms.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionsFragment$mzTmAvf4YOPrLFkOMZHcihQBE3M
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$updatePreferences$3$AutoAppPermissionsFragment(preference2);
                }
            });
            int count = this.mExtraScreen.getPreferenceCount() - 1;
            extraPerms.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, count, Integer.valueOf(count)));
            PreferenceCategory category2 = extraPermsAreAllowed ? allowed : denied;
            category2.addPreference(extraPerms);
        }
        if (allowed.getPreferenceCount() == 0) {
            Preference empty = new Preference(context);
            empty.setTitle(getString(R.string.no_permissions_allowed));
            empty.setSelectable(false);
            allowed.addPreference(empty);
        }
        if (denied.getPreferenceCount() == 0) {
            Preference empty2 = new Preference(context);
            empty2.setTitle(getString(R.string.no_permissions_denied));
            empty2.setSelectable(false);
            denied.addPreference(empty2);
        }
        setLoading(false);
    }

    public /* synthetic */ int lambda$updatePreferences$2$AutoAppPermissionsFragment(AppPermissionGroup x, AppPermissionGroup y) {
        return this.mCollator.compare(x.getLabel(), y.getLabel());
    }

    public /* synthetic */ boolean lambda$updatePreferences$3$AutoAppPermissionsFragment(Preference preference) {
        AdditionalPermissionsFragment frag = new AdditionalPermissionsFragment();
        setPackageNameAndUserHandle(frag, getArguments().getString("android.intent.extra.PACKAGE_NAME"), (UserHandle) getArguments().getParcelable("android.intent.extra.USER"));
        frag.setTargetFragment(this, 0);
        getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack(null).commit();
        return true;
    }

    private Preference createPermissionPreference(final Context context, final AppPermissionGroup group) {
        Preference preference = new Preference(context);
        Drawable icon = Utils.loadDrawable(context.getPackageManager(), group.getIconPkg(), group.getIconResId());
        preference.setKey(group.getName());
        preference.setTitle(group.getFullLabel());
        preference.setIcon(Utils.applyTint(context, icon, android.R.attr.colorControlNormal));
        preference.setSummary(getPreferenceSummary(group));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionsFragment$cqvLblr48rlXsgUhsKmaFNf8hw0
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return AutoAppPermissionsFragment.lambda$createPermissionPreference$4(group, context, preference2);
            }
        });
        return preference;
    }

    static /* synthetic */ boolean lambda$createPermissionPreference$4(AppPermissionGroup group, Context context, Preference pref) {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSION");
        intent.putExtra("android.intent.extra.PACKAGE_NAME", group.getApp().packageName);
        intent.putExtra("android.intent.extra.PERMISSION_NAME", group.getPermissions().get(0).getName());
        intent.putExtra("android.intent.extra.USER", group.getUser());
        intent.putExtra("com.android.permissioncontroller.extra.CALLER_NAME", AutoAppPermissionsFragment.class.getName());
        context.startActivity(intent);
        return true;
    }

    private String getPreferenceSummary(AppPermissionGroup group) {
        return getGroupSummary(group);
    }

    private String getGroupSummary(AppPermissionGroup group) {
        if (group.hasPermissionWithBackgroundMode() && group.areRuntimePermissionsGranted()) {
            AppPermissionGroup backgroundGroup = group.getBackgroundPermissions();
            if (backgroundGroup == null || !backgroundGroup.areRuntimePermissionsGranted()) {
                return getContext().getString(R.string.permission_subtitle_only_in_foreground);
            }
            return null;
        }
        return null;
    }

    public static class AdditionalPermissionsFragment extends AutoSettingsFrameFragment {
        AutoAppPermissionsFragment mOuterFragment;

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle savedInstanceState) {
            this.mOuterFragment = (AutoAppPermissionsFragment) getTargetFragment();
            super.onCreate(savedInstanceState);
            setHeaderLabel(this.mOuterFragment.getHeaderLabel());
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String s) {
            setPreferenceScreen(this.mOuterFragment.mExtraScreen);
        }
    }
}
