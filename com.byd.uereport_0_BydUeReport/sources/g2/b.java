package g2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: compiled from: AbstractDateDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends e implements t {
    @Override // g2.e, g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // g2.e
    public <T> T f(f2.a aVar, Type type, Object obj, String str, int i8) throws Throwable {
        SimpleDateFormat simpleDateFormat;
        Object obj2;
        SimpleDateFormat simpleDateFormat2;
        f2.b bVar = aVar.f4861j;
        Object objN = null;
        objN = null;
        objN = null;
        objN = null;
        objN = null;
        if (bVar.q() == 2) {
            long jI = bVar.i();
            bVar.V(16);
            if ("unixtime".equals(str)) {
                jI *= 1000;
            }
            objN = Long.valueOf(jI);
        } else if (bVar.q() == 4) {
            String strN0 = bVar.n0();
            if (str != null) {
                if ("yyyy-MM-dd HH:mm:ss.SSSSSSSSS".equals(str) && (type instanceof Class) && ((Class) type).getName().equals("java.sql.Timestamp")) {
                    return (T) l2.l.B(strN0);
                }
                try {
                    simpleDateFormat = new SimpleDateFormat(str, aVar.f4861j.h0());
                } catch (IllegalArgumentException e8) {
                    if (str.contains("T")) {
                        try {
                            simpleDateFormat = new SimpleDateFormat(str.replaceAll("T", "'T'"), aVar.f4861j.h0());
                        } catch (IllegalArgumentException unused) {
                            throw e8;
                        }
                    } else {
                        simpleDateFormat = null;
                    }
                }
                if (c2.a.defaultTimeZone != null) {
                    simpleDateFormat.setTimeZone(aVar.f4861j.o0());
                }
                try {
                    obj2 = simpleDateFormat.parse(strN0);
                } catch (ParseException unused2) {
                    obj2 = null;
                }
                if (obj2 == null && c2.a.defaultLocale == Locale.CHINA) {
                    try {
                        simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
                    } catch (IllegalArgumentException e9) {
                        simpleDateFormat2 = simpleDateFormat;
                        if (str.contains("T")) {
                            try {
                                simpleDateFormat2 = new SimpleDateFormat(str.replaceAll("T", "'T'"), aVar.f4861j.h0());
                            } catch (IllegalArgumentException unused3) {
                                throw e9;
                            }
                        }
                    }
                    simpleDateFormat2.setTimeZone(aVar.f4861j.o0());
                    try {
                        obj2 = simpleDateFormat2.parse(strN0);
                    } catch (ParseException unused4) {
                        obj2 = null;
                    }
                }
                if (obj2 != null) {
                    objN = obj2;
                } else if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strN0.length() == 19) {
                    try {
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", c2.a.defaultLocale);
                        simpleDateFormat3.setTimeZone(c2.a.defaultTimeZone);
                        objN = simpleDateFormat3.parse(strN0);
                    } catch (ParseException unused5) {
                    }
                }
            }
            if (objN == null) {
                bVar.V(16);
                Object obj3 = strN0;
                if (bVar.M(Feature.AllowISO8601DateFormat)) {
                    f2.e eVar = new f2.e(strN0);
                    Object time = strN0;
                    if (eVar.j1()) {
                        time = eVar.w0().getTime();
                    }
                    eVar.close();
                    obj3 = time;
                }
                objN = obj3;
            }
        } else if (bVar.q() == 8) {
            bVar.E();
        } else if (bVar.q() == 12) {
            bVar.E();
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            if (c2.a.DEFAULT_TYPE_KEY.equals(bVar.n0())) {
                bVar.E();
                aVar.e(17);
                Type typeI = aVar.o().i(bVar.n0(), null, bVar.x());
                if (typeI != null) {
                    type = typeI;
                }
                aVar.e(4);
                aVar.e(16);
            }
            bVar.m0(2);
            if (bVar.q() != 2) {
                throw new JSONException("syntax error : " + bVar.c0());
            }
            long jI2 = bVar.i();
            bVar.E();
            objN = Long.valueOf(jI2);
            aVar.e(13);
        } else if (aVar.F() == 2) {
            aVar.u0(0);
            aVar.e(16);
            if (bVar.q() != 4) {
                throw new JSONException("syntax error");
            }
            if (!"val".equals(bVar.n0())) {
                throw new JSONException("syntax error");
            }
            bVar.E();
            aVar.e(17);
            objN = aVar.N();
            aVar.e(13);
        } else {
            objN = aVar.N();
        }
        return (T) g(aVar, type, obj, objN);
    }

    public abstract <T> T g(f2.a aVar, Type type, Object obj, Object obj2);
}
