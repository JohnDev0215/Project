package org.chromium.device.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import java.util.List;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.components.location.LocationUtils;
import org.chromium.device.bluetooth.Wrappers;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeBluetoothAdapter extends BroadcastReceiver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "Bluetooth";
    private final Wrappers.BluetoothAdapterWrapper mAdapter;
    private long mNativeBluetoothAdapterAndroid;
    private ScanCallback mScanCallback;

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeCreateOrUpdateDeviceOnScan(long j, String str, Object obj, List<ParcelUuid> list);

    private native void nativeOnAdapterStateChanged(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnScanFailed(long j);

    public ChromeBluetoothAdapter(long j, Wrappers.BluetoothAdapterWrapper bluetoothAdapterWrapper) {
        this.mNativeBluetoothAdapterAndroid = j;
        this.mAdapter = bluetoothAdapterWrapper;
        registerBroadcastReceiver();
        if (bluetoothAdapterWrapper == null) {
            Log.i(TAG, "ChromeBluetoothAdapter created with no adapterWrapper.", new Object[0]);
        } else {
            Log.i(TAG, "ChromeBluetoothAdapter created with provided adapterWrapper.", new Object[0]);
        }
    }

    private void onBluetoothAdapterAndroidDestruction() {
        stopScan();
        this.mNativeBluetoothAdapterAndroid = 0L;
        unregisterBroadcastReceiver();
    }

    private static ChromeBluetoothAdapter create(long j, Object obj) {
        return new ChromeBluetoothAdapter(j, (Wrappers.BluetoothAdapterWrapper) obj);
    }

    private String getAddress() {
        return isPresent() ? this.mAdapter.getAddress() : "";
    }

    private String getName() {
        return isPresent() ? this.mAdapter.getName() : "";
    }

    private boolean isPresent() {
        return this.mAdapter != null;
    }

    private boolean isPowered() {
        return isPresent() && this.mAdapter.isEnabled();
    }

    private boolean setPowered(boolean z) {
        return z ? isPresent() && this.mAdapter.enable() : isPresent() && this.mAdapter.disable();
    }

    private boolean isDiscoverable() {
        return isPresent() && this.mAdapter.getScanMode() == 23;
    }

    private boolean isDiscovering() {
        return isPresent() && (this.mAdapter.isDiscovering() || this.mScanCallback != null);
    }

    private boolean startScan() {
        Wrappers.BluetoothLeScannerWrapper bluetoothLeScanner = this.mAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner == null || !canScan()) {
            return false;
        }
        ScanCallback scanCallback = new ScanCallback();
        this.mScanCallback = scanCallback;
        try {
            bluetoothLeScanner.startScan(null, 2, scanCallback);
            return true;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot start scan: " + e, new Object[0]);
            this.mScanCallback = null;
            return false;
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Adapter is off. Cannot start scan: " + e2, new Object[0]);
            this.mScanCallback = null;
            return false;
        }
    }

    private boolean stopScan() {
        if (this.mScanCallback == null) {
            return false;
        }
        try {
            Wrappers.BluetoothLeScannerWrapper bluetoothLeScanner = this.mAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.mScanCallback);
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Cannot stop scan: " + e, new Object[0]);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Adapter is off. Cannot stop scan: " + e2, new Object[0]);
        }
        this.mScanCallback = null;
        return true;
    }

    private boolean canScan() {
        Context context = this.mAdapter.getContext();
        return LocationUtils.getInstance().hasAndroidLocationPermission(context) && LocationUtils.getInstance().isSystemLocationSettingEnabled(context);
    }

    private void registerBroadcastReceiver() {
        Wrappers.BluetoothAdapterWrapper bluetoothAdapterWrapper = this.mAdapter;
        if (bluetoothAdapterWrapper != null) {
            bluetoothAdapterWrapper.getContext().registerReceiver(this, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        }
    }

    private void unregisterBroadcastReceiver() {
        Wrappers.BluetoothAdapterWrapper bluetoothAdapterWrapper = this.mAdapter;
        if (bluetoothAdapterWrapper != null) {
            bluetoothAdapterWrapper.getContext().unregisterReceiver(this);
        }
    }

    private class ScanCallback extends Wrappers.ScanCallbackWrapper {
        private ScanCallback() {
        }

        @Override // org.chromium.device.bluetooth.Wrappers.ScanCallbackWrapper
        public void onBatchScanResult(List<Wrappers.ScanResultWrapper> list) {
            Log.v(ChromeBluetoothAdapter.TAG, "onBatchScanResults");
        }

        @Override // org.chromium.device.bluetooth.Wrappers.ScanCallbackWrapper
        public void onScanResult(int i, Wrappers.ScanResultWrapper scanResultWrapper) {
            Log.v(ChromeBluetoothAdapter.TAG, "onScanResult %d %s %s", Integer.valueOf(i), scanResultWrapper.getDevice().getAddress(), scanResultWrapper.getDevice().getName());
            List<ParcelUuid> scanRecord_getServiceUuids = scanResultWrapper.getScanRecord_getServiceUuids();
            ChromeBluetoothAdapter chromeBluetoothAdapter = ChromeBluetoothAdapter.this;
            chromeBluetoothAdapter.nativeCreateOrUpdateDeviceOnScan(chromeBluetoothAdapter.mNativeBluetoothAdapterAndroid, scanResultWrapper.getDevice().getAddress(), scanResultWrapper.getDevice(), scanRecord_getServiceUuids);
        }

        @Override // org.chromium.device.bluetooth.Wrappers.ScanCallbackWrapper
        public void onScanFailed(int i) {
            Log.w(ChromeBluetoothAdapter.TAG, "onScanFailed: %d", Integer.valueOf(i));
            ChromeBluetoothAdapter chromeBluetoothAdapter = ChromeBluetoothAdapter.this;
            chromeBluetoothAdapter.nativeOnScanFailed(chromeBluetoothAdapter.mNativeBluetoothAdapterAndroid);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (isPresent() && "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            Log.w(TAG, "onReceive: BluetoothAdapter.ACTION_STATE_CHANGED: %s", getBluetoothStateString(intExtra));
            if (intExtra == 10) {
                nativeOnAdapterStateChanged(this.mNativeBluetoothAdapterAndroid, false);
            } else {
                if (intExtra != 12) {
                    return;
                }
                nativeOnAdapterStateChanged(this.mNativeBluetoothAdapterAndroid, true);
            }
        }
    }

    private String getBluetoothStateString(int i) {
        switch (i) {
            case 10:
                return "STATE_OFF";
            case 11:
                return "STATE_TURNING_ON";
            case 12:
                return "STATE_ON";
            case 13:
                return "STATE_TURNING_OFF";
            default:
                return "illegal state: " + i;
        }
    }
}
