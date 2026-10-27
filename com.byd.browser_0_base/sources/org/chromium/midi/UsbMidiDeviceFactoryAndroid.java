package org.chromium.midi;

import J.N;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import defpackage.q11;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class UsbMidiDeviceFactoryAndroid {
    public final a b;
    public final b c;
    public final HashSet e;
    public boolean f;
    public long g;
    public final ArrayList d = new ArrayList();
    public final UsbManager a = (UsbManager) xz.a.getSystemService("usb");

    public class a extends BroadcastReceiver {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onReceive(android.content.Context r7, android.content.Intent r8) {
            /*
                r6 = this;
                boolean r7 = defpackage.q11.g(r8)
                if (r7 != 0) goto L7
                return
            L7:
                org.chromium.midi.UsbMidiDeviceFactoryAndroid r6 = org.chromium.midi.UsbMidiDeviceFactoryAndroid.this
                r6.getClass()
                java.lang.String r7 = "device"
                android.os.Parcelable r7 = r8.getParcelableExtra(r7)
                android.hardware.usb.UsbDevice r7 = (android.hardware.usb.UsbDevice) r7
                java.util.HashSet r0 = r6.e
                boolean r1 = r0.contains(r7)
                r2 = 0
                r3 = 0
                if (r1 == 0) goto L29
                r0.remove(r7)
                java.lang.String r1 = "permission"
                boolean r8 = r8.getBooleanExtra(r1, r2)
                if (r8 != 0) goto L2a
            L29:
                r7 = r3
            L2a:
                java.util.ArrayList r8 = r6.d
                if (r7 == 0) goto L4f
                java.util.Iterator r1 = r8.iterator()
            L32:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L4f
                java.lang.Object r4 = r1.next()
                org.chromium.midi.UsbMidiDeviceAndroid r4 = (org.chromium.midi.UsbMidiDeviceAndroid) r4
                boolean r5 = r4.e
                if (r5 != 0) goto L32
                android.hardware.usb.UsbDevice r4 = r4.h
                int r4 = r4.getDeviceId()
                int r5 = r7.getDeviceId()
                if (r4 != r5) goto L32
                r7 = r3
            L4f:
                if (r7 == 0) goto L5b
                org.chromium.midi.UsbMidiDeviceAndroid r3 = new org.chromium.midi.UsbMidiDeviceAndroid
                android.hardware.usb.UsbManager r1 = r6.a
                r3.<init>(r1, r7)
                r8.add(r3)
            L5b:
                boolean r7 = r0.isEmpty()
                if (r7 != 0) goto L62
                goto L7e
            L62:
                long r0 = r6.g
                r4 = 0
                int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r7 != 0) goto L6b
                goto L7e
            L6b:
                boolean r7 = r6.f
                if (r7 == 0) goto L79
                java.lang.Object[] r7 = r8.toArray()
                J.N.MrVQrlMA(r0, r7)
                r6.f = r2
                goto L7e
            L79:
                if (r3 == 0) goto L7e
                J.N.MGbhHafy(r0, r3)
            L7e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.midi.UsbMidiDeviceFactoryAndroid.a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
            boolean zEquals = "android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction());
            UsbMidiDeviceFactoryAndroid usbMidiDeviceFactoryAndroid = UsbMidiDeviceFactoryAndroid.this;
            if (zEquals) {
                usbMidiDeviceFactoryAndroid.a(usbDevice);
            }
            if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                return;
            }
            HashSet hashSet = usbMidiDeviceFactoryAndroid.e;
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UsbDevice usbDevice2 = (UsbDevice) it.next();
                if (usbDevice2.getDeviceId() == usbDevice.getDeviceId()) {
                    hashSet.remove(usbDevice2);
                    break;
                }
            }
            int i = 0;
            while (true) {
                ArrayList arrayList = usbMidiDeviceFactoryAndroid.d;
                if (i >= arrayList.size()) {
                    return;
                }
                UsbMidiDeviceAndroid usbMidiDeviceAndroid = (UsbMidiDeviceAndroid) arrayList.get(i);
                if (!usbMidiDeviceAndroid.e && usbMidiDeviceAndroid.h.getDeviceId() == usbDevice.getDeviceId()) {
                    usbMidiDeviceAndroid.close();
                    if (usbMidiDeviceFactoryAndroid.f) {
                        arrayList.remove(i);
                        return;
                    }
                    long j = usbMidiDeviceFactoryAndroid.g;
                    if (j != 0) {
                        N.MQPqHj_p(j, i);
                        return;
                    }
                    return;
                }
                i++;
            }
        }
    }

    public UsbMidiDeviceFactoryAndroid(long j) {
        this.g = j;
        a aVar = new a();
        this.b = aVar;
        b bVar = new b();
        this.c = bVar;
        this.e = new HashSet();
        Context context = xz.a;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("org.chromium.midi.USB_PERMISSION");
        xz.f(context, aVar, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter2.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        xz.g(context, bVar, intentFilter2);
    }

    @CalledByNative
    public static UsbMidiDeviceFactoryAndroid create(long j) {
        return new UsbMidiDeviceFactoryAndroid(j);
    }

    public final void a(UsbDevice usbDevice) {
        HashSet hashSet = this.e;
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((UsbDevice) it.next()).getDeviceId() == usbDevice.getDeviceId()) {
                return;
            }
        }
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                Context context = xz.a;
                Intent intent = new Intent("org.chromium.midi.USB_PERMISSION");
                intent.setPackage(context.getPackageName());
                q11.a(intent);
                this.a.requestPermission(usbDevice, PendingIntent.getBroadcast(context, 0, intent, q11.d(true)));
                hashSet.add(usbDevice);
                return;
            }
        }
    }

    @CalledByNative
    public void close() {
        this.g = 0L;
        xz.a.unregisterReceiver(this.c);
        xz.a.unregisterReceiver(this.b);
    }

    @CalledByNative
    public boolean enumerateDevices() {
        this.f = true;
        HashMap<String, UsbDevice> deviceList = this.a.getDeviceList();
        if (deviceList.isEmpty()) {
            this.f = false;
            return false;
        }
        Iterator<UsbDevice> it = deviceList.values().iterator();
        while (it.hasNext()) {
            a(it.next());
        }
        return !this.e.isEmpty();
    }
}
