package defpackage;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import java.nio.ByteBuffer;
import java.util.Map;
import org.chromium.midi.UsbMidiDeviceAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class EI extends Thread {
    public final /* synthetic */ Map s;
    public final /* synthetic */ UsbMidiDeviceAndroid t;

    public EI(UsbMidiDeviceAndroid usbMidiDeviceAndroid, Map map) {
        this.t = usbMidiDeviceAndroid;
        this.s = map;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            UsbRequest usbRequestRequestWait = this.t.a.requestWait();
            if (usbRequestRequestWait == null) {
                return;
            }
            UsbEndpoint endpoint = usbRequestRequestWait.getEndpoint();
            if (endpoint.getDirection() == 128) {
                ByteBuffer byteBuffer = (ByteBuffer) this.s.get(endpoint);
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
                    byteBuffer.rewind();
                    byte[] bArr = new byte[iPosition];
                    byteBuffer.get(bArr, 0, iPosition);
                    UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.t;
                    usbMidiDeviceAndroid.d.post(new FI(usbMidiDeviceAndroid, endpoint.getEndpointNumber(), bArr));
                }
                byteBuffer.rewind();
                usbRequestRequestWait.queue(byteBuffer, byteBuffer.capacity());
            }
        }
    }
}
