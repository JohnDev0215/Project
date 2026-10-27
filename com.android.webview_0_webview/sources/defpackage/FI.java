package defpackage;

import J.N;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FI implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ byte[] t;
    public final /* synthetic */ UsbMidiDeviceAndroid u;

    public FI(UsbMidiDeviceAndroid usbMidiDeviceAndroid, int i, byte[] bArr) {
        this.u = usbMidiDeviceAndroid;
        this.s = i;
        this.t = bArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.u.e) {
            return;
        }
        N.MNGB4bj1(this.u.g, this.s, this.t);
    }
}
