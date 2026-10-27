package com.android.permissioncontroller.permission.ui.auto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.legacy.PermissionApps;
import com.android.permissioncontroller.permission.ui.handheld.PermissionAppsFragment;
import com.android.permissioncontroller.permission.ui.handheld.PermissionControlPreference;
import com.android.permissioncontroller.permission.utils.Utils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class AutoPermissionAppsFragment extends AutoSettingsFrameFragment implements PermissionApps.Callback {
    private static final String KEY_ALLOWED_FOREGROUND_PERMISSIONS_GROUP = "allowed_foreground_permissions_group";
    private static final String KEY_ALLOWED_PERMISSIONS_GROUP = "allowed_permissions_group";
    private static final String KEY_DENIED_PERMISSIONS_GROUP = "denied_permissions_group";
    private static final String KEY_SHOW_SYSTEM_PREFS = "_showSystem";
    private static final String SHOW_SYSTEM_KEY = AutoPermissionAppsFragment.class.getName() + KEY_SHOW_SYSTEM_PREFS;
    private Collator mCollator;
    private boolean mHasSystemApps;
    private PermissionApps mPermissionApps;
    private boolean mShowSystem;

    public static AutoPermissionAppsFragment newInstance(String permissionName) {
        return (AutoPermissionAppsFragment) setPermissionName(new AutoPermissionAppsFragment(), permissionName);
    }

    private static <T extends Fragment> T setPermissionName(T fragment, String permissionName) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PERMISSION_NAME", permissionName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mShowSystem = savedInstanceState.getBoolean(SHOW_SYSTEM_KEY);
        }
        setLoading(true);
        String groupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
        PermissionApps permissionApps = new PermissionApps(getActivity(), groupName, this);
        this.mPermissionApps = permissionApps;
        permissionApps.refresh(true);
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
        setShowSystemAppsToggle();
        bindUi(this.mPermissionApps, groupName);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getContext()));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOW_SYSTEM_KEY, this.mShowSystem);
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mPermissionApps.refresh(true);
    }

    private void setShowSystemAppsToggle() {
        String label;
        if (!this.mHasSystemApps) {
            setAction(null, null);
            return;
        }
        if (this.mShowSystem) {
            label = getString(R.string.menu_hide_system);
        } else {
            label = getString(R.string.menu_show_system);
        }
        setAction(label, new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoPermissionAppsFragment$EptQjgaieElrasMN4255dw6gh4Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setShowSystemAppsToggle$0$AutoPermissionAppsFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$setShowSystemAppsToggle$0$AutoPermissionAppsFragment(View v) {
        this.mShowSystem = !this.mShowSystem;
        if (this.mPermissionApps.getApps() != null) {
            onPermissionsLoaded(this.mPermissionApps);
        }
        setShowSystemAppsToggle();
    }

    private void bindUi(PermissionApps permissionApps, String groupName) {
        CharSequence label = permissionApps.getFullLabel();
        setHeaderLabel(label);
        Drawable icon = permissionApps.getIcon();
        Preference header = new Preference(getContext());
        header.setTitle(label);
        header.setIcon(icon);
        header.setSummary(Utils.getPermissionGroupDescriptionString(getContext(), groupName, permissionApps.getDescription()));
        getPreferenceScreen().addPreference(header);
        PreferenceGroup allowed = new PreferenceCategory(getContext());
        allowed.setKey(KEY_ALLOWED_PERMISSIONS_GROUP);
        allowed.setTitle(R.string.allowed_header);
        allowed.setVisible(false);
        getPreferenceScreen().addPreference(allowed);
        PreferenceGroup foreground = new PreferenceCategory(getContext());
        foreground.setKey(KEY_ALLOWED_FOREGROUND_PERMISSIONS_GROUP);
        foreground.setTitle(R.string.allowed_foreground_header);
        foreground.setVisible(false);
        getPreferenceScreen().addPreference(foreground);
        PreferenceGroup denied = new PreferenceCategory(getContext());
        denied.setKey(KEY_DENIED_PERMISSIONS_GROUP);
        denied.setTitle(R.string.denied_header);
        denied.setVisible(false);
        getPreferenceScreen().addPreference(denied);
    }

    @Override // com.android.permissioncontroller.permission.model.legacy.PermissionApps.Callback
    public void onPermissionsLoaded(PermissionApps permissionApps) {
        boolean z;
        PreferenceCategory category;
        Map<String, Preference> existingPrefs;
        int numPreferences;
        Context context = getPreferenceManager().getContext();
        if (context != null && getActivity() != null) {
            PreferenceCategory allowed = (PreferenceCategory) findPreference(KEY_ALLOWED_PERMISSIONS_GROUP);
            PreferenceCategory allowedForeground = (PreferenceCategory) findPreference(KEY_ALLOWED_FOREGROUND_PERMISSIONS_GROUP);
            PreferenceCategory denied = (PreferenceCategory) findPreference(KEY_DENIED_PERMISSIONS_GROUP);
            boolean z2 = true;
            allowed.setOrderingAsAdded(true);
            allowedForeground.setOrderingAsAdded(true);
            denied.setOrderingAsAdded(true);
            Map<String, Preference> existingPrefs2 = new ArrayMap<>();
            int numPreferences2 = allowed.getPreferenceCount();
            for (int i = 0; i < numPreferences2; i++) {
                Preference preference = allowed.getPreference(i);
                existingPrefs2.put(preference.getKey(), preference);
            }
            allowed.removeAll();
            int numPreferences3 = allowedForeground.getPreferenceCount();
            for (int i2 = 0; i2 < numPreferences3; i2++) {
                Preference preference2 = allowedForeground.getPreference(i2);
                existingPrefs2.put(preference2.getKey(), preference2);
            }
            allowedForeground.removeAll();
            int numPreferences4 = denied.getPreferenceCount();
            for (int i3 = 0; i3 < numPreferences4; i3++) {
                Preference preference3 = denied.getPreference(i3);
                existingPrefs2.put(preference3.getKey(), preference3);
            }
            denied.removeAll();
            boolean z3 = false;
            this.mHasSystemApps = false;
            boolean hasPermissionWithBackgroundMode = false;
            ArrayList<PermissionApps.PermissionApp> sortedApps = new ArrayList<>(permissionApps.getApps());
            sortedApps.sort(new Comparator() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoPermissionAppsFragment$jT43gHVgaZyWx4JREVivcNIVQVo
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return this.f$0.lambda$onPermissionsLoaded$1$AutoPermissionAppsFragment((PermissionApps.PermissionApp) obj, (PermissionApps.PermissionApp) obj2);
                }
            });
            int i4 = 0;
            while (i4 < sortedApps.size()) {
                PermissionApps.PermissionApp app = sortedApps.get(i4);
                AppPermissionGroup group = app.getPermissionGroup();
                hasPermissionWithBackgroundMode = (hasPermissionWithBackgroundMode || group.hasPermissionWithBackgroundMode()) ? z2 : z3;
                if (!Utils.shouldShowPermission(getContext(), group)) {
                    existingPrefs = existingPrefs2;
                    numPreferences = numPreferences4;
                } else if (!app.getAppInfo().enabled) {
                    existingPrefs = existingPrefs2;
                    numPreferences = numPreferences4;
                } else {
                    String key = app.getKey();
                    Preference existingPref = existingPrefs2.get(key);
                    if (existingPref != null) {
                        existingPref.setOrder(Integer.MAX_VALUE);
                    }
                    boolean isSystemApp = Utils.isGroupOrBgGroupUserSensitive(group) ^ z2;
                    if (isSystemApp) {
                        this.mHasSystemApps = z2;
                    }
                    if (isSystemApp && !this.mShowSystem) {
                        existingPrefs = existingPrefs2;
                        numPreferences = numPreferences4;
                    } else {
                        if (group.areRuntimePermissionsGranted()) {
                            if (!group.hasPermissionWithBackgroundMode() || (group.getBackgroundPermissions() != null && group.getBackgroundPermissions().areRuntimePermissionsGranted())) {
                                category = allowed;
                            } else {
                                category = allowedForeground;
                            }
                        } else {
                            category = denied;
                        }
                        if (existingPref != null) {
                            category.addPreference(existingPref);
                            existingPrefs = existingPrefs2;
                            numPreferences = numPreferences4;
                        } else {
                            existingPrefs = existingPrefs2;
                            numPreferences = numPreferences4;
                            PermissionControlPreference pref = new PermissionControlPreference(context, group, PermissionAppsFragment.class.getName());
                            pref.setKey(key);
                            pref.setIcon(app.getIcon());
                            pref.setTitle(Utils.getFullAppLabel(app.getAppInfo(), context));
                            pref.setEllipsizeEnd();
                            pref.useSmallerIcon();
                            category.addPreference(pref);
                        }
                    }
                }
                i4++;
                existingPrefs2 = existingPrefs;
                numPreferences4 = numPreferences;
                z2 = true;
                z3 = false;
            }
            if (hasPermissionWithBackgroundMode) {
                allowed.setTitle(R.string.allowed_always_header);
            }
            if (allowed.getPreferenceCount() == 0) {
                Preference empty = new Preference(context);
                empty.setTitle(R.string.no_apps_allowed);
                empty.setSelectable(false);
                allowed.addPreference(empty);
            }
            allowed.setVisible(true);
            allowedForeground.setVisible(allowedForeground.getPreferenceCount() > 0);
            if (denied.getPreferenceCount() != 0) {
                z = false;
            } else {
                Preference empty2 = new Preference(context);
                empty2.setTitle(R.string.no_apps_denied);
                z = false;
                empty2.setSelectable(false);
                denied.addPreference(empty2);
            }
            denied.setVisible(true);
            setShowSystemAppsToggle();
            setLoading(z);
        }
    }

    public /* synthetic */ int lambda$onPermissionsLoaded$1$AutoPermissionAppsFragment(PermissionApps.PermissionApp x, PermissionApps.PermissionApp y) {
        int result = this.mCollator.compare(x.getLabel(), y.getLabel());
        if (result == 0) {
            return x.getUid() - y.getUid();
        }
        return result;
    }
}
