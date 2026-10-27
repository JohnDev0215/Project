package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class StringUtils {
    public static final StringConverter<Object> ARRAY_STRING_CONVERTER;
    private static final StringConverter<Object> DEFAULT_STRING_CONVERTER;
    public static final StringConverter<Iterable<?>> ITERABLE_STRING_CONVERTER;
    public static final StringConverter<Iterator<?>> ITERATOR_STRING_CONVERTER;
    private static final TimeUnit[] ORDERED_TIME_UNITS;
    private static final String[] ORDERED_TIME_UNIT_STRINGS;
    private static final StringConverter<Redactable> REDACTABLE_STRING_CONVERTER;
    public static final MapStringConverter REDACT_ALL_MAP_STRING_CONVERTER;
    public static final MapStringConverter REDACT_KEYS_MAP_STRING_CONVERTER;
    public static final MapStringConverter REDACT_VALUES_MAP_STRING_CONVERTER;
    private static final Map<Class<?>, StringConverter<?>> converterMap;
    private static final List<StringConverter> converters;
    private static boolean redact;

    public interface Redactable {
        String toString(boolean z);
    }

    @NonNull
    public static String nullToEmpty(@Nullable String str) {
        return str == null ? "" : str;
    }

    public static abstract class StringConverter<T> {
        private Class<T> clazz;

        public abstract String toString(@NonNull T t, boolean z);

        public StringConverter(Class<T> cls) {
            this.clazz = cls;
        }

        public boolean accepts(@NonNull Class<?> cls) {
            return this.clazz.isAssignableFrom(cls);
        }
    }

    private static class MapStringConverter extends StringConverter<Map<?, ?>> {
        private final boolean redactKeys;
        private final boolean redactValues;

        public MapStringConverter(boolean z, boolean z2) {
            super(Map.class);
            this.redactKeys = z;
            this.redactValues = z2;
        }

        @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
        public String toString(@NonNull Map<?, ?> map, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            boolean z2 = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                boolean z3 = false;
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(", ");
                }
                sb.append(StringUtils.toString(entry.getKey(), this.redactKeys && z));
                sb.append("=>");
                Object value = entry.getValue();
                if (this.redactValues && z) {
                    z3 = true;
                }
                sb.append(StringUtils.toString(value, z3));
            }
            sb.append("}");
            return sb.toString();
        }
    }

    static {
        Class<Object> cls = Object.class;
        StringConverter<Iterator<?>> stringConverter = new StringConverter<Iterator<?>>(Iterator.class) { // from class: com.amazon.music.media.playback.util.StringUtils.1
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull Iterator<?> it, boolean z) {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                boolean z2 = true;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (z2) {
                        z2 = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(StringUtils.toString(next, z));
                }
                sb.append("}");
                return sb.toString();
            }
        };
        ITERATOR_STRING_CONVERTER = stringConverter;
        StringConverter<Iterable<?>> stringConverter2 = new StringConverter<Iterable<?>>(Iterable.class) { // from class: com.amazon.music.media.playback.util.StringUtils.2
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull Iterable<?> iterable, boolean z) {
                return StringUtils.ITERATOR_STRING_CONVERTER.toString(iterable.iterator(), z);
            }
        };
        ITERABLE_STRING_CONVERTER = stringConverter2;
        ARRAY_STRING_CONVERTER = new StringConverter<Object>(cls) { // from class: com.amazon.music.media.playback.util.StringUtils.3
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull Object obj, boolean z) {
                return StringUtils.ITERATOR_STRING_CONVERTER.toString(new Iterator<Object>(obj) { // from class: com.amazon.music.media.playback.util.StringUtils.3.1
                    private int index = 0;
                    private final int size;
                    final /* synthetic */ Object val$array;

                    {
                        this.val$array = obj;
                        this.size = Array.getLength(obj);
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.index < this.size;
                    }

                    @Override // java.util.Iterator
                    public Object next() {
                        Object obj2 = this.val$array;
                        int i = this.index;
                        this.index = i + 1;
                        return Array.get(obj2, i);
                    }
                }, z);
            }
        };
        DEFAULT_STRING_CONVERTER = new StringConverter<Object>(cls) { // from class: com.amazon.music.media.playback.util.StringUtils.4
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull Object obj, boolean z) {
                return z ? "<Redacted>" : obj.toString();
            }
        };
        StringConverter<Redactable> stringConverter3 = new StringConverter<Redactable>(Redactable.class) { // from class: com.amazon.music.media.playback.util.StringUtils.5
            @Override // com.amazon.music.media.playback.util.StringUtils.StringConverter
            public String toString(@NonNull Redactable redactable, boolean z) {
                return redactable.toString(z);
            }
        };
        REDACTABLE_STRING_CONVERTER = stringConverter3;
        REDACT_KEYS_MAP_STRING_CONVERTER = new MapStringConverter(true, false);
        REDACT_VALUES_MAP_STRING_CONVERTER = new MapStringConverter(false, true);
        MapStringConverter mapStringConverter = new MapStringConverter(true, true);
        REDACT_ALL_MAP_STRING_CONVERTER = mapStringConverter;
        ArrayList arrayList = new ArrayList();
        converters = arrayList;
        arrayList.add(stringConverter3);
        arrayList.add(mapStringConverter);
        arrayList.add(stringConverter2);
        arrayList.add(stringConverter);
        converterMap = new HashMap();
        ORDERED_TIME_UNITS = new TimeUnit[]{TimeUnit.NANOSECONDS, TimeUnit.MICROSECONDS, TimeUnit.MILLISECONDS, TimeUnit.SECONDS, TimeUnit.MINUTES, TimeUnit.HOURS, TimeUnit.DAYS};
        ORDERED_TIME_UNIT_STRINGS = new String[]{"ns", "us", "ms", "secs", "mins", "hours", "days"};
        redact = true;
    }

    private static <T> StringConverter<? super T> getStringConverter(@NonNull T t) {
        StringConverter<? super T> stringConverter;
        Map<Class<?>, StringConverter<?>> map = converterMap;
        synchronized (map) {
            Class<?> cls = t.getClass();
            stringConverter = (StringConverter) map.get(cls);
            if (stringConverter == null) {
                stringConverter = DEFAULT_STRING_CONVERTER;
                Iterator<StringConverter> it = converters.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    StringConverter next = it.next();
                    if (next.accepts(cls)) {
                        stringConverter = next;
                        break;
                    }
                }
                converterMap.put(cls, stringConverter);
            }
        }
        return stringConverter;
    }

    public static void registerStringConverter(StringConverter<?> stringConverter) {
        Map<Class<?>, StringConverter<?>> map = converterMap;
        synchronized (map) {
            List<StringConverter> list = converters;
            if (!list.contains(stringConverter)) {
                list.add(stringConverter);
                map.clear();
            }
        }
    }

    @Nullable
    public static String emptyToNull(@NonNull String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }

    public static boolean isEmpty(@Nullable String str) {
        return str == null || str.length() == 0;
    }

    public static String toString(Object obj) {
        return toString(obj, false);
    }

    public static String redactIfNeeded(Object obj) {
        return toString(obj, true);
    }

    public static String toString(Object obj, String str) {
        String string;
        return (obj == null || (string = obj.toString()) == null) ? str : string;
    }

    public static <T> String toString(T t, boolean z) {
        StringConverter<Object> stringConverter;
        if (t == null) {
            return "null";
        }
        if (t.getClass().isArray()) {
            stringConverter = ARRAY_STRING_CONVERTER;
        } else {
            stringConverter = getStringConverter(t);
        }
        return toString(t, stringConverter, z);
    }

    public static <T> String toString(T t, StringConverter<? super T> stringConverter, boolean z) {
        if (t == null) {
            return "null";
        }
        return stringConverter.toString(t, z && shouldRedact());
    }

    public static String getDurationString(long j) {
        return getDurationString(j, TimeUnit.MILLISECONDS);
    }

    public static String getDurationString(long j, TimeUnit timeUnit) {
        String string;
        boolean z = j < 0;
        long jAbs = Math.abs(j) * 1000;
        TimeUnit timeUnit2 = (TimeUnit) Validate.notNull(timeUnit);
        int length = ORDERED_TIME_UNITS.length - 2;
        int i = 0;
        long j2 = jAbs;
        while (i <= length) {
            TimeUnit[] timeUnitArr = ORDERED_TIME_UNITS;
            if (timeUnitArr[i] == timeUnit2) {
                TimeUnit timeUnit3 = timeUnitArr[i + 1];
                long jConvert = timeUnit3.convert(jAbs, timeUnit);
                if (jConvert < 1000) {
                    break;
                }
                timeUnit2 = timeUnit3;
                j2 = jConvert;
            }
            i++;
        }
        if (timeUnit2 == timeUnit && j < 1000) {
            string = Long.toString(j);
        } else if (j2 < 100000 && (timeUnit2 == TimeUnit.HOURS || timeUnit2 == TimeUnit.MINUTES)) {
            string = ((j2 + 8) / 1000) + ":" + String.format(Locale.US, "%02d", Integer.valueOf((((((int) j2) + 8) % 1000) * 60) / 1000));
        } else if (j2 < 10000) {
            string = String.format(Locale.US, "%1.2f", Double.valueOf(j2 / 1000.0d));
        } else if (j2 < 100000) {
            string = String.format(Locale.US, "%2.1f", Double.valueOf(j2 / 1000.0d));
        } else {
            string = j2 < 1000000000000L ? String.format(Locale.US, "%,d", Long.valueOf(j2 / 1000)) : "billions of";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "-" : "");
        sb.append(string);
        sb.append(" ");
        sb.append(ORDERED_TIME_UNIT_STRINGS[i]);
        return sb.toString();
    }

    public static String getStackTrace(int i) {
        return getStackTrace(Thread.currentThread().getStackTrace(), 3, i);
    }

    private static String getStackTrace(StackTraceElement[] stackTraceElementArr, int i, int i2) {
        int iMin = Math.min(i2 > 0 ? i2 + i : Integer.MAX_VALUE, stackTraceElementArr.length);
        StringBuilder sb = new StringBuilder();
        while (i < iMin) {
            sb.append("\n    at ");
            sb.append(stackTraceElementArr[i].toString());
            i++;
        }
        return sb.toString();
    }

    public static boolean shouldRedact() {
        return redact;
    }

    public static void setShouldRedact(boolean z) {
        redact = z;
    }

    private StringUtils() {
    }
}
