package android.bluetooth;

import android.bluetooth.IBluetoothManager;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothManager {
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothManager";
    private final BluetoothAdapter mAdapter;

    public BluetoothManager(Context context) {
        if (context.getAttributionTag() == null) {
            context = context.getApplicationContext();
            if (context == null) {
                throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
            }
            this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        } else {
            IBinder service = ServiceManager.getService(BluetoothAdapter.BLUETOOTH_MANAGER_SERVICE);
            if (service != null) {
                this.mAdapter = new BluetoothAdapter(IBluetoothManager.Stub.asInterface(service));
            } else {
                Log.e(TAG, "Bluetooth binder is null");
                this.mAdapter = null;
            }
        }
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.setContext(context);
        }
    }

    public BluetoothAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice, int i) {
        Iterator<BluetoothDevice> it = getConnectedDevices(i).iterator();
        while (it.hasNext()) {
            if (bluetoothDevice.equals(it.next())) {
                return 2;
            }
        }
        return 0;
    }

    public List<BluetoothDevice> getConnectedDevices(int i) {
        if (i != 7 && i != 8) {
            throw new IllegalArgumentException("Profile not supported: " + i);
        }
        ArrayList arrayList = new ArrayList();
        try {
            IBluetoothGatt bluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
            return bluetoothGatt == null ? arrayList : bluetoothGatt.getDevicesMatchingConnectionStates(new int[]{2});
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return arrayList;
        }
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int i, int[] iArr) {
        if (i != 7 && i != 8) {
            throw new IllegalArgumentException("Profile not supported: " + i);
        }
        ArrayList arrayList = new ArrayList();
        try {
            IBluetoothGatt bluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
            return bluetoothGatt == null ? arrayList : bluetoothGatt.getDevicesMatchingConnectionStates(iArr);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return arrayList;
        }
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback bluetoothGattServerCallback) {
        return openGattServer(context, bluetoothGattServerCallback, 0);
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback bluetoothGattServerCallback, int i) {
        if (context == null || bluetoothGattServerCallback == null) {
            throw new IllegalArgumentException("null parameter: " + context + " " + bluetoothGattServerCallback);
        }
        try {
            IBluetoothGatt bluetoothGatt = this.mAdapter.getBluetoothManager().getBluetoothGatt();
            if (bluetoothGatt == null) {
                Log.e(TAG, "Fail to get GATT Server connection");
                return null;
            }
            BluetoothGattServer bluetoothGattServer = new BluetoothGattServer(bluetoothGatt, i);
            if (Boolean.valueOf(bluetoothGattServer.registerCallback(bluetoothGattServerCallback)).booleanValue()) {
                return bluetoothGattServer;
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }
}
