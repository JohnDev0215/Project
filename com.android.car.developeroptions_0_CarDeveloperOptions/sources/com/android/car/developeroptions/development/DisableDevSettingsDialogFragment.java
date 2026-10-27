package com.android.car.developeroptions.development;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class DisableDevSettingsDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1591;
    }

    static DisableDevSettingsDialogFragment newInstance() {
        return new DisableDevSettingsDialogFragment();
    }

    public static void show(DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        DisableDevSettingsDialogFragment disableDevSettingsDialogFragment = new DisableDevSettingsDialogFragment();
        disableDevSettingsDialogFragment.setTargetFragment(developmentSettingsDashboardFragment, 0);
        disableDevSettingsDialogFragment.show(developmentSettingsDashboardFragment.getActivity().getSupportFragmentManager(), "DisableDevSettingDlg");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.bluetooth_disable_a2dp_hw_offload_dialog_message);
        builder.setTitle(R.string.bluetooth_disable_a2dp_hw_offload_dialog_title);
        builder.setPositiveButton(R.string.bluetooth_disable_a2dp_hw_offload_dialog_confirm, this);
        builder.setNegativeButton(R.string.bluetooth_disable_a2dp_hw_offload_dialog_cancel, this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Fragment targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DevelopmentSettingsDashboardFragment)) {
            Log.e("DisableDevSettingDlg", "getTargetFragment return unexpected type");
        }
        DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment = (DevelopmentSettingsDashboardFragment) targetFragment;
        if (i == -1) {
            developmentSettingsDashboardFragment.onDisableDevelopmentOptionsConfirmed();
            ((PowerManager) getContext().getSystemService(PowerManager.class)).reboot(null);
        } else {
            developmentSettingsDashboardFragment.onDisableDevelopmentOptionsRejected();
        }
    }
}
