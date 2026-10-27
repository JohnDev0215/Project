package defpackage;

import J.N;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.device.bluetooth.ChromeBluetoothDevice;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattServiceWrapper;

/* JADX INFO: renamed from: vE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1646vE implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ BE t;

    public RunnableC1646vE(BE be, int i) {
        this.t = be;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        ChromeBluetoothDevice chromeBluetoothDevice = this.t.a;
        if (chromeBluetoothDevice.a != 0) {
            if (chromeBluetoothDevice.c == null) {
                Lt.a.a("Bluetooth.Web.Android.onServicesDiscovered.Status.Disconnected", this.s);
                return;
            }
            Lt.a.a("Bluetooth.Web.Android.onServicesDiscovered.Status.Connected", this.s);
            DE de = this.t.a.c;
            List<BluetoothGattService> services = de.a.getServices();
            ArrayList<Wrappers$BluetoothGattServiceWrapper> arrayList = new ArrayList(services.size());
            Iterator<BluetoothGattService> it = services.iterator();
            while (it.hasNext()) {
                arrayList.add(new Wrappers$BluetoothGattServiceWrapper(it.next(), de.b));
            }
            for (Wrappers$BluetoothGattServiceWrapper wrappers$BluetoothGattServiceWrapper : arrayList) {
                String str = this.t.a.getAddress() + "/" + wrappers$BluetoothGattServiceWrapper.a.getUuid().toString() + "," + wrappers$BluetoothGattServiceWrapper.a.getInstanceId();
                ChromeBluetoothDevice chromeBluetoothDevice2 = this.t.a;
                N.MAoRk69U(chromeBluetoothDevice2.a, chromeBluetoothDevice2, str, wrappers$BluetoothGattServiceWrapper);
            }
            ChromeBluetoothDevice chromeBluetoothDevice3 = this.t.a;
            N.M9HSgyay(chromeBluetoothDevice3.a, chromeBluetoothDevice3);
        }
    }
}
