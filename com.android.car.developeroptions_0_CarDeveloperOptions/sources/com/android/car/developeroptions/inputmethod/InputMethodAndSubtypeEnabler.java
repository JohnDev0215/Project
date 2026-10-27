package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodAndSubtypeEnabler extends DashboardFragment {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "InputMethodAndSubtypeEnabler";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 60;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.input_methods_subtype;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((InputMethodAndSubtypePreferenceController) use(InputMethodAndSubtypePreferenceController.class)).initialize(this, getStringExtraFromIntentOrArguments("input_method_id"));
    }

    private String getStringExtraFromIntentOrArguments(String str) {
        String stringExtra = getActivity().getIntent().getStringExtra(str);
        if (stringExtra != null) {
            return stringExtra;
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        return arguments.getString(str);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        String stringExtraFromIntentOrArguments = getStringExtraFromIntentOrArguments("android.intent.extra.TITLE");
        if (TextUtils.isEmpty(stringExtraFromIntentOrArguments)) {
            return;
        }
        getActivity().setTitle(stringExtraFromIntentOrArguments);
    }
}
