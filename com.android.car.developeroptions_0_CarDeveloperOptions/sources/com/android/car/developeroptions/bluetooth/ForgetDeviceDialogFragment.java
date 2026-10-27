package com.android.car.developeroptions.bluetooth;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes.dex */
public class ForgetDeviceDialogFragment extends InstrumentedDialogFragment {
    private CachedBluetoothDevice mDevice;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1031;
    }

    CachedBluetoothDevice getDevice(Context context) {
        String string = getArguments().getString("device_address");
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(context);
        return localBtManager.getCachedDeviceManager().findDevice(localBtManager.getBluetoothAdapter().getRemoteDevice(string));
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$ForgetDeviceDialogFragment$luzm1Od-McRdtLRhhFVx4yCO0So
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$ForgetDeviceDialogFragment(dialogInterface, i);
            }
        };
        Context context = getContext();
        this.mDevice = getDevice(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(R.string.bluetooth_unpair_dialog_forget_confirm_button, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setTitle(R.string.bluetooth_unpair_dialog_title);
        alertDialogCreate.setMessage(context.getString(R.string.bluetooth_unpair_dialog_body, this.mDevice.getName()));
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$ForgetDeviceDialogFragment(DialogInterface dialogInterface, int i) {
        this.mDevice.unpair();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
