package defpackage;

import android.bluetooth.le.ScanResult;
import androidx.preference.Preference;
import defpackage.bo2;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.device.bluetooth.Wrappers$BluetoothDeviceWrapper;

/* JADX INFO: compiled from: MethodCallsLogger.kt */
/* JADX INFO: loaded from: classes.dex */
public class ph1 implements mb1 {
    public Object e;

    public /* synthetic */ ph1(Object obj) {
        this.e = obj;
    }

    public final synchronized void a(bo2.a aVar) {
        if (!((ArrayList) this.e).contains(aVar)) {
            ((ArrayList) this.e).add(aVar);
        }
    }

    @Override // defpackage.mb1
    public boolean b(Preference preference) {
        return ((mb1) this.e).b(preference);
    }

    public final Wrappers$BluetoothDeviceWrapper c() {
        return new Wrappers$BluetoothDeviceWrapper(((ScanResult) this.e).getDevice());
    }

    @Override // defpackage.mb1
    public final int d() {
        return ((mb1) this.e).d();
    }

    @Override // defpackage.mb1
    public final boolean e() {
        return ((mb1) this.e).e();
    }

    public ph1(int i) {
        if (i != 2) {
            if (i != 6) {
                this.e = new HashMap();
            } else {
                this.e = new ArrayList();
            }
        }
    }
}
