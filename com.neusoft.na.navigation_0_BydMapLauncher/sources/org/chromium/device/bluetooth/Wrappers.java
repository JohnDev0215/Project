package org.chromium.device.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
class Wrappers {
    public static final int DEVICE_CLASS_UNSPECIFIED = 7936;
    private static final String TAG = "Bluetooth";

    Wrappers() {
    }

    static class BluetoothAdapterWrapper {
        private final BluetoothAdapter mAdapter;
        protected final Context mContext;
        protected BluetoothLeScannerWrapper mScannerWrapper;

        public static BluetoothAdapterWrapper createWithDefaultAdapter(Context context) {
            if (!(context.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0 && context.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0)) {
                Log.w(Wrappers.TAG, "BluetoothAdapterWrapper.create failed: Lacking Bluetooth permissions.", new Object[0]);
                return null;
            }
            if (!(context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le"))) {
                Log.i(Wrappers.TAG, "BluetoothAdapterWrapper.create failed: No Low Energy support.", new Object[0]);
                return null;
            }
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                Log.i(Wrappers.TAG, "BluetoothAdapterWrapper.create failed: Default adapter not found.", new Object[0]);
                return null;
            }
            return new BluetoothAdapterWrapper(defaultAdapter, context);
        }

        public BluetoothAdapterWrapper(BluetoothAdapter bluetoothAdapter, Context context) {
            this.mAdapter = bluetoothAdapter;
            this.mContext = context;
        }

        public boolean disable() {
            return this.mAdapter.disable();
        }

        public boolean enable() {
            return this.mAdapter.enable();
        }

        public String getAddress() {
            return this.mAdapter.getAddress();
        }

        public BluetoothLeScannerWrapper getBluetoothLeScanner() {
            BluetoothLeScanner bluetoothLeScanner = this.mAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner == null) {
                return null;
            }
            if (this.mScannerWrapper == null) {
                this.mScannerWrapper = new BluetoothLeScannerWrapper(bluetoothLeScanner);
            }
            return this.mScannerWrapper;
        }

        public Context getContext() {
            return this.mContext;
        }

        public String getName() {
            return this.mAdapter.getName();
        }

        public int getScanMode() {
            return this.mAdapter.getScanMode();
        }

        public boolean isDiscovering() {
            return this.mAdapter.isDiscovering();
        }

