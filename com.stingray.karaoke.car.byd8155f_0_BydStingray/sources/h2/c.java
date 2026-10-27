package H2;

import h2.t;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f2786n;
    public long[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long[] f2787p;

    public static Serializable a1(int i7, t tVar) {
        if (i7 == 0) {
            return Double.valueOf(Double.longBitsToDouble(tVar.p()));
        }
        if (i7 == 1) {
            return Boolean.valueOf(tVar.v() == 1);
        }
        if (i7 == 2) {
            return c1(tVar);
        }
        if (i7 != 3) {
            if (i7 == 8) {
                return b1(tVar);
            }
            if (i7 != 10) {
                if (i7 != 11) {
                    return null;
                }
                Date date = new Date((long) Double.longBitsToDouble(tVar.p()));
                tVar.I(2);
                return date;
            }
            int iZ = tVar.z();
            ArrayList arrayList = new ArrayList(iZ);
            for (int i10 = 0; i10 < iZ; i10++) {
                Serializable serializableA1 = a1(tVar.v(), tVar);
                if (serializableA1 != null) {
                    arrayList.add(serializableA1);
                }
            }
            return arrayList;
        }
        HashMap map = new HashMap();
        while (true) {
            String strC1 = c1(tVar);
            int iV = tVar.v();
            if (iV == 9) {
                return map;
            }
            Serializable serializableA12 = a1(iV, tVar);
            if (serializableA12 != null) {
                map.put(strC1, serializableA12);
            }
        }
    }

    public static HashMap b1(t tVar) {
        int iZ = tVar.z();
        HashMap map = new HashMap(iZ);
        for (int i7 = 0; i7 < iZ; i7++) {
            String strC1 = c1(tVar);
            Serializable serializableA1 = a1(tVar.v(), tVar);
            if (serializableA1 != null) {
                map.put(strC1, serializableA1);
            }
        }
        return map;
    }

    public static String c1(t tVar) {
        int iB = tVar.B();
        int i7 = tVar.f15242b;
        tVar.I(iB);
        return new String(tVar.f15241a, i7, iB);
    }
}
