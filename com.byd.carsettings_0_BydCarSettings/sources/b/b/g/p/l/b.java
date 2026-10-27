package b.b.g.p.l;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.icu.text.TimeZoneFormat;
import android.icu.text.TimeZoneNames;
import android.text.BidiFormatter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.timezone.CountryTimeZones;
import android.timezone.TimeZoneFinder;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.byd.carsettings.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ZoneGetterV2.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: compiled from: ZoneGetterV2.java */
    @VisibleForTesting
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f1415a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final CharSequence[] f1416b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final TimeZone[] f1417c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Set<String> f1418d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1419e;

        @RequiresApi(api = 26)
        public a(Context context) {
            Locale locale = context.getResources().getConfiguration().locale;
            TimeZoneFormat timeZoneFormat = TimeZoneFormat.getInstance(locale);
            Date date = new Date();
            List<String> listH = b.h(context);
            int size = listH.size();
            this.f1419e = size;
            this.f1415a = new String[size];
            this.f1417c = new TimeZone[size];
            this.f1416b = new CharSequence[size];
            for (int i = 0; i < this.f1419e; i++) {
                String str = listH.get(i);
                this.f1415a[i] = str;
                TimeZone timeZone = TimeZone.getTimeZone(str);
                this.f1417c[i] = timeZone;
                this.f1416b[i] = b.d(timeZoneFormat, locale, timeZone, date);
            }
            List<String> listB = b(locale.getCountry());
            this.f1418d = listB != null ? new HashSet(listB) : new HashSet();
        }

        public static List<String> a(List<CountryTimeZones.TimeZoneMapping> list) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<CountryTimeZones.TimeZoneMapping> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getTimeZoneId());
            }
            return Collections.unmodifiableList(arrayList);
        }

        @VisibleForTesting
        public List<String> b(String str) {
            if (TimeZoneFinder.getInstance().lookupCountryTimeZones(str) == null) {
                return null;
            }
            return a(new ArrayList());
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, CharSequence charSequence, TtsSpan ttsSpan) {
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(charSequence);
        spannableStringBuilder.setSpan(ttsSpan, length, spannableStringBuilder.length(), 0);
    }

    public static Map<String, Object> b(TimeZone timeZone, CharSequence charSequence, CharSequence charSequence2, int i) {
        HashMap map = new HashMap();
        map.put("id", timeZone.getID());
        map.put("name", charSequence2.toString());
        map.put("display_label", charSequence2);
        map.put("gmt", charSequence.toString());
        map.put("offset_label", charSequence);
        map.put("offset", Integer.valueOf(i));
        return map;
    }

    public static String c(int i, int i2, String str) {
        int i3 = i / 10;
        int i4 = i % 10;
        StringBuilder sb = new StringBuilder(i2);
        if (i >= 10 || i2 == 2) {
            sb.append(str.charAt(i3));
        }
        sb.append(str.charAt(i4));
        return sb.toString();
    }

    @RequiresApi(api = 26)
    public static CharSequence d(TimeZoneFormat timeZoneFormat, Locale locale, TimeZone timeZone, Date date) {
        String strSubstring;
        String str;
        TimeZoneFormat.GMTOffsetPatternType gMTOffsetPatternType;
        int i;
        String str2;
        int i2;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String gMTPattern = timeZoneFormat.getGMTPattern();
        int iIndexOf = gMTPattern.indexOf("{0}");
        if (iIndexOf == -1) {
            str = "GMT";
            strSubstring = "";
        } else {
            String strSubstring2 = gMTPattern.substring(0, iIndexOf);
            strSubstring = gMTPattern.substring(iIndexOf + 3);
            str = strSubstring2;
        }
        if (!str.isEmpty()) {
            a(spannableStringBuilder, str, new TtsSpan.TextBuilder(str).build());
        }
        int offset = timeZone.getOffset(date.getTime());
        if (offset < 0) {
            offset = -offset;
            gMTOffsetPatternType = TimeZoneFormat.GMTOffsetPatternType.NEGATIVE_HM;
        } else {
            gMTOffsetPatternType = TimeZoneFormat.GMTOffsetPatternType.POSITIVE_HM;
        }
        String gMTOffsetPattern = timeZoneFormat.getGMTOffsetPattern(gMTOffsetPatternType);
        String gMTOffsetDigits = timeZoneFormat.getGMTOffsetDigits();
        int i3 = offset / 3600000;
        int iAbs = Math.abs(offset / 60000) % 60;
        int i4 = 0;
        while (i4 < gMTOffsetPattern.length()) {
            char cCharAt = gMTOffsetPattern.charAt(i4);
            if (cCharAt == '+' || cCharAt == '-' || cCharAt == 8722) {
                String strValueOf = String.valueOf(cCharAt);
                a(spannableStringBuilder, strValueOf, new TtsSpan.VerbatimBuilder(strValueOf).build());
            } else if (cCharAt == 'H' || cCharAt == 'm') {
                int i5 = i4 + 1;
                if (i5 >= gMTOffsetPattern.length() || gMTOffsetPattern.charAt(i5) != cCharAt) {
                    i5 = i4;
                    i = 1;
                } else {
                    i = 2;
                }
                if (cCharAt == 'H') {
                    str2 = "hour";
                    i2 = i3;
                } else {
                    str2 = "minute";
                    i2 = iAbs;
                }
                a(spannableStringBuilder, c(i2, i, gMTOffsetDigits), new TtsSpan.MeasureBuilder().setNumber(i2).setUnit(str2).build());
                i4 = i5;
            } else {
                spannableStringBuilder.append(cCharAt);
            }
            i4++;
        }
        if (!strSubstring.isEmpty()) {
            a(spannableStringBuilder, strSubstring, new TtsSpan.TextBuilder(strSubstring).build());
        }
        return BidiFormatter.getInstance().unicodeWrap(new SpannableString(spannableStringBuilder), TextUtils.getLayoutDirectionFromLocale(locale) == 1 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
    }

    public static CharSequence e(Context context, a aVar, TimeZoneNames timeZoneNames, boolean z, TimeZone timeZone, String str) {
        String strF;
        Date date = new Date();
        boolean zContains = aVar.f1418d.contains(str);
        boolean z2 = zContains && !z;
        b.b.m.c.a.a("ZoneGetterV2", "isLocalZoneId:" + zContains + ",preferLongName:" + z2);
        if (z2) {
            strF = f(timeZoneNames, timeZone, date);
        } else {
            String canonicalID = android.icu.util.TimeZone.getCanonicalID(timeZone.getID());
            if (canonicalID == null) {
                canonicalID = timeZone.getID();
            }
            String exemplarLocationName = timeZoneNames.getExemplarLocationName(canonicalID);
            strF = (exemplarLocationName == null || exemplarLocationName.isEmpty()) ? f(timeZoneNames, timeZone, date) : exemplarLocationName;
        }
        if ("Asia/Shanghai".equals(str)) {
            strF = context.getString(R.string.time_time_zone_shanghai);
        }
        if ("Asia/Hong_Kong".equals(str)) {
            strF = context.getString(R.string.time_time_zone_hong_kong);
        }
        if ("Asia/Taipei".equals(str)) {
            strF = context.getString(R.string.time_time_zone_taipei);
        }
        b.b.m.c.a.a("ZoneGetterV2", "displayName:" + strF);
        return strF;
    }

    public static String f(TimeZoneNames timeZoneNames, TimeZone timeZone, Date date) {
        return timeZoneNames.getDisplayName(timeZone.getID(), timeZone.inDaylightTime(date) ? TimeZoneNames.NameType.LONG_DAYLIGHT : TimeZoneNames.NameType.LONG_STANDARD, date.getTime());
    }

    @RequiresApi(api = 26)
    public static List<Map<String, Object>> g(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        Date date = new Date();
        TimeZoneNames timeZoneNames = TimeZoneNames.getInstance(locale);
        a aVar = new a(context);
        boolean zI = i(aVar, timeZoneNames);
        b.b.m.c.a.a("ZoneGetterV2", "useExemplarLocationForLocalNames:" + zI);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < aVar.f1419e; i++) {
            TimeZone timeZone = aVar.f1417c[i];
            CharSequence charSequence = aVar.f1416b[i];
            CharSequence charSequenceE = e(context, aVar, timeZoneNames, zI, timeZone, aVar.f1415a[i]);
            if (TextUtils.isEmpty(charSequenceE)) {
                charSequenceE = charSequence;
            }
            arrayList.add(b(timeZone, charSequence, charSequenceE, timeZone.getOffset(date.getTime())));
        }
        return arrayList;
    }

    public static List<String> h(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            XmlResourceParser xml = context.getResources().getXml(R.xml.timezones);
            while (xml.next() != 2) {
            }
            xml.next();
            while (xml.getEventType() != 3) {
                while (xml.getEventType() != 2) {
                    if (xml.getEventType() == 1) {
                        xml.close();
                        return arrayList;
                    }
                    xml.next();
                }
                if (xml.getName().equals("timezone")) {
                    arrayList.add(xml.getAttributeValue(0));
                }
                while (xml.getEventType() != 3) {
                    xml.next();
                }
                xml.next();
            }
            xml.close();
        } catch (IOException unused) {
            Log.e("ZoneGetterV2", "Unable to read timezones.xml file");
        } catch (XmlPullParserException unused2) {
            Log.e("ZoneGetterV2", "Ill-formatted timezones.xml file");
        }
        return arrayList;
    }

    public static boolean i(a aVar, TimeZoneNames timeZoneNames) {
        HashSet hashSet = new HashSet();
        Date date = new Date();
        for (int i = 0; i < aVar.f1419e; i++) {
            if (aVar.f1418d.contains(aVar.f1415a[i])) {
                CharSequence charSequenceF = f(timeZoneNames, aVar.f1417c[i], date);
                if (charSequenceF == null) {
                    charSequenceF = aVar.f1416b[i];
                }
                if (!hashSet.add(charSequenceF)) {
                    return true;
                }
            }
        }
        return false;
    }
}
