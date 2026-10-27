package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.contacts_picker.PickerCategoryView;
import org.chromium.components.permissions.BluetoothScanningPermissionDialog;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c12 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ c12(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                int i2 = PickerCategoryView.x;
                ((PickerCategoryView) obj).j(null, 0, 0);
                break;
            case 1:
                ((Callback) obj).onResult("User closed the Payment Request UI.");
                break;
            default:
                long j = ((BluetoothScanningPermissionDialog) obj).h;
                if (j != 0) {
                    N.MdX25cA2(j, 2);
                    break;
                }
                break;
        }
    }
}
