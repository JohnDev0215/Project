package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: compiled from: BigDecimalCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigDecimal f382a = BigDecimal.valueOf(-9007199254740991L);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigDecimal f383b = BigDecimal.valueOf(9007199254740991L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f384c = new l();

    public static <T> T f(b.a.a.j.a aVar) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 2) {
            T t = (T) bVar.J();
            bVar.F(16);
            return t;
        }
        if (bVar.j() == 3) {
            T t2 = (T) bVar.J();
            bVar.F(16);
            return t2;
        }
        Object objZ = aVar.z();
        if (objZ == null) {
            return null;
        }
        return (T) b.a.a.n.l.i(objZ);
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        try {
            return (T) f(aVar);
        } catch (Exception e2) {
            throw new JSONException("parseDecimal error, field : " + obj, e2);
        }
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigDecimal bigDecimal = (BigDecimal) obj;
        int iScale = bigDecimal.scale();
        String string = (!SerializerFeature.isEnabled(i, d1Var.i, SerializerFeature.WriteBigDecimalAsPlain) || iScale < -100 || iScale >= 100) ? bigDecimal.toString() : bigDecimal.toPlainString();
        if (iScale == 0 && string.length() >= 16 && SerializerFeature.isEnabled(i, d1Var.i, SerializerFeature.BrowserCompatible) && (bigDecimal.compareTo(f382a) < 0 || bigDecimal.compareTo(f383b) > 0)) {
            d1Var.N(string);
            return;
        }
        d1Var.write(string);
        if (d1Var.l(SerializerFeature.WriteClassName) && type != BigDecimal.class && bigDecimal.scale() == 0) {
            d1Var.write(46);
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
