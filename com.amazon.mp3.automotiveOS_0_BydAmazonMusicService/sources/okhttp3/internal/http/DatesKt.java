package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

/* JADX INFO: compiled from: dates.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000#\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0004\u001a\u000e\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0000*\u00020\u0001H\u0000\"\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006\"\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t\"\u001c\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"", "Ljava/util/Date;", "toHttpDateOrNull", "toHttpDateString", "okhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1", "STANDARD_DATE_FORMAT", "Lokhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1;", "", "BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS", "[Ljava/lang/String;", "Ljava/text/DateFormat;", "BROWSER_COMPATIBLE_DATE_FORMATS", "[Ljava/text/DateFormat;", "okhttp"}, k = 2, mv = {1, 6, 0})
public final class DatesKt {
    private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS;
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
    private static final DatesKt$STANDARD_DATE_FORMAT$1 STANDARD_DATE_FORMAT = new ThreadLocal<DateFormat>() { // from class: okhttp3.internal.http.DatesKt$STANDARD_DATE_FORMAT$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(Util.UTC);
            return simpleDateFormat;
        }
    };

    /* JADX WARN: Type inference failed for: r0v0, types: [okhttp3.internal.http.DatesKt$STANDARD_DATE_FORMAT$1] */
    static {
        String[] strArr = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = strArr;
        BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[strArr.length];
    }

    public static final Date toHttpDateOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = STANDARD_DATE_FORMAT.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return date;
        }
        String[] strArr = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
        synchronized (strArr) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                DateFormat[] dateFormatArr = BROWSER_COMPATIBLE_DATE_FORMATS;
                DateFormat simpleDateFormat = dateFormatArr[i];
                if (simpleDateFormat == null) {
                    simpleDateFormat = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i], Locale.US);
                    simpleDateFormat.setTimeZone(Util.UTC);
                    dateFormatArr[i] = simpleDateFormat;
                }
                parsePosition.setIndex(0);
                Date date2 = simpleDateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return date2;
                }
                i = i2;
            }
            Unit unit = Unit.INSTANCE;
            return null;
        }
    }

    public static final String toHttpDateString(Date date) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        String str = STANDARD_DATE_FORMAT.get().format(date);
        Intrinsics.checkNotNullExpressionValue(str, "STANDARD_DATE_FORMAT.get().format(this)");
        return str;
    }
}
