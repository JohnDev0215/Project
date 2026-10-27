package Touch.MultiSelectorTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SelectedElementsClientInformation", generator = "Immutables")
public final class ImmutableSelectedElementsClientInformation extends SelectedElementsClientInformation {
    private final Map<String, Set<String>> elementIds;

    private ImmutableSelectedElementsClientInformation(Map<String, Set<String>> map) {
        this.elementIds = map;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectedElementsClientInformation
    @JsonProperty("elementIds")
    public Map<String, Set<String>> elementIds() {
        return this.elementIds;
    }

    public final ImmutableSelectedElementsClientInformation withElementIds(Map<String, ? extends Set<String>> map) {
        return this.elementIds == map ? this : new ImmutableSelectedElementsClientInformation(createUnmodifiableMap(true, false, map));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSelectedElementsClientInformation) && equalTo(0, (ImmutableSelectedElementsClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableSelectedElementsClientInformation immutableSelectedElementsClientInformation) {
        return this.elementIds.equals(immutableSelectedElementsClientInformation.elementIds);
    }

    public int hashCode() {
        return 172192 + this.elementIds.hashCode() + 5381;
    }

    public String toString() {
        return "SelectedElementsClientInformation{elementIds=" + this.elementIds + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SelectedElementsClientInformation", generator = "Immutables")
    static final class Json extends SelectedElementsClientInformation {

        @Nullable
        Map<String, Set<String>> elementIds = Collections.emptyMap();

        Json() {
        }

        @JsonProperty("elementIds")
        public void setElementIds(Map<String, Set<String>> map) {
            this.elementIds = map;
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.SelectedElementsClientInformation
        public Map<String, Set<String>> elementIds() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSelectedElementsClientInformation fromJson(Json json) {
        Builder builder = builder();
        Map<String, Set<String>> map = json.elementIds;
        if (map != null) {
            builder.putAllElementIds(map);
        }
        return builder.build();
    }

    public static ImmutableSelectedElementsClientInformation copyOf(SelectedElementsClientInformation selectedElementsClientInformation) {
        if (selectedElementsClientInformation instanceof ImmutableSelectedElementsClientInformation) {
            return (ImmutableSelectedElementsClientInformation) selectedElementsClientInformation;
        }
        return builder().from(selectedElementsClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SelectedElementsClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private Map<String, Set<String>> elementIds;

        private Builder() {
            this.elementIds = new LinkedHashMap();
        }

        public final Builder from(SelectedElementsClientInformation selectedElementsClientInformation) {
            ImmutableSelectedElementsClientInformation.requireNonNull(selectedElementsClientInformation, "instance");
            putAllElementIds(selectedElementsClientInformation.elementIds());
            return this;
        }

        public final Builder putElementIds(String str, Set<String> set) {
            this.elementIds.put((String) ImmutableSelectedElementsClientInformation.requireNonNull(str, "elementIds key"), (Set) ImmutableSelectedElementsClientInformation.requireNonNull(set, "elementIds value"));
            return this;
        }

        public final Builder putElementIds(Map.Entry<String, ? extends Set<String>> entry) {
            this.elementIds.put((String) ImmutableSelectedElementsClientInformation.requireNonNull(entry.getKey(), "elementIds key"), (Set) ImmutableSelectedElementsClientInformation.requireNonNull(entry.getValue(), "elementIds value"));
            return this;
        }

        @JsonProperty("elementIds")
        public final Builder elementIds(Map<String, ? extends Set<String>> map) {
            this.elementIds.clear();
            return putAllElementIds(map);
        }

        public final Builder putAllElementIds(Map<String, ? extends Set<String>> map) {
            for (Map.Entry<String, ? extends Set<String>> entry : map.entrySet()) {
                this.elementIds.put((String) ImmutableSelectedElementsClientInformation.requireNonNull(entry.getKey(), "elementIds key"), (Set) ImmutableSelectedElementsClientInformation.requireNonNull(entry.getValue(), "elementIds value"));
            }
            return this;
        }

        public ImmutableSelectedElementsClientInformation build() {
            return new ImmutableSelectedElementsClientInformation(ImmutableSelectedElementsClientInformation.createUnmodifiableMap(false, false, this.elementIds));
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
