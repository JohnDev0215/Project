package com.android.permissioncontroller.role.ui.specialappaccess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.RoleItem;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment.Parent;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessListChildFragment<PF extends PreferenceFragmentCompat & Parent> extends Fragment implements Preference.OnPreferenceClickListener {
    private SpecialAppAccessListViewModel mViewModel;

    public interface Parent {
        TwoTargetPreference createPreference(Context context);

        void onPreferenceScreenChanged();
    }

    public static SpecialAppAccessListChildFragment newInstance() {
        return new SpecialAppAccessListChildFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SpecialAppAccessListViewModel specialAppAccessListViewModel = (SpecialAppAccessListViewModel) ViewModelProviders.of(this).get(SpecialAppAccessListViewModel.class);
        this.mViewModel = specialAppAccessListViewModel;
        specialAppAccessListViewModel.getLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.specialappaccess.-$$Lambda$SpecialAppAccessListChildFragment$FuBt1y8bi1pEp3xpQkCOy6Dn01I
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onActivityCreated$0$SpecialAppAccessListChildFragment((List) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onActivityCreated$0$SpecialAppAccessListChildFragment(List roleItems) {
        onRoleListChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onRoleListChanged() {
        List<RoleItem> roleItems = this.mViewModel.getLiveData().getValue();
        if (roleItems == null) {
            return;
        }
        PreferenceFragmentCompat preferenceFragmentCompatRequirePreferenceFragment = requirePreferenceFragment();
        PreferenceManager preferenceManager = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceManager();
        Context context = preferenceManager.getContext();
        PreferenceScreen preferenceScreen = preferenceFragmentCompatRequirePreferenceFragment.getPreferenceScreen();
        ArrayMap<String, Preference> oldPreferences = new ArrayMap<>();
        if (preferenceScreen != null) {
            for (int i = preferenceScreen.getPreferenceCount() - 1; i >= 0; i--) {
                Preference preference = preferenceScreen.getPreference(i);
                preferenceScreen.removePreference(preference);
                preference.setOrder(Integer.MAX_VALUE);
                oldPreferences.put(preference.getKey(), preference);
            }
        } else {
            preferenceScreen = preferenceManager.createPreferenceScreen(context);
            preferenceFragmentCompatRequirePreferenceFragment.setPreferenceScreen(preferenceScreen);
        }
        int roleItemsSize = roleItems.size();
        for (int i2 = 0; i2 < roleItemsSize; i2++) {
            RoleItem roleItem = roleItems.get(i2);
            Role role = roleItem.getRole();
            TwoTargetPreference preference2 = (TwoTargetPreference) oldPreferences.get(role.getName());
            if (preference2 == null) {
                preference2 = ((Parent) preferenceFragmentCompatRequirePreferenceFragment).createPreference(context);
                preference2.setKey(role.getName());
                preference2.setIconSpaceReserved(true);
                preference2.setTitle(role.getShortLabelResource());
                preference2.setPersistent(false);
                preference2.setOnPreferenceClickListener(this);
            }
            role.preparePreferenceAsUser(preference2, Process.myUserHandle(), context);
            preferenceScreen.addPreference(preference2);
        }
        ((Parent) preferenceFragmentCompatRequirePreferenceFragment).onPreferenceScreenChanged();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String roleName = preference.getKey();
        Context context = requireContext();
        Role role = Roles.get(context).get(roleName);
        UserHandle user = Process.myUserHandle();
        Intent intent = role.getManageIntentAsUser(user, context);
        if (intent == null) {
            intent = SpecialAppAccessActivity.createIntent(roleName, context);
        }
        startActivity(intent);
        return true;
    }

    private PF requirePreferenceFragment() {
        return (PF) ((PreferenceFragmentCompat) requireParentFragment());
    }
}
