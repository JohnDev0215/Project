package b.b.g.p;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;

/* JADX INFO: compiled from: RowUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static boolean a() {
        String strA = c.a(BYDAutoSysPropertiesDevice.BUILD_REGION, "other");
        b.b.m.c.a.a("RowUtils", "isRowVersion---value: " + strA);
        if (!"ROW".equals(strA)) {
            return false;
        }
        b.b.m.c.a.d("RowUtils", "isRowVersion: yes");
        return true;
    }
}
