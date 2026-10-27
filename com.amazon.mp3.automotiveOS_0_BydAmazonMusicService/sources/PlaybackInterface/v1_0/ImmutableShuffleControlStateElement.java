package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.ShuffleControlStateElement;
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
@Generated(from = "ShuffleControlStateElement", generator = "Immutables")
public final class ImmutableShuffleControlStateElement extends ShuffleControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<ShuffleControlStateElement.State, String> altTexts;
    private final Map<ShuffleControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledShuffleControlSelected;
    private final List<Method> onEnabledShuffleControlSelected;
    private final ShuffleControlStateElement.State state;

    private ImmutableShuffleControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.state = builder.state;
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledShuffleControlSelectedIsSet()) {
            this.initShim.onEnabledShuffleControlSelected(createUnmodifiableList(true, builder.onEnabledShuffleControlSelected));
        }
        if (builder.onDisabledShuffleControlSelectedIsSet()) {
            this.initShim.onDisabledShuffleControlSelected(createUnmodifiableList(true, builder.onDisabledShuffleControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledShuffleControlSelected = this.initShim.onEnabledShuffleControlSelected();
        this.onDisabledShuffleControlSelected = this.initShim.onDisabledShuffleControlSelected();
        this.initShim = null;
    }

    private ImmutableShuffleControlStateElement(ShuffleControlStateElement.State state, Map<ShuffleControlStateElement.State, String> map, Map<ShuffleControlStateElement.State, String> map2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.state = state;
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.onEnabledShuffleControlSelected = list;
        this.onDisabledShuffleControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "ShuffleControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledShuffleControlSelected;
        private byte onDisabledShuffleControlSelectedBuildStage;
        private List<Method> onEnabledShuffleControlSelected;
        private byte onEnabledShuffleControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledShuffleControlSelectedBuildStage = (byte) 0;
            this.onDisabledShuffleControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableShuffleControlStateElement.requireNonNull(ImmutableShuffleControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledShuffleControlSelected() {
            byte b = this.onEnabledShuffleControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledShuffleControlSelectedBuildStage = (byte) -1;
                this.onEnabledShuffleControlSelected = ImmutableShuffleControlStateElement.createUnmodifiableList(false, ImmutableShuffleControlStateElement.createSafeList(ImmutableShuffleControlStateElement.super.onEnabledShuffleControlSelected(), true, false));
                this.onEnabledShuffleControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledShuffleControlSelected;
        }

        void onEnabledShuffleControlSelected(List<Method> list) {
            this.onEnabledShuffleControlSelected = list;
            this.onEnabledShuffleControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledShuffleControlSelected() {
            byte b = this.onDisabledShuffleControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledShuffleControlSelectedBuildStage = (byte) -1;
                this.onDisabledShuffleControlSelected = ImmutableShuffleControlStateElement.createUnmodifiableList(false, ImmutableShuffleControlStateElement.createSafeList(ImmutableShuffleControlStateElement.super.onDisabledShuffleControlSelected(), true, false));
                this.onDisabledShuffleControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledShuffleControlSelected;
        }

        void onDisabledShuffleControlSelected(List<Method> list) {
            this.onDisabledShuffleControlSelected = list;
            this.onDisabledShuffleControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledShuffleControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledShuffleControlSelected");
            }
            if (this.onDisabledShuffleControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledShuffleControlSelected");
            }
            return "Cannot build ShuffleControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("state")
    public ShuffleControlStateElement.State state() {
        return this.state;
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("descriptions")
    public Map<ShuffleControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("altTexts")
    public Map<ShuffleControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("onEnabledShuffleControlSelected")
    public List<Method> onEnabledShuffleControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledShuffleControlSelected();
        }
        return this.onEnabledShuffleControlSelected;
    }

    @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
    @JsonProperty("onDisabledShuffleControlSelected")
    public List<Method> onDisabledShuffleControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledShuffleControlSelected();
        }
        return this.onDisabledShuffleControlSelected;
    }

    public final ImmutableShuffleControlStateElement withState(ShuffleControlStateElement.State state) {
        ShuffleControlStateElement.State state2 = (ShuffleControlStateElement.State) requireNonNull(state, "state");
        return this.state == state2 ? this : new ImmutableShuffleControlStateElement(state2, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledShuffleControlSelected, this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withDescriptions(Map<ShuffleControlStateElement.State, ? extends String> map) {
        if (this.descriptions == map) {
            return this;
        }
        return new ImmutableShuffleControlStateElement(this.state, createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.onEnabledShuffleControlSelected, this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withAltTexts(Map<ShuffleControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutableShuffleControlStateElement(this.state, this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.onEnabledShuffleControlSelected, this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableShuffleControlStateElement(this.state, this.descriptions, this.altTexts, bool2, this.onEnabledShuffleControlSelected, this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withOnEnabledShuffleControlSelected(Method... methodArr) {
        return new ImmutableShuffleControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withOnEnabledShuffleControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledShuffleControlSelected == iterable) {
            return this;
        }
        return new ImmutableShuffleControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledShuffleControlSelected);
    }

    public final ImmutableShuffleControlStateElement withOnDisabledShuffleControlSelected(Method... methodArr) {
        return new ImmutableShuffleControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledShuffleControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableShuffleControlStateElement withOnDisabledShuffleControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledShuffleControlSelected == iterable) {
            return this;
        }
        return new ImmutableShuffleControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledShuffleControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableShuffleControlStateElement) && equalTo(0, (ImmutableShuffleControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableShuffleControlStateElement immutableShuffleControlStateElement) {
        return this.state.equals(immutableShuffleControlStateElement.state) && this.descriptions.equals(immutableShuffleControlStateElement.descriptions) && this.altTexts.equals(immutableShuffleControlStateElement.altTexts) && this.isDisabled.equals(immutableShuffleControlStateElement.isDisabled) && this.onEnabledShuffleControlSelected.equals(immutableShuffleControlStateElement.onEnabledShuffleControlSelected) && this.onDisabledShuffleControlSelected.equals(immutableShuffleControlStateElement.onDisabledShuffleControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.state.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.descriptions.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.altTexts.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.isDisabled.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onEnabledShuffleControlSelected.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.onDisabledShuffleControlSelected.hashCode();
    }

    public String toString() {
        return "ShuffleControlStateElement{state=" + this.state + ", descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", onEnabledShuffleControlSelected=" + this.onEnabledShuffleControlSelected + ", onDisabledShuffleControlSelected=" + this.onDisabledShuffleControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ShuffleControlStateElement", generator = "Immutables")
    static final class Json extends ShuffleControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledShuffleControlSelectedIsSet;
        boolean onEnabledShuffleControlSelectedIsSet;

        @Nullable
        ShuffleControlStateElement.State state;

        @Nullable
        Map<ShuffleControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<ShuffleControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onEnabledShuffleControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledShuffleControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("state")
        public void setState(ShuffleControlStateElement.State state) {
            this.state = state;
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<ShuffleControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<ShuffleControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledShuffleControlSelected")
        public void setOnEnabledShuffleControlSelected(List<Method> list) {
            this.onEnabledShuffleControlSelected = list;
            this.onEnabledShuffleControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledShuffleControlSelected")
        public void setOnDisabledShuffleControlSelected(List<Method> list) {
            this.onDisabledShuffleControlSelected = list;
            this.onDisabledShuffleControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public ShuffleControlStateElement.State state() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public Map<ShuffleControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public Map<ShuffleControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public List<Method> onEnabledShuffleControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ShuffleControlStateElement
        public List<Method> onDisabledShuffleControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableShuffleControlStateElement fromJson(Json json) {
        Builder builder = builder();
        ShuffleControlStateElement.State state = json.state;
        if (state != null) {
            builder.state(state);
        }
        Map<ShuffleControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<ShuffleControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledShuffleControlSelectedIsSet) {
            builder.addAllOnEnabledShuffleControlSelected(json.onEnabledShuffleControlSelected);
        }
        if (json.onDisabledShuffleControlSelectedIsSet) {
            builder.addAllOnDisabledShuffleControlSelected(json.onDisabledShuffleControlSelected);
        }
        return builder.build();
    }

    public static ImmutableShuffleControlStateElement copyOf(ShuffleControlStateElement shuffleControlStateElement) {
        if (shuffleControlStateElement instanceof ImmutableShuffleControlStateElement) {
            return (ImmutableShuffleControlStateElement) shuffleControlStateElement;
        }
        return builder().from(shuffleControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ShuffleControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_STATE = 1;
        private static final long OPT_BIT_ON_DISABLED_SHUFFLE_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_SHUFFLE_CONTROL_SELECTED = 1;
        private EnumMap<ShuffleControlStateElement.State, String> altTexts;
        private EnumMap<ShuffleControlStateElement.State, String> descriptions;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledShuffleControlSelected;
        private List<Method> onEnabledShuffleControlSelected;
        private long optBits;

        @Nullable
        private ShuffleControlStateElement.State state;

        private Builder() {
            this.initBits = 1L;
            this.descriptions = new EnumMap<>(ShuffleControlStateElement.State.class);
            this.altTexts = new EnumMap<>(ShuffleControlStateElement.State.class);
            this.onEnabledShuffleControlSelected = new ArrayList();
            this.onDisabledShuffleControlSelected = new ArrayList();
        }

        public final Builder from(ShuffleControlStateElement shuffleControlStateElement) {
            ImmutableShuffleControlStateElement.requireNonNull(shuffleControlStateElement, "instance");
            state(shuffleControlStateElement.state());
            putAllDescriptions(shuffleControlStateElement.descriptions());
            putAllAltTexts(shuffleControlStateElement.altTexts());
            isDisabled(shuffleControlStateElement.isDisabled());
            addAllOnEnabledShuffleControlSelected(shuffleControlStateElement.onEnabledShuffleControlSelected());
            addAllOnDisabledShuffleControlSelected(shuffleControlStateElement.onDisabledShuffleControlSelected());
            return this;
        }

        @JsonProperty("state")
        public final Builder state(ShuffleControlStateElement.State state) {
            this.state = (ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(state, "state");
            this.initBits &= -2;
            return this;
        }

        public final Builder putDescriptions(ShuffleControlStateElement.State state, String str) {
            this.descriptions.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutableShuffleControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<ShuffleControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableShuffleControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<ShuffleControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<ShuffleControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ShuffleControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableShuffleControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(ShuffleControlStateElement.State state, String str) {
            this.altTexts.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutableShuffleControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<ShuffleControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableShuffleControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<ShuffleControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<ShuffleControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ShuffleControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((ShuffleControlStateElement.State) ImmutableShuffleControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableShuffleControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableShuffleControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledShuffleControlSelected(Method method) {
            this.onEnabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(method, "onEnabledShuffleControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledShuffleControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(method, "onEnabledShuffleControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledShuffleControlSelected")
        public final Builder onEnabledShuffleControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledShuffleControlSelected.clear();
            return addAllOnEnabledShuffleControlSelected(iterable);
        }

        public final Builder addAllOnEnabledShuffleControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(it.next(), "onEnabledShuffleControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledShuffleControlSelected(Method method) {
            this.onDisabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(method, "onDisabledShuffleControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledShuffleControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(method, "onDisabledShuffleControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledShuffleControlSelected")
        public final Builder onDisabledShuffleControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledShuffleControlSelected.clear();
            return addAllOnDisabledShuffleControlSelected(iterable);
        }

        public final Builder addAllOnDisabledShuffleControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledShuffleControlSelected.add((Method) ImmutableShuffleControlStateElement.requireNonNull(it.next(), "onDisabledShuffleControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableShuffleControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableShuffleControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledShuffleControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledShuffleControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("state");
            }
            return "Cannot build ShuffleControlStateElement, some of required attributes are not set " + arrayList;
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
