package com.android.car.developeroptions.print;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.dashboard.profileselector.UserAdapter;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProfileSettingsPreferenceFragment extends SettingsPreferenceFragment {
    protected abstract String getIntentActionString();

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        final UserAdapter userAdapterCreateUserSpinnerAdapter = UserAdapter.createUserSpinnerAdapter((UserManager) getSystemService("user"), getActivity());
        if (userAdapterCreateUserSpinnerAdapter != null) {
            final Spinner spinner = (Spinner) setPinnedHeaderView(R.layout.spinner_view);
            spinner.setAdapter((SpinnerAdapter) userAdapterCreateUserSpinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.car.developeroptions.print.ProfileSettingsPreferenceFragment.1
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(AdapterView<?> adapterView, View view2, int i, long j) {
                    UserHandle userHandle = userAdapterCreateUserSpinnerAdapter.getUserHandle(i);
                    if (userHandle.getIdentifier() != UserHandle.myUserId()) {
                        Intent intent = new Intent(ProfileSettingsPreferenceFragment.this.getIntentActionString());
                        intent.addFlags(268435456);
                        intent.addFlags(32768);
                        ProfileSettingsPreferenceFragment.this.getActivity().startActivityAsUser(intent, userHandle);
                        spinner.setSelection(0);
                    }
                }
            });
        }
    }
}
