package PlaybackInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "LyricsElement", generator = "Immutables")
public final class ImmutableLyricsElement extends LyricsElement {
    private final String creditsLine;
    private final Integer increment;
    private final Map<String, String> lines;
    private final Map<String, String> timing;

    private ImmutableLyricsElement(Integer num, Map<String, String> map, Map<String, String> map2, String str) {
        this.increment = num;
        this.lines = map;
        this.timing = map2;
        this.creditsLine = str;
    }

    @Override // PlaybackInterface.v1_0.LyricsElement
    @JsonProperty("increment")
    public Integer increment() {
        return this.increment;
    }

    @Override // PlaybackInterface.v1_0.LyricsElement
    @JsonProperty("lines")
    public Map<String, String> lines() {
        return this.lines;
    }

    @Override // PlaybackInterface.v1_0.LyricsElement
    @JsonProperty("timing")
    public Map<String, String> timing() {
        return this.timing;
    }

    @Override // PlaybackInterface.v1_0.LyricsElement
    @JsonProperty("creditsLine")
    public String creditsLine() {
        return this.creditsLine;
    }

    public final ImmutableLyricsElement withIncrement(Integer num) {
        Integer num2 = (Integer) requireNonNull(num, "increment");
        return this.increment.equals(num2) ? this : new ImmutableLyricsElement(num2, this.lines, this.timing, this.creditsLine);
    }

    public final ImmutableLyricsElement withLines(Map<String, ? extends String> map) {
        if (this.lines == map) {
            return this;
        }
        return new ImmutableLyricsElement(this.increment, createUnmodifiableMap(true, false, map), this.timing, this.creditsLine);
    }

    public final ImmutableLyricsElement withTiming(Map<String, ? extends String> map) {
        if (this.timing == map) {
            return this;
        }
        return new ImmutableLyricsElement(this.increment, this.lines, createUnmodifiableMap(true, false, map), this.creditsLine);
    }

    public final ImmutableLyricsElement withCreditsLine(String str) {
        String str2 = (String) requireNonNull(str, "creditsLine");
        return this.creditsLine.equals(str2) ? this : new ImmutableLyricsElement(this.increment, this.lines, this.timing, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLyricsElement) && equalTo(0, (ImmutableLyricsElement) obj);
    }

    private boolean equalTo(int i, ImmutableLyricsElement immutableLyricsElement) {
        return this.increment.equals(immutableLyricsElement.increment) && this.lines.equals(immutableLyricsElement.lines) && this.timing.equals(immutableLyricsElement.timing) && this.creditsLine.equals(immutableLyricsElement.creditsLine);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.increment.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.lines.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.timing.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.creditsLine.hashCode();
    }

