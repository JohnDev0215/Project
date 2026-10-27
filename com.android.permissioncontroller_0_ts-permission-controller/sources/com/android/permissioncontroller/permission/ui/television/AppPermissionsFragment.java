package com.android.permissioncontroller.permission.ui.television;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.util.ArraySet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.livedatatypes.AutoRevokeState;
import com.android.permissioncontroller.permission.ui.ReviewPermissionsActivity;
import com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel;
import com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModelFactory;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Iterator;

/* JADX INFO: loaded from: classes8.dex */
public final class AppPermissionsFragment extends SettingsWithHeader implements Preference.OnPreferenceClickListener {
    private static final String AUTO_REVOKE_SWITCH_KEY = "_AUTO_REVOKE_SWITCH_KEY";
    static final String EXTRA_HIDE_INFO_BUTTON = "hideInfoButton";
    private static final String LOG_TAG = "ManagePermsFragment";
    private static final int MENU_ALL_PERMS = 0;
    private AppPermissions mAppPermissions;
    private PreferenceScreen mExtraScreen;
    private boolean mHasConfirmedRevoke;
    private ArraySet<AppPermissionGroup> mToggledGroups;
    private AppPermissionGroupsViewModel mViewModel;

    public static AppPermissionsFragment newInstance(String packageName, UserHandle user) {
        return (AppPermissionsFragment) setPackage(new AppPermissionsFragment(), packageName, user);
    }

