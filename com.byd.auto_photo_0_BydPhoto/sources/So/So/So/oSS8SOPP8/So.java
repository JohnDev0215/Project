package So.So.So.oSS8SOPP8;

import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.provider.RemoteDeiviceUtils;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkBluetoothDeviceUtils.class, singleton = true)
public class So implements IDiLinkBluetoothDeviceUtils {
    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public void deleteDevice(ContentResolver contentResolver, BluetoothDevice bluetoothDevice) {
        RemoteDeiviceUtils.deleteDevice(contentResolver, bluetoothDevice);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public boolean deviceExist(ContentResolver contentResolver, BluetoothDevice bluetoothDevice) {
        return RemoteDeiviceUtils.deviceExist(contentResolver, bluetoothDevice);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public void insertDevice(ContentResolver contentResolver, BluetoothDevice bluetoothDevice) {
        RemoteDeiviceUtils.insertDevice(contentResolver, bluetoothDevice);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public boolean isMobile(BluetoothDevice bluetoothDevice) {
        return RemoteDeiviceUtils.isMobile(bluetoothDevice);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public Queue<BluetoothDevice> queryAutoConnectDevice(ContentResolver contentResolver) {
        return RemoteDeiviceUtils.queryAuoConnectDevice(contentResolver);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public int queryDeviceParam(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, int i) {
        return RemoteDeiviceUtils.queryDeviceParam(contentResolver, bluetoothDevice, i);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public HashMap<Integer, Integer> queryDeviceParams(ContentResolver contentResolver, BluetoothDevice bluetoothDevice) {
        return RemoteDeiviceUtils.queryDeviceParams(contentResolver, bluetoothDevice);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public BluetoothDevice queryLastConnectDevice(ContentResolver contentResolver) {
        return RemoteDeiviceUtils.queryLastConnectDevice(contentResolver);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public List<BluetoothDevice> queryTimerConnectDevice(ContentResolver contentResolver) {
        return RemoteDeiviceUtils.queryTimerConnectDevice(contentResolver);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public void updateDeviceParam(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, int i, int i2) {
        RemoteDeiviceUtils.updateDeviceParam(contentResolver, bluetoothDevice, i, i2);
    }

    @Override // com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils
    public void updateTime(ContentResolver contentResolver, BluetoothDevice bluetoothDevice, String str) {
        RemoteDeiviceUtils.updateTime(contentResolver, bluetoothDevice, str);
    }
}
