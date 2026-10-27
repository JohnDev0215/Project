package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
import PlaybackInterface.v1_0.ThumbsDownControlStateElement;
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
@Generated(from = "ThumbsDownControlStateElement", generator = "Immutables")
public final class ImmutableThumbsDownControlStateElement extends ThumbsDownControlStateElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Map<ThumbsDownControlStateElement.State, String> altTexts;
    private final Map<ThumbsDownControlStateElement.State, String> descriptions;
    private volatile transient InitShim initShim;
    private final Boolean isDisabled;
    private final List<Method> onDisabledThumbsDownControlSelected;
    private final List<Method> onEnabledThumbsDownControlSelected;
    private final ThumbsDownControlStateElement.State state;

    private ImmutableThumbsDownControlStateElement(Builder builder) {
        this.initShim = new InitShim();
        this.state = builder.state;
        this.descriptions = createUnmodifiableEnumMap(false, false, builder.descriptions);
        this.altTexts = createUnmodifiableEnumMap(false, false, builder.altTexts);
        if (builder.isDisabled != null) {
            this.initShim.isDisabled(builder.isDisabled);
        }
        if (builder.onEnabledThumbsDownControlSelectedIsSet()) {
            this.initShim.onEnabledThumbsDownControlSelected(createUnmodifiableList(true, builder.onEnabledThumbsDownControlSelected));
        }
        if (builder.onDisabledThumbsDownControlSelectedIsSet()) {
            this.initShim.onDisabledThumbsDownControlSelected(createUnmodifiableList(true, builder.onDisabledThumbsDownControlSelected));
        }
        this.isDisabled = this.initShim.isDisabled();
        this.onEnabledThumbsDownControlSelected = this.initShim.onEnabledThumbsDownControlSelected();
        this.onDisabledThumbsDownControlSelected = this.initShim.onDisabledThumbsDownControlSelected();
        this.initShim = null;
    }

    private ImmutableThumbsDownControlStateElement(ThumbsDownControlStateElement.State state, Map<ThumbsDownControlStateElement.State, String> map, Map<ThumbsDownControlStateElement.State, String> map2, Boolean bool, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.state = state;
        this.descriptions = map;
        this.altTexts = map2;
        this.isDisabled = bool;
        this.onEnabledThumbsDownControlSelected = list;
        this.onDisabledThumbsDownControlSelected = list2;
        this.initShim = null;
    }

    @Generated(from = "ThumbsDownControlStateElement", generator = "Immutables")
    private final class InitShim {
        private Boolean isDisabled;
        private byte isDisabledBuildStage;
        private List<Method> onDisabledThumbsDownControlSelected;
        private byte onDisabledThumbsDownControlSelectedBuildStage;
        private List<Method> onEnabledThumbsDownControlSelected;
        private byte onEnabledThumbsDownControlSelectedBuildStage;

        private InitShim() {
            this.isDisabledBuildStage = (byte) 0;
            this.onEnabledThumbsDownControlSelectedBuildStage = (byte) 0;
            this.onDisabledThumbsDownControlSelectedBuildStage = (byte) 0;
        }

        Boolean isDisabled() {
            byte b = this.isDisabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isDisabledBuildStage = (byte) -1;
                this.isDisabled = (Boolean) ImmutableThumbsDownControlStateElement.requireNonNull(ImmutableThumbsDownControlStateElement.super.isDisabled(), "isDisabled");
                this.isDisabledBuildStage = (byte) 1;
            }
            return this.isDisabled;
        }

        void isDisabled(Boolean bool) {
            this.isDisabled = bool;
            this.isDisabledBuildStage = (byte) 1;
        }

        List<Method> onEnabledThumbsDownControlSelected() {
            byte b = this.onEnabledThumbsDownControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEnabledThumbsDownControlSelectedBuildStage = (byte) -1;
                this.onEnabledThumbsDownControlSelected = ImmutableThumbsDownControlStateElement.createUnmodifiableList(false, ImmutableThumbsDownControlStateElement.createSafeList(ImmutableThumbsDownControlStateElement.super.onEnabledThumbsDownControlSelected(), true, false));
                this.onEnabledThumbsDownControlSelectedBuildStage = (byte) 1;
            }
            return this.onEnabledThumbsDownControlSelected;
        }

        void onEnabledThumbsDownControlSelected(List<Method> list) {
            this.onEnabledThumbsDownControlSelected = list;
            this.onEnabledThumbsDownControlSelectedBuildStage = (byte) 1;
        }

        List<Method> onDisabledThumbsDownControlSelected() {
            byte b = this.onDisabledThumbsDownControlSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onDisabledThumbsDownControlSelectedBuildStage = (byte) -1;
                this.onDisabledThumbsDownControlSelected = ImmutableThumbsDownControlStateElement.createUnmodifiableList(false, ImmutableThumbsDownControlStateElement.createSafeList(ImmutableThumbsDownControlStateElement.super.onDisabledThumbsDownControlSelected(), true, false));
                this.onDisabledThumbsDownControlSelectedBuildStage = (byte) 1;
            }
            return this.onDisabledThumbsDownControlSelected;
        }

        void onDisabledThumbsDownControlSelected(List<Method> list) {
            this.onDisabledThumbsDownControlSelected = list;
            this.onDisabledThumbsDownControlSelectedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isDisabledBuildStage == -1) {
                arrayList.add("isDisabled");
            }
            if (this.onEnabledThumbsDownControlSelectedBuildStage == -1) {
                arrayList.add("onEnabledThumbsDownControlSelected");
            }
            if (this.onDisabledThumbsDownControlSelectedBuildStage == -1) {
                arrayList.add("onDisabledThumbsDownControlSelected");
            }
            return "Cannot build ThumbsDownControlStateElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("state")
    public ThumbsDownControlStateElement.State state() {
        return this.state;
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("descriptions")
    public Map<ThumbsDownControlStateElement.State, String> descriptions() {
        return this.descriptions;
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("altTexts")
    public Map<ThumbsDownControlStateElement.State, String> altTexts() {
        return this.altTexts;
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("isDisabled")
    public Boolean isDisabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isDisabled();
        }
        return this.isDisabled;
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("onEnabledThumbsDownControlSelected")
    public List<Method> onEnabledThumbsDownControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEnabledThumbsDownControlSelected();
        }
        return this.onEnabledThumbsDownControlSelected;
    }

    @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
    @JsonProperty("onDisabledThumbsDownControlSelected")
    public List<Method> onDisabledThumbsDownControlSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onDisabledThumbsDownControlSelected();
        }
        return this.onDisabledThumbsDownControlSelected;
    }

    public final ImmutableThumbsDownControlStateElement withState(ThumbsDownControlStateElement.State state) {
        ThumbsDownControlStateElement.State state2 = (ThumbsDownControlStateElement.State) requireNonNull(state, "state");
        return this.state == state2 ? this : new ImmutableThumbsDownControlStateElement(state2, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsDownControlSelected, this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withDescriptions(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
        if (this.descriptions == map) {
            return this;
        }
        return new ImmutableThumbsDownControlStateElement(this.state, createUnmodifiableEnumMap(true, false, map), this.altTexts, this.isDisabled, this.onEnabledThumbsDownControlSelected, this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withAltTexts(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
        if (this.altTexts == map) {
            return this;
        }
        return new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, createUnmodifiableEnumMap(true, false, map), this.isDisabled, this.onEnabledThumbsDownControlSelected, this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withIsDisabled(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isDisabled");
        return this.isDisabled.equals(bool2) ? this : new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, this.altTexts, bool2, this.onEnabledThumbsDownControlSelected, this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withOnEnabledThumbsDownControlSelected(Method... methodArr) {
        return new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withOnEnabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
        if (this.onEnabledThumbsDownControlSelected == iterable) {
            return this;
        }
        return new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onDisabledThumbsDownControlSelected);
    }

    public final ImmutableThumbsDownControlStateElement withOnDisabledThumbsDownControlSelected(Method... methodArr) {
        return new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsDownControlSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableThumbsDownControlStateElement withOnDisabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
        if (this.onDisabledThumbsDownControlSelected == iterable) {
            return this;
        }
        return new ImmutableThumbsDownControlStateElement(this.state, this.descriptions, this.altTexts, this.isDisabled, this.onEnabledThumbsDownControlSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableThumbsDownControlStateElement) && equalTo(0, (ImmutableThumbsDownControlStateElement) obj);
    }

    private boolean equalTo(int i, ImmutableThumbsDownControlStateElement immutableThumbsDownControlStateElement) {
        return this.state.equals(immutableThumbsDownControlStateElement.state) && this.descriptions.equals(immutableThumbsDownControlStateElement.descriptions) && this.altTexts.equals(immutableThumbsDownControlStateElement.altTexts) && this.isDisabled.equals(immutableThumbsDownControlStateElement.isDisabled) && this.onEnabledThumbsDownControlSelected.equals(immutableThumbsDownControlStateElement.onEnabledThumbsDownControlSelected) && this.onDisabledThumbsDownControlSelected.equals(immutableThumbsDownControlStateElement.onDisabledThumbsDownControlSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.state.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.descriptions.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.altTexts.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.isDisabled.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onEnabledThumbsDownControlSelected.hashCode();
        return iHashCode5 + (iHashCode5 << 5) + this.onDisabledThumbsDownControlSelected.hashCode();
    }

    public String toString() {
        return "ThumbsDownControlStateElement{state=" + this.state + ", descriptions=" + this.descriptions + ", altTexts=" + this.altTexts + ", isDisabled=" + this.isDisabled + ", onEnabledThumbsDownControlSelected=" + this.onEnabledThumbsDownControlSelected + ", onDisabledThumbsDownControlSelected=" + this.onDisabledThumbsDownControlSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ThumbsDownControlStateElement", generator = "Immutables")
    static final class Json extends ThumbsDownControlStateElement {

        @Nullable
        Boolean isDisabled;
        boolean onDisabledThumbsDownControlSelectedIsSet;
        boolean onEnabledThumbsDownControlSelectedIsSet;

        @Nullable
        ThumbsDownControlStateElement.State state;

        @Nullable
        Map<ThumbsDownControlStateElement.State, String> descriptions = Collections.emptyMap();

        @Nullable
        Map<ThumbsDownControlStateElement.State, String> altTexts = Collections.emptyMap();

        @Nullable
        List<Method> onEnabledThumbsDownControlSelected = Collections.emptyList();

        @Nullable
        List<Method> onDisabledThumbsDownControlSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("state")
        public void setState(ThumbsDownControlStateElement.State state) {
            this.state = state;
        }

        @JsonProperty("descriptions")
        public void setDescriptions(Map<ThumbsDownControlStateElement.State, String> map) {
            this.descriptions = map;
        }

        @JsonProperty("altTexts")
        public void setAltTexts(Map<ThumbsDownControlStateElement.State, String> map) {
            this.altTexts = map;
        }

        @JsonProperty("isDisabled")
        public void setIsDisabled(Boolean bool) {
            this.isDisabled = bool;
        }

        @JsonProperty("onEnabledThumbsDownControlSelected")
        public void setOnEnabledThumbsDownControlSelected(List<Method> list) {
            this.onEnabledThumbsDownControlSelected = list;
            this.onEnabledThumbsDownControlSelectedIsSet = true;
        }

        @JsonProperty("onDisabledThumbsDownControlSelected")
        public void setOnDisabledThumbsDownControlSelected(List<Method> list) {
            this.onDisabledThumbsDownControlSelected = list;
            this.onDisabledThumbsDownControlSelectedIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public ThumbsDownControlStateElement.State state() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public Map<ThumbsDownControlStateElement.State, String> descriptions() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public Map<ThumbsDownControlStateElement.State, String> altTexts() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public Boolean isDisabled() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public List<Method> onEnabledThumbsDownControlSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ThumbsDownControlStateElement
        public List<Method> onDisabledThumbsDownControlSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableThumbsDownControlStateElement fromJson(Json json) {
        Builder builder = builder();
        ThumbsDownControlStateElement.State state = json.state;
        if (state != null) {
            builder.state(state);
        }
        Map<ThumbsDownControlStateElement.State, String> map = json.descriptions;
        if (map != null) {
            builder.putAllDescriptions(map);
        }
        Map<ThumbsDownControlStateElement.State, String> map2 = json.altTexts;
        if (map2 != null) {
            builder.putAllAltTexts(map2);
        }
        Boolean bool = json.isDisabled;
        if (bool != null) {
            builder.isDisabled(bool);
        }
        if (json.onEnabledThumbsDownControlSelectedIsSet) {
            builder.addAllOnEnabledThumbsDownControlSelected(json.onEnabledThumbsDownControlSelected);
        }
        if (json.onDisabledThumbsDownControlSelectedIsSet) {
            builder.addAllOnDisabledThumbsDownControlSelected(json.onDisabledThumbsDownControlSelected);
        }
        return builder.build();
    }

    public static ImmutableThumbsDownControlStateElement copyOf(ThumbsDownControlStateElement thumbsDownControlStateElement) {
        if (thumbsDownControlStateElement instanceof ImmutableThumbsDownControlStateElement) {
            return (ImmutableThumbsDownControlStateElement) thumbsDownControlStateElement;
        }
        return builder().from(thumbsDownControlStateElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ThumbsDownControlStateElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_STATE = 1;
        private static final long OPT_BIT_ON_DISABLED_THUMBS_DOWN_CONTROL_SELECTED = 2;
        private static final long OPT_BIT_ON_ENABLED_THUMBS_DOWN_CONTROL_SELECTED = 1;
        private EnumMap<ThumbsDownControlStateElement.State, String> altTexts;
        private EnumMap<ThumbsDownControlStateElement.State, String> descriptions;
        private long initBits;

        @Nullable
        private Boolean isDisabled;
        private List<Method> onDisabledThumbsDownControlSelected;
        private List<Method> onEnabledThumbsDownControlSelected;
        private long optBits;

        @Nullable
        private ThumbsDownControlStateElement.State state;

        private Builder() {
            this.initBits = 1L;
            this.descriptions = new EnumMap<>(ThumbsDownControlStateElement.State.class);
            this.altTexts = new EnumMap<>(ThumbsDownControlStateElement.State.class);
            this.onEnabledThumbsDownControlSelected = new ArrayList();
            this.onDisabledThumbsDownControlSelected = new ArrayList();
        }

        public final Builder from(ThumbsDownControlStateElement thumbsDownControlStateElement) {
            ImmutableThumbsDownControlStateElement.requireNonNull(thumbsDownControlStateElement, "instance");
            state(thumbsDownControlStateElement.state());
            putAllDescriptions(thumbsDownControlStateElement.descriptions());
            putAllAltTexts(thumbsDownControlStateElement.altTexts());
            isDisabled(thumbsDownControlStateElement.isDisabled());
            addAllOnEnabledThumbsDownControlSelected(thumbsDownControlStateElement.onEnabledThumbsDownControlSelected());
            addAllOnDisabledThumbsDownControlSelected(thumbsDownControlStateElement.onDisabledThumbsDownControlSelected());
            return this;
        }

        @JsonProperty("state")
        public final Builder state(ThumbsDownControlStateElement.State state) {
            this.state = (ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(state, "state");
            this.initBits &= -2;
            return this;
        }

        public final Builder putDescriptions(ThumbsDownControlStateElement.State state, String str) {
            this.descriptions.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(state, "descriptions key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(str, "descriptions value"));
            return this;
        }

        public final Builder putDescriptions(Map.Entry<ThumbsDownControlStateElement.State, ? extends String> entry) {
            this.descriptions.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            return this;
        }

        @JsonProperty("descriptions")
        public final Builder descriptions(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
            this.descriptions.clear();
            return putAllDescriptions(map);
        }

        public final Builder putAllDescriptions(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ThumbsDownControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.descriptions.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getKey(), "descriptions key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getValue(), "descriptions value"));
            }
            return this;
        }

        public final Builder putAltTexts(ThumbsDownControlStateElement.State state, String str) {
            this.altTexts.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(state, "altTexts key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(str, "altTexts value"));
            return this;
        }

        public final Builder putAltTexts(Map.Entry<ThumbsDownControlStateElement.State, ? extends String> entry) {
            this.altTexts.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            return this;
        }

        @JsonProperty("altTexts")
        public final Builder altTexts(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
            this.altTexts.clear();
            return putAllAltTexts(map);
        }

        public final Builder putAllAltTexts(Map<ThumbsDownControlStateElement.State, ? extends String> map) {
            for (Map.Entry<ThumbsDownControlStateElement.State, ? extends String> entry : map.entrySet()) {
                this.altTexts.put((ThumbsDownControlStateElement.State) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getKey(), "altTexts key"), (String) ImmutableThumbsDownControlStateElement.requireNonNull(entry.getValue(), "altTexts value"));
            }
            return this;
        }

        @JsonProperty("isDisabled")
        public final Builder isDisabled(Boolean bool) {
            this.isDisabled = (Boolean) ImmutableThumbsDownControlStateElement.requireNonNull(bool, "isDisabled");
            return this;
        }

        public final Builder addOnEnabledThumbsDownControlSelected(Method method) {
            this.onEnabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(method, "onEnabledThumbsDownControlSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnEnabledThumbsDownControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEnabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(method, "onEnabledThumbsDownControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onEnabledThumbsDownControlSelected")
        public final Builder onEnabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
            this.onEnabledThumbsDownControlSelected.clear();
            return addAllOnEnabledThumbsDownControlSelected(iterable);
        }

        public final Builder addAllOnEnabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEnabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(it.next(), "onEnabledThumbsDownControlSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnDisabledThumbsDownControlSelected(Method method) {
            this.onDisabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(method, "onDisabledThumbsDownControlSelected element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnDisabledThumbsDownControlSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onDisabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(method, "onDisabledThumbsDownControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onDisabledThumbsDownControlSelected")
        public final Builder onDisabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
            this.onDisabledThumbsDownControlSelected.clear();
            return addAllOnDisabledThumbsDownControlSelected(iterable);
        }

        public final Builder addAllOnDisabledThumbsDownControlSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onDisabledThumbsDownControlSelected.add((Method) ImmutableThumbsDownControlStateElement.requireNonNull(it.next(), "onDisabledThumbsDownControlSelected element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableThumbsDownControlStateElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableThumbsDownControlStateElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEnabledThumbsDownControlSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onDisabledThumbsDownControlSelectedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("state");
            }
            return "Cannot build ThumbsDownControlStateElement, some of required attributes are not set " + arrayList;
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
