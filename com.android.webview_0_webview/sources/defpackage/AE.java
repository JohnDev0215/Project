package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattDescriptor;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattDescriptorWrapper;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AE implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattDescriptorWrapper s;
    public final /* synthetic */ int t;
    public final /* synthetic */ BE u;

    public AE(BE be, Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper, int i) {
        this.u = be;
        this.s = wrappers$BluetoothGattDescriptorWrapper;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        ChromeBluetoothRemoteGattDescriptor chromeBluetoothRemoteGattDescriptor = (ChromeBluetoothRemoteGattDescriptor) this.u.a.f.get(this.s);
        if (chromeBluetoothRemoteGattDescriptor == null) {
            return;
        }
        Lt.a.a("Bluetooth.Web.Android.onDescriptorWrite.Status", this.t);
        int i = this.t;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? "OK" : "Error";
        Ts.d("Bluetooth", "onDescriptorWrite status:%d==%s", objArr);
        long j = chromeBluetoothRemoteGattDescriptor.a;
        if (j != 0) {
            N.M4SmPJiR(j, chromeBluetoothRemoteGattDescriptor, i);
        }
    }
}
