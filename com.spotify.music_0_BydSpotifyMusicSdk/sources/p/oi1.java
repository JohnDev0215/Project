package p;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import coil.decode.DataSource;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class oi1 {
    public final l31 a;
    public final kk0 b;

    public oi1(l31 l31Var, kk0 kk0Var) {
        this.a = l31Var;
        this.b = kk0Var;
    }

    public static bh2 c(coil.intercept.b bVar, q31 q31Var, mi1 mi1Var, ni1 ni1Var) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(q31Var.a.getResources(), ni1Var.a);
        DataSource dataSource = DataSource.c;
        Map map = ni1Var.b;
        Object obj = map.get("coil#disk_cache_key");
        String str = obj instanceof String ? (String) obj : null;
        Object obj2 = map.get("coil#is_sampled");
        Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Bitmap.Config[] configArr = i.a;
        return new bh2(bitmapDrawable, q31Var, dataSource, mi1Var, str, zBooleanValue, (bVar instanceof coil.intercept.b) && bVar.g);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
    
        if (r7 != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006e, code lost:
    
        r16 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
    
        if (p.v03.b(r2, r20.toString()) != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x010a, code lost:
    
        if (r7 == false) goto L77;
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fe A[PHI: r16
  0x00fe: PHI (r16v1 p.ni1) = (r16v0 p.ni1), (r16v0 p.ni1), (r16v2 p.ni1) binds: [B:66:0x00fb, B:61:0x00ef, B:53:0x00db] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.ni1 a(p.q31 r18, p.mi1 r19, p.qd2 r20, coil.size.Scale r21) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.oi1.a(p.q31, p.mi1, p.qd2, coil.size.Scale):p.ni1");
    }

    public final mi1 b(q31 q31Var, Object obj, br1 br1Var, ii0 ii0Var) {
        String strA;
        Map mapH0;
        mi1 mi1Var = q31Var.e;
        if (mi1Var != null) {
            return mi1Var;
        }
        List list = ((coil.b) this.a).f.c;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                strA = null;
                break;
            }
            Pair pair = (Pair) list.get(i);
            n81 n81Var = (n81) pair.a();
            if (((Class) pair.b()).isAssignableFrom(obj.getClass())) {
                v03.g(n81Var, "null cannot be cast to non-null type coil.key.Keyer<kotlin.Any>");
                strA = n81Var.a(obj, br1Var);
                if (strA != null) {
                    break;
                }
            }
            i++;
        }
        if (strA == null) {
            return null;
        }
        Map map = q31Var.D.c;
        if (map.isEmpty()) {
            mapH0 = kotlin.collections.c.h0();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it = map.entrySet().iterator();
            if (it.hasNext()) {
                u4.z(((Map.Entry) it.next()).getValue());
                throw null;
            }
            mapH0 = linkedHashMap;
        }
        List list2 = q31Var.l;
        if (list2.isEmpty() && mapH0.isEmpty()) {
            return new mi1(strA, kotlin.collections.c.h0());
        }
        LinkedHashMap linkedHashMapN0 = kotlin.collections.c.n0(mapH0);
        if (!list2.isEmpty()) {
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                linkedHashMapN0.put(u4.e("coil#transformation_", i2), ((eu) list2.get(i2)).a);
            }
            linkedHashMapN0.put("coil#transformation_size", br1Var.d.toString());
        }
        return new mi1(strA, linkedHashMapN0);
    }
}
