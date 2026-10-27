package defpackage;

import android.bluetooth.BluetoothGatt;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;

/* JADX INFO: compiled from: FindInPageDirectActionHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class um0 {
    public Object a;
    public Object b;

    public /* synthetic */ um0() {
    }

    public /* synthetic */ um0(BluetoothGatt bluetoothGatt, Wrappers$BluetoothDeviceWrapper wrappers$BluetoothDeviceWrapper) {
        this.a = bluetoothGatt;
        this.b = wrappers$BluetoothDeviceWrapper;
    }

    public final boolean a(OfflineItem offlineItem) {
        int i = offlineItem.A;
        if (i == 0 || i == 6 || (i == 4 && offlineItem.B) || new Date(offlineItem.r).after((Date) this.a)) {
            ((Set) this.b).add(offlineItem.e);
        }
        return ((Set) this.b).contains(offlineItem.e);
    }

    public um0(wc0 wc0Var) {
        this.b = new HashSet();
        this.a = new Date(new Date().getTime() - 1800000);
    }
}
