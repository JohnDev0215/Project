package b.a.a.j.j;

import b.a.a.k.d1;
import b.a.a.k.i0;
import b.a.a.k.t0;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: Jdk8DateCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends e implements t0, b.a.a.k.u, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f292a = new q();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DateTimeFormatter f293b = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final DateTimeFormatter f294c = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DateTimeFormatter f295d = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final DateTimeFormatter f296e = DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DateTimeFormatter f297f = DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分s秒");
    public static final DateTimeFormatter g = DateTimeFormatter.ofPattern("yyyy년M월d일 HH:mm:ss");
    public static final DateTimeFormatter h = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    public static final DateTimeFormatter i = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static final DateTimeFormatter j = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static final DateTimeFormatter k = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final DateTimeFormatter l = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter m = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter n = DateTimeFormatter.ofPattern("yyyy年M月d日");
    public static final DateTimeFormatter o = DateTimeFormatter.ofPattern("yyyy년M월d일");
    public static final DateTimeFormatter p = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter q = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter r = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter s = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter t = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter u = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static Object g(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        if (str == null) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return LocalDateTime.parse(obj.toString(), DateTimeFormatter.ofPattern(str));
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i2) throws IOException {
        int nano;
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.J();
            return;
        }
        if (type == null) {
            type = obj.getClass();
        }
        if (type != LocalDateTime.class) {
            d1Var.N(obj.toString());
            return;
        }
        SerializerFeature serializerFeature = SerializerFeature.UseISO8601DateFormat;
        int mask = serializerFeature.getMask();
        LocalDateTime localDateTime = (LocalDateTime) obj;
        String strV = i0Var.v();
        if (strV == null) {
            strV = ((i2 & mask) != 0 || i0Var.A(serializerFeature) || (nano = localDateTime.getNano()) == 0) ? "yyyy-MM-dd'T'HH:mm:ss" : nano % 1000000 == 0 ? "yyyy-MM-dd'T'HH:mm:ss.SSS" : "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
        }
        k(d1Var, localDateTime, strV);
    }

    @Override // b.a.a.k.u
    public void d(i0 i0Var, Object obj, b.a.a.k.j jVar) throws IOException {
        k(i0Var.k, (TemporalAccessor) obj, jVar.b());
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }

    @Override // b.a.a.j.j.e
    public <T> T f(b.a.a.j.a aVar, Type type, Object obj, String str, int i2) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 8) {
            bVar.u();
            return null;
        }
        if (bVar.j() != 4) {
            if (bVar.j() != 2) {
                throw new UnsupportedOperationException();
            }
            long jE = bVar.e();
            bVar.u();
            if ("unixtime".equals(str)) {
                jE *= 1000;
            } else if ("yyyyMMddHHmmss".equals(str)) {
                int i3 = (int) (jE / 10000000000L);
                int i4 = (int) ((jE / 100000000) % 100);
                int i5 = (int) ((jE / 1000000) % 100);
                int i6 = (int) ((jE / 10000) % 100);
                int i7 = (int) ((jE / 100) % 100);
                int i8 = (int) (jE % 100);
                if (type == LocalDateTime.class) {
                    return (T) LocalDateTime.of(i3, i4, i5, i6, i7, i8);
                }
            }
            if (type == LocalDateTime.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jE), b.a.a.a.defaultTimeZone.toZoneId());
            }
            if (type == LocalDate.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jE), b.a.a.a.defaultTimeZone.toZoneId()).toLocalDate();
            }
            if (type == LocalTime.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jE), b.a.a.a.defaultTimeZone.toZoneId()).toLocalTime();
            }
            if (type == ZonedDateTime.class) {
                return (T) ZonedDateTime.ofInstant(Instant.ofEpochMilli(jE), b.a.a.a.defaultTimeZone.toZoneId());
            }
            if (type == Instant.class) {
                return (T) Instant.ofEpochMilli(jE);
            }
            throw new UnsupportedOperationException();
        }
        String strV = bVar.V();
        bVar.u();
        DateTimeFormatter dateTimeFormatterOfPattern = str != null ? "yyyy-MM-dd HH:mm:ss".equals(str) ? f293b : DateTimeFormatter.ofPattern(str) : null;
        if ("".equals(strV)) {
            return null;
        }
        if (type == LocalDateTime.class) {
            return (strV.length() == 10 || strV.length() == 8) ? (T) LocalDateTime.of(i(strV, str, dateTimeFormatterOfPattern), LocalTime.MIN) : (T) h(strV, dateTimeFormatterOfPattern);
        }
        if (type == LocalDate.class) {
            if (strV.length() != 23) {
                return (T) i(strV, str, dateTimeFormatterOfPattern);
            }
            LocalDateTime localDateTime = LocalDateTime.parse(strV);
            return (T) LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        }
        boolean z = true;
        if (type == LocalTime.class) {
            if (strV.length() == 23) {
                LocalDateTime localDateTime2 = LocalDateTime.parse(strV);
                return (T) LocalTime.of(localDateTime2.getHour(), localDateTime2.getMinute(), localDateTime2.getSecond(), localDateTime2.getNano());
            }
            for (int i9 = 0; i9 < strV.length(); i9++) {
                char cCharAt = strV.charAt(i9);
                if (cCharAt < '0' || cCharAt > '9') {
                    z = false;
                    break;
                }
            }
            return (!z || strV.length() <= 8 || strV.length() >= 19) ? (T) LocalTime.parse(strV) : (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(strV)), b.a.a.a.defaultTimeZone.toZoneId()).toLocalTime();
        }
        if (type == ZonedDateTime.class) {
            if (dateTimeFormatterOfPattern == f293b) {
                dateTimeFormatterOfPattern = t;
            }
            if (dateTimeFormatterOfPattern == null && strV.length() <= 19) {
                b.a.a.j.e eVar = new b.a.a.j.e(strV);
                TimeZone timeZoneW = aVar.g.W();
                eVar.I0(timeZoneW);
                if (eVar.R0(false)) {
                    return (T) ZonedDateTime.ofInstant(eVar.d0().getTime().toInstant(), timeZoneW.toZoneId());
                }
            }
            return (T) j(strV, dateTimeFormatterOfPattern);
        }
        if (type == OffsetDateTime.class) {
            return (T) OffsetDateTime.parse(strV);
        }
        if (type == OffsetTime.class) {
            return (T) OffsetTime.parse(strV);
        }
        if (type == ZoneId.class) {
            return (T) ZoneId.of(strV);
        }
        if (type == Period.class) {
            return (T) Period.parse(strV);
        }
        if (type == Duration.class) {
            return (T) Duration.parse(strV);
        }
        if (type != Instant.class) {
            return null;
        }
        for (int i10 = 0; i10 < strV.length(); i10++) {
            char cCharAt2 = strV.charAt(i10);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                z = false;
                break;
            }
        }
        return (!z || strV.length() <= 8 || strV.length() >= 19) ? (T) Instant.parse(strV) : (T) Instant.ofEpochMilli(Long.parseLong(strV));
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.time.LocalDateTime h(java.lang.String r17, java.time.format.DateTimeFormatter r18) {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.j.q.h(java.lang.String, java.time.format.DateTimeFormatter):java.time.LocalDateTime");
    }

    public LocalDate i(String str, String str2, DateTimeFormatter dateTimeFormatter) {
        DateTimeFormatter dateTimeFormatter2;
        DateTimeFormatter dateTimeFormatter3;
        if (dateTimeFormatter == null) {
            if (str.length() == 8) {
                dateTimeFormatter = l;
            }
            boolean z = false;
            if (str.length() == 10) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                if (cCharAt == '/' && cCharAt2 == '/') {
                    dateTimeFormatter = m;
                }
                char cCharAt3 = str.charAt(0);
                char cCharAt4 = str.charAt(1);
                char cCharAt5 = str.charAt(2);
                char cCharAt6 = str.charAt(3);
                char cCharAt7 = str.charAt(5);
                if (cCharAt5 == '/' && cCharAt7 == '/') {
                    int i2 = ((cCharAt6 - '0') * 10) + (cCharAt - '0');
                    if (((cCharAt3 - '0') * 10) + (cCharAt4 - '0') > 12) {
                        dateTimeFormatter3 = q;
                    } else if (i2 > 12) {
                        dateTimeFormatter3 = p;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            dateTimeFormatter3 = p;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            dateTimeFormatter3 = q;
                        }
                    }
                    dateTimeFormatter = dateTimeFormatter3;
                } else if (cCharAt5 == '.' && cCharAt7 == '.') {
                    dateTimeFormatter = r;
                } else if (cCharAt5 == '-' && cCharAt7 == '-') {
                    dateTimeFormatter = s;
                }
            }
            if (str.length() >= 9) {
                char cCharAt8 = str.charAt(4);
                if (cCharAt8 == 24180) {
                    dateTimeFormatter2 = n;
                } else if (cCharAt8 == 45380) {
                    dateTimeFormatter2 = o;
                }
                dateTimeFormatter = dateTimeFormatter2;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= str.length()) {
                    z = true;
                    break;
                }
                char cCharAt9 = str.charAt(i3);
                if (cCharAt9 < '0' || cCharAt9 > '9') {
                    break;
                }
                i3++;
            }
            if (z && str.length() > 8 && str.length() < 19) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(str)), b.a.a.a.defaultTimeZone.toZoneId()).toLocalDate();
            }
        }
        return dateTimeFormatter == null ? LocalDate.parse(str) : LocalDate.parse(str, dateTimeFormatter);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.time.ZonedDateTime j(java.lang.String r16, java.time.format.DateTimeFormatter r17) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.j.q.j(java.lang.String, java.time.format.DateTimeFormatter):java.time.ZonedDateTime");
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.time.ZonedDateTime] */
    public final void k(d1 d1Var, TemporalAccessor temporalAccessor, String str) {
        if ("unixtime".equals(str)) {
            if (temporalAccessor instanceof ChronoZonedDateTime) {
                d1Var.F((int) ((ChronoZonedDateTime) temporalAccessor).toEpochSecond());
                return;
            } else if (temporalAccessor instanceof LocalDateTime) {
                d1Var.F((int) ((LocalDateTime) temporalAccessor).atZone(b.a.a.a.defaultTimeZone.toZoneId()).toEpochSecond());
                return;
            }
        }
        if ("millis".equals(str)) {
            Instant instant = null;
            if (temporalAccessor instanceof ChronoZonedDateTime) {
                instant = ((ChronoZonedDateTime) temporalAccessor).toInstant();
            } else if (temporalAccessor instanceof LocalDateTime) {
                instant = ((LocalDateTime) temporalAccessor).atZone(b.a.a.a.defaultTimeZone.toZoneId()).toInstant();
            }
            if (instant != null) {
                d1Var.H(instant.toEpochMilli());
                return;
            }
        }
        d1Var.N((str == "yyyy-MM-dd'T'HH:mm:ss" ? u : DateTimeFormatter.ofPattern(str)).format(temporalAccessor));
    }
}
