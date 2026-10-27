package g2;

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
    public static final z f5081a = new z();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z f5082b = new z(true);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5083c;

    public z() {
        this.f5083c = false;
    }

    @Override // g2.t
    public int e() {
        return 2;
    }

    @Override // g2.b
    public <T> T g(f2.a aVar, Type type, Object obj, Object obj2) {
        long timeInMillis;
        if (this.f5083c) {
            return (T) h(aVar, type, obj, obj2);
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return (T) new java.sql.Date(((Date) obj2).getTime());
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new java.sql.Date(l2.l.D0((BigDecimal) obj2));
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
        f2.e eVar = new f2.e(str);
        try {
            if (eVar.j1()) {
                timeInMillis = eVar.w0().getTimeInMillis();
            } else {
                try {
                    return (T) new java.sql.Date(aVar.t().parse(str).getTime());
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

    public <T> T h(f2.a aVar, Type type, Object obj, Object obj2) {
        long timeInMillis;
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return (T) new Timestamp(((Date) obj2).getTime());
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new Timestamp(l2.l.D0((BigDecimal) obj2));
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
        f2.e eVar = new f2.e(str);
        try {
            if (str.length() > 19 && str.charAt(4) == '-' && str.charAt(7) == '-' && str.charAt(10) == ' ' && str.charAt(13) == ':' && str.charAt(16) == ':' && str.charAt(19) == '.') {
                String strR = aVar.r();
                if (strR.length() != str.length() && strR == c2.a.DEFFAULT_DATE_FORMAT) {
                    return (T) Timestamp.valueOf(str);
                }
            }
            if (eVar.k1(false)) {
                timeInMillis = eVar.w0().getTimeInMillis();
            } else {
                try {
                    return (T) new Timestamp(aVar.t().parse(str).getTime());
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

    public z(boolean z7) {
        this.f5083c = false;
        this.f5083c = true;
    }
}
