package defpackage;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* JADX INFO: compiled from: UsbMidiDeviceAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class gk3 extends Thread {
    public final /* synthetic */ Map e;
    public final /* synthetic */ UsbMidiDeviceAndroid f;

    public gk3(UsbMidiDeviceAndroid usbMidiDeviceAndroid, HashMap map) {
        this.f = usbMidiDeviceAndroid;
        this.e = map;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.f;
            UsbRequest usbRequestRequestWait = usbMidiDeviceAndroid.a.requestWait();
            if (usbRequestRequestWait == null) {
                return;
            }
            UsbEndpoint endpoint = usbRequestRequestWait.getEndpoint();
            if (endpoint.getDirection() == 128) {
                ByteBuffer byteBuffer = (ByteBuffer) this.e.get(endpoint);
                int iPosition = byteBuffer.position();
                int i = 0;
                while (true) {
                    if (i >= iPosition) {
                        break;
                    }
                    if (byteBuffer.get(i) == 0) {
                        iPosition = i;
                        break;
                    }
                    i += 4;
                }
                if (iPosition > 0) {
                    byte[] bArr = new byte[iPosition];
                    byteBuffer.get(bArr, 0, iPosition);
                    usbMidiDeviceAndroid.d.post(new hk3(usbMidiDeviceAndroid, endpoint.getEndpointNumber(), bArr));
                }
                usbRequestRequestWait.queue(byteBuffer, byteBuffer.capacity());
            }
        }
    }
}
