package b.b.d.g;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import b.b.m.g.f;
import com.byd.bt.model.BTMajorType;

/* JADX INFO: compiled from: BTUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static BTMajorType[] f622a = {BTMajorType.PHONE};

    public static BTMajorType a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) {
            return null;
        }
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
        b.b.m.c.a.d("BTUtil", "--getBTMajorType--device: " + f.g(bluetoothDevice.getName()) + ", btclass: " + bluetoothClass + ", mdClass: " + majorDeviceClass);
        return majorDeviceClass != 0 ? majorDeviceClass != 256 ? majorDeviceClass != 512 ? majorDeviceClass != 768 ? majorDeviceClass != 1024 ? majorDeviceClass != 1280 ? majorDeviceClass != 1536 ? majorDeviceClass != 1792 ? majorDeviceClass != 2048 ? majorDeviceClass != 2304 ? BTMajorType.UNKNOW : BTMajorType.HEALTH : BTMajorType.TOY : BTMajorType.WEARABLE : BTMajorType.IMAGING : BTMajorType.PERIPHERAL : BTMajorType.AUDIO_VIDEO : BTMajorType.NETWORKING : BTMajorType.PHONE : BTMajorType.COMPUTER : BTMajorType.MISC;
    }

    public static boolean b(BluetoothDevice bluetoothDevice) {
        return c(a(bluetoothDevice));
    }

    public static boolean c(BTMajorType bTMajorType) {
        for (BTMajorType bTMajorType2 : f622a) {
            if (bTMajorType2.equals(bTMajorType)) {
                return true;
            }
        }
        return false;
    }
}
