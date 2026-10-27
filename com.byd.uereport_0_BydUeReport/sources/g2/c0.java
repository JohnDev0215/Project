package g2;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;

/* JADX INFO: compiled from: TimeDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f5027a = new c0();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        long timeInMillis;
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 16) {
            bVar.V(4);
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            bVar.m0(2);
            if (bVar.q() != 2) {
                throw new JSONException("syntax error");
            }
            long jI = bVar.i();
            bVar.V(13);
            if (bVar.q() != 13) {
                throw new JSONException("syntax error");
            }
            bVar.V(16);
            return (T) new Time(jI);
        }
        T t7 = (T) aVar.N();
        if (t7 == 0) {
            return null;
        }
        if (t7 instanceof Time) {
            return t7;
        }
        if (t7 instanceof BigDecimal) {
            return (T) new Time(l2.l.D0((BigDecimal) t7));
        }
        if (t7 instanceof Number) {
            return (T) new Time(((Number) t7).longValue());
        }
        if (!(t7 instanceof String)) {
            throw new JSONException("parse error");
        }
        String str = (String) t7;
        if (str.length() == 0) {
            return null;
        }
        f2.e eVar = new f2.e(str);
        if (eVar.j1()) {
            timeInMillis = eVar.w0().getTimeInMillis();
        } else {
            boolean z7 = false;
            int i8 = 0;
            while (true) {
                if (i8 >= str.length()) {
                    z7 = true;
                    break;
                }
                char cCharAt = str.charAt(i8);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i8++;
            }
            if (!z7) {
                eVar.close();
                return (T) Time.valueOf(str);
            }
            timeInMillis = Long.parseLong(str);
        }
        eVar.close();
        return (T) new Time(timeInMillis);
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
