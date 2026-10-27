package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothRemoteGattCharacteristic;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;

/* JADX INFO: renamed from: wE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1701wE implements Runnable {
    public final /* synthetic */ Wrappers$BluetoothGattCharacteristicWrapper s;
    public final /* synthetic */ byte[] t;
    public final /* synthetic */ BE u;

    public RunnableC1701wE(BE be, Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper, byte[] bArr) {
        this.u = be;
        this.s = wrappers$BluetoothGattCharacteristicWrapper;
        this.t = bArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        ChromeBluetoothRemoteGattCharacteristic chromeBluetoothRemoteGattCharacteristic = (ChromeBluetoothRemoteGattCharacteristic) this.u.a.e.get(this.s);
        if (chromeBluetoothRemoteGattCharacteristic == null) {
            return;
        }
        byte[] bArr = this.t;
        Ts.d("Bluetooth", "onCharacteristicChanged", new Object[0]);
        long j = chromeBluetoothRemoteGattCharacteristic.a;
        if (j != 0) {
            N.MZCKcdTH(j, chromeBluetoothRemoteGattCharacteristic, bArr);
        }
    }
}
