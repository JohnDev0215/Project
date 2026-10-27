package com.android.permissioncontroller.permission.ui.television;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.legacy.PermissionApps;
import com.android.permissioncontroller.permission.ui.ReviewPermissionsActivity;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;

/* JADX INFO: loaded from: classes8.dex */
public final class PermissionAppsFragment extends SettingsWithHeader implements PermissionApps.Callback, Preference.OnPreferenceClickListener {
    private static final String KEY_CATEGORY_ALLOWED = "_allowed";
    private static final String KEY_CATEGORY_DENIED = "_denied";
    private static final String KEY_NO_APPS_ALLOWED = "_noAppsAllowed";
    private static final String KEY_NO_APPS_DENIED = "_noAppsDenied";
    private static final String KEY_SHOW_SYSTEM_PREFS = "_showSystem";
    private static final int MENU_HIDE_SYSTEM = 2;
    private static final int MENU_SHOW_SYSTEM = 1;
    private PreferenceScreen mExtraScreen;
    private boolean mHasConfirmedRevoke;
    private boolean mHasSystemApps;
    private MenuItem mHideSystemMenu;
    private PermissionApps.Callback mOnPermissionsLoadedListener;
    private PermissionApps mPermissionApps;
    private boolean mShowSystem;
    private MenuItem mShowSystemMenu;
    private ArraySet<AppPermissionGroup> mToggledGroups;

    public static PermissionAppsFragment newInstance(String permissionName) {
        return (PermissionAppsFragment) setPermissionName(new PermissionAppsFragment(), permissionName);
    }

    private static <T extends PermissionsFrameFragment> T setPermissionName(T fragment, String permissionName) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PERMISSION_NAME", permissionName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoading(true, false);
        String groupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
        this.mPermissionApps = new PermissionApps(getActivity(), groupName, this);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        this.mPermissionApps.refresh(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.mHasSystemApps) {
            this.mShowSystemMenu = menu.add(0, 1, 0, R.string.menu_show_system);
            this.mHideSystemMenu = menu.add(0, 2, 0, R.string.menu_hide_system);
            updateMenu();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 1 || itemId == 2) {
            this.mShowSystem = item.getItemId() == 1;
            if (this.mPermissionApps.getApps() != null) {
                onPermissionsLoaded(this.mPermissionApps);
            }
            updateMenu();
        } else if (itemId == 16908332) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMenu() {
        this.mShowSystemMenu.setVisible(!this.mShowSystem);
        this.mHideSystemMenu.setVisible(this.mShowSystem);
    }

    @Override // com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment
    protected void onSetEmptyText(TextView textView) {
        textView.setText(R.string.no_apps);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindUi(this, this.mPermissionApps);
    }

