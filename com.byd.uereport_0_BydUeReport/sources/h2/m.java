package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

/* JADX INFO: compiled from: BigIntegerCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class m implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5286a = BigInteger.valueOf(-9007199254740991L);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f5287b = BigInteger.valueOf(9007199254740991L);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m f5288c = new m();

    public static <T> T f(f2.a aVar) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() != 2) {
            Object objN = aVar.N();
            if (objN == null) {
                return null;
            }
            return (T) l2.l.j(objN);
        }
        String strL0 = bVar.l0();
        bVar.V(16);
        if (strL0.length() <= 65535) {
            return (T) new BigInteger(strL0);
        }
        throw new JSONException("decimal overflow");
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) f(aVar);
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        BigInteger bigInteger = (BigInteger) obj;
        String string = bigInteger.toString();
        if (string.length() < 16 || !SerializerFeature.isEnabled(i8, d1Var.f5215l, SerializerFeature.BrowserCompatible) || (bigInteger.compareTo(f5286a) >= 0 && bigInteger.compareTo(f5287b) <= 0)) {
            d1Var.write(string);
        } else {
            d1Var.d0(string);
        }
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
