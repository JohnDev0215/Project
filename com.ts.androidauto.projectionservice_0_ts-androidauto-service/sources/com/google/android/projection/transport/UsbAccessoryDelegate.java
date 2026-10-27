package com.google.android.projection.transport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.google.android.projection.logging.Log;
import com.google.android.projection.transport.TransportFactory;
import com.google.android.projection.transport.usb.UsbAccessoryTransport;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class UsbAccessoryDelegate implements TransportFactory.TransportFactoryDelegate {
    private static final String TAG = "AHU.USB_ACC_CONNECTION";
    private final Context mAppContext;
    private int mFactoryId;
    private boolean mIsReceiverRegistered;
    private final BroadcastReceiver mAccessoryDetachedReceiver = new BroadcastReceiver() { // from class: com.google.android.projection.transport.UsbAccessoryDelegate.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UsbAccessory accessory;
            TransportFactory.Callback callback;
            if ("android.hardware.usb.action.USB_ACCESSORY_DETACHED".equals(intent.getAction()) && (accessory = (UsbAccessory) intent.getParcelableExtra("accessory")) != null && (callback = (TransportFactory.Callback) UsbAccessoryDelegate.this.mCallbacks.remove(accessory)) != null) {
                Log.i("AHU.USB_ACC_CONNECTION", "Disconnected, finishing");
                callback.onDisconnected(UsbAccessoryDelegate.this.mFactoryId);
            }
        }
    };
    private final Map<UsbAccessory, TransportFactory.Callback> mCallbacks = new ArrayMap();

    UsbAccessoryDelegate(Context context, int factoryId) {
        this.mFactoryId = -1;
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        applicationContext.registerReceiver(this.mAccessoryDetachedReceiver, new IntentFilter("android.hardware.usb.action.USB_ACCESSORY_DETACHED"));
        this.mIsReceiverRegistered = true;
        this.mFactoryId = factoryId;
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public void shutdown() {
        if (this.mIsReceiverRegistered) {
            this.mAppContext.unregisterReceiver(this.mAccessoryDetachedReceiver);
            this.mIsReceiverRegistered = false;
        }
    }

    @Override // com.google.android.projection.transport.TransportFactory.TransportFactoryDelegate
    public TransportFactory.FactoryHandle tryOpen(Parcelable device, TransportFactory.Callback callback) throws IOException {
        if (!(device instanceof UsbAccessory)) {
            return null;
        }
        UsbManager manager = (UsbManager) this.mAppContext.getSystemService("usb");
        if (manager == null) {
            throw new IOException("Unable to get USB manager");
        }
        UsbAccessory accessory = (UsbAccessory) device;
        UsbAccessoryTransport transport = new UsbAccessoryTransport(manager, accessory);
        Log.d("AHU.USB_ACC_CONNECTION", "Started USB accessory connection");
        this.mCallbacks.put(accessory, callback);
        callback.onConnected(this.mFactoryId, transport);
        return TransportFactory.DO_NOTHING_HANDLE;
    }
}
