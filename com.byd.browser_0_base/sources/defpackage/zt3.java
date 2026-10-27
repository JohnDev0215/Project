package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.util.Log;
import org.chromium.device.bluetooth.ChromeBluetoothDevice;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;

/* JADX INFO: compiled from: Wrappers.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zt3 extends BluetoothGattCallback {
    public final vd1 a;
    public final Wrappers$BluetoothDeviceWrapper b;

    public zt3(ChromeBluetoothDevice.a aVar, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.a = aVar;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Log.i("cr_Bluetooth", "wrapper onCharacteristicChanged.");
        this.a.k(this.b.b.get(bluetoothGattCharacteristic));
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.a.l(this.b.b.get(bluetoothGattCharacteristic), i);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        this.a.m(this.b.b.get(bluetoothGattCharacteristic), i);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.a.n(i, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        this.a.o(this.b.c.get(bluetoothGattDescriptor), i);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        this.a.p(this.b.c.get(bluetoothGattDescriptor), i);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.a.y(i, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        this.a.B(i);
    }
}
