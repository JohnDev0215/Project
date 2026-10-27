package com.android.permissioncontroller.role.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Pair;
import androidx.activity.result.ActivityResultCaller;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent;
import com.android.permissioncontroller.role.ui.DefaultAppConfirmationDialogFragment;
import com.android.permissioncontroller.role.ui.DefaultAppViewModel;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppChildFragment<PF extends PreferenceFragmentCompat & Parent> extends Fragment implements DefaultAppConfirmationDialogFragment.Listener, Preference.OnPreferenceClickListener {
    private Role mRole;
    private String mRoleName;
    private UserHandle mUser;
    private DefaultAppViewModel mViewModel;
    private static final String PREFERENCE_KEY_NONE = DefaultAppChildFragment.class.getName() + ".preference.NONE";
    private static final String PREFERENCE_KEY_DESCRIPTION = DefaultAppChildFragment.class.getName() + ".preference.DESCRIPTION";

    public interface Parent {
        TwoStatePreference createApplicationPreference(Context context);

        Preference createFooterPreference(Context context);

        void onPreferenceScreenChanged();

        void setTitle(CharSequence charSequence);
    }

    public static DefaultAppChildFragment newInstance(String roleName, UserHandle user) {
        DefaultAppChildFragment fragment = new DefaultAppChildFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        arguments.putParcelable("android.intent.extra.USER", user);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
        this.mUser = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActivityResultCaller activityResultCallerRequirePreferenceFragment = requirePreferenceFragment();
        Activity activity = requireActivity();
        Role role = Roles.get(activity).get(this.mRoleName);
        this.mRole = role;
        ((Parent) activityResultCallerRequirePreferenceFragment).setTitle(getString(role.getLabelResource()));
        DefaultAppViewModel defaultAppViewModel = (DefaultAppViewModel) ViewModelProviders.of(this, new DefaultAppViewModel.Factory(this.mRole, this.mUser, activity.getApplication())).get(DefaultAppViewModel.class);
        this.mViewModel = defaultAppViewModel;
        defaultAppViewModel.getRoleLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppChildFragment$leNlhYeGW50glipCcuPV7nkyeIA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onRoleChanged((List) obj);
            }
        });
        this.mViewModel.getManageRoleHolderStateLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppChildFragment$VampiARrbmvNeKWe-ssFLEYMlrw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onManageRoleHolderStateChanged(((Integer) obj).intValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void onRoleChanged(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
        PreferenceScreen preferenceScreen;
        Preference oldDescriptionPreference;
        PreferenceFragmentCompat preferenceFragmentCompatRequirePreferenceFragment = requirePreferenceFragment();
        PreferenceManager preferenceManager = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceManager();
        Context context = preferenceManager.getContext();
        PreferenceScreen preferenceScreen2 = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceScreen();
        ArrayMap<String, Preference> oldPreferences = new ArrayMap<>();
        if (preferenceScreen2 == null) {
            PreferenceScreen preferenceScreen3 = preferenceManager.createPreferenceScreen(context);
            preferenceFragmentCompatRequirePreferenceFragment.setPreferenceScreen(preferenceScreen3);
            preferenceScreen = preferenceScreen3;
            oldDescriptionPreference = null;
        } else {
            Preference oldDescriptionPreference2 = preferenceScreen2.findPreference(PREFERENCE_KEY_DESCRIPTION);
            if (oldDescriptionPreference2 != null) {
                preferenceScreen2.removePreference(oldDescriptionPreference2);
                oldDescriptionPreference2.setOrder(Integer.MAX_VALUE);
            }
            for (int i = preferenceScreen2.getPreferenceCount() - 1; i >= 0; i--) {
                Preference preference = preferenceScreen2.getPreference(i);
                preferenceScreen2.removePreference(preference);
                preference.setOrder(Integer.MAX_VALUE);
                oldPreferences.put(preference.getKey(), preference);
            }
            preferenceScreen = preferenceScreen2;
            oldDescriptionPreference = oldDescriptionPreference2;
        }
        if (this.mRole.shouldShowNone()) {
            Drawable icon = AppCompatResources.getDrawable(context, R.drawable.ic_remove_circle);
            String title = getString(R.string.default_app_none);
            boolean noHolderApplication = !hasHolderApplication(qualifyingApplications);
            addPreference(PREFERENCE_KEY_NONE, icon, title, noHolderApplication, null, oldPreferences, preferenceScreen, context);
        }
        int i2 = 0;
        for (int qualifyingApplicationsSize = qualifyingApplications.size(); i2 < qualifyingApplicationsSize; qualifyingApplicationsSize = qualifyingApplicationsSize) {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = qualifyingApplications.get(i2);
            ApplicationInfo qualifyingApplicationInfo = (ApplicationInfo) qualifyingApplication.first;
            boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
            String key = qualifyingApplicationInfo.packageName;
            Drawable icon2 = Utils.getBadgedIcon(context, qualifyingApplicationInfo);
            String title2 = Utils.getFullAppLabel(qualifyingApplicationInfo, context);
            addPreference(key, icon2, title2, isHolderApplication, qualifyingApplicationInfo, oldPreferences, preferenceScreen, context);
            i2++;
        }
        Preference descriptionPreference = oldDescriptionPreference;
        if (descriptionPreference == null) {
            descriptionPreference = ((Parent) preferenceFragmentCompatRequirePreferenceFragment).createFooterPreference(context);
            descriptionPreference.setKey(PREFERENCE_KEY_DESCRIPTION);
            descriptionPreference.setSummary(this.mRole.getDescriptionResource());
        }
        preferenceScreen.addPreference(descriptionPreference);
        ((Parent) preferenceFragmentCompatRequirePreferenceFragment).onPreferenceScreenChanged();
    }

    private static boolean hasHolderApplication(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
        int qualifyingApplicationsSize = qualifyingApplications.size();
        for (int i = 0; i < qualifyingApplicationsSize; i++) {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = qualifyingApplications.get(i);
            boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
            if (isHolderApplication) {
                return true;
            }
        }
        return false;
    }

    private void addPreference(String key, Drawable icon, CharSequence title, boolean checked, ApplicationInfo applicationInfo, ArrayMap<String, Preference> oldPreferences, PreferenceScreen preferenceScreen, Context context) {
        TwoStatePreference preference = (TwoStatePreference) oldPreferences.get(key);
        if (preference == null) {
            preference = ((Parent) requirePreferenceFragment()).createApplicationPreference(context);
            preference.setKey(key);
            preference.setIcon(icon);
            preference.setTitle(title);
            preference.setPersistent(false);
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$DefaultAppChildFragment$IzJDec354HOk6tVhLNH8-wtD6pU
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference2, Object obj) {
                    return DefaultAppChildFragment.lambda$addPreference$0(preference2, obj);
                }
            });
            preference.setOnPreferenceClickListener(this);
        }
        preference.setChecked(checked);
        if (applicationInfo != null) {
            this.mRole.prepareApplicationPreferenceAsUser(preference, applicationInfo, this.mUser, context);
        }
        preferenceScreen.addPreference(preference);
    }

    static /* synthetic */ boolean lambda$addPreference$0(Preference preference2, Object newValue) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onManageRoleHolderStateChanged(int state) {
        ManageRoleHolderStateLiveData liveData = this.mViewModel.getManageRoleHolderStateLiveData();
        if (state != 2) {
            if (state == 3) {
                liveData.resetState();
            }
        } else {
            String packageName = liveData.getLastPackageName();
            if (packageName != null) {
                this.mRole.onHolderSelectedAsUser(packageName, liveData.getLastUser(), requireContext());
            }
            liveData.resetState();
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (Objects.equals(key, PREFERENCE_KEY_NONE)) {
            this.mViewModel.setNoneDefaultApp();
            return true;
        }
        CharSequence confirmationMessage = this.mRole.getConfirmationMessage(key, requireContext());
        if (confirmationMessage != null) {
            DefaultAppConfirmationDialogFragment.show(key, confirmationMessage, this);
            return true;
        }
        setDefaultApp(key);
        return true;
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppConfirmationDialogFragment.Listener
    public void setDefaultApp(String packageName) {
        this.mViewModel.setDefaultApp(packageName);
    }

    private PF requirePreferenceFragment() {
        return (PF) ((PreferenceFragmentCompat) requireParentFragment());
    }
}
