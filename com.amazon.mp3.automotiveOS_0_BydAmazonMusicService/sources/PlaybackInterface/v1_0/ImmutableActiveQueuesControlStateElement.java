package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.ActiveQueuesControlStateElement;
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
@Generated(from = "ActiveQueuesControlStateElement", generator = "Immutables")
public final class ImmutableActiveQueuesControlStateElement extends ActiveQueuesControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<ActiveQueuesControlStateElement.State, String> altTexts;
    private final Map<ActiveQueuesControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onCloseSelected;
    private final List<Method> onOpenSelected;

    private ImmutableActiveQueuesControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onOpenSelectedIsSet()) {
            this.initShim.onOpenSelected(createUnmodifiableList(true, builder.onOpenSelected));
        }
        if (builder.onCloseSelectedIsSet()) {
            this.initShim.onCloseSelected(createUnmodifiableList(true, builder.onCloseSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onOpenSelected = this.initShim.onOpenSelected();
        this.onCloseSelected = this.initShim.onCloseSelected();
        this.initShim = null;
    }

    private ImmutableActiveQueuesControlStateElement(Map<ActiveQueuesControlStateElement.State, String> map, Map<ActiveQueuesControlStateElement.State, String> map2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.onOpenSelected = list;
        this.onCloseSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "ActiveQueuesControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onCloseSelected;
        private byte onCloseSelectedBuildStage;
        private List<Method> onOpenSelected;
        private byte onOpenSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onOpenSelectedBuildStage = (byte) 0;
            this.onCloseSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableActiveQueuesControlStateElement.requireNonNull(ImmutableActiveQueuesControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onOpenSelected() {
            byte b = this.onOpenSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onOpenSelectedBuildStage = (byte) -1;
                this.onOpenSelected = ImmutableActiveQueuesControlStateElement.createUnmodifiableList(false, ImmutableActiveQueuesControlStateElement.createSafeList(ImmutableActiveQueuesControlStateElement.super.onOpenSelected(), true, false));
                this.onOpenSelectedBuildStage = (byte) 1;
            }
            return this.onOpenSelected;
        }

        void onOpenSelected(List<Method> list) {
            this.onOpenSelected = list;
            this.onOpenSelectedBuildStage = (byte) 1;
        }

        List<Method> onCloseSelected() {
            byte b = this.onCloseSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCloseSelectedBuildStage = (byte) -1;
                this.onCloseSelected = ImmutableActiveQueuesControlStateElement.createUnmodifiableList(false, ImmutableActiveQueuesControlStateElement.createSafeList(ImmutableActiveQueuesControlStateElement.super.onCloseSelected(), true, false));
                this.onCloseSelectedBuildStage = (byte) 1;
            }
            return this.onCloseSelected;
        }

        void onCloseSelected(List<Method> list) {
            this.onCloseSelected = list;
            this.onCloseSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onOpenSelectedBuildStage == -1) {
                arrayList.add("onOpenSelected");
            }
            if (this.onCloseSelectedBuildStage == -1) {
                arrayList.add("onCloseSelected");
            }
            return "Cannot build ActiveQueuesControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
    @JsonProperty("descriptions")
    public Map<ActiveQueuesControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
    @JsonProperty("altTexts")
    public Map<ActiveQueuesControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
    @JsonProperty("onOpenSelected")
    public List<Method> onOpenSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onOpenSelected();
        }
        return this.onOpenSelected;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
    @JsonProperty("onCloseSelected")
    public List<Method> onCloseSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCloseSelected();
        }
        return this.onCloseSelected;
    }

    public final ImmutableActiveQueuesControlStateElement withDescriptions(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
        return this.descriptions == map ? this : new ImmutableActiveQueuesControlStateElement(createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.onOpenSelected, this.onCloseSelected);
    }

    public final ImmutableActiveQueuesControlStateElement withAltTexts(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutableActiveQueuesControlStateElement(this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.onOpenSelected, this.onCloseSelected);
    }

    public final ImmutableActiveQueuesControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableActiveQueuesControlStateElement(this.descriptions, this.altTexts, bool2, this.onOpenSelected, this.onCloseSelected);
    }

    public final ImmutableActiveQueuesControlStateElement withOnOpenSelected(Method... methodArr) {
        return new ImmutableActiveQueuesControlStateElement(this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onCloseSelected);
    }

    public final ImmutableActiveQueuesControlStateElement withOnOpenSelected(Iterable<? extends Method> iterable) {
        if (this.onOpenSelected == iterable) {
            return this;
        }
        return new ImmutableActiveQueuesControlStateElement(this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onCloseSelected);
    }

    public final ImmutableActiveQueuesControlStateElement withOnCloseSelected(Method... methodArr) {
        return new ImmutableActiveQueuesControlStateElement(this.descriptions, this.altTexts, this.isDisabled, this.onOpenSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableActiveQueuesControlStateElement withOnCloseSelected(Iterable<? extends Method> iterable) {
        if (this.onCloseSelected == iterable) {
            return this;
        }
        return new ImmutableActiveQueuesControlStateElement(this.descriptions, this.altTexts, this.isDisabled, this.onOpenSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableActiveQueuesControlStateElement) && equalTo(0, (ImmutableActiveQueuesControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableActiveQueuesControlStateElement immutableActiveQueuesControlStateElement) {
        return this.descriptions.equals(immutableActiveQueuesControlStateElement.descriptions) && this.altTexts.equals(immutableActiveQueuesControlStateElement.altTexts) && this.isDisabled.equals(immutableActiveQueuesControlStateElement.isDisabled) && this.onOpenSelected.equals(immutableActiveQueuesControlStateElement.onOpenSelected) && this.onCloseSelected.equals(immutableActiveQueuesControlStateElement.onCloseSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.descriptions.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.altTexts.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.isDisabled.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onOpenSelected.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.onCloseSelected.hashCode();
    }

    public String toString() {
        return "ActiveQueuesControlStateElement{descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", onOpenSelected=" + this.onOpenSelected + ", onCloseSelected=" + this.onCloseSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ActiveQueuesControlStateElement", generator = "Immutables")
    static final class Json extends ActiveQueuesControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onCloseSelectedIsSet;
        boolean onOpenSelectedIsSet;

        @Nullable
        Map<ActiveQueuesControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<ActiveQueuesControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onOpenSelected = Collections.emptyList();

        @Nullable
        List<Method> onCloseSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<ActiveQueuesControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<ActiveQueuesControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onOpenSelected")
        public void setOnOpenSelected(List<Method> list) {
            this.onOpenSelected = list;
            this.onOpenSelectedIsSet = true;
        }

        @JsonProperty("onCloseSelected")
        public void setOnCloseSelected(List<Method> list) {
            this.onCloseSelected = list;
            this.onCloseSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
        public Map<ActiveQueuesControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
        public Map<ActiveQueuesControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
        public List<Method> onOpenSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesControlStateElement
        public List<Method> onCloseSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableActiveQueuesControlStateElement fromJson(Json json) {
        Builder builder = builder();
        Map<ActiveQueuesControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<ActiveQueuesControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onOpenSelectedIsSet) {
            builder.addAllOnOpenSelected(json.onOpenSelected);
        }
        if (json.onCloseSelectedIsSet) {
            builder.addAllOnCloseSelected(json.onCloseSelected);
        }
        return builder.build();
    }

    public static ImmutableActiveQueuesControlStateElement copyOf(ActiveQueuesControlStateElement activeQueuesControlStateElement) {
        if (activeQueuesControlStateElement instanceof ImmutableActiveQueuesControlStateElement) {
            return (ImmutableActiveQueuesControlStateElement) activeQueuesControlStateElement;
        }
        return builder().from(activeQueuesControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ActiveQueuesControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_CLOSE_SELECTED = 2;
        private static final long OPT_BIT_ON_OPEN_SELECTED = 1;
        private EnumMap<ActiveQueuesControlStateElement.State, String> altTexts;
        private EnumMap<ActiveQueuesControlStateElement.State, String> descriptions;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onCloseSelected;
        private List<Method> onOpenSelected;
        private long optBits;

        private Builder() {
            this.descriptions = new EnumMap<>(ActiveQueuesControlStateElement.State.class);
            this.altTexts = new EnumMap<>(ActiveQueuesControlStateElement.State.class);
            this.onOpenSelected = new ArrayList();
            this.onCloseSelected = new ArrayList();
        }

        public final Builder from(ActiveQueuesControlStateElement activeQueuesControlStateElement) {
            ImmutableActiveQueuesControlStateElement.requireNonNull(activeQueuesControlStateElement, "instance");
            putAllDescriptions(activeQueuesControlStateElement.descriptions());
            putAllAltTexts(activeQueuesControlStateElement.altTexts());
            isDisabled(activeQueuesControlStateElement.isDisabled());
            addAllOnOpenSelected(activeQueuesControlStateElement.onOpenSelected());
            addAllOnCloseSelected(activeQueuesControlStateElement.onCloseSelected());
            return this;
        }

        public final Builder putDescriptions(ActiveQueuesControlStateElement.State state, String str) {
            this.descriptions.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<ActiveQueuesControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ActiveQueuesControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(ActiveQueuesControlStateElement.State state, String str) {
            this.altTexts.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<ActiveQueuesControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<ActiveQueuesControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ActiveQueuesControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((ActiveQueuesControlStateElement.State) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableActiveQueuesControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableActiveQueuesControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnOpenSelected(Method method) {
            this.onOpenSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(method, "onOpenSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnOpenSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onOpenSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(method, "onOpenSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onOpenSelected")
        public final Builder onOpenSelected(Iterable<? extends Method> iterable) {
            this.onOpenSelected.clear();
            return addAllOnOpenSelected(iterable);
        }

        public final Builder addAllOnOpenSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onOpenSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(it.next(), "onOpenSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCloseSelected(Method method) {
            this.onCloseSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(method, "onCloseSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnCloseSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCloseSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(method, "onCloseSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onCloseSelected")
        public final Builder onCloseSelected(Iterable<? extends Method> iterable) {
            this.onCloseSelected.clear();
            return addAllOnCloseSelected(iterable);
        }

        public final Builder addAllOnCloseSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCloseSelected.add((Method) ImmutableActiveQueuesControlStateElement.requireNonNull(it.next(), "onCloseSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableActiveQueuesControlStateElement build() {
            return new ImmutableActiveQueuesControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onOpenSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCloseSelectedIsSet() {
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
