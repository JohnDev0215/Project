package defpackage;

import J.N;
import android.view.View;
import org.chromium.components.page_info.PageInfoRowView;
import org.chromium.components.permissions.BluetoothScanningPermissionDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class vu1 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ vu1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                int i2 = PageInfoRowView.h;
                ((PageInfoRowView.a) obj).g.run();
                break;
            default:
                BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog = (BluetoothScanningPermissionDialog) obj;
                long j = bluetoothScanningPermissionDialog.h;
                if (j != 0) {
                    N.MdX25cA2(j, 1);
                }
                bluetoothScanningPermissionDialog.c.setOnDismissListener(null);
                bluetoothScanningPermissionDialog.c.dismiss();
                break;
        }
    }
}
