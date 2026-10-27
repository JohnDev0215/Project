package g2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byd.systemsettings.system.utils.BydDateUtils;
import h2.d1;
import h2.i0;
import h2.t0;
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
public class q extends e implements t0, h2.u, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f5050a = new q();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DateTimeFormatter f5051b = DateTimeFormatter.ofPattern(BydDateUtils.DEF_FORMAT);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final DateTimeFormatter f5052c = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DateTimeFormatter f5053d = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final DateTimeFormatter f5054e = DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DateTimeFormatter f5055f = DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分s秒");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final DateTimeFormatter f5056g = DateTimeFormatter.ofPattern("yyyy년M월d일 HH:mm:ss");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final DateTimeFormatter f5057h = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final DateTimeFormatter f5058i = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final DateTimeFormatter f5059j = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final DateTimeFormatter f5060k = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final DateTimeFormatter f5061l = DateTimeFormatter.ofPattern("yyyyMMdd");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final DateTimeFormatter f5062m = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final DateTimeFormatter f5063n = DateTimeFormatter.ofPattern("yyyy年M月d日");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final DateTimeFormatter f5064o = DateTimeFormatter.ofPattern("yyyy년M월d일");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final DateTimeFormatter f5065p = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final DateTimeFormatter f5066q = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final DateTimeFormatter f5067r = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final DateTimeFormatter f5068s = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final DateTimeFormatter f5069t = DateTimeFormatter.ofPattern(BydDateUtils.DEF_FORMAT).withZone(ZoneId.systemDefault());

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final DateTimeFormatter f5070u = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static Object g(Object obj, String str) {
        if (obj == null) {
            return null;
        }
        if (str == null) {
            str = BydDateUtils.DEF_FORMAT;
        }
        return LocalDateTime.parse(obj.toString(), DateTimeFormatter.ofPattern(str));
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        int nano;
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.Z();
            return;
        }
        if (type == null) {
            type = obj.getClass();
        }
        if (type != LocalDateTime.class) {
            d1Var.d0(obj.toString());
            return;
        }
        SerializerFeature serializerFeature = SerializerFeature.UseISO8601DateFormat;
        int mask = serializerFeature.getMask();
        LocalDateTime localDateTime = (LocalDateTime) obj;
        String strV = i0Var.v();
        if (strV == null) {
            strV = ((i8 & mask) != 0 || i0Var.A(serializerFeature) || (nano = localDateTime.getNano()) == 0) ? "yyyy-MM-dd'T'HH:mm:ss" : nano % 1000000 == 0 ? "yyyy-MM-dd'T'HH:mm:ss.SSS" : "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
        }
        k(d1Var, localDateTime, strV);
    }

    @Override // h2.u
    public void d(i0 i0Var, Object obj, h2.j jVar) throws IOException {
        k(i0Var.f5241k, (TemporalAccessor) obj, jVar.b());
    }

    @Override // g2.t
    public int e() {
        return 4;
    }

    @Override // g2.e
    public <T> T f(f2.a aVar, Type type, Object obj, String str, int i8) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 8) {
            bVar.E();
            return null;
        }
        if (bVar.q() != 4) {
            if (bVar.q() != 2) {
                throw new UnsupportedOperationException();
            }
            long jI = bVar.i();
            bVar.E();
            if ("unixtime".equals(str)) {
                jI *= 1000;
            } else if ("yyyyMMddHHmmss".equals(str)) {
                int i9 = (int) (jI / 10000000000L);
                int i10 = (int) ((jI / 100000000) % 100);
                int i11 = (int) ((jI / 1000000) % 100);
                int i12 = (int) ((jI / 10000) % 100);
                int i13 = (int) ((jI / 100) % 100);
                int i14 = (int) (jI % 100);
                if (type == LocalDateTime.class) {
                    return (T) LocalDateTime.of(i9, i10, i11, i12, i13, i14);
                }
            }
            if (type == LocalDateTime.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jI), c2.a.defaultTimeZone.toZoneId());
            }
            if (type == LocalDate.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jI), c2.a.defaultTimeZone.toZoneId()).toLocalDate();
            }
            if (type == LocalTime.class) {
                return (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(jI), c2.a.defaultTimeZone.toZoneId()).toLocalTime();
            }
            if (type == ZonedDateTime.class) {
                return (T) ZonedDateTime.ofInstant(Instant.ofEpochMilli(jI), c2.a.defaultTimeZone.toZoneId());
            }
            if (type == Instant.class) {
                return (T) Instant.ofEpochMilli(jI);
            }
            throw new UnsupportedOperationException();
        }
        String strN0 = bVar.n0();
        bVar.E();
        DateTimeFormatter dateTimeFormatterOfPattern = str != null ? BydDateUtils.DEF_FORMAT.equals(str) ? f5051b : DateTimeFormatter.ofPattern(str) : null;
        if ("".equals(strN0)) {
            return null;
        }
        if (type == LocalDateTime.class) {
            return (strN0.length() == 10 || strN0.length() == 8) ? (T) LocalDateTime.of(i(strN0, str, dateTimeFormatterOfPattern), LocalTime.MIN) : (T) h(strN0, dateTimeFormatterOfPattern);
        }
        if (type == LocalDate.class) {
            if (strN0.length() != 23) {
                return (T) i(strN0, str, dateTimeFormatterOfPattern);
            }
            LocalDateTime localDateTime = LocalDateTime.parse(strN0);
            return (T) LocalDate.of(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        }
        boolean z7 = true;
        if (type == LocalTime.class) {
            if (strN0.length() == 23) {
                LocalDateTime localDateTime2 = LocalDateTime.parse(strN0);
                return (T) LocalTime.of(localDateTime2.getHour(), localDateTime2.getMinute(), localDateTime2.getSecond(), localDateTime2.getNano());
            }
            for (int i15 = 0; i15 < strN0.length(); i15++) {
                char cCharAt = strN0.charAt(i15);
                if (cCharAt < '0' || cCharAt > '9') {
                    z7 = false;
                    break;
                }
            }
            return (!z7 || strN0.length() <= 8 || strN0.length() >= 19) ? (T) LocalTime.parse(strN0) : (T) LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(strN0)), c2.a.defaultTimeZone.toZoneId()).toLocalTime();
        }
        if (type == ZonedDateTime.class) {
            if (dateTimeFormatterOfPattern == f5051b) {
                dateTimeFormatterOfPattern = f5069t;
            }
            if (dateTimeFormatterOfPattern == null && strN0.length() <= 19) {
                f2.e eVar = new f2.e(strN0);
                TimeZone timeZoneO0 = aVar.f4861j.o0();
                eVar.b1(timeZoneO0);
                if (eVar.k1(false)) {
                    return (T) ZonedDateTime.ofInstant(eVar.w0().getTime().toInstant(), timeZoneO0.toZoneId());
                }
            }
            return (T) j(strN0, dateTimeFormatterOfPattern);
        }
        if (type == OffsetDateTime.class) {
            return (T) OffsetDateTime.parse(strN0);
        }
        if (type == OffsetTime.class) {
            return (T) OffsetTime.parse(strN0);
        }
        if (type == ZoneId.class) {
            return (T) ZoneId.of(strN0);
        }
        if (type == Period.class) {
            return (T) Period.parse(strN0);
        }
        if (type == Duration.class) {
            return (T) Duration.parse(strN0);
        }
        if (type != Instant.class) {
            return null;
        }
        for (int i16 = 0; i16 < strN0.length(); i16++) {
            char cCharAt2 = strN0.charAt(i16);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                z7 = false;
                break;
            }
        }
        return (!z7 || strN0.length() <= 8 || strN0.length() >= 19) ? (T) Instant.parse(strN0) : (T) Instant.ofEpochMilli(Long.parseLong(strN0));
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
        throw new UnsupportedOperationException("Method not decompiled: g2.q.h(java.lang.String, java.time.format.DateTimeFormatter):java.time.LocalDateTime");
    }

    public LocalDate i(String str, String str2, DateTimeFormatter dateTimeFormatter) {
        DateTimeFormatter dateTimeFormatter2;
        DateTimeFormatter dateTimeFormatter3;
        if (dateTimeFormatter == null) {
            if (str.length() == 8) {
                dateTimeFormatter = f5061l;
            }
            boolean z7 = false;
            if (str.length() == 10) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                if (cCharAt == '/' && cCharAt2 == '/') {
                    dateTimeFormatter = f5062m;
                }
                char cCharAt3 = str.charAt(0);
                char cCharAt4 = str.charAt(1);
                char cCharAt5 = str.charAt(2);
                char cCharAt6 = str.charAt(3);
                char cCharAt7 = str.charAt(5);
                if (cCharAt5 == '/' && cCharAt7 == '/') {
                    int i8 = ((cCharAt6 - '0') * 10) + (cCharAt - '0');
                    if (((cCharAt3 - '0') * 10) + (cCharAt4 - '0') > 12) {
                        dateTimeFormatter3 = f5066q;
                    } else if (i8 > 12) {
                        dateTimeFormatter3 = f5065p;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            dateTimeFormatter3 = f5065p;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            dateTimeFormatter3 = f5066q;
                        }
                    }
                    dateTimeFormatter = dateTimeFormatter3;
                } else if (cCharAt5 == '.' && cCharAt7 == '.') {
                    dateTimeFormatter = f5067r;
                } else if (cCharAt5 == '-' && cCharAt7 == '-') {
                    dateTimeFormatter = f5068s;
                }
            }
            if (str.length() >= 9) {
                char cCharAt8 = str.charAt(4);
                if (cCharAt8 == 24180) {
                    dateTimeFormatter2 = f5063n;
                } else if (cCharAt8 == 45380) {
                    dateTimeFormatter2 = f5064o;
                }
                dateTimeFormatter = dateTimeFormatter2;
            }
            int i9 = 0;
            while (true) {
                if (i9 >= str.length()) {
                    z7 = true;
                    break;
                }
                char cCharAt9 = str.charAt(i9);
                if (cCharAt9 < '0' || cCharAt9 > '9') {
                    break;
                }
                i9++;
            }
            if (z7 && str.length() > 8 && str.length() < 19) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(str)), c2.a.defaultTimeZone.toZoneId()).toLocalDate();
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
        throw new UnsupportedOperationException("Method not decompiled: g2.q.j(java.lang.String, java.time.format.DateTimeFormatter):java.time.ZonedDateTime");
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.time.ZonedDateTime] */
    public final void k(d1 d1Var, TemporalAccessor temporalAccessor, String str) {
        if ("unixtime".equals(str)) {
            if (temporalAccessor instanceof ChronoZonedDateTime) {
                d1Var.V((int) ((ChronoZonedDateTime) temporalAccessor).toEpochSecond());
                return;
            } else if (temporalAccessor instanceof LocalDateTime) {
                d1Var.V((int) ((LocalDateTime) temporalAccessor).atZone(c2.a.defaultTimeZone.toZoneId()).toEpochSecond());
                return;
            }
        }
        if ("millis".equals(str)) {
            Instant instant = null;
            if (temporalAccessor instanceof ChronoZonedDateTime) {
                instant = ((ChronoZonedDateTime) temporalAccessor).toInstant();
            } else if (temporalAccessor instanceof LocalDateTime) {
                instant = ((LocalDateTime) temporalAccessor).atZone(c2.a.defaultTimeZone.toZoneId()).toInstant();
            }
            if (instant != null) {
                d1Var.Y(instant.toEpochMilli());
                return;
            }
        }
        d1Var.d0((str == "yyyy-MM-dd'T'HH:mm:ss" ? f5070u : DateTimeFormatter.ofPattern(str)).format(temporalAccessor));
    }
}
