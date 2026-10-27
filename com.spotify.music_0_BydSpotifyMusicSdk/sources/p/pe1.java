package p;

import com.google.protobuf.MapFieldLite;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class pe1 {
    public static gh2 a(Object obj) {
        return ((oe1) obj).a;
    }

    public static MapFieldLite b(Object obj) {
        return (MapFieldLite) obj;
    }

    public static int c(Object obj, int i, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        oe1 oe1Var = (oe1) obj2;
        int iA = 0;
        if (!mapFieldLite.isEmpty()) {
            for (Map.Entry entry : mapFieldLite.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                oe1Var.getClass();
                int iL = com.google.protobuf.h.L(i);
                int iA2 = oe1.a(oe1Var.a, key, value);
                iA = u4.a(iA2, iA2, iL, iA);
            }
        }
        return iA;
    }

    public static boolean d(Object obj) {
        return !((MapFieldLite) obj).e();
    }

    public static MapFieldLite e(Object obj, Object obj2) {
        MapFieldLite mapFieldLiteG = (MapFieldLite) obj;
        MapFieldLite mapFieldLite = (MapFieldLite) obj2;
        if (!mapFieldLite.isEmpty()) {
            if (!mapFieldLiteG.e()) {
                mapFieldLiteG = mapFieldLiteG.g();
            }
            mapFieldLiteG.d();
            if (!mapFieldLite.isEmpty()) {
                mapFieldLiteG.putAll(mapFieldLite);
            }
        }
        return mapFieldLiteG;
    }

    public static MapFieldLite f() {
        return MapFieldLite.c.g();
    }

    public static void g(Object obj) {
        ((MapFieldLite) obj).f();
    }
}
