package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.PlayControlStateElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
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
@Generated(from = "PlayControlStateElement", generator = "Immutables")
public final class ImmutablePlayControlStateElement extends PlayControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<PlayControlStateElement.State, String> altTexts;
    private final Map<PlayControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledPlayControlSelected;
    private final List<Method> onEnabledPlayControlSelected;

    private ImmutablePlayControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledPlayControlSelectedIsSet()) {
            this.initShim.onEnabledPlayControlSelected(createUnmodifiableList(true, builder.onEnabledPlayControlSelected));
        }
        if (builder.onDisabledPlayControlSelectedIsSet()) {
            this.initShim.onDisabledPlayControlSelected(createUnmodifiableList(true, builder.onDisabledPlayControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledPlayControlSelected = this.initShim.onEnabledPlayControlSelected();
        this.onDisabledPlayControlSelected = this.initShim.onDisabledPlayControlSelected();
        this.initShim = null;
    }

    private ImmutablePlayControlStateElement(Map<PlayControlStateElement.State, String> map, Map<PlayControlStateElement.State, String> map2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.onEnabledPlayControlSelected = list;
        this.onDisabledPlayControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "PlayControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledPlayControlSelected;
        private byte onDisabledPlayControlSelectedBuildStage;
        private List<Method> onEnabledPlayControlSelected;
        private byte onEnabledPlayControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledPlayControlSelectedBuildStage = (byte) 0;
            this.onDisabledPlayControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutablePlayControlStateElement.requireNonNull(ImmutablePlayControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledPlayControlSelected() {
            byte b = this.onEnabledPlayControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledPlayControlSelectedBuildStage = (byte) -1;
                this.onEnabledPlayControlSelected = ImmutablePlayControlStateElement.createUnmodifiableList(false, ImmutablePlayControlStateElement.createSafeList(ImmutablePlayControlStateElement.super.onEnabledPlayControlSelected(), true, false));
                this.onEnabledPlayControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledPlayControlSelected;
        }

        void onEnabledPlayControlSelected(List<Method> list) {
            this.onEnabledPlayControlSelected = list;
            this.onEnabledPlayControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledPlayControlSelected() {
            byte b = this.onDisabledPlayControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledPlayControlSelectedBuildStage = (byte) -1;
                this.onDisabledPlayControlSelected = ImmutablePlayControlStateElement.createUnmodifiableList(false, ImmutablePlayControlStateElement.createSafeList(ImmutablePlayControlStateElement.super.onDisabledPlayControlSelected(), true, false));
                this.onDisabledPlayControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledPlayControlSelected;
        }

        void onDisabledPlayControlSelected(List<Method> list) {
            this.onDisabledPlayControlSelected = list;
            this.onDisabledPlayControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledPlayControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledPlayControlSelected");
            }
            if (this.onDisabledPlayControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledPlayControlSelected");
            }
            return "Cannot build PlayControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.PlayControlStateElement
    @JsonProperty("descriptions")
    public Map<PlayControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.PlayControlStateElement
    @JsonProperty("altTexts")
    public Map<PlayControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.PlayControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.PlayControlStateElement
    @JsonProperty("onEnabledPlayControlSelected")
    public List<Method> onEnabledPlayControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledPlayControlSelected();
        }
        return this.onEnabledPlayControlSelected;
    }

    @Override // PlaybackInterface.v1_0.PlayControlStateElement
    @JsonProperty("onDisabledPlayControlSelected")
    public List<Method> onDisabledPlayControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledPlayControlSelected();
        }
        return this.onDisabledPlayControlSelected;
    }

    public final ImmutablePlayControlStateElement withDescriptions(Map<PlayControlStateElement.State, ? extends String> map) {
        return this.descriptions == map ? this : new ImmutablePlayControlStateElement(createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.onEnabledPlayControlSelected, this.onDisabledPlayControlSelected);
    }

    public final ImmutablePlayControlStateElement withAltTexts(Map<PlayControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutablePlayControlStateElement(this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.onEnabledPlayControlSelected, this.onDisabledPlayControlSelected);
    }

    public final ImmutablePlayControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutablePlayControlStateElement(this.descriptions, this.altTexts, bool2, this.onEnabledPlayControlSelected, this.onDisabledPlayControlSelected);
    }

    public final ImmutablePlayControlStateElement withOnEnabledPlayControlSelected(Method... methodArr) {
        return new ImmutablePlayControlStateElement(this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledPlayControlSelected);
    }

    public final ImmutablePlayControlStateElement withOnEnabledPlayControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledPlayControlSelected == iterable) {
            return this;
        }
        return new ImmutablePlayControlStateElement(this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledPlayControlSelected);
    }

    public final ImmutablePlayControlStateElement withOnDisabledPlayControlSelected(Method... methodArr) {
        return new ImmutablePlayControlStateElement(this.descriptions, this.altTexts, this.isDisabled, this.onEnabledPlayControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePlayControlStateElement withOnDisabledPlayControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledPlayControlSelected == iterable) {
            return this;
        }
        return new ImmutablePlayControlStateElement(this.descriptions, this.altTexts, this.isDisabled, this.onEnabledPlayControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlayControlStateElement) && equalTo(0, (ImmutablePlayControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlayControlStateElement immutablePlayControlStateElement) {
        return this.descriptions.equals(immutablePlayControlStateElement.descriptions) && this.altTexts.equals(immutablePlayControlStateElement.altTexts) && this.isDisabled.equals(immutablePlayControlStateElement.isDisabled) && this.onEnabledPlayControlSelected.equals(immutablePlayControlStateElement.onEnabledPlayControlSelected) && this.onDisabledPlayControlSelected.equals(immutablePlayControlStateElement.onDisabledPlayControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.descriptions.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altTexts.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onEnabledPlayControlSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onDisabledPlayControlSelected.hashCode();
    }

    public String toString() {
        return "PlayControlStateElement{descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", onEnabledPlayControlSelected=" + this.onEnabledPlayControlSelected + ", onDisabledPlayControlSelected=" + this.onDisabledPlayControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlayControlStateElement", generator = "Immutables")
    static final class Json extends PlayControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledPlayControlSelectedIsSet;
        boolean onEnabledPlayControlSelectedIsSet;

        @Nullable
        Map<PlayControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<PlayControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onEnabledPlayControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledPlayControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<PlayControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<PlayControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledPlayControlSelected")
        public void setOnEnabledPlayControlSelected(List<Method> list) {
            this.onEnabledPlayControlSelected = list;
            this.onEnabledPlayControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledPlayControlSelected")
        public void setOnDisabledPlayControlSelected(List<Method> list) {
            this.onDisabledPlayControlSelected = list;
            this.onDisabledPlayControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.PlayControlStateElement
        public Map<PlayControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayControlStateElement
        public Map<PlayControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayControlStateElement
        public List<Method> onEnabledPlayControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.PlayControlStateElement
        public List<Method> onDisabledPlayControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlayControlStateElement fromJson(Json json) {
        Builder builder = builder();
        Map<PlayControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<PlayControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledPlayControlSelectedIsSet) {
            builder.addAllOnEnabledPlayControlSelected(json.onEnabledPlayControlSelected);
        }
        if (json.onDisabledPlayControlSelectedIsSet) {
            builder.addAllOnDisabledPlayControlSelected(json.onDisabledPlayControlSelected);
        }
        return builder.build();
    }

    public static ImmutablePlayControlStateElement copyOf(PlayControlStateElement playControlStateElement) {
        if (playControlStateElement instanceof ImmutablePlayControlStateElement) {
            return (ImmutablePlayControlStateElement) playControlStateElement;
        }
        return builder().from(playControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlayControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_DISABLED_PLAY_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_PLAY_CONTROL_SELECTED = 1;
        private EnumMap<PlayControlStateElement.State, String> altTexts;
        private EnumMap<PlayControlStateElement.State, String> descriptions;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledPlayControlSelected;
        private List<Method> onEnabledPlayControlSelected;
        private long optBits;

        private Builder() {
            this.descriptions = new EnumMap<>(PlayControlStateElement.State.class);
            this.altTexts = new EnumMap<>(PlayControlStateElement.State.class);
            this.onEnabledPlayControlSelected = new ArrayList();
            this.onDisabledPlayControlSelected = new ArrayList();
        }

        public final Builder from(PlayControlStateElement playControlStateElement) {
            ImmutablePlayControlStateElement.requireNonNull(playControlStateElement, "instance");
            putAllDescriptions(playControlStateElement.descriptions());
            putAllAltTexts(playControlStateElement.altTexts());
            isDisabled(playControlStateElement.isDisabled());
            addAllOnEnabledPlayControlSelected(playControlStateElement.onEnabledPlayControlSelected());
            addAllOnDisabledPlayControlSelected(playControlStateElement.onDisabledPlayControlSelected());
            return this;
        }

        public final Builder putDescriptions(PlayControlStateElement.State state, String str) {
            this.descriptions.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutablePlayControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<PlayControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutablePlayControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<PlayControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<PlayControlStateElement.State, ? extends String> map) {
            for (Map.Entry<PlayControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutablePlayControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(PlayControlStateElement.State state, String str) {
            this.altTexts.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutablePlayControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<PlayControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutablePlayControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<PlayControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<PlayControlStateElement.State, ? extends String> map) {
            for (Map.Entry<PlayControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((PlayControlStateElement.State) ImmutablePlayControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutablePlayControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutablePlayControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledPlayControlSelected(Method method) {
            this.onEnabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(method, "onEnabledPlayControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledPlayControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(method, "onEnabledPlayControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledPlayControlSelected")
        public final Builder onEnabledPlayControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledPlayControlSelected.clear();
            return addAllOnEnabledPlayControlSelected(iterable);
        }

        public final Builder addAllOnEnabledPlayControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(it.next(), "onEnabledPlayControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledPlayControlSelected(Method method) {
            this.onDisabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(method, "onDisabledPlayControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledPlayControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(method, "onDisabledPlayControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledPlayControlSelected")
        public final Builder onDisabledPlayControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledPlayControlSelected.clear();
            return addAllOnDisabledPlayControlSelected(iterable);
        }

        public final Builder addAllOnDisabledPlayControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledPlayControlSelected.add((Method) ImmutablePlayControlStateElement.requireNonNull(it.next(), "onDisabledPlayControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutablePlayControlStateElement build() {
            return new ImmutablePlayControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledPlayControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledPlayControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
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
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }

    private static <K extends Enum<K>, V> Map<K, V> createUnmodifiableEnumMap(boolean z, boolean z2, Map<K, ? extends V> map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        EnumMap enumMap = new EnumMap(map);
        if (z || z2) {
            Iterator<? extends V> it = map.values().iterator();
            while (it.hasNext()) {
                V next = it.next();
                if (z) {
                    requireNonNull(next, "value");
                } else if (z2 && next == null) {
                    it.remove();
                }
            }
        }
        return Collections.unmodifiableMap(enumMap);
    }
}
