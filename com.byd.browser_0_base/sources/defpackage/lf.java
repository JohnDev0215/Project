package defpackage;

import android.app.Dialog;
import android.content.Context;
import org.chromium.components.permissions.BluetoothScanningPermissionDialog;

/* JADX INFO: compiled from: BluetoothScanningPermissionDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lf extends Dialog {
    public final /* synthetic */ BluetoothScanningPermissionDialog e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lf(BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog, Context context) {
        super(context);
        this.e = bluetoothScanningPermissionDialog;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog = this.e;
        if (!bluetoothScanningPermissionDialog.f && !z) {
            dismiss();
        }
        bluetoothScanningPermissionDialog.f = false;
    }
}