        public boolean isEnabled() {
            return this.mAdapter.isEnabled();
        }
    }

    static class BluetoothLeScannerWrapper {
        private final HashMap<ScanCallbackWrapper, ForwardScanCallbackToWrapper> mCallbacks = new HashMap<>();
        protected final BluetoothLeScanner mScanner;

        public BluetoothLeScannerWrapper(BluetoothLeScanner bluetoothLeScanner) {
            this.mScanner = bluetoothLeScanner;
        }

        public void startScan(List<ScanFilter> list, int i, ScanCallbackWrapper scanCallbackWrapper) {
            ScanSettings scanSettingsBuild = new ScanSettings.Builder().setScanMode(i).build();
            ForwardScanCallbackToWrapper forwardScanCallbackToWrapper = new ForwardScanCallbackToWrapper(scanCallbackWrapper);
            this.mCallbacks.put(scanCallbackWrapper, forwardScanCallbackToWrapper);
            this.mScanner.startScan(list, scanSettingsBuild, forwardScanCallbackToWrapper);
        }

        public void stopScan(ScanCallbackWrapper scanCallbackWrapper) {
            this.mScanner.stopScan(this.mCallbacks.remove(scanCallbackWrapper));
        }
    }

    static class ForwardScanCallbackToWrapper extends ScanCallback {
        final ScanCallbackWrapper mWrapperCallback;

        ForwardScanCallbackToWrapper(ScanCallbackWrapper scanCallbackWrapper) {
            this.mWrapperCallback = scanCallbackWrapper;
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<ScanResult> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new ScanResultWrapper(it.next()));
            }
            this.mWrapperCallback.onBatchScanResult(arrayList);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            this.mWrapperCallback.onScanResult(i, new ScanResultWrapper(scanResult));
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            this.mWrapperCallback.onScanFailed(i);
        }
    }

    static abstract class ScanCallbackWrapper {
        public abstract void onBatchScanResult(List<ScanResultWrapper> list);

        public abstract void onScanFailed(int i);

        public abstract void onScanResult(int i, ScanResultWrapper scanResultWrapper);

        ScanCallbackWrapper() {
        }
    }

    static class ScanResultWrapper {
        private final ScanResult mScanResult;

        public ScanResultWrapper(ScanResult scanResult) {
            this.mScanResult = scanResult;
        }

        public BluetoothDeviceWrapper getDevice() {
            return new BluetoothDeviceWrapper(this.mScanResult.getDevice());
        }

        public List<ParcelUuid> getScanRecord_getServiceUuids() {
            return this.mScanResult.getScanRecord().getServiceUuids();
        }
    }

    static class BluetoothDeviceWrapper {
        private final HashMap<BluetoothGattCharacteristic, BluetoothGattCharacteristicWrapper> mCharacteristicsToWrappers = new HashMap<>();
        private final HashMap<BluetoothGattDescriptor, BluetoothGattDescriptorWrapper> mDescriptorsToWrappers = new HashMap<>();
        private final BluetoothDevice mDevice;

        public BluetoothDeviceWrapper(BluetoothDevice bluetoothDevice) {
            this.mDevice = bluetoothDevice;
        }

        public BluetoothGattWrapper connectGatt(Context context, boolean z, BluetoothGattCallbackWrapper bluetoothGattCallbackWrapper) {
            return new BluetoothGattWrapper(this.mDevice.connectGatt(context, z, new ForwardBluetoothGattCallbackToWrapper(bluetoothGattCallbackWrapper, this)), this);
        }

        public String getAddress() {
            return this.mDevice.getAddress();
        }

        public int getBluetoothClass_getDeviceClass() {
            BluetoothDevice bluetoothDevice = this.mDevice;
            return (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) ? Wrappers.DEVICE_CLASS_UNSPECIFIED : this.mDevice.getBluetoothClass().getDeviceClass();
        }

        public int getBondState() {
            return this.mDevice.getBondState();
        }

        public String getName() {
            return this.mDevice.getName();
        }
    }

    static class BluetoothGattWrapper {
        private final BluetoothDeviceWrapper mDeviceWrapper;
        private final BluetoothGatt mGatt;

        BluetoothGattWrapper(BluetoothGatt bluetoothGatt, BluetoothDeviceWrapper bluetoothDeviceWrapper) {
            this.mGatt = bluetoothGatt;
            this.mDeviceWrapper = bluetoothDeviceWrapper;
        }

        public void disconnect() {
            this.mGatt.disconnect();
        }

        public void close() {
            this.mGatt.close();
        }

        public void discoverServices() {
            this.mGatt.discoverServices();
        }

        public List<BluetoothGattServiceWrapper> getServices() {
            List<BluetoothGattService> services = this.mGatt.getServices();
            ArrayList arrayList = new ArrayList(services.size());
            Iterator<BluetoothGattService> it = services.iterator();
            while (it.hasNext()) {
                arrayList.add(new BluetoothGattServiceWrapper(it.next(), this.mDeviceWrapper));
            }
            return arrayList;
        }

        boolean readCharacteristic(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper) {
            return this.mGatt.readCharacteristic(bluetoothGattCharacteristicWrapper.mCharacteristic);
        }

        boolean setCharacteristicNotification(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, boolean z) {
            return this.mGatt.setCharacteristicNotification(bluetoothGattCharacteristicWrapper.mCharacteristic, z);
        }

        boolean writeCharacteristic(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper) {
            return this.mGatt.writeCharacteristic(bluetoothGattCharacteristicWrapper.mCharacteristic);
        }

        boolean readDescriptor(BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper) {
            return this.mGatt.readDescriptor(bluetoothGattDescriptorWrapper.mDescriptor);
        }

        boolean writeDescriptor(BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper) {
            return this.mGatt.writeDescriptor(bluetoothGattDescriptorWrapper.mDescriptor);
        }
    }

    static class ForwardBluetoothGattCallbackToWrapper extends BluetoothGattCallback {
        final BluetoothDeviceWrapper mDeviceWrapper;
        final BluetoothGattCallbackWrapper mWrapperCallback;

        ForwardBluetoothGattCallbackToWrapper(BluetoothGattCallbackWrapper bluetoothGattCallbackWrapper, BluetoothDeviceWrapper bluetoothDeviceWrapper) {
            this.mWrapperCallback = bluetoothGattCallbackWrapper;
            this.mDeviceWrapper = bluetoothDeviceWrapper;
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Log.i(Wrappers.TAG, "wrapper onCharacteristicChanged.", new Object[0]);
            this.mWrapperCallback.onCharacteristicChanged((BluetoothGattCharacteristicWrapper) this.mDeviceWrapper.mCharacteristicsToWrappers.get(bluetoothGattCharacteristic));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            this.mWrapperCallback.onCharacteristicRead((BluetoothGattCharacteristicWrapper) this.mDeviceWrapper.mCharacteristicsToWrappers.get(bluetoothGattCharacteristic), i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            this.mWrapperCallback.onCharacteristicWrite((BluetoothGattCharacteristicWrapper) this.mDeviceWrapper.mCharacteristicsToWrappers.get(bluetoothGattCharacteristic), i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            this.mWrapperCallback.onDescriptorRead((BluetoothGattDescriptorWrapper) this.mDeviceWrapper.mDescriptorsToWrappers.get(bluetoothGattDescriptor), i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            this.mWrapperCallback.onDescriptorWrite((BluetoothGattDescriptorWrapper) this.mDeviceWrapper.mDescriptorsToWrappers.get(bluetoothGattDescriptor), i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            this.mWrapperCallback.onConnectionStateChange(i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            this.mWrapperCallback.onServicesDiscovered(i);
        }
    }

    static abstract class BluetoothGattCallbackWrapper {
        public abstract void onCharacteristicChanged(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper);

        public abstract void onCharacteristicRead(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, int i);

        public abstract void onCharacteristicWrite(BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper, int i);

        public abstract void onConnectionStateChange(int i, int i2);

        public abstract void onDescriptorRead(BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper, int i);

        public abstract void onDescriptorWrite(BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper, int i);

        public abstract void onServicesDiscovered(int i);

        BluetoothGattCallbackWrapper() {
        }
    }

    static class BluetoothGattServiceWrapper {
        private final BluetoothDeviceWrapper mDeviceWrapper;
        private final BluetoothGattService mService;

        public BluetoothGattServiceWrapper(BluetoothGattService bluetoothGattService, BluetoothDeviceWrapper bluetoothDeviceWrapper) {
            this.mService = bluetoothGattService;
            this.mDeviceWrapper = bluetoothDeviceWrapper;
        }

        public List<BluetoothGattCharacteristicWrapper> getCharacteristics() {
            List<BluetoothGattCharacteristic> characteristics = this.mService.getCharacteristics();
            ArrayList arrayList = new ArrayList(characteristics.size());
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                BluetoothGattCharacteristicWrapper bluetoothGattCharacteristicWrapper = (BluetoothGattCharacteristicWrapper) this.mDeviceWrapper.mCharacteristicsToWrappers.get(bluetoothGattCharacteristic);
                if (bluetoothGattCharacteristicWrapper == null) {
                    bluetoothGattCharacteristicWrapper = new BluetoothGattCharacteristicWrapper(bluetoothGattCharacteristic, this.mDeviceWrapper);
                    this.mDeviceWrapper.mCharacteristicsToWrappers.put(bluetoothGattCharacteristic, bluetoothGattCharacteristicWrapper);
                }
                arrayList.add(bluetoothGattCharacteristicWrapper);
            }
            return arrayList;
        }

        public int getInstanceId() {
            return this.mService.getInstanceId();
        }

        public UUID getUuid() {
            return this.mService.getUuid();
        }
    }

    static class BluetoothGattCharacteristicWrapper {
        final BluetoothGattCharacteristic mCharacteristic;
        final BluetoothDeviceWrapper mDeviceWrapper;

        public BluetoothGattCharacteristicWrapper(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothDeviceWrapper bluetoothDeviceWrapper) {
            this.mCharacteristic = bluetoothGattCharacteristic;
            this.mDeviceWrapper = bluetoothDeviceWrapper;
        }

        public List<BluetoothGattDescriptorWrapper> getDescriptors() {
            List<BluetoothGattDescriptor> descriptors = this.mCharacteristic.getDescriptors();
            ArrayList arrayList = new ArrayList(descriptors.size());
            for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
                BluetoothGattDescriptorWrapper bluetoothGattDescriptorWrapper = (BluetoothGattDescriptorWrapper) this.mDeviceWrapper.mDescriptorsToWrappers.get(bluetoothGattDescriptor);
                if (bluetoothGattDescriptorWrapper == null) {
                    bluetoothGattDescriptorWrapper = new BluetoothGattDescriptorWrapper(bluetoothGattDescriptor, this.mDeviceWrapper);
                    this.mDeviceWrapper.mDescriptorsToWrappers.put(bluetoothGattDescriptor, bluetoothGattDescriptorWrapper);
                }
                arrayList.add(bluetoothGattDescriptorWrapper);
            }
            return arrayList;
        }

        public int getInstanceId() {
            return this.mCharacteristic.getInstanceId();
        }

        public int getProperties() {
            return this.mCharacteristic.getProperties();
        }

        public UUID getUuid() {
            return this.mCharacteristic.getUuid();
        }

        public byte[] getValue() {
            return this.mCharacteristic.getValue();
        }

        public boolean setValue(byte[] bArr) {
            return this.mCharacteristic.setValue(bArr);
        }
    }

    static class BluetoothGattDescriptorWrapper {
        private final BluetoothGattDescriptor mDescriptor;
        final BluetoothDeviceWrapper mDeviceWrapper;

        public BluetoothGattDescriptorWrapper(BluetoothGattDescriptor bluetoothGattDescriptor, BluetoothDeviceWrapper bluetoothDeviceWrapper) {
            this.mDescriptor = bluetoothGattDescriptor;
            this.mDeviceWrapper = bluetoothDeviceWrapper;
        }

        public BluetoothGattCharacteristicWrapper getCharacteristic() {
            return (BluetoothGattCharacteristicWrapper) this.mDeviceWrapper.mCharacteristicsToWrappers.get(this.mDescriptor.getCharacteristic());
        }

        public UUID getUuid() {
            return this.mDescriptor.getUuid();
        }

        public byte[] getValue() {
            return this.mDescriptor.getValue();
        }

        public boolean setValue(byte[] bArr) {
            return this.mDescriptor.setValue(bArr);
        }
    }
}
