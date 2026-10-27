package org.chromium.midi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import defpackage.GI;
import defpackage.Is;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UsbMidiDeviceFactoryAndroid {
    public Set d;
    public boolean e;
    public long f;
    public final List c = new ArrayList();
    public UsbManager a = (UsbManager) Is.a.getSystemService("usb");
    public BroadcastReceiver b = new GI(this);

    public UsbMidiDeviceFactoryAndroid(long j) {
        this.f = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction("org.chromium.midi.USB_PERMISSION");
        Is.a.registerReceiver(this.b, intentFilter);
        this.d = new HashSet();
    }

    public static UsbMidiDeviceFactoryAndroid create(long j) {
        return new UsbMidiDeviceFactoryAndroid(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.content.Intent r7) {
        /*
            r6 = this;
            java.lang.String r0 = "device"
            android.os.Parcelable r0 = r7.getParcelableExtra(r0)
            android.hardware.usb.UsbDevice r0 = (android.hardware.usb.UsbDevice) r0
            java.util.Set r1 = r6.d
            boolean r1 = r1.contains(r0)
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L1f
            java.util.Set r1 = r6.d
            r1.remove(r0)
            java.lang.String r1 = "permission"
            boolean r7 = r7.getBooleanExtra(r1, r2)
            if (r7 != 0) goto L20
        L1f:
            r0 = r3
        L20:
            if (r0 == 0) goto L45
            java.util.List r7 = r6.c
            java.util.Iterator r7 = r7.iterator()
        L28:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L45
            java.lang.Object r1 = r7.next()
            org.chromium.midi.UsbMidiDeviceAndroid r1 = (org.chromium.midi.UsbMidiDeviceAndroid) r1
            boolean r4 = r1.e
            if (r4 != 0) goto L28
            android.hardware.usb.UsbDevice r1 = r1.h
            int r1 = r1.getDeviceId()
            int r4 = r0.getDeviceId()
            if (r1 != r4) goto L28
            r0 = r3
        L45:
            if (r0 == 0) goto L53
            org.chromium.midi.UsbMidiDeviceAndroid r3 = new org.chromium.midi.UsbMidiDeviceAndroid
            android.hardware.usb.UsbManager r7 = r6.a
            r3.<init>(r7, r0)
            java.util.List r7 = r6.c
            r7.add(r3)
        L53:
            java.util.Set r7 = r6.d
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L5c
            return
        L5c:
            long r0 = r6.f
            r4 = 0
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 != 0) goto L65
            return
        L65:
            boolean r7 = r6.e
            if (r7 == 0) goto L75
            java.util.List r7 = r6.c
            java.lang.Object[] r7 = r7.toArray()
            J.N.MrVQrlMA(r0, r7)
            r6.e = r2
            goto L7a
        L75:
            if (r3 == 0) goto L7a
            J.N.MGbhHafy(r0, r3)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.midi.UsbMidiDeviceFactoryAndroid.a(android.content.Intent):void");
    }

    public final void b(UsbDevice usbDevice) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            if (((UsbDevice) it.next()).getDeviceId() == usbDevice.getDeviceId()) {
                return;
            }
        }
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.a.requestPermission(usbDevice, PendingIntent.getBroadcast(Is.a, 0, new Intent("org.chromium.midi.USB_PERMISSION"), 0));
                this.d.add(usbDevice);
                return;
            }
        }
    }

    public void close() {
        this.f = 0L;
        Is.a.unregisterReceiver(this.b);
    }

    public boolean enumerateDevices() {
        this.e = true;
        HashMap<String, UsbDevice> deviceList = this.a.getDeviceList();
        if (deviceList.isEmpty()) {
            this.e = false;
            return false;
        }
        Iterator<UsbDevice> it = deviceList.values().iterator();
        while (it.hasNext()) {
            b(it.next());
        }
        return true ^ this.d.isEmpty();
    }
}
