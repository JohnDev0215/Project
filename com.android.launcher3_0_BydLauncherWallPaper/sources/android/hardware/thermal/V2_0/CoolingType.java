package android.hardware.thermal.V2_0;

import android.bluetooth.BluetoothHeadset;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class CoolingType {
    public static final int BATTERY = 1;
    public static final int COMPONENT = 6;
    public static final int CPU = 2;
    public static final int FAN = 0;
    public static final int GPU = 3;
    public static final int MODEM = 4;
    public static final int NPU = 5;

    public static final String toString(int i) {
        return i == 0 ? "FAN" : i == 1 ? BluetoothHeadset.VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL : i == 2 ? "CPU" : i == 3 ? "GPU" : i == 4 ? "MODEM" : i == 5 ? "NPU" : i == 6 ? "COMPONENT" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("FAN");
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add(BluetoothHeadset.VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL);
        } else {
            i2 = 0;
        }
        if ((i & 2) == 2) {
            arrayList.add("CPU");
            i2 |= 2;
        }
        if ((i & 3) == 3) {
            arrayList.add("GPU");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("MODEM");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("NPU");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("COMPONENT");
            i2 |= 6;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
