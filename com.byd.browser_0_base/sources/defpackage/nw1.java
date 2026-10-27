package defpackage;

import android.view.View;
import android.widget.LinearLayout;
import defpackage.ow1;
import org.chromium.base.Callback;
import org.chromium.components.permissions.BluetoothScanningPermissionDialog;
import org.chromium.components.permissions.R$id;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nw1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ nw1(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i9) {
            case 0:
                ow1.a aVar = (ow1.a) obj2;
                Callback callback = (Callback) obj;
                aVar.getClass();
                if (i8 - i6 < i4 - i2) {
                    callback.onResult(Boolean.valueOf(t41.f.f(aVar.a, view)));
                    break;
                }
                break;
            default:
                BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog = (BluetoothScanningPermissionDialog) obj2;
                LinearLayout linearLayout = (LinearLayout) obj;
                bluetoothScanningPermissionDialog.getClass();
                if (i != i5 || i2 != i6 || i3 != i7 || i4 != i8) {
                    View viewFindViewById = linearLayout.findViewById(R$id.container);
                    int height = bluetoothScanningPermissionDialog.a.getWindow().getDecorView().getHeight();
                    viewFindViewById.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(a8.o((Math.round((((height / r2) * 0.3f) / 48.0f) - 0.5f) + 0.5f) * 48.0f, 72.0f, 408.0f) * bluetoothScanningPermissionDialog.b.getResources().getDisplayMetrics().density)));
                }
                break;
        }
    }
}
