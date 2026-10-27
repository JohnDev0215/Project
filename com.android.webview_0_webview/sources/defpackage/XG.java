package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import java.util.Objects;
import org.chromium.media.AudioManagerAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XG extends BroadcastReceiver {
    public final /* synthetic */ AudioManagerAndroid a;

    public XG(AudioManagerAndroid audioManagerAndroid) {
        this.a = audioManagerAndroid;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.a.f((UsbDevice) intent.getParcelableExtra("device"))) {
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                synchronized (this.a.j) {
                    if (!this.a.a.isWiredHeadsetOn()) {
                        this.a.k[4] = true;
                        this.a.k[2] = false;
                    }
                }
            } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()) && !this.a.e()) {
                synchronized (this.a.j) {
                    if (!this.a.a.isWiredHeadsetOn()) {
                        this.a.k[4] = false;
                        Objects.requireNonNull(this.a);
                        if (Is.a.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
                            this.a.k[2] = true;
                        }
                    }
                }
            }
            if (AudioManagerAndroid.b(this.a)) {
                AudioManagerAndroid.c(this.a);
            }
        }
    }
}
