package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class w extends g2.b implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f5309a = new w();

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        char[] charArray;
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls == Date.class) {
            long time = ((Date) obj).getTime();
            if ((time + ((long) i0Var.f5249s.getOffset(time))) % 86400000 == 0 && !SerializerFeature.isEnabled(d1Var.f5215l, i8, SerializerFeature.WriteClassName)) {
                d1Var.d0(obj.toString());
                return;
            }
        }
        if (cls == Time.class) {
            long time2 = ((Time) obj).getTime();
            if ("unixtime".equals(i0Var.v())) {
                d1Var.Y(time2 / 1000);
                return;
            } else if ("millis".equals(i0Var.v())) {
                d1Var.Y(time2);
                return;
            } else if (time2 < 86400000) {
                d1Var.d0(obj.toString());
                return;
            }
        }
        int nanos = cls == Timestamp.class ? ((Timestamp) obj).getNanos() : 0;
        java.util.Date dateO = obj instanceof java.util.Date ? (java.util.Date) obj : l2.l.o(obj);
        if ("unixtime".equals(i0Var.v())) {
            d1Var.Y(dateO.getTime() / 1000);
            return;
        }
        if ("millis".equals(i0Var.v())) {
            d1Var.Y(dateO.getTime());
            return;
        }
        if (d1Var.t(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormatU = i0Var.u();
            if (dateFormatU == null) {
                String strW = i0Var.w();
                if (strW == null) {
                    strW = c2.a.DEFFAULT_DATE_FORMAT;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strW, i0Var.f5250t);
                simpleDateFormat.setTimeZone(i0Var.f5249s);
                dateFormatU = simpleDateFormat;
            }
            d1Var.d0(dateFormatU.format(dateO));
            return;
        }
        if (d1Var.t(SerializerFeature.WriteClassName) && cls != type) {
            if (cls == java.util.Date.class) {
                d1Var.write("new Date(");
                d1Var.Y(((java.util.Date) obj).getTime());
                d1Var.write(41);
                return;
            } else {
                d1Var.write(123);
                d1Var.E(c2.a.DEFAULT_TYPE_KEY);
                i0Var.I(cls.getName());
                d1Var.M(',', "val", ((java.util.Date) obj).getTime());
                d1Var.write(125);
                return;
            }
        }
        long time3 = dateO.getTime();
        if (!d1Var.t(SerializerFeature.UseISO8601DateFormat)) {
            d1Var.Y(time3);
            return;
        }
        int i9 = d1Var.t(SerializerFeature.UseSingleQuotes) ? 39 : 34;
        d1Var.write(i9);
        Calendar calendar = Calendar.getInstance(i0Var.f5249s, i0Var.f5250t);
        calendar.setTimeInMillis(time3);
        int i10 = calendar.get(1);
        int i11 = calendar.get(2) + 1;
        int i12 = calendar.get(5);
        int i13 = calendar.get(11);
        int i14 = calendar.get(12);
        int i15 = calendar.get(13);
        int i16 = calendar.get(14);
        if (nanos > 0) {
            charArray = "0000-00-00 00:00:00.000000000".toCharArray();
            l2.e.i(nanos, 29, charArray);
            l2.e.i(i15, 19, charArray);
            l2.e.i(i14, 16, charArray);
            l2.e.i(i13, 13, charArray);
            l2.e.i(i12, 10, charArray);
            l2.e.i(i11, 7, charArray);
            l2.e.i(i10, 4, charArray);
        } else if (i16 != 0) {
            char[] charArray2 = "0000-00-00T00:00:00.000".toCharArray();
            l2.e.i(i16, 23, charArray2);
            l2.e.i(i15, 19, charArray2);
            l2.e.i(i14, 16, charArray2);
            l2.e.i(i13, 13, charArray2);
            l2.e.i(i12, 10, charArray2);
            l2.e.i(i11, 7, charArray2);
            l2.e.i(i10, 4, charArray2);
            charArray = charArray2;
        } else if (i15 == 0 && i14 == 0 && i13 == 0) {
            char[] charArray3 = "0000-00-00".toCharArray();
            l2.e.i(i12, 10, charArray3);
            l2.e.i(i11, 7, charArray3);
            l2.e.i(i10, 4, charArray3);
            charArray = charArray3;
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            l2.e.i(i15, 19, charArray);
            l2.e.i(i14, 16, charArray);
            l2.e.i(i13, 13, charArray);
            l2.e.i(i12, 10, charArray);
            l2.e.i(i11, 7, charArray);
            l2.e.i(i10, 4, charArray);
        }
        if (nanos > 0) {
            int i17 = 0;
            while (i17 < 9 && charArray[(charArray.length - i17) - 1] == '0') {
                i17++;
            }
            d1Var.write(charArray, 0, charArray.length - i17);
            d1Var.write(i9);
            return;
        }
        d1Var.write(charArray);
        float offset = calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000.0f;
        int i18 = (int) offset;
        if (i18 == 0.0d) {
            d1Var.write(90);
        } else {
            if (i18 > 9) {
                d1Var.write(43);
                d1Var.V(i18);
            } else if (i18 > 0) {
                d1Var.write(43);
                d1Var.write(48);
                d1Var.V(i18);
            } else if (i18 < -9) {
                d1Var.write(45);
                d1Var.V(-i18);
            } else if (i18 < 0) {
                d1Var.write(45);
                d1Var.write(48);
                d1Var.V(-i18);
            }
            d1Var.write(58);
            d1Var.append(String.format("%02d", Integer.valueOf((int) (Math.abs(offset - i18) * 60.0f))));
        }
        d1Var.write(i9);
    }

    @Override // g2.t
    public int e() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v17, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r5v24, types: [T, java.util.Calendar] */
    @Override // g2.b
    public <T> T g(f2.a aVar, Type type, Object obj, Object obj2) {
        if (obj2 == 0) {
            return null;
        }
        if (obj2 instanceof java.util.Date) {
            return obj2;
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new java.util.Date(l2.l.D0((BigDecimal) obj2));
        }
        if (obj2 instanceof Number) {
            return (T) new java.util.Date(((Number) obj2).longValue());
        }
        if (!(obj2 instanceof String)) {
            throw new JSONException("parse error");
        }
        String strSubstring = (String) obj2;
        if (strSubstring.length() == 0) {
            return null;
        }
        if (strSubstring.length() == 23 && strSubstring.endsWith(" 000")) {
            strSubstring = strSubstring.substring(0, 19);
        }
        f2.e eVar = new f2.e(strSubstring);
        try {
            if (eVar.k1(false)) {
                ?? r52 = (T) eVar.w0();
                return type == Calendar.class ? r52 : (T) r52.getTime();
            }
            eVar.close();
            String strR = aVar.r();
            if (strSubstring.length() == strR.length() || (strSubstring.length() == 22 && strR.equals("yyyyMMddHHmmssSSSZ")) || (strSubstring.indexOf(84) != -1 && strR.contains("'T'") && strSubstring.length() + 2 == strR.length())) {
                try {
                    return (T) aVar.t().parse(strSubstring);
                } catch (ParseException unused) {
                }
            }
            if (strSubstring.startsWith("/Date(") && strSubstring.endsWith(")/")) {
                strSubstring = strSubstring.substring(6, strSubstring.length() - 2);
            }
            if ("0000-00-00".equals(strSubstring) || "0000-00-00T00:00:00".equalsIgnoreCase(strSubstring) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(strSubstring)) {
                return null;
            }
            int iLastIndexOf = strSubstring.lastIndexOf(124);
            if (iLastIndexOf > 20) {
                TimeZone timeZone = TimeZone.getTimeZone(strSubstring.substring(iLastIndexOf + 1));
                if (!"GMT".equals(timeZone.getID())) {
                    f2.e eVar2 = new f2.e(strSubstring.substring(0, iLastIndexOf));
                    try {
                        if (eVar2.k1(false)) {
                            ?? r53 = (T) eVar2.w0();
                            r53.setTimeZone(timeZone);
                            return type == Calendar.class ? r53 : (T) r53.getTime();
                        }
                    } finally {
                    }
                }
            }
            return (T) new java.util.Date(Long.parseLong(strSubstring));
        } finally {
        }
    }
}
