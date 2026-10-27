package com.android.car.developeroptions.accessibility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.bluetooth.BluetoothPairingDetail;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class HearingAidDialogFragment extends InstrumentedDialogFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1512;
    }

    public static HearingAidDialogFragment newInstance() {
        return new HearingAidDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.accessibility_hearingaid_pair_instructions_first_message).setMessage(R.string.accessibility_hearingaid_pair_instructions_second_message).setPositiveButton(R.string.accessibility_hearingaid_instruction_continue_button, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.accessibility.HearingAidDialogFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                HearingAidDialogFragment.this.launchBluetoothAddDeviceSetting();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.accessibility.HearingAidDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchBluetoothAddDeviceSetting() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
        subSettingLauncher.setDestination(BluetoothPairingDetail.class.getName());
        subSettingLauncher.setSourceMetricsCategory(2);
        subSettingLauncher.launch();
    }
}
