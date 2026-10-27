package com.android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.car.BLEStreamProtos.VersionExchangeProto;

/* JADX INFO: loaded from: classes3.dex */
class BLEVersionExchangeResolver {
    private static final int MESSAGING_VERSION = 1;
    private static final int SECURITY_VERSION = 1;
    private static final String TAG = "BLEVersionExchangeResolver";

    static BleMessageStream resolveToStream(VersionExchangeProto.BLEVersionExchange versionExchange, BluetoothDevice device, BlePeripheralManager blePeripheralManager, BluetoothGattCharacteristic writeCharacteristic, BluetoothGattCharacteristic readCharacteristic) {
        int minMessagingVersion = versionExchange.getMinSupportedMessagingVersion();
        int minSecurityVersion = versionExchange.getMinSupportedSecurityVersion();
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Checking for supported version on (minMessagingVersion: " + minMessagingVersion + ", minSecurityVersion: " + minSecurityVersion + ")");
        }
        if (minMessagingVersion == 1 && minSecurityVersion == 1) {
            return new BleMessageStreamV1(new Handler(Looper.getMainLooper()), blePeripheralManager, device, writeCharacteristic, readCharacteristic);
        }
        return null;
    }

    static VersionExchangeProto.BLEVersionExchange makeVersionExchange() {
        return VersionExchangeProto.BLEVersionExchange.newBuilder().setMinSupportedMessagingVersion(1).setMaxSupportedMessagingVersion(1).setMinSupportedSecurityVersion(1).setMaxSupportedSecurityVersion(1).build();
    }

    private BLEVersionExchangeResolver() {
    }
}
