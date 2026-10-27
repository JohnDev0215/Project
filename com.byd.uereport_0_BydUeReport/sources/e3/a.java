package e3;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import com.byd.bt.model.BTMajorType;
import m4.d;

/* JADX INFO: compiled from: BTUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static BTMajorType[] f4586a = {BTMajorType.PHONE};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f4587b = false;

    public static BTMajorType a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getBluetoothClass() == null) {
            return null;
        }
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
        BTMajorType bTMajorType = majorDeviceClass != 0 ? majorDeviceClass != 256 ? majorDeviceClass != 512 ? majorDeviceClass != 768 ? majorDeviceClass != 1024 ? majorDeviceClass != 1280 ? majorDeviceClass != 1536 ? majorDeviceClass != 1792 ? majorDeviceClass != 2048 ? majorDeviceClass != 2304 ? BTMajorType.UNKNOW : BTMajorType.HEALTH : BTMajorType.TOY : BTMajorType.WEARABLE : BTMajorType.IMAGING : BTMajorType.PERIPHERAL : BTMajorType.AUDIO_VIDEO : BTMajorType.NETWORKING : BTMajorType.PHONE : BTMajorType.COMPUTER : BTMajorType.MISC;
        x3.a.c("BTUtil", "--getBTMajorType--device: " + d.c(bluetoothDevice) + ", typeMajor: " + bTMajorType + ", btclass: " + bluetoothClass + ", mdClass: " + majorDeviceClass);
        return bTMajorType;
    }

    public static boolean b(BluetoothDevice bluetoothDevice) {
        return c(a(bluetoothDevice));
    }

    public static boolean c(BTMajorType bTMajorType) {
        if (f4587b) {
            return true;
        }
        for (BTMajorType bTMajorType2 : f4586a) {
            if (bTMajorType2.equals(bTMajorType)) {
                return true;
            }
        }
        return false;
    }

    public static void d(boolean z7) {
        f4587b = z7;
    }
}
