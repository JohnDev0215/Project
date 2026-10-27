package defpackage;

import J.N;
import android.bluetooth.BluetoothAdapter;
import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.keyboard_accessory.ManualFillingComponentBridge;
import org.chromium.components.permissions.BluetoothChooserDialog;
import org.chromium.components.permissions.PermissionUtil;
import org.chromium.components.permissions.R$string;
import org.chromium.components.permissions.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tb1 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ tb1(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                ManualFillingComponentBridge manualFillingComponentBridge = (ManualFillingComponentBridge) obj2;
                N.M2tSygph(manualFillingComponentBridge.e, manualFillingComponentBridge, i2, ((Boolean) obj).booleanValue());
                break;
            case 1:
                BluetoothChooserDialog bluetoothChooserDialog = (BluetoothChooserDialog) obj2;
                View view = (View) obj;
                long j = bluetoothChooserDialog.o;
                if (j != 0) {
                    WindowAndroid windowAndroid = bluetoothChooserDialog.e;
                    switch (i2) {
                        case 0:
                            N.MNZ4441F(j);
                            break;
                        case 1:
                            BluetoothAdapter bluetoothAdapter = bluetoothChooserDialog.q;
                            if (bluetoothAdapter != null && bluetoothAdapter.enable()) {
                                bluetoothChooserDialog.h.b(0);
                            } else {
                                bluetoothChooserDialog.h.a(bluetoothChooserDialog.g.getString(R$string.bluetooth_unable_to_turn_on_adapter), bluetoothChooserDialog.r);
                            }
                            break;
                        case 2:
                            N.Myj$17aV(j);
                            break;
                        case 3:
                            bluetoothChooserDialog.h.l = true;
                            PermissionUtil.requestSystemPermissionsForBluetooth(windowAndroid, bluetoothChooserDialog);
                            break;
                        case 4:
                            bluetoothChooserDialog.h.l = true;
                            PermissionUtil.requestLocationServices(windowAndroid);
                            break;
                        case 5:
                            N.M7w2qGR3(j);
                            break;
                        case 6:
                            b bVar = bluetoothChooserDialog.h;
                            bVar.k.clear();
                            bVar.b(1);
                            N.MsAog9yr(bluetoothChooserDialog.o);
                            break;
                    }
                    view.invalidate();
                    break;
                }
                break;
            default:
                ((df0) obj2).b(i2, (pb2) obj);
                break;
        }
    }
}
