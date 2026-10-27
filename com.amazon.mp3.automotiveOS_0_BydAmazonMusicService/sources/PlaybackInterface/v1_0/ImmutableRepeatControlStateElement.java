package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.RepeatControlStateElement;
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
@Generated(from = "RepeatControlStateElement", generator = "Immutables")
public final class ImmutableRepeatControlStateElement extends RepeatControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<RepeatControlStateElement.State, String> altTexts;
    private final Map<RepeatControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final Boolean isRepeatAllDisabled;
    private final List<Method> onDisabledRepeatControlSelected;
    private final List<Method> onEnabledRepeatControlSelected;
    private final RepeatControlStateElement.State state;

    private ImmutableRepeatControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.state = builder.state;
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.isRepeatAllDisabled != null) {
            this.initShim.isRepeatAllDisabled(builder.isRepeatAllDisabled);
        }
        if (builder.onEnabledRepeatControlSelectedIsSet()) {
            this.initShim.onEnabledRepeatControlSelected(createUnmodifiableList(true, builder.onEnabledRepeatControlSelected));
        }
        if (builder.onDisabledRepeatControlSelectedIsSet()) {
            this.initShim.onDisabledRepeatControlSelected(createUnmodifiableList(true, builder.onDisabledRepeatControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.isRepeatAllDisabled = this.initShim.isRepeatAllDisabled();
        this.onEnabledRepeatControlSelected = this.initShim.onEnabledRepeatControlSelected();
        this.onDisabledRepeatControlSelected = this.initShim.onDisabledRepeatControlSelected();
        this.initShim = null;
    }

    private ImmutableRepeatControlStateElement(RepeatControlStateElement.State state, Map<RepeatControlStateElement.State, String> map, Map<RepeatControlStateElement.State, String> map2, Boolean bool, Boolean bool2, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.state = state;
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.isRepeatAllDisabled = bool2;
        this.onEnabledRepeatControlSelected = list;
        this.onDisabledRepeatControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "RepeatControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private Boolean isRepeatAllDisabled;
        private byte isRepeatAllDisabledBuildStage;
        private List<Method> onDisabledRepeatControlSelected;
        private byte onDisabledRepeatControlSelectedBuildStage;
        private List<Method> onEnabledRepeatControlSelected;
        private byte onEnabledRepeatControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.isRepeatAllDisabledBuildStage = (byte) 0;
            this.onEnabledRepeatControlSelectedBuildStage = (byte) 0;
            this.onDisabledRepeatControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableRepeatControlStateElement.requireNonNull(ImmutableRepeatControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        Boolean isRepeatAllDisabled() {
            byte b = this.isRepeatAllDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isRepeatAllDisabledBuildStage = (byte) -1;
                this.isRepeatAllDisabled = (Boolean) ImmutableRepeatControlStateElement.requireNonNull(ImmutableRepeatControlStateElement.super.isRepeatAllDisabled(), "isRepeatAllDisabled");
                this.isRepeatAllDisabledBuildStage = (byte) 1;
            }
            return this.isRepeatAllDisabled;
        }

        void isRepeatAllDisabled(Boolean bool) {
            this.isRepeatAllDisabled = bool;
            this.isRepeatAllDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledRepeatControlSelected() {
            byte b = this.onEnabledRepeatControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledRepeatControlSelectedBuildStage = (byte) -1;
                this.onEnabledRepeatControlSelected = ImmutableRepeatControlStateElement.createUnmodifiableList(false, ImmutableRepeatControlStateElement.createSafeList(ImmutableRepeatControlStateElement.super.onEnabledRepeatControlSelected(), true, false));
                this.onEnabledRepeatControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledRepeatControlSelected;
        }

        void onEnabledRepeatControlSelected(List<Method> list) {
            this.onEnabledRepeatControlSelected = list;
            this.onEnabledRepeatControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledRepeatControlSelected() {
            byte b = this.onDisabledRepeatControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledRepeatControlSelectedBuildStage = (byte) -1;
                this.onDisabledRepeatControlSelected = ImmutableRepeatControlStateElement.createUnmodifiableList(false, ImmutableRepeatControlStateElement.createSafeList(ImmutableRepeatControlStateElement.super.onDisabledRepeatControlSelected(), true, false));
                this.onDisabledRepeatControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledRepeatControlSelected;
        }

        void onDisabledRepeatControlSelected(List<Method> list) {
            this.onDisabledRepeatControlSelected = list;
            this.onDisabledRepeatControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.isRepeatAllDisabledBuildStage == -1) {
                arrayList.add("isRepeatAllDisabled");
            }
            if (this.onEnabledRepeatControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledRepeatControlSelected");
            }
            if (this.onDisabledRepeatControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledRepeatControlSelected");
            }
            return "Cannot build RepeatControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("state")
    public RepeatControlStateElement.State state() {
        return this.state;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("descriptions")
    public Map<RepeatControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("altTexts")
    public Map<RepeatControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("isRepeatAllDisabled")
    public Boolean isRepeatAllDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isRepeatAllDisabled();
        }
        return this.isRepeatAllDisabled;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("onEnabledRepeatControlSelected")
    public List<Method> onEnabledRepeatControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledRepeatControlSelected();
        }
        return this.onEnabledRepeatControlSelected;
    }

    @Override // PlaybackInterface.v1_0.RepeatControlStateElement
    @JsonProperty("onDisabledRepeatControlSelected")
    public List<Method> onDisabledRepeatControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledRepeatControlSelected();
        }
        return this.onDisabledRepeatControlSelected;
    }

    public final ImmutableRepeatControlStateElement withState(RepeatControlStateElement.State state) {
        RepeatControlStateElement.State state2 = (RepeatControlStateElement.State) requireNonNull(state, "state");
        return this.state == state2 ? this : new ImmutableRepeatControlStateElement(state2, this.descriptions, this.altTexts, this.isDisabled, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withDescriptions(Map<RepeatControlStateElement.State, ? extends String> map) {
        if (this.descriptions == map) {
            return this;
        }
        return new ImmutableRepeatControlStateElement(this.state, createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withAltTexts(Map<RepeatControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutableRepeatControlStateElement(this.state, this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, bool2, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withIsRepeatAllDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isRepeatAllDisabled");
        return this.isRepeatAllDisabled.equals(bool2) ? this : new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, bool2, this.onEnabledRepeatControlSelected, this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withOnEnabledRepeatControlSelected(Method... methodArr) {
        return new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.isRepeatAllDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withOnEnabledRepeatControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledRepeatControlSelected == iterable) {
            return this;
        }
        return new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.isRepeatAllDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledRepeatControlSelected);
    }

    public final ImmutableRepeatControlStateElement withOnDisabledRepeatControlSelected(Method... methodArr) {
        return new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableRepeatControlStateElement withOnDisabledRepeatControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledRepeatControlSelected == iterable) {
            return this;
        }
        return new ImmutableRepeatControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.isRepeatAllDisabled, this.onEnabledRepeatControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRepeatControlStateElement) && equalTo(0, (ImmutableRepeatControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableRepeatControlStateElement immutableRepeatControlStateElement) {
        return this.state.equals(immutableRepeatControlStateElement.state) && this.descriptions.equals(immutableRepeatControlStateElement.descriptions) && this.altTexts.equals(immutableRepeatControlStateElement.altTexts) && this.isDisabled.equals(immutableRepeatControlStateElement.isDisabled) && this.isRepeatAllDisabled.equals(immutableRepeatControlStateElement.isRepeatAllDisabled) && this.onEnabledRepeatControlSelected.equals(immutableRepeatControlStateElement.onEnabledRepeatControlSelected) && this.onDisabledRepeatControlSelected.equals(immutableRepeatControlStateElement.onDisabledRepeatControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.state.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.descriptions.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.altTexts.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.isDisabled.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.isRepeatAllDisabled.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onEnabledRepeatControlSelected.hashCode();
        return iHashCode6 + (iHashCode6 << 5) + this.onDisabledRepeatControlSelected.hashCode();
    }

    public String toString() {
        return "RepeatControlStateElement{state=" + this.state + ", descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", isRepeatAllDisabled=" + this.isRepeatAllDisabled + ", onEnabledRepeatControlSelected=" + this.onEnabledRepeatControlSelected + ", onDisabledRepeatControlSelected=" + this.onDisabledRepeatControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RepeatControlStateElement", generator = "Immutables")
    static final class Json extends RepeatControlStateElement {

        @Nullable
        Boolean isDisabled;

        @Nullable
        Boolean isRepeatAllDisabled;
        boolean onDisabledRepeatControlSelectedIsSet;
        boolean onEnabledRepeatControlSelectedIsSet;

        @Nullable
        RepeatControlStateElement.State state;

        @Nullable
        Map<RepeatControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<RepeatControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onEnabledRepeatControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledRepeatControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("state")
        public void setState(RepeatControlStateElement.State state) {
            this.state = state;
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<RepeatControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<RepeatControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("isRepeatAllDisabled")
        public void setIsRepeatAllDisabled(Boolean bool) {
            this.isRepeatAllDisabled = bool;
        }

        @JsonProperty("onEnabledRepeatControlSelected")
        public void setOnEnabledRepeatControlSelected(List<Method> list) {
            this.onEnabledRepeatControlSelected = list;
            this.onEnabledRepeatControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledRepeatControlSelected")
        public void setOnDisabledRepeatControlSelected(List<Method> list) {
            this.onDisabledRepeatControlSelected = list;
            this.onDisabledRepeatControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public RepeatControlStateElement.State state() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public Map<RepeatControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public Map<RepeatControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public Boolean isRepeatAllDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public List<Method> onEnabledRepeatControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.RepeatControlStateElement
        public List<Method> onDisabledRepeatControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRepeatControlStateElement fromJson(Json json) {
        Builder builder = builder();
        RepeatControlStateElement.State state = json.state;
        if (state != null) {
            builder.state(state);
        }
        Map<RepeatControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<RepeatControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        Boolean bool2 = json.isRepeatAllDisabled;
        if (bool2 != null) {
            builder.isRepeatAllDisabled(bool2);
        }
        if (json.onEnabledRepeatControlSelectedIsSet) {
            builder.addAllOnEnabledRepeatControlSelected(json.onEnabledRepeatControlSelected);
        }
        if (json.onDisabledRepeatControlSelectedIsSet) {
            builder.addAllOnDisabledRepeatControlSelected(json.onDisabledRepeatControlSelected);
        }
        return builder.build();
    }

    public static ImmutableRepeatControlStateElement copyOf(RepeatControlStateElement repeatControlStateElement) {
        if (repeatControlStateElement instanceof ImmutableRepeatControlStateElement) {
            return (ImmutableRepeatControlStateElement) repeatControlStateElement;
        }
        return builder().from(repeatControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RepeatControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_STATE = 1;
        private static final long OPT_BIT_ON_DISABLED_REPEAT_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_REPEAT_CONTROL_SELECTED = 1;
        private EnumMap<RepeatControlStateElement.State, String> altTexts;
        private EnumMap<RepeatControlStateElement.State, String> descriptions;
        private long initBits;

        @Nullable
        private Boolean isDisabled;

        @Nullable
        private Boolean isRepeatAllDisabled;
        private List<Method> onDisabledRepeatControlSelected;
        private List<Method> onEnabledRepeatControlSelected;
        private long optBits;

        @Nullable
        private RepeatControlStateElement.State state;

        private Builder() {
            this.initBits = 1L;
            this.descriptions = new EnumMap<>(RepeatControlStateElement.State.class);
            this.altTexts = new EnumMap<>(RepeatControlStateElement.State.class);
            this.onEnabledRepeatControlSelected = new ArrayList();
            this.onDisabledRepeatControlSelected = new ArrayList();
        }

        public final Builder from(RepeatControlStateElement repeatControlStateElement) {
            ImmutableRepeatControlStateElement.requireNonNull(repeatControlStateElement, "instance");
            state(repeatControlStateElement.state());
            putAllDescriptions(repeatControlStateElement.descriptions());
            putAllAltTexts(repeatControlStateElement.altTexts());
            isDisabled(repeatControlStateElement.isDisabled());
            isRepeatAllDisabled(repeatControlStateElement.isRepeatAllDisabled());
            addAllOnEnabledRepeatControlSelected(repeatControlStateElement.onEnabledRepeatControlSelected());
            addAllOnDisabledRepeatControlSelected(repeatControlStateElement.onDisabledRepeatControlSelected());
            return this;
        }

        @JsonProperty("state")
        public final Builder state(RepeatControlStateElement.State state) {
            this.state = (RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(state, "state");
            this.initBits &= -2;
            return this;
        }

        public final Builder putDescriptions(RepeatControlStateElement.State state, String str) {
            this.descriptions.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutableRepeatControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<RepeatControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableRepeatControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<RepeatControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<RepeatControlStateElement.State, ? extends String> map) {
            for (Map.Entry<RepeatControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableRepeatControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(RepeatControlStateElement.State state, String str) {
            this.altTexts.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutableRepeatControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<RepeatControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableRepeatControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<RepeatControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<RepeatControlStateElement.State, ? extends String> map) {
            for (Map.Entry<RepeatControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((RepeatControlStateElement.State) ImmutableRepeatControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableRepeatControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableRepeatControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        @JsonProperty("isRepeatAllDisabled")
        public final Builder isRepeatAllDisabled(Boolean bool) {
            this.isRepeatAllDisabled = (Boolean) ImmutableRepeatControlStateElement.requireNonNull(bool, "isRepeatAllDisabled");
            return this;
        }

        public final Builder addOnEnabledRepeatControlSelected(Method method) {
            this.onEnabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(method, "onEnabledRepeatControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledRepeatControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(method, "onEnabledRepeatControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledRepeatControlSelected")
        public final Builder onEnabledRepeatControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledRepeatControlSelected.clear();
            return addAllOnEnabledRepeatControlSelected(iterable);
        }

        public final Builder addAllOnEnabledRepeatControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(it.next(), "onEnabledRepeatControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledRepeatControlSelected(Method method) {
            this.onDisabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(method, "onDisabledRepeatControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledRepeatControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(method, "onDisabledRepeatControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledRepeatControlSelected")
        public final Builder onDisabledRepeatControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledRepeatControlSelected.clear();
            return addAllOnDisabledRepeatControlSelected(iterable);
        }

        public final Builder addAllOnDisabledRepeatControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledRepeatControlSelected.add((Method) ImmutableRepeatControlStateElement.requireNonNull(it.next(), "onDisabledRepeatControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableRepeatControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRepeatControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledRepeatControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledRepeatControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("state");
            }
            return "Cannot build RepeatControlStateElement, some of required attributes are not set " + arrayList;
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
