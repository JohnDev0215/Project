package defpackage;

import J.N;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* JADX INFO: compiled from: UsbMidiDeviceAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hk3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ byte[] f;
    public final /* synthetic */ UsbMidiDeviceAndroid g;

    public hk3(UsbMidiDeviceAndroid usbMidiDeviceAndroid, int i, byte[] bArr) {
        this.g = usbMidiDeviceAndroid;
        this.e = i;
        this.f = bArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.g;
        if (usbMidiDeviceAndroid.e) {
            return;
        }
        N.MNGB4bj1(usbMidiDeviceAndroid.g, this.e, this.f);
    }
}
