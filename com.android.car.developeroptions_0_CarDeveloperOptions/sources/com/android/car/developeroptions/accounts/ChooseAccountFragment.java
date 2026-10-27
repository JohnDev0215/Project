package com.android.car.developeroptions.accounts;

import android.content.Context;
import android.os.UserManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;

/* JADX INFO: loaded from: classes.dex */
public class ChooseAccountFragment extends DashboardFragment {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ChooseAccountFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 10;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.add_account_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((ChooseAccountPreferenceController) use(ChooseAccountPreferenceController.class)).initialize(getIntent().getStringArrayExtra("authorities"), getIntent().getStringArrayExtra("account_types"), Utils.getSecureTargetUser(getActivity().getActivityToken(), UserManager.get(getContext()), null, getIntent().getExtras()), getActivity());
    }
}
