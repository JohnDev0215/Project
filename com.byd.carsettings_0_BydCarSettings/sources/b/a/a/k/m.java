package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

/* JADX INFO: compiled from: BigIntegerCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f385a = BigInteger.valueOf(-9007199254740991L);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f386b = BigInteger.valueOf(9007199254740991L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f387c = new m();

    public static <T> T f(b.a.a.j.a aVar) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() != 2) {
            Object objZ = aVar.z();
            if (objZ == null) {
                return null;
            }
            return (T) b.a.a.n.l.j(objZ);
        }
        String strT = bVar.T();
        bVar.F(16);
        if (strT.length() <= 65535) {
            return (T) new BigInteger(strT);
        }
        throw new JSONException("decimal overflow");
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) f(aVar);
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigInteger bigInteger = (BigInteger) obj;
        String string = bigInteger.toString();
        if (string.length() < 16 || !SerializerFeature.isEnabled(i, d1Var.i, SerializerFeature.BrowserCompatible) || (bigInteger.compareTo(f385a) >= 0 && bigInteger.compareTo(f386b) <= 0)) {
            d1Var.write(string);
        } else {
            d1Var.N(string);
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
