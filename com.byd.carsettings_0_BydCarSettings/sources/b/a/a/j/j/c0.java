package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Time;

/* JADX INFO: compiled from: TimeDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f273a = new c0();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        long timeInMillis;
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 16) {
            bVar.F(4);
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            bVar.U(2);
            if (bVar.j() != 2) {
                throw new JSONException("syntax error");
            }
            long jE = bVar.e();
            bVar.F(13);
            if (bVar.j() != 13) {
                throw new JSONException("syntax error");
            }
            bVar.F(16);
            return (T) new Time(jE);
        }
        T t = (T) aVar.z();
        if (t == 0) {
            return null;
        }
        if (t instanceof Time) {
            return t;
        }
        if (t instanceof BigDecimal) {
            return (T) new Time(b.a.a.n.l.D0((BigDecimal) t));
        }
        if (t instanceof Number) {
            return (T) new Time(((Number) t).longValue());
        }
        if (!(t instanceof String)) {
            throw new JSONException("parse error");
        }
        String str = (String) t;
        if (str.length() == 0) {
            return null;
        }
        b.a.a.j.e eVar = new b.a.a.j.e(str);
        if (eVar.Q0()) {
            timeInMillis = eVar.d0().getTimeInMillis();
        } else {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= str.length()) {
                    z = true;
                    break;
                }
                char cCharAt = str.charAt(i);
                if (cCharAt < '0' || cCharAt > '9') {
                    break;
                }
                i++;
            }
            if (!z) {
                eVar.close();
                return (T) Time.valueOf(str);
            }
            timeInMillis = Long.parseLong(str);
        }
        eVar.close();
        return (T) new Time(timeInMillis);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
