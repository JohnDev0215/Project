package com.android.car.settings.accounts;

import android.content.Context;
import android.content.Intent;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseAccountFragment extends SettingsFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.choose_account_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Intent intent = requireActivity().getIntent();
        ChooseAccountPreferenceController preferenceController = (ChooseAccountPreferenceController) use(ChooseAccountPreferenceController.class, R.string.pk_add_account);
        String[] authorities = intent.getStringArrayExtra("authorities");
        if (authorities != null) {
            preferenceController.setAuthorities(Arrays.asList(authorities));
        }
        String[] accountTypesForFilter = intent.getStringArrayExtra("account_types");
        if (accountTypesForFilter != null) {
            preferenceController.setAccountTypesFilter(new HashSet(Arrays.asList(accountTypesForFilter)));
        }
    }
}
