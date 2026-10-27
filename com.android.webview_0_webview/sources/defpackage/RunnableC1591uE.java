package defpackage;

import J.N;
import org.chromium.device.bluetooth.ChromeBluetoothDevice;

/* JADX INFO: renamed from: uE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1591uE implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ BE u;

    public RunnableC1591uE(BE be, int i, int i2) {
        this.u = be;
        this.s = i;
        this.t = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i = this.s;
        if (i == 2) {
            Lt.a.a("Bluetooth.Web.Android.onConnectionStateChange.Status.Connected", this.t);
            this.u.a.c.a.discoverServices();
        } else if (i == 0) {
            Lt.a.a("Bluetooth.Web.Android.onConnectionStateChange.Status.Disconnected", this.t);
            DE de = this.u.a.c;
            if (de != null) {
                de.a.close();
                this.u.a.c = null;
            }
        } else {
            Lt.a.a("Bluetooth.Web.Android.onConnectionStateChange.Status.InvalidState", this.t);
        }
        if (this.u.a.a != 0) {
            ChromeBluetoothDevice chromeBluetoothDevice = this.u.a;
            N.MmnW7gQC(chromeBluetoothDevice.a, chromeBluetoothDevice, this.t, this.s == 2);
        }
    }
}
