package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
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
public class k0 implements t0, u, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k0 f376a = new k0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DateTimeFormatter f377b = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final DateTimeFormatter f378c = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DateTimeFormatter f379d = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final DateTimeFormatter f380e = DateTimeFormat.forPattern("yyyy年M月d日 HH:mm:ss");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DateTimeFormatter f381f = DateTimeFormat.forPattern("yyyy年M月d日 H时m分s秒");
    public static final DateTimeFormatter g = DateTimeFormat.forPattern("yyyy년M월d일 HH:mm:ss");
    public static final DateTimeFormatter h = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
    public static final DateTimeFormatter i = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    public static final DateTimeFormatter j = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
    public static final DateTimeFormatter k = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
    public static final DateTimeFormatter l = DateTimeFormat.forPattern("yyyyMMdd");
    public static final DateTimeFormatter m = DateTimeFormat.forPattern("yyyy/MM/dd");
    public static final DateTimeFormatter n = DateTimeFormat.forPattern("yyyy年M月d日");
    public static final DateTimeFormatter o = DateTimeFormat.forPattern("yyyy년M월d일");
    public static final DateTimeFormatter p = DateTimeFormat.forPattern("MM/dd/yyyy");
    public static final DateTimeFormatter q = DateTimeFormat.forPattern("dd/MM/yyyy");
    public static final DateTimeFormatter r = DateTimeFormat.forPattern("dd.MM.yyyy");
    public static final DateTimeFormatter s = DateTimeFormat.forPattern("dd-MM-yyyy");
    public static final DateTimeFormatter t = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withZone(DateTimeZone.getDefault());
    public static final DateTimeFormatter u = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        return (T) f(aVar, type, obj, null, 0);
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i2) throws IOException {
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
            strV = ((i2 & mask) != 0 || i0Var.A(serializerFeature)) ? "yyyy-MM-dd'T'HH:mm:ss" : i0Var.A(SerializerFeature.WriteDateUseDateFormat) ? b.a.a.a.DEFFAULT_DATE_FORMAT : localDateTime.getMillisOfSecond() == 0 ? "yyyy-MM-dd'T'HH:mm:ss.SSS" : "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS";
        }
        if (strV != null) {
            j(d1Var, localDateTime, strV);
        } else {
            d1Var.H(localDateTime.toDateTime(DateTimeZone.forTimeZone(b.a.a.a.defaultTimeZone)).toInstant().getMillis());
        }
    }

    @Override // b.a.a.k.u
    public void d(i0 i0Var, Object obj, j jVar) throws IOException {
        j(i0Var.k, (ReadablePartial) obj, jVar.b());
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }

    /* JADX WARN: Type inference failed for: r8v3, types: [T, org.joda.time.LocalDateTime] */
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
            TimeZone timeZone = b.a.a.a.defaultTimeZone;
            if (timeZone == null) {
                timeZone = TimeZone.getDefault();
            }
            if (type == DateTime.class) {
                return (T) new DateTime(jE, DateTimeZone.forTimeZone(timeZone));
            }
            ?? r8 = (T) new LocalDateTime(jE, DateTimeZone.forTimeZone(timeZone));
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
                return (T) new Instant(jE);
            }
            throw new UnsupportedOperationException();
        }
        String strV = bVar.V();
        bVar.u();
        DateTimeFormatter dateTimeFormatterForPattern = str != null ? "yyyy-MM-dd HH:mm:ss".equals(str) ? f377b : DateTimeFormat.forPattern(str) : null;
        if ("".equals(strV)) {
            return null;
        }
        if (type == LocalDateTime.class) {
            return (strV.length() == 10 || strV.length() == 8) ? (T) h(strV, str, dateTimeFormatterForPattern).toLocalDateTime(LocalTime.MIDNIGHT) : (T) g(strV, dateTimeFormatterForPattern);
        }
        if (type == LocalDate.class) {
            return strV.length() == 23 ? (T) LocalDateTime.parse(strV).toLocalDate() : (T) h(strV, str, dateTimeFormatterForPattern);
        }
        if (type == LocalTime.class) {
            return strV.length() == 23 ? (T) LocalDateTime.parse(strV).toLocalTime() : (T) LocalTime.parse(strV);
        }
        if (type == DateTime.class) {
            if (dateTimeFormatterForPattern == f377b) {
                dateTimeFormatterForPattern = t;
            }
            return (T) i(strV, dateTimeFormatterForPattern);
        }
        if (type == DateTimeZone.class) {
            return (T) DateTimeZone.forID(strV);
        }
        if (type == Period.class) {
            return (T) Period.parse(strV);
        }
        if (type == Duration.class) {
            return (T) Duration.parse(strV);
        }
        if (type != Instant.class) {
            if (type == DateTimeFormatter.class) {
                return (T) DateTimeFormat.forPattern(strV);
            }
            return null;
        }
        boolean z = false;
        int i3 = 0;
        while (true) {
            if (i3 >= strV.length()) {
                z = true;
                break;
            }
            char cCharAt = strV.charAt(i3);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            i3++;
        }
        return (!z || strV.length() <= 8 || strV.length() >= 19) ? (T) Instant.parse(strV) : (T) new Instant(Long.parseLong(strV));
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
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.k0.g(java.lang.String, org.joda.time.format.DateTimeFormatter):org.joda.time.LocalDateTime");
    }

    public LocalDate h(String str, String str2, DateTimeFormatter dateTimeFormatter) {
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
                return new LocalDateTime(Long.parseLong(str), DateTimeZone.forTimeZone(b.a.a.a.defaultTimeZone)).toLocalDate();
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
                            dateTimeFormatter = u;
                        } else if (cCharAt3 == ' ') {
                            dateTimeFormatter = f377b;
                        }
                    } else if (cCharAt == '/' && cCharAt2 == '/') {
                        dateTimeFormatter = f379d;
                    } else {
                        char cCharAt6 = str.charAt(0);
                        char cCharAt7 = str.charAt(1);
                        char cCharAt8 = str.charAt(2);
                        char cCharAt9 = str.charAt(3);
                        char cCharAt10 = str.charAt(5);
                        if (cCharAt8 == '/' && cCharAt10 == '/') {
                            int i2 = ((cCharAt9 - '0') * 10) + (cCharAt - '0');
                            if (((cCharAt6 - '0') * 10) + (cCharAt7 - '0') > 12) {
                                dateTimeFormatter = i;
                            } else if (i2 > 12) {
                                dateTimeFormatter = h;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    dateTimeFormatter = h;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    dateTimeFormatter = i;
                                }
                            }
                        } else if (cCharAt8 == '.' && cCharAt10 == '.') {
                            dateTimeFormatter = j;
                        } else if (cCharAt8 == '-' && cCharAt10 == '-') {
                            dateTimeFormatter = k;
                        }
                    }
                }
            }
            if (str.length() >= 17) {
                char cCharAt11 = str.charAt(4);
                if (cCharAt11 == 24180) {
                    dateTimeFormatter = str.charAt(str.length() - 1) == 31186 ? f381f : f380e;
                } else if (cCharAt11 == 45380) {
                    dateTimeFormatter = g;
                }
            }
        }
        return dateTimeFormatter == null ? DateTime.parse(str) : DateTime.parse(str, dateTimeFormatter);
    }

    public final void j(d1 d1Var, ReadablePartial readablePartial, String str) {
        d1Var.N((str.equals("yyyy-MM-dd'T'HH:mm:ss") ? u : DateTimeFormat.forPattern(str)).print(readablePartial));
    }
}