    public String toString() {
        return "LyricsElement{increment=" + this.increment + ", lines=" + this.lines + ", timing=" + this.timing + ", creditsLine=" + this.creditsLine + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LyricsElement", generator = "Immutables")
    static final class Json extends LyricsElement {

        @Nullable
        String creditsLine;

        @Nullable
        Integer increment;

        @Nullable
        Map<String, String> lines = Collections.emptyMap();

        @Nullable
        Map<String, String> timing = Collections.emptyMap();

        Json() {
        }

        @JsonProperty("increment")
        public void setIncrement(Integer num) {
            this.increment = num;
        }

        @JsonProperty("lines")
        public void setLines(Map<String, String> map) {
            this.lines = map;
        }

        @JsonProperty("timing")
        public void setTiming(Map<String, String> map) {
            this.timing = map;
        }

        @JsonProperty("creditsLine")
        public void setCreditsLine(String str) {
            this.creditsLine = str;
        }

        @Override // PlaybackInterface.v1_0.LyricsElement
        public Integer increment() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.LyricsElement
        public Map<String, String> lines() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.LyricsElement
        public Map<String, String> timing() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.LyricsElement
        public String creditsLine() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLyricsElement fromJson(Json json) {
        Builder builder = builder();
        Integer num = json.increment;
        if (num != null) {
            builder.increment(num);
        }
        Map<String, String> map = json.lines;
        if (map != null) {
            builder.putAllLines(map);
        }
        Map<String, String> map2 = json.timing;
        if (map2 != null) {
            builder.putAllTiming(map2);
        }
        String str = json.creditsLine;
        if (str != null) {
            builder.creditsLine(str);
        }
        return builder.build();
    }

    public static ImmutableLyricsElement copyOf(LyricsElement lyricsElement) {
        if (lyricsElement instanceof ImmutableLyricsElement) {
            return (ImmutableLyricsElement) lyricsElement;
        }
        return builder().from(lyricsElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LyricsElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_CREDITS_LINE = 2;
        private static final long INIT_BIT_INCREMENT = 1;

        @Nullable
        private String creditsLine;

        @Nullable
        private Integer increment;
        private long initBits;
        private Map<String, String> lines;
        private Map<String, String> timing;

        private Builder() {
            this.initBits = 3L;
            this.lines = new LinkedHashMap();
            this.timing = new LinkedHashMap();
        }

        public final Builder from(LyricsElement lyricsElement) {
            ImmutableLyricsElement.requireNonNull(lyricsElement, "instance");
            increment(lyricsElement.increment());
            putAllLines(lyricsElement.lines());
            putAllTiming(lyricsElement.timing());
            creditsLine(lyricsElement.creditsLine());
            return this;
        }

        @JsonProperty("increment")
        public final Builder increment(Integer num) {
            this.increment = (Integer) ImmutableLyricsElement.requireNonNull(num, "increment");
            this.initBits &= -2;
            return this;
        }

        public final Builder putLines(String str, String str2) {
            this.lines.put((String) ImmutableLyricsElement.requireNonNull(str, "lines key"), (String) ImmutableLyricsElement.requireNonNull(str2, "lines value"));
            return this;
        }

        public final Builder putLines(Map.Entry<String, ? extends String> entry) {
            this.lines.put((String) ImmutableLyricsElement.requireNonNull(entry.getKey(), "lines key"), (String) ImmutableLyricsElement.requireNonNull(entry.getValue(), "lines value"));
            return this;
        }

        @JsonProperty("lines")
        public final Builder lines(Map<String, ? extends String> map) {
            this.lines.clear();
            return putAllLines(map);
        }

        public final Builder putAllLines(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.lines.put((String) ImmutableLyricsElement.requireNonNull(entry.getKey(), "lines key"), (String) ImmutableLyricsElement.requireNonNull(entry.getValue(), "lines value"));
            }
            return this;
        }

        public final Builder putTiming(String str, String str2) {
            this.timing.put((String) ImmutableLyricsElement.requireNonNull(str, "timing key"), (String) ImmutableLyricsElement.requireNonNull(str2, "timing value"));
            return this;
        }

        public final Builder putTiming(Map.Entry<String, ? extends String> entry) {
            this.timing.put((String) ImmutableLyricsElement.requireNonNull(entry.getKey(), "timing key"), (String) ImmutableLyricsElement.requireNonNull(entry.getValue(), "timing value"));
            return this;
        }

        @JsonProperty("timing")
        public final Builder timing(Map<String, ? extends String> map) {
            this.timing.clear();
            return putAllTiming(map);
        }

        public final Builder putAllTiming(Map<String, ? extends String> map) {
            for (Map.Entry<String, ? extends String> entry : map.entrySet()) {
                this.timing.put((String) ImmutableLyricsElement.requireNonNull(entry.getKey(), "timing key"), (String) ImmutableLyricsElement.requireNonNull(entry.getValue(), "timing value"));
            }
            return this;
        }

        @JsonProperty("creditsLine")
        public final Builder creditsLine(String str) {
            this.creditsLine = (String) ImmutableLyricsElement.requireNonNull(str, "creditsLine");
            this.initBits &= -3;
            return this;
        }

        public ImmutableLyricsElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLyricsElement(this.increment, ImmutableLyricsElement.createUnmodifiableMap(false, false, this.lines), ImmutableLyricsElement.createUnmodifiableMap(false, false, this.timing), this.creditsLine);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("increment");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("creditsLine");
            }
            return "Cannot build LyricsElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> Map<K, V> createUnmodifiableMap(boolean z, boolean z2, Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return Collections.emptyMap();
        }
        if (size == 1) {
            Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
            K key = next.getKey();
            V value = next.getValue();
            if (z) {
                requireNonNull(key, "key");
                requireNonNull(value, "value");
            }
            if (z2 && (key == null || value == null)) {
                return Collections.emptyMap();
            }
            return Collections.singletonMap(key, value);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        if (z2 || z) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                K key2 = entry.getKey();
                V value2 = entry.getValue();
                if (z2) {
                    if (key2 == null || value2 == null) {
                    }
                } else if (z) {
                    requireNonNull(key2, "key");
                    requireNonNull(value2, "value");
                }
                linkedHashMap.put(key2, value2);
            }
        } else {
            linkedHashMap.putAll(map);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
