package i6;

import g5.r;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: dates.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final a f7046a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f7047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final DateFormat[] f7048c;

    /* JADX INFO: compiled from: dates.kt */
    public static final class a extends ThreadLocal<DateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(d6.b.f5965f);
            return simpleDateFormat;
        }
    }

    static {
        String[] strArr = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        f7047b = strArr;
        f7048c = new DateFormat[strArr.length];
    }

    public static final Date a(String toHttpDateOrNull) {
        l.e(toHttpDateOrNull, "$this$toHttpDateOrNull");
        if (toHttpDateOrNull.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = f7046a.get().parse(toHttpDateOrNull, parsePosition);
        if (parsePosition.getIndex() == toHttpDateOrNull.length()) {
            return date;
        }
        String[] strArr = f7047b;
        synchronized (strArr) {
            int length = strArr.length;
            for (int i7 = 0; i7 < length; i7++) {
                DateFormat[] dateFormatArr = f7048c;
                DateFormat dateFormat = dateFormatArr[i7];
                DateFormat dateFormat2 = dateFormat;
                if (dateFormat == null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(f7047b[i7], Locale.US);
                    simpleDateFormat.setTimeZone(d6.b.f5965f);
                    dateFormatArr[i7] = simpleDateFormat;
                    dateFormat2 = simpleDateFormat;
                }
                parsePosition.setIndex(0);
                Date date2 = dateFormat2.parse(toHttpDateOrNull, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return date2;
                }
            }
            r rVar = r.f6563a;
            return null;
        }
    }

    public static final String b(Date toHttpDateString) {
        l.e(toHttpDateString, "$this$toHttpDateString");
        String str = f7046a.get().format(toHttpDateString);
        l.d(str, "STANDARD_DATE_FORMAT.get().format(this)");
        return str;
    }
}
