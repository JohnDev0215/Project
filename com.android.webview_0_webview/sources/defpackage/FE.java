package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattCharacteristicWrapper;
import org.chromium.device.bluetooth.Wrappers$BluetoothGattDescriptorWrapper;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FE extends BluetoothGattCallback {
    public final CE a;
    public final Wrappers$BluetoothDeviceWrapper b;

    public FE(CE ce, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.a = ce;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Ts.d("Bluetooth", "wrapper onCharacteristicChanged.", new Object[0]);
        CE ce = this.a;
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic);
        BE be = (BE) ce;
        Objects.requireNonNull(be);
        Ts.d("Bluetooth", "device onCharacteristicChanged.", new Object[0]);
        byte[] value = wrappers$BluetoothGattCharacteristicWrapper.a.getValue();
        JE jeA = JE.a();
        RunnableC1701wE runnableC1701wE = new RunnableC1701wE(be, wrappers$BluetoothGattCharacteristicWrapper, value);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1701wE);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        CE ce = this.a;
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic);
        BE be = (BE) ce;
        Objects.requireNonNull(be);
        JE jeA = JE.a();
        RunnableC1756xE runnableC1756xE = new RunnableC1756xE(be, wrappers$BluetoothGattCharacteristicWrapper, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1756xE);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        CE ce = this.a;
        Wrappers$BluetoothGattCharacteristicWrapper wrappers$BluetoothGattCharacteristicWrapper = (Wrappers$BluetoothGattCharacteristicWrapper) this.b.b.get(bluetoothGattCharacteristic);
        BE be = (BE) ce;
        Objects.requireNonNull(be);
        JE jeA = JE.a();
        RunnableC1811yE runnableC1811yE = new RunnableC1811yE(be, wrappers$BluetoothGattCharacteristicWrapper, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1811yE);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        BE be = (BE) this.a;
        Objects.requireNonNull(be);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i2 == 2 ? "Connected" : "Disconnected";
        Ts.d("Bluetooth", "onConnectionStateChange status:%d newState:%s", objArr);
        JE jeA = JE.a();
        RunnableC1591uE runnableC1591uE = new RunnableC1591uE(be, i2, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1591uE);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        CE ce = this.a;
        Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = (Wrappers$BluetoothGattDescriptorWrapper) this.b.c.get(bluetoothGattDescriptor);
        BE be = (BE) ce;
        Objects.requireNonNull(be);
        JE jeA = JE.a();
        RunnableC1866zE runnableC1866zE = new RunnableC1866zE(be, wrappers$BluetoothGattDescriptorWrapper, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1866zE);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        CE ce = this.a;
        Wrappers$BluetoothGattDescriptorWrapper wrappers$BluetoothGattDescriptorWrapper = (Wrappers$BluetoothGattDescriptorWrapper) this.b.c.get(bluetoothGattDescriptor);
        BE be = (BE) ce;
        Objects.requireNonNull(be);
        JE jeA = JE.a();
        AE ae = new AE(be, wrappers$BluetoothGattDescriptorWrapper, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(ae);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        BE be = (BE) this.a;
        Objects.requireNonNull(be);
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = i == 0 ? "OK" : "Error";
        Ts.d("Bluetooth", "onServicesDiscovered status:%d==%s", objArr);
        JE jeA = JE.a();
        RunnableC1646vE runnableC1646vE = new RunnableC1646vE(be, i);
        Objects.requireNonNull(jeA);
        ThreadUtils.g(runnableC1646vE);
    }
}
