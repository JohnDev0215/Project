package com.android.permissioncontroller.role.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArrayMap;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.DefaultAppListChildFragment.Parent;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppListChildFragment<PF extends PreferenceFragmentCompat & Parent> extends Fragment implements Preference.OnPreferenceClickListener {
    private DefaultAppListViewModel mViewModel;
    private static final String PREFERENCE_KEY_MORE_DEFAULT_APPS = DefaultAppListChildFragment.class.getName() + ".preference.MORE_DEFAULT_APPS";
    private static final String PREFERENCE_KEY_MANAGE_DOMAIN_URLS = DefaultAppListChildFragment.class.getName() + ".preference.MANAGE_DOMAIN_URLS";
    private static final String PREFERENCE_KEY_WORK_CATEGORY = DefaultAppListChildFragment.class.getName() + ".preference.WORK_CATEGORY";

    public interface Parent {
        TwoTargetPreference createPreference(Context context);

        void onPreferenceScreenChanged();
    }

    public static DefaultAppListChildFragment newInstance() {
        return new DefaultAppListChildFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DefaultAppListViewModel defaultAppListViewModel = (DefaultAppListViewModel) ViewModelProviders.of(this).get(DefaultAppListViewModel.class);
        this.mViewModel = defaultAppListViewModel;
        defaultAppListViewModel.getLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppListChildFragment$V0P-GcVNShFZj9pKIpThfG1OC8k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onActivityCreated$0$DefaultAppListChildFragment((List) obj);
            }
        });
        if (this.mViewModel.hasWorkProfile()) {
            this.mViewModel.getWorkLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppListChildFragment$C0xV-FwXFzwSdQgkqzMh5Ydlu3s
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$onActivityCreated$1$DefaultAppListChildFragment((List) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onActivityCreated$0$DefaultAppListChildFragment(List roleItems) {
        onRoleListChanged();
    }

    public /* synthetic */ void lambda$onActivityCreated$1$DefaultAppListChildFragment(List roleItems) {
        onRoleListChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onRoleListChanged() {
        List<RoleItem> workRoleItems;
        PreferenceScreen preferenceScreen;
        PreferenceCategory oldWorkPreferenceCategory;
        PreferenceCategory workPreferenceCategory;
        List<RoleItem> roleItems = this.mViewModel.getLiveData().getValue();
        if (roleItems == null) {
            return;
        }
        boolean hasWorkProfile = this.mViewModel.hasWorkProfile();
        if (!hasWorkProfile) {
            workRoleItems = null;
        } else {
            List<RoleItem> workRoleItems2 = this.mViewModel.getWorkLiveData().getValue();
            List<RoleItem> workRoleItems3 = workRoleItems2;
            if (workRoleItems3 != null) {
                workRoleItems = workRoleItems3;
            } else {
                return;
            }
        }
        PreferenceFragmentCompat preferenceFragmentCompatRequirePreferenceFragment = requirePreferenceFragment();
        PreferenceManager preferenceManager = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceManager();
        Context context = preferenceManager.getContext();
        PreferenceScreen preferenceScreen2 = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceScreen();
        ArrayMap<String, Preference> oldPreferences = new ArrayMap<>();
        ArrayMap<String, Preference> oldWorkPreferences = new ArrayMap<>();
        if (preferenceScreen2 == null) {
            PreferenceScreen preferenceScreen3 = preferenceManager.createPreferenceScreen(context);
            preferenceFragmentCompatRequirePreferenceFragment.setPreferenceScreen(preferenceScreen3);
            preferenceScreen = preferenceScreen3;
            oldWorkPreferenceCategory = null;
        } else {
            PreferenceCategory oldWorkPreferenceCategory2 = (PreferenceCategory) preferenceScreen2.findPreference(PREFERENCE_KEY_WORK_CATEGORY);
            if (oldWorkPreferenceCategory2 != null) {
                clearPreferences(oldWorkPreferenceCategory2, oldWorkPreferences);
                preferenceScreen2.removePreference(oldWorkPreferenceCategory2);
                oldWorkPreferenceCategory2.setOrder(Integer.MAX_VALUE);
            }
            clearPreferences(preferenceScreen2, oldPreferences);
            preferenceScreen = preferenceScreen2;
            oldWorkPreferenceCategory = oldWorkPreferenceCategory2;
        }
        PreferenceScreen preferenceScreen4 = preferenceScreen;
        addPreferences(preferenceScreen, roleItems, oldPreferences, this, this.mViewModel.getUser(), context);
        addMoreDefaultAppsPreference(preferenceScreen4, oldPreferences, context);
        addManageDomainUrlsPreference(preferenceScreen4, oldPreferences, context);
        if (hasWorkProfile && !workRoleItems.isEmpty()) {
            PreferenceCategory workPreferenceCategory2 = oldWorkPreferenceCategory;
            if (workPreferenceCategory2 != null) {
                workPreferenceCategory = workPreferenceCategory2;
            } else {
                PreferenceCategory workPreferenceCategory3 = new PreferenceCategory(context);
                workPreferenceCategory3.setKey(PREFERENCE_KEY_WORK_CATEGORY);
                workPreferenceCategory3.setTitle(R.string.default_apps_for_work);
                workPreferenceCategory = workPreferenceCategory3;
            }
            preferenceScreen4.addPreference(workPreferenceCategory);
            addPreferences(workPreferenceCategory, workRoleItems, oldWorkPreferences, this, this.mViewModel.getWorkProfile(), context);
        }
        ((Parent) preferenceFragmentCompatRequirePreferenceFragment).onPreferenceScreenChanged();
    }

    private static void clearPreferences(PreferenceGroup preferenceGroup, ArrayMap<String, Preference> oldPreferences) {
        for (int i = preferenceGroup.getPreferenceCount() - 1; i >= 0; i--) {
            Preference preference = preferenceGroup.getPreference(i);
            preferenceGroup.removePreference(preference);
            preference.setOrder(Integer.MAX_VALUE);
            oldPreferences.put(preference.getKey(), preference);
        }
    }

    private void addPreferences(PreferenceGroup preferenceGroup, List<RoleItem> roleItems, ArrayMap<String, Preference> oldPreferences, Preference.OnPreferenceClickListener listener, UserHandle user, Context context) {
        ActivityResultCaller activityResultCallerRequirePreferenceFragment = requirePreferenceFragment();
        int roleItemsSize = roleItems.size();
        for (int i = 0; i < roleItemsSize; i++) {
            RoleItem roleItem = roleItems.get(i);
            Role role = roleItem.getRole();
            TwoTargetPreference preference = (TwoTargetPreference) oldPreferences.get(role.getName());
            if (preference == null) {
                preference = ((Parent) activityResultCallerRequirePreferenceFragment).createPreference(context);
                preference.setKey(role.getName());
                preference.setIconSpaceReserved(true);
                preference.setTitle(role.getShortLabelResource());
                preference.setPersistent(false);
                preference.setOnPreferenceClickListener(listener);
                preference.getExtras().putParcelable("android.intent.extra.USER", user);
            }
            List<ApplicationInfo> holderApplicationInfos = roleItem.getHolderApplicationInfos();
            if (holderApplicationInfos.isEmpty()) {
                preference.setIcon((Drawable) null);
                preference.setSummary(R.string.default_app_none);
            } else {
                ApplicationInfo holderApplicationInfo = holderApplicationInfos.get(0);
                preference.setIcon(Utils.getBadgedIcon(context, holderApplicationInfo));
                preference.setSummary(Utils.getAppLabel(holderApplicationInfo, context));
            }
            role.preparePreferenceAsUser(preference, user, context);
            preferenceGroup.addPreference(preference);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String roleName = preference.getKey();
        Context context = requireContext();
        Role role = Roles.get(context).get(roleName);
        UserHandle user = (UserHandle) preference.getExtras().getParcelable("android.intent.extra.USER");
        Intent intent = role.getManageIntentAsUser(user, context);
        if (intent == null) {
            intent = DefaultAppActivity.createIntent(roleName, user, context);
        }
        startActivity(intent);
        return true;
    }

    private static void addMoreDefaultAppsPreference(PreferenceGroup preferenceGroup, ArrayMap<String, Preference> oldPreferences, final Context context) {
        final Intent intent = new Intent("android.settings.MANAGE_MORE_DEFAULT_APPS_SETTINGS");
        if (!isIntentResolvedToSettings(intent, context)) {
            return;
        }
        Preference preference = oldPreferences.get(PREFERENCE_KEY_MORE_DEFAULT_APPS);
        if (preference == null) {
            preference = new Preference(context);
            preference.setKey(PREFERENCE_KEY_MORE_DEFAULT_APPS);
            preference.setIconSpaceReserved(true);
            preference.setTitle(context.getString(R.string.default_apps_more));
            preference.setPersistent(false);
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppListChildFragment$NyLsgcocirPb8HwCAoQCrPuvYzg
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return DefaultAppListChildFragment.lambda$addMoreDefaultAppsPreference$2(context, intent, preference2);
                }
            });
        }
        preferenceGroup.addPreference(preference);
    }

    static /* synthetic */ boolean lambda$addMoreDefaultAppsPreference$2(Context context, Intent intent, Preference preference2) {
        context.startActivity(intent);
        return true;
    }

    private static void addManageDomainUrlsPreference(PreferenceGroup preferenceGroup, ArrayMap<String, Preference> oldPreferences, final Context context) {
        final Intent intent = new Intent("android.settings.MANAGE_DOMAIN_URLS");
        if (!isIntentResolvedToSettings(intent, context)) {
            return;
        }
        Preference preference = oldPreferences.get(PREFERENCE_KEY_MANAGE_DOMAIN_URLS);
        if (preference == null) {
            preference = new Preference(context);
            preference.setKey(PREFERENCE_KEY_MANAGE_DOMAIN_URLS);
            preference.setIconSpaceReserved(true);
            preference.setTitle(context.getString(R.string.default_apps_manage_domain_urls));
            preference.setPersistent(false);
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppListChildFragment$Xo5eLLdNp9KeprD0MtDyvshac58
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return DefaultAppListChildFragment.lambda$addManageDomainUrlsPreference$3(context, intent, preference2);
                }
            });
        }
        preferenceGroup.addPreference(preference);
    }

    static /* synthetic */ boolean lambda$addManageDomainUrlsPreference$3(Context context, Intent intent, Preference preference2) {
        context.startActivity(intent);
        return true;
    }

    private static boolean isIntentResolvedToSettings(Intent intent, Context context) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = intent.resolveActivity(packageManager);
        if (componentName == null) {
            return false;
        }
        Intent settingsIntent = new Intent("android.settings.SETTINGS");
        String settingsPackageName = settingsIntent.resolveActivity(packageManager).getPackageName();
        return Objects.equals(componentName.getPackageName(), settingsPackageName);
    }

    private PF requirePreferenceFragment() {
        return (PF) ((PreferenceFragmentCompat) requireParentFragment());
    }
}