    private static void bindUi(SettingsWithHeader fragment, PermissionApps permissionApps) {
        permissionApps.getIcon();
        CharSequence label = permissionApps.getLabel();
        fragment.setHeader(null, null, null, fragment.getString(R.string.permission_apps_decor_title, label));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnPermissionsLoadedListener(PermissionApps.Callback callback) {
        this.mOnPermissionsLoadedListener = callback;
    }

    @Override // com.android.permissioncontroller.permission.model.legacy.PermissionApps.Callback
    public void onPermissionsLoaded(PermissionApps permissionApps) {
        int allowedSystemApps;
        Context context = getPreferenceManager().getContext();
        if (context != null) {
            this.mHasSystemApps = false;
            boolean isTelevision = DeviceUtils.isTelevision(context);
            ArraySet<PermissionApps.PermissionApp> mainScreenApps = new ArraySet<>();
            ArraySet<PermissionApps.PermissionApp> systemScreenApps = new ArraySet<>();
            for (PermissionApps.PermissionApp app : permissionApps.getApps()) {
                if (Utils.shouldShowPermission(getContext(), app.getPermissionGroup())) {
                    boolean isSystemApp = !Utils.isGroupOrBgGroupUserSensitive(app.getPermissionGroup());
                    if (isSystemApp) {
                        this.mHasSystemApps = true;
                    }
                    if (isSystemApp && isTelevision) {
                        systemScreenApps.add(app);
                    } else if (!isSystemApp || this.mShowSystem) {
                        mainScreenApps.add(app);
                    }
                }
            }
            PreferenceScreen mainScreen = getPreferenceScreen();
            if (systemScreenApps.isEmpty()) {
                this.mExtraScreen = null;
            } else if (this.mExtraScreen == null) {
                this.mExtraScreen = getPreferenceManager().createPreferenceScreen(context);
            }
            PreferenceCategory allowedCategory = findOrCreateCategory(context, mainScreen, KEY_CATEGORY_ALLOWED, R.string.allowed_header);
            PreferenceCategory deniedCategory = findOrCreateCategory(context, mainScreen, KEY_CATEGORY_DENIED, R.string.denied_header);
            updateCategories(context, allowedCategory, deniedCategory, mainScreenApps);
            Preference showSystemAppPref = mainScreen.findPreference(KEY_SHOW_SYSTEM_PREFS);
            PreferenceScreen preferenceScreen = this.mExtraScreen;
            if (preferenceScreen != null) {
                PreferenceCategory allowedCategory2 = findOrCreateCategory(context, preferenceScreen, KEY_CATEGORY_ALLOWED, R.string.allowed_header);
                PreferenceCategory deniedCategory2 = findOrCreateCategory(context, this.mExtraScreen, KEY_CATEGORY_DENIED, R.string.denied_header);
                updateCategories(context, allowedCategory2, deniedCategory2, systemScreenApps);
                if (showSystemAppPref == null) {
                    showSystemAppPref = new Preference(context);
                    showSystemAppPref.setKey(KEY_SHOW_SYSTEM_PREFS);
                    showSystemAppPref.setIcon(Utils.applyTint(context, R.drawable.ic_toc, android.R.attr.colorControlNormal));
                    showSystemAppPref.setTitle(R.string.preference_show_system_apps);
                    showSystemAppPref.setOnPreferenceClickListener(this);
                    mainScreen.addPreference(showSystemAppPref);
                }
                if (allowedCategory2.findPreference(KEY_NO_APPS_ALLOWED) != null) {
                    allowedSystemApps = 0;
                } else {
                    allowedSystemApps = allowedCategory2.getPreferenceCount();
                }
                showSystemAppPref.setSummary(getString(R.string.app_permissions_group_summary, Integer.valueOf(allowedSystemApps), Integer.valueOf(systemScreenApps.size())));
            } else if (showSystemAppPref != null) {
                mainScreen.removePreference(showSystemAppPref);
            }
            setLoading(false, true);
            PermissionApps.Callback callback = this.mOnPermissionsLoadedListener;
            if (callback != null) {
                callback.onPermissionsLoaded(permissionApps);
            }
            if (this.mHasSystemApps) {
                getActivity().invalidateOptionsMenu();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[PHI: r6 r7
  0x004d: PHI (r6v3 'hasAllowed' boolean) = (r6v1 'hasAllowed' boolean), (r6v22 'hasAllowed' boolean) binds: [B:15:0x0049, B:12:0x0040] A[DONT_GENERATE, DONT_INLINE]
  0x004d: PHI (r7v4 'hasDenied' boolean) = (r7v2 'hasDenied' boolean), (r7v1 'hasDenied' boolean) binds: [B:15:0x0049, B:12:0x0040] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateCategories(android.content.Context r21, androidx.preference.PreferenceCategory r22, androidx.preference.PreferenceCategory r23, android.util.ArraySet<com.android.permissioncontroller.permission.model.legacy.PermissionApps.PermissionApp> r24) {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.ui.television.PermissionAppsFragment.updateCategories(android.content.Context, androidx.preference.PreferenceCategory, androidx.preference.PreferenceCategory, android.util.ArraySet):void");
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (KEY_SHOW_SYSTEM_PREFS.equals(key)) {
            SystemAppsFragment frag = new SystemAppsFragment();
            setPermissionName(frag, getArguments().getString("android.intent.extra.PERMISSION_NAME"));
            frag.setTargetFragment(this, 0);
            getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack("SystemApps").commit();
            return true;
        }
        PermissionApps.PermissionApp app = this.mPermissionApps.getApp(key);
        if (LocationUtils.isLocationGroupAndProvider(getContext(), this.mPermissionApps.getGroupName(), app.getPackageName())) {
            LocationUtils.showLocationDialog(getContext(), app.getLabel());
            return true;
        }
        addToggledGroup(app.getPackageName(), app.getPermissionGroup());
        if (app.isReviewRequired()) {
            Intent intent = new Intent(getActivity(), (Class<?>) ReviewPermissionsActivity.class);
            intent.putExtra("android.intent.extra.PACKAGE_NAME", app.getPackageName());
            startActivity(intent);
            return true;
        }
        AppPermissionFragment frag2 = new AppPermissionFragment();
        frag2.setArguments(AppPermissionFragment.createArgs(app.getPackageName(), null, app.getPermissionGroup().getName(), app.getPermissionGroup().getUser(), null, 0L, null));
        frag2.setTargetFragment(this, 0);
        getFragmentManager().beginTransaction().replace(android.R.id.content, frag2).addToBackStack(null).commit();
        return true;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        logToggledGroups();
    }

    private PreferenceCategory findOrCreateCategory(Context context, PreferenceScreen screen, String key, int titleResId) {
        PreferenceCategory category = (PreferenceCategory) screen.findPreference(key);
        if (category == null) {
            PreferenceCategory category2 = new PreferenceCategory(context);
            category2.setKey(key);
            category2.setTitle(titleResId);
            screen.addPreference(category2);
            return category2;
        }
        return category;
    }

    private ArraySet<String> getAllPreferencesKeys(PreferenceGroup group) {
        ArraySet<String> keys = new ArraySet<>();
        if (group != null) {
            int n = group.getPreferenceCount();
            for (int i = 0; i < n; i++) {
                keys.add(group.getPreference(i).getKey());
            }
        }
        return keys;
    }

    private void removePreferences(PreferenceGroup group, ArraySet<String> keys) {
        if (group == null || keys == null) {
            return;
        }
        int n = keys.size();
        for (int i = 0; i < n; i++) {
            Preference preference = group.findPreference(keys.valueAt(i));
            if (preference != null) {
                group.removePreference(preference);
            }
        }
    }

    private void showWarningIfNeeded(final PermissionApps.PermissionApp app) {
        final boolean grantedByDefault = app.hasGrantedByDefaultPermissions();
        if (grantedByDefault || (!app.doesSupportRuntimePermissions() && !this.mHasConfirmedRevoke)) {
            new AlertDialog.Builder(getContext()).setMessage(grantedByDefault ? R.string.system_warning : R.string.old_sdk_deny_warning).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.grant_dialog_button_deny_anyway, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$PermissionAppsFragment$ll3uGfRm33aF4clSMjGJ-ccBUBk
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$showWarningIfNeeded$0$PermissionAppsFragment(app, grantedByDefault, dialogInterface, i);
                }
            }).show();
        } else {
            app.revokeRuntimePermissions();
        }
    }

    public /* synthetic */ void lambda$showWarningIfNeeded$0$PermissionAppsFragment(PermissionApps.PermissionApp app, boolean grantedByDefault, DialogInterface dialog, int which) {
        app.revokeRuntimePermissions();
        if (!grantedByDefault) {
            this.mHasConfirmedRevoke = true;
        }
    }

    private void addToggledGroup(String packageName, AppPermissionGroup group) {
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

    public static class SystemAppsFragment extends SettingsWithHeader implements PermissionApps.Callback {
        PermissionAppsFragment mOuterFragment;

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onCreate(Bundle savedInstanceState) {
            this.mOuterFragment = (PermissionAppsFragment) getTargetFragment();
            setLoading(true, false);
            super.onCreate(savedInstanceState);
        }

        @Override // com.android.permissioncontroller.permission.ui.television.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            if (this.mOuterFragment.mExtraScreen == null) {
                this.mOuterFragment.setOnPermissionsLoadedListener(this);
            } else {
                setPreferenceScreen();
            }
        }

        @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            String groupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
            PermissionApps permissionApps = new PermissionApps(getActivity(), groupName, (PermissionApps.Callback) null);
            bindUi(this, permissionApps);
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            this.mOuterFragment.mPermissionApps.refresh(true);
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            this.mOuterFragment.setOnPermissionsLoadedListener(null);
        }

        private static void bindUi(SettingsWithHeader fragment, PermissionApps permissionApps) {
            CharSequence label = permissionApps.getLabel();
            fragment.setHeader(null, null, null, fragment.getString(R.string.system_apps_decor_title, label));
        }

        @Override // com.android.permissioncontroller.permission.model.legacy.PermissionApps.Callback
        public void onPermissionsLoaded(PermissionApps permissionApps) {
            setPreferenceScreen();
        }

        private void setPreferenceScreen() {
            setPreferenceScreen(this.mOuterFragment.mExtraScreen);
            setLoading(false, true);
        }
    }
}
