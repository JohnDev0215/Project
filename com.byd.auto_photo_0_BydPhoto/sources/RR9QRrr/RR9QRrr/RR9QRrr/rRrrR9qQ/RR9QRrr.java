package RR9QRrr.RR9QRrr.RR9QRrr.rRrrR9qQ;

import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.provider.RemoteDeiviceUtils;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.provider.IDiLinkBluetoothDeviceUtils;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkBluetoothDeviceUtils.class, singleton = true)
public class RR9QRrr implements IDiLinkBluetoothDeviceUtils {
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
        throw new UnsupportedTargetPlatformException("Di4.0 没有 isMobile 方法！");
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
