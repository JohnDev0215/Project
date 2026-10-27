package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattCharacteristic;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;

/* JADX INFO: renamed from: xE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1756xE implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper s;
    public final /* synthetic */ int t;
    public final /* synthetic */ BE u;

    public RunnableC1756xE(BE be, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, int i) {
        this.u = be;
        this.s = wrappers$BluetoothGattCharacteristicWrapper;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = (ChromeBluetoothRemoteGattCharacteristic) this.u.a.e.get(this.s);
        if (chromeBluetoothRemoteGattCharacteristic == null) {
            return;
        }
        Lt.a.a("Bluetooth.Web.Android.onCharacteristicRead.Status", this.t);
        int i = this.t;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? "OK" : "Error";
        Ts.d("Bluetooth", "onCharacteristicRead status:%d==%s", objArr);
        long j = chromeBluetoothRemoteGattCharacteristic.a;
        if (j != 0) {
            N.Mn9Gzi$d(j, chromeBluetoothRemoteGattCharacteristic, i, chromeBluetoothRemoteGattCharacteristic.b.a.getValue());
        }
    }
}
