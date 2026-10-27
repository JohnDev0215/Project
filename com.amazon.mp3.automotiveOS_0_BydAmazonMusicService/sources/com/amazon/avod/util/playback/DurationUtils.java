package com.amazon.avod.util.playback;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.NavigableMap;
import java.util.TimeZone;
import java.util.TreeMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DurationUtils {
    private static final NavigableMap<Long, String> AUTO_FORMAT_LOOKUP;
    private static final String[] DOUBLE_DIGIT_PADDING_STRINGS;
    private static final String[] TRIPLE_DIGIT_PADDING_STRINGS;
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");

    static {
        TreeMap treeMapNewTreeMap = Maps.newTreeMap();
        AUTO_FORMAT_LOOKUP = treeMapNewTreeMap;
        treeMapNewTreeMap.put(0L, "mm:ss");
        treeMapNewTreeMap.put(3600000L, "H:mm:ss");
        treeMapNewTreeMap.put(36000000L, "HH:mm:ss");
        DOUBLE_DIGIT_PADDING_STRINGS = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"};
        TRIPLE_DIGIT_PADDING_STRINGS = new String[]{"000", "001", "002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023", "024", "025", "026", "027", "028", "029", "030", "031", "032", "033", "034", "035", "036", "037", "038", "039", "040", "041", "042", "043", "044", "045", "046", "047", "048", "049", "050", "051", "052", "053", "054", "055", "056", "057", "058", "059", "060", "061", "062", "063", "064", "065", "066", "067", "068", "069", "070", "071", "072", "073", "074", "075", "076", "077", "078", "079", "080", "081", "082", "083", "084", "085", "086", "087", "088", "089", "090", "091", "092", "093", "094", "095", "096", "097", "098", "099"};
    }

    public static String getSuggestedFormat(long j) {
        Preconditions.checkArgument(j >= 0, "Duration has to be a positive number");
        return AUTO_FORMAT_LOOKUP.floorEntry(Long.valueOf(j)).getValue();
    }

    public static String format(long j, String str, StringBuilder sb, boolean z) {
        int i;
        Preconditions.checkArgument(j >= 0, "Duration has to be a positive number");
        if (z) {
            sb.setLength(0);
        }
        int iIndexOf = 0;
        while (iIndexOf < str.length()) {
            char cCharAt = str.charAt(iIndexOf);
            if (cCharAt == '\'') {
                int i2 = iIndexOf + 1;
                sb.append(str.substring(i2, str.indexOf(39, i2)));
                iIndexOf = str.indexOf(39, i2);
            } else if (cCharAt == 'H') {
                int i3 = (int) (j / 3600000);
                j %= 3600000;
                i = iIndexOf + 1;
                if (i == str.length() || str.charAt(i) != 'H') {
                    sb.append(lookupHMS(i3, false));
                } else {
                    sb.append(lookupHMS(i3, true));
                    iIndexOf = i;
                }
            } else if (cCharAt == 'S') {
                int i4 = (int) j;
                int i5 = iIndexOf + 1;
                if (i5 == str.length() || (str.charAt(i5) != 'S' && str.charAt(iIndexOf + 2) != 'S')) {
                    sb.append(lookupMillis(i4, false));
                } else {
                    sb.append(lookupMillis(i4, true));
                    iIndexOf += 2;
                }
            } else if (cCharAt == 'm') {
                int i6 = (int) (j / 60000);
                j %= 60000;
                i = iIndexOf + 1;
                if (i == str.length() || str.charAt(i) != 'm') {
                    sb.append(lookupHMS(i6, false));
                } else {
                    sb.append(lookupHMS(i6, true));
                    iIndexOf = i;
                }
            } else if (cCharAt == 's') {
                int i7 = (int) (j / 1000);
                j %= 1000;
                i = iIndexOf + 1;
                if (i == str.length() || str.charAt(i) != 's') {
                    sb.append(lookupHMS(i7, false));
                } else {
                    sb.append(lookupHMS(i7, true));
                    iIndexOf = i;
                }
            } else {
                sb.append(cCharAt);
            }
            iIndexOf++;
        }
        return sb.toString();
    }

    private static String lookupHMS(int i, boolean z) {
        String[] strArr = DOUBLE_DIGIT_PADDING_STRINGS;
        if (i < strArr.length && z) {
            return strArr[i];
        }
        return String.valueOf(i);
    }

    private static String lookupMillis(int i, boolean z) {
        String[] strArr = TRIPLE_DIGIT_PADDING_STRINGS;
        if (i < strArr.length && z) {
            return strArr[i];
        }
        return String.valueOf(i);
    }

    public static long makeTimeInMillisFromString(@Nonnull String str, @Nonnull String str2) throws ParseException {
        Preconditions.checkNotNull(str, "Cannot parse a null time format");
        Preconditions.checkNotNull(str2, "Cannot convert a null time to millis");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.getDefault());
        simpleDateFormat.setTimeZone(UTC);
        return simpleDateFormat.parse(str2).getTime();
    }
}
