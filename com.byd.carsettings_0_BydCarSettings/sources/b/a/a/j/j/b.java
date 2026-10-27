package b.a.a.j.j;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: compiled from: AbstractDateDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends e implements t {
    @Override // b.a.a.j.j.e, b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // b.a.a.j.j.e
    public <T> T f(b.a.a.j.a aVar, Type type, Object obj, String str, int i) throws Throwable {
        SimpleDateFormat simpleDateFormat;
        Object obj2;
        SimpleDateFormat simpleDateFormat2;
        b.a.a.j.b bVar = aVar.g;
        Object objZ = null;
        objZ = null;
        objZ = null;
        objZ = null;
        objZ = null;
        if (bVar.j() == 2) {
            long jE = bVar.e();
            bVar.F(16);
            if ("unixtime".equals(str)) {
                jE *= 1000;
            }
            objZ = Long.valueOf(jE);
        } else if (bVar.j() == 4) {
            String strV = bVar.V();
            if (str != null) {
                if ("yyyy-MM-dd HH:mm:ss.SSSSSSSSS".equals(str) && (type instanceof Class) && ((Class) type).getName().equals("java.sql.Timestamp")) {
                    return (T) b.a.a.n.l.B(strV);
                }
                try {
                    simpleDateFormat = new SimpleDateFormat(str, aVar.g.R());
                } catch (IllegalArgumentException e2) {
                    if (str.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                        try {
                            simpleDateFormat = new SimpleDateFormat(str.replaceAll(ExifInterface.GPS_DIRECTION_TRUE, "'T'"), aVar.g.R());
                        } catch (IllegalArgumentException unused) {
                            throw e2;
                        }
                    } else {
                        simpleDateFormat = null;
                    }
                }
                if (b.a.a.a.defaultTimeZone != null) {
                    simpleDateFormat.setTimeZone(aVar.g.W());
                }
                try {
                    obj2 = simpleDateFormat.parse(strV);
                } catch (ParseException unused2) {
                    obj2 = null;
                }
                if (obj2 == null && b.a.a.a.defaultLocale == Locale.CHINA) {
                    try {
                        simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
                    } catch (IllegalArgumentException e3) {
                        simpleDateFormat2 = simpleDateFormat;
                        if (str.contains(ExifInterface.GPS_DIRECTION_TRUE)) {
                            try {
                                simpleDateFormat2 = new SimpleDateFormat(str.replaceAll(ExifInterface.GPS_DIRECTION_TRUE, "'T'"), aVar.g.R());
                            } catch (IllegalArgumentException unused3) {
                                throw e3;
                            }
                        }
                    }
                    simpleDateFormat2.setTimeZone(aVar.g.W());
                    try {
                        obj2 = simpleDateFormat2.parse(strV);
                    } catch (ParseException unused4) {
                        obj2 = null;
                    }
                }
                if (obj2 != null) {
                    objZ = obj2;
                } else if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strV.length() == 19) {
                    try {
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", b.a.a.a.defaultLocale);
                        simpleDateFormat3.setTimeZone(b.a.a.a.defaultTimeZone);
                        objZ = simpleDateFormat3.parse(strV);
                    } catch (ParseException unused5) {
                    }
                }
            }
            if (objZ == null) {
                bVar.F(16);
                Object obj3 = strV;
                if (bVar.y(Feature.AllowISO8601DateFormat)) {
                    b.a.a.j.e eVar = new b.a.a.j.e(strV);
                    Object time = strV;
                    if (eVar.Q0()) {
                        time = eVar.d0().getTime();
                    }
                    eVar.close();
                    obj3 = time;
                }
                objZ = obj3;
            }
        } else if (bVar.j() == 8) {
            bVar.u();
        } else if (bVar.j() == 12) {
            bVar.u();
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            if (b.a.a.a.DEFAULT_TYPE_KEY.equals(bVar.V())) {
                bVar.u();
                aVar.a(17);
                Type typeI = aVar.i().i(bVar.V(), null, bVar.q());
                if (typeI != null) {
                    type = typeI;
                }
                aVar.a(4);
                aVar.a(16);
            }
            bVar.U(2);
            if (bVar.j() != 2) {
                throw new JSONException("syntax error : " + bVar.M());
            }
            long jE2 = bVar.e();
            bVar.u();
            objZ = Long.valueOf(jE2);
            aVar.a(13);
        } else if (aVar.v() == 2) {
            aVar.b0(0);
            aVar.a(16);
            if (bVar.j() != 4) {
                throw new JSONException("syntax error");
            }
            if (!"val".equals(bVar.V())) {
                throw new JSONException("syntax error");
            }
            bVar.u();
            aVar.a(17);
            objZ = aVar.z();
            aVar.a(13);
        } else {
            objZ = aVar.z();
        }
        return (T) g(aVar, type, obj, objZ);
    }

    public abstract <T> T g(b.a.a.j.a aVar, Type type, Object obj, Object obj2);
}
