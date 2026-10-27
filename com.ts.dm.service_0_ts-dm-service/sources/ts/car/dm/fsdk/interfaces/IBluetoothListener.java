package ts.car.dm.fsdk.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothListener {
    void onBluetoothStateChanged(int i);

    void onDeviceBondStatusChanged(BluetoothDevice bluetoothDevice, int i);

    void onDeviceNameChanged(BluetoothDevice bluetoothDevice);

    void onDeviceProfileStatusChanged(BluetoothDevice bluetoothDevice, int i);
}
