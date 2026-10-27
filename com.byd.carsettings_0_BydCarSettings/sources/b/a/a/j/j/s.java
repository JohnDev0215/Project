package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: compiled from: NumberDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f299a = new s();

    /* JADX WARN: Type inference failed for: r9v24, types: [T, java.math.BigDecimal] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String strT = bVar.T();
                bVar.F(16);
                return (T) Double.valueOf(Double.parseDouble(strT));
            }
            long jE = bVar.e();
            bVar.F(16);
            if (type == Short.TYPE || type == Short.class) {
                if (jE <= 32767 && jE >= -32768) {
                    return (T) Short.valueOf((short) jE);
                }
                throw new JSONException("short overflow : " + jE);
            }
            if (type != Byte.TYPE && type != Byte.class) {
                return (jE < -2147483648L || jE > 2147483647L) ? (T) Long.valueOf(jE) : (T) Integer.valueOf((int) jE);
            }
            if (jE <= 127 && jE >= -128) {
                return (T) Byte.valueOf((byte) jE);
            }
            throw new JSONException("short overflow : " + jE);
        }
        if (bVar.j() == 3) {
            if (type == Double.TYPE || type == Double.class) {
                String strT2 = bVar.T();
                bVar.F(16);
                return (T) Double.valueOf(Double.parseDouble(strT2));
            }
            if (type == Short.TYPE || type == Short.class) {
                BigDecimal bigDecimalJ = bVar.J();
                bVar.F(16);
                return (T) Short.valueOf(b.a.a.n.l.L0(bigDecimalJ));
            }
            if (type == Byte.TYPE || type == Byte.class) {
                BigDecimal bigDecimalJ2 = bVar.J();
                bVar.F(16);
                return (T) Byte.valueOf(b.a.a.n.l.e(bigDecimalJ2));
            }
            ?? r9 = (T) bVar.J();
            bVar.F(16);
            return bVar.y(Feature.UseBigDecimal) ? r9 : (T) Double.valueOf(r9.doubleValue());
        }
        if (bVar.j() == 18 && "NaN".equals(bVar.V())) {
            bVar.u();
            if (type == Double.class) {
                return (T) Double.valueOf(Double.NaN);
            }
            if (type == Float.class) {
                return (T) Float.valueOf(Float.NaN);
            }
            return null;
        }
        Object objZ = aVar.z();
        if (objZ == null) {
            return null;
        }
        if (type == Double.TYPE || type == Double.class) {
            try {
                return (T) b.a.a.n.l.q(objZ);
            } catch (Exception e2) {
                throw new JSONException("parseDouble error, field : " + obj, e2);
            }
        }
        if (type == Short.TYPE || type == Short.class) {
            try {
                return (T) b.a.a.n.l.x(objZ);
            } catch (Exception e3) {
                throw new JSONException("parseShort error, field : " + obj, e3);
            }
        }
        if (type != Byte.TYPE && type != Byte.class) {
            return (T) b.a.a.n.l.i(objZ);
        }
        try {
            return (T) b.a.a.n.l.l(objZ);
        } catch (Exception e4) {
            throw new JSONException("parseByte error, field : " + obj, e4);
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
