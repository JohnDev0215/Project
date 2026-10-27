package com.android.permissioncontroller.permission.ui.handheld;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.model.ManageCustomPermissionsViewModel;
import com.android.permissioncontroller.permission.ui.model.ManageCustomPermissionsViewModelFactory;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes8.dex */
public class ManageCustomPermissionsFragment extends ManagePermissionsFragment {
    private ManageCustomPermissionsViewModel mViewModel;

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
        ManageCustomPermissionsViewModelFactory factory = new ManageCustomPermissionsViewModelFactory(getActivity().getApplication());
        ManageCustomPermissionsViewModel manageCustomPermissionsViewModel = (ManageCustomPermissionsViewModel) new ViewModelProvider(this, factory).get(ManageCustomPermissionsViewModel.class);
        this.mViewModel = manageCustomPermissionsViewModel;
        this.mPermissionGroups = manageCustomPermissionsViewModel.getUiDataLiveData().getValue();
        this.mViewModel.getUiDataLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ManageCustomPermissionsFragment$5FlK5HsZYbK5hnUb6hyIpRxPq8s
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$0$ManageCustomPermissionsFragment((Map) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$ManageCustomPermissionsFragment(Map permissionGroups) {
        if (permissionGroups == null) {
            this.mPermissionGroups = new HashMap();
        } else {
            this.mPermissionGroups = permissionGroups;
        }
        updatePermissionsUi();
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.ManagePermissionsFragment
    public void showPermissionApps(String permissionGroupName) {
        this.mViewModel.showPermissionApps(this, PermissionAppsFragment.createArgs(permissionGroupName, getArguments().getLong(Constants.EXTRA_SESSION_ID)));
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getActivity().setTitle(R.string.additional_permissions);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
