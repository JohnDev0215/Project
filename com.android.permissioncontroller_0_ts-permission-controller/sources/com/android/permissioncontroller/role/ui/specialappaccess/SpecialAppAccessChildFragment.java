package com.android.permissioncontroller.role.ui.specialappaccess;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Pair;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.ManageRoleHolderStateLiveData;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessChildFragment<PF extends PreferenceFragmentCompat & Parent> extends Fragment implements Preference.OnPreferenceClickListener {
    private static final String PREFERENCE_EXTRA_APPLICATION_INFO = SpecialAppAccessChildFragment.class.getName() + ".extra.APPLICATION_INFO";
    private static final String PREFERENCE_KEY_DESCRIPTION = SpecialAppAccessChildFragment.class.getName() + ".preference.DESCRIPTION";
    private Role mRole;
    private String mRoleName;
    private SpecialAppAccessViewModel mViewModel;

    public interface Parent {
        TwoStatePreference createApplicationPreference(Context context);

        Preference createFooterPreference(Context context);

        void onPreferenceScreenChanged();

        void setTitle(CharSequence charSequence);
    }

    public static SpecialAppAccessChildFragment newInstance(String roleName) {
        SpecialAppAccessChildFragment fragment = new SpecialAppAccessChildFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ActivityResultCaller activityResultCallerRequirePreferenceFragment = requirePreferenceFragment();
        Activity activity = requireActivity();
        Role role = Roles.get(activity).get(this.mRoleName);
        this.mRole = role;
        ((Parent) activityResultCallerRequirePreferenceFragment).setTitle(getString(role.getLabelResource()));
        SpecialAppAccessViewModel specialAppAccessViewModel = (SpecialAppAccessViewModel) ViewModelProviders.of(this, new SpecialAppAccessViewModel.Factory(this.mRole, activity.getApplication())).get(SpecialAppAccessViewModel.class);
        this.mViewModel = specialAppAccessViewModel;
        specialAppAccessViewModel.getRoleLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessChildFragment$Uy3vLZLOT0ml9PUEqP9I-IOXv2I
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onRoleChanged((List) obj);
            }
        });
        this.mViewModel.observeManageRoleHolderState(this, new $$Lambda$SpecialAppAccessChildFragment$g2ZEqrC1spV4o7tZXzbq1LMM1eI(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void onRoleChanged(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
        PreferenceManager preferenceManager;
        PreferenceFragmentCompat preferenceFragmentCompatRequirePreferenceFragment = requirePreferenceFragment();
        PreferenceManager preferenceManager2 = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceManager();
        Context context = preferenceManager2.getContext();
        PreferenceScreen preferenceScreen = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceScreen();
        Preference oldDescriptionPreference = null;
        ArrayMap<String, Preference> oldPreferences = new ArrayMap<>();
        if (preferenceScreen == null) {
            preferenceScreen = preferenceManager2.createPreferenceScreen(context);
            preferenceFragmentCompatRequirePreferenceFragment.setPreferenceScreen(preferenceScreen);
        } else {
            oldDescriptionPreference = preferenceScreen.findPreference(PREFERENCE_KEY_DESCRIPTION);
            if (oldDescriptionPreference != null) {
                preferenceScreen.removePreference(oldDescriptionPreference);
                oldDescriptionPreference.setOrder(Integer.MAX_VALUE);
            }
            for (int i = preferenceScreen.getPreferenceCount() - 1; i >= 0; i--) {
                Preference preference = preferenceScreen.getPreference(i);
                preferenceScreen.removePreference(preference);
                preference.setOrder(Integer.MAX_VALUE);
                oldPreferences.put(preference.getKey(), preference);
            }
        }
        int qualifyingApplicationsSize = qualifyingApplications.size();
        int i2 = 0;
        while (i2 < qualifyingApplicationsSize) {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = qualifyingApplications.get(i2);
            ApplicationInfo qualifyingApplicationInfo = (ApplicationInfo) qualifyingApplication.first;
            boolean isHolderPackage = ((Boolean) qualifyingApplication.second).booleanValue();
            String key = qualifyingApplicationInfo.packageName + '_' + qualifyingApplicationInfo.uid;
            TwoStatePreference preference2 = (TwoStatePreference) oldPreferences.get(key);
            if (preference2 != null) {
                preferenceManager = preferenceManager2;
            } else {
                preference2 = ((Parent) preferenceFragmentCompatRequirePreferenceFragment).createApplicationPreference(context);
                preference2.setKey(key);
                preference2.setIcon(Utils.getBadgedIcon(context, qualifyingApplicationInfo));
                preference2.setTitle(Utils.getFullAppLabel(qualifyingApplicationInfo, context));
                preference2.setPersistent(false);
                preference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessChildFragment$6cV7lK8MU1GYPbo-emLloavCMlQ
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference3, Object obj) {
                        return SpecialAppAccessChildFragment.lambda$onRoleChanged$0(preference3, obj);
                    }
                });
                preference2.setOnPreferenceClickListener(this);
                preferenceManager = preferenceManager2;
                preference2.getExtras().putParcelable(PREFERENCE_EXTRA_APPLICATION_INFO, qualifyingApplicationInfo);
            }
            preference2.setChecked(isHolderPackage);
            UserHandle user = UserHandle.getUserHandleForUid(qualifyingApplicationInfo.uid);
            this.mRole.prepareApplicationPreferenceAsUser(preference2, qualifyingApplicationInfo, user, context);
            preferenceScreen.addPreference(preference2);
            i2++;
            preferenceManager2 = preferenceManager;
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

    static /* synthetic */ boolean lambda$onRoleChanged$0(Preference preference2, Object newValue) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onManageRoleHolderStateChanged(ManageRoleHolderStateLiveData liveData, int state) {
        if (state != 2) {
            if (state == 3) {
                liveData.resetState();
            }
        } else {
            String packageName = liveData.getLastPackageName();
            if (packageName != null && liveData.isLastAdd()) {
                this.mRole.onHolderSelectedAsUser(packageName, liveData.getLastUser(), requireContext());
            }
            liveData.resetState();
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        ApplicationInfo applicationInfo = (ApplicationInfo) preference.getExtras().getParcelable(PREFERENCE_EXTRA_APPLICATION_INFO);
        String packageName = applicationInfo.packageName;
        UserHandle user = UserHandle.getUserHandleForUid(applicationInfo.uid);
        boolean allow = !((TwoStatePreference) preference).isChecked();
        String key = preference.getKey();
        this.mViewModel.setSpecialAppAccessAsUser(packageName, allow, user, key, this, new $$Lambda$SpecialAppAccessChildFragment$g2ZEqrC1spV4o7tZXzbq1LMM1eI(this));
        return true;
    }

    private PF requirePreferenceFragment() {
        return (PF) ((PreferenceFragmentCompat) requireParentFragment());
    }
}
