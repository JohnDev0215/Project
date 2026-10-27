package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byd.systemsettings.system.utils.BydDateUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/* JADX INFO: compiled from: JodaCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class k0 implements t0, u, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k0 f5262a = new k0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DateTimeFormatter f5263b = DateTimeFormat.forPattern(BydDateUtils.DEF_FORMAT);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final DateTimeFormatter f5264c = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DateTimeFormatter f5265d = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final DateTimeFormatter f5266e = DateTimeFormat.forPattern("yyyy年M月d日 HH:mm:ss");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DateTimeFormatter f5267f = DateTimeFormat.forPattern("yyyy年M月d日 H时m分s秒");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final DateTimeFormatter f5268g = DateTimeFormat.forPattern("yyyy년M월d일 HH:mm:ss");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final DateTimeFormatter f5269h = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final DateTimeFormatter f5270i = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final DateTimeFormatter f5271j = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final DateTimeFormatter f5272k = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final DateTimeFormatter f5273l = DateTimeFormat.forPattern("yyyyMMdd");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final DateTimeFormatter f5274m = DateTimeFormat.forPattern("yyyy/MM/dd");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final DateTimeFormatter f5275n = DateTimeFormat.forPattern("yyyy年M月d日");

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final DateTimeFormatter f5276o = DateTimeFormat.forPattern("yyyy년M월d일");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final DateTimeFormatter f5277p = DateTimeFormat.forPattern("MM/dd/yyyy");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final DateTimeFormatter f5278q = DateTimeFormat.forPattern("dd/MM/yyyy");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final DateTimeFormatter f5279r = DateTimeFormat.forPattern("dd.MM.yyyy");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final DateTimeFormatter f5280s = DateTimeFormat.forPattern("dd-MM-yyyy");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final DateTimeFormatter f5281t = DateTimeFormat.forPattern(BydDateUtils.DEF_FORMAT).withZone(DateTimeZone.getDefault());

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final DateTimeFormatter f5282u = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
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
            strV = ((i8 & mask) != 0 || i0Var.A(serializerFeature)) ? "yyyy-MM-dd'T'HH:mm:ss" : i0Var.A(SerializerFeature.WriteDateUseDateFormat) ? c2.a.DEFFAULT_DATE_FORMAT : localDateTime.getMillisOfSecond() == 0 ? "yyyy-MM-dd'T'HH:mm:ss.SSS" : "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
        }
        if (strV != null) {
            j(d1Var, localDateTime, strV);
        } else {
            d1Var.Y(localDateTime.toDateTime(DateTimeZone.forTimeZone(c2.a.defaultTimeZone)).toInstant().getMillis());
        }
    }

    @Override // h2.u
    public void d(i0 i0Var, Object obj, j jVar) throws IOException {
        j(i0Var.f5241k, (ReadablePartial) obj, jVar.b());
    }

    @Override // g2.t
    public int e() {
        return 4;
    }

    /* JADX WARN: Type inference failed for: r8v3, types: [T, org.joda.time.LocalDateTime] */
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
            TimeZone timeZone = c2.a.defaultTimeZone;
            if (timeZone == null) {
                timeZone = TimeZone.getDefault();
            }
            if (type == DateTime.class) {
                return (T) new DateTime(jI, DateTimeZone.forTimeZone(timeZone));
            }
            ?? r8 = (T) new LocalDateTime(jI, DateTimeZone.forTimeZone(timeZone));
            if (type == LocalDateTime.class) {
                return r8;
            }
            if (type == LocalDate.class) {
                return (T) r8.toLocalDate();
            }
            if (type == LocalTime.class) {
                return (T) r8.toLocalTime();
            }
            if (type == Instant.class) {
                return (T) new Instant(jI);
            }
            throw new UnsupportedOperationException();
        }
        String strN0 = bVar.n0();
        bVar.E();
        DateTimeFormatter dateTimeFormatterForPattern = str != null ? BydDateUtils.DEF_FORMAT.equals(str) ? f5263b : DateTimeFormat.forPattern(str) : null;
        if ("".equals(strN0)) {
            return null;
        }
        if (type == LocalDateTime.class) {
            return (strN0.length() == 10 || strN0.length() == 8) ? (T) h(strN0, str, dateTimeFormatterForPattern).toLocalDateTime(LocalTime.MIDNIGHT) : (T) g(strN0, dateTimeFormatterForPattern);
        }
        if (type == LocalDate.class) {
            return strN0.length() == 23 ? (T) LocalDateTime.parse(strN0).toLocalDate() : (T) h(strN0, str, dateTimeFormatterForPattern);
        }
        if (type == LocalTime.class) {
            return strN0.length() == 23 ? (T) LocalDateTime.parse(strN0).toLocalTime() : (T) LocalTime.parse(strN0);
        }
        if (type == DateTime.class) {
            if (dateTimeFormatterForPattern == f5263b) {
                dateTimeFormatterForPattern = f5281t;
            }
            return (T) i(strN0, dateTimeFormatterForPattern);
        }
        if (type == DateTimeZone.class) {
            return (T) DateTimeZone.forID(strN0);
        }
        if (type == Period.class) {
            return (T) Period.parse(strN0);
        }
        if (type == Duration.class) {
            return (T) Duration.parse(strN0);
        }
        if (type != Instant.class) {
            if (type == DateTimeFormatter.class) {
                return (T) DateTimeFormat.forPattern(strN0);
            }
            return null;
        }
        boolean z7 = false;
        int i9 = 0;
        while (true) {
            if (i9 >= strN0.length()) {
                z7 = true;
                break;
            }
            char cCharAt = strN0.charAt(i9);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i9++;
        }
        return (!z7 || strN0.length() <= 8 || strN0.length() >= 19) ? (T) Instant.parse(strN0) : (T) new Instant(Long.parseLong(strN0));
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0129 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.joda.time.LocalDateTime g(java.lang.String r17, org.joda.time.format.DateTimeFormatter r18) {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.k0.g(java.lang.String, org.joda.time.format.DateTimeFormatter):org.joda.time.LocalDateTime");
    }

    public LocalDate h(String str, String str2, DateTimeFormatter dateTimeFormatter) {
        DateTimeFormatter dateTimeFormatter2;
        DateTimeFormatter dateTimeFormatter3;
        if (dateTimeFormatter == null) {
            if (str.length() == 8) {
                dateTimeFormatter = f5273l;
            }
            boolean z7 = false;
            if (str.length() == 10) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                if (cCharAt == '/' && cCharAt2 == '/') {
                    dateTimeFormatter = f5274m;
                }
                char cCharAt3 = str.charAt(0);
                char cCharAt4 = str.charAt(1);
                char cCharAt5 = str.charAt(2);
                char cCharAt6 = str.charAt(3);
                char cCharAt7 = str.charAt(5);
                if (cCharAt5 == '/' && cCharAt7 == '/') {
                    int i8 = ((cCharAt6 - '0') * 10) + (cCharAt - '0');
                    if (((cCharAt3 - '0') * 10) + (cCharAt4 - '0') > 12) {
                        dateTimeFormatter3 = f5278q;
                    } else if (i8 > 12) {
                        dateTimeFormatter3 = f5277p;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            dateTimeFormatter3 = f5277p;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            dateTimeFormatter3 = f5278q;
                        }
                    }
                    dateTimeFormatter = dateTimeFormatter3;
                } else if (cCharAt5 == '.' && cCharAt7 == '.') {
                    dateTimeFormatter = f5279r;
                } else if (cCharAt5 == '-' && cCharAt7 == '-') {
                    dateTimeFormatter = f5280s;
                }
            }
            if (str.length() >= 9) {
                char cCharAt8 = str.charAt(4);
                if (cCharAt8 == 24180) {
                    dateTimeFormatter2 = f5275n;
                } else if (cCharAt8 == 45380) {
                    dateTimeFormatter2 = f5276o;
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
                return new LocalDateTime(Long.parseLong(str), DateTimeZone.forTimeZone(c2.a.defaultTimeZone)).toLocalDate();
            }
        }
        return dateTimeFormatter == null ? LocalDate.parse(str) : LocalDate.parse(str, dateTimeFormatter);
    }

    public DateTime i(String str, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            if (str.length() == 19) {
                char cCharAt = str.charAt(4);
                char cCharAt2 = str.charAt(7);
                char cCharAt3 = str.charAt(10);
                char cCharAt4 = str.charAt(13);
                char cCharAt5 = str.charAt(16);
                if (cCharAt4 == ':' && cCharAt5 == ':') {
                    if (cCharAt == '-' && cCharAt2 == '-') {
                        if (cCharAt3 == 'T') {
                            dateTimeFormatter = f5282u;
                        } else if (cCharAt3 == ' ') {
                            dateTimeFormatter = f5263b;
                        }
                    } else if (cCharAt == '/' && cCharAt2 == '/') {
                        dateTimeFormatter = f5265d;
                    } else {
                        char cCharAt6 = str.charAt(0);
                        char cCharAt7 = str.charAt(1);
                        char cCharAt8 = str.charAt(2);
                        char cCharAt9 = str.charAt(3);
                        char cCharAt10 = str.charAt(5);
                        if (cCharAt8 == '/' && cCharAt10 == '/') {
                            int i8 = ((cCharAt9 - '0') * 10) + (cCharAt - '0');
                            if (((cCharAt6 - '0') * 10) + (cCharAt7 - '0') > 12) {
                                dateTimeFormatter = f5270i;
                            } else if (i8 > 12) {
                                dateTimeFormatter = f5269h;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    dateTimeFormatter = f5269h;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    dateTimeFormatter = f5270i;
                                }
                            }
                        } else if (cCharAt8 == '.' && cCharAt10 == '.') {
                            dateTimeFormatter = f5271j;
                        } else if (cCharAt8 == '-' && cCharAt10 == '-') {
                            dateTimeFormatter = f5272k;
                        }
                    }
                }
            }
            if (str.length() >= 17) {
                char cCharAt11 = str.charAt(4);
                if (cCharAt11 == 24180) {
                    dateTimeFormatter = str.charAt(str.length() - 1) == 31186 ? f5267f : f5266e;
                } else if (cCharAt11 == 45380) {
                    dateTimeFormatter = f5268g;
                }
            }
        }
        return dateTimeFormatter == null ? DateTime.parse(str) : DateTime.parse(str, dateTimeFormatter);
    }

    public final void j(d1 d1Var, ReadablePartial readablePartial, String str) {
        d1Var.d0((str.equals("yyyy-MM-dd'T'HH:mm:ss") ? f5282u : DateTimeFormat.forPattern(str)).print(readablePartial));
    }
}
