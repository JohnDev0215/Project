package b.a.a.j.j;

import com.alibaba.fastjson.JSONException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/* JADX INFO: compiled from: SqlDateDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class z extends b implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f306a = new z();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z f307b = new z(true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f308c;

    public z() {
        this.f308c = false;
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }

    @Override // b.a.a.j.j.b
    public <T> T g(b.a.a.j.a aVar, Type type, Object obj, Object obj2) {
        long timeInMillis;
        if (this.f308c) {
            return (T) h(aVar, type, obj, obj2);
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return (T) new java.sql.Date(((Date) obj2).getTime());
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new java.sql.Date(b.a.a.n.l.D0((BigDecimal) obj2));
        }
        if (obj2 instanceof Number) {
            return (T) new java.sql.Date(((Number) obj2).longValue());
        }
        if (!(obj2 instanceof String)) {
            throw new JSONException("parse error : " + obj2);
        }
        String str = (String) obj2;
        if (str.length() == 0) {
            return null;
        }
        b.a.a.j.e eVar = new b.a.a.j.e(str);
        try {
            if (eVar.Q0()) {
                timeInMillis = eVar.d0().getTimeInMillis();
            } else {
                try {
                    return (T) new java.sql.Date(aVar.l().parse(str).getTime());
                } catch (ParseException unused) {
                    timeInMillis = Long.parseLong(str);
                }
            }
            eVar.close();
            return (T) new java.sql.Date(timeInMillis);
        } finally {
            eVar.close();
        }
    }

    public <T> T h(b.a.a.j.a aVar, Type type, Object obj, Object obj2) {
        long timeInMillis;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return (T) new Timestamp(((Date) obj2).getTime());
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new Timestamp(b.a.a.n.l.D0((BigDecimal) obj2));
        }
        if (obj2 instanceof Number) {
            return (T) new Timestamp(((Number) obj2).longValue());
        }
        if (!(obj2 instanceof String)) {
            throw new JSONException("parse error");
        }
        String str = (String) obj2;
        if (str.length() == 0) {
            return null;
        }
        b.a.a.j.e eVar = new b.a.a.j.e(str);
        try {
            if (str.length() > 19 && str.charAt(4) == '-' && str.charAt(7) == '-' && str.charAt(10) == ' ' && str.charAt(13) == ':' && str.charAt(16) == ':' && str.charAt(19) == '.') {
                String strK = aVar.k();
                if (strK.length() != str.length() && strK == b.a.a.a.DEFFAULT_DATE_FORMAT) {
                    return (T) Timestamp.valueOf(str);
                }
            }
            if (eVar.R0(false)) {
                timeInMillis = eVar.d0().getTimeInMillis();
            } else {
                try {
                    return (T) new Timestamp(aVar.l().parse(str).getTime());
                } catch (ParseException unused) {
                    timeInMillis = Long.parseLong(str);
                }
            }
            eVar.close();
            return (T) new Timestamp(timeInMillis);
        } finally {
            eVar.close();
        }
    }

    public z(boolean z) {
        this.f308c = false;
        this.f308c = true;
    }
}
