package com.google.android.projection.transport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.google.android.projection.logging.Log;
import com.google.android.projection.transport.TransportFactory;
import com.google.android.projection.transport.usb.UsbHostTransport;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class UsbHostDelegate implements TransportFactory.TransportFactoryDelegate {
    private static final String TAG = "AHU.USB_HOST_CONNECTION";
    private final Context mAppContext;
    private int mFactoryId;
    private boolean mIsReceiverRegistered;
    private final Map<UsbDevice, TransportFactory.Callback> mCallbacks = new ArrayMap();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.google.android.projection.transport.UsbHostDelegate.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UsbDevice device;
            TransportFactory.Callback callback;
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction()) && (device = (UsbDevice) intent.getParcelableExtra("device")) != null && (callback = (TransportFactory.Callback) UsbHostDelegate.this.mCallbacks.remove(device)) != null) {
                Log.i("AHU.USB_HOST_CONNECTION", "Disconnected, finishing");
                callback.onDisconnected(UsbHostDelegate.this.mFactoryId);
            }
        }
    };

    public UsbHostDelegate(Context context, int factoryId) {
        this.mFactoryId = -1;
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        applicationContext.registerReceiver(this.mReceiver, new IntentFilter("android.hardware.usb.action.USB_DEVICE_DETACHED"));
        this.mIsReceiverRegistered = true;
        this.mFactoryId = factoryId;
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public void shutdown() {
        if (this.mIsReceiverRegistered) {
            this.mAppContext.unregisterReceiver(this.mReceiver);
            this.mIsReceiverRegistered = false;
        }
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public TransportFactory.FactoryHandle tryOpen(Parcelable device, TransportFactory.Callback callback) throws IOException {
        if (!(device instanceof UsbDevice)) {
            return null;
        }
        UsbManager manager = (UsbManager) this.mAppContext.getSystemService("usb");
        if (manager == null) {
            throw new IOException("Unable to get USB manager");
        }
        UsbDevice usbDevice = (UsbDevice) device;
        UsbHostTransport transport = new UsbHostTransport(manager, usbDevice);
        Log.d("AHU.USB_HOST_CONNECTION", "Started USB host connection");
        this.mCallbacks.put(usbDevice, callback);
        callback.onConnected(this.mFactoryId, transport);
        return TransportFactory.DO_NOTHING_HANDLE;
    }
}
