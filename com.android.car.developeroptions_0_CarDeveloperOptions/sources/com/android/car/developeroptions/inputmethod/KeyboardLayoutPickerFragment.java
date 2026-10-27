package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.hardware.input.InputDeviceIdentifier;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;

/* JADX INFO: loaded from: classes.dex */
public class KeyboardLayoutPickerFragment extends DashboardFragment {
    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "KeyboardLayoutPicker";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 58;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.keyboard_layout_picker_fragment;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        InputDeviceIdentifier parcelableExtra = getActivity().getIntent().getParcelableExtra("input_device_identifier");
        if (parcelableExtra == null) {
            getActivity().finish();
        }
        ((KeyboardLayoutPickerController) use(KeyboardLayoutPickerController.class)).initialize(this, parcelableExtra);
    }
}
