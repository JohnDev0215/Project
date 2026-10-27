package b.a.a.k;

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
public class w extends b.a.a.j.j.b implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w f406a = new w();

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        char[] charArray;
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
            return;
        }
        Class<?> cls = obj.getClass();
        if (cls == Date.class) {
            long time = ((Date) obj).getTime();
            if ((time + ((long) i0Var.s.getOffset(time))) % 86400000 == 0 && !SerializerFeature.isEnabled(d1Var.i, i, SerializerFeature.WriteClassName)) {
                d1Var.N(obj.toString());
                return;
            }
        }
        if (cls == Time.class) {
            long time2 = ((Time) obj).getTime();
            if ("unixtime".equals(i0Var.v())) {
                d1Var.H(time2 / 1000);
                return;
            } else if ("millis".equals(i0Var.v())) {
                d1Var.H(time2);
                return;
            } else if (time2 < 86400000) {
                d1Var.N(obj.toString());
                return;
            }
        }
        int nanos = cls == Timestamp.class ? ((Timestamp) obj).getNanos() : 0;
        java.util.Date dateO = obj instanceof java.util.Date ? (java.util.Date) obj : b.a.a.n.l.o(obj);
        if ("unixtime".equals(i0Var.v())) {
            d1Var.H(dateO.getTime() / 1000);
            return;
        }
        if ("millis".equals(i0Var.v())) {
            d1Var.H(dateO.getTime());
            return;
        }
        if (d1Var.l(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormatU = i0Var.u();
            if (dateFormatU == null) {
                String strW = i0Var.w();
                if (strW == null) {
                    strW = b.a.a.a.DEFFAULT_DATE_FORMAT;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strW, i0Var.t);
                simpleDateFormat.setTimeZone(i0Var.s);
                dateFormatU = simpleDateFormat;
            }
            d1Var.N(dateFormatU.format(dateO));
            return;
        }
        if (d1Var.l(SerializerFeature.WriteClassName) && cls != type) {
            if (cls == java.util.Date.class) {
                d1Var.write("new Date(");
                d1Var.H(((java.util.Date) obj).getTime());
                d1Var.write(41);
                return;
            } else {
                d1Var.write(123);
                d1Var.u(b.a.a.a.DEFAULT_TYPE_KEY);
                i0Var.I(cls.getName());
                d1Var.y(',', "val", ((java.util.Date) obj).getTime());
                d1Var.write(125);
                return;
            }
        }
        long time3 = dateO.getTime();
        if (!d1Var.l(SerializerFeature.UseISO8601DateFormat)) {
            d1Var.H(time3);
            return;
        }
        int i2 = d1Var.l(SerializerFeature.UseSingleQuotes) ? 39 : 34;
        d1Var.write(i2);
        Calendar calendar = Calendar.getInstance(i0Var.s, i0Var.t);
        calendar.setTimeInMillis(time3);
        int i3 = calendar.get(1);
        int i4 = calendar.get(2) + 1;
        int i5 = calendar.get(5);
        int i6 = calendar.get(11);
        int i7 = calendar.get(12);
        int i8 = calendar.get(13);
        int i9 = calendar.get(14);
        if (nanos > 0) {
            charArray = "0000-00-00 00:00:00.000000000".toCharArray();
            b.a.a.n.e.i(nanos, 29, charArray);
            b.a.a.n.e.i(i8, 19, charArray);
            b.a.a.n.e.i(i7, 16, charArray);
            b.a.a.n.e.i(i6, 13, charArray);
            b.a.a.n.e.i(i5, 10, charArray);
            b.a.a.n.e.i(i4, 7, charArray);
            b.a.a.n.e.i(i3, 4, charArray);
        } else if (i9 != 0) {
            char[] charArray2 = "0000-00-00T00:00:00.000".toCharArray();
            b.a.a.n.e.i(i9, 23, charArray2);
            b.a.a.n.e.i(i8, 19, charArray2);
            b.a.a.n.e.i(i7, 16, charArray2);
            b.a.a.n.e.i(i6, 13, charArray2);
            b.a.a.n.e.i(i5, 10, charArray2);
            b.a.a.n.e.i(i4, 7, charArray2);
            b.a.a.n.e.i(i3, 4, charArray2);
            charArray = charArray2;
        } else if (i8 == 0 && i7 == 0 && i6 == 0) {
            char[] charArray3 = "0000-00-00".toCharArray();
            b.a.a.n.e.i(i5, 10, charArray3);
            b.a.a.n.e.i(i4, 7, charArray3);
            b.a.a.n.e.i(i3, 4, charArray3);
            charArray = charArray3;
        } else {
            charArray = "0000-00-00T00:00:00".toCharArray();
            b.a.a.n.e.i(i8, 19, charArray);
            b.a.a.n.e.i(i7, 16, charArray);
            b.a.a.n.e.i(i6, 13, charArray);
            b.a.a.n.e.i(i5, 10, charArray);
            b.a.a.n.e.i(i4, 7, charArray);
            b.a.a.n.e.i(i3, 4, charArray);
        }
        if (nanos > 0) {
            int i10 = 0;
            while (i10 < 9 && charArray[(charArray.length - i10) - 1] == '0') {
                i10++;
            }
            d1Var.write(charArray, 0, charArray.length - i10);
            d1Var.write(i2);
            return;
        }
        d1Var.write(charArray);
        float offset = calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000.0f;
        int i11 = (int) offset;
        if (i11 == 0.0d) {
            d1Var.write(90);
        } else {
            if (i11 > 9) {
                d1Var.write(43);
                d1Var.F(i11);
            } else if (i11 > 0) {
                d1Var.write(43);
                d1Var.write(48);
                d1Var.F(i11);
            } else if (i11 < -9) {
                d1Var.write(45);
                d1Var.F(-i11);
            } else if (i11 < 0) {
                d1Var.write(45);
                d1Var.write(48);
                d1Var.F(-i11);
            }
            d1Var.write(58);
            d1Var.append(String.format("%02d", Integer.valueOf((int) (Math.abs(offset - i11) * 60.0f))));
        }
        d1Var.write(i2);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v17, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r5v24, types: [T, java.util.Calendar] */
    @Override // b.a.a.j.j.b
    public <T> T g(b.a.a.j.a aVar, Type type, Object obj, Object obj2) {
        if (obj2 == 0) {
            return null;
        }
        if (obj2 instanceof java.util.Date) {
            return obj2;
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new java.util.Date(b.a.a.n.l.D0((BigDecimal) obj2));
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
        b.a.a.j.e eVar = new b.a.a.j.e(strSubstring);
        try {
            if (eVar.R0(false)) {
                ?? r5 = (T) eVar.d0();
                return type == Calendar.class ? r5 : (T) r5.getTime();
            }
            eVar.close();
            String strK = aVar.k();
            if (strSubstring.length() == strK.length() || (strSubstring.length() == 22 && strK.equals("yyyyMMddHHmmssSSSZ")) || (strSubstring.indexOf(84) != -1 && strK.contains("'T'") && strSubstring.length() + 2 == strK.length())) {
                try {
                    return (T) aVar.l().parse(strSubstring);
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
                    b.a.a.j.e eVar2 = new b.a.a.j.e(strSubstring.substring(0, iLastIndexOf));
                    try {
                        if (eVar2.R0(false)) {
                            ?? r52 = (T) eVar2.d0();
                            r52.setTimeZone(timeZone);
                            return type == Calendar.class ? r52 : (T) r52.getTime();
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
