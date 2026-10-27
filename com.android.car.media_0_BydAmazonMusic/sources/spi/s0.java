package spi;

import android.car.utils.Logs;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import java.util.HashMap;

/* JADX INFO: compiled from: BydPermissionsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class s0 extends w {
    public static final String[] b = {BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON};
    public static final HashMap<String, Boolean> c = new a();

    /* JADX INFO: compiled from: BydPermissionsUtil.java */
    public class a extends HashMap<String, Boolean> {
        public a() {
            Boolean bool = Boolean.TRUE;
            put(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON, bool);
            put(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON, bool);
        }
    }

    /* JADX INFO: compiled from: BydPermissionsUtil.java */
    public static class b {
        public static final s0 a = new s0();

        private b() {
        }
    }

    @Override // spi.w
    public final String[] a() {
        return (String[]) b.clone();
    }

    @Override // spi.w
    public final boolean c(String str) {
        try {
            Boolean bool = c.get(str);
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Exception unused) {
            Logs.e("BydPermissions", str + " isNeedOnStart: error");
            return false;
        }
    }
}
