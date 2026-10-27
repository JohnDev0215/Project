package org.chromium.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import defpackage.xz;
import java.util.Iterator;
import org.chromium.media.e;

/* JADX INFO: compiled from: AudioDeviceListener.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d {
    public boolean a;
    public a b;
    public b c;
    public final UsbManager d = (UsbManager) xz.a.getSystemService("usb");
    public c e;
    public final e.b f;

    public d(e.b bVar) {
        this.f = bVar;
    }

    public static boolean c(UsbDevice usbDevice) {
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 2) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        xz.a.unregisterReceiver(this.b);
        this.b = null;
        if (this.a) {
            xz.a.unregisterReceiver(this.c);
            this.c = null;
        }
        xz.a.unregisterReceiver(this.e);
        this.e = null;
    }

    public final boolean b() {
        try {
            Iterator<UsbDevice> it = this.d.getDeviceList().values().iterator();
            while (it.hasNext()) {
                if (c(it.next())) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final void d(boolean z) {
        boolean zHasSystemFeature = xz.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
        e.b bVar = this.f;
        bVar.a(2, zHasSystemFeature);
        bVar.a(4, b());
        boolean z2 = false;
        bVar.a(0, true);
        this.a = z;
        if (z) {
            if (z) {
                BluetoothAdapter adapter = ((BluetoothManager) xz.a.getSystemService("bluetooth")).getAdapter();
                if (adapter != null) {
                    int profileConnectionState = adapter.getProfileConnectionState(1);
                    if (adapter.isEnabled() && profileConnectionState == 2) {
                        z2 = true;
                    }
                }
            } else {
                Log.w("cr_media", "hasBluetoothHeadset() requires BLUETOOTH permission");
            }
            bVar.a(3, z2);
            IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            b bVar2 = new b(this);
            this.c = bVar2;
            xz.g(xz.a, bVar2, intentFilter);
        } else {
            Log.w("cr_media", "registerBluetoothIntentsIfNeeded: Requires BLUETOOTH permission");
        }
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.HEADSET_PLUG");
        a aVar = new a(this);
        this.b = aVar;
        xz.g(xz.a, aVar, intentFilter2);
        this.e = new c(this);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter3.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        xz.g(xz.a, this.e, intentFilter3);
    }
}
