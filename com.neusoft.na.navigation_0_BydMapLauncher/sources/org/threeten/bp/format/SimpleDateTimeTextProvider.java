package org.threeten.bp.format;

import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalField;

/* JADX INFO: loaded from: classes4.dex */
final class SimpleDateTimeTextProvider extends DateTimeTextProvider {
    private static final ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> CACHE = new ConcurrentHashMap(16, 0.75f, 2);
    private static final Comparator<Map.Entry<String, Long>> COMPARATOR = new Comparator<Map.Entry<String, Long>>() { // from class: org.threeten.bp.format.SimpleDateTimeTextProvider.1
        @Override // java.util.Comparator
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getKey().length() - entry.getKey().length();
        }
    };

    SimpleDateTimeTextProvider() {
    }

    @Override // org.threeten.bp.format.DateTimeTextProvider
    public Locale[] getAvailableLocales() {
        return DateFormatSymbols.getAvailableLocales();
    }

    @Override // org.threeten.bp.format.DateTimeTextProvider
    public String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
        Object objFindStore = findStore(temporalField, locale);
        if (objFindStore instanceof LocaleStore) {
            return ((LocaleStore) objFindStore).getText(j, textStyle);
        }
        return null;
    }

    @Override // org.threeten.bp.format.DateTimeTextProvider
    public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale) {
        Object objFindStore = findStore(temporalField, locale);
        if (objFindStore instanceof LocaleStore) {
            return ((LocaleStore) objFindStore).getTextIterator(textStyle);
        }
        return null;
    }

    private Object findStore(TemporalField temporalField, Locale locale) {
        Map.Entry<TemporalField, Locale> entryCreateEntry = createEntry(temporalField, locale);
        ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> concurrentMap = CACHE;
        Object obj = concurrentMap.get(entryCreateEntry);
        if (obj != null) {
            return obj;
        }
        concurrentMap.putIfAbsent(entryCreateEntry, createStore(temporalField, locale));
        return concurrentMap.get(entryCreateEntry);
    }

    private Object createStore(TemporalField temporalField, Locale locale) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
            HashMap map = new HashMap();
            String[] months = dateFormatSymbols.getMonths();
            HashMap map2 = new HashMap();
            map2.put(1L, months[0]);
            map2.put(2L, months[1]);
            map2.put(3L, months[2]);
            map2.put(4L, months[3]);
            map2.put(5L, months[4]);
            map2.put(6L, months[5]);
            map2.put(7L, months[6]);
            map2.put(8L, months[7]);
            map2.put(9L, months[8]);
            map2.put(10L, months[9]);
            map2.put(11L, months[10]);
            map2.put(12L, months[11]);
            map.put(TextStyle.FULL, map2);
            HashMap map3 = new HashMap();
            map3.put(1L, months[0].substring(0, 1));
            map3.put(2L, months[1].substring(0, 1));
            map3.put(3L, months[2].substring(0, 1));
            map3.put(4L, months[3].substring(0, 1));
            map3.put(5L, months[4].substring(0, 1));
            map3.put(6L, months[5].substring(0, 1));
            map3.put(7L, months[6].substring(0, 1));
            map3.put(8L, months[7].substring(0, 1));
            map3.put(9L, months[8].substring(0, 1));
            map3.put(10L, months[9].substring(0, 1));
            map3.put(11L, months[10].substring(0, 1));
            map3.put(12L, months[11].substring(0, 1));
            map.put(TextStyle.NARROW, map3);
            String[] shortMonths = dateFormatSymbols.getShortMonths();
            HashMap map4 = new HashMap();
            map4.put(1L, shortMonths[0]);
            map4.put(2L, shortMonths[1]);
            map4.put(3L, shortMonths[2]);
            map4.put(4L, shortMonths[3]);
            map4.put(5L, shortMonths[4]);
            map4.put(6L, shortMonths[5]);
            map4.put(7L, shortMonths[6]);
            map4.put(8L, shortMonths[7]);
            map4.put(9L, shortMonths[8]);
            map4.put(10L, shortMonths[9]);
            map4.put(11L, shortMonths[10]);
            map4.put(12L, shortMonths[11]);
            map.put(TextStyle.SHORT, map4);
            return createLocaleStore(map);
        }
        if (temporalField == ChronoField.DAY_OF_WEEK) {
            DateFormatSymbols dateFormatSymbols2 = DateFormatSymbols.getInstance(locale);
            HashMap map5 = new HashMap();
            String[] weekdays = dateFormatSymbols2.getWeekdays();
            HashMap map6 = new HashMap();
            map6.put(1L, weekdays[2]);
            map6.put(2L, weekdays[3]);
            map6.put(3L, weekdays[4]);
            map6.put(4L, weekdays[5]);
            map6.put(5L, weekdays[6]);
            map6.put(6L, weekdays[7]);
            map6.put(7L, weekdays[1]);
            map5.put(TextStyle.FULL, map6);
            HashMap map7 = new HashMap();
            map7.put(1L, weekdays[2].substring(0, 1));
            map7.put(2L, weekdays[3].substring(0, 1));
            map7.put(3L, weekdays[4].substring(0, 1));
            map7.put(4L, weekdays[5].substring(0, 1));
            map7.put(5L, weekdays[6].substring(0, 1));
            map7.put(6L, weekdays[7].substring(0, 1));
            map7.put(7L, weekdays[1].substring(0, 1));
            map5.put(TextStyle.NARROW, map7);
            String[] shortWeekdays = dateFormatSymbols2.getShortWeekdays();
            HashMap map8 = new HashMap();
            map8.put(1L, shortWeekdays[2]);
            map8.put(2L, shortWeekdays[3]);
            map8.put(3L, shortWeekdays[4]);
            map8.put(4L, shortWeekdays[5]);
            map8.put(5L, shortWeekdays[6]);
            map8.put(6L, shortWeekdays[7]);
            map8.put(7L, shortWeekdays[1]);
            map5.put(TextStyle.SHORT, map8);
            return createLocaleStore(map5);
        }
        if (temporalField == ChronoField.AMPM_OF_DAY) {
            DateFormatSymbols dateFormatSymbols3 = DateFormatSymbols.getInstance(locale);
            HashMap map9 = new HashMap();
            String[] amPmStrings = dateFormatSymbols3.getAmPmStrings();
            HashMap map10 = new HashMap();
            map10.put(0L, amPmStrings[0]);
            map10.put(1L, amPmStrings[1]);
            map9.put(TextStyle.FULL, map10);
            map9.put(TextStyle.SHORT, map10);
            return createLocaleStore(map9);
        }
        if (temporalField == ChronoField.ERA) {
            DateFormatSymbols dateFormatSymbols4 = DateFormatSymbols.getInstance(locale);
            HashMap map11 = new HashMap();
            String[] eras = dateFormatSymbols4.getEras();
            HashMap map12 = new HashMap();
            map12.put(0L, eras[0]);
            map12.put(1L, eras[1]);
            map11.put(TextStyle.SHORT, map12);
            if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                HashMap map13 = new HashMap();
                map13.put(0L, "Before Christ");
                map13.put(1L, "Anno Domini");
                map11.put(TextStyle.FULL, map13);
            } else {
                map11.put(TextStyle.FULL, map12);
            }
            HashMap map14 = new HashMap();
            map14.put(0L, eras[0].substring(0, 1));
            map14.put(1L, eras[1].substring(0, 1));
            map11.put(TextStyle.NARROW, map14);
            return createLocaleStore(map11);
        }
        if (temporalField != IsoFields.QUARTER_OF_YEAR) {
            return "";
        }
        HashMap map15 = new HashMap();
        HashMap map16 = new HashMap();
        map16.put(1L, "Q1");
        map16.put(2L, "Q2");
        map16.put(3L, "Q3");
        map16.put(4L, "Q4");
        map15.put(TextStyle.SHORT, map16);
        HashMap map17 = new HashMap();
        map17.put(1L, "1st quarter");
        map17.put(2L, "2nd quarter");
        map17.put(3L, "3rd quarter");
        map17.put(4L, "4th quarter");
        map15.put(TextStyle.FULL, map17);
        return createLocaleStore(map15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <A, B> Map.Entry<A, B> createEntry(A a, B b) {
        return new AbstractMap.SimpleImmutableEntry(a, b);
    }

    private static LocaleStore createLocaleStore(Map<TextStyle, Map<Long, String>> map) {
        map.put(TextStyle.FULL_STANDALONE, map.get(TextStyle.FULL));
        map.put(TextStyle.SHORT_STANDALONE, map.get(TextStyle.SHORT));
        if (map.containsKey(TextStyle.NARROW) && !map.containsKey(TextStyle.NARROW_STANDALONE)) {
            map.put(TextStyle.NARROW_STANDALONE, map.get(TextStyle.NARROW));
        }
        return new LocaleStore(map);
    }

    static final class LocaleStore {
        private final Map<TextStyle, List<Map.Entry<String, Long>>> parsable;
        private final Map<TextStyle, Map<Long, String>> valueTextMap;

        LocaleStore(Map<TextStyle, Map<Long, String>> map) {
            this.valueTextMap = map;
            HashMap map2 = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (TextStyle textStyle : map.keySet()) {
                HashMap map3 = new HashMap();
                for (Map.Entry<Long, String> entry : map.get(textStyle).entrySet()) {
                    map3.put(entry.getValue(), SimpleDateTimeTextProvider.createEntry(entry.getValue(), entry.getKey()));
                }
                ArrayList arrayList2 = new ArrayList(map3.values());
                Collections.sort(arrayList2, SimpleDateTimeTextProvider.COMPARATOR);
                map2.put(textStyle, arrayList2);
                arrayList.addAll(arrayList2);
                map2.put(null, arrayList);
            }
            Collections.sort(arrayList, SimpleDateTimeTextProvider.COMPARATOR);
            this.parsable = map2;
        }

        String getText(long j, TextStyle textStyle) {
            Map<Long, String> map = this.valueTextMap.get(textStyle);
            if (map != null) {
                return map.get(Long.valueOf(j));
            }
            return null;
        }

        Iterator<Map.Entry<String, Long>> getTextIterator(TextStyle textStyle) {
            List<Map.Entry<String, Long>> list = this.parsable.get(textStyle);
            if (list != null) {
                return list.iterator();
            }
            return null;
        }
    }
}
