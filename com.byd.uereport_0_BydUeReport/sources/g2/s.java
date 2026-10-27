package g2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: compiled from: NumberDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f5072a = new s();

    /* JADX WARN: Type inference failed for: r9v24, types: [T, java.math.BigDecimal] */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 2) {
            if (type == Double.TYPE || type == Double.class) {
                String strL0 = bVar.l0();
                bVar.V(16);
                return (T) Double.valueOf(Double.parseDouble(strL0));
            }
            long jI = bVar.i();
            bVar.V(16);
            if (type == Short.TYPE || type == Short.class) {
                if (jI <= 32767 && jI >= -32768) {
                    return (T) Short.valueOf((short) jI);
                }
                throw new JSONException("short overflow : " + jI);
            }
            if (type != Byte.TYPE && type != Byte.class) {
                return (jI < -2147483648L || jI > 2147483647L) ? (T) Long.valueOf(jI) : (T) Integer.valueOf((int) jI);
            }
            if (jI <= 127 && jI >= -128) {
                return (T) Byte.valueOf((byte) jI);
            }
            throw new JSONException("short overflow : " + jI);
        }
        if (bVar.q() == 3) {
            if (type == Double.TYPE || type == Double.class) {
                String strL02 = bVar.l0();
                bVar.V(16);
                return (T) Double.valueOf(Double.parseDouble(strL02));
            }
            if (type == Short.TYPE || type == Short.class) {
                BigDecimal bigDecimalZ = bVar.Z();
                bVar.V(16);
                return (T) Short.valueOf(l2.l.L0(bigDecimalZ));
            }
            if (type == Byte.TYPE || type == Byte.class) {
                BigDecimal bigDecimalZ2 = bVar.Z();
                bVar.V(16);
                return (T) Byte.valueOf(l2.l.e(bigDecimalZ2));
            }
            ?? r9 = (T) bVar.Z();
            bVar.V(16);
            return bVar.M(Feature.UseBigDecimal) ? r9 : (T) Double.valueOf(r9.doubleValue());
        }
        if (bVar.q() == 18 && "NaN".equals(bVar.n0())) {
            bVar.E();
            if (type == Double.class) {
                return (T) Double.valueOf(Double.NaN);
            }
            if (type == Float.class) {
                return (T) Float.valueOf(Float.NaN);
            }
            return null;
        }
        Object objN = aVar.N();
        if (objN == null) {
            return null;
        }
        if (type == Double.TYPE || type == Double.class) {
            try {
                return (T) l2.l.q(objN);
            } catch (Exception e8) {
                throw new JSONException("parseDouble error, field : " + obj, e8);
            }
        }
        if (type == Short.TYPE || type == Short.class) {
            try {
                return (T) l2.l.x(objN);
            } catch (Exception e9) {
                throw new JSONException("parseShort error, field : " + obj, e9);
            }
        }
        if (type != Byte.TYPE && type != Byte.class) {
            return (T) l2.l.i(objN);
        }
        try {
            return (T) l2.l.l(objN);
        } catch (Exception e10) {
            throw new JSONException("parseByte error, field : " + obj, e10);
        }
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