    private static <T extends PermissionsFrameFragment> T setPackage(T fragment, String packageName, UserHandle user) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putParcelable("android.intent.extra.USER", user);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoading(true, false);
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        String packageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        UserHandle user = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        Activity activity = getActivity();
        PackageInfo packageInfo = getPackageInfo(activity, packageName);
        if (packageName == null) {
            Toast.makeText(activity, R.string.app_not_found_dlg_title, 1).show();
            getActivity().finish();
            return;
        }
        AppPermissions appPermissions = new AppPermissions(activity, packageInfo, true, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionsFragment$gBzQ8Z4EApCdjCJdWwmIunzvbIQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreate$0$AppPermissionsFragment();
            }
        });
        this.mAppPermissions = appPermissions;
        if (appPermissions.isReviewRequired()) {
            Intent intent = new Intent(getActivity(), (Class<?>) ReviewPermissionsActivity.class);
            intent.putExtra("android.intent.extra.PACKAGE_NAME", packageName);
            intent.putExtra("android.intent.extra.USER", user);
            startActivity(intent);
            getActivity().finish();
        }
    }

    public /* synthetic */ void lambda$onCreate$0$AppPermissionsFragment() {
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        String packageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        UserHandle user = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        AppPermissionGroupsViewModelFactory factory = new AppPermissionGroupsViewModelFactory(packageName, user, 0L);
        AppPermissionGroupsViewModel appPermissionGroupsViewModel = (AppPermissionGroupsViewModel) new ViewModelProvider(this, factory).get(AppPermissionGroupsViewModel.class);
        this.mViewModel = appPermissionGroupsViewModel;
        appPermissionGroupsViewModel.getAutoRevokeLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionsFragment$tPRlGyVDUQi5zfy4EOy7_0PGJEQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setAutoRevokeToggleState((AutoRevokeState) obj);
            }
        });
        this.mAppPermissions.refresh();
        loadPreferences();
        setPreferencesCheckedState();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 0) {
            PermissionsFrameFragment frag = AllAppPermissionsFragment.newInstance(getArguments().getString("android.intent.extra.PACKAGE_NAME"));
            getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack("AllPerms").commit();
            return true;
        }
        if (itemId == 16908332) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.mAppPermissions != null) {
            bindUi(this, getArguments().getString("android.intent.extra.PACKAGE_NAME"), (UserHandle) getArguments().getParcelable("android.intent.extra.USER"), R.string.app_permissions_decor_title);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0, 0, 0, R.string.all_permissions);
    }

    static void bindUi(SettingsWithHeader fragment, String packageName, UserHandle user, int decorTitleStringResId) {
        Activity activity = fragment.getActivity();
        Application application = activity.getApplication();
        CharSequence label = BidiFormatter.getInstance().unicodeWrap(KotlinUtils.INSTANCE.getPackageLabel(application, packageName, user));
        Drawable icon = KotlinUtils.INSTANCE.getBadgedPackageIcon(application, packageName, user);
        Intent infoIntent = null;
        if (!activity.getIntent().getBooleanExtra(EXTRA_HIDE_INFO_BUTTON, false)) {
            infoIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", packageName, null));
        }
        fragment.setHeader(icon, label, infoIntent, fragment.getString(R.string.additional_permissions_decor_title));
    }

    private void loadPreferences() {
        Context context = getPreferenceManager().getContext();
        if (context == null) {
            return;
        }
        PreferenceScreen screen = getPreferenceScreen();
        screen.removeAll();
        screen.addPreference(createHeaderLineTwoPreference(context));
        PreferenceScreen preferenceScreen = this.mExtraScreen;
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
            this.mExtraScreen = null;
        }
        Preference extraPerms = new Preference(context);
        extraPerms.setIcon(R.drawable.ic_toc);
        extraPerms.setTitle(R.string.additional_permissions);
        Iterator<AppPermissionGroup> it = this.mAppPermissions.getPermissionGroups().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AppPermissionGroup group = it.next();
            if (Utils.shouldShowPermission(getContext(), group)) {
                boolean isPlatform = group.getDeclaringPackage().equals(Utils.OS_PKG);
                Preference preference = new Preference(context);
                preference.setOnPreferenceClickListener(this);
                preference.setKey(group.getName());
                Drawable icon = Utils.loadDrawable(context.getPackageManager(), group.getIconPkg(), group.getIconResId());
                preference.setIcon(Utils.applyTint(getContext(), icon, android.R.attr.colorControlNormal));
                preference.setTitle(group.getLabel());
                if (group.isSystemFixed()) {
                    preference.setSummary(getString(R.string.permission_summary_enabled_system_fixed));
                } else if (group.isPolicyFixed()) {
                    preference.setSummary(getString(R.string.permission_summary_enforced_by_policy));
                }
                preference.setPersistent(false);
                preference.setEnabled((group.isSystemFixed() || group.isPolicyFixed()) ? false : true);
                if (isPlatform) {
                    screen.addPreference(preference);
                } else {
                    if (this.mExtraScreen == null) {
                        PreferenceScreen preferenceScreenCreatePreferenceScreen = getPreferenceManager().createPreferenceScreen(context);
                        this.mExtraScreen = preferenceScreenCreatePreferenceScreen;
                        preferenceScreenCreatePreferenceScreen.addPreference(createHeaderLineTwoPreference(context));
                    }
                    this.mExtraScreen.addPreference(preference);
                }
            }
        }
        final String packageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        final UserHandle user = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        if (this.mExtraScreen != null) {
            extraPerms.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionsFragment$Ekpeph1bS5jU3rxUDvsTGi3t_1U
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$loadPreferences$1$AppPermissionsFragment(packageName, user, preference2);
                }
            });
            int count = this.mExtraScreen.getPreferenceCount() - 1;
            extraPerms.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, count, Integer.valueOf(count)));
            screen.addPreference(extraPerms);
        }
        addAutoRevokePreferences(getPreferenceScreen());
        setLoading(false, true);
    }

    public /* synthetic */ boolean lambda$loadPreferences$1$AppPermissionsFragment(String packageName, UserHandle user, Preference preference) {
        AdditionalPermissionsFragment frag = new AdditionalPermissionsFragment();
        setPackage(frag, packageName, user);
        frag.setTargetFragment(this, 0);
        getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack(null).commit();
        return true;
    }

    private Preference createHeaderLineTwoPreference(Context context) {
        Preference headerLineTwo = new Preference(context) { // from class: com.android.permissioncontroller.permission.ui.television.AppPermissionsFragment.1
            @Override // androidx.preference.Preference
            public void onBindViewHolder(PreferenceViewHolder holder) {
                super.onBindViewHolder(holder);
                holder.itemView.setBackgroundColor(AppPermissionsFragment.this.getResources().getColor(R.color.lb_header_banner_color));
            }
        };
        headerLineTwo.setKey("HeaderPreferenceKey");
        headerLineTwo.setSelectable(false);
        headerLineTwo.setTitle(this.mLabel);
        headerLineTwo.setIcon(this.mIcon);
        return headerLineTwo;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String groupName = preference.getKey();
        AppPermissionGroup group = this.mAppPermissions.getPermissionGroup(groupName);
        if (group == null) {
            return false;
        }
        addToggledGroup(group);
        if (LocationUtils.isLocationGroupAndProvider(getContext(), group.getName(), group.getApp().packageName)) {
            LocationUtils.showLocationDialog(getContext(), this.mAppPermissions.getAppLabel());
            return false;
        }
        AppPermissionFragment frag = new AppPermissionFragment();
        frag.setArguments(AppPermissionFragment.createArgs(group.getApp().packageName, null, group.getName(), group.getUser(), null, 0L, null));
        frag.setTargetFragment(this, 0);
        getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack(null).commit();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.mViewModel.getAutoRevokeLiveData().removeObservers(this);
        super.onPause();
        logToggledGroups();
    }

    private void addToggledGroup(AppPermissionGroup group) {
        if (this.mToggledGroups == null) {
            this.mToggledGroups = new ArraySet<>();
        }
        this.mToggledGroups.add(group);
    }

    private void logToggledGroups() {
        ArraySet<AppPermissionGroup> arraySet = this.mToggledGroups;
        if (arraySet != null) {
            SafetyNetLogger.logPermissionsToggled(arraySet);
            this.mToggledGroups = null;
        }
    }

    private void setPreferencesCheckedState() {
        setPreferencesCheckedState(getPreferenceScreen());
        PreferenceScreen preferenceScreen = this.mExtraScreen;
        if (preferenceScreen != null) {
            setPreferencesCheckedState(preferenceScreen);
        }
        setAutoRevokeToggleState(this.mViewModel.getAutoRevokeLiveData().getValue());
    }

    private void setPreferencesCheckedState(PreferenceScreen screen) {
        AppPermissionGroup group;
        int preferenceCount = screen.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = screen.getPreference(i);
            if (preference.getKey() != null && (group = this.mAppPermissions.getPermissionGroup(preference.getKey())) != null) {
                AppPermissionGroup backgroundGroup = group.getBackgroundPermissions();
                if (group.areRuntimePermissionsGranted()) {
                    if (backgroundGroup == null) {
                        preference.setSummary(R.string.app_permission_button_allow);
                    } else if (backgroundGroup.areRuntimePermissionsGranted()) {
                        preference.setSummary(R.string.permission_access_always);
                    } else {
                        preference.setSummary(R.string.permission_access_only_foreground);
                    }
                } else {
                    preference.setSummary(R.string.permission_access_never);
                }
            }
        }
    }

    private void addAutoRevokePreferences(PreferenceScreen screen) {
        final SwitchPreference autoRevokeSwitch = new SwitchPreference(screen.getPreferenceManager().getContext());
        autoRevokeSwitch.setLayoutResource(R.layout.preference_permissions_revoke);
        autoRevokeSwitch.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionsFragment$d3tGLkb-92gt3k0TIDfDsNS-RA8
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$addAutoRevokePreferences$2$AppPermissionsFragment(autoRevokeSwitch, preference);
            }
        });
        autoRevokeSwitch.setTitle(R.string.auto_revoke_label);
        autoRevokeSwitch.setSummary(R.string.auto_revoke_summary);
        autoRevokeSwitch.setKey(AUTO_REVOKE_SWITCH_KEY);
        screen.addPreference(autoRevokeSwitch);
    }

    public /* synthetic */ boolean lambda$addAutoRevokePreferences$2$AppPermissionsFragment(SwitchPreference autoRevokeSwitch, Preference preference) {
        this.mViewModel.setAutoRevoke(autoRevokeSwitch.isChecked());
        Log.w(LOG_TAG, "setAutoRevoke " + autoRevokeSwitch.isChecked());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAutoRevokeToggleState(AutoRevokeState state) {
        SwitchPreference autoRevokeSwitch = (SwitchPreference) getPreferenceScreen().findPreference(AUTO_REVOKE_SWITCH_KEY);
        if (state == null || autoRevokeSwitch == null) {
            return;
        }
        if (!state.getIsEnabledGlobal() || !state.getShouldShowSwitch()) {
            autoRevokeSwitch.setVisible(false);
        } else {
            autoRevokeSwitch.setVisible(true);
            autoRevokeSwitch.setChecked(state.getIsEnabledForApp());
        }
    }

    private static PackageInfo getPackageInfo(Activity activity, String packageName) {
        try {
            return activity.getPackageManager().getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package:" + activity.getCallingPackage(), e);
            return null;
        }
    }

    public static class AdditionalPermissionsFragment extends SettingsWithHeader {
        AppPermissionsFragment mOuterFragment;

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle savedInstanceState) {
            this.mOuterFragment = (AppPermissionsFragment) getTargetFragment();
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override // com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferenceScreen(this.mOuterFragment.mExtraScreen);
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            AppPermissionsFragment.bindUi(this, getArguments().getString("android.intent.extra.PACKAGE_NAME"), (UserHandle) getArguments().getParcelable("android.intent.extra.USER"), R.string.additional_permissions_decor_title);
        }

        @Override // androidx.fragment.app.Fragment
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == 16908332) {
                getFragmentManager().popBackStack();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
