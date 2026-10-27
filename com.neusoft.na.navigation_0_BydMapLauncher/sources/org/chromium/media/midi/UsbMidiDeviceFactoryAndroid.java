package org.chromium.media.midi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media::midi")
class UsbMidiDeviceFactoryAndroid {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ACTION_USB_PERMISSION = "org.chromium.media.USB_PERMISSION";
    private boolean mIsEnumeratingDevices;
    private long mNativePointer;
    private Set<UsbDevice> mRequestedDevices;
    private UsbManager mUsbManager;
    private final List<UsbMidiDeviceAndroid> mDevices = new ArrayList();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: org.chromium.media.midi.UsbMidiDeviceFactoryAndroid.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra = intent.getParcelableExtra("device");
            if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
                UsbMidiDeviceFactoryAndroid.this.requestDevicePermissionIfNecessary(context, (UsbDevice) parcelableExtra);
            }
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                UsbMidiDeviceFactoryAndroid.this.onUsbDeviceDetached((UsbDevice) parcelableExtra);
            }
            if (UsbMidiDeviceFactoryAndroid.ACTION_USB_PERMISSION.equals(intent.getAction())) {
                UsbMidiDeviceFactoryAndroid.this.onUsbDevicePermissionRequestDone(context, intent);
            }
        }
    };

    private static native void nativeOnUsbMidiDeviceAttached(long j, Object obj);

    private static native void nativeOnUsbMidiDeviceDetached(long j, int i);

    private static native void nativeOnUsbMidiDeviceRequestDone(long j, Object[] objArr);

    UsbMidiDeviceFactoryAndroid(Context context, long j) {
        this.mUsbManager = (UsbManager) context.getSystemService("usb");
        this.mNativePointer = j;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addAction(ACTION_USB_PERMISSION);
        context.registerReceiver(this.mReceiver, intentFilter);
        this.mRequestedDevices = new HashSet();
    }

    static UsbMidiDeviceFactoryAndroid create(Context context, long j) {
        return new UsbMidiDeviceFactoryAndroid(context, j);
    }

    boolean enumerateDevices(Context context) {
        this.mIsEnumeratingDevices = true;
        HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
        if (deviceList.isEmpty()) {
            this.mIsEnumeratingDevices = false;
            return false;
        }
        Iterator<UsbDevice> it = deviceList.values().iterator();
        while (it.hasNext()) {
            requestDevicePermissionIfNecessary(context, it.next());
        }
        return !this.mRequestedDevices.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDevicePermissionIfNecessary(Context context, UsbDevice usbDevice) {
        Iterator<UsbDevice> it = this.mRequestedDevices.iterator();
        while (it.hasNext()) {
            if (it.next().getDeviceId() == usbDevice.getDeviceId()) {
                return;
            }
        }
        for (int i = 0; i < usbDevice.getInterfaceCount(); i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (usbInterface.getInterfaceClass() == 1 && usbInterface.getInterfaceSubclass() == 3) {
                this.mUsbManager.requestPermission(usbDevice, PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0));
                this.mRequestedDevices.add(usbDevice);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUsbDeviceDetached(UsbDevice usbDevice) {
        Iterator<UsbDevice> it = this.mRequestedDevices.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UsbDevice next = it.next();
            if (next.getDeviceId() == usbDevice.getDeviceId()) {
                this.mRequestedDevices.remove(next);
                break;
            }
        }
        for (int i = 0; i < this.mDevices.size(); i++) {
            UsbMidiDeviceAndroid usbMidiDeviceAndroid = this.mDevices.get(i);
            if (!usbMidiDeviceAndroid.isClosed() && usbMidiDeviceAndroid.getUsbDevice().getDeviceId() == usbDevice.getDeviceId()) {
                usbMidiDeviceAndroid.close();
                if (this.mIsEnumeratingDevices) {
                    this.mDevices.remove(i);
                    return;
                }
                long j = this.mNativePointer;
                if (j != 0) {
                    nativeOnUsbMidiDeviceDetached(j, i);
                    return;
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onUsbDevicePermissionRequestDone(android.content.Context r6, android.content.Intent r7) {
        /*
            r5 = this;
            java.lang.String r6 = "device"
            android.os.Parcelable r6 = r7.getParcelableExtra(r6)
            android.hardware.usb.UsbDevice r6 = (android.hardware.usb.UsbDevice) r6
            java.util.Set<android.hardware.usb.UsbDevice> r0 = r5.mRequestedDevices
            boolean r0 = r0.contains(r6)
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L1f
            java.util.Set<android.hardware.usb.UsbDevice> r0 = r5.mRequestedDevices
            r0.remove(r6)
            java.lang.String r0 = "permission"
            boolean r7 = r7.getBooleanExtra(r0, r1)
            if (r7 != 0) goto L20
        L1f:
            r6 = r2
        L20:
            if (r6 == 0) goto L49
            java.util.List<org.chromium.media.midi.UsbMidiDeviceAndroid> r7 = r5.mDevices
            java.util.Iterator r7 = r7.iterator()
        L28:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L49
            java.lang.Object r0 = r7.next()
            org.chromium.media.midi.UsbMidiDeviceAndroid r0 = (org.chromium.media.midi.UsbMidiDeviceAndroid) r0
            boolean r3 = r0.isClosed()
            if (r3 != 0) goto L28
            android.hardware.usb.UsbDevice r0 = r0.getUsbDevice()
            int r0 = r0.getDeviceId()
            int r3 = r6.getDeviceId()
            if (r0 != r3) goto L28
            r6 = r2
        L49:
            if (r6 == 0) goto L57
            org.chromium.media.midi.UsbMidiDeviceAndroid r2 = new org.chromium.media.midi.UsbMidiDeviceAndroid
            android.hardware.usb.UsbManager r7 = r5.mUsbManager
            r2.<init>(r7, r6)
            java.util.List<org.chromium.media.midi.UsbMidiDeviceAndroid> r6 = r5.mDevices
            r6.add(r2)
        L57:
            java.util.Set<android.hardware.usb.UsbDevice> r6 = r5.mRequestedDevices
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L60
            return
        L60:
            long r6 = r5.mNativePointer
            r3 = 0
            int r0 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r0 != 0) goto L69
            return
        L69:
            boolean r0 = r5.mIsEnumeratingDevices
            if (r0 == 0) goto L79
            java.util.List<org.chromium.media.midi.UsbMidiDeviceAndroid> r0 = r5.mDevices
            java.lang.Object[] r0 = r0.toArray()
            nativeOnUsbMidiDeviceRequestDone(r6, r0)
            r5.mIsEnumeratingDevices = r1
            goto L7e
        L79:
            if (r2 == 0) goto L7e
            nativeOnUsbMidiDeviceAttached(r6, r2)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.midi.UsbMidiDeviceFactoryAndroid.onUsbDevicePermissionRequestDone(android.content.Context, android.content.Intent):void");
    }

    void close(Context context) {
        this.mNativePointer = 0L;
        context.unregisterReceiver(this.mReceiver);
    }
}
