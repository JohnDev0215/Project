package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: compiled from: BigDecimalCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigDecimal f5283a = BigDecimal.valueOf(-9007199254740991L);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigDecimal f5284b = BigDecimal.valueOf(9007199254740991L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f5285c = new l();

    public static <T> T f(f2.a aVar) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 2) {
            T t7 = (T) bVar.Z();
            bVar.V(16);
            return t7;
        }
        if (bVar.q() == 3) {
            T t8 = (T) bVar.Z();
            bVar.V(16);
            return t8;
        }
        Object objN = aVar.N();
        if (objN == null) {
            return null;
        }
        return (T) l2.l.i(objN);
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        try {
            return (T) f(aVar);
        } catch (Exception e8) {
            throw new JSONException("parseDecimal error, field : " + obj, e8);
        }
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        int iScale = bigDecimal.scale();
        String string = (!SerializerFeature.isEnabled(i8, d1Var.f5215l, SerializerFeature.WriteBigDecimalAsPlain) || iScale < -100 || iScale >= 100) ? bigDecimal.toString() : bigDecimal.toPlainString();
        if (iScale == 0 && string.length() >= 16 && SerializerFeature.isEnabled(i8, d1Var.f5215l, SerializerFeature.BrowserCompatible) && (bigDecimal.compareTo(f5283a) < 0 || bigDecimal.compareTo(f5284b) > 0)) {
            d1Var.d0(string);
            return;
        }
        d1Var.write(string);
        if (d1Var.t(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
            d1Var.write(46);
        }
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
