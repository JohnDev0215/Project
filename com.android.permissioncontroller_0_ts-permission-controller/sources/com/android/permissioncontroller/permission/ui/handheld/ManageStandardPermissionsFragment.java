package com.android.permissioncontroller.permission.ui.handheld;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment;
import com.android.permissioncontroller.permission.ui.model.ManageStandardPermissionsViewModel;
import com.android.permissioncontroller.permission.ui.model.ManageStandardPermissionsViewModelFactory;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public final class ManageStandardPermissionsFragment extends ManagePermissionsFragment {
    private static final String AUTO_REVOKE_KEY = "auto_revoke_key";
    private static final String EXTRA_PREFS_KEY = "extra_prefs_key";
    private static final String LOG_TAG = ManageStandardPermissionsFragment.class.getSimpleName();
    private ManageStandardPermissionsViewModel mViewModel;

    @Override // com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment, androidx.preference.Preference.OnPreferenceClickListener
    public /* bridge */ /* synthetic */ boolean onPreferenceClick(Preference preference) {
        return super.onPreferenceClick(preference);
    }

    public static Bundle createArgs(long sessionId) {
        Bundle arguments = new Bundle();
        arguments.putLong(Constants.EXTRA_SESSION_ID, sessionId);
        return arguments;
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        ManageStandardPermissionsViewModelFactory factory = new ManageStandardPermissionsViewModelFactory(getActivity().getApplication());
        ManageStandardPermissionsViewModel manageStandardPermissionsViewModel = (ManageStandardPermissionsViewModel) new ViewModelProvider(this, factory).get(ManageStandardPermissionsViewModel.class);
        this.mViewModel = manageStandardPermissionsViewModel;
        this.mPermissionGroups = manageStandardPermissionsViewModel.getUiDataLiveData().getValue();
        this.mViewModel.getUiDataLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageStandardPermissionsFragment$YQzh6AQ8URfPVVcVPofpjvl6rr8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$0$ManageStandardPermissionsFragment((Map) obj);
            }
        });
        this.mViewModel.getNumCustomPermGroups().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageStandardPermissionsFragment$9sVUfyqxCkOV_lKQPJPADck-H6g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$1$ManageStandardPermissionsFragment((Integer) obj);
            }
        });
        this.mViewModel.getNumAutoRevoked().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageStandardPermissionsFragment$O5yLmqQXf3inALAC6w5QOLgiYgo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$2$ManageStandardPermissionsFragment((Integer) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$ManageStandardPermissionsFragment(Map permissionGroups) {
        if (permissionGroups != null) {
            this.mPermissionGroups = permissionGroups;
            updatePermissionsUi();
        } else {
            Log.e(LOG_TAG, "ViewModel returned null data, exiting");
            getActivity().finish();
        }
    }

    public /* synthetic */ void lambda$onCreate$1$ManageStandardPermissionsFragment(Integer permNames) {
        updatePermissionsUi();
    }

    public /* synthetic */ void lambda$onCreate$2$ManageStandardPermissionsFragment(Integer show) {
        updatePermissionsUi();
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getActivity().setTitle(R.string.app_permission_manager);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment
    protected PreferenceScreen updatePermissionsUi() {
        PreferenceScreen screen = super.updatePermissionsUi();
        if (screen == null) {
            return null;
        }
        int numExtraPermissions = 0;
        if (this.mViewModel.getNumCustomPermGroups().getValue() != null) {
            numExtraPermissions = this.mViewModel.getNumCustomPermGroups().getValue().intValue();
        }
        Preference additionalPermissionsPreference = screen.findPreference(EXTRA_PREFS_KEY);
        if (numExtraPermissions == 0) {
            if (additionalPermissionsPreference != null) {
                screen.removePreference(additionalPermissionsPreference);
            }
        } else {
            if (additionalPermissionsPreference == null) {
                additionalPermissionsPreference = new ManagePermissionsFragment.FixedSizeIconPreference(getPreferenceManager().getContext());
                additionalPermissionsPreference.setKey(EXTRA_PREFS_KEY);
                additionalPermissionsPreference.setIcon(Utils.applyTint(getActivity(), R.drawable.ic_more_items, android.R.attr.colorControlNormal));
                additionalPermissionsPreference.setTitle(R.string.additional_permissions);
                additionalPermissionsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageStandardPermissionsFragment$DsuljzT1HyKpS-oaJ1V3x1qnHuw
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updatePermissionsUi$3$ManageStandardPermissionsFragment(preference);
                    }
                });
                screen.addPreference(additionalPermissionsPreference);
            }
            additionalPermissionsPreference.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, numExtraPermissions, Integer.valueOf(numExtraPermissions)));
        }
        Integer numAutoRevoked = this.mViewModel.getNumAutoRevoked().getValue();
        Preference autoRevokePreference = screen.findPreference(AUTO_REVOKE_KEY);
        if (numAutoRevoked != null && numAutoRevoked.intValue() != 0) {
            if (autoRevokePreference == null) {
                Preference autoRevokePreference2 = new ManagePermissionsFragment.FixedSizeIconPreference(getPreferenceManager().getContext(), true, true);
                autoRevokePreference2.setOrder(-1);
                autoRevokePreference2.setKey(AUTO_REVOKE_KEY);
                autoRevokePreference2.setSingleLineTitle(false);
                autoRevokePreference2.setIcon(R.drawable.ic_info_outline_accent);
                autoRevokePreference2.setTitle(R.string.auto_revoke_permission_notification_title);
                autoRevokePreference2.setSummary(R.string.auto_revoke_setting_subtitle);
                autoRevokePreference2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageStandardPermissionsFragment$vp46gpOHhXbMfzYWPqUO-fQdCRA
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updatePermissionsUi$4$ManageStandardPermissionsFragment(preference);
                    }
                });
                screen.addPreference(autoRevokePreference2);
            }
        } else if (numAutoRevoked != null && autoRevokePreference != null) {
            screen.removePreference(autoRevokePreference);
        }
        return screen;
    }

    public /* synthetic */ boolean lambda$updatePermissionsUi$3$ManageStandardPermissionsFragment(Preference preference) {
        this.mViewModel.showCustomPermissions(this, ManageCustomPermissionsFragment.createArgs(getArguments().getLong(Constants.EXTRA_SESSION_ID)));
        return true;
    }

    public /* synthetic */ boolean lambda$updatePermissionsUi$4$ManageStandardPermissionsFragment(Preference preference) {
        this.mViewModel.showAutoRevoke(this, AutoRevokeFragment.createArgs(getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L)));
        return true;
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment
    public void showPermissionApps(String permissionGroupName) {
        this.mViewModel.showPermissionApps(this, PermissionAppsFragment.createArgs(permissionGroupName, getArguments().getLong(Constants.EXTRA_SESSION_ID)));
    }
}
