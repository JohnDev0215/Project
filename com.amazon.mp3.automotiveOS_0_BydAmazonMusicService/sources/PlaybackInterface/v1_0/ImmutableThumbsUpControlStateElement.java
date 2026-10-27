package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.ThumbsUpControlStateElement;
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
@Generated(from = "ThumbsUpControlStateElement", generator = "Immutables")
public final class ImmutableThumbsUpControlStateElement extends ThumbsUpControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<ThumbsUpControlStateElement.State, String> altTexts;
    private final Map<ThumbsUpControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledThumbsUpControlSelected;
    private final List<Method> onEnabledThumbsUpControlSelected;
    private final ThumbsUpControlStateElement.State state;

    private ImmutableThumbsUpControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.state = builder.state;
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledThumbsUpControlSelectedIsSet()) {
            this.initShim.onEnabledThumbsUpControlSelected(createUnmodifiableList(true, builder.onEnabledThumbsUpControlSelected));
        }
        if (builder.onDisabledThumbsUpControlSelectedIsSet()) {
            this.initShim.onDisabledThumbsUpControlSelected(createUnmodifiableList(true, builder.onDisabledThumbsUpControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledThumbsUpControlSelected = this.initShim.onEnabledThumbsUpControlSelected();
        this.onDisabledThumbsUpControlSelected = this.initShim.onDisabledThumbsUpControlSelected();
        this.initShim = null;
    }

    private ImmutableThumbsUpControlStateElement(ThumbsUpControlStateElement.State state, Map<ThumbsUpControlStateElement.State, String> map, Map<ThumbsUpControlStateElement.State, String> map2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.state = state;
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.onEnabledThumbsUpControlSelected = list;
        this.onDisabledThumbsUpControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "ThumbsUpControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledThumbsUpControlSelected;
        private byte onDisabledThumbsUpControlSelectedBuildStage;
        private List<Method> onEnabledThumbsUpControlSelected;
        private byte onEnabledThumbsUpControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledThumbsUpControlSelectedBuildStage = (byte) 0;
            this.onDisabledThumbsUpControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableThumbsUpControlStateElement.requireNonNull(ImmutableThumbsUpControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledThumbsUpControlSelected() {
            byte b = this.onEnabledThumbsUpControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledThumbsUpControlSelectedBuildStage = (byte) -1;
                this.onEnabledThumbsUpControlSelected = ImmutableThumbsUpControlStateElement.createUnmodifiableList(false, ImmutableThumbsUpControlStateElement.createSafeList(ImmutableThumbsUpControlStateElement.super.onEnabledThumbsUpControlSelected(), true, false));
                this.onEnabledThumbsUpControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledThumbsUpControlSelected;
        }

        void onEnabledThumbsUpControlSelected(List<Method> list) {
            this.onEnabledThumbsUpControlSelected = list;
            this.onEnabledThumbsUpControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledThumbsUpControlSelected() {
            byte b = this.onDisabledThumbsUpControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledThumbsUpControlSelectedBuildStage = (byte) -1;
                this.onDisabledThumbsUpControlSelected = ImmutableThumbsUpControlStateElement.createUnmodifiableList(false, ImmutableThumbsUpControlStateElement.createSafeList(ImmutableThumbsUpControlStateElement.super.onDisabledThumbsUpControlSelected(), true, false));
                this.onDisabledThumbsUpControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledThumbsUpControlSelected;
        }

        void onDisabledThumbsUpControlSelected(List<Method> list) {
            this.onDisabledThumbsUpControlSelected = list;
            this.onDisabledThumbsUpControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledThumbsUpControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledThumbsUpControlSelected");
            }
            if (this.onDisabledThumbsUpControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledThumbsUpControlSelected");
            }
            return "Cannot build ThumbsUpControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("state")
    public ThumbsUpControlStateElement.State state() {
        return this.state;
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("descriptions")
    public Map<ThumbsUpControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("altTexts")
    public Map<ThumbsUpControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("onEnabledThumbsUpControlSelected")
    public List<Method> onEnabledThumbsUpControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledThumbsUpControlSelected();
        }
        return this.onEnabledThumbsUpControlSelected;
    }

    @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
    @JsonProperty("onDisabledThumbsUpControlSelected")
    public List<Method> onDisabledThumbsUpControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledThumbsUpControlSelected();
        }
        return this.onDisabledThumbsUpControlSelected;
    }

    public final ImmutableThumbsUpControlStateElement withState(ThumbsUpControlStateElement.State state) {
        ThumbsUpControlStateElement.State state2 = (ThumbsUpControlStateElement.State) requireNonNull(state, "state");
        return this.state == state2 ? this : new ImmutableThumbsUpControlStateElement(state2, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsUpControlSelected, this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withDescriptions(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
        if (this.descriptions == map) {
            return this;
        }
        return new ImmutableThumbsUpControlStateElement(this.state, createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.onEnabledThumbsUpControlSelected, this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withAltTexts(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.onEnabledThumbsUpControlSelected, this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, this.altTexts, bool2, this.onEnabledThumbsUpControlSelected, this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withOnEnabledThumbsUpControlSelected(Method... methodArr) {
        return new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withOnEnabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledThumbsUpControlSelected == iterable) {
            return this;
        }
        return new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledThumbsUpControlSelected);
    }

    public final ImmutableThumbsUpControlStateElement withOnDisabledThumbsUpControlSelected(Method... methodArr) {
        return new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsUpControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableThumbsUpControlStateElement withOnDisabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledThumbsUpControlSelected == iterable) {
            return this;
        }
        return new ImmutableThumbsUpControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsUpControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableThumbsUpControlStateElement) && equalTo(0, (ImmutableThumbsUpControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableThumbsUpControlStateElement immutableThumbsUpControlStateElement) {
        return this.state.equals(immutableThumbsUpControlStateElement.state) && this.descriptions.equals(immutableThumbsUpControlStateElement.descriptions) && this.altTexts.equals(immutableThumbsUpControlStateElement.altTexts) && this.isDisabled.equals(immutableThumbsUpControlStateElement.isDisabled) && this.onEnabledThumbsUpControlSelected.equals(immutableThumbsUpControlStateElement.onEnabledThumbsUpControlSelected) && this.onDisabledThumbsUpControlSelected.equals(immutableThumbsUpControlStateElement.onDisabledThumbsUpControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.state.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.descriptions.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.altTexts.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.isDisabled.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onEnabledThumbsUpControlSelected.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.onDisabledThumbsUpControlSelected.hashCode();
    }

    public String toString() {
        return "ThumbsUpControlStateElement{state=" + this.state + ", descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", onEnabledThumbsUpControlSelected=" + this.onEnabledThumbsUpControlSelected + ", onDisabledThumbsUpControlSelected=" + this.onDisabledThumbsUpControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ThumbsUpControlStateElement", generator = "Immutables")
    static final class Json extends ThumbsUpControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledThumbsUpControlSelectedIsSet;
        boolean onEnabledThumbsUpControlSelectedIsSet;

        @Nullable
        ThumbsUpControlStateElement.State state;

        @Nullable
        Map<ThumbsUpControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<ThumbsUpControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onEnabledThumbsUpControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledThumbsUpControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("state")
        public void setState(ThumbsUpControlStateElement.State state) {
            this.state = state;
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<ThumbsUpControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<ThumbsUpControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledThumbsUpControlSelected")
        public void setOnEnabledThumbsUpControlSelected(List<Method> list) {
            this.onEnabledThumbsUpControlSelected = list;
            this.onEnabledThumbsUpControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledThumbsUpControlSelected")
        public void setOnDisabledThumbsUpControlSelected(List<Method> list) {
            this.onDisabledThumbsUpControlSelected = list;
            this.onDisabledThumbsUpControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public ThumbsUpControlStateElement.State state() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public Map<ThumbsUpControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public Map<ThumbsUpControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public List<Method> onEnabledThumbsUpControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsUpControlStateElement
        public List<Method> onDisabledThumbsUpControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableThumbsUpControlStateElement fromJson(Json json) {
        Builder builder = builder();
        ThumbsUpControlStateElement.State state = json.state;
        if (state != null) {
            builder.state(state);
        }
        Map<ThumbsUpControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<ThumbsUpControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledThumbsUpControlSelectedIsSet) {
            builder.addAllOnEnabledThumbsUpControlSelected(json.onEnabledThumbsUpControlSelected);
        }
        if (json.onDisabledThumbsUpControlSelectedIsSet) {
            builder.addAllOnDisabledThumbsUpControlSelected(json.onDisabledThumbsUpControlSelected);
        }
        return builder.build();
    }

    public static ImmutableThumbsUpControlStateElement copyOf(ThumbsUpControlStateElement thumbsUpControlStateElement) {
        if (thumbsUpControlStateElement instanceof ImmutableThumbsUpControlStateElement) {
            return (ImmutableThumbsUpControlStateElement) thumbsUpControlStateElement;
        }
        return builder().from(thumbsUpControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ThumbsUpControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_STATE = 1;
        private static final long OPT_BIT_ON_DISABLED_THUMBS_UP_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_THUMBS_UP_CONTROL_SELECTED = 1;
        private EnumMap<ThumbsUpControlStateElement.State, String> altTexts;
        private EnumMap<ThumbsUpControlStateElement.State, String> descriptions;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledThumbsUpControlSelected;
        private List<Method> onEnabledThumbsUpControlSelected;
        private long optBits;

        @Nullable
        private ThumbsUpControlStateElement.State state;

        private Builder() {
            this.initBits = 1L;
            this.descriptions = new EnumMap<>(ThumbsUpControlStateElement.State.class);
            this.altTexts = new EnumMap<>(ThumbsUpControlStateElement.State.class);
            this.onEnabledThumbsUpControlSelected = new ArrayList();
            this.onDisabledThumbsUpControlSelected = new ArrayList();
        }

        public final Builder from(ThumbsUpControlStateElement thumbsUpControlStateElement) {
            ImmutableThumbsUpControlStateElement.requireNonNull(thumbsUpControlStateElement, "instance");
            state(thumbsUpControlStateElement.state());
            putAllDescriptions(thumbsUpControlStateElement.descriptions());
            putAllAltTexts(thumbsUpControlStateElement.altTexts());
            isDisabled(thumbsUpControlStateElement.isDisabled());
            addAllOnEnabledThumbsUpControlSelected(thumbsUpControlStateElement.onEnabledThumbsUpControlSelected());
            addAllOnDisabledThumbsUpControlSelected(thumbsUpControlStateElement.onDisabledThumbsUpControlSelected());
            return this;
        }

        @JsonProperty("state")
        public final Builder state(ThumbsUpControlStateElement.State state) {
            this.state = (ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(state, "state");
            this.initBits &= -2;
            return this;
        }

        public final Builder putDescriptions(ThumbsUpControlStateElement.State state, String str) {
            this.descriptions.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<ThumbsUpControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ThumbsUpControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(ThumbsUpControlStateElement.State state, String str) {
            this.altTexts.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<ThumbsUpControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<ThumbsUpControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ThumbsUpControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((ThumbsUpControlStateElement.State) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableThumbsUpControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableThumbsUpControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledThumbsUpControlSelected(Method method) {
            this.onEnabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(method, "onEnabledThumbsUpControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledThumbsUpControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(method, "onEnabledThumbsUpControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledThumbsUpControlSelected")
        public final Builder onEnabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledThumbsUpControlSelected.clear();
            return addAllOnEnabledThumbsUpControlSelected(iterable);
        }

        public final Builder addAllOnEnabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(it.next(), "onEnabledThumbsUpControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledThumbsUpControlSelected(Method method) {
            this.onDisabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(method, "onDisabledThumbsUpControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledThumbsUpControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(method, "onDisabledThumbsUpControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledThumbsUpControlSelected")
        public final Builder onDisabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledThumbsUpControlSelected.clear();
            return addAllOnDisabledThumbsUpControlSelected(iterable);
        }

        public final Builder addAllOnDisabledThumbsUpControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledThumbsUpControlSelected.add((Method) ImmutableThumbsUpControlStateElement.requireNonNull(it.next(), "onDisabledThumbsUpControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableThumbsUpControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableThumbsUpControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledThumbsUpControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledThumbsUpControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("state");
            }
            return "Cannot build ThumbsUpControlStateElement, some of required attributes are not set " + arrayList;
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
